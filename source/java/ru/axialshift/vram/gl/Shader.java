package ru.axialshift.vram.gl;

import org.lwjgl.opengl.GL20;

import ru.axialshift.constants.ShaderType;
import ru.axialshift.resources.ShaderData;
import ru.axialshift.vram.VRAMObject;

public class Shader extends VRAMObject {

	private int pointer;
	
	private ShaderData data;
	private ShaderType type;
	
	public Shader(ShaderData data, ShaderType type){
		this.data=data;
		this.type=type;
	}
	
	
	@Override
	protected void upload_gl() {
		pointer = GL20.glCreateShader(type.getType());
		GL20.glShaderSource(pointer, data.getShaderSource());
	}

	@Override
	protected void unload_gl() {
		GL20.glDeleteShader(pointer);
	}


	public int getPointer() {
		return pointer;
	}

}
