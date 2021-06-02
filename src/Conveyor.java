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

    // request Lock method
    public boolean requestConveyor() {
        // Lock is available - get lock and return true
        if(lock.tryLock())
            return true;
        // Lock is not available - return false
        else
            return false;
    }

    // release Lock method
    public void releaseConveyor() {
        lock.unlock();
    }
}
