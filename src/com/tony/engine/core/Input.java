package com.tony.engine.core;

import java.awt.event.KeyListener;
import java.util.Map;
import java.util.HashMap;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import com.tony.engine.entities.Entity;

public class Input implements KeyListener{
	private static HashMap<String,Boolean> pressed = new HashMap<String,Boolean>();
	private Entity entity;

    public Input(JFrame frame){
        frame.addKeyListener(this);
    }

    public Input(){

    }

    public void setEntity(Entity e){
        entity=e;
    }

    public Entity getEntity(){
        return entity;
    }

    public void keyTyped(KeyEvent event){
    }

    public void keyPressed(KeyEvent event){
        int code = event.getKeyCode();
        String key = event.getKeyText(code).toLowerCase();

        if(pressed.get(key) == null)
    		pressed.put(key,true);
    }

    public void keyReleased(KeyEvent event){
        int code = event.getKeyCode();
        String key = event.getKeyText(code).toLowerCase();

    	if(pressed.get(key) != null)
    	    pressed.remove(key);
    }
    
    public static boolean isDown(String key){
        key = key.toLowerCase();

        for(Map.Entry<String,Boolean>entry: pressed.entrySet()){
            if(entry.getKey().equals(key))
                return true; 
        }
        return false;
    }
}
