package myThreads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Chopstick {
	private Lock lock;

	public Chopstick() {
		lock = new ReentrantLock();
	}

	public boolean pickUp() {
		return lock.tryLock();
	}

	public void putDown() {
		lock.unlock();	
	}
}

class Philosopher extends Thread {
	private final int maxPause = 100;
	private int bites = 2;
	private Chopstick left;
	private Chopstick right;
	private int index;

	public Philosopher(int i, Chopstick left, Chopstick right) {
		index = i;
		this.left = left;
		this.right = right;
	}

	public void eat() {
		System.out.println("Philosopher " + index + ": start eating");
		if (pickUp()) {
			chew();
			putDown();
			System.out.println("Philosopher " + index + ": done eating");
		} else {
			System.out.println("Philosopher " + index + ": gave up on eating");
		}
	}

	public boolean pickUp() {
		pause();
		if (!left.pickUp()) {
			return false;
		} 
		pause();
		if (!right.pickUp()) {
			left.putDown();
			return false;
		} 
		pause();
		return true;
	}

	public void chew() {
		System.out.println("Philosopher " + index + ": eating");
		pause();
	}

	public void putDown() {
		left.putDown();
		right.putDown();
	}

	public void run() {
		for (int i = 0; i < bites; i++) {
			eat();
		}
	}
	
	public void pause() {
		try {
			int pause = (int) (Math.random() * (maxPause + 1));
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class DiningPhilosophers {
	public static int size = 2;

	public static void main(String[] args) {
		Chopstick[] chopsticks = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			chopsticks[i] = new Chopstick();
		}

		Philosopher[] philosophers = new Philosopher[size];
		for (int i = 0; i < size; i++) {
			Chopstick left = chopsticks[i];
			Chopstick right = chopsticks[(i + 1) % size];
			philosophers[i] = new Philosopher(i, left, right);
		}

		for (int i = 0; i < size; i++) {
			philosophers[i].start();
		}		
	}
}
