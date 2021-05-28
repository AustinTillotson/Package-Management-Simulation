/*
        Name: Austin Tillotson
        Course: CNT 4717 Summer 2021
        Assignment title: Project 1 - Multi-threaded programming in Java
        Date: June 6, 2021

        Class: Station
 */

import java.util.Random;

public class Station implements Runnable {

    protected static int MAXSLEEP = 500;

    protected static Random sleepTime = new Random();
    protected int workload;
    protected int workloadCounter;
    protected int stationNum;
    protected Conveyor inputConveyor;
    protected  Conveyor outputConveyor;

    // constructor
    public Station (int packages, int number, Conveyor input, Conveyor output) {
        workload = packages;
        workloadCounter = packages;
        stationNum = number;
        inputConveyor = input;
        outputConveyor = output;
    }

    // sleep method using random
    public void putToSleep() {
        try {
            Thread.sleep(sleepTime.nextInt(MAXSLEEP-1+1)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // do work method
    public void doWork() {

        // print messages and do work as following describes

        // flow package from input conveyor to station
        // 9) println that package moved from input to station
        System.out.printf("Station %d: . . . Active. . . moving packages into station on input conveyor C%d.\n", stationNum, inputConveyor.conveyorNumber);

        // flow package from output conveyor to station
        // 10) println that package moved from station to output
        System.out.printf("Station %d: . . .Active. . .moving packages out of station on output conveyor C%d.\n", stationNum, outputConveyor.conveyorNumber);

        // decrement package counter

        // reduce number of packages left by 1
        // 11) println that package successfully moved through the system
        workloadCounter--;
        System.out.printf("Station %d: Number of packages groups left to move is: %d.\n", stationNum, workloadCounter);

        //// sleep thread maybe
    }

    public void run() {
        // to do

        // 1,2) add println for input and output conveyor connections for station X
        // 3) add println for workload set of station X

        // loop till counter is zero
        while(workloadCounter > 0) {
            // set variable that station has both locks to false
            // loop while station does not have locks
            // try to get input lock
            // try to get output lock
            // if both locks, set variable to true
            // doWork
            doWork();
            // release both locks, set variable
            // print messages
            // else
            // print messages
            // sleep station
        }

        // end loops

        // print messages and do work as following describes
        // decrement package counter
        // sleep thread

        // acquire input Conveyor Lock
        // 4) println that Lock was acquired

        // acquire output Conveyor Lock
        // 5) println that Lock was acquired
        // if lock could not be acquired, free input lock
        // 7) println that lock was not acquired and input lock was freed

        // release input Conveyor Lock
        // 6) println that input lock was released

        // release output Conveyor Lock
        // 8) println that output lock was released

        // put thread to sleep if workload is done
        // 12) println that station has completed workload

        //System.out.printf("I am Station %d. I have %d packages to move. My conveyors are %d and %d.\n", stationNum, totalPackages, inputConveyor.test(), outputConveyor.test());
    }
}
