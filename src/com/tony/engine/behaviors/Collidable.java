package com.tony.engine.behaviors;

import com.tony.engine.entities.Entity;

public interface Collidable{
	public void collide(double delta,Entity e);
}
