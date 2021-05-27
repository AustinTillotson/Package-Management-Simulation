/*
        Name: Austin Tillotson
        Course: CNT 4717 Summer 2021
        Assignment title: Project 1 - Multi-threaded programming in Java
        Date: June 6, 2021

        Class: Station
 */

import java.util.Random;

public class Station implements Runnable {
    private static Random generator = new Random();
    private int totalPackages;
    private int stationNum;
    public Station (int packages, int station) { // constructor
        totalPackages = packages;
        stationNum = station;
    }
    public void run() {
        // to do
        System.out.printf("Total packages to deliver at station %d: %d\n", stationNum, totalPackages);
    }
}
