package arrays;

public class Sort012 {

	void sort012(int a[])
	{
		int lo = 0;
		int hi = a.length - 1;
		int i = 0;

		while(i <= hi)
		{
			switch(a[i])
			{
			case 0:
				int s1temp = a[lo];
				a[lo] = a[i];
				a[i] = s1temp;
				lo++;i++;
				break;
			case 1:
				i++;
				break;
			case 2:
				int s2temp = a[hi];
				a[hi] = a[i];
				a[i] = s2temp;
				hi--;
				break;
			}
		}
		for (int j = 0; j < a.length; j++)
			System.out.print(a[j] + "\t");
	}

	public static void main(String[] args) {
		int arr[] = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
		//int arr[] = {0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1};
		new Sort012().sort012(arr);

	}

}
