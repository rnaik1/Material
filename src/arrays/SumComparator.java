package arrays;
import java.util.Comparator;

public class SumComparator implements Comparator<PairSum> {
	public int compare(PairSum o1, PairSum o2) {
		if (o1.sum > o2.sum) return 1;
		if (o1.sum < o2.sum) return -1;
		return 0;
	}
}