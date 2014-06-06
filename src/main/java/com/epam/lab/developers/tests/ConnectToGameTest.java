package com.epam.lab.developers.tests;

import net.sourceforge.jwebunit.htmlunit.HtmlUnitTestingEngineImpl;
import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;

public class ConnectToGameTest {

	private WebTester tester;
	
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/developers");
	}
	
	@Test
	public void testCreateGame() {
		tester.beginAt("/home");
		// зробити Ajax-виклики синхронними
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl) {
			((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient().setAjaxController(new NicelyResynchronizingAjaxController());
		}
		login("ostap", "123");
		// чи переходить на connect, коли ввійшов на сайт
		tester.assertTitleEquals("Developers - Connect");
		tester.assertElementPresent("createGame");
		tester.clickElementByXPath("//*[@id='createGame']");
		// чи переходить на game, коли створив гру
		tester.assertTitleEquals("Developers - Game");
	}
	
	@Test
	public void testJoinToGame() {
		tester.beginAt("/home");
		// зробити Ajax-виклики синхронними
		if (tester.getTestingEngine() instanceof HtmlUnitTestingEngineImpl) {
			((HtmlUnitTestingEngineImpl) tester.getTestingEngine()).getWebClient().setAjaxController(new NicelyResynchronizingAjaxController());
		}
		login("omg", "123");
		// чи переходить на connect, коли ввійшов на сайт
		tester.assertTitleEquals("Developers - Connect");
		tester.assertElementPresent("game 1");
		tester.clickElementByXPath("//*[@id='game 1']");
		// чи переходить на game, коли приєднався до гри
		tester.assertTitleEquals("Developers - Game");
	}
	
	private void login(String name, String password) {
		tester.assertFormPresent("formLogin");
		tester.setTextField("name_login", name);
		tester.setTextField("password_login", password);
		tester.assertTextFieldEquals("name_login", name);
		tester.assertTextFieldEquals("password_login", password);
		tester.assertButtonPresent("s_Login");
		tester.clickButton("s_Login");
	}
	
}
