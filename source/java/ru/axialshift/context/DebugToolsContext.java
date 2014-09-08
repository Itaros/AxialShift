package ru.axialshift.context;

import ru.axialshift.constants.ShaderType;
import ru.axialshift.resources.ShaderData;
import ru.axialshift.vram.gl.Program;
import ru.axialshift.vram.gl.Shader;

public class DebugToolsContext extends Context {

	private Program prog_renderUVs = new Program(new Shader(new ShaderData("bin/shaders/debug/uvs.vsh"),ShaderType.VERTEX),new Shader(new ShaderData("bin/shaders/debug/uvs.fsh"),ShaderType.FRAGMENT));
	private Program prog_renderNormals = new Program(new Shader(new ShaderData("bin/shaders/debug/normals.vsh"),ShaderType.VERTEX),new Shader(new ShaderData("bin/shaders/debug/normals.fsh"),ShaderType.FRAGMENT));
	
	public DebugToolsContext(){
		super();
		this.setVRAMObjects(prog_renderUVs,prog_renderNormals);
		this.makeFinal();
	}

	public Program getUVsProgram() {
		return prog_renderUVs;
	}
	
	public Program getNormalsProgram() {
		return prog_renderNormals;
	}	
	
}
