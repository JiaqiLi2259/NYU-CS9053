package edu.nyu.cs9053.homework10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorWordCounter extends AbstractConcurrencyFactorProvider implements WordCounter {

    private final ExecutorService executor;
    private final CountDownLatch latch;

    public ExecutorWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.latch = new CountDownLatch(concurrencyFactor);
        this.executor = Executors.newFixedThreadPool(concurrencyFactor);
    }

    @Override public void count(String fileContents, String word, Callback callback) {
        // TODO - implement this class using calls to an ExecutorService; one call per {@link #concurrencyFactor}
        // HINT - break up {@linkplain fileContents} and distribute the work across the calls
        // HINT - do not create the ExecutorService object in this method
        int concurrencyFactor = getConcurrencyFactor();
        AtomicLong totalCountNumber = new AtomicLong(0);
        String[] words = fileContents.split("\\W+");
        int lengthPerThread = words.length / concurrencyFactor;
        for (int i = 0; i < concurrencyFactor - 1; i++) {
            executor.submit(new WordCounterExecutor(words, word, i*lengthPerThread, (i+1)*lengthPerThread, totalCountNumber));
        }
        executor.submit(new WordCounterExecutor(words, word, (concurrencyFactor-1)*lengthPerThread, words.length, totalCountNumber));
        try {
            latch.await();
        } catch (InterruptedException ioe) {
            Thread.currentThread().interrupt();
        }
        callback.counted(totalCountNumber.get());
        stop();
    }

    @Override public void stop() {
        // TODO - stop the executor
        executor.shutdown();
    }

    private class WordCounterExecutor implements Runnable{
        private final String[] words;
        private final String word;
        private final int startIndex;
        private final int endIndex;
        private final AtomicLong totalCount;
        private final AtomicInteger threadCount;

        public WordCounterExecutor(String[] words, String word, int startIndex, int endIndex, AtomicLong totalCount) {
            this.words = words;
            this.word = word;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.totalCount = totalCount;
            this.threadCount = new AtomicInteger(0);
        }

        @Override
        public void run() {
            for (int index = startIndex; index < endIndex; index++) {
                if (words[index].equals(word)) {
                    threadCount.getAndIncrement();
                }
            }
            totalCount.getAndAdd(threadCount.get());
            latch.countDown();
        }
    }

}