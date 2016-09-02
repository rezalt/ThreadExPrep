/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadexprep;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This is the main thread that starts all the producer threads as well as the
 * consumer thread.
 *
 * This thread will also fill the shared data structure (S1) which is an
 * ArrayBlockingQeue with numbers that will be calculated as Fibonacci numbers.
 *
 *
 *
 * @author rezalt
 */
public class ThreadExPrep {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException 
    {
        // Here are the numbers that will be calculated
        String line = "4 5 8 12 21 22 34 35 36 37 42";
        String[] parts = line.split(" ");

        BlockingQueue S1 = new ArrayBlockingQueue(parts.length + 1);
        BlockingQueue S2 = new ArrayBlockingQueue(parts.length + 1);

        // Inserting numbers into S1
        for (String part : parts)
        {
            S1.put(Integer.parseInt(part));
            System.out.println("Inserting into S1: " + part);
        }
     
        System.out.println("-----INSERT-DONE------\n");

        System.out.println("Insert number of producer threads to use \n from 1-4 \n");
        // Here we create the input scanner.
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt())
        {
            scan.next();
        }
        int answer = scan.nextInt();

        if (answer <= 0)
        {
            answer = 1;
        }
        else if (answer > 4)
        {
            answer = 4;
        }

        System.out.println("Starting " + answer + " producer threads.");
        Producer producer = new Producer(S1, S2);
        ArrayList<Thread> threadArray = new ArrayList();
        
        // Here we start recording the time for the program to finish.
        long start = System.nanoTime();
        
        // adding and starting chosen number of threads.
        for (int i = 0; i <= answer - 1; i++)
        {
            threadArray.add(new Thread(new Producer(S1, S2)));
            threadArray.get(i).start();
        }

        System.out.println("Starting consumer thread.");
        Consumer consumer = new Consumer(S2);
        Thread c = new Thread(consumer);
        c.start();
        c.join();
        long end = System.nanoTime();
        System.out.println("Time Sequental: "+(end-start));

    }

}
