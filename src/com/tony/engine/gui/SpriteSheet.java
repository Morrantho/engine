package com.tony.engine.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import java.util.HashMap;

public class SpriteSheet{
	private HashMap<String,Sprite> sprites;
	private BufferedImage image;
	private String xml;

	public SpriteSheet(String sheet,String xml){
		sprites = new HashMap<String,Sprite>();
		load(sheet);
		loadXML(xml);
	}

	public HashMap<String,Sprite> getSprites(){
		return sprites;
	}

    public Image getImage(){
        return image;
    }

	public void parse(NodeList list){
		for(int i=0;i<list.getLength();i++){
			Node tempNode = list.item(i);

			if(tempNode.getNodeType() == Node.ELEMENT_NODE){				
				if(tempNode.hasAttributes()){
					NamedNodeMap nodeMap = tempNode.getAttributes();

					String name = "";
					int x = 0;
					int y = 0;
					int w = 0;
					int h = 0;

					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(j);
						String key = node.getNodeName();
					
						if(key.equals("name")){
							name = node.getNodeValue();
						}else if(key.equals("x")){
							x = Integer.parseInt(node.getNodeValue());
						}else if(key.equals("y")){
							y = Integer.parseInt(node.getNodeValue());
						}else if(key.equals("width")){
							w = Integer.parseInt(node.getNodeValue());
						}else if(key.equals("height")){
							h = Integer.parseInt(node.getNodeValue());
						}
					}

					if(w != 0 && h != 0){
						Image img  = crop(x,y,w,h);
						Sprite spr = new Sprite(img);
						sprites.put(name,spr);
					}
				}

				if (tempNode.hasChildNodes())
					parse(tempNode.getChildNodes());
			}
		}
	}

	public BufferedImage crop(int x,int y,int w,int h){
		return image.getSubimage(x,y,w,h);
	}

	public void load(String path){
		try{						
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void loadXML(String path){
		try{
			try{
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				
				try{
					Document doc = builder.parse(getClass().getClassLoader().getResourceAsStream(path));
					parse(doc.getChildNodes());
				}catch(SAXException e){
					e.printStackTrace();
				}
			}catch(ParserConfigurationException e){
				e.printStackTrace();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
