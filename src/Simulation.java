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

public class Simulation {
    public static void main(String[] args) {
        int numStations = 0;
        try {
            File input = new File("config.txt");
            Scanner scanInput = new Scanner(input);
            numStations = scanInput.nextInt();
            for(int i = 0; i < numStations; i++) {
                System.out.printf("%d\n", scanInput.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Config file not found.\n");
            e.printStackTrace();
        }
    }
}
