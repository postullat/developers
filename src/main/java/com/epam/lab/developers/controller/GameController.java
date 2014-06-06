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
        // Метод який буде виконуватись при завантаженні сервлету
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response); // переадресація запиту Get на метод Post

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        // потрібно перевірити запит що прийшов.
        // створити гру
        // або приєднатись до гри

        // якщо прийшов запит на створення гри - то
        // створити обєкти гри, підвантажити на клієнт карти і очікувати на суперника

        // якщо прийшов запит на приєднання до гри - то
        // підвантажити карти із координатами

        // переадресація запиту на сторінку game.jsp
        String url = "/WEB-INF/view/game.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

}
