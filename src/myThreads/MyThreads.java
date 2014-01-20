package myThreads;

public class MyThreads {

	public class HeavyWorkRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println("Doing heavy processing - START "+Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
				//Get database connection, delete unused data from DB
				doDBProcessing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Doing heavy processing - END "+Thread.currentThread().getName());
		}

		private void doDBProcessing() throws InterruptedException {
			Thread.sleep(5000);
		}

	}

	public class MyThread extends Thread {

		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			System.out.println("MyThread - START "+Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
				//Get database connection, delete unused data from DB
				doDBProcessing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MyThread - END "+Thread.currentThread().getName());
		}

		private void doDBProcessing() throws InterruptedException {
			Thread.sleep(5000);
		}

	}
	
	class MyRunnable implements Runnable{

	    @Override
	    public void run() {
	        System.out.println("Thread started:::"+Thread.currentThread().getName());
	        try {
	            Thread.sleep(4000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Thread ended:::"+Thread.currentThread().getName());
	    }
	    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println("Thread Id: " + Thread.activeCount());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		//t.start();

		/*Thread t1 = new Thread(new MyThreads().new HeavyWorkRunnable(), "t1");
		Thread t2 = new Thread(new MyThreads().new HeavyWorkRunnable(), "t2");
		System.out.println("Starting Runnable threads");
		t1.start();
		t2.start();
		System.out.println("Runnable Threads has been started");
		Thread t3 = new MyThreads().new MyThread("t3");
		Thread t4 = new MyThreads().new MyThread("t4");
		System.out.println("Starting MyThreads");
		t3.start();
		t4.start();
		System.out.println("MyThreads has been started");*/

		Thread t1 = new Thread(new MyThreads().new MyRunnable(), "t1");
		Thread t2 = new Thread(new MyThreads().new MyRunnable(), "t2");
		Thread t3 = new Thread(new MyThreads().new MyRunnable(), "t3");

		t1.start();

		//start second thread after waiting for 2 seconds or if it's dead
		try {
			t1.join(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t2.start();

		//start third thread only when first thread is dead
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t3.start();

		//let all threads finish execution before finishing main thread
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All threads are dead, exiting main thread");
	}

}
