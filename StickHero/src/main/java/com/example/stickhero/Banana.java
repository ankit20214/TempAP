package com.example.stickhero;

public class Banana extends Fruit{
    final private int POINTS=10;
    private static int totalBananas;

    public int getPOINTS() {
        return POINTS;
    }

    public static int getTotalBananas() {
        return totalBananas;
    }

    public static void setTotalBananas(int totalBananas) {
        Banana.totalBananas = totalBananas;
    }
}
