package com.tony.engine.gui;

import com.tony.engine.core.Game;
import com.tony.engine.entities.Player;

public class Camera{
	private int x;
	private int y;

	public Camera(int x,int y){
		this.x=x;
		this.y=y;
	}

	public void setX(int x){this.x=x;}
	public void setY(int y){this.y=y;}
	public int getX(){return x;}
	public int getY(){return y;}

	public void tick(double delta,Player player){
		int winW = (int)Game.windowHandle.getW();
		int winH = (int)Game.windowHandle.getH();
		int plX  = (int)player.getX();
		int plY  = (int)player.getY();

		x = -plX + winW/2;
		y = -plY + winH/2;
	}
}