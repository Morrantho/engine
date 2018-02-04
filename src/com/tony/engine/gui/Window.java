package com.tony.engine.gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

import com.tony.engine.core.Input;
import com.tony.engine.entities.Entities;
import com.tony.engine.entities.Player;

import java.awt.Dimension;

public class Window extends Canvas{
	JFrame frame;
	BufferedImage img;
	BufferStrategy bs;
	Graphics g;
	Graphics2D g2d;
	Camera cam;
	int[] pixels;
	Input input;
	public static Input inputHandle = new Input();
	private int w;
	private int h;

	public Window(int width,int height){
		this.w=w;
		this.h=h;
		this.cam = new Camera(0,0);
		Dimension d = new Dimension(width,height);
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);

		frame = new JFrame("Game");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();

		input  = new Input(frame);
		inputHandle = input;

		img    = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		pixels = ( (DataBufferInt) img.getRaster().getDataBuffer() ).getData();
	}

	public void setW(int w){
		this.w=w;
	}
	public void setH(int h){
		this.h=h;
	}
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}

	public void clear(){
		for(int i=0;i<pixels.length;i++){
			pixels[i] = 0x000000;
		}
	}

	public void tick(double delta){
		Entities.tick(delta);
		Player p = (Player)Entities.findByClass("Player");

		if(p != null){
			cam.tick(delta,p);
		}
	}

	public void render(){
		bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		g2d = (Graphics2D)g;
		// g2d.scale(1.5,1.5);
		g.drawImage(img,0,0,this.getWidth(),this.getHeight(),null);
		clear();

		g2d.translate(cam.getX(),cam.getY());
			if(g != null)
				Entities.render(g);
		g2d.translate(-cam.getX(),-cam.getY());

		g.dispose();
		bs.show();
	}
}