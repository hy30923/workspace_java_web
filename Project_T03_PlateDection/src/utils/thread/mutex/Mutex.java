package utils.thread.mutex;

public class Mutex {
	
	private boolean isLocked = false;
	
	public synchronized void lock() throws InterruptedException {
		
	    while(this.isLocked) 
	        wait();
	    this.isLocked= true;
	    
	}

	public synchronized void unlock() throws InterruptedException{
		
	    this.isLocked= false;
	    this.notify();
    }
}