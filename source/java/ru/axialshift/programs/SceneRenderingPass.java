package ru.axialshift.programs;

import java.util.Iterator;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

import ru.axialshift.display.RenderingManager;
import ru.axialshift.scene.Entity;
import ru.axialshift.scene.SimpleGraphicalEntity;
import ru.axialshift.scene.SimpleSceneManager;
import ru.axialshift.vram.gl.Program;

public class SceneRenderingPass extends BasePass {

	private SimpleSceneManager manager;
	
	public SceneRenderingPass(SimpleSceneManager manager){
		this.manager=manager;
	}
	
	@Override
	public void execute(RenderingManager renderingManager) {
		Iterator<Entity> i = manager.getActiveIterator();
		while(i.hasNext()){
			Entity e = i.next();
			render(renderingManager,e);
			Vector3f posdeb = e.getCoords();
			posdeb.x+=0.01F;
			e.setCoords(posdeb);
		}
	}

	private void render(RenderingManager renderingManager, Entity e) {
		if(e instanceof SimpleGraphicalEntity){
			SimpleGraphicalEntity sge = (SimpleGraphicalEntity)e;
			
			Program current = renderingManager.getCurrentProgram();
			if(current!=null){
				GL20.glUniformMatrix4(current.getMVPPointer(), false, sge.getTransformationFeed());
			}
			
			sge.getVAOIncapsulator().drawDirectly();
		}		
	}

}
