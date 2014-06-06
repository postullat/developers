package com.epam.lab.developers.tests;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {

    private WebTester tester;

    @Before
    public void prepare() {
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/developers");

    }

    @Test
    public void test() throws InterruptedException {

        tester.beginAt("/home");
        tester.clickLink("btnLogout");
        tester.clickLink("btnLogin");
        tester.assertFormElementPresent("name_login");
        tester.setTextField("name_login", "omg"); // ������� � ���� login_����� �������� aquilla
        tester.setTextField("password_login", "123"); // ������� � ���� password_login �������� 12345
        tester.assertTextFieldEquals("name_login", "omg");
        tester.assertTextFieldEquals("password_login", "123");
        tester.submit();

        tester.assertLinkPresent("btnProfile");
        // tester.assertLinkPresent("btnLogout");
        tester.clickLink("btnProfile");
        // tester.beginAt("/profile");
        // tester.assertElementPresent("userInfo");
        // tester.assertElementPresent("sidebar_text");
        // tester.clickLinkWithImage("resources/img/user/335rabstol_ru_177.jpg");
        // tester.clickLink("userPhotoImg");
        // tester.assertButtonPresent("button");
        // tester.assertButtonPresent("s_Login"); //�� ����
        // tester.dumpCookies();
        // tester.clickLink("btnLogout");

    }

}
