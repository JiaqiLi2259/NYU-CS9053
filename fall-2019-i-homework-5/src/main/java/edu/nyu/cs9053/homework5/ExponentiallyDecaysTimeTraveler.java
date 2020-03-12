package edu.nyu.cs9053.homework5;

public class ExponentiallyDecaysTimeTraveler extends GeneralTimeTraveler {

    private final double decayConstant;

    private double remainingYearsOfTravel = 100;

    public ExponentiallyDecaysTimeTraveler(String name, double decayConstant) {
        super(name);
        this.decayConstant = decayConstant;
    }

    @Override public Double getRemainingYearsOfTravel() {
        return remainingYearsOfTravel;
    }

    @Override public void adjust(Time unit, int amount, boolean ahead) {
        double actualAmountOfYear = super.convertTime(unit, amount);
        remainingYearsOfTravel *= Math.pow(Math.E,-decayConstant*actualAmountOfYear);
    }

}
