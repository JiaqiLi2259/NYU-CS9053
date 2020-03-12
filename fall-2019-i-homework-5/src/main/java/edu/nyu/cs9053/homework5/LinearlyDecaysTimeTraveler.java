package edu.nyu.cs9053.homework5;

public class LinearlyDecaysTimeTraveler extends GeneralTimeTraveler {

    private static final double DECAY_FACTOR = 1.0;

    private double remainingYearsOfTravel = 100;

    public LinearlyDecaysTimeTraveler(String name) {
        super(name);
    }

    @Override public Double getRemainingYearsOfTravel() {
        return remainingYearsOfTravel;
    }

    @Override public void adjust(Time unit, int amount, boolean ahead) {
        double actualAmountOfYear = super.convertTime(unit, amount);
        remainingYearsOfTravel -= actualAmountOfYear * DECAY_FACTOR;
    }

}
