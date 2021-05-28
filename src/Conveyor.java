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

    private int conveyorNumber;

    // create conveyor lock
    private Lock lock = new ReentrantLock();

    public Conveyor(int number) {
        conveyorNumber = number;
    }

    public int test() {
        return conveyorNumber;
    }
}
