package mysamples;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Roman {
	// Integer to Roman
	private static int[] bases = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
	private static HashMap<Integer, String> map = new HashMap<Integer, String>();

	// Roman to Integer
	private final static Pattern regex = Pattern.compile("M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");
	final static char symbol[] = {'M','D','C','L','X','V','I'};
	final static int value[] = {1000,500,100,50,10,5,1};

	/**
	 * Test whether the input string is a valid Roman numeral (between 1 and 4999).
	 */
	public static boolean isRoman(String s) {
		return s!=null && !"".equals(s) && regex.matcher(s.toUpperCase()).matches();
	}

	private static void setup() {
		map.put(1, "I");
		map.put(4, "IV");
		map.put(5, "V");
		map.put(9, "IX");
		map.put(10, "X");
		map.put(40, "XL");
		map.put(50, "L");
		map.put(90, "XC");
		map.put(100, "C");
		map.put(400, "CD");
		map.put(500, "D");
		map.put(900, "CM");
		map.put(1000, "M");
	}

	public static String intToRoman(int num) {
		setup();
		String result = new String();
		for (int i : bases) {
			while (num >= i) {
				result += map.get(i);
				num -= i;
			}
		}
		return result;
	}

	public static int valueOf(String roman)
	{
		roman = roman.toUpperCase();
		
		if(roman.length() == 0) return 0;
		
		for(int i = 0; i < symbol.length; i++)
		{
			int pos = roman.indexOf(symbol[i]) ;
			if(pos >= 0)
				return value[i] - valueOf(roman.substring(0,pos)) + valueOf(roman.substring(pos+1));
		}
		throw new IllegalArgumentException("Invalid Roman Symbol.");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(intToRoman(4999));
		//System.out.println(isRoman("XL")?valueOf("XL"):"Not a valid number dude");
		//System.out.println(valueOf("DCCCXC"));
		//System.out.println(valueOf("MDCCC"));
		System.out.println(valueOf("XXX"));
	}

}
