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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class GLInitializer {

	PixelFormat pixelFormat = new PixelFormat();
	ContextAttribs contextAttributes = new ContextAttribs(3, 2)
	.withForwardCompatible(true)
	.withProfileCore(true);
	
	public void set32GLDisplay(int width, int height){
		System.out.println("Attempting to init GL3.2 LWJGL Display...");
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle("AxialShift Rendering Engine");
			Display.create(pixelFormat, contextAttributes);
		} catch (LWJGLException e) {
			System.out.println("...Failure!");
			e.printStackTrace();
			System.exit(-1);
		}		
		System.out.println("...Success!");
		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
	}
	
}
