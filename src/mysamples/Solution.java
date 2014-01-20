package mysamples;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("rawtypes")
public class Solution implements Iterable{

	private String[] circularArray;
	private int count = 0;
	private int popIx = 0;
	private int pushIx = 0;

	public Solution(int capacity) {
		circularArray = new String[capacity];
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public void push(String item) {
		if (count == circularArray.length) {
			circularArray[pushIx] = item;
			pushIx = (pushIx + 1) % circularArray.length;
		}
		else{
			circularArray[pushIx] = item;
			pushIx = (pushIx + 1) % circularArray.length;
			if(count <= circularArray.length)
				count++;
		}
	}

	public String pop() {
		if (isEmpty()) {
			throw new RuntimeException("Buffer is Empty :(");
		}
		String item = circularArray[popIx];
		circularArray[popIx] = null;
		count--;
		popIx = (popIx + 1) % circularArray.length;
		return item;
	}

	public Iterator iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator {

		int i = popIx;

		public boolean hasNext() {
			return i < pushIx;
		}

		public String next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return circularArray[i++];
		}
		
		@Override
		public void remove() {}
	}

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Solution cb = new Solution(N);
		char[] cmdInst = br.readLine().toCharArray();
		while(true){
			switch(cmdInst[0]){
			case 'A':
				int nPushEles = Character.getNumericValue(cmdInst[2]);
				for(int i=0;i<nPushEles;i++)
					cb.push(br.readLine());
				cmdInst = br.readLine().toCharArray();
				break;
			case 'R':
				int nPopEles = Character.getNumericValue(cmdInst[2]);
				for(int i=0;i<nPopEles;i++)
					cb.pop();	
				cmdInst = br.readLine().toCharArray();
				break;
			case 'L':
				Iterator<String> itr = cb.iterator();
				while(itr.hasNext()){
					System.out.println(itr.next());
				}
				cmdInst = br.readLine().toCharArray();
				break;
			case 'Q':
				System.exit(0);
				break;
			}
		}
	}
}