package com.jupiter.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.jupiter.qa.base.TestBase;

public class ShopPage extends TestBase {

	@FindBy(xpath = "//li[@id ='nav-home']")
	WebElement lnkHome;
	
	@FindBy(xpath = "//li[@id ='nav-shop']")
	WebElement lnkShop;
	
	@FindBy(xpath = "//li[@id ='nav-contact']")
	WebElement lnkContact;
	
	@FindBy(xpath = "//h4[.='Funny Cow']/following::a[1]")
	WebElement btnFunnyCow;
		
	@FindBy(xpath = "//h4[.='Fluffy Bunny']/following::a[1]")
	WebElement btnFluffyBunny;
	
	@FindBy(xpath = "//h4[.='Stuffed Frog']/following::a[1]")
	WebElement btnStuffedFrog;
	
	@FindBy(xpath = "//h4[.='Stuffed Frog']/following::span[1]")
	WebElement lblStuffedFrogPrice;	
	
	@FindBy(xpath = "//h4[.='ValentineBear']/following::a[1]")
	WebElement btnValentineBear;
	
	@FindBy(xpath = "//a[@href ='#/cart']")
	WebElement lnkCart;
	
	@FindBy(xpath = "//a[@href ='#/shop/']")
	WebElement lnkShopping;
	
	@FindBy(xpath = "//*[contains(text(),' Funny Cow')]")
	WebElement lblFunnyCow;
	
	@FindBy(xpath = "//*[contains(text(),' Fluffy Bunny')]")
	WebElement lblFluffyBunny;
	
	@FindBy(xpath = "//*[contains(text(),' Funny Cow')]/following::input[1]")
	WebElement lblFunnyCowQuantity;
	
	@FindBy(xpath = "//*[contains(text(),' Fluffy Bunny')]/following::input[1]")
	WebElement lblFluffyBunnyQuantity;
	
	@FindBy(xpath = "//td[contains(text(),' Fluffy Bunny')]/following::td")
	WebElement lblFluffyBunnyPrice;
	
	@FindBy(xpath = "//strong[contains(text(),'Total:')]")
	WebElement lblTotal;
	
	@FindBy(xpath = "//a[contains(text(),'Empty Cart')]")
	WebElement btnEmptyCart;
	
	@FindBy(xpath = "//a[contains(text(),'Yes')]")
	WebElement btnYes;
	
	String itemPrice;
	float item3SubTotal;
	float item2SubTotal;
	float item4SubTotal;
	 
	// Initializing the Page Objects:
	public ShopPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyShopPageTitle(){
		return driver.getTitle();
	}
		
	public void clickBuyButton(String itemName){
		driver.findElement(By.xpath("//h4[.='"+ itemName +"']/following::a[1]")).click();
	}
		
	public void clickBuyItemButton(String itemName, String itemQuantity){
		itemPrice = driver.findElement(By.xpath("//h4[.='"+ itemName +"']/following::span[1]")).getText();
		driver.findElement(By.xpath("//h4[.='"+ itemName +"']/following::a[1]")).click();
		this.clickCart();
		this.inputQuantityForEachItem(itemName, itemQuantity);
	}
	
	public void inputQuantityForEachItem(String itemName, String itemQuantity){
		driver.findElement(By.xpath("//*[contains(text(),'"+ itemName +"')]/following::input[1]")).clear();
		driver.findElement(By.xpath("//*[contains(text(),'"+ itemName +"')]/following::input[1]")).sendKeys(itemQuantity);
	}
		
	public void verifyPriceForEachProduct(String itemName, String itemQuantity){
		this.clickBuyItemButton(itemName, itemQuantity);			
		String itemPriceInCart = driver.findElement(By.xpath("//td[contains(text(),'"+ itemName +"')]/following::td[1]")).getText();
		Assert.assertEquals(itemPrice, itemPriceInCart);
		this.clickBackToShoppingLink();
	}
	
	public void verifySubTotalForEachProduct(String item3, String item2, String item4, String item3Quantity, String item2Quantity, String item4Quantity){
		this.clickCart();
		float item3Price = Float.parseFloat(driver.findElement(By.xpath("//td[contains(text(),'"+ item3 +"')]/following::td[1]")).getText().substring(1));
		float item2Price = Float.parseFloat(driver.findElement(By.xpath("//td[contains(text(),'"+ item2 +"')]/following::td[1]")).getText().substring(1));
		float item4Price = Float.parseFloat(driver.findElement(By.xpath("//td[contains(text(),'"+ item4 +"')]/following::td[1]")).getText().substring(1));
		
		item3SubTotal = Float.parseFloat(driver.findElement(By.xpath("//td[contains(text(),'"+ item3 +"')]/following::td[3]")).getText().substring(1));
		item2SubTotal = Float.parseFloat(driver.findElement(By.xpath("//td[contains(text(),'"+ item2 +"')]/following::td[3]")).getText().substring(1));
		item4SubTotal = Float.parseFloat(driver.findElement(By.xpath("//td[contains(text(),'"+ item4 +"')]/following::td[3]")).getText().substring(1));
				
		int quantity3 = Integer.parseInt(item3Quantity);
		int quantity2 = Integer.parseInt(item2Quantity);
		int quantity4 = Integer.parseInt(item4Quantity);

	    Assert.assertEquals((Math.round(item3Price*100)/100.00 * Math.round(quantity3*100)/100.00), Math.round(item3SubTotal*100)/100.00);
	    Assert.assertEquals((Math.round(item2Price*100)/100.00 * Math.round(quantity2*100)/100.00), Math.round(item2SubTotal*100)/100.00);
	    Assert.assertEquals((Math.round(item4Price*100)/100.00 * Math.round(quantity4*100)/100.00), Math.round(item4SubTotal*100)/100.00);
	}
	
	public void veriftTotalAmount(){
		String[] tot =lblTotal.getText().split(" ");		
		float total = Float.parseFloat(tot[1]);
		Assert.assertEquals(Math.round(total*100)/100.00, (Math.round(item3SubTotal*100)/100.00 + Math.round(item2SubTotal*100)/100.00 + Math.round(item4SubTotal*100)/100.00));
	}
	
	public void clickCart(){
		lnkCart.click();
	}
	
	public void clearCart(){
		btnEmptyCart.click();
		btnYes.click();
	}
	
	public void clickBackToShoppingLink(){
		lnkShopping.click();
	}
	
	public boolean verifyItemsInCart(String itemName1, String itemName2, String itemQuantity1, String itemQuantity2) throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[contains(text(),'"+ itemName1 +"')]")).isDisplayed();
		driver.findElement(By.xpath("//td[contains(text(),'"+ itemName2 +"')]")).isDisplayed();
		driver.findElement(By.xpath("//td[contains(text(),'"+ itemName1 +"')]/following::input[@value='"+itemQuantity1+"'][1]")).isDisplayed();
		driver.findElement(By.xpath("//td[contains(text(),'"+ itemName2 +"')]/following::input[@value='"+itemQuantity2+"'][1]")).isDisplayed();
		return true;
	}
		
}	
	
	

