package com.tony.engine.entities;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Entities{
	public static ArrayList<Entity> active = new ArrayList<Entity>();

	public static void tick(double delta){
		for(int i=0; i < active.size(); i++){
			active.get(i).tick(delta);
		}
	}

	public static void render(Graphics g){
		for(int i=0; i < active.size(); i++){
			active.get(i).render(g);
		}		
	}

	public static Entity findByClass(String cls){
		for(int i=0;i<active.size();i++){
			if(active.get(i).getClass().getSimpleName().equals(cls)){
				return active.get(i);
			}
		}
		return null;
	}
	
	public static Player findPlayer() {
		return (Player) findByClass("Player");
	}
}