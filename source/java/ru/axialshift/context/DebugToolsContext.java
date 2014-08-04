package ru.axialshift.context;

import ru.axialshift.constants.ShaderType;
import ru.axialshift.resources.ShaderData;
import ru.axialshift.vram.gl.Program;
import ru.axialshift.vram.gl.Shader;

public class DebugToolsContext extends Context {

	private Program prog_renderUVs = new Program(new Shader(new ShaderData("bin/shaders/debug/uvs.vsh"),ShaderType.VERTEX),new Shader(new ShaderData("bin/shaders/debug/uvs.fsh"),ShaderType.FRAGMENT));
	
	public DebugToolsContext(){
		super();
		this.setVRAMObjects(prog_renderUVs);
		this.makeFinal();
	}

	public Program getUVsProgram() {
		return prog_renderUVs;
	}
	
}
