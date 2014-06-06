package com.epam.lab.developers.tests;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class RootController {

    private WebTester tester;

    @Before
    public void prepare() {
        tester = new WebTester();
        tester.setBaseUrl("http://localhost:8080/developers");
    }

    @Test
    public void test() {
        // перевірка елементів для модуля реєстрації
        tester.beginAt("/");

        tester.assertButtonPresent("s_Register"); // чи існує кнопка з ід s_Login
        tester.assertElementPresent("name"); // чи існує поле з ід name
        tester.assertElementPresent("password"); // чи існує поле з ід name
        tester.assertElementPresent("r_password"); // чи існує поле з ід name
        tester.assertElementPresent("email"); // чи існує поле з ід name

        tester.setTextField("name", "aquilla"); // записуєм в поле name значення aquilla
        tester.setTextField("password", "12345"); // записуєм в поле password значення 12345
        tester.setTextField("r_password", "12345"); // записуєм в поле r_password значення 12345
        tester.setTextField("email", "aquilla@i.ua"); // записуєм в поле email значення aquilla@i.ua
        tester.submit("b"); // натискаємо кнопку submit

        // перевірка елементів для модуля логування
        tester.beginAt("/");
        tester.assertButtonPresent("s_Login"); // чи існує кнопка з ід s_Login
        tester.assertElementPresent("name_login"); // чи існує поле з ід name_login
        tester.assertElementPresent("password_login"); // чи існує поле з ід password_login

        tester.setTextField("name_login", "aquilla"); // записуєм в поле name_дщпшт значення aquilla
        tester.setTextField("password_login", "12345"); // записуєм в поле password_login значення 12345
        tester.submit("a"); // натискаємо кнопку submit
    }

}
