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
package ru.axialshift.vram.gl;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import ru.axialshift.resources.MeshData;

/*
 * Usually to be used as VAOIncapsulator data source
 */
public class IndicesVBO extends VBO {
	
	public IndicesVBO(MeshData data){
		this.data=data;
		generateData();
	}	
	
	private void generateData() {
		byte[] indices = data.getIndices();
		tovram = BufferUtils.createByteBuffer(indices.length);
		((ByteBuffer)tovram).put(indices);
		tovram.flip();
	}

	@Override
	protected void upload_gl() {
		glpointer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, glpointer);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (ByteBuffer)tovram, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	@Override
	protected void unload_gl() {
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		super.unload_gl();
	}

}
