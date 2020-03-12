package edu.nyu.cs9053.homework8;

public class WeightedLambdaJob extends LambdaJob {

    private final int value;

    WeightedLambdaJob(int startingTime, int finalTime, int value) {
        super(startingTime, finalTime);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
