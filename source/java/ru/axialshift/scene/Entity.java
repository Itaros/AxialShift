package ru.axialshift.scene;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class Entity extends TransformationProvider {

	public Vector3f coords;
	public Quaternion rotation;
	
	
	public void setCoords(Vector3f n){
		coords = n;
		markDirty();
	}
	public Vector3f getCoords(){
		return coords;
	}
}
