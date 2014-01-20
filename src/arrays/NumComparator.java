package arrays;

import java.util.Comparator;

class NumComparator implements Comparator<Integer> {
	public int compare(Integer o1, Integer o2) {
		if (Integer.parseInt(o1.toString() + o2.toString()) > Integer.parseInt(o2.toString()+o1.toString())) return -1;
		if (Integer.parseInt(o1.toString() + o2.toString()) < Integer.parseInt(o2.toString()+o1.toString())) return 1;
		return 0;
	}
}
