package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Assignment2 {
	List<String> companyList = new ArrayList<String>();
	HashMap<String,String> companyName = new HashMap<String, String>();
	HashMap<String,String> companyLocation = new HashMap<String, String>();
	HashMap<String,Integer> EmployeeCount = new HashMap<String, Integer>();
	HashMap<String,String> companyTech = new HashMap<String, String>();

	public List<String> getCompanyList() {

		FileInputStream fs;
		XSSFWorkbook wb = null;
		try {
			fs = new FileInputStream("C://Users//ghassani//Desktop//Selenium Java assignments//TestData.xlsx");
			wb = new XSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheetAt(0);

		for(int row =1; row<=sheet.getLastRowNum(); row++) {
			//for (int col=1;col<= sheet.getRow(1).getLastCellNum()-1;col++) {
				this.companyList.add(String.valueOf(row));

				this.companyTech.put("CompanyTech_"+row, sheet.getRow(row).getCell(3).toString());
				this.companyLocation.put("CompanyLocation_"+row, sheet.getRow(row).getCell(2).toString());
				this.companyName.put("CompanyName_"+row,sheet.getRow(row).getCell(1).toString());
				this.EmployeeCount.put("EmployeeCount_"+row,(int) sheet.getRow(row).getCell(4).getNumericCellValue());
			//}
		}

		return companyList;

	}

	public void checkIfCompanyExist(String strCompanyName) {

		boolean blnCompanyFound = false;
		for(String val:companyList) {

			if(companyName.get("CompanyName_"+val).trim().equalsIgnoreCase(strCompanyName)){
				System.out.println("Company Name :"+companyName.get("CompanyName_"+val)+"\n"+"Company Location :"+companyLocation.get("CompanyLocation_"+val)+"\n"+"Technology Used :"+companyTech.get("CompanyTech_"+val)+"\n"+"Employees Working :"+EmployeeCount.get("EmployeeCount_"+val));
				System.out.println(".............................................");
				blnCompanyFound = true;
				break;
			}
		}
		if(blnCompanyFound==false) {
			try {
				throw new CompanyNotFoundException();
			} catch (CompanyNotFoundException e) {
				System.out.println("No Company Data found with given conditions. Company Entered : '"+strCompanyName+"'");
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args)  {

		Assignment2 objAssignment2 = new Assignment2();
		objAssignment2.getCompanyList();
		objAssignment2.checkIfCompanyExist("Genpact");
		objAssignment2.checkIfCompanyExist("Sopra Steria");
		objAssignment2.checkIfCompanyExist("Amazon");
		
	}

}
