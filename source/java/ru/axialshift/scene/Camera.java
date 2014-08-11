package ru.axialshift.scene;

import org.lwjgl.util.vector.Matrix4f;

public class Camera extends Entity {

	private Matrix4f projection = new Matrix4f();
	
	private Matrix4f PV = new Matrix4f();
	
	public void setupPerspectiveProjection(float aspectRatio, float fieldOfView, float nearPlane, float farPlane){
		projection = new Matrix4f();
		 
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
	}
	
	public Matrix4f getV(){
		return this.getM();
	}

	@Override
	protected void fillMatrix(Matrix4f model) {
		super.fillMatrix(model);
		
		PV = Matrix4f.mul(projection, getV(), PV);
		
	}
	
	
	
}
