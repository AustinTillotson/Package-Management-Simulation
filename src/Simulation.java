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
            File input = new File("config.txt");
            Scanner scanInput = new Scanner(input);
            numStations = scanInput.nextInt();
            ExecutorService application = Executors.newFixedThreadPool(numStations);
            for(int i = 0; i < numStations; i++) {
                try
                {
                    application.execute(new Station(scanInput.nextInt(), i, ((i + numStations - 1) % numStations)));
                } catch (Exception executorException) {
                    executorException.printStackTrace();
                }
            }
            while(!application.isTerminated()) {
                // loop till threads terminate
            }
            application.shutdown();
        } catch (FileNotFoundException fileException) {
            System.out.println("Error: Config file not found.\n");
            fileException.printStackTrace();
        }
    }
}
