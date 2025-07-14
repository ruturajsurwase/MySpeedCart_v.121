package pageObjects;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkdPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String phnumber) {
		txtTelephone.sendKeys(phnumber);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void setConfirmPassword(String pwd) {
		txtConfirmPassword.sendKeys(pwd);
	}

	public void checkPolicy() {
		chkdPolicy.click();
	}

	public void clickOnContinue() {
		btnContinue.click();

		// sol 2
		// btnContinue.submit();

		// sol 3
		// Actions action=new Actions(driver);
		// action.moveToElement(btnContinue).click().perform();

		// sol 4
		// JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		// jsExecutor.executeScript("arguments[0].click();",btnContinue);

		// sol 5
		// btnContinue.sendKeys(Keys.RETURN);

		// sol 6
		// WebDriverWait myWait=new WebDriverWait(driver,Duration.ofSeconds(10));
		// myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}

	public String getConfirmationMsg() 
	{
	    try {
	    	Thread.sleep(2000);
	    	String text = msgConfirmation.getText();
	      return text;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
