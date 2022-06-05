package com.jupiter.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.jupiter.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//li[@id ='nav-home']")
	WebElement lnkHome;
	
	@FindBy(xpath = "//li[@id ='nav-shop']")
	WebElement lnkShop;
	
	@FindBy(xpath = "//li[@id ='nav-contact']")
	WebElement lnkContact;
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	public ContactsPage clickOnContactLink(){
		lnkContact.click();
		return new ContactsPage();	
	}
		 
	public ShopPage clickOnShopLink(){
		lnkShop.click();
		return new ShopPage();
	}
	
}
