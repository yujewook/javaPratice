package java_harshly_part3;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class SpinLockBool {
	private final AtomicBoolean owner = new AtomicBoolean();
	
	public void lock() {
		while(!owner.compareAndSet(false,true))
			LockSupport.parkNanos(1);
	}
	public void unlock() {
		owner.set(false);
	}
}