package com.epam.lab.developers.game.map.object.active;

import com.epam.lab.developers.game.Team;
import com.epam.lab.developers.game.map.unit.Effect;
import com.epam.lab.developers.game.map.unit.Unit;

public class Server extends ActiveObject {

    private Team team;

    public Server(int x, int y, int rotationAngle, String path) {
        super(x, y, rotationAngle, path);
        // TODO Auto-generated constructor stub
    }

    @Override
    public float use(Unit unit) {
        // TODO Auto-generated method stub

        if(unitChecking(unit))
            switch(unit.getTask().getTask()) {

                case "use":
                    this.use();
                    break;

                case "breakDown":
                    this.breakDown();

                default:
                    return super.use(unit);

            }

        return processPercentage;

    }

    private void breakDown() {
        // TODO Auto-generated method stub

        for(Unit unit : team.getUnits()) {

            unit.getUnitStatus().addEffect("Server Break", (new Effect(20000)).addFeature("code", new Double(-1)));

            System.out.println(unit.getName());
        }

        activeUnit.getTask().setTask("stop");
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public void action() {
        // TODO Auto-generated method stub

    }

}
