package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.domain.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.game.GameChat;
import com.google.gson.Gson;

@WebServlet("/chat")
public class Chat extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Chat() {
        super();

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

                if(command.equals("write")) { // якщо запит на запис даних в чат

                    GameChat gamechat = new GameChat(); // створюємо обєкт типу юзер-повідомлення
                    String message = request.getParameter("message"); // беремо повідомлення яке прийшло

                    Date date = new Date();
                    // System.out.println("date = "+date.getTime());
                    gamechat.setTime(date.getTime());
                    gamechat.setUser(user.getName()); // пишемо імя юзера
                    gamechat.setMessage(message); // пишемо повідомлення яке він написав

                    game.getChat().add(gamechat); // додаємо юзер/повідомлення в гру

                }
                if(command.equals("read")) { // якщо запит на читання даних з чату

                    List<GameChat> chat = game.getChat(); // беремо колекцію чату з гри
                    Gson gson = new Gson(); // створюємо обєкт Gson
                    String jsonChat = gson.toJson(chat); // створюємо json чату

                    // якщо обєктів в колекції більше 10 то видаляємо, щоб не передавати мегабайти переписки
//                    if(chat.size() > 10) { // НЕ СТРИРАТИ!!!
//                        chat.remove(0); // видаляємо перший елемент, так як нове повідомлення додається вкінець списку
//                    }

                    PrintWriter writer = response.getWriter(); // створюємо обєкт для відсилання даних на клієнт
                    writer.write(jsonChat); // відсилаємо json на клієнт
                    writer.close(); // закриваємо зєднання

                }
            }

        }
    }

}
