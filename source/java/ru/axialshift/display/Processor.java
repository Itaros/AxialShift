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

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Processor {

	private ContextManager manager;
	private RenderingManager renderer;
	
	public void start(ContextManager manager, RenderingManager rendering){
		this.manager=manager;
		this.renderer=rendering;
		
		System.out.println("Preconfiguring...");
		preconfigureOGL();
		
		System.out.println("Executing Rendering Loop!");
		//Cycling
		while (!Display.isCloseRequested()){
			load();
			
			draw();
			
			cutFrame();
		}
	}

	private void preconfigureOGL() {
		GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}

	private void draw() {
		renderer.draw();
	}

	private void load() {
		manager.process();
		manager.getVRAMBus().process();
	}

	private void cutFrame() {
		Display.sync(60);
		Display.update();
	}
	
}
