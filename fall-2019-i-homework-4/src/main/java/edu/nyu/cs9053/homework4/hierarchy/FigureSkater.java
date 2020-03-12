package edu.nyu.cs9053.homework4.hierarchy;

public class FigureSkater extends IceSkater {

    private final int numberOfAttendedGames;

    public FigureSkater(String name, int age, int skateSize, int numberOfAttendedGames) {
        super(name, age, skateSize);
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
        FigureSkater that = (FigureSkater) obj;
        return (getName() == null ? that.getName() ==  null : getName().equals(that.getName()))
                && (getAge() == that.getAge())
                && (getSkateSize() == that.getSkateSize())
                && (numberOfAttendedGames == that.getNumberOfAttendedGames());
    }

    @Override
    public int hashCode() {
        int hash = (getName() == null ? 0 : getName().hashCode());
        hash = 11 * hash + Integer.valueOf(getAge()).hashCode();
        hash = 11 * hash + Integer.valueOf(getSkateSize()).hashCode();
        hash = 11 * hash + Integer.valueOf(numberOfAttendedGames).hashCode();
        return hash;
    }

}
