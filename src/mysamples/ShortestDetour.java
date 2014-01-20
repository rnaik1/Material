package mysamples;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculate the detour distance between two different rides. Given four latitude / longitude pairs, where driver one is 
 * traveling from point A to point B and driver two is traveling from point C to point D, write a function (in your language of choice) 
 * to calculate the shorter of the detour distances the drivers would need to take to pick-up and drop-off the other driver.
 * @author RevanthNaik
 *
 */

public class ShortestDetour {

	// Assume the 4 pairs to be A, B, C, D to have random points
	private static GeographicLoc A = new GeographicLoc(getRandom(), getRandom()),
			B = new GeographicLoc(getRandom(), getRandom()),
			C = new GeographicLoc(getRandom(), getRandom()),
			D = new GeographicLoc(getRandom(), getRandom());

	// Only 2 possible paths are possible, given the criteria
	private static String[] paths = { "ACDB", "CABD" };
	static Map<String, GeographicLoc> GeoPoints = new HashMap<String, GeographicLoc>() {
		private static final long serialVersionUID = 1L;
		{
			put("A", A);
			put("B", B);
			put("C", C);
			put("D", D);
		}
	};

	public static class GeographicLoc {
		private double latitude;
		private double longitude;

		public GeographicLoc(double lat, double lon) {
			latitude = lat;
			longitude = lon;
		}

		public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double x) {
			this.latitude = x;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double y) {
			this.longitude = y;
		}
	}
	
	public static double getRandom(){
		return Math.random()*10000;
	}
	
	/*
	 * Using haversine formula to calculate the distance between 2 geo points
	 * Haversine formula:	 
	 * a = sin²(Δφ/2) + cos(φ1).cos(φ2).sin²(Δλ/2)
	 * c = 2.atan2(√a, √(1−a))
	 * d = R.c
	 * where	φ is latitude, λ is longitude, R is earth’s radius (mean radius = 6,371km)
	 */
	public static float distance(GeographicLoc start, GeographicLoc end) {
		double earthRadius = 6371;
		double yLatitude = end.getLatitude();
		double xLatitude = start.getLatitude();
		double dLatitude = Math.toRadians(yLatitude - xLatitude);
		double yLongitude = end.getLongitude();
		double xLongitude = start.getLongitude();
		double dLongitude = Math.toRadians(yLongitude - xLongitude);
		double a = Math.sin(dLatitude / 2) * Math.sin(dLatitude / 2)
				+ Math.cos(Math.toRadians(xLatitude))
				* Math.cos(Math.toRadians(yLatitude))
				* Math.sin(dLongitude / 2) * Math.sin(dLongitude / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;

		return new Float(dist).floatValue();
	}

	public static void main(String[] args) {
		
		List<Double> distances = new ArrayList<Double>();
		
		for (String path : paths) {
			char[] points = path.toCharArray();
			double distance = 0.0;
			for (int i = 0; i < points.length - 1; i++) {
				distance += distance(GeoPoints.get("" + points[i]), GeoPoints.get("" + points[i + 1]));
			}
			distances.add(distance);
		}

		double shortestDistance = distances.get(0);
		
		String bestPath = paths[0];
		
		for (int i = 1; i < paths.length; i++) {
			if (shortestDistance > distances.get(i)) {
				bestPath = paths[i];
				shortestDistance = distances.get(i);
			}
		}
		System.out.println(bestPath + " is the shortest path with a distance of " + shortestDistance + " kms");
	}
}
