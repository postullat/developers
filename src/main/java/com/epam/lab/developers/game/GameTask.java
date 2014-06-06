package com.epam.lab.developers.game;

import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;

import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.map.unit.Unit;

public class GameTask extends TimerTask {
	private Game game;
	private float codeLines;

	public GameTask(Game game) {
		super();
		this.game = game;
	}

	@Override
	public void run() {
		for (User user : game.getPlayers()) {
			codeLines = 0;
			Iterator<Unit> iterator = user.getTeam().getUnits().iterator();
			while (iterator.hasNext()) {
				Unit unit = iterator.next();
				try {
					unit.action();
					codeLines += unit.getCodeLines();
					checkReputation(unit, iterator);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Can not do unit.action()");
				}
			}
			user.getTeam().setCodeLines(codeLines);
			if (codeLines >= Game.CODE_LINES) {
				game.finish(user);
			}
		}
	}

	private void checkReputation(Unit unit, Iterator<Unit> iterator) {
		if (unit.getUnitStatus().getReputation() < -50) {
			iterator.remove();
			GameChat gameChat = new GameChat();
			gameChat.setMessage("[" + unit.getName() + " was fired]");
			gameChat.setTime(new Date().getTime());
			gameChat.setUser("ADMIN");
			this.game.getChat().add(gameChat);
		}
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
