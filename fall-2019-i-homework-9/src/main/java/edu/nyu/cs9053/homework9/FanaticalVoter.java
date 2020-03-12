package edu.nyu.cs9053.homework9;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class FanaticalVoter implements Voter {

    private static final int NUMBER_OF_TYPES_OF_BALLOT = 2;

    private static final String OBAMA_NAME = "Barack Obama";

    private static final String BUSH_NAME = "George W. Bush";

    private final Semaphore semaphore;

    private final Random random;

    private final PresidentialBallot presidentialBallot;

    protected FanaticalVoter(Semaphore semaphore) {
        this.random = new Random();
        this.semaphore = semaphore;
        this.presidentialBallot = getRandomBallot();
    }

    private PresidentialBallot getRandomBallot() {
        int randomNumber = random.nextInt(NUMBER_OF_TYPES_OF_BALLOT);
        PresidentialBallot presidentialBallot = null;
        switch (randomNumber) {
            case 0:
                presidentialBallot = new ObamaBallot(true, OBAMA_NAME);
                break;
            case 1:
                presidentialBallot = new BushBallot(true, BUSH_NAME);
                break;
            default:
                break;
        }
        return presidentialBallot;
    }

    @Override
    public QueueNumber vote(Queue queue) {
        try {
            semaphore.acquire();
            try {
                if (queue.full()) {
                    return null;
                }
                QueueNumber queueNumber = queue.addBallot(presidentialBallot);
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
