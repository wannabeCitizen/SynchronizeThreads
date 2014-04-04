// Michael Skirpan
// Concurrent Programming
// Homework 3
// SharedCounter class

public class SharedCounter extends FooBar {

	//Need to set shared counter to volatile so changes propagate through bus
	private volatile int counter = 0; 
	private TTASLock lock = new TTASLock(); 

	//Barrier code for this scheme; has each thread acquiring a lock
	@Override
	public void barrier(){
		lock.lock();
		try{
			counter++;
		}finally{
			lock.unlock();
		}

		while(counter != THREADS){}
	}

	//Main method for testing.
	public static void main(String[] args){
		SharedCounter testCounter = new SharedCounter();
		try{
			testCounter.testFooBar();
		} catch (Exception e){
			System.out.println("Shit fucked up!");
		}
	}
}