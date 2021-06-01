/*
        Name: Austin Tillotson
        Course: CNT 4717 Summer 2021
        Assignment title: Project 1 - Multi-threaded programming in Java
        Date: June 6, 2021

        Class: Simulation
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation
{
    public static void main(String[] args)
    {
        int numStations = 0;
        try
        { // config setup
            System.out.println("\n CNT 4714 - Project 1 - Summer 2021 \n");
            System.out.println("\n * * * SIMULATION BEGINS * * * \n");
            File input = new File("config.txt");
            Scanner scanInput = new Scanner(input);
            numStations = scanInput.nextInt();

            // create thread pool of size equalling number of stations in config
            ExecutorService application = Executors.newFixedThreadPool(numStations);

            // creates array of conveyors
            Conveyor[] conveyors = new Conveyor[numStations];

            // fills array with conveyors
            for(int i = 0; i < numStations; i++) {
                conveyors[i] = new Conveyor(i);
            }
            // creates stations
            for(int i = 0; i < numStations; i++) {
                try
                { // starts station threads using station constructor
                    application.execute(new Station(scanInput.nextInt(), i, conveyors[i], conveyors[(i + numStations - 1) % numStations]));
                } catch (Exception executorException) {
                    executorException.printStackTrace();
                }
            }
            application.shutdown();
            while(!application.isTerminated()) {
                // simulation is ongoing
            }
            // simulation terminates
            System.out.println("\n * * * * ALL WORKLOADS COMPLETE * * * * SIMULATION ENDS * * * *");

        } catch (FileNotFoundException fileException) {
            System.out.println("Error: Config file not found.\n");
            fileException.printStackTrace();
        }
    }
}
