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

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import ru.axialshift.resources.MeshData;
import ru.axialshift.vram.VRAMObject;

/*
 * VAO to represent all VRAM data needed to render MeshData
 */
public class VAOIncapsulator extends VRAMObject {

	private int glpointer;
	
	private MeshData source;
	
	private VertexVBO vertexVBO;
	private IndicesVBO indicesVBO;
	
	public VAOIncapsulator(MeshData source){
		this.source=source;
		
		vertexVBO = new VertexVBO(this.source);
		indicesVBO = new IndicesVBO(this.source);
	}
	
	
	@Override
	protected void upload_gl() {
		glpointer = GL30.glGenVertexArrays();
		
		GL30.glBindVertexArray(glpointer);
		vertexVBO.upload();
		GL30.glBindVertexArray(0);
		
		indicesVBO.upload();
	}

	@Override
	protected void unload_gl() {
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(glpointer);
		
		vertexVBO.unload();
		indicesVBO.unload();
	}

	/*
	 * This method is useful if you don't need any fancy stuff.
	 * It binds VAO, sets pointers, binds indices VBO and draws it. Then it unbinds it.
	 */
	public void drawDirectly() {
		GL30.glBindVertexArray(glpointer);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		//GL20.glEnableVertexAttribArray(2);
		//GL20.glEnableVertexAttribArray(3);
		//GL20.glEnableVertexAttribArray(4);
		
		indicesVBO.draw();
		
		GL30.glBindVertexArray(0);
	}

}
