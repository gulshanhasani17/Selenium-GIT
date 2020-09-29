package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Assignment3 {

	String filePath = "C://Users//ghassani//Desktop//Selenium Java assignments//TestFile.txt";

	public String readTextFile(String filePath) {

		File objFile = new File(filePath);

		Scanner objScan = null;
		String stringToBeChecked ="";
		try {
			objScan = new Scanner(objFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(objScan.hasNextLine()) {
			stringToBeChecked = stringToBeChecked +" "+ objScan.nextLine();
			
		}
		return stringToBeChecked;
	}


	public void streamOperationsOnData(String stringToBeChecked ) {

		List<String> string = new ArrayList<String>();

		String[] strArray = stringToBeChecked.split(" ");

		string = Arrays.asList(strArray);
		
		Stream<String> strStream = string.stream();

		strStream.
		sorted().
		forEach(x -> {

			if (x.length()>=5) {
				System.out.println("Length of the word "+x.toUpperCase()+" : "+x.length());
			}				
		});
	}

	public static void main(String[] args) {


		Assignment3 objAssignment3 = new Assignment3();

		String stringToBeChecked = objAssignment3.readTextFile(objAssignment3.filePath);
		objAssignment3.streamOperationsOnData(stringToBeChecked);

	}
}


