package com.epam.lab.developers.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.epam.lab.developers.entity.User;
import com.epam.lab.developers.game.Game;
import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.GameMap;
import com.epam.lab.developers.servlet.json.GameJson;

public class DataHolder {

    private static final DataHolder INSTANCE = new DataHolder();

    // static final Logger logger = Logger.getLogger(DataHolder.class);
    private Map<HttpSession, User> userSessions = new HashMap<>();
    private List<Game> games = new ArrayList<>();
    private Map<User, Game> userGames = new HashMap<>();

    // static {
    //
    // PropertyConfigurator.configure(getInstance().getClass().getResource(
    // "/log4j.properties"));
    // }

    private DataHolder() {
    }

    // отримуЇ користувача по ≥мен≥, €кщо в≥н зараз граЇ гру
    // це потр≥бно лд€ сервлета Login, бо коли користувач в гр≥ ≥ вийшов з
    // сайту,
    // ми даЇм йому шанс ще повернутис€ в гру
    public User getPlayingUserByName(String userName) {
        User user = null;
        for(User playingUser : this.userGames.keySet()) {
            if(playingUser.getName().equals(userName)) {
                user = playingUser;
                break;
            }
        }
        return user;
    }

    // к≥льк≥сть ≥гор
    public int getAmountOfGames() {
        return this.games.size();
    }

    // отримати список ≥гор, €к≥ чекають старту
    public List<GameJson> getGamesToJoin() {
        List<GameJson> listNotRunning = new ArrayList<>();
        for(Game game : this.games) {
            if(!game.isRunningGame()) {
                listNotRunning.add(getGameJson(game));
            }
        }
        return listNotRunning;
    }

    // отримати json, €кий в≥дображаЇ гру
    public GameJson getGameJson(Game game) {
        boolean isJoinAvailable = game.getPlayers().size() < game.getMap().getPlayerCount();
        GameJson gameJson = new GameJson(game, isJoinAvailable);
        return gameJson;
    }

    // створити нову гру
    public void createGame(String name, GameMap map, List<Team> teams, User creator) {
        Game game = new Game(name, map, teams, creator);
        this.games.add(game);
        this.userGames.put(creator, game);
    }

    // приЇднатись до гри
    public boolean joinToGame(String gameName, User user) {
        boolean isJoined = false;
        for(Game game : this.games) {
            if(game.getName().equals(gameName)) {
                // приЇднати користувача до гри
                if(game.addPlayerAndSetTeam(user)) {
                    this.userGames.put(user, game);
                    isJoined = true;
                    break;
                }
            }
        }
        return isJoined;
    }

    // чи користувач граЇ гру
    public boolean isUserPlaying(User user) {
        return this.userGames.containsKey(user);
    }

    // отримати гру, €ку граЇ користувач
    public Game getGame(User user) {
        return this.userGames.get(user);
    }

    // отримати гру по назв≥
    public Game getGameByName(String gameName) {
        Game game = null;
        for(Game findedGame : this.games) {
            if(findedGame.getName().equals(gameName)) {
                game = findedGame;
                break;
            }
        }
        return game;
    }

    // вийти з гри
    public void exitGame(User user) {
        Game game = this.userGames.get(user);
        if(null != game) {
            game.getPlayers().remove(user); // вилучити з гри користувача
            if(game.getPlayers().isEmpty()) { // €кщо немаЇ жодного гравц€ у
                                              // гр≥ - зупинити ≥ видалити гру
                game.stop();
                this.games.remove(game);
            }
            this.userGames.remove(user);
            // logger.debug(user.getName() + " exit the game" + game.getName()
            // + " date of create " + game.getDateOfCreation()
            // + " creator:" + game.getCreator());
        }
    }

    public Map<HttpSession, User> getUserSessions() {
        return userSessions;
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }

}
