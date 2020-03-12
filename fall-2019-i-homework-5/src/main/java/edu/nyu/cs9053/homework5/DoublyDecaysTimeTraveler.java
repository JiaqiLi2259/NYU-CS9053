package edu.nyu.cs9053.homework5;

public class DoublyDecaysTimeTraveler extends GeneralTimeTraveler {

    private static final double DECAY_FACTOR = 2.0;

    private double remainingYearsOfTravel = 100;

    public DoublyDecaysTimeTraveler(String name) {
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
