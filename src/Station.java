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
    private int inputConveyor;
    private  int outputConveyor;
    public Station (int packages, int station, int output) { // constructor
        totalPackages = packages;
        stationNum = station;
        inputConveyor = station;
        outputConveyor = output;
    }
    public void run() {
        // to do
        System.out.printf("Total packages to deliver at station %d: %d\nInput conveyor is %d and output is %d\n", stationNum, totalPackages, inputConveyor, outputConveyor);
    }
}
