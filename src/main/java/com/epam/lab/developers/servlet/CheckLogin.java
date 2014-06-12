package com.epam.lab.developers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.developers.dao.impl.UserDAO;
import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.User;
import com.google.gson.Gson;

@WebServlet("/check-login")
public class CheckLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CheckLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println("User_name"+request.getParameter("user_name"));
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession();

        // System.out.println("User_name"+request.getParameter("user_name"));
        /* якщо залогінений вертає користувача на сторінку */
        if(isLogined(request, session)) {
            // перевіряє чи прийшов запит на виведення даних іншого користувача
            if(request.getParameter("user_name") == null) {

                User user = DataHolder.getInstance().getUserSessions().get(session);
                response.getWriter().println(new Gson().toJson(user));
            } else {
                // якщо прийшов то вибрати дані про користувача з БД
                response.getWriter().println(new Gson().toJson(getUserByNick(request.getParameter("user_name"))));
            }

        }
    }

    /* превіряє чи залогінений користувач */
    private boolean isLogined(HttpServletRequest request, HttpSession session) {
        return DataHolder.getInstance().getUserSessions().containsKey(session);
    }

    // вибирає з БД користувачача за ім'ям
    private User getUserByNick(String nick) {

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getByName(nick);

        return user;
    }

}
