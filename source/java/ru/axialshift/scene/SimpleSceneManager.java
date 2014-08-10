package ru.axialshift.scene;

import java.util.ArrayList;


public class SimpleSceneManager {

	public SimpleSceneManager(){
		
	}
	
	private ArrayList<Entity> active = new ArrayList<Entity>();
	private ArrayList<Entity> storage = new ArrayList<Entity>();
	
	private Camera camera;
	
	
	public void addToActive(Entity e){
		active.add(e);
	}
	public void addToStorage(Entity e){
		storage.add(e);
	}
	public void removeFromActive(Entity e){
		active.remove(e);
	}
	public void removeFromStorage(Entity e){
		storage.remove(e);
	}
	public void pushFromActive(Entity e){
		removeFromActive(e);
		addToStorage(e);
	}
	public void pushFromStorage(Entity e){
		removeFromStorage(e);
		addToActive(e);
	}	
	
	public void setCamera(Camera c){
		camera = c;
	}
	
}
