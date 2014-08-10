package ru.axialshift.scene;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class Entity extends TransformationProvider {

	private boolean isTransformationInvalidated=true;
	
	public Vector3f coords;
	public Quaternion rotation;
	
	
	public void setCoords(Vector3f n){
		coords = n;
		isTransformationInvalidated=true;
	}
	public Vector3f getCoords(){
		return coords;
	}
}
