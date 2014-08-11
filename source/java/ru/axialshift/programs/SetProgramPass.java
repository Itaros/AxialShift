package ru.axialshift.programs;

import ru.axialshift.display.RenderingManager;
import ru.axialshift.vram.gl.Program;

/*
 * Used in grouped rendering to declare program to use.
 * Very efficient \o/
 */
public class SetProgramPass extends BasePass {

	private Program program;
	
	public SetProgramPass(Program program){
		this.program=program;
	}
	
	@Override
	public void execute(RenderingManager renderingManager) {
		program.makeActive(renderingManager);
	}

}
