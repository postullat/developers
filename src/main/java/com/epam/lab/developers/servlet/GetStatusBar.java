package com.epam.lab.developers.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epam.lab.developers.data.DataHolder;
import com.epam.lab.developers.data.LoginData;
import com.epam.lab.developers.domain.User;
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.unit.Unit;
import com.epam.lab.developers.game.map.unit.UnitStatus;
import com.epam.lab.developers.game.map.unit.UnitTask;
import com.epam.lab.developers.servlet.json.StatusBar;
import com.epam.lab.developers.servlet.json.StatusBar.UnitStatusBar;
import com.google.gson.Gson;

@Controller
@RequestMapping("/get-status-bar")
public class GetStatusBar {

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody
	String getStatusBar(HttpServletRequest request) {

		User user = LoginData.userLogined(request);
		if (user != null && DataHolder.getInstance().getGame(user) != null) {

			Team team = user.getTeam();
			float mycodeLines = team.getCodeLines();
			List<UnitStatusBar> unitStatusBars = new ArrayList<>();
			for (Unit unit : team.getUnits()) {
				String name = unit.getName();
				String initTexture = unit.getTextures().get(0);
				double mood = 0;
				double reputation = 0;
				double health = 0;
				float codeLines = unit.getCodeLines();
				UnitStatus unitStatus = unit.getUnitStatus();
				if (unitStatus != null) {
					mood = Math.round(unit.getUnitStatus().getMood());
					reputation = Math.round(unit.getUnitStatus().getReputation());
					health = Math.round(unit.getUnitStatus().getHealth());
				}
				String task = "";
				UnitTask unitTask = unit.getTask();
				if (unitTask != null) {
					task = unitTask.getTask();
				}
				unitStatusBars.add(new UnitStatusBar(name, initTexture, mood, reputation, health, task, codeLines));
			}
			Unit activeUnit = team.getActiveUnit();
			UnitStatusBar selectedUnitStatusBar = null;
			if (activeUnit != null) {
				String name = activeUnit.getName();
				String initTexture = activeUnit.getTextures().get(0);
				double mood = 0;
				double reputation = 0;
				double health = 0;
				float codeLines = activeUnit.getCodeLines();
				UnitStatus unitStatus = activeUnit.getUnitStatus();
				if (null != unitStatus) {
					mood = Math.round(activeUnit.getUnitStatus().getMood());
					reputation = Math.round(activeUnit.getUnitStatus().getReputation());
					health = Math.round(activeUnit.getUnitStatus().getHealth());
				}
				String task = "";
				UnitTask unitTask = activeUnit.getTask();
				if (null != unitTask) {
					task = unitTask.getTask();
				}
				selectedUnitStatusBar = new UnitStatusBar(name, initTexture, mood, reputation, health, task, codeLines);
			}
			StatusBar statusBar = new StatusBar(mycodeLines, unitStatusBars, selectedUnitStatusBar);

			return new Gson().toJson(statusBar);
		}
		
		return "{'error':'Can not get status bar'}";
	}

}
