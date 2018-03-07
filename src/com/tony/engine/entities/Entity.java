package com.tony.engine.entities;

import java.awt.Graphics;

import com.tony.engine.behaviors.Renderable;

import java.awt.Color;

public class Entity implements Renderable{
	double x;
	double y;
	double w;
	double h;
	int id;
	Color color;
	Color borderColor;
	boolean debug;
	public double oldX;
	public double oldY;

	public Entity(double x,double y,double w,double h,Color color){
		this.id=Entities.active.size();
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;

		this.color=color;
		this.borderColor=color;
		this.debug=true;
		Entities.active.add(this);
	}

	public void setX(double x){this.x=x;}
	public void setY(double y){this.y=y;}
	public void setW(double w){this.w=w;}
	public void setH(double h){this.h=h;}
	public void setColor(Color color){this.color=color;}
	public void setBorderColor(Color color){this.borderColor=color;}
	public void setDebug(boolean debug){this.debug=debug;}

	public double getId(){return id;}
	public double getX(){return x;}
	public double getY(){return y;}
	public double getW(){return w;}
	public double getH(){return h;}
	public double getTotalW(){return x+w;}
	public double getTotalH(){return y+h;}

	public Color getColor(){return color;}
	public Color getBorderColor(){return borderColor;}
	public boolean isDebug(){return debug;}
	
	public double distanceX(Entity e) {
		return Math.pow(Math.pow(e.x-x,2.0),0.5);
	}
	public double distanceY(Entity e) {
		return Math.pow(Math.pow(e.y-y,2.0),0.5);
	}
	public double distance(Entity e) {
		return Math.pow(Math.pow(e.y-y,2.0)+Math.pow(e.x-e.y,2.0),0.5);
	}

	
	public void tick(double delta){
		oldX = getX();
		oldY = getY();
	}

	public void render(Graphics g){
	    if(debug){
	    	g.setColor(borderColor);
	    	g.drawRect((int)x,(int)y,(int)w,(int)h);
	    }
	}
}
