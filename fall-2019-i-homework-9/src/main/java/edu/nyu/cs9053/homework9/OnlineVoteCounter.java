package edu.nyu.cs9053.homework9;

import java.util.concurrent.Semaphore;

public class OnlineVoteCounter implements VoteCounter {

    private final Semaphore semaphore;

    protected OnlineVoteCounter(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public QueueNumber count(Queue from) {
        try {
            semaphore.acquire();
            try {
                if (from.isEmpty()) {
                    return null;
                }
                QueueNumber queueNumber = from.getQueueNumber();
                return queueNumber;
            } finally {
                semaphore.release();
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        }
    }

}
