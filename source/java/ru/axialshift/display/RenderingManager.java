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
package ru.axialshift.display;

import ru.axialshift.programs.IRenderProgram;

public class RenderingManager {

	IRenderProgram[] passes;
	
	
	public void setRendering(IRenderProgram[] passes){
		this.passes=passes;
	}
	
	public void draw(){
		if(passes!=null){
			for(int i = 0; i< passes.length;i++){
				if(passes[i].isActive()){
					passes[i].execute();
				}
			}
		}
	}
	
}
