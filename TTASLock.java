// Michael Skirpan
// Concurrent Programming
// Homework 3
// TTAS Lock Class
//Test, Test, and Set Lock to make sure threads spin on their own cache
//Rather than spinning in the shared space; clogging up the bus

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class TTASLock implements Lock {
	AtomicBoolean state = new AtomicBoolean(false);

	public void lock() {
		while (true) {
			//Check if the lock is free before pouncing - spin on my cache
			while (state.get()) {};
			//Once it's free, go in and try to obtain
			if (!state.getAndSet(true))
				return;
		}
	}

	public void unlock() {
		state.set(false);
	}

    // Any class implementing Lock must provide these methods
	public Condition newCondition() {
	throw new java.lang.UnsupportedOperationException();
    }
    public boolean tryLock(long time,
			   TimeUnit unit)
	throws InterruptedException {
	throw new java.lang.UnsupportedOperationException();
    }
    public boolean tryLock() {
	throw new java.lang.UnsupportedOperationException();
    }
    public void lockInterruptibly() throws InterruptedException {
	throw new java.lang.UnsupportedOperationException();
    }
}