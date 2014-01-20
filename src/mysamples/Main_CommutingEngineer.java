package mysamples;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * Commuting Engineers problem
 */
class Main_CommutingEngineer
{
	public static class GeographicLoc {
		private float latitude;
		private float longitude;

		public GeographicLoc(float lat, float lon) {
			latitude = lat;
			longitude = lon;
		}

		public float getLatitude() {
			return latitude;
		}

		public float getLongitude() {
			return longitude;
		}
	}
	
	/*
	 * Gets the adjacency matrix for the given co-ordinates
	 */
	public static float[][] getAdjacency(String path) throws NumberFormatException, IOException{
		ArrayList<GeographicLoc> points = new ArrayList<GeographicLoc>();;
		File file = new File(path);
		BufferedReader freader = null;
		try {
			freader = new BufferedReader(new FileReader(file));
			String textString;
			while ((textString = freader.readLine()) != null) {
				int firstOpenBracket = textString.indexOf('(');
				int firstCloseBracket = textString.indexOf(')');
				int comma = textString.lastIndexOf(',');
				points.add(new GeographicLoc(Float.parseFloat(textString.substring(firstOpenBracket+1, comma)),
						Float.parseFloat(textString.substring(comma+2,firstCloseBracket))));
			}
		} 
		finally{
			try {
				if (freader != null) {
					freader.close();
				}
			} catch (IOException e) {

			}
		}
		
		float[][] result = new float[points.size()][points.size()];
		for(int i = 0;i<result.length;i++){
			for(int j = 0;j<result.length;j++){
				result[i][j] = distance(points.get(i), points.get(j));
			}
		}
		return result;
	}
	
	/*
	 * Used for calculating the distance between 2 co-ordinates on the earth
	 */
	public static float distance(GeographicLoc start, GeographicLoc end) {
		float earthRadius = 6371;
		float yLatitude = end.getLatitude();
		float xLatitude = start.getLatitude();
		double dLatitude = Math.toRadians(yLatitude - xLatitude);
		float yLongitude = end.getLongitude();
		float xLongitude = start.getLongitude();
		double dLongitude = Math.toRadians(yLongitude - xLongitude);
		double a = Math.sin(dLatitude / 2) * Math.sin(dLatitude / 2)
				+ Math.cos(Math.toRadians(xLatitude))
				* Math.cos(Math.toRadians(yLatitude))
				* Math.sin(dLongitude / 2) * Math.sin(dLongitude / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return new Float(dist).floatValue();
	}
	
	/*
	 * Gives the shortest path covering all the startups
	 */
	public static void getPath(float[][] adjMatrix){

		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);

		int[] visited = new int[adjMatrix.length];
		visited[0] = 1;

		int nextP = 0;
		boolean minFound = false;
		boolean start = true;

		System.out.println(1);

		while (!stack.isEmpty())
		{
			int point;
			if(start){
				point = stack.pop()-1;
				start = false;
			}
			else
				point = stack.pop();

			int i = 0;
			double min = Double.MAX_VALUE;
			while (i < adjMatrix[0].length)
			{
				if (adjMatrix[point][i] > 0 && visited[i] == 0) {
					if (min > adjMatrix[point][i]) {
						min = adjMatrix[point][i];
						nextP = i;
						minFound = true;
					}
				}
				i++;
			}
			if (minFound)
			{
				visited[nextP] = 1;
				stack.push(nextP);
				System.out.println(nextP+1);
				minFound = false;
				continue;
			}
		}
	}

	public static void main(String args[]) {
		try {
			getPath(getAdjacency(args[0]));
			System.exit(0);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}