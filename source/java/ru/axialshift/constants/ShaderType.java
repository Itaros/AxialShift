package ru.axialshift.constants;

import org.lwjgl.opengl.GL20;

public enum ShaderType {

	VERTEX(GL20.GL_VERTEX_SHADER),
	FRAGMENT(GL20.GL_FRAGMENT_SHADER);
	
	ShaderType(int type){
		this.type=type;
	}
	public int getType(){
		return type;
	}
	
	private int type;
	
}
