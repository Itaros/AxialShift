package ru.axialshift.vram.gl;

import org.lwjgl.opengl.GL20;

import ru.axialshift.vram.VRAMObject;

public class Program extends VRAMObject {

	private int pointer;
	
	private Shader vertex,fragment;
	
	public Program(Shader vertex, Shader fragment){
		this.vertex=vertex;
		this.fragment=fragment;
	}
	
	
	@Override
	protected void upload_gl() {
		vertex.upload();
		fragment.upload();
		
		pointer = GL20.glCreateProgram();
		GL20.glAttachShader(pointer, vertex.getPointer());
		GL20.glAttachShader(pointer, fragment.getPointer());
		
		//TODO: add pointers contract there
		GL20.glBindAttribLocation(pointer, 0, "in_Position");
		GL20.glBindAttribLocation(pointer, 1, "in_uvs");
		
		GL20.glLinkProgram(pointer);
		GL20.glValidateProgram(pointer);
	}

	@Override
	protected void unload_gl() {
		GL20.glUseProgram(0);

		GL20.glDetachShader(pointer, vertex.getPointer());
		GL20.glDetachShader(pointer, fragment.getPointer());
		
		vertex.unload();
		fragment.unload();
		
		GL20.glDeleteProgram(pointer);
		
	}


	public void makeActive() {
		GL20.glUseProgram(pointer);
	}

}
