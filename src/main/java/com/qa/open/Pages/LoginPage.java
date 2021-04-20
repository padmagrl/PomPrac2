package com.qa.open.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	private WebDriver d;
	//1
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBut = By.xpath("//input[@type='submit']");
	private By forgotPwd = By.xpath("//div[@class='form-group']//a[.='Forgotten Password']");
	//2
	
	public LoginPage(WebDriver d)
	{
		this.d=d;
	}
	//3
	public String getLoginPageTitle()
	{
		return d.getTitle();
	}
	public String getLoginPageUrl()
	{
		return d.getCurrentUrl();
	}
	public boolean loginPageisPwdLinkExist()
	{
		return d.findElement(forgotPwd).isDisplayed();
	}
	public void doLogin(String uname,String pwd)
	{
		d.findElement(username).sendKeys(uname);
		d.findElement(password).sendKeys(pwd);
		d.findElement(loginBut).click();
	}
}
