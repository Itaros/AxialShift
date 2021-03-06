package ru.axialshift.demo;

import ru.axialshift.context.DebugToolsContext;
import ru.axialshift.context.PrimitivesContext;
import ru.axialshift.display.RenderingManager;
import ru.axialshift.programs.BasePass;

public class SphereRenderingDebugPass extends BasePass {

	private PrimitivesContext primitives;
	private DebugToolsContext debugTools;
	
	public SphereRenderingDebugPass(PrimitivesContext context, DebugToolsContext debugTools){
		this.primitives=context;
		this.debugTools=debugTools;
	}
	
	@Override
	public void execute(RenderingManager renderingManager) {
		debugTools.getUVsProgram().makeActive(renderingManager);
		primitives.getSphere().drawDirectly();
	}

}
