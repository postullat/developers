package com.epam.lab.developers.game.map.algorithm_way;

import com.epam.lab.developers.game.map.GameMap;
import com.epam.lab.developers.game.map.object.MapObject;
import com.epam.lab.developers.game.map.object.passive.Desk;
import com.epam.lab.developers.game.map.object.passive.Floor;
import com.epam.lab.developers.game.map.object.passive.Wall;
import com.epam.lab.developers.game.map.object.passive.WallCorner;

public class MapWrapper {
	
	private int[][] mapForAlgorithm;
	
	public MapWrapper(GameMap gameMap) {
		// ! інверсія координат
		mapForAlgorithm = new int[gameMap.getColumns()][gameMap.getRows()];
		for (int i = 0; i < gameMap.getColumns(); ++i) {
			for (int j = 0; j < gameMap.getRows(); ++j) {
				mapForAlgorithm[i][j] = getTypeOfObject(gameMap.getMapObjects()[j][i]);
			}
		}
	}

	private int getTypeOfObject(MapObject mapObject) {
		int type;
		if (mapObject instanceof Floor) {
			type = 0;
		} else if (mapObject instanceof Wall || mapObject instanceof WallCorner) {
			type = 1;
		} else if (mapObject instanceof Desk) {
			type = 2;
		} else {
			type = 3;
		}
		return type;
	}

	public int[][] getMapForAlgorithm() {
		return mapForAlgorithm;
	}
	
	
}
