package myThreads;

class ProcessingThread implements Runnable{
	private int count;

	@Override
	public void run() {
		for(int i=1; i< 5; i++){
			System.out.println(Thread.currentThread().getName() + "\t" + i);
			processSomething(i);
			count++;
			System.out.println("count: " + count);
		}
	}

	public int getCount() {
		return this.count;
	}

	private void processSomething(int i) {
		// processing some job
		try {
			//System.out.println(i*1000);
			System.out.println(Thread.currentThread().getName() + "\t" + i);
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ThreadSafety {

	public static void main(String[] args) throws InterruptedException {

		ProcessingThread pt = new ProcessingThread();
		Thread t1 = new Thread(pt, "t1");
		t1.start();
		Thread t2 = new Thread(pt, "t2");
		t2.start();
		//wait for threads to finish processing
		t1.join();
		t2.join();
		System.out.println("Processing count="+pt.getCount());
	}
}