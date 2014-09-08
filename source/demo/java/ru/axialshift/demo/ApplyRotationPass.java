package ru.axialshift.demo;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

import ru.axialshift.display.RenderingManager;
import ru.axialshift.programs.BasePass;
import ru.axialshift.scene.SimpleGraphicalEntity;
import ru.axialshift.utils.QuaternionHelper;

public class ApplyRotationPass extends BasePass {

	private SimpleGraphicalEntity object;
	
	float pitch,yaw,roll;
	
	public ApplyRotationPass(SimpleGraphicalEntity object, float pitch, float yaw, float roll){
		this.object=object;
		this.pitch=pitch;
		this.yaw=yaw;
		this.roll=roll;
	}
	
	@Override
	public void execute(RenderingManager renderingManager) {
		Quaternion rot = object.getRotation();
		Quaternion n = new Quaternion();
		QuaternionHelper.rotate(n, pitch, yaw, roll);
		rot = Quaternion.mul(rot, n, rot);
		object.setRotation(rot);
	}
	

}
