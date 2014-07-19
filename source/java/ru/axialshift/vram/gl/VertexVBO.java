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

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import ru.axialshift.resources.MeshData;
import ru.axialshift.utils.VertexInterlievedIterator;
import ru.axialshift.vram.VRAMObject;

/*
 * Interleaved VBO for vertices, uvs, normals, tangents and bitangents.
 * Usually to be used as VAOIncapsulator data source
 */
public class VertexVBO extends VBO {

	public static final int id_pos = 0;
	public static final int id_uvs = 1;
	public static final int id_nor = 2;
	public static final int id_tan = 3;
	public static final int id_btan= 4;
	
	private VertexInterlievedIterator iterator;
	
	public VertexVBO(MeshData data){
		this.data=data;
		iterator = new VertexInterlievedIterator(data);
		generateData();
	}
	
	private void generateData(){
		tovram = BufferUtils.createFloatBuffer(iterator.getInterlievedSize());
		while(iterator.step()){
			FloatBuffer fb = (FloatBuffer)tovram;
			fb.put(iterator.getVertices());
			fb.put(iterator.getUVs());
			fb.put(iterator.getNormals());
			fb.put(iterator.getTangents());
			fb.put(iterator.getBiTangents());
		}
		tovram.flip();
		isReadyForUpload=true;
	}
	
	//TODO: bail out for a bus cycle if not ready
	private boolean isReadyForUpload=false;
	
	@Override
	protected void upload_gl() {
		//Allocation
		glpointer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, glpointer);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, (FloatBuffer)tovram, GL15.GL_STATIC_DRAW);
		
		//Mapping
		int stride=4*(MeshData.VERTICES_STRIDE+MeshData.UVS_STRIDE+MeshData.NORMALS_STRIDE+MeshData.TANGENTS_STRIDE+MeshData.BITANGENTS_STRIDE);
		int offset=0;
		//Vertices
		int bytesPerVertex = MeshData.VERTICES_STRIDE*4;
		GL20.glVertexAttribPointer(id_pos, MeshData.VERTICES_STRIDE, GL11.GL_FLOAT, false, stride, offset);
		//UVs
		int bytesPerUVs = MeshData.UVS_STRIDE*4;
		offset+=bytesPerVertex;
		GL20.glVertexAttribPointer(id_uvs, MeshData.UVS_STRIDE, GL11.GL_FLOAT, false, stride, offset);
		//Normals
		int bytesPerNormals = MeshData.NORMALS_STRIDE*4;
		offset+=bytesPerUVs;
		GL20.glVertexAttribPointer(id_nor, MeshData.NORMALS_STRIDE, GL11.GL_FLOAT, false, stride, offset);	
		//Tangents
		int bytesPerTangents = MeshData.TANGENTS_STRIDE*4;
		offset+=bytesPerNormals;
		GL20.glVertexAttribPointer(id_tan, MeshData.TANGENTS_STRIDE, GL11.GL_FLOAT, false, stride, offset);	
		//BiTangents
		//int bytesPerBiTangents = MeshData.BITANGENTS_STRIDE*4;
		offset+=bytesPerTangents;
		GL20.glVertexAttribPointer(id_btan, MeshData.BITANGENTS_STRIDE, GL11.GL_FLOAT, false, stride, offset);	
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);		
	}

	@Override
	protected void unload_gl() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		super.unload_gl();
	}

}
