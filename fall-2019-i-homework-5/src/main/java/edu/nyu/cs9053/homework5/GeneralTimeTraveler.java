package edu.nyu.cs9053.homework5;

public abstract class GeneralTimeTraveler implements TimeTraveler {

    private final String name;

    public GeneralTimeTraveler(String name) {
        this.name = name;
    }
    
    public abstract Double getRemainingYearsOfTravel();
    
    public abstract void adjust(Time unit, int amount, boolean ahead);
    
    public String getName() {
        return name;
    }

    public double convertTime(Time unit, int amount) {
        double timeInUnitOfYear;
        if (unit == Time.Days) {
            timeInUnitOfYear = amount / 365.0;
        }else {
            timeInUnitOfYear = amount / (365 * 24.0);
        }
        return timeInUnitOfYear;
    }
}
