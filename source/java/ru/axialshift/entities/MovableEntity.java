/*
 * Copyright 2014 Semion Nadezhdin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.axialshift.entities;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

import ru.axialshift.utils.Conversions;

public class MovableEntity {

	private Matrix4f model=new Matrix4f();
	private FloatBuffer modelBuffer = BufferUtils.createFloatBuffer(4*4);
	
	private Vector3f position;
	private Quaternion rotation;
	private Vector3f scale;
	
	private boolean isTransformationDirty=true;
	
	private void composeMatrix(){
		model.setIdentity();
		Matrix4f.translate(position, model, model);
		Matrix4f.mul(model, Conversions.toMatrix4f(rotation), model);
		Matrix4f.scale(scale, model, model);
	}
	
	private void composeFloatBuffer(){
		model.store(modelBuffer);
		modelBuffer.flip();
	}
	
	private void reevaluate(){
		composeMatrix();
		composeFloatBuffer();
	}
	
	public FloatBuffer getModelMatrixData(){
		if(isTransformationDirty){
			isTransformationDirty=false;
			reevaluate();
		}
		return modelBuffer;
	}
	
}
