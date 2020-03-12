package edu.nyu.cs9053.homework10;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:50 PM
 */
public class ExecutorFilesWordCounter extends AbstractConcurrencyFactorProvider implements FilesWordCounter {

    private final ExecutorService executor;

    public ExecutorFilesWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        executor = Executors.newFixedThreadPool(concurrencyFactor);
    }

    @Override public void count(Map<String, String> files, String word, Callback callback) {
        // TODO - implement this class using calls to an ExecutorService; with one call per {@linkplain concurrencyFactor}.
        // HINT - do not create the ExecutorService object in this method
        Iterator fileIterator = files.entrySet().iterator();
        CountDownLatch latch = new CountDownLatch(files.entrySet().size());
        while (fileIterator.hasNext()) {
            Map.Entry fileEntry =  (Map.Entry) fileIterator.next();
            String fileName = (String) fileEntry.getKey();
            String fileContent = (String) fileEntry.getValue();
            ExecutorWordCounter ewc = new ExecutorWordCounter(getConcurrencyFactor());
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    WordCounter.Callback wordCounterCallback = new WordCounter.Callback() {
                        @Override
                        public void counted(long count) {
                            callback.counted(fileName, count);
                        }
                    };
                    ewc.count(fileContent, word, wordCounterCallback);
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException ioe) {
            Thread.currentThread().interrupt();
        }
        stop();
    }

    @Override public void stop() {
        // TODO - stop the executor
        executor.shutdown();
    }

}