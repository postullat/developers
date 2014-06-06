package com.epam.lab.developers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.servlet.json.UserWinLooseJson;
import com.google.gson.Gson;

@WebServlet("/finish-game")
public class FinishGame extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public FinishGame() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        User user = LoginData.userLogined(request);
        if(null != user) {

            String command = request.getParameter("what"); // перевіряємо чи запит на запис даних в чат чи на зчитування
            Game game = DataHolder.getInstance().getGame(user); // беремо в гру в якій є зараз юзер

            if(game != null) {

                if(command.equals("getWinLoose")) { // якщо запит на запис даних в чат

                    UserWinLooseJson winLoose = new UserWinLooseJson();
                    winLoose.setUserWin(game.getUserWin().getName());
                    winLoose.setUserLoose(game.getUserLoose().getName());

                    String json = new Gson().toJson(winLoose);
                    // try{
                    //
                    response.getWriter().println(json);
                    // System.out.println(json);
                    // } catch(Exception e){
                    // e.printStackTrace();
                    // System.out.println("userWin="+userWin+" userLoose="+userLoose);
                    // System.out.println("{\"userWin\":"+userWin+",\"userLoose\":"+userLoose+"}");
                    // }
                }

            }

        }
    }

}
