package edu.nyu.cs9053.homework10;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CountDownLatch;

public class ThreadedWordCounter extends AbstractThreadedCounter implements WordCounter {

    private final BlockingQueue<Runnable> work;
    private final List<Thread> threads;
    private final CountDownLatch latch;

    public ThreadedWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.work = getWork();
        this.threads = getThreads();
        this.latch = new CountDownLatch(concurrencyFactor);
    }

    @Override public void count(String fileContents, String word, Callback callback) {
        // TODO - implement this class using Thread objects; one Thread per {@link #concurrencyFactor}
        // HINT - break up {@linkplain fileContents} and distribute the work across the threads
        // HINT - do not create the Thread objects in this method
        int concurrencyFactor = getConcurrencyFactor();
        AtomicLong totalCountNumber = new AtomicLong(0);
        String[] words = fileContents.split("\\W+");
        int lengthPerThread = words.length / concurrencyFactor;
        try {
            for (int i = 0; i < concurrencyFactor - 1; i++) {
                work.put(createNewWork(words, word, i*lengthPerThread, (i+1)*lengthPerThread, totalCountNumber));
            }
            work.put(createNewWork(words, word, (concurrencyFactor-1)*lengthPerThread, words.length, totalCountNumber));
            latch.await();
        } catch (InterruptedException ioe) {
            Thread.currentThread().interrupt();
        }
        callback.counted(totalCountNumber.get());
        stop();
    }

    @Override public void stop() {
        // TODO - stop the threads
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private Runnable createNewWork(String[] words, String word, int startIndex, int endIndex, AtomicLong totalCount) {
        return new Runnable() {
            private final AtomicInteger threadCount = new AtomicInteger(0);
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
        };
    }

}