
package myThreads;

import java.io.IOException;

class lock{}

class rwmonitor extends Thread
{
	public static lock l=new lock();
	public static boolean writing=false;
	public int thread_id;
	public static int data=0;
	public static int reader_num=0;
	String type;
	rwmonitor(int i,String s)
	{
		thread_id=i;
		type=s;
	}
	public void run()
	{
		if(type.equals("reader"))
		{
			keepreading();
		}
		else
		{
			keepwriting();
		}
	}
	public void keepreading()
	{
		acquirereadlock();
		System.out.println(type+" "+thread_id+" reads "+data);
		releasedreadlock();

	}
	public void keepwriting()
	{

		acquirewritelock();
		data++;
		System.out.println(type+" "+thread_id+" writes "+data);
		releasedwritelock();

	}
	public void acquirereadlock()
	{
		synchronized(l)
		{
			while(writing)
			{
				try{
					System.out.println(type+" "+thread_id+"waiting");
					l.wait();
				}catch(InterruptedException e){}}
			reader_num++;
		}


	}
	public void acquirewritelock()
	{
		synchronized(l)
		{
			while(writing||reader_num>0)
			{
				try{
					System.out.println(type+" "+thread_id+"waiting");
					l.wait();
				}catch(InterruptedException e){}}
			writing=true;
		}

	}
	public void releasedreadlock()
	{
		synchronized(l)
		{
			--reader_num;
			System.out.println("reader"+thread_id+" complete");
			if(reader_num==0)
			{
				l.notifyAll();
			}
		}

	}
	public void releasedwritelock()
	{
		synchronized(l)
		{
			writing=false;
			System.out.println("writer"+thread_id+" complete");
			l.notifyAll();
		}

	}
}
class ReaderWriter
{
	public static void main(String args[])throws IOException
	{
		int i;
		rwmonitor a[]=new rwmonitor[4];
		for(i=0;i<2;i++)
			a[i]=new rwmonitor(i,"reader");
		for(i=2;i<4;i++)
			a[i]=new rwmonitor(i,"writer");
		for(i=3;i>=0;i--)
			a[i].start();
	}
}