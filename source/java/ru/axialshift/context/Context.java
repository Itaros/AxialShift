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

import ru.axialshift.vram.BindingContract;
import ru.axialshift.vram.IRequiresBindingContract;
import ru.axialshift.vram.VRAMBusManager;
import ru.axialshift.vram.VRAMObject;

/*
 * Context represents set of videodata to load/unload in batch
 * Context can be static(primitives library) or dynamic(scene context/composition context)
 * 
 * Render Passes can ask for specialized contexts(gui) 
 * or contexts can be loaded/unloaded on their own without disturbing program flow
 */
public class Context {

	private VRAMObject[] loadables;
	
	public Context(VRAMObject[] loadables){
		this.loadables=loadables;
	}
	
	protected Context(){}
	
	public Context setVRAMObjects(VRAMObject...objects){
		loadables=objects;
		bindContract();
		return this;
	}
	
	public Context makeFinal(){
		this.isFilled=true;
		return this;
	}
	
	/*
	 * If this is false context manager will refuse to process is
	 */
	private boolean isFilled=false;
	
	/*
	 * If true it is loaded into VRAM and its resources can be used for actual rendering in passes
	 */
	private boolean isLoaded=false;
	
	/*
	 * If true it is changing states
	 */
	private boolean isChangingStates=false;
	
	public boolean upload(VRAMBusManager bus){
		if(!isFilled){return false;}
		isChangingStates=true;
		for(VRAMObject vo:loadables){
			bus.loadIntoVRAM(vo);
		}
		return true;
	}
	public boolean unload(VRAMBusManager bus){
		if(!isFilled){return false;}
		isLoaded=false;
		isChangingStates=true;
		for(VRAMObject vo:loadables){
			bus.unloadFromVRAM(vo);
		}
		return true;
	}	
	
	public void poll(){
		if(isChangingStates){
			boolean initial = loadables[0].IsAvailable();
			for(VRAMObject vo:loadables){
				if(vo.IsAvailable()!=initial){return;}
			}
			isLoaded=initial;
			isChangingStates=false;
		}
	}

	
	private BindingContract contract;
	
	private void bindContract(){
		for(VRAMObject o:loadables){
			if(o instanceof IRequiresBindingContract){
				IRequiresBindingContract irbc = (IRequiresBindingContract)o;
				irbc.setBindingContract(contract);
			}
		}
	}
	
	public void setBindingContractToAll(BindingContract contract) {
		this.contract=contract;
		bindContract();
	}	
	
}
