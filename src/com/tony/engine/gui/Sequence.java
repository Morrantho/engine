package com.tony.engine.gui;

import java.util.HashMap;
import java.util.ArrayList;

public class Sequence{
	private SpriteSheet sheet;
	private ArrayList<String> keys;
	private Sprite sprite;
	private int ptr=0;
	private String key="";

	private double rate = 100000000.0;
	private double time;
	private double curTime;

	public Sequence(SpriteSheet sheet,ArrayList<String> keys){
		this.sheet=sheet;
		this.keys=keys;
		key=keys.get(ptr);
	}

	public void setRate(double rate){
		this.rate=rate;
	}

	public Sprite getSprite(){return sprite;}

	public void play(){
		curTime = System.nanoTime();

		if(curTime-time >= rate){
			if(ptr>=keys.size()){ptr=0;}
			key=keys.get(ptr); //If null, you provided invalid sprite in keys list.
			sprite=sheet.getSprites().get(key);
			time=System.nanoTime();
			ptr++;
		}
	}
}
