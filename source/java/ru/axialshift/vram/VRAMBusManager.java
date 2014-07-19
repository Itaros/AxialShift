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
package ru.axialshift.vram;

import java.util.ArrayList;
import java.util.LinkedList;

public class VRAMBusManager {

	private LinkedList<VRAMObject> queue_in = new LinkedList<VRAMObject>();
	private LinkedList<VRAMObject> queue_out = new LinkedList<VRAMObject>();
	private ArrayList<VRAMObject> loaded = new ArrayList<VRAMObject>();
	
	private boolean isValid(VRAMObject object){
		if(object.isChangingStates){
			System.out.println(object.identifier()+" is already changing states!");
			return false;
		}else{
			return true;
		}
	}
	
	public void loadIntoVRAM(VRAMObject object){
		if(isValid(object)){
			if(object.isAvailable){System.out.println("Possible error: attempt to load one "+object.identifier()+" twice!");}
			object.isChangingStates=true;
			queue_in.add(object);
		}
	}
	
	public void unloadFromVRAM(VRAMObject object){
		if(isValid(object)){
			if(isLoadedHere(object)){
				if(!object.isAvailable){System.out.println("Possible error: attempt to unload one "+object.identifier()+" twice!");}
				object.isChangingStates=true;
				queue_out.add(object);
			}
		}
	}

	private boolean isLoadedHere(VRAMObject object) {
		for(VRAMObject vo:loaded){
			if(vo==object){return true;}
		}
		return false;
	}	
	
	public void process(){
		VRAMObject vo;
		vo = queue_in.poll();
		if(vo!=null){
			System.out.println("VRAM upload: "+vo.identifier());
			vo.upload();
			loaded.add(vo);
			vo.isChangingStates=false;
		}
		vo = queue_out.poll();
		if(vo!=null){
			System.out.println("VRAM unload: "+vo.identifier());
			vo.unload();
			loaded.remove(vo);
			vo.isChangingStates=false;
		}		
	}
	
	
}
