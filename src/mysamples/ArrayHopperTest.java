package mysamples;
import org.junit.Test;

public class ArrayHopperTest {

	@Test
	public void test() {
		ArrayHopper arrhopper = new ArrayHopper();
		arrhopper.readFiletoArray("src/empty.txt");
		if(arrhopper.getArr().size() != 0){
			arrhopper.hopper();
			arrhopper.printPath();
		}
		else {
			if(!arrhopper.isErrorFlag())
				System.err.println("File is empty. Array not constructed from the file");
		}
	}

	@Test
	public void tc2InvalidPath() {
		ArrayHopper arrhopper = new ArrayHopper();
		arrhopper.readFiletoArray("src/invalid.txt");
		if(arrhopper.getArr().size() != 0){
			arrhopper.hopper();
			arrhopper.printPath();
		}
		else {
			if(!arrhopper.isErrorFlag())
				System.err.println("File is empty. Array not constructed from the file");
		}
	}


	@Test
	public void tc3NegativeFile() {
		ArrayHopper arrhopper = new ArrayHopper();
		arrhopper.readFiletoArray("src/negative.txt");
		if(arrhopper.getArr().size() != 0){
			arrhopper.hopper();
			arrhopper.printPath();
		}
		else {
			if(!arrhopper.isErrorFlag())
				System.err.println("File is empty. Array not constructed from the file");
		}
	}

	@Test
	public void tc4ValidFile1() {
		ArrayHopper arrhopper = new ArrayHopper();
		arrhopper.readFiletoArray("src/testFile1.txt");
		if(arrhopper.getArr().size() != 0){
			arrhopper.hopper();
			arrhopper.printPath();
		}
		else {
			if(!arrhopper.isErrorFlag())
				System.err.println("File is empty. Array not constructed from the file");
		}
	}

	@Test
	public void tc5ValidFile2() {
		ArrayHopper arrhopper = new ArrayHopper();
		arrhopper.readFiletoArray("src/testFile2.txt");
		if(arrhopper.getArr().size() != 0){
			arrhopper.hopper();
			arrhopper.printPath();
		}
		else {
			if(!arrhopper.isErrorFlag())
				System.err.println("File is empty. Array not constructed from the file");
		}
	}
	
	@Test
	public void tc6ValidFile3() {
		ArrayHopper arrhopper = new ArrayHopper();
		arrhopper.readFiletoArray("src/testFile3.txt");
		if(arrhopper.getArr().size() != 0){
			arrhopper.hopper();
			arrhopper.printPath();
		}
		else {
			if(!arrhopper.isErrorFlag())
				System.err.println("File is empty. Array not constructed from the file");
		}
	}

}
