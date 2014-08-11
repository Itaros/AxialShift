package ru.axialshift.scene;

import ru.axialshift.vram.gl.VAOIncapsulator;

/*
 * Has all of the Entity facilities, but bears the reference to VAOIncapsulator.
 * Program grouping is a responsibility of passes with their own scene managers.
 * Btw, those can be linked into one composite scene manager for efficient program context grouping.
 */
public class SimpleGraphicalEntity extends Entity {

	private VAOIncapsulator incapsulator;
	
	public SimpleGraphicalEntity(VAOIncapsulator incapsulator){
		this.incapsulator=incapsulator;
	}
	
	public VAOIncapsulator getVAOIncapsulator(){
		return this.incapsulator;
	}
	
}
