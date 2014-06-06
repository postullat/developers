package com.epam.lab.developers.game.map.algorithm_way;

public class Step {
	
	private int x;	
	private int y;
	
	
	public Step(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString (){
		return this.x +", " + this.y+"\n";
	}
	


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
