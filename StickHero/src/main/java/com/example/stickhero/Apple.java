package com.example.stickhero;

public class Apple extends Fruit{
    final private int POINTS=20;

    public int getPOINTS() {
        return POINTS;
    }

    public static int getTotalApples() {
        return totalApples;
    }

    public static void setTotalApples(int totalApples) {
        Apple.totalApples = totalApples;
    }

    private static int totalApples;
}
