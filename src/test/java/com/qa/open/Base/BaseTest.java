package com.qa.open.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.open.Factory.DriverFactory;
import com.qa.open.Pages.LoginPage;

public class BaseTest {
	public LoginPage lp;
	DriverFactory df; 
	WebDriver d;
	@BeforeTest
	public void setUp()
	
	{
		df=new DriverFactory();
		d=df.init_driver("chrome");
		lp=new LoginPage(d);
	}
	@AfterTest
	public void tearDown()
	{
		d.quit();
	}

}
