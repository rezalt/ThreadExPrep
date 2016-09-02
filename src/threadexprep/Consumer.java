/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadexprep;

import java.util.concurrent.BlockingQueue;

/**
 * This class will be a consumer thread that take()'s calculated Fibonacci
 * numbers from an ArrayBlockingQueue(S2) and outputs them to the console. It
 * will also track the sum of all the calculated Fibonacci numbers, which will
 * be printed to the console at the end of the program.
 *
 * @author rezalt
 */
public class Consumer implements Runnable {

    BlockingQueue S2 = null;

    public Consumer( BlockingQueue S2 )
    {
        this.S2 = S2;
    }
    
    
    public void run()
    {
        long fibonacciNr = 0;
        long fibonacciSum = 0;
        String message;
        boolean run = true;
        while (run)
        {
            //nåede fik ikke mit interface til at virke, so this runs forever.
            try
            {
                fibonacciNr = (Long)S2.take();
                System.out.println( "Calculated Fibonacci nr: " + fibonacciNr);
                fibonacciSum += fibonacciNr;
            }
            catch (Exception e)
            {
                System.out.println("woot" + e);
            }
         
        }
           System.out.println("Sum of Fibonacci numbers: " + fibonacciSum);


    }
}
