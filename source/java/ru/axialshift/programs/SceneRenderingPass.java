package ru.axialshift.programs;

import java.util.Iterator;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

import ru.axialshift.display.RenderingManager;
import ru.axialshift.scene.Camera;
import ru.axialshift.scene.Entity;
import ru.axialshift.scene.SimpleGraphicalEntity;
import ru.axialshift.scene.SimpleSceneManager;
import ru.axialshift.utils.QuaternionHelper;
import ru.axialshift.vram.gl.Program;

public class SceneRenderingPass extends BasePass {

	private SimpleSceneManager manager;
	
	public SceneRenderingPass(SimpleSceneManager manager){
		this.manager=manager;
	}
	
	private float deb=0f;
	
	@Override
	public void execute(RenderingManager renderingManager) {
		
		Camera cam = manager.getCamera();
		Vector3f posdeb2 = cam.getCoords();
		//posdeb2.y+=0.01F;
		cam.setCoords(posdeb2);
		
		Iterator<Entity> i = manager.getActiveIterator();
		while(i.hasNext()){
			Entity e = i.next();
			render(renderingManager,e,cam);
			Quaternion q = e.getRotation();
			Vector3f posdeb = e.getCoords();
			QuaternionHelper.rotate(q, deb, 0.5F ,0 );
			deb+=0.05F;
			posdeb.z-=0.01F;
			e.setCoords(posdeb);
			e.setRotation(q);
		}
	}

	private void render(RenderingManager renderingManager, Entity e, Camera cam) {
		if(e instanceof SimpleGraphicalEntity){
			SimpleGraphicalEntity sge = (SimpleGraphicalEntity)e;
			
			Program current = renderingManager.getCurrentProgram();
			if(current!=null){
				GL20.glUniformMatrix4(current.getMPointer(), false, sge.getTransformationFeed());
				GL20.glUniformMatrix4(current.getVPPointer(), false, cam.getTransformationFeed());
			}
			
			sge.getVAOIncapsulator().drawDirectly();
		}		
	}

}
