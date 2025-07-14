package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	
	@Test(groups ={"Sanity","Master"})
	public void verify_Login()
	{
		logger.info("********* starting TC002_LoginTest  *********");
		
		try {
			
		HomePage homePage=new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLoginLink();
		
		logger.info("********* Providing Credentilas to Login   *********");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setEmailAddress(properties.getProperty("email"));
		loginPage.setPassword(properties.getProperty("password"));
	
		loginPage.clickLoginButton();
		
		
		MyAccountPage myAccountPage=new MyAccountPage(driver);
		boolean myAccountPageExists = myAccountPage.isMyAccountPageExists();
		
		 Assert.assertEquals(myAccountPageExists,true,"Login Failed");
		} catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("********* Finished TC002_LoginTest  *********");
	}
	
	
	
	
	
	
}
