package edu.nyu.cs9053.homework9;

public abstract class PresidentialBallot implements Ballot {

    private final boolean isSupport;

    private final String presidentName;

    public PresidentialBallot(boolean isSupport, String presidentName) {
        this.isSupport = isSupport;
        this.presidentName = presidentName;
    }

    @Override
    public boolean isYes() {
        return isSupport;
    }

    public String getPresidentName() {
        return presidentName;
    }

}
