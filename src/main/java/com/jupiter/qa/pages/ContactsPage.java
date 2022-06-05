package com.jupiter.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.jupiter.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//li[@id ='nav-home']")
	WebElement lnkHome;
	
	@FindBy(xpath = "//li[@id ='nav-shop']")
	WebElement lnkShop;
	
	@FindBy(xpath = "//a[contains(text(),'Submit')]")
	WebElement lnkContact;
	
	@FindBy(xpath = "//a[contains(text(),'Submit')]")
	WebElement btnSubmit;
	
	@FindBy(xpath = "//span[contains(text(),'Forename is required')]")
	WebElement lblForename;
	
	@FindBy(xpath = "//span[contains(text(),'Email is required')]")
	WebElement lnkEmail;
	
	@FindBy(xpath = "//span[contains(text(),'Message is required')]")
	WebElement lnkMessage;
	
	@FindBy(xpath = "//input[@id ='forename']")
	WebElement txtForename;
	
	@FindBy(xpath = "//input[@id ='email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//textarea[@id ='message']")
	WebElement txtMessage;
	
	@FindBy(xpath = "//strong[contains(text(),'Thanks')]")
	WebElement lblSuccessMsg;
	
	@FindBy(xpath = "//a[@ng-click='goBack()']")
	WebElement btnBack;
	
	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyContactPageTitle(){
		return driver.getTitle();
	}
	
	public void clickSubmitButton(){
		btnSubmit.click();
	}	
	
	public void validateErrorMessages(){
		lblForename.isDisplayed();
		lnkEmail.isDisplayed();
		lnkMessage.isDisplayed();
	}
	
	public void inputMandatoryFields(String foreName, String email, String message){
		txtForename.sendKeys(foreName);
		txtEmail.sendKeys(email);
		txtMessage.sendKeys(message);
	}
		
	public boolean validateNotDisplayOfErrorMessages(){
		if ((driver.findElements(By.xpath("//span[contains(text(),'Forename is required')]")).size() > 0)||
		   (driver.findElements(By.xpath("//span[contains(text(),'Email is required')]")).size() > 0)||
		   (driver.findElements(By.xpath("//span[contains(text(),'Message is required')]")).size() > 0))
		   {
			return false;
		   }
	return true;		
	}
	
	public boolean validateSuccessMessages(String foreName){
		String lblMessage = driver.findElement(By.xpath("//strong[contains(text(),'Thanks " + foreName +"')]")).getText();
		Assert.assertEquals(lblMessage, "Thanks " + foreName);
		return true;
	}
	
	public void clickBackButton(){
		btnBack.click();
	}
		
}
