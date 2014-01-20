package misc;

public class MyHashTable {
	/* take the capacity as prime number to reduce the collision */
	private static int SIZE = 31;
	/* initialize array to store value */
	
	private int[] tableValues = new int[SIZE];
	
	public synchronized void put(String key, Integer value) {
		if (value == null || key == null) {
			throw new IllegalArgumentException("The key or value cannot be null");
		}
		int index = hash(key.hashCode()) % SIZE;
		tableValues[index] = value;
		//return value;
	}

	public synchronized Integer get(String key) {
		int index = hash(key.hashCode()) % SIZE;
		return tableValues[index];
	}

	private synchronized int hash(int h) {
		/*h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);*/
		return h%SIZE;
	}

	public static void main(String arg[]) {
		MyHashTable table = new MyHashTable();
		/* store 10 values in Hashtable */
		for (int i = 0; i < 10; i++) {
			table.put("key" + i, i);
		}
		/* retrive values */
		for (int i = 0; i < 10; i++) {
			System.out.println(table.get("key" + i));
		}
	}
}
