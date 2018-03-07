package com.tony.engine.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Image;
import java.util.Arrays;

import com.tony.engine.behaviors.Movable;
import com.tony.engine.gui.Sequence;
import com.tony.engine.gui.SpriteSheet;

public class NPC extends PhysEnt implements Movable{
	private int direction     = 0;
	private int lastDir       = 1;
	private int range         = 128; // Alert range
	private double speed      = 64.0;
	private double damping    = speed * 8;
	private double cap        = speed / 32.0;
	private double jumpHeight = 12.0;
	private Sequence sequence;

	public NPC(SpriteSheet sheet,Sequence sequence,int x,int y){
		super(sheet,x,y);
		this.sequence=sequence;
		this.color=new Color(255,0,0);
		this.borderColor=new Color(255,0,0);
    }

    public void setSequence(Sequence sequence){this.sequence=sequence;}
    public Sequence getSequence(){return sequence;}

	public void setDirection(int dir){
		if(direction != 0){lastDir=direction;}
		direction=dir;
	}
	public int getDirection(){return direction;}

	public void setLastDirection(int dir){lastDir=dir;}
	public int getLastDirection(){return lastDir;}

	public void setSpeed(double speed){this.speed=speed;}
	public double getSpeed(){return speed;}

	public void setRange(int range) {this.range=range;}
	public int getRange() {return range;}

	public double getJumpHeight(){return jumpHeight;}

	public void walk(double delta){
		if(direction == -1 && getXVel() > -cap){ // Left
			setXVel(getXVel()-speed*delta);
		}else if(direction == 0){ // Idle
			setXVel(getXVel() / (1+damping*delta));
		}else if(direction == 1 && getXVel() < cap){ // Right
			setXVel(getXVel()+speed*delta);
		}
	}

	public void followPlayer(){
		Player p = Entities.findPlayer();

		if(distanceX(p) < range){
			if(getX()+getW() < p.getX()) {
				setDirection(1);
			}else if(getX()> p.getX()) {
				setDirection(-1);
			}
		}else {
			setDirection(0);
		}
	}

	public void jump(double delta){
		if(getYVel() == 0.0){

			double vel = getYVel();
			setYVel(-jumpHeight+vel*delta);
		}
	}

	public void tick(double delta){
		super.tick(delta);
		followPlayer();
		walk(delta);
		// jump(delta);
	}

	public void animate(){

	}

	public void render(Graphics g){
		super.render(g);
		if(sequence!=null)
			sequence.play();

		if(getSprite() != null)
			getSprite().setEntity(null);
		if(sequence!=null)
			setSprite(sequence.getSprite());
		if(getSprite() != null)
			getSprite().setEntity(this);

		animate();
	}
}
