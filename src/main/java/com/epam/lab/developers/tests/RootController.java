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
        // �������� �������� ��� ������ ���������
        tester.beginAt("/");

        tester.assertButtonPresent("s_Register"); // �� ���� ������ � �� s_Login
        tester.assertElementPresent("name"); // �� ���� ���� � �� name
        tester.assertElementPresent("password"); // �� ���� ���� � �� name
        tester.assertElementPresent("r_password"); // �� ���� ���� � �� name
        tester.assertElementPresent("email"); // �� ���� ���� � �� name

        tester.setTextField("name", "aquilla"); // ������� � ���� name �������� aquilla
        tester.setTextField("password", "12345"); // ������� � ���� password �������� 12345
        tester.setTextField("r_password", "12345"); // ������� � ���� r_password �������� 12345
        tester.setTextField("email", "aquilla@i.ua"); // ������� � ���� email �������� aquilla@i.ua
        tester.submit("b"); // ��������� ������ submit

        // �������� �������� ��� ������ ���������
        tester.beginAt("/");
        tester.assertButtonPresent("s_Login"); // �� ���� ������ � �� s_Login
        tester.assertElementPresent("name_login"); // �� ���� ���� � �� name_login
        tester.assertElementPresent("password_login"); // �� ���� ���� � �� password_login

        tester.setTextField("name_login", "aquilla"); // ������� � ���� name_����� �������� aquilla
        tester.setTextField("password_login", "12345"); // ������� � ���� password_login �������� 12345
        tester.submit("a"); // ��������� ������ submit
    }

}
