package edu.nyu.cs9053.homework4.hierarchy;

public abstract class WinterSportPlayer {

    private final String name;

    private final int age;

    public WinterSportPlayer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
