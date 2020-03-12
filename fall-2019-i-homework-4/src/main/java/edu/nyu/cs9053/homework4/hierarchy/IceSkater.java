package edu.nyu.cs9053.homework4.hierarchy;

public abstract class IceSkater extends WinterSportPlayer {

    private final int skateSize;

    public IceSkater(String name, int age, int skateSize) {
        super(name, age);
        this.skateSize = skateSize;
    }

    public int getSkateSize() {
        return skateSize;
    }
}
