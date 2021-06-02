# PackageManagementSimulation
 A mutli-threaded Java program simulating a package shipping management system, CNT 4714 Project 1.
 
This program simulates a package shipping management system where a station with a given workload (the amount of packages it has to move) moves its package from an input conveyor to an output conveyor. The program builds the system based on a given input.

# How it works
The program uses multi-threads for the stations. Each station will attempt to get the Locks of its respective input and output conveyors. If successful, the station will move a package and then sleep before trying again. If the Locks are not available, the thread will sleep before trying again. A deadlock scenario is avoided by requiring a station to have both Locks, if it fails to get a Lock, it must release the Lock it holds, ensuring the case of each station holding a single Lock never occurs. The conveyor object contains its Lock with methods for passing access to the Lock. The main simulation reads the user config, creates a system of stations and conveyors and terminates once all the stations have finished their workloads.

# How To Use
The program reads in an input from config.txt

This file should contain int values.

The first line represents how many stations (and similarly the number of conveyors) the system will have. The following n lines (n being the number of stations chosen) will each contain a single integer representing the workload of the station (how many packages the station has to move).

From there, the simulation will run, printing each step during the process till it terminates after all stations are done with their workloads.
