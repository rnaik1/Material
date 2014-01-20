package mysamples;
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class with static methods for justifying Strings.
 */

public class Justify
{
	/**
	 * Right justify.
	 *
	 * @param text the String to justify.
	 * @param length the length of the output String.
	 *
	 * If text is too long, don't truncate - better to destroy
	 * formatting than to destroy information.
	 *
	 * @return text, padded on the left with blanks as needed.
	 */
	public static String right( String text, int length ) 
	{
		if ( text.length() >= length ) {
			return text;
		}
		int blankCount = length - text.length();
		StringBuffer buf = new StringBuffer(length);
		for (int i = 0; i < blankCount; i++) {
			buf.append(' ');
		}
		buf.append(text);
		return buf.toString();
	}

	/**
	 * Left justify.
	 *
	 * @param text the String to justify.
	 * @param length the length of the output String.
	 *
	 * If text is too long, don't truncate - better to destroy
	 * formatting than to destroy information.
	 *
	 * @return text, padded on the right with blanks as needed.
	 */
	public static String left( String text, int length ) 
	{
		if ( text.length() >= length ) {
			return text;
		}
		int blankCount = length - text.length();
		StringBuffer buf = new StringBuffer(length);
		buf.append(text);
		for (int i = 0; i < blankCount; i++) {
			buf.append(' ');
		}
		return buf.toString();
	}

	public static String justifyLeft(String st, int width) {
		StringBuffer buf = new StringBuffer(st);
		int lastspace = -1;
		int linestart = 0;
		int i = 0;

		while (i < buf.length()) {
			if ( buf.charAt(i) == ' ' ) lastspace = i;
			if ( buf.charAt(i) == '\n' ) {
				lastspace = -1;
				linestart = i+1;
			}
			if (i > linestart + width - 1 ) {
				if (lastspace != -1) {
					buf.setCharAt(lastspace,'\n');
					linestart = lastspace+1;
					lastspace = -1;
				}
				else {
					buf.insert(i,'\n');
					linestart = i+1;
				}
			}
			i++;
		}
		return buf.toString();
	}

	public static String[] justify(String[] lines, int columnWidth) {
		ArrayList<String> tokens = new ArrayList<String>();
		for (String line : lines) {
			tokens.addAll(Arrays.asList(line.split(" ")));
		}
		ArrayList<String> justified = new ArrayList<String>();
		String current = "";
		for (String token : tokens) {
			boolean needSpace = !current.equals("");
			if (current.length() + token.length() + (needSpace ? 1 : 0) <= columnWidth) {
				current += (needSpace ? " " : "") + token;
			} else {
				justified.add(current);
				current = token;
			}
		}
		if (!current.equals("")) {
			justified.add(current);
		}
		String result[] = new String[justified.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = justified.get(i);
		}
		return result;
	}

	public static ArrayList<String> fullJustify(String[] words, int L) {
		// Start typing your Java solution below
		// DO NOT write main() function
		ArrayList<String> result=new ArrayList<String>(); 
		if(L==0 || words.length==0) 
		{
			result.add("");
			return result; 
		}
		int i=0; 
		while(i<words.length)
		{
			ArrayList<String> subResult=new ArrayList<String>(); 
			int length=words[i].length(); 
			subResult.add(words[i]);
			int num=1; 

			while(i+1<words.length && (length+1+words[i+1].length())<=L)  //check the num of words in one line
			{
				length+=1+words[i+1].length(); 
				subResult.add(words[i+1]);
				i++; 
				num++; 
			}

			StringBuffer sb=new StringBuffer(); 
			if(num==1)     // only one word in one line and append all the empty space
			{
				int tempLength=L-length; 
				sb.append(subResult.get(0));
				while(tempLength>0)
				{
					sb.append(" ");
					tempLength--; 
				}
			}
			else        //multiply words in one line 
			{
				if(i==words.length-1)  //if this line is the last line
				{
					int tempSp=L-length-1; 
					int lastLine=0; 
					while(lastLine<num)
					{
						sb.append(subResult.get(lastLine));
						lastLine++; 
						sb.append(" ");
					}
					while(tempSp>0)
					{
						sb.append(" ");
						tempSp--; 
					}
				}
				else            //if it is not the last line
				{
					int space=(L-length+num-1)/(num-1);  //counter the normal num of space in each gap
					int counter=0; 
					String empty=""; 
					while(space>0)
					{
						empty+=" "; space--; 
					}
					int extra=(L-length+num-1)%(num-1); //extra space left
					while(counter<num-1)
					{
						sb.append(subResult.get(counter));
						sb.append(empty);
						if(extra>0)
						{
							sb.append(" "); extra--;  //assign one extra space from left
						}
						counter++; 
					}
					sb.append(subResult.get(counter));  
				}
			}
			result.add(sb.toString());    //put the line in the result list
			i++; 
		}
		return result; 
	}

	public static void main( String[] args )
	{
		System.out.println("|" + Justify.right("abcd",5) + "|");
		System.out.println("|" + Justify.left("abcdefghij",5) + "|");

		String input = "Cracking the Coding Interview: 150 Programming Interview Questions and Answers focuses on mastering the programming interview. Topics include: strategies to handle tough algorithm questions, preparation techniques, behavioral questions, and 150 programming interview questions and answers.";
		int width = 20;

		/*int pos = 0;
		StringBuilder sb = new StringBuilder();

		String[] words = input.split(" ");
		for (String w : words) {
			if (pos + w.length() > width) {
				sb.append('\n');
				pos = 0;
			}
			sb.append(w);
			sb.append(' ');
			pos += w.length() + 1;
		}

		System.out.println(sb);*/

		//System.out.println("|" + Justify.left(Justify.right(input,width),width) + "|");
		System.out.println("|" + Justify.justifyLeft("abcd ef g hijkl fskfas asfwqoiqasf shfaksfhashfah asahsahosafhasfashas aioash",10) + "|");
		System.out.println();
		String[] lines = {"I am a good and nice boy from arizona state", "good and nice", "boy from ", "arizona state"};
		System.out.println(fullJustify(lines,10));
	}
}