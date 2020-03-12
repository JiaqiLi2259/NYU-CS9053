package edu.nyu.cs9053.homework4.hierarchy;

public class Curler extends WinterSportPlayer {

    private final int numberOfAttendedGames;

    public Curler(String name, int age, int numberOfAttendedGames) {
        super(name, age);
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
        Curler that = (Curler) obj;
        return (getName() == null ? that.getName() ==  null : getName().equals(that.getName()))
                && (getAge() == that.getAge())
                && (numberOfAttendedGames == that.getNumberOfAttendedGames());
    }

    @Override
    public int hashCode() {
        int hash = (getName() == null ? 0 : getName().hashCode());
        hash = 11 * hash + Integer.valueOf(getAge()).hashCode();
        hash = 11 * hash + Integer.valueOf(numberOfAttendedGames).hashCode();
        return hash;
    }

}
