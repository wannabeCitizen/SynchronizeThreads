// Michael Skirpan
// Concurrent Programming
// Homework 3
// FooBar class
//Abstract class to use with either the Shared Counter or Boolean Array Schema

import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Thread.sleep;

public abstract class FooBar {

    //Set the number of threads
	protected static final int THREADS = 16;
	Thread[] thread = new Thread[THREADS];

    //Atmoic set of Thread ID #'s
	final protected ThreadLocal<Integer> THREAD_ID = new ThreadLocal<Integer>(){
       final private AtomicInteger id = new AtomicInteger(0);
        //Increments 
        	protected Integer initialValue(){
            	return id.getAndIncrement();
        }
    };

    public void foo(){
    	try{
    		sleep(20);
    	} catch(InterruptedException e){
    		e.printStackTrace();
    	}
    }

    public void bar(){
    	try{
    		sleep(20);
    	} catch(InterruptedException e){
    		e.printStackTrace();
    	}
    }

    //To be overwritten for the different schemas
    public void barrier(){}


    //Get threads started up
    public void testFooBar() throws Exception {

      for (int i = 0; i < THREADS; i++) {
	  	thread[i] = new FooBarThread();
      }
      for (int i = 0; i < THREADS; i++) {
	  	thread[i].start();
      }
      for (int i = 0; i < THREADS; i++) {
	  	thread[i].join();
      }

    }

    //Run the code in the desired order.
    class FooBarThread extends Thread {
    	public void run() {
    		foo();
    		barrier();
    		bar();
    	}
    }

}