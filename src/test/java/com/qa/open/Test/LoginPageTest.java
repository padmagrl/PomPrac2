package com.qa.open.Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.open.Base.BaseTest;
import com.qa.open.Utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String title = lp.getLoginPageTitle();
		Assert.assertEquals(title,"Account Login");
	}
	@Test
	public void loginPageUrlTest() {
		String url = lp.getLoginPageUrl();
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_VAL));
	}
	@Test
	public void loginPageisPwdLinkExistTest() {
		boolean b = lp.loginPageisPwdLinkExist();
		Assert.assertTrue(b);
	}
	@Test
	public void loginPagedoLoginTest() {
		lp.doLogin("padmatadela@gmail.com", "potta123$");
	}

}
