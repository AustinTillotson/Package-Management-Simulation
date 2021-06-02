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
        System.out.printf("Station %d: . . . Active . . . moving packages into station on input conveyor C%d.\n", stationNum, inputConveyor.conveyorNumber);
        System.out.printf("Station %d: . . . Active . . . moving packages out of station on output conveyor C%d.\n", stationNum, outputConveyor.conveyorNumber);
        workloadCounter--;
        System.out.printf("Station %d: Number of packages groups left to move is: %d.\n", stationNum, workloadCounter);
    }

    public void run() {
        System.out.printf("Routing Station %d: Input connection is set to conveyor number C%d.\n" +
                "Routing Station %d: Output connection is set to conveyor number C%d.\n",
                stationNum, inputConveyor.conveyorNumber, stationNum, outputConveyor.conveyorNumber);
        System.out.printf("Routing Station %d: Workload set. Station %d has a total of %d package groups to move.\n",
                stationNum, stationNum, workload);

        // loop till counter is zero
        while(workloadCounter > 0) {
            boolean acquiredLocks = false;
            // loop while station does not have locks
            while(!acquiredLocks) {
                // Lock is available - claim input lock
                if(inputConveyor.requestConveyor()) {
                    System.out.printf("Station %d: LOCK ACQUIRED! Now holding lock on input conveyor C%d.\n",
                            stationNum, inputConveyor.conveyorNumber);
                    // Lock is available- claim output lock
                    if(outputConveyor.requestConveyor()) {
                        System.out.printf("Station %d: LOCK ACQUIRED! Now holding lock on output conveyor C%d.\n",
                                stationNum, outputConveyor.conveyorNumber);
                        // both Locks are claimed
                        acquiredLocks = true;
                        // move packages
                        doWork();
                        // release locks
                        inputConveyor.releaseConveyor();
                        System.out.printf("Station %d: Unlocks input conveyor C%d.\n", stationNum, inputConveyor.conveyorNumber);
                        outputConveyor.releaseConveyor();
                        System.out.printf("Station %d: Unlocks output conveyor C%d.\n", stationNum, outputConveyor.conveyorNumber);
                        // sleep thread
                        putToSleep();
                    }
                    // Deadlock avoidance scenario
                    // Output lock could not be claimed - release input lock
                    else {
                        inputConveyor.releaseConveyor();
                        System.out.printf("Station %d: Unable to lock output conveyor C%d – releasing lock on input conveyor C%d.\n",
                        stationNum, outputConveyor.conveyorNumber, inputConveyor.conveyorNumber);
                        // sleep before trying again
                        putToSleep();
                    }
                }
            }
        } // end loops

        // workload is done - terminate thread
        System.out.printf("* * Station %d: Workload successfully completed. * * Station Going Idle!\n", stationNum);
    } // end run
}
