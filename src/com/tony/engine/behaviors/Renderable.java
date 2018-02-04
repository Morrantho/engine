package com.tony.engine.behaviors;

import java.awt.Graphics;

public interface Renderable{
	public void tick(double delta);
	public void render(Graphics g);
}
