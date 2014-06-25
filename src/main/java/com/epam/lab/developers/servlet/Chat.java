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

            String command = request.getParameter("what"); // ���������� �� ����� �� ����� ����� � ��� �� �� ����������
            Game game = DataHolder.getInstance().getGame(user); // ������ � ��� � ��� � ����� ����

            if(game != null) {

                if(command.equals("write")) { // ���� ����� �� ����� ����� � ���

                    GameChat gamechat = new GameChat(); // ��������� ���� ���� ����-�����������
                    String message = request.getParameter("message"); // ������ ����������� ��� �������

                    Date date = new Date();
                    // System.out.println("date = "+date.getTime());
                    gamechat.setTime(date.getTime());
                    gamechat.setUser(user.getName()); // ������ ��� �����
                    gamechat.setMessage(message); // ������ ����������� ��� �� �������

                    game.getChat().add(gamechat); // ������ ����/����������� � ���

                }
                if(command.equals("read")) { // ���� ����� �� ������� ����� � ����

                    List<GameChat> chat = game.getChat(); // ������ �������� ���� � ���
                    Gson gson = new Gson(); // ��������� ���� Gson
                    String jsonChat = gson.toJson(chat); // ��������� json ����

                    // ���� ����� � �������� ����� 10 �� ���������, ��� �� ���������� ��������� ���������
//                    if(chat.size() > 10) { // �� ��������!!!
//                        chat.remove(0); // ��������� ������ �������, ��� �� ���� ����������� �������� ������ ������
//                    }

                    PrintWriter writer = response.getWriter(); // ��������� ���� ��� ��������� ����� �� �볺��
                    writer.write(jsonChat); // �������� json �� �볺��
                    writer.close(); // ��������� �������

                }
            }

        }
    }

}
