package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//h2[normalize-space()='My Account']") // My account page heading
	WebElement msgHeading;
	
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement linkLogout;
	

	public boolean isMyAccountPageExists() 
	{
		try {
			boolean isDisplayed = msgHeading.isDisplayed();
			return isDisplayed;
		} catch (Exception e) {
			return false;
		}

	}

	
	
	public void clickOnLogoutButton()
	{
		linkLogout.click();
	}
	
	
	
	
	
}
