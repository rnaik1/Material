package arrays;
import java.util.Arrays;

public class FourSumArrayProblem {

	public static boolean noCommon(PairSum a, PairSum b)
	{
		if (a.first == b.first || a.first == b.sec ||
				a.sec == b.first || a.sec == b.sec)
			return false;
		return true;
	}

	public static void findFourElements (int arr[], int n, int X)
	{
		int i, j;
		int size = (n*(n-1))/2;
		PairSum[] aux = new PairSum[size];
		int k = 0;
		for (i = 0; i < n-1; i++)
		{
			for (j = i+1; j < n; j++)
			{
				aux[k] = new PairSum();
				aux[k].setSum(arr[i] + arr[j]);
				aux[k].setFirst(i);
				aux[k].setSec(j);
				k++;
			}
		}
		
		Arrays.sort(aux, new SumComparator());
		
		i = 0;
		j = size-1;
		while (i<j)
		{
			if ((aux[i].sum + aux[j].sum == X) && noCommon(aux[i], aux[j]))
			{
				System.out.println(arr[aux[i].first] + "\t" + arr[aux[i].sec] + "\t" + arr[aux[j].first] + "\t" + arr[aux[j].sec]);
				return;
				/*i++;
				j--;*/
			}
			else if (aux[i].sum + aux[j].sum < X)
				i++;
			else
				j--;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int arr[] = {1,2,3,4,5,-2};
		int arr[] = {10, 20, 30, 40, 1, 2};
		findFourElements(arr, 6, 101);
	}

}
