package com.epam.lab.developers.game.map.algorithm_way;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.lab.developers.servlet.Register;

public class WaveAlgorithm {
	private int [][] map;
	private int xA, yA, xB, yB;
	private LinkedList<Step> way = new LinkedList<>();;
	static final Logger logger = Logger.getLogger(WaveAlgorithm.class);
	private int wigth;
	private int height;
	private int x1,y1;
	
	public WaveAlgorithm(int[][] map, int xA, int yA, int xB, int yB, int wigth, int height) {
		this.map = map;
		this.xA = xA;
		this.xB = xB;
		this.yA = yA;
		this.yB = yB;
		this.height = height;
		this.wigth = wigth;
	}
	
	public String Algorithm (){
		//way = new ArrayList<>();
		int n = 15;//висота
		int k = 20; //довжина
		int l;//кількість кроків
		int x,y;
		int[][] mapM =new int[n][k];
		if(xA==xB && yA == yB)return "too_close";
		if(map[xA][yA]!=1 || map[xB][yB] !=1){
			mapM[xA][yA]=1;
			l=1;
			do{
				l+=1;
				for(int i=0;i<n;++i){
					for(int j=0;j<k;++j){
						if(mapM[i][j]==l-1){
							if(j<k-1){
								if(map[i][j+1]==0 && mapM[i][j+1]==0)mapM[i][j+1]=l;
							}
							if(j>0){
								if(map[i][j-1]==0 && mapM[i][j-1]==0)mapM[i][j-1]=l;
							}
							if(i<n-1){
								if(map[i+1][j]==0 && mapM[i+1][j]==0)mapM[i+1][j]=l;
							}
							if(i>0){
								if(map[i-1][j]==0 && mapM[i-1][j]==0)mapM[i-1][j]=l;
							}
						}
					}
				}
				if(l>n*k){
					logger.debug("way doesn t exist"+xA+" "+yA+"point of destination:"+xB +" "+yB);
					return "way doesn t exist";
				}
			}
			while(mapM[xB][yB]==0);
		}else {
			logger.debug("wrong to point of destination:"+xB +yB);
			return "wrong to point of destination";
		}
		l=l-1;
		x=xB;
		y=yB;
		x1=x;
		y1=y;
		do{	
			if (x1<x){
				x1 = x1 *wigth;
				for(int i=1; i<5; ++i)way.add(new Step(y*height, x1+i*10));
				x1=x;
			}
			if(x1>x){
				x1 = x1 *wigth;
				for(int i=1; i<5; ++i)way.add(new Step(y*height, x1-i*10));
				x1=x;
			}
			if(y1>y){
				y1 = y1 *height;
				for(int i=1; i<5; ++i)way.add(new Step(y1-i*10, x*wigth));
				//y1=y;
			}
			if(y1<y){
				y1 = y1 *height;
				for(int i=1; i<5; ++i)way.add(new Step(y1+i*10, x*wigth));
				//y1=y;
			}
			way.add(new Step(y*height, x*wigth));

			x1=x;
			y1=y;
			if(x < n-1){ 
				if(mapM[x][y] - mapM[x + 1][ y] == 1) {
					x=x+1;
					continue;
				}
			}
			
			if(x > 0){
				if(mapM[x][y] - mapM[x - 1][ y] == 1){ 
					x=x-1;
					continue;
				}
			}
			if(y < k-1){
				if(mapM[x][y] - mapM[x][y + 1] == 1){ 
					y=y+1;
					continue;
				}
			}
			if(y > 0){
				if(mapM[x][y] - mapM[x][y - 1] == 1){ 
					y=y-1;
					continue;
				}
			}
			l-=1;
			if(y==yA && x==xA){
				
			}
		}while(y!=yA || x!=xA  );
		if (x1<x){
			x1 = x1 *wigth;
			for(int i=1; i<5; ++i)way.add(new Step(y*height, x1+i*10));
			x1=x;
			
		}
		if(x1>x){
			x1 = x1 *wigth;
			for(int i=1; i<5; ++i)way.add(new Step(y*height, x1-i*10));
			x1=x;
			
		}
		if(y1>y){
			y1 = y1 *height;
			for(int i=1; i<5; ++i)way.add(new Step(y1-i*10, x*wigth));
			y1=y;
			
		}
		if(y1<y){
			y1 = y1 *height;
			for(int i=1; i<5; ++i)way.add(new Step(y1+i*10, x*wigth));
			y1=y;
			
		}
//		System.out.println(way.toString());
		
		return "good";
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public LinkedList<Step> getWay() {
		return way;
	}

	public void setWay(LinkedList<Step> way) {
		this.way = way;
	}
	

}
