package ru.axialshift.scene;

import org.lwjgl.util.vector.Matrix4f;

import ru.axialshift.utils.Conversions;

public class Camera extends Entity {

	private Matrix4f projection = new Matrix4f();
	
	//private Matrix4f PV = new Matrix4f();
	
	private float near,far;
	
	public float getNear() {
		return near;
	}

	public float getFar() {
		return far;
	}

	public Camera setupPerspectiveProjection(float aspectRatio, float fieldOfView, float nearPlane, float farPlane){
		projection = new Matrix4f();
		 
		this.near=nearPlane;
		this.far=farPlane;
		
		float y_scale = (float) (1D/Math.tan((Math.PI/180D)*(fieldOfView / 2f)));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = farPlane - nearPlane;
		 
		projection.m00 = x_scale;
		projection.m11 = y_scale;
		projection.m22 = -((farPlane + nearPlane) / frustum_length);
		projection.m23 = -1;
		projection.m32 = -((2 * nearPlane * farPlane) / frustum_length);
		projection.m33 = 0;		
		
		markDirty();
		
		return this;
	}
	
	//public Matrix4f getV(){
	//	return this.getM();
	//}

	@Override
	protected void fillMatrix(Matrix4f model) {
		//super.fillMatrix(model);
		
		//Matrix4f v = new Matrix4f();
		
		model.setIdentity();
		Matrix4f.scale(scale, model, model);	
		Matrix4f.translate(coords, model, model);
		Matrix4f.mul(model, Conversions.toMatrix4f(rotation), model);
		
		model = Matrix4f.mul(model, projection, model);
		
		
		
		//model = PV;
		
	}
	
	
	
}
