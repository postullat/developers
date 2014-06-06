package com.epam.lab.developers.game.map;

import java.util.List;

import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.object.active.ActiveObject;

public class GameMap {

	private int width;
	private int height;
	private int frameWidth;
	private int frameHeight;
	private int rows;
	private int columns;
	private int playerCount;
	private MapObject[][] mapObjects;
	

	public GameMap() {
		// TODO Auto-generated constructor stub
	}

	public GameMap(int frameWidth, int frameHeight, int rows,
			int columns, int playerCount) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.rows = rows;
		this.columns = columns;
		this.playerCount = playerCount;
		this.width = this.columns * frameWidth;
		this.height = this.rows * frameHeight;
		this.mapObjects = new MapObject[rows][columns];
	}

	public void setMapObjects(MapObject object) {
		mapObjects[object.getI()][object.getJ()] = object;
	}
	

	public void setMapObjects(MapObject[][] mapObjects) {
		this.mapObjects = mapObjects;
	}


	
	public MapObject[][] getMapObjects() {
		return mapObjects;
	}
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public int getPlayerCount() {
		return playerCount;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

}
