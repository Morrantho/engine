package com.tony.engine.entities;

import java.awt.Color;
import java.awt.Graphics;

import java.io.IOException;

import com.tony.engine.behaviors.Collidable;
import com.tony.engine.gui.Sprite;
import com.tony.engine.gui.SpriteSheet;

public class PhysEnt extends Entity implements Collidable{
	private SpriteSheet sheet;
	private Sprite sprite;     // Active Sprite.
	private double gravity = 128.0; // 128.0
	private double xV = 0;
	private double yV = 0;
	private boolean grounded;

	public Rectangle top;
	public Rectangle bottom;
	public Rectangle left;
	public Rectangle right;

	//Keep the option of being able to draw these without a sprite.
	public PhysEnt(double x,double y,double w,double h,Color color){
		super(x,y,w,h,color);
		top=new Rectangle();
		bottom=new Rectangle();
		left=new Rectangle();
		right=new Rectangle();
	}

	//Render PhysEnt's as sprites.
	public PhysEnt(SpriteSheet sheet,String startSprite,double x,double y){
		super(x,y,32,32,new Color(255,255,255)); // w and h get overwritten by sprite size anyways.
		if(sheet.getSprites().get(startSprite) == null){System.out.println(startSprite+": doesnt exist in this spritesheet.");}

		this.sheet=sheet;
		this.sprite=sheet.getSprites().get(startSprite); // Default active sprite to sheet's first sprite.		
		this.sprite.setEntity(this);

        // //Scale size to sprite image size.
        // this.setW(sprite.getImage().getWidth());
        // this.setH(sprite.getImage().getHeight());

		top=new Rectangle();
		bottom=new Rectangle();
		left=new Rectangle();
		right=new Rectangle();
	}

	public PhysEnt(SpriteSheet sheet,double x,double y){// Used for derivatives that dont need a starting sprite, using sequences for animating.
		super(x,y,32,32,new Color(255,255,255));
		this.sheet=sheet;
		top=new Rectangle();
		bottom=new Rectangle();
		left=new Rectangle();
		right=new Rectangle();
	}

	public void setSprite(Sprite sprite){this.sprite=sprite;}
	public Sprite getSprite(){return sprite;}

	public void setSheet(SpriteSheet sheet){this.sheet=sheet;}
	public SpriteSheet getSheet(){return sheet;}

	public void setGravity(double g){gravity = g;}
	public double getGravity(){return gravity;}

	public void setXVel(double v){xV = v;}
	public double getXVel(){return xV;}

	public void setYVel(double v){yV = v;}
	public double getYVel(){return yV;}

	public void setGrounded(boolean grounded){this.grounded=grounded;}
	public boolean isGrounded(){return grounded;}

	public void tick(double delta){
		oldX = getX(); // Keep previous locations if a collision happens.
		oldY = getY();

		if(sprite != null){
	        setW(sprite.getImage().getWidth());
	        setH(sprite.getImage().getHeight());
		}
		
		setX( getX()+xV );
		setY( getY()+yV );

		yV += gravity*delta;

		left.x = (int)getX();
		left.y = (int)getY()+8;
		left.w = 2;
		left.h = (int)getH()-16;

		top.x = (int)getX()+4;
		top.y = (int)getY();
		top.w = (int)getW()-8;
		top.h = 4;

		right.x=(int)getX()+(int)getW()-2;
		right.y=(int)getY()+8;
		right.w=2;
		right.h=(int)getH()-16;

		bottom.x=(int)getX()+4;
		bottom.y=(int)getTotalH()-2;
		bottom.w=(int)getW()-8;
		bottom.h=4;

		collide(delta);
	}

	public void render(Graphics g){
		super.render(g);

		if(sprite != null){
			sprite.render(g);
		}

		if(isDebug()){
	    	g.setColor(new Color(0,0,255));

	    	if(left!=null && right!=null && top!=null && bottom!=null){
		    	g.drawRect(left.x,left.y,left.w,left.h);
		    	g.drawRect(right.x,right.y,right.w,right.h);
		    	g.drawRect(top.x,top.y,top.w,top.h);
		    	g.drawRect(bottom.x,bottom.y,bottom.w,bottom.h);
	    	}
		}
	}

	public boolean intersects(Rectangle one,Entity e){
		return one.x < e.oldX+e.getW() &&
		e.oldX < one.x+one.w &&
		one.y < e.oldY+e.getH() &&
		e.oldY < one.y+one.h;
	}

	public void collide(double t,Entity e){

	}

	private void collide(double delta){
		for(int i=0; i < Entities.active.size(); i++){
			Entity e = Entities.active.get(i);
			if(e == this) continue;

			// Ground Collision
			if(intersects(bottom,e)){
				setYVel(0.0);
				setY(e.getY()-getH());
			}
			
			//Top collision
			if(intersects(top,e)){
				yV /= 2;
				setY(oldY);
			}

			// Left Or Right Collision
			if(intersects(left,e)){
				setX(oldX);
			}

			if(intersects(right,e)){
				setX( oldX );
			}
		}
	}

}