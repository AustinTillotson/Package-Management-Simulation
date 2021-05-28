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
    private Conveyor inputConveyor;
    private  Conveyor outputConveyor;
    public Station (int packages, int number, Conveyor input, Conveyor output) { // constructor
        totalPackages = packages;
        stationNum = number;
        inputConveyor = input;
        outputConveyor = output;
    }
    public void run() {
        // to do
        System.out.printf("I am Station %d. I have %d packages to move. My conveyors are %d and %d.\n", stationNum, totalPackages, inputConveyor.test(), outputConveyor.test());
    }
}
