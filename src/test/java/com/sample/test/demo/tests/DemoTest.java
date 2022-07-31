package com.sample.test.demo.tests;

import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;
import com.sample.test.pages.PizzaOrderDetails;

import junit.framework.Assert;

public class DemoTest extends TestBase {
	
	/**
	 * 
	 */
    @Test(priority = 0)
    public void demoTest() {
    	PizzaOrderDetails pizza = new PizzaOrderDetails(driver);
    	pizza.choosePizzaAndTopics("SMALL_ONETOPPINGS","MANGOS","OLIVES","3");
    	pizza.enterPickUpInformation("ABc", "abc@jlj.com", "290902");
    	pizza.choosePaymentInformation("credit");
    	pizza.clickOnSubmitOrderOrReset("placeOrder");
    	Assert.assertEquals(PizzaOrderDetails.totalCost, PizzaOrderDetails.costOnScreen);
    	Assert.assertTrue(pizza.getDailogTextFromScreen().contains("Thank you for your order!"));
    }

    /**
     * 
     */
    @Test(priority = 1)
    public void demoTest2() {
    	PizzaOrderDetails pizza = new PizzaOrderDetails(driver);
    	pizza.choosePizzaAndTopics("MEDIUM_TWOTOPPINGS","MANGOS","OLIVES","1");
    	pizza.enterPickUpInformation("Sourabh", "sourabh@jlj.com", "290902");
    	pizza.choosePaymentInformation("both");
    	pizza.clickOnSubmitOrderOrReset("placeOrder");
    	Assert.assertTrue(!pizza.getDailogTextFromScreen().contains("Thank you for your order!"));
    }
}
