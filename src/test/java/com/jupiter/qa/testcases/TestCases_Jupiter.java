package com.jupiter.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.jupiter.qa.base.TestBase;
import com.jupiter.qa.pages.ContactsPage;
import com.jupiter.qa.pages.HomePage;
import com.jupiter.qa.pages.ShopPage;
import com.jupiter.qa.util.TestUtil;

public class TestCases_Jupiter extends TestBase {
	ShopPage shopPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "contacts";

	public TestCases_Jupiter() {
		super();
	}

	@BeforeTest
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage = new HomePage();
		shopPage = new ShopPage();
	}

	@DataProvider(name="formData")
	public Object[][] getTestData(){

		String formData [][]= {
				{"Tom", "tom@jupiter.com", "Jupiter Toys"}
		};
		return formData;
	}

	/* ------------ Test Case 01-------------------*
	 * Here using data provider to fetch form data */

	@Test(priority=1, dataProvider = "formData")
	public void navigateToContractPageAndValidatePageTitle(String foreName, String email, String message)throws InterruptedException{
		homePage.clickOnContactLink();
		contactsPage.verifyContactPageTitle();
		contactsPage.clickSubmitButton();
		contactsPage.validateErrorMessages();
		contactsPage.inputMandatoryFields(foreName, email, message);
		//		Assert.assertTrue(contactsPage.validateNotDisplayOfErrorMessages()); --- /*This step takes considerable time, plz uncomment and check*/
		contactsPage.clickSubmitButton();
		contactsPage.clickBackButton();
	}

	/* ------------ Test Case 02-------------------*
	 * Here using nestng.xml to fetch form data    */

	@Test(priority=2, invocationCount = 5)
	@Parameters ({"foreName", "email", "message"})
	public void successfully_Submit_Contact_Form_With_Mandatory_Fields(String foreName, String email, String message)throws InterruptedException{		
		homePage.clickOnContactLink();
		contactsPage.inputMandatoryFields(foreName, email, message);
		contactsPage.clickSubmitButton();
		contactsPage.validateSuccessMessages(foreName);
		contactsPage.clickBackButton();
	}

	/* ------------ Test Case 03-------------------*/

	@Test(priority=3)
	@Parameters ({"item1", "item2", "item1Quantity", "item2Quantity"})
	public void add_Items_To_Cart_And_Verify_The_Items_In_Cart(String item1, String item2, String item1Quantity, String item2Quantity)throws InterruptedException{
		homePage.clickOnShopLink();
		shopPage.clickBuyButton(item1);
		shopPage.clickBuyButton(item1);
		shopPage.clickBuyButton(item2);
		shopPage.clickCart();
		shopPage.verifyItemsInCart(item1, item2, item1Quantity, item2Quantity);
		shopPage.clearCart();
	}

	/* ------------ Test Case 04-------------------*/

	@Test(priority=4)
	@Parameters ({"item3", "item_2", "item4", "item3Quantity", "item_2Quantity", "item4Quantity"})
	public void validate_calculations_of_prices_In_Cart(String item3, String item2, String item4, String item3Quantity, String item2Quantity, String item4Quantity )throws InterruptedException{
		homePage.clickOnShopLink();		
		shopPage.verifyPriceForEachProduct(item3, item3Quantity);
		shopPage.verifyPriceForEachProduct(item2, item2Quantity);
		shopPage.verifyPriceForEachProduct(item4, item4Quantity);
		shopPage.verifySubTotalForEachProduct(item3, item2, item4, item3Quantity, item2Quantity, item4Quantity);
		shopPage.veriftTotalAmount();	
	}

	@AfterTest
	public void tearDown(){
		driver.quit();
	}	
}
