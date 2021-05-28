/*
        Name: Austin Tillotson
        Course: CNT 4717 Summer 2021
        Assignment title: Project 1 - Multi-threaded programming in Java
        Date: June 6, 2021

        Class: Conveyor
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conveyor {

    int conveyorNumber;

    // create conveyor lock
    private Lock lock = new ReentrantLock();

    // constructor
    public Conveyor(int number) {
        conveyorNumber = number;
    }

    // needed methods: requestConveyor and releaseConveyor
    // requestConveyor:
    public boolean requestConveyor() {
        // use tryLock
        // return true if lock was acquired
        if(lock.tryLock())
            return true;
        // return false if lock is not free
        else
            return false;
    }

    // releaseConveyor: release the lock
    public void releaseConveyor() {
        lock.unlock();
    }

    /*public int test() {
        return conveyorNumber;
    }*/
}
