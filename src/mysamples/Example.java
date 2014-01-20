package mysamples;

public class Example {

	static void drawCircle(int r)

	{

		int N = 2*r+1;

		int x, y;



		// Draw a square of size N*N.

		for (int i = 0; i < N; i++)

		{

			for (int j = 0; j < N; j++)

			{

				// Start from the left most corner point

				x = i-r;

				y = j-r;



				// If this point is inside the circle, print it

				if (x*x + y*y <= r*r+1 )

					System.out.print(".");

				else // If outside the circle, print space

					System.out.print(" ");

				//System.out.println(" ");

			}



			System.out.println();

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		drawCircle(7);
	}

}
