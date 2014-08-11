package ru.axialshift.scene;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;

public class TransformationProvider {
	private boolean isTransformationInvalidated=true;
	
	private Matrix4f model=new Matrix4f();
	private FloatBuffer modelBuffer = BufferUtils.createFloatBuffer(4*4);
	
	
	public FloatBuffer getTransformationFeed(){
		if(isTransformationInvalidated){
			fillMatrix(model);
			fillBuffer();
		}
		return modelBuffer;
	}


	protected void fillMatrix(Matrix4f model) {
		//Does nothing here, but extension classes fill matrix there
	}


	private void fillBuffer() {
		model.store(modelBuffer);
		modelBuffer.flip();
	}
	
	protected void markDirty(){
		isTransformationInvalidated=true;
	}
	
	public Matrix4f getM(){
		return model;
	}
	
}
