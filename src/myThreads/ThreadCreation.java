package myThreads;

class MyThreadClass extends Thread {
	int instance;
	public MyThreadClass() {
		this.instance = 0;
		System.out.println("Demo for Thread class method");
	}
	public void run(){
		System.out.println("Starting the Thread thread");
		for(int i=0;i<5;i++){
			this.instance++;
			System.out.println(instance);
		}
		System.out.println("Terminating the thread");
	}
}
class MyRunnableClass implements Runnable {
	
	int instance;
	public MyRunnableClass() {
		this.instance = 0;
		System.out.println("Demo for Runnable interface implementation method");
	}
	public void run(){
		System.out.println("Starting the Runnable thread");
		for(int i=0;i<5;i++){
			this.instance++;
			System.out.println(instance);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Terminating the thread");
	}
}
public class ThreadCreation {

	public static void main(String[] args) {
		
		MyRunnableClass tRunnable = new MyRunnableClass();
		Thread threadRunnable = new Thread(tRunnable);
		threadRunnable.start();
		while(tRunnable.instance!=5){
			try {
				System.out.println("Thread Sleeping in main()");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		MyThreadClass t = new MyThreadClass();
		t.start();
		while(t.instance!=5){
			try {
				System.out.println("Thread Sleeping in main()");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
