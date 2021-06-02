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
        System.out.printf("Station %d: . . . Active . . . moving packages into station on input conveyor C%d.\n", stationNum, inputConveyor.conveyorNumber);

        // flow package from output conveyor to station
        // 10) println that package moved from station to output
        System.out.printf("Station %d: . . . Active . . . moving packages out of station on output conveyor C%d.\n", stationNum, outputConveyor.conveyorNumber);

        // decrement package counter

        // reduce number of packages left by 1
        // 11) println that package successfully moved through the system
        workloadCounter--;
        System.out.printf("Station %d: Number of packages groups left to move is: %d.\n", stationNum, workloadCounter);

        // sleep thread
        putToSleep();
    }

    public void run() {
        // to do

        // 1,2) add println for input and output conveyor connections for station X
        System.out.printf("Routing Station %d: Input connection is set to conveyor number C%d.\n" +
                "Routing Station %d: Output connection is set to conveyor number C%d.\n",
                stationNum, inputConveyor.conveyorNumber, stationNum, outputConveyor.conveyorNumber);
        // 3) add println for workload set of station X
        System.out.printf("Routing Station %d: Workload set. Station %d has a total of %d package groups to move.\n",
                stationNum, stationNum, workload);

        // loop till counter is zero
        while(workloadCounter > 0) {
     //       System.out.printf("Station %d: Current workload %d.\n", stationNum, workloadCounter);
            // set variable that station has both locks to false
            boolean acquiredLocks = false;
            // loop while station does not have locks
            while(!acquiredLocks) {
                // try to get input lock
                // acquire input Conveyor Lock
                // 4) println that Lock was acquired
                if(inputConveyor.requestConveyor()) {
                    System.out.printf("Station %d: LOCK ACQUIRED! Now holding lock on input conveyor C%d.\n",
                            stationNum, inputConveyor.conveyorNumber);
                    // try to get output lock
                    // acquire output Conveyor Lock
                    // 5) println that Lock was acquired
                    if(outputConveyor.requestConveyor()) {
                        System.out.printf("Station %d: LOCK ACQUIRED! Now holding lock on output conveyor C%d.\n",
                                stationNum, outputConveyor.conveyorNumber);
                        // if both locks, set variable to true
                        acquiredLocks = true;
                        // doWork
                        doWork();
                        // release both locks, set variable
                        // release input Conveyor Lock
                        // 6) println that input lock was released
                        inputConveyor.releaseConveyor();
                        System.out.printf("Station %d: Unlocks input conveyor C%d.\n", stationNum, inputConveyor.conveyorNumber);
                        // release output Conveyor Lock
                        // 8) println that output lock was released
                        outputConveyor.releaseConveyor();
                        System.out.printf("Station %d: Unlocks output conveyor C%d.\n", stationNum, outputConveyor.conveyorNumber);
                        // print messages
                    }
                    else {
                        // else
                        // print messages
                        // if lock could not be acquired, free input lock
                        // 7) println that lock was not acquired and input lock was freed
                        inputConveyor.releaseConveyor();
                        System.out.printf("Station %d: Unable to lock output conveyor C%d – releasing lock on input conveyor C%d.\n",
                        stationNum, outputConveyor.conveyorNumber, inputConveyor.conveyorNumber);
                        // sleep station
                        putToSleep();
                    }
                }
            }
        }

        // put thread to sleep if workload is done
        // 12) println that station has completed workload
        System.out.printf("* * Station %d: Workload successfully completed. * * Station Going Idle!\n", stationNum);

        // end loops

        //System.out.printf("I am Station %d. I have %d packages to move. My conveyors are %d and %d.\n", stationNum, totalPackages, inputConveyor.test(), outputConveyor.test());
    }
}
