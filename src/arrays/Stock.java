package arrays;

class Interval
{
	int buy;
	int sell;
};

public class Stock {

	// This function finds the buy sell schedule for maximum profit
	static void stockBuySell(int price[], int n)
	{
		// Prices must be given for at least two days
		if (n == 1)
			return;

		int count = 0; // count of solution pairs

		// solution vector
		Interval[] sol = new Interval[n/2+1];
		for(int j=0;j<sol.length;j++)
			sol[j] = new Interval();

		// Traverse through given price array
		int i = 0;
		while (i < n-1)
		{
			// Find Local Minima. Note that the limit is (n-2) as we are
			// comparing present element to the next element. 
			while ((i < n-1) && (price[i+1] <= price[i]))
				i++;

			// If we reached the end, break as no further solution possible
			if (i == n-1)
				break;

			// Store the index of minima
			sol[count].buy = i++;

			// Find Local Maxima.  Note that the limit is (n-1) as we are
			// comparing to previous element
			while ((i < n) && (price[i] >= price[i-1]))
				i++;

			// Store the index of maxima
			sol[count].sell = i-1;

			// Increment count of buy/sell pairs
			count++;
		}

		// print solution
		if (count == 0)
			System.out.println("There is no day when buying the stock will make profit\n");
		else
		{
			for (int itr = 0; itr < count; itr++)
				System.out.println("Buy on day: " + sol[itr].buy + "\t Sell on day: " + sol[itr].sell );
		}

		return;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// stock prices on consecutive days
		int price[] = {100, 180, 260, 310, 40, 535, 695};
		int n = price.length;
		
		stockBuySell(price, n);
	}

}
