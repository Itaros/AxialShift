package ru.axialshift.demo;

import ru.axialshift.context.PrimitivesContext;
import ru.axialshift.programs.BasePass;

public class QuadRenderingDebugPass extends BasePass {

	private PrimitivesContext primitives;
	
	public QuadRenderingDebugPass(PrimitivesContext context){
		this.primitives=context;
	}
	
	@Override
	public void execute() {
		primitives.getQuad().drawDirectly();
	}

}
