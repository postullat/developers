package com.epam.lab.developers.servlet;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.entity.MyUserDetails;
import com.epam.lab.developers.entity.User;

@Controller
@RequestMapping("/exit-game")
public class ExitGame {


	@RequestMapping(method=RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> doExit(Principal principal, HttpServletRequest request) {
        
		User user = ((MyUserDetails) ((Authentication) principal).getPrincipal()).getUser();
        if(null != user) {
            DataHolder.getInstance().exitGame(user);

            return new ResponseEntity<String>(HttpStatus.OK);
        }
        
        return new ResponseEntity<String>("User object equals null", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
