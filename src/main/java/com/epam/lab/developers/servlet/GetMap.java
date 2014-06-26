package com.epam.lab.developers.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.game.map.GameMap;
import com.google.gson.Gson;

@Controller
@RequestMapping("/get_map")
public class GetMap {


	@RequestMapping(method=RequestMethod.POST)
    public @ResponseBody String getMap(@RequestParam String message, HttpServletRequest request) {

        User user = LoginData.userLogined(request);
        if(user!=null) {
            Game game = DataHolder.getInstance().getGame(user);
            GameMap map = game.getMap();

            String json = new Gson().toJson(map);

            if(message.equals("give_me_object")) { // чи запит на отримання даних

                return json;

            } 
        }
        
        return "'error':'Incomming command is incorrect="+message+"'";
    }

}
