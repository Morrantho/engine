package com.tony.engine.entities;

import java.awt.Graphics;

import com.tony.engine.core.Input;
import com.tony.engine.gui.Sequence;
import com.tony.engine.gui.SpriteSheet;
import com.tony.engine.gui.Window;
import com.tony.engine.util.Resources;

import java.awt.Color;

public class Player extends NPC{
	private boolean wasCrouching = false;
	private boolean crouching = false;

	public Player(SpriteSheet sheet,Sequence sequence,int x,int y){
		super(sheet,sequence,x,y);
		//Attach keyboard input to player entity
		setSpeed(1024.0);
		Window.inputHandle.setEntity(this);
	}

	public void walk(double delta){
		super.walk(delta);
		
		if(Input.isDown("a")){
			setDirection(-1);
		}else if(Input.isDown("d")){
			setDirection(1);

		}else if(Input.isDown("s") && !crouching){
			crouching = true;
		}else if(!Input.isDown("s") && crouching){
			crouching = false;
		}else{
			setDirection(0);
		}
		
		if(Input.isDown("space")){
			jump(delta);
		}
	}

	public void jump(double delta){
		super.jump(delta);
	}

	public void tick(double delta){
		super.tick(delta);
		walk(delta);
	}

	public void animate(){
		if(crouching && getDirection() == -1 || crouching && getDirection() == 1){	
			setSequence(Resources.SEQ_SAMUS_ROLL);
		}else if(crouching){
			setSequence(Resources.SEQ_SAMUS_ROLL_IDLE);
		}else if(getDirection() == 1){
			setSequence(Resources.SEQ_SAMUS_WALK_RIGHT);
		}else if(getDirection() == -1){
			setSequence(Resources.SEQ_SAMUS_WALK_LEFT);
		}else{
			if(getLastDirection()==1){
				setSequence(Resources.SEQ_SAMUS_IDLE_RIGHT);
			}else{
				setSequence(Resources.SEQ_SAMUS_IDLE_LEFT);
			}
		}
	}

	public void render(Graphics g){
		super.render(g);
	}
}