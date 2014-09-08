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
package ru.axialshift.context;

import ru.axialshift.resources.OBJMesh;
import ru.axialshift.resources.QuadMesh;
import ru.axialshift.vram.BindingContract;
import ru.axialshift.vram.VRAMObject;
import ru.axialshift.vram.gl.VAOIncapsulator;

public class PrimitivesContext extends Context {

	private VAOIncapsulator quad = new VAOIncapsulator(new QuadMesh());
	private VAOIncapsulator sphere = new VAOIncapsulator(new OBJMesh("bin/meshes/sphere.obj"));
	
	public PrimitivesContext() {
		super();
		this.setVRAMObjects(defineStaticData());
		this.makeFinal();
	}

	private VRAMObject[] defineStaticData() {
		return new VRAMObject[]{quad,sphere};
	}

	public VAOIncapsulator getQuad(){
		return quad;
	}	
	public VAOIncapsulator getSphere(){
		return sphere;
	}

	
}
