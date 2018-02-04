package com.tony.engine.gui;

import java.awt.Image;
import java.awt.Graphics;
import javax.imageio.ImageIO;

import com.tony.engine.entities.Entity;

import java.io.IOException;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Sprite{
	private Image image;
	private Entity e; // Parent to render on.

    public Sprite(Image image){
		this.image=image;
	}

	public void setEntity(Entity e){
		this.e=e;
	}

    public BufferedImage getImage(){
        return (BufferedImage)image;
    }

	public void render(Graphics g){
		g.drawImage(image,(int)e.getX(),(int)e.getY(),(int)e.getW(),(int)e.getH(),null);
	}

    // render animations here.	
	public void tick(double tick){

	}
}
