package com.epam.lab.developers.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.game.map.GameMap;
import com.google.gson.Gson;

@WebServlet("/get_map")
public class GetMap extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public GetMap() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        //
        // MapDAO mapObject = new MapDAO(); //��������� �����, ���� ������ ���������� ����� �� ��������� �� ��
        // ��������
        //
        // GameMap map = null;
        // try {
        // map = mapObject.getByName("test");
        // } catch (SQLException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        User user = LoginData.userLogined(request);
        if(null != user) {
            Game game = DataHolder.getInstance().getGame(user);
            GameMap map = game.getMap();

            Gson gson = new Gson(); // ��������� ����� gson ���� ���� ������������� ����� � ������ json
            String json = new String(); // ������ � ��� ���� ������������ ����������� json

            json = gson.toJson(map); // ��������� ������ � ������ json

            String message = request.getParameter("message"); // ��������� ���������, ���� ��������� �� ��� ����� ��
                                                              // ��������� ����� ��� ��������
            // System.out.println(message);

            if(message.equals("give_me_object")) { // �� ����� �� ��������� �����

                // System.out.println(json); //���� json �� �������
                PrintWriter pw = response.getWriter(); // ��������� ����� PrintWriter
                pw.write(json); // ���������� json �� �볺��
                pw.close(); // �������� ������� PrintWriter

            } else {
                System.out.println("�� ����� ������ ������");
            }
        }
    }

}
