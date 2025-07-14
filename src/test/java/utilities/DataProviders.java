package utilities;



import org.testng.annotations.DataProvider;

public class DataProviders {

	
	
	
	
	@DataProvider(name="LoginData")
	public String [] [] getData() throws Exception
	{
		  String path=".\\testData\\OpenCart_LoginData.xlsx";   //taking xl file from testdata
		  
		  ExcelUtility excelUtility=new ExcelUtility(path);    //creat object  of ExcelUtility
		  
		  int totalRowCount = excelUtility.getRowCount("Sheet1");
		  int totalCellCount = excelUtility.getCellCount("Sheet1",1);
		  
		  
		  String logindata[][] =new String [totalRowCount][totalCellCount];  //created for two dimension array
		   
		  for(int i=1;i<=totalRowCount;i++)   //1   read data from xl storing in two dimen array
		  {
			  for(int j=0;j<totalCellCount;j++)  //0     i=rows j=col 
			  {
				  logindata[i-1][j]= excelUtility.getCellData("Sheet1", i, j);   //
			  }
		  }
		  
		  return logindata;
	}
	
	
	
	// dataprovider 2
	
	//dataprovider 3
	
	
}
