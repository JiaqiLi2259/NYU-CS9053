package edu.nyu.cs9053.homework10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractThreadedCounter extends AbstractConcurrencyFactorProvider{

    private final BlockingQueue<Runnable> work;
    private final List<Thread> threads;

    public AbstractThreadedCounter(int concurrencyFactor) {
        super(concurrencyFactor);
        this.work = new LinkedBlockingQueue<Runnable>();
        this.threads = new ArrayList<>(concurrencyFactor);
        Runnable threadCode = getThreadCode();
        for (int i = 0; i < concurrencyFactor; i++){
            threads.add(new Thread(threadCode));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    protected Runnable getThreadCode() {
        return new Runnable() {
            @Override
            public void run() {
                while (! Thread.currentThread().isInterrupted()) {
                    try {
                        Runnable todo = work.poll(100, TimeUnit.MILLISECONDS);
                        if (todo != null) {
                            todo.run();
                        }
                    } catch (InterruptedException ioe) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };
    }

    protected BlockingQueue<Runnable> getWork() {
        return this.work;
    }

    protected List<Thread> getThreads() {
        return this.threads;
    }

}
