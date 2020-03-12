package edu.nyu.cs9053.homework9;

import java.util.concurrent.Semaphore;

/**
 * User: blangel
 */
public class Factory {

    private static final Semaphore SEMAPHORE = new Semaphore(1);

    public static Voter createVoter() {
        return new FanaticalVoter(SEMAPHORE);
    }

    public static VoteCounter createVoteCounter() {
        return new OnlineVoteCounter(SEMAPHORE);
    }

}
