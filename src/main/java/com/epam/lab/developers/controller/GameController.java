package com.epam.lab.developers.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/game")
public class GameController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GameController() {
        super();

    }

    public void init(ServletConfig config) throws ServletException {
        // ����� ���� ���� ������������ ��� ����������� ��������
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response); // ������������� ������ Get �� ����� Post

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // ������� ��������� ����� �� �������.
        // �������� ���
        // ��� ���������� �� ���

        // ���� ������� ����� �� ��������� ��� - ��
        // �������� ����� ���, ����������� �� �볺�� ����� � ��������� �� ���������

        // ���� ������� ����� �� ��������� �� ��� - ��
        // ����������� ����� �� ������������

        // ������������� ������ �� ������� game.jsp
        String url = "/WEB-INF/view/game.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

}
