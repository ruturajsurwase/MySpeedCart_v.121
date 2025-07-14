package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class BaseClass {

	
	public  static WebDriver driver;
	public Logger logger;
	public Properties properties; 
	
	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException {
		
		//loading config.properties file
		FileReader file= new FileReader("./src//test//resources//config.properties");
		properties=new Properties();
		properties.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			
		else {
			System.out.println("no matching os");
			return;
		   }
			
			//browser
			switch (br.toLowerCase()) 
			{
			 case "chrome" : capabilities.setBrowserName("chrome"); break;
			 case "edge" :   capabilities.setBrowserName("MicrosoftEdge"); break;
			 case "firefox" : capabilities.setBrowserName("firefox"); break;
			 default: System.out.println("invalid browser..."); return;
			}
		
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
			
		}
		
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			
			//browser
			switch (br.toLowerCase()) 
			{
			 case "chrome" : driver = new ChromeDriver(); break;
			 case "edge" :   driver = new EdgeDriver(); break;
			 case "firefox" : driver =new FirefoxDriver(); break;
			 default: System.out.println("invalid browser..."); return;
			}
			
			
			
		}
		
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(properties.getProperty("appURL"));    //readinig url link properties file
		
		driver.manage().window().maximize();

	}

	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}

	
	
	
	public String randomeString()
	{
		String genratedString = RandomStringUtils.randomAlphabetic(5);
		return genratedString;
	}

	
	public String randomeNumber()
	{
		String randomeNumber = RandomStringUtils.randomNumeric(10);
		return randomeNumber;
	}

	public String randomeAlphaNumric()
	{
		String randomeAlphaNumric = RandomStringUtils.randomAlphanumeric(7);
		return randomeAlphaNumric;
	}

	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		     TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		     File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		     
		    String  targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+ tname +"_"+timeStamp+".png";
		     
		     File targetFile = new File(targetFilePath);
	
		     sourceFile.renameTo(targetFile);
		     
		     return targetFilePath;
	}
	

}
