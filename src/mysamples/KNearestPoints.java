package mysamples;
import java.util.ArrayList;

public class KNearestPoints {

	static class Point {
		int x;
		int y;
		int distance;

		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	Point[] KPoints(int k, Point[] s){
		int[] distance = new int[s.length];
		for(int i=0;i<s.length;i++){
			int d = s[i].x*s[i].x + s[i].y*s[i].y;
			s[i].distance = d;
			distance[i] = d;
		}
		int kdist = select(distance, 0, distance.length-1, k);
		Point[] result = new Point[k];
		int j = 0;
		for(int i=0;i<s.length;i++){
			if(s[i].distance <= kdist && j<k){
				result[j] = s[i];
				j++;
			}
		}

		return result;
	}

	public static int select(int arr[], int p, int r, int i){
		if(i>arr.length) return Integer.MAX_VALUE;
		if(p==r) return arr[p];
		else
		{
			int q = partition(arr, p, r);
			int k = q-p+1;
			if(k==i) return q;
			else if (k>=i) return select(arr, p, q-1, i);
			else return select(arr, q+1, r, i-k);
		}
	}

	public static int partition(int arr[], int p, int r)
	{
		int x = arr[r];
		int i = p-1;
		for(int j=p;j<=r-1;j++){
			if(arr[j]<=x){
				i++;
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}
		}
		int tmp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = tmp;

		return i+1;
	}

	public static void main(String[] args) {
		KNearestPoints knn = new KNearestPoints();
		Point p1 = new Point(1, 1);
		Point p2 = new Point(2, 2);
		Point p3 = new Point(3, 3);
		Point p4 = new Point(4, 4);
		Point p5 = new Point(5, 5);
		Point p6 = new Point(-1, 1);
		Point p7 = new Point(1, -1);
		Point p8 = new Point(-1, -1);
		Point p9 = new Point(0, 3);
		Point p10 = new Point(3, 0);
		Point p11 = new Point(0, 0);

		Point[] arr = {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5),
				new Point(-1, 1),new Point(1, -1),new Point(-1, -1),new Point(0, 3),new Point(3, 0),new Point(0, 0)};

		ArrayList<Point> s = new ArrayList<Point>();
		s.add(p1);s.add(p2);s.add(p3);s.add(p4);s.add(p5);
		s.add(p6);s.add(p7);s.add(p8);s.add(p9);s.add(p10);s.add(p11);
		Point[] result = knn.KPoints(5, arr);
		for(int i=0;i<result.length;i++){
			System.out.println(result[i].x + "\t" + result[i].y);
		}
	}

}
