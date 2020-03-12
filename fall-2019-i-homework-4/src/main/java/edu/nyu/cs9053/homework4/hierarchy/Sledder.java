package edu.nyu.cs9053.homework4.hierarchy;

public abstract class Sledder extends WinterSportPlayer {
    private final String sledColor;

    public Sledder(String name, int age, String sledColor) {
        super(name, age);
        this.sledColor = sledColor;
    }

    public String getSledColor() {
        return sledColor;
    }
}
