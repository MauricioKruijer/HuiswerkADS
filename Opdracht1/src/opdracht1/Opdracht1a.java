package opdracht1;

import java.io.*;
import java.util.*;

/**
 * Schrijf een programma dat deze array bijhoudt en vult met 50 objecten met 
 * willekeurige x en y waardes. Zorg er voor dat je de maximale x en y waardes 
 * en het aantal te genereren objecten als parameters op kan geven.
 * @author Jeff
 */
public class Opdracht1a {

    /**
     * Main method, does everything and calls from Methods.java
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException { 
       
        System.out.println("Max X?");
        int maxX = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        
        System.out.println("Max Y?");
        int maxY = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        
        // Make array with maxX and maxY
        
        
        System.out.println("How many objects?");
        int numObj = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        //System.out.println("1000000");
        //int numObj = 1000000;
        
        for(int mult = 8; mult < 18; mult++) {
        int bottomhalfcounter = 0;
        for(int c = 0; c < 3; c++) {

            maxX = (int) (Math.pow((double) 2, (double) mult) * 100);
            int[][] world = new int[maxX][maxY];
            
            // Fill world with numObj objects
            Random random = new Random();
            int randomX;
            int randomY;
            for(int i = 0; i < numObj; i++) {
                randomX = random.nextInt(maxX);
                randomY = random.nextInt(maxY);
                world[randomX][randomY]++;
            }
            //System.out.println("WorldGen took " +  + "ms");
            

            // Now check how many cells have 2 OR MORE
            int counter = 0;
            for(int x = 0; x < maxX; x++) {
                for(int y = 0; y < maxY; y++) {
                    if(world[x][y] >= 2) {
                        counter++;
                    }
                }
            }
            

            //System.out.println("There are " + BottomHalf(world) + " objects in the "
              //               + "bottom half of the world.");
            
            
            

            
            //for(int i = 0; i < maxY; i++) {
            //    System.out.println("Row " + (i+1) + " has " + RowCount(world, i) + " objects.");
            //}
            
            
            
            
/*
            System.out.println(counter + " out of " + (maxX*maxY) + " are >= 2");
            int total = maxX * maxY;
            double chance = ((double)counter / (double)total) * 100.0;
            System.out.println("That makes a chance of: " + chance + "% (sorry about the comma)");
            gcounter += counter;

*/
            BeginTime();
            //System.out.println("There are " + CountEmpty(world) + " empty tiles");
            CountEmpty(world);
            bottomhalfcounter += EndTime();
            /*
            for(int i = 0; i < maxY; i++) {
                System.out.println("Row " + (i+1) + "'s first tile with at least one object: " + GetFirstFilledTile(world, i));
            }
            
            
/**/
           // System.out.println("Longest joined sequence of tiles: " + LongestTileRow(world));
    
            /**/
        }
        
        
        
            bottomhalfcounter = bottomhalfcounter / 6;
            System.out.println("Time: " + bottomhalfcounter + "ms (" + maxX + "x" + maxY + ", " + numObj + ")");
            //System.out.println("That makes a chance of: " + chance + "% (sorry about the comma)");
        }   
    }
    
    
    public static long beginMillis;
    public static void BeginTime() {
        beginMillis = System.currentTimeMillis();
    }
    
    public static int EndTime() {
        int difference;
        difference = (int) (System.currentTimeMillis() - beginMillis);
        return difference;
    }
    
    /**
     *
     * @param world
     * @return the number of objects beneath the middle-y value
     */
    public static int BottomHalf(int[][] world) {
        // Method to check the bottom half of the world
        // Returns the number of objects beneath the middle-y value
        int countt = 0;
        
        int maxX = world.length;
        int maxY = world[0].length;
        
        // Get the middle line
        int middle = maxY / 2;
        System.out.println("MaxY: " + maxY + ", so middle: " + middle);
        
        for(int x = 0; x < maxX; x++) {
            for(int y = middle; y < maxY; y++) {
                countt += world[x][y];
            }
        }
        
        return countt;
    }
    
    public static int RowCount(int[][] world, int row) {
        int maxX = world.length;
        int count = 0;
        for(int x = 0; x < maxX; x++) {
            count += world[x][row];
        }
        return count;

    }
    
    public static int CountEmpty(int[][] world) {
        int count = 0;
        
        int maxX = world.length;
        int maxY = world[0].length;
        
        for(int x = 0; x < maxX; x++) {
            for(int y = 0; y < maxY; y++) {
                if(world[x][y] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static int GetFirstFilledTile(int[][] world, int row) {
        int maxX = world.length;
        int x = 0;
        boolean found = false;
        
        while(x < maxX && found == false) {
            if(world[x][row] != 0) {
                found = true;
            } else {
                x++;
            }
        }
        
        if(found == false)
            return -1;
        
        return x;
    }
    
    public static int LongestTileRow(int[][] world) {        
        int maxX = world.length;
        int maxY = world[0].length;
        
        int[] rowMax = new int[maxY];
        
        for(int y = 0; y < maxY; y++) {
            // rows
            int count = 0;
            int lastMax = 0;
            for(int x = 0; x < maxX; x++) {
                // columns
                if(world[x][y] != 0) {
                    count++;
                } else {
                    if(count > lastMax) {
                        lastMax = count;
                    }
                    count = 0;
                }
            }
            
            rowMax[y] = lastMax;
        }
        
        Arrays.sort(rowMax);
        
        return rowMax[maxY - 1];
    }
}
