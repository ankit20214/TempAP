package com.example.stickhero;

public class Fruit extends GameObjects{
    private static int totalFruits;

    public static int getTotalFruits() {
        return totalFruits;
    }

    public static void setTotalFruits(int totalFruits) {
        Fruit.totalFruits = totalFruits;
    }

    public Fruit getFruit(){
        return null;
    }
    public void spawnFruit(int coordinateX,int coordinateY){}
}
