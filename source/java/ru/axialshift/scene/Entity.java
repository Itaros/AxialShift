package ru.axialshift.scene;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

import ru.axialshift.utils.Conversions;

public class Entity extends TransformationProvider {

	protected Vector3f coords=new Vector3f(0F,0F,0F);
	protected Quaternion rotation=new Quaternion();
	protected Vector3f scale=new Vector3f(1F,1F,1F);
	
	public void setCoords(Vector3f n){
		coords = n;
		markDirty();
	}
	public Vector3f getCoords(){
		return coords;
	}
	
	public void setRotation(Quaternion q){
		rotation=q;
		markDirty();
	}
	public Quaternion getRotation(){
		return rotation;
	}
	
	public void setScale(Vector3f n){
		scale=n;
		markDirty();
	}
	public Vector3f getScale(){
		return scale;
	}
	
	@Override
	protected void fillMatrix(Matrix4f model) {
		super.fillMatrix(model);
		
		model.setIdentity();
		Matrix4f.scale(scale, model, model);	
		Matrix4f.translate(coords, model, model);
		Matrix4f.mul(model, Conversions.toMatrix4f(rotation), model);
	
	}
}
