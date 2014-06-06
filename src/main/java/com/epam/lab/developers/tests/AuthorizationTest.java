package com.epam.lab.developers.tests;

import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;

public class AuthorizationTest {

private WebTester tester;
	
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/developers");
	}
	
	@Test
	public void LoginTest(){
		tester.beginAt("/home");
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl) {
			((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient().setAjaxController(new NicelyResynchronizingAjaxController());
		}
		tester.assertFormPresent("formLogin");
		tester.setTextField("name_login", "Roman");
		tester.setTextField("password_login", "1111");
		tester.assertTextFieldEquals("name_login", "Roman");
		tester.assertTextFieldEquals("password_login", "1111");
		tester.assertButtonPresent("s_Login");
		tester.clickButton("s_Login");
		tester.assertTitleEquals("Developers - Connect");
	}
	
}
