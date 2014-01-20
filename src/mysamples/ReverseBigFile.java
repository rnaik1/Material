package mysamples;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReverseBigFile {

	public static void main(String[] args) throws Exception {
		FileReader fis = new FileReader("C:\\test.java");
		BufferedReader br = new BufferedReader(fis);
		PrintWriter pw = null;
		String line = br.readLine();
		int fileNumber = 0;
		ArrayList<File> fileNames = new ArrayList<File>();
		
		//write reversed strings to temp files line by line
		while (line != null) {
			String[] tokens = line.split(" ");
			File tempFile = File.createTempFile("file" + fileNumber, ".tmp");
			fileNames.add(tempFile);
			pw = new PrintWriter(tempFile);
			for (int i = tokens.length - 1; i >= 0; i--) {
				pw.print(tokens[i] + " ");
			}
			line = br.readLine();
			pw.flush();
			pw.close();
		}
		fis.close();
		br.close();
		pw = new PrintWriter(new File("C:\\output.java"));
		
		//read the files one by one in reverse order and print it to a bigger file
		for (int i = fileNames.size() - 1; i >= 0; i--) {
			fis = new FileReader(fileNames.get(i));
			br = new BufferedReader(fis);
			line = br.readLine();
			while (line != null) {
				pw.print(line);
				pw.print(" ");
				line = br.readLine();
			}

			fileNames.get(i).delete();
			fis.close();
			br.close();
		}
		pw.flush();
		pw.close();
	}

}
