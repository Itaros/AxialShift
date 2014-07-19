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
package ru.axialshift.utils;

import ru.axialshift.resources.MeshData;

/*
 * Pure awesomeness
 */
public class VertexInterlievedIterator {

	private MeshData data;
	
	public VertexInterlievedIterator(MeshData data){
		this.data=data;
	}
	
	
	private int step=-1;
	
	public boolean step(){
		step++;
		//Evaluating length
		int required = (step*MeshData.VERTICES_STRIDE)+MeshData.VERTICES_STRIDE;
		if(required>data.getVertices().length){return false;}//No more data
		fill();
		return true;
	}
	
	private void fill(){
		int startpoint;
		
		//Vertices
		startpoint = (step*MeshData.VERTICES_STRIDE);
		fillStridedArray(vertices,data.getVertices(),startpoint);
		//UVs
		startpoint = (step*MeshData.UVS_STRIDE);
		fillStridedArray(uvs,data.getUVs(),startpoint);		
		//Normals
		startpoint = (step*MeshData.NORMALS_STRIDE);
		fillStridedArray(normals,data.getNormals(),startpoint);			
		//Tangents
		startpoint = (step*MeshData.TANGENTS_STRIDE);
		fillStridedArray(tangents,data.getTangents(),startpoint);	
		//Bitangents
		startpoint = (step*MeshData.BITANGENTS_STRIDE);
		fillStridedArray(bitangents,data.getBiTangents(),startpoint);		
	}
	
	private static void fillStridedArray(float[] target, float[] source, int startpoint){
		//Assuming Target Length == Data Stride
		for(int i=0;i<target.length;i++){
			target[i]=source[i+startpoint];
		}		
	}
	
	private float[] vertices=new float[MeshData.VERTICES_STRIDE];
	private float[] uvs=new float[MeshData.UVS_STRIDE];
	private float[] normals=new float[MeshData.NORMALS_STRIDE];
	private float[] tangents=new float[MeshData.TANGENTS_STRIDE];
	private float[] bitangents=new float[MeshData.BITANGENTS_STRIDE];
	
	public float[] getVertices(){
		return vertices;
	}
	public float[] getUVs(){
		return uvs;
	}
	public float[] getNormals(){
		return normals;
	}
	public float[] getTangents(){
		return tangents;
	}
	public float[] getBiTangents(){
		return bitangents;
	}

	public int getInterlievedSize() {
		return data.getVertices().length+data.getUVs().length+data.getNormals().length+data.getTangents().length+data.getBiTangents().length;
	}
	
}
