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

import java.util.ArrayList;

import ru.axialshift.context.Context;
import ru.axialshift.vram.VRAMBusManager;

public class ContextManager {

	private VRAMBusManager bus;
	
	public ContextManager(VRAMBusManager bus) {
		this.bus=bus;
	}
	
	public VRAMBusManager getVRAMBus(){
		return bus;
	}

	private ArrayList<Context> loaded = new ArrayList<Context>();
	
	public void enqueueContext(Context context){
		System.out.println("Context enqueued: "+context.getClass().getSimpleName());
		loaded.add(context);
		context.upload(bus);
	}
	
	public void relieveContext(Context context){
		System.out.println("Context dequeued: "+context.getClass().getSimpleName());
		loaded.remove(context);
		context.unload(bus);
	}
	
	void process(){
		;
	}
	
}
