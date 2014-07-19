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
package ru.axialshift.resources;

public class MeshData {

	protected MeshData(){
		
	}

	public MeshData(float[] vertices, byte[] indices){
		this.vertices=vertices;
		this.indices=indices;
	}
	
	public MeshData setUVs(float[] uvs){
		this.uvs=uvs;
		return this;
	}
	
	protected float[] vertices;
	protected float[] uvs;
	protected float[] normals;
	protected float[] tangents;
	protected float[] bitangents;
	
	protected byte[] indices;
	
	private boolean isReady=false;
	
	public MeshData calculate(){
		normals = new float[vertices.length];
		tangents = new float[vertices.length];
		bitangents = new float[vertices.length];
		//TODO: calc stuff like normals, tangents and bitangents
		isReady=true;
		return this;
	}
	
	public float[] getVertices(){
		check();
		return vertices;
	}
	public float[] getUVs(){
		check();
		return uvs;
	}
	public float[] getNormals(){
		check();
		return normals;
	}
	public float[] getTangents(){
		check();
		return tangents;
	}
	public float[] getBiTangents(){
		check();
		return bitangents;
	}
	public byte[] getIndices(){
		check();
		return indices;
	}
	
	private void check(){
		if(!isReady){
			throw new IllegalStateException("Mesh data is not ready!");
		}
	}
	
	
	//CONSTANTS
	private static final int VECTOR3_STRIDE = 3;
	private static final int VECTOR2_STRIDE = 2;
	
	public static final int VERTICES_STRIDE=VECTOR3_STRIDE;
	public static final int UVS_STRIDE=VECTOR2_STRIDE;
	public static final int NORMALS_STRIDE=VECTOR3_STRIDE;
	public static final int TANGENTS_STRIDE=VECTOR3_STRIDE;
	public static final int BITANGENTS_STRIDE=VECTOR3_STRIDE;
	
}
