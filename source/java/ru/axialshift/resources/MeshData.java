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

import org.lwjgl.util.vector.Vector3f;

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
		prepNormals();
		
		isReady=true;
		return this;
	}
	
	private void prepNormals(){
		//Normal calc is performed per triangle
		for(int i = 0; i < indices.length ; i +=3){
			int i1=indices[i];
			int i2=indices[i+1];
			int i3=indices[i+2];
			
			Vector3f c1 = new Vector3f(vertices[(3*i1)+0],vertices[(3*i1)+1],vertices[(3*i1)+2]);
			Vector3f c2 = new Vector3f(vertices[(3*i2)+0],vertices[(3*i2)+1],vertices[(3*i2)+2]);
			Vector3f c3 = new Vector3f(vertices[(3*i3)+0],vertices[(3*i3)+1],vertices[(3*i3)+2]);
			
			Vector3f S = new Vector3f();
			Vector3f T = new Vector3f();
			S=Vector3f.sub(c2, c1, S);
			T=Vector3f.sub(c3, c1, T);
			
			Vector3f N = new Vector3f(
					(S.y*T.z)-(S.z*T.y),
					(S.z*T.x)-(S.x*T.z),
					(S.x*T.y)-(S.y*T.x)
					);
			
			normals[(3*i1)+0]=N.x;
			normals[(3*i1)+1]=N.y;	
			normals[(3*i1)+2]=N.z;	
			
			normals[(3*i2)+0]=N.x;
			normals[(3*i2)+1]=N.y;
			normals[(3*i2)+2]=N.z;	
			
			normals[(3*i3)+0]=N.x;
			normals[(3*i3)+1]=N.y;	
			normals[(3*i3)+2]=N.z;	
		}
		
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
