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

import ru.axialshift.programs.ClearScreenPass;
import ru.axialshift.programs.IRenderProgram;
import ru.axialshift.utils.Console;
import ru.axialshift.vram.VRAMBusManager;

public class BaseDisplay {

	GLInitializer initializer=new GLInitializer();
	Processor processor = new Processor();
	VRAMBusManager bus = new VRAMBusManager();
	ContextManager manager = new ContextManager(bus);
	RenderingManager rendering = new RenderingManager();
	
	protected void start(){
		initializer.set32GLDisplay(800, 600);
		
		//Setting dummy rendering program
		rendering.setRendering(new IRenderProgram[]{new ClearScreenPass()});
		
		processor.start(manager,rendering);
		
	}
	
	
	public static void main(String[] args) {
		System.out.println("Starting base display!");
		Console.out_separator();
		BaseDisplay base = new BaseDisplay();
		base.start();
		Console.out_separator();
		System.out.println("Have a good day, sir!");
	}

}
