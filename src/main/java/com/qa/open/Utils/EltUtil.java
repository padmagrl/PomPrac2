package com.qa.open.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EltUtil {
	WebDriver driver;

	public EltUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement findElt(By locator) {
		return driver.findElement(locator);
	}

	public void doSendkeys(By locator, String value) {
		findElt(locator).sendKeys(value);
	}

	public List<WebElement> findElts(By locator) {
		return driver.findElements(locator);
	}

	public void doClick(By locator) {
		findElt(locator).click();
	}

	public String doGetText(By locator) {
		return findElt(locator).getText();
	}

	public WebElement doSelectByPartialLinkTest(String val) {
		return driver.findElement(By.partialLinkText(val));
	}

	public boolean doIsDisplayed(By locator) {
		return findElt(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {
		return findElt(locator).isEnabled();
	}

	public int doFindSizeOfList(By locator) {
		return findElts(locator).size();
	}

	public List<String> getEltTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eltList = findElts(locator);
		for (WebElement e : eltList) {
			if (e.getText() != null) {
				eleTextList.add(e.getText());
			}
		}
		return eleTextList;

	}

	public void getEltTextListWithoutArrayList(By locator) {

		List<WebElement> eltList = findElts(locator);
		for (WebElement e : eltList) {
			String s = e.getText();
			if (s != null) {
				System.out.println(s);
			}
		}

	}

	public List<String> getEltTextListFor(By locator) {
		List<String> eleTextList = new ArrayList<String>();
		List<WebElement> eltList = findElts(locator);
		for (int i = 0; i < eltList.size(); i++) {
			if (eltList.get(i).getText() != null) {
				eleTextList.add(eltList.get(i).getText());
			}

		}
		return eleTextList;

	}

	public List<String> getAttributeList(By locator, String attName) {
		List<WebElement> attEltList = findElts(locator);
		List<String> attTextList = new ArrayList<String>();
		for (WebElement e : attEltList) {
			if ((!e.getAttribute(attName).isBlank()) || (!e.getAttribute(attName).isEmpty())
					|| e.getAttribute(attName) != null) {
				attTextList.add(e.getAttribute(attName));
				// System.out.println(e.getAttribute(attName));
			}

		}
		return attTextList;
	}

	// ***********Dropdown -with Select Otion*****************
	public void doSelctDropDownByIndex(By locator, int index) {
		Select select = new Select(findElt(locator));
		select.selectByIndex(index);
	}

	public void doSelctDropDownByIndexWithWebElt(WebElement welt, int index) {
		Select select = new Select(welt);
		select.selectByIndex(index);
	}

	public void doSelctDropDownByVisibleText(By locator, String text) {
		Select select = new Select(findElt(locator));
		select.selectByVisibleText(text);
		// System.out.println(text +" is clicked");
	}

	public void doSelctDropDownByValue(By locator, String value) {
		Select select = new Select(findElt(locator));
		select.selectByValue(value);
		// System.out.println(value +" is clicked");
	}

	public List<String> doSelectDropDownAllOptions(By locator) {
		Select select = new Select(findElt(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>();
		for (WebElement e : optionsList) {
			optionsTextList.add(e.getText());

		}
		return optionsTextList;
	}

	public void doSelectDropDownAllOptionsClick(By locator) {

		List<WebElement> optionsList = driver.findElements(locator);
		for (WebElement e : optionsList) {
			e.click();

		}

	}

	public void doSelectDropDownValueFromOptions(By locator, String value) {
		Select select = new Select(findElt(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}

		}

	}

	public void doSelectDropdownWithOutSelect(By locator, String value) {
		List<WebElement> dropdownList = findElts(locator);
		for (WebElement e : dropdownList) {
			if (e.getText().equals(value)) {
				JavaScriptUtil js = new JavaScriptUtil(driver);
				js.scrollIntoView(e);
				e.click();
			}
		}

	}

	public void doClickItemFromList(By locator, String value) {
		List<WebElement> li = findElts(locator);
		for (WebElement e : li) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}

	}

	public void comboDropDownSelect(By locator, String... value) {
		// findElt(ddLoc).click();
		List<WebElement> choices = findElts(locator);
		try {

			if (!value[0].equals("all")) {
				for (int i = 0; i < choices.size(); i++) {
					String text = choices.get(i).getText();
					for (int j = 0; j < value.length; j++) {
						if (text.equals(value[j])) {
							choices.get(i).click();
							System.out.println(choices.get(i).getText() + " is clicked ");
							break;
						}
					}

				}
			} else {
				for (int i = 0; i < choices.size(); i++) {
					choices.get(i).click();
					System.out.println(choices.get(i).getText() + " is clicked ");
				}
			}
		} catch (Exception e) {
			System.out.println("ex handled ");
		}
	}

	public void comboDropDownDeselect(By locator, String... value) {
		List<WebElement> choices = findElts(locator);
		try {

			if (!value[0].equals("deselectall")) {
				for (int i = 0; i < choices.size(); i++) {
					String text = choices.get(i).getText();
					for (int j = 0; j < value.length; j++) {
						if (text.equals(value[j])) {
							if (choices.get(i).isEnabled()) {
								choices.get(i).click();
								System.out.println(choices.get(i).getText() + " is un selected ");
							}
							break;
						}
					}

				}
			} else {
				for (int i = 0; i < choices.size(); i++) {
					if (choices.get(i).isEnabled()) {
						choices.get(i).click();
						System.out.println(choices.get(i).getText() + " is un selected ");
					}

				}
			}
		} catch (Exception e) {
			System.out.println("ex handled in deselect meth");
		}
	}
	// ************Alerts************

	public void doAlertAccept() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void doAlertDismiss() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String doAlertGetText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	// ************Window Handles********
	public String doGetParentWindowHandle() {
		return driver.getWindowHandle();
	}

	public Set<String> doGetWindowHandles() {
		return driver.getWindowHandles();
	}

	public void doSwitchParentWindow(String Parent) {
		driver.switchTo().window(Parent);
	}

	public void doSwitchChildWindow(String parent) {
		Set<String> list = driver.getWindowHandles();
		for (String s : list) {
			if (!s.equals(parent)) {
				driver.switchTo().window(s);
			}

		}
	}

	public void doSwitchChildWindowAndClose(String parent) {
		Set<String> list = driver.getWindowHandles();
		for (String s : list) {
			if (!s.equals(parent)) {
				driver.switchTo().window(s);
				System.out.println(driver.getTitle());
				driver.close();
			}

		}
	}

	public ArrayList<String> doGetWindowID() {
		Set<String> winHandles = driver.getWindowHandles();
		ArrayList<String> al = new ArrayList<String>(winHandles);
		return al;
	}

	public String doSwitchWindowAndGetTitle(String WinId) {
		driver.switchTo().window(WinId);
		String title = driver.getTitle();
		System.out.println(title);
		return title;

	}

	// *************Frame Handle*************
	public WebDriver doGetFrameHandleWithFrameWebElt(WebElement frameElement) {
		return driver.switchTo().frame(frameElement);
	}

	public WebDriver doGetFrameHandleWithFrameName(String name) {
		return driver.switchTo().frame(name);
	}

	public void doSwitchDefaultContent() {
		driver.switchTo().defaultContent();
	}

	// ************Actions**********
	public void doMouseHOverAction(By loc) {
		Actions act = new Actions(driver);
		act.moveToElement(findElt(loc)).perform();

	}

	public void doMouseHOverActionAndClick(WebElement hoverElt, By loc) {
		Actions act = new Actions(driver);
		act.moveToElement(hoverElt).perform();
		doClick(loc);

	}
	public void doMouseHOverActionAndClickOnEltFromList(WebElement hoverElt, By loc,String val) {
		Actions act = new Actions(driver);
		act.moveToElement(hoverElt).perform();
		
		List<WebElement> li=findElts(loc);
		for (WebElement e : li) {
			if(e.getText().equals(val))
			{
				e.click();
				break;
			}
			
		}
		
		

	}
//	public void doMouseHOverActionAndClickFor(WebElement hoverElt, By loc) {
//		Actions act = new Actions(driver);
//		act.moveToElement(hoverElt).perform();
//		//findElts(loc);
//		doClick(loc);
//
//	}

	public void doMouseHoverActionDragDrop(By sourceLoc, By targetLoc) {
		Actions act = new Actions(driver);
		act.dragAndDrop(findElt(sourceLoc), findElt(targetLoc)).perform();

	}

	public void doActionsClick(By loc) {
		Actions act = new Actions(driver);
		act.click(findElt(loc)).perform();

	}

	public void doRightClick(By loc) {
		Actions act = new Actions(driver);
		act.contextClick(findElt(loc)).perform();
	}

	public void doSelectRightClickOptions(By loc, String val) {
		List<WebElement> rightOptions = findElts(loc);
		for (WebElement e : rightOptions) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(val)) {
				e.click();
				break;
			}

		}

	}
	// ************CRM PRO**********

	public void doActionSenkeys(By loc, String val) {
		Actions act = new Actions(driver);
		act.sendKeys(findElt(loc), val);

	}

	public void doSelectContact(String ContactName) {
		By xpath = By.xpath("(//a[.='" + ContactName + "']/../preceding-sibling::td/input[@type='checkbox'])[1]");
		driver.findElement(xpath).click();

	}

	public String doGetComapny(String ContactName) {
		By xpath = By.xpath("//a[.='" + ContactName + "']/../following-sibling::td/a[@context='company']");
		return driver.findElement(xpath).getText();

	}

	public String doGetPhone(String ContactName) {
		By xpath = By.xpath("//a[.='" + ContactName + "']/../following-sibling::td/span[@context='phone']");
		return driver.findElement(xpath).getText();

	}

	public String doGetEmail(String ContactName) {
		By xpath = By.xpath("//a[.='" + ContactName + "']/../following-sibling::td/a[contains(@href,'mailto')]");
		return driver.findElement(xpath).getText();
	}

	public ArrayList<String> doGetContactDetails(String ContactName) {
		ArrayList<String> al = new ArrayList<String>();
		al.add(doGetComapny(ContactName));
		al.add(doGetPhone(ContactName));
		al.add(doGetEmail(ContactName));
		return al;
	}

	// ************cric info**********
	public ArrayList<String> doGetScoreBoard(String ContactName) {
		By xpathScore = By.xpath("//a[contains(.,'" + ContactName + "')]/../following-sibling::td");
		ArrayList<String> scoreValueList = new ArrayList<String>();
		List<WebElement> scoreList = driver.findElements(xpathScore);
		for (WebElement e : scoreList) {
			if (!e.getText().isEmpty()) {
				scoreValueList.add(e.getText());
			}

		}
		return scoreValueList;
	}

	// ************Web Table**********
	public ArrayList<String> doGetWebTableData(int rowsize, int colsize) {
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 2; i <= rowsize; i++) {
			for (int j = 1; j <= colsize; j++) {
				String xpath1 = ".//*[@id='leftcontainer']/table/tbody/tr[" + i + "]/td[" + j + "]";
				String text = driver.findElement(By.xpath(xpath1)).getText();
				al.add(text);
			}
			System.out.println();
		}
		return al;

	}

	// ************WAITS**********
	public WebElement waitForELtPresent(By loc, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(loc));
	}

	public WebElement waitForELtVisible(By loc, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}

	public Alert waitForAlertPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String waitForAlertText(int timeout) {

		return waitForAlertPresent(timeout).getText();
	}

	public void waitForAlertAccept(int timeout) {
		waitForAlertPresent(timeout).accept();
	}

	public void waitForAlertDismiss(int timeout) {
		waitForAlertPresent(timeout).dismiss();
	}

	// **********Wait For Title and Url*********
	public String waitForTitle(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public String waitForTitle(String title, int timeout, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, intervalTime);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public String waitForTitleContains(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitForURL(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlToBe(url));
		return driver.getCurrentUrl();
	}

	public Boolean waitForURLContains(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));

	}

	// ***************Wait for Frame***********
	public void waitForFrameAndSwitch(String idOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitch(WebElement frameLoc, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLoc));
	}

	// ***************Wait for Visibility of Elts**********
	public List<WebElement> waitForVisibilityOfELtsLocated(By loc, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loc));
	}

	public ArrayList<String> waitForVisibilityOfELtsLocatedAndGetText(By loc, int timeOut) {
		ArrayList<String> eltTextList = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> eltList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loc));
		for (WebElement e : eltList) {
			if ((!e.getText().isEmpty()) || (!e.getText().isEmpty())) {
				eltTextList.add(e.getText());
			}

		}
		return eltTextList;
	}

	// ***************Wait for Presence of Elts**********
	public List<WebElement> waitForPresenceOfELtsLocated(By loc, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(loc));
	}

	public ArrayList<String> waitForPresenceOfELtsLocatedAndGetText(By loc, int timeOut) {
		ArrayList<String> eltTextList = new ArrayList<String>();
		List<WebElement> eltList = waitForPresenceOfELtsLocated(loc, timeOut);
		for (WebElement e : eltList) {
			if ((!e.getText().isEmpty()) || (!e.getText().isBlank()) || (e.getText() != null)) {
				eltTextList.add(e.getText());
			}
		}
		return eltTextList;

	}

	// ***************Wait for elt to be clickable**********
	public WebElement waitForEltTOBeClickable(By loc, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(loc));
	}

	public void waitClickWhenReady(By loc, int timeOut) {
		waitForEltTOBeClickable(loc, timeOut).click();
	}

	// ***************Fluent Wait**********
	public WebElement waitForPresenceOfEltFluentWait(By loc, int timeOut, long pollingTime) {
		Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return w.until(ExpectedConditions.presenceOfElementLocated(loc));

	}

	public List<WebElement> waitForVisibilityOfELtsLocatedFluent(By loc, int timeOut, long pollingTime) {
		Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loc));
	}

	public List<WebElement> waitForPresenceOfELtsLocatedFluent(By loc, int timeOut, long pollingTime) {
		Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(loc));
	}

	public void waitForFrameAndSwitchFluent(By idOrName, int timeOut, long pollingTime) {
		Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public Alert waitForAlertFluent(int timeOut, long pollingTime) {
		Wait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return w.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForAlertAcceptFluent(int timeout, long pollingTime) {
		waitForAlertFluent(timeout, pollingTime).accept();
	}

	public void waitForAlertDismissFluent(int timeout, long pollingTime) {
		waitForAlertFluent(timeout, pollingTime).dismiss();
	}

	public String waitForAlertGetTextFluent(int timeout, long pollingTime) {
		return waitForAlertFluent(timeout, pollingTime).getText();
	}

	// ************Streams*************
	public void printLinksTextStreams(By loc, int timeOut) {
		waitForVisibilityOfELtsLocated(loc, timeOut).stream().forEach(e -> System.out.println(e.getText()));
	}

	public List<String> getEltsTextListByValStreams(By loc, int timeOut, String val) {
		return waitForVisibilityOfELtsLocated(loc, timeOut).stream().filter(e -> e.getText().contains(val))
				.map(e -> e.getText()).collect(Collectors.toList());
	}

	public List<WebElement> getEltsListByValStreams(By loc, int timeOut, String val) {
		return waitForVisibilityOfELtsLocated(loc, timeOut).stream().filter(e -> e.getText().contains(val))
				.collect(Collectors.toList());
	}

	public void PrintListEltsStreams(List<String> eleList) {
		eleList.forEach(e -> System.out.println(e));
	}

	// ************Calender Handle*************
	public void selectDate(String day, String htmlTag) {
		findElt(By.xpath("//" + htmlTag + "[.='" + day + "']")).click();
	}

	public void selectDate(By loc, String day) {
		List<WebElement> li = findElts(loc);
		for (WebElement e : li) {
			if (e.getText().equals(day)) {
				e.click();
				break;
			}

		}
	}

	public void selectDateStream(By loc, String day) {
		List<WebElement> li = findElts(loc);
		li.stream().filter(e -> e.getText().contains(day)).forEach(e -> e.click());

	}

	// ************Carousel Handle*************
	public ArrayList<String> carouselClickNext(By eltLoc, By nextLoc) {
		List<WebElement> list = findElts(eltLoc);
		System.out.println(list.size());
		ArrayList<String> al = new ArrayList<String>();
		Set<String> ds = new TreeSet<String>();
		while (!findElt(nextLoc).getAttribute("class").contains("swiper-button-disabled")) {
			for (WebElement e : list) {
				String text = e.getText();
				// System.out.println(text);
				if (!text.isEmpty()) {
					al.add(text);
				}
			}
			findElt(nextLoc).click();

		}
		al.stream().forEach(e -> System.out.println(e));
		return al;
	}

}
