package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*  data is valid ----> login success ----> test pass ----> logout
 *  data is valid ----> login failed ----> test fail
 *     
 *    data is invalid ----> login  success----> test fail ----> logout
 *    data is invalid ----> login failed ----> test pass
 */

public class TC003_LoginDDT extends BaseClass 
{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = "DataDriven") // getting data provider from another class
																				   
	public void verify_Login_DDT(String email, String password, String expResult) {

		logger.info("********* starting TC003_LoginDDT *********");

		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLoginLink();

			logger.info("********* Providing Credentilas to Login   *********");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmailAddress(email);
			loginPage.setPassword(password);
			loginPage.clickLoginButton();

			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean targetPage = myAccountPage.isMyAccountPageExists();

			if (expResult.equalsIgnoreCase("valid")) {
				if (targetPage == true) {
					myAccountPage.clickOnLogoutButton();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (expResult.equalsIgnoreCase("invalid")) {
				if (targetPage == true) {
					myAccountPage.clickOnLogoutButton();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("********* finished TC003_LoginDDT *********");

	}

}
