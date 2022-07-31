package com.sample.test.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;

public class TestUtility {
	double costOfPizza=0.0;
	
	public void selectFromDropDown(WebElement element,String valueToBeSelected,String valueType) {
		String dropDownValue;
		if(valueType.equalsIgnoreCase("Pizza")) {
			dropDownValue=PizzaTypes.valueOf(valueToBeSelected).getDisplayName();
			costOfPizza=PizzaTypes.valueOf(valueToBeSelected).getCost();
		}else {
			dropDownValue=PizzaToppings.valueOf(valueToBeSelected).getDisplayName();
		}
		
		Select pizzaOptions =new Select(element);
		System.out.println("selecting the "+valueToBeSelected);
		pizzaOptions.selectByValue(dropDownValue);
		System.out.println("selected the Value");
	}
	
	
	public void enterValueIntoInputBox(WebElement element, String valueToBeEntered) {
		element.clear();
		element.sendKeys(valueToBeEntered);
		System.out.println("value "+valueToBeEntered+" is Entered");
		element.sendKeys(Keys.TAB);
		
	}
	
	public double CalculateTheCost(int qty) {
		double totalCost=qty*costOfPizza;
		return totalCost;
	}
	
	public double getCostOnScreen(WebElement element) {
		String costOnScreen=element.getAttribute("value").toString();
		return Double.parseDouble(costOnScreen);
	}
}
