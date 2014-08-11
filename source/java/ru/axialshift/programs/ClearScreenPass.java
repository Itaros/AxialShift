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
package ru.axialshift.programs;

import org.lwjgl.opengl.GL11;

import ru.axialshift.display.RenderingManager;

public class ClearScreenPass extends BasePass {

	@Override
	public void execute(RenderingManager renderingManager) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}

}
