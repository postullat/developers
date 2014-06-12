package com.epam.lab.developers.game;

import java.util.ArrayList;
import java.util.List;

import com.epam.lab.developers.game.map.object.active.ActiveObject;
import com.epam.lab.developers.game.map.unit.Unit;

public class Team {

    private transient int id;
    private List<Unit> units = new ArrayList<>();
    private transient List<ActiveObject> activeObjects = new ArrayList<>();
    private transient float codeLines = 0;
    private transient Unit activeUnit;
    private transient ActiveObject selectedActiveObject;

    public Unit getActiveUnit() {
        return activeUnit;
    }

    public void setActiveUnit(Unit activeUnit) {
        this.activeUnit = activeUnit;
    }

    public Team() {

    }

    public Team(int id, List<ActiveObject> activeObjects, List<Unit> units) {
        this.id = id;
        this.units = units;
        this.activeObjects = activeObjects;
    }

    public int getId() {
        return id;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<ActiveObject> getActiveObjects() {
        return activeObjects;
    }

    public void setActiveObjects(List<ActiveObject> activeObjects) {
        this.activeObjects = activeObjects;
    }

    public float getCodeLines() {
        return codeLines;
    }

    public void setCodeLines(float codeLines) {
        this.codeLines = codeLines;
    }

    public ActiveObject getSelectedActiveObject() {
        return selectedActiveObject;
    }

    public void setSelectedActiveObject(ActiveObject selectedActiveObject) {
        this.selectedActiveObject = selectedActiveObject;
    }

}
