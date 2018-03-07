package com.tony.engine.core;

import java.lang.InterruptedException;
import java.lang.Thread;

import com.tony.engine.behaviors.Togglable;
import com.tony.engine.entities.Entity;
import com.tony.engine.entities.NPC;
import com.tony.engine.entities.Player;
import com.tony.engine.gui.Window;
import com.tony.engine.util.Resources;
import com.tony.engine.util.Util;

import java.awt.Color;

public class Game implements Runnable,Togglable{
	long serialVersionUID = 1L;
	boolean running;
	int width;
	int height;
	Thread thread;
	Window window;
	double current=Util.time();
	double previous=Util.time();
	double delta;
	double diff=Util.time();
	final double TICKRATE = 15151515.152; //66tick in nanoseconds

	public static Window windowHandle;

	public Game(int width,int height){
		window = new Window(width,height);
		windowHandle = window;
		windowHandle.setW(width);
		windowHandle.setH(height);

		start();

        Player p = new Player(
        	Resources.SHEET_SAMUS,
        	Resources.SEQ_SAMUS_IDLE,
        	128,
        	0
        );
        // p.debug=false;
        // p.setGravity(0);

        NPC n = new NPC(
        	Resources.SHEET_SAMUS,
        	Resources.SEQ_SAMUS_IDLE,
        	512,
        	0
        );
        
        // Left wall
        for(int i=0;i<32*32;i+=32) {
        	new Entity(0,-i+512,32,32,new Color(0,255,0));
        }
        
        for(int i=0;i<32*32;i+=32){
        	new Entity(i,512,32,32,new Color(0,255,0));
        }
        
        for(int i=0;i<32*32;i+=32) {
        	new Entity(1024,-i+512,32,32,new Color(0,255,0));
        }
	}

	public synchronized void start(){
		if(running){return;}
		running = true;
		thread = new Thread(this,"Game");
		thread.start();
	}

	public synchronized void stop(){
		if(!running){return;}
		running = false;

		try{thread.join();}
		catch(InterruptedException e){e.printStackTrace();}
	}

	public void tick(double delta){
		delta /= 1000000000.0D;
		window.tick(delta);
	}

	public void render(){
		window.render();
	}

	public void run(){
		while(running){
			current=Util.time();
			delta=current-previous;

			if(Util.time()-diff >= TICKRATE){
				tick(delta);			
				diff=Util.time();
			}

			previous=Util.time();
			render();
		}
	}

	public static void main(String[] args){
		Game game = new Game(1024,1024);
	}
}
