package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.game.Team;
import com.google.gson.Gson;

/**
 * Servlet implementation class GetUnit
 */
@WebServlet("/get_unit")
public class GetUnit extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GetUnit() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        User user = LoginData.userLogined(request);
        if(null != user) {

            Game game = DataHolder.getInstance().getGame(user);
            List<Team> teams = game.getTeams();
            Gson gson = new Gson();
            String jsonUnits = gson.toJson(teams);
            // System.out.println(jsonUnits);

            PrintWriter writer = response.getWriter();
            writer.write(jsonUnits);
            writer.close();

        }
    }
}
