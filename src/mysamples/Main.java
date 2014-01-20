package mysamples;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	static class Point {
		double x;
		double y;
	};

	static boolean onSegment(Point p, Point q, Point r) {
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x)
				&& q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
			return true;

		return false;
	}

	/*
	 *  find orientation of ordered triplet (p, q, r).
	 */
	static int orientation(Point p, Point q, Point r) {
		double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

		if (val == 0)
			return 0;

		// clock or counterclock wise
		return (val > 0) ? 1 : 2;
	}

	static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
		// Find the four orientations needed for general and
		// special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		// General case
		if (o1 != o2 && o3 != o4)
			return true;

		// Special Cases
		// p1, q1 and p2 are colinear and p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1))
			return true;

		// p1, q1 and p2 are colinear and q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1))
			return true;

		// p2, q2 and p1 are colinear and p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2))
			return true;

		// p2, q2 and q1 are colinear and q1 lies on segment p2q2
		if (o4 == 0 && onSegment(p2, q1, q2))
			return true;

		return false; // Doesn't fall in any of the above cases
	}

	public static void readandProcessInput(String path) throws NumberFormatException, IOException {
		File file = new File(path);
		BufferedReader freader = null;
		try {
			List<ArrayList<Point>> points = new ArrayList<ArrayList<Point>>();
			freader = new BufferedReader(new FileReader(file));
			String textString;
			while ((textString = freader.readLine()) != null) {
				int firstBracket = textString.indexOf('[');
				int lastBracket = textString.lastIndexOf('[');
				int firstminus = textString.indexOf('-');
				int lastminus = textString.lastIndexOf('-');

				Point p = new Point();
				p.x = Double.parseDouble(textString.substring(firstBracket+1, 14));
				p.y = Double.parseDouble(textString.substring(firstminus, textString.indexOf(']')));
				
				Point q = new Point();
				q.x = Double.parseDouble(textString.substring(lastBracket+1, lastBracket+10));
				q.y = Double.parseDouble(textString.substring(lastminus, textString.lastIndexOf(']')));
				ArrayList<Point> temp = new ArrayList<Point>();
				temp.add(p);
				temp.add(q);
				points.add(temp);
			}

			List<Integer> resultPoints = new ArrayList<Integer>(points.size());
			boolean intersect = false;
			Set<Integer> setOfInterectionSegments = new HashSet<Integer>(points.size());
			for(int i=0;i<points.size();i++){
				for(int j=0;j<points.size();j++){
					if(i != j && !setOfInterectionSegments.contains(j)){
						if(doIntersect(points.get(i).get(0), points.get(i).get(1), points.get(j).get(0), points.get(j).get(1))){
							setOfInterectionSegments.add(i);
							intersect = true;
							break;
						}
					}
				}
				if(!intersect)
					resultPoints.add(i+1);
				intersect = false;
			}
			for(int i=0;i<resultPoints.size();i++)
				System.out.println(resultPoints.get(i));
		}
		finally{
			try {
				if (freader != null) {
					freader.close();
				}
			} catch (IOException e) {

			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = args[0];
		try {
			readandProcessInput(path);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
