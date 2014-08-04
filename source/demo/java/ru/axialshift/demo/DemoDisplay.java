package ru.axialshift.demo;

import ru.axialshift.context.DebugToolsContext;
import ru.axialshift.context.PrimitivesContext;
import ru.axialshift.display.BaseDisplay;
import ru.axialshift.programs.ClearScreenPass;
import ru.axialshift.programs.IRenderProgram;
import ru.axialshift.utils.Console;

public class DemoDisplay extends BaseDisplay {

	PrimitivesContext primitives = new PrimitivesContext();
	DebugToolsContext debugTools = new DebugToolsContext();
	
	@Override
	protected void start() {
		this.manager.enqueueContext(primitives);
		this.manager.enqueueContext(debugTools);
		this.rendering.setRendering(new IRenderProgram[]{new ClearScreenPass(),new QuadRenderingDebugPass(primitives,debugTools)});
		
		super.start();
	}

	public static void main(String[] args) {
		System.out.println("Starting base display!");
		Console.out_separator();
		DemoDisplay base = new DemoDisplay();
		base.start();
		Console.out_separator();
		System.out.println("Have a good day, sir!");
	}	
	
}
