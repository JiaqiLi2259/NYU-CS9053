package edu.nyu.cs9053.homework4.hierarchy;

public class Bobsledder extends Sledder {

    private final int numberOfAttendedGames;

    public Bobsledder(String name, int age, String sledColor, int numberOfAttendedGames) {
        super(name, age, sledColor);
        this.numberOfAttendedGames = numberOfAttendedGames;
    }

    public int getNumberOfAttendedGames() {
        return numberOfAttendedGames;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Bobsledder that = (Bobsledder) obj;
        return (getName() == null ? that.getName() ==  null : getName().equals(that.getName()))
                && (getAge() == that.getAge())
                && (getSledColor() == null ? that.getSledColor() == null : getSledColor().equals(that.getSledColor()))
                && (numberOfAttendedGames == that.getNumberOfAttendedGames());
    }

    @Override
    public int hashCode() {
        int hash = (getName() == null ? 0 : getName().hashCode());
        hash = 11 * hash + Integer.valueOf(getAge()).hashCode();
        hash = 11 * hash + (getSledColor() == null ? 0 : getSledColor().hashCode());
        hash = 11 * hash + Integer.valueOf(numberOfAttendedGames).hashCode();
        return hash;
    }

}
