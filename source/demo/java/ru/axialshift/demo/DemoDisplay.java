package ru.axialshift.demo;

import org.lwjgl.opengl.GL20;

import ru.axialshift.context.DebugToolsContext;
import ru.axialshift.context.PrimitivesContext;
import ru.axialshift.display.BaseDisplay;
import ru.axialshift.programs.ClearScreenPass;
import ru.axialshift.programs.IRenderProgram;
import ru.axialshift.utils.Console;
import ru.axialshift.vram.BindingContract;

public class DemoDisplay extends BaseDisplay {

	PrimitivesContext primitives = new PrimitivesContext();
	DebugToolsContext debugTools = new DebugToolsContext();
	
	String[] defaultBindingDefs = {"0->in_Position","1->in_uvs"};
	BindingContract contract = new BindingContract(defaultBindingDefs);
	
	@Override
	protected void start() {
		//Configs
		primitives.setBindingContractToAll(contract);
		debugTools.setBindingContractToAll(contract);
		//Context
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
