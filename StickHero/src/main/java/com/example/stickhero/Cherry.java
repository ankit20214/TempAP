package com.example.stickhero;

public class Cherry extends Fruit{
    final private int POINTS=30;
    private static int totalCherries;

    public int getPOINTS() {
        return POINTS;
    }

    public static int getTotalCherries() {
        return totalCherries;
    }

    public static void setTotalCherries(int totalCherries) {
        Cherry.totalCherries = totalCherries;
    }
}
