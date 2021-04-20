package com.qa.open.Factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	WebDriver d;
	Properties prop;

	public WebDriver init_driver(String broName) {
		System.out.println("bro name is " + broName);
		switch (broName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			d = new ChromeDriver();
			break;
		case "ff":
			WebDriverManager.firefoxdriver().setup();
			d = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			d = new EdgeDriver();
			break;
		default:
			System.out.println("plese pass correct bro");
			break;
		}
		return d;
	}

	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
