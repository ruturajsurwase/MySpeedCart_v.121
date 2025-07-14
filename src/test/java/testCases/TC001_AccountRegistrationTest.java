package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	void verify_account_registation() {

		logger.info("********* starting TC001_AccountRegistrationTest  *********");
		
		try {
			HomePage homePage = new HomePage(driver);
		
		homePage.clickMyAccount();
		logger.info("clicked on myAccount link");
		
		homePage.clickRegister();
		logger.info("clicked Register link");
		
		
		AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);

		logger.info("Providing customer details......");
		
		registrationPage.setFirstName(randomeString().toUpperCase());

		registrationPage.setLastName(randomeString().toUpperCase());

		registrationPage.setEmail(randomeString() + "@gmail.com");

		registrationPage.setTelephone(randomeNumber());

		String password = randomeAlphaNumric();

		registrationPage.setPassword(password);
		registrationPage.setConfirmPassword(password);

		registrationPage.checkPolicy();
		
		registrationPage.clickOnContinue();


		logger.info("validating expected massage......");
		
		String confirmationMsg = registrationPage.getConfirmationMsg();
		
		logger.info("Confirmation message: " + confirmationMsg); 
		
		if(confirmationMsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			  logger.error("Test failed....");  // Log full exception trace
			  logger.debug("debug logs....");
			  Assert.assertTrue(false);
		}
		
	}
		catch (Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("********* finished TC001_AccountRegistrationTest  *********");

	}

	
	
	
	
	
	
	
	
	
	
}
