package ru.axialshift.demo;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import ru.axialshift.context.DebugToolsContext;
import ru.axialshift.context.PrimitivesContext;
import ru.axialshift.display.BaseDisplay;
import ru.axialshift.programs.ClearScreenPass;
import ru.axialshift.programs.IRenderProgram;
import ru.axialshift.programs.SceneRenderingPass;
import ru.axialshift.programs.SetProgramPass;
import ru.axialshift.scene.Camera;
import ru.axialshift.scene.SimpleGraphicalEntity;
import ru.axialshift.scene.SimpleSceneManager;
import ru.axialshift.utils.Console;
import ru.axialshift.vram.BindingContract;

public class DemoDisplay extends BaseDisplay {

	SimpleSceneManager uvsetScene = new SimpleSceneManager();
	
	PrimitivesContext primitives = new PrimitivesContext();
	DebugToolsContext debugTools = new DebugToolsContext();
	
	String[] defaultBindingDefs = {"0->in_Position","1->in_uvs","2->in_normals"};
	BindingContract contract = new BindingContract(defaultBindingDefs);
	
	SimpleGraphicalEntity quadObject = (SimpleGraphicalEntity) new SimpleGraphicalEntity(primitives.getSphere()).setCoords(new Vector3f(0F,0F,-5F));
	
	@Override
	protected void start() {
		//Configs
		primitives.setBindingContractToAll(contract);
		debugTools.setBindingContractToAll(contract);
		//Presets
		uvsetScene.addToActive(quadObject);
		Camera cam = new Camera();
		cam.setupPerspectiveProjection(800F/600F, 60F, 0.1F, 100F);
		uvsetScene.setCamera(cam);
		//Context
		this.manager.enqueueContext(primitives);
		this.manager.enqueueContext(debugTools);
		
		IRenderProgram[] renderQueue = new IRenderProgram[]
				{
					new ApplyRotationPass(quadObject,.01F,0F,0F),
					new ClearScreenPass(),
					new SetProgramPass(debugTools.getNormalsProgram()),
					new SceneRenderingPass(uvsetScene)
				};
		
		this.rendering.setRendering(renderQueue);
		
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
