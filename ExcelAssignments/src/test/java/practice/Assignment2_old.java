package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Assignment2_old{
	


public static void main(String[] args) {
	
	
	//List<String> strList = new ArrayList<String>();
	FileInputStream fis = null ;
	
	try {
		fis = new FileInputStream("C:\\Users\\ghassani\\Desktop\\Selenium Java assignments\\Property.properties");
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	Properties prop = new Properties();
	
	try {
		prop.load(fis);	
	}
	catch (IOException e) {
		e.printStackTrace();
	}
	
	//Set<Entry<String>> setEntry = prop.keySet();
	System.out.println(prop.getProperty("Namrata"));
}	



}