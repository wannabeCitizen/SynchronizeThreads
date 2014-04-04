// Michael Skirpan
// Concurrent Programming
// Homework 3
// BoolArray Class
//Uses a Boolean Array to keep track of threads that need synchronized

import java.util.concurrent.atomic.AtomicBoolean;

public class BoolArray extends FooBar {
	//Array needs to be AtomicBooleans so we can incrementing is protected
	private AtomicBoolean[] syncUs = new AtomicBoolean[THREADS];
	
	public BoolArray(){
		for (int i = 0; i < THREADS; i++){
			syncUs[i] = new AtomicBoolean(false);
		}
	}

	//Barrier Code - ensures each thread spins until the thread before it gets to barrier
	@Override
	public void barrier(){
		int id = THREAD_ID.get();

		if(id == 0){
			syncUs[id].set(true);
			while (syncUs[THREADS-1].get() != true){}
		}else {
			while (syncUs[id - 1].get() != true){}
			syncUs[id].set(true);
			while (syncUs[THREADS-1].get() != true) {}
		}
	}

	//Main method for testing
	public static void main(String[] args){
		BoolArray testBool = new BoolArray();
		try{
			testBool.testFooBar();
		} catch (Exception e){
			System.out.println("Shit fucked up!");
		}
	}

}