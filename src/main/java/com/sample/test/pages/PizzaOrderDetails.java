package com.sample.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.utils.TestUtility;

public class PizzaOrderDetails extends PageIntializer {

	public PizzaOrderDetails(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "pizza1Pizza")
	WebElement pizza1;
	@FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings1']")
	WebElement pizza1Toppings1;
	@FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings2']")
	WebElement pizza1Toppings2;
	@FindBy(id = "pizza1Qty")
	WebElement pizza1Quantity;
	@FindBy(id = "pizza1Cost")
	WebElement pizza1Cost;
	@FindBy(id = "ccpayment")
	WebElement radioCreditCard;
	@FindBy(id = "cashpayment")
	WebElement radioCash;
	@FindBy(id = "email")
	WebElement email;
	@FindBy(id = "name")
	WebElement name;
	@FindBy(id = "phone")
	WebElement phone;
	@FindBy(id = "placeOrder")
	WebElement placeOrderButton;
	@FindBy(id = "reset")
	WebElement resetButton;
	@FindBy(id = "dialog")
	WebElement dialog;
	@FindBy(xpath = "//div[@id='dialog']/p")
	WebElement dialogText;

	TestUtility util = new TestUtility();
	public static double totalCost=0.0;
	public static double costOnScreen=0.0;
	public static String textDialog;
	public static String dialogTextFromScreen;

	public void choosePizzaAndTopics(String pizzaName, String topping1, String topping2, String qty) {
		try {
			Thread.sleep(10000);

			// selecting the Pizza
			util.selectFromDropDown(pizza1, pizzaName, "Pizza");
			
			if (PizzaTypes.valueOf(pizzaName).getDisplayName().contains("1 topping")) {
				chooseTopping1(topping1);
			} else if (PizzaTypes.valueOf(pizzaName).getDisplayName().contains("2 toppings")) {
				chooseTopping1(topping1);
				chooseTopping1(topping2);
			}
			// Entering the Qty
			util.enterValueIntoInputBox(pizza1Quantity, qty);
			costOnScreen=getCostOnScreen();
			totalCost=util.CalculateTheCost(Integer.parseInt(qty));
			System.out.println("Total Cost is +"+ totalCost );
			textDialog="Thank you for your order!\r\n"
					+ "TOTAL: "+totalCost+"\r\n"
					+ "\r\n"
					+ ""+PizzaTypes.valueOf(pizzaName).getDisplayName()+"";
			System.out.println(textDialog);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chooseTopping1(String topping1) {
		util.selectFromDropDown(pizza1Toppings1, topping1, "Topping");
	}

	public void chooseTopping2(String topping2) {
		util.selectFromDropDown(pizza1Toppings2, topping2, "Topping");
	}

	public void enterPickUpInformation(String customerName, String customerEmail, String phoneNumber) {
		util.enterValueIntoInputBox(name, customerName);
		util.enterValueIntoInputBox(email, customerEmail);
		util.enterValueIntoInputBox(phone, phoneNumber);
	}

	public void choosePaymentInformation(String paymentType) {
		if ("credit".equalsIgnoreCase(paymentType)) {
			radioCreditCard.click();
		} else if ("Cash".equalsIgnoreCase(paymentType)){
			radioCash.click();
		}else if ("both".equalsIgnoreCase(paymentType)) {
			radioCreditCard.click();
			radioCash.click();
		}
	}

	public void clickOnSubmitOrderOrReset(String buttonType) {
		if ("placeOrder".equalsIgnoreCase(buttonType)) {
			placeOrderButton.click();
		}
	}
	
	public double getCostOnScreen() {
		String costOnScreen=pizza1Cost.getAttribute("value").toString();
		return Double.parseDouble(costOnScreen);
	}
	
	public String getDailogTextFromScreen() {
		if(dialog.isDisplayed()) {
			dialogTextFromScreen=dialogText.getText().toString();
		}
		return dialogTextFromScreen;
	}
}
