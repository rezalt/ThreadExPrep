/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadexprep;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class will be a producer thread that calculates Fibonacci numbers from
 * an ArrayBlockingQueue(S1), and put()'s them into another
 * ArrayBlockingQueue(S2).
 *
 * @author rezalt
 */
public class Producer implements Runnable {

    BlockingQueue S1 = null;
    BlockingQueue S2 = null;

    public Producer(BlockingQueue S1, BlockingQueue S2)
    {
        this.S1 = S1;
        this.S2 = S2;
    }

    @Override
    public void run()
    {
        long fibonacciNr = 0;
        int calculate = 0;
        while (!S1.isEmpty())
        {
            try
            {
                calculate = (int) S1.poll();
                //System.out.println("Starting calculation on: " + calculate);
                fibonacciNr = fib((long) calculate);
                S2.put(fibonacciNr);
                // System.out.println("Calculation done.");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }
    }


private long fib(long n)
    {
        if ((n == 0) || (n == 1))
        {
            return n;
        }
        else
        {
            return fib(n - 1) + fib(n - 2);
        }
    }

}
