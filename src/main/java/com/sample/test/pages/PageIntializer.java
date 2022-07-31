package com.sample.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageIntializer {

	private static final int TIMEOUT = 5;
	private static final int POLLING = 100;

	protected WebDriver driver;
	private WebDriverWait wait;

	public PageIntializer(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}
}
