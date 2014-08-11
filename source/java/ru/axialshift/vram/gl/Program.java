package ru.axialshift.vram.gl;

import org.lwjgl.opengl.GL20;

import ru.axialshift.display.RenderingManager;
import ru.axialshift.vram.BindingContract;
import ru.axialshift.vram.IRequiresBindingContract;
import ru.axialshift.vram.VRAMObject;

public class Program extends VRAMObject implements IRequiresBindingContract {

	private int pointer;
	
	private Shader vertex,fragment;
	
	public Program(Shader vertex, Shader fragment){
		this.vertex=vertex;
		this.fragment=fragment;
	}
	
	private int MVPPointer;
	
	public int getMVPPointer(){
		return MVPPointer;
	}
	
	@Override
	protected void upload_gl() {
		vertex.upload();
		fragment.upload();
		
		pointer = GL20.glCreateProgram();
		GL20.glAttachShader(pointer, vertex.getPointer());
		GL20.glAttachShader(pointer, fragment.getPointer());
		
		if(contract!=null){
			contract.configureGLShader(pointer);
		}
		
		GL20.glLinkProgram(pointer);
		GL20.glValidateProgram(pointer);
		
		//TODO: There should be shader descriptor file to list uniform requirments
		MVPPointer = GL20.glGetUniformLocation(pointer, "MVP");		
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


	public void makeActive(RenderingManager renderingManager) {
		renderingManager.notifyProgramActivation(this);
		GL20.glUseProgram(pointer);
	}


	private BindingContract contract;
	
	@Override
	public IRequiresBindingContract setBindingContract(BindingContract contract) {
		this.contract=contract;
		return this;
	}


	@Override
	public BindingContract getBindingContract() {
		return contract;
	}

}
