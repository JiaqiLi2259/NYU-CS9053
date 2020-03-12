package edu.nyu.cs9053.homework10;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.lang.InterruptedException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * User: blangel
 * Date: 11/16/14
 * Time: 3:50 PM
 */
public class ThreadedFilesWordCounter extends AbstractThreadedCounter implements FilesWordCounter {

    private final BlockingQueue<Runnable> work;
    private final List<Thread> threads;

    public ThreadedFilesWordCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.work = getWork();
        this.threads = getThreads();
    }

    @Override public void count(Map<String, String> files, String word, Callback callback) {
        // TODO - implement this class using Thread objects; one Thread per {@link #concurrencyFactor} with each Thread handling at most one file at one time
        // HINT - do not create the ExecutorService object in this method
        Iterator fileIterator = files.entrySet().iterator();
        CountDownLatch latch = new CountDownLatch(files.entrySet().size());
        try {
            while (fileIterator.hasNext()) {
                work.put(createNewWork(fileIterator, word, callback, latch));
            }
            latch.await();
        } catch (InterruptedException ioe) {
            Thread.currentThread().interrupt();
        }
        stop();
    }

    @Override public void stop() {
        // TODO - stop the threads
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private Runnable createNewWork(Iterator fileIterator, String word, Callback callback, CountDownLatch latch) {
        Map.Entry fileEntry =  (Map.Entry) fileIterator.next();
        String fileName = (String) fileEntry.getKey();
        String fileContent = (String) fileEntry.getValue();
        ThreadedWordCounter twc = new ThreadedWordCounter(getConcurrencyFactor());
        return new Runnable() {
            @Override
            public void run() {
                WordCounter.Callback wordCounterCallback = new WordCounter.Callback() {
                    @Override
                    public void counted(long count) {
                        callback.counted(fileName, count);
                    }
                };
                twc.count(fileContent, word, wordCounterCallback);
                latch.countDown();
            }
        };
    }

}