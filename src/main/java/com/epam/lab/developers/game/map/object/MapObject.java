package com.epam.lab.developers.game.map.object;

public abstract class MapObject {

	private int i;
	private int j;
	private int rotationAngle;
	private String serverPath;
	
	public MapObject(int i,int j,int rotationAngle ,String path){
		this.i =i;
		this.j=j;
		this.rotationAngle = rotationAngle;
		this.serverPath = path;
	}	

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public String getServerPath() {
		return serverPath;
	}

	public int getRotationAngle() {
		return rotationAngle;
	} 
	
}
