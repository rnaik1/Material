package misc;
public class LeapYear {
	public static void main (String[] args) {

		// The year to check for leapiness.
		int theYear = 2004;

		if (theYear < 100) {
			// If the year is greater than 40 assume it
			// is from 1900's.  If the year is less than
			// 40 assume it is from 2000's.
			if (theYear > 40) {
				theYear = theYear + 1900;
			}
			else {
				theYear = theYear + 2000;
			}
		}

		// Is theYear Divisible by 4?
		if (theYear % 4 == 0) {

			// Is theYear Divisible by 4 but not 100?
			if (theYear % 100 != 0) {
				System.out.println(theYear + " is a leap year.");
			}
			// Is theYear Divisible by 4 and 100 and 400?
			else if (theYear % 400 == 0) {
				System.out.println(theYear + " is a leap year.");
			}
			// It is Divisible by 4 and 100 but not 400!
			else {
				System.out.println(theYear + " is not a leap year.");
			}
		}
		// It is not divisible by 4.
		else {
			System.out.println(theYear + " is not a leap year.");
		}
	}
}