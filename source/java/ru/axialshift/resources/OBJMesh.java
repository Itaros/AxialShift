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
package ru.axialshift.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class OBJMesh extends MeshData {

	RandomAccessFile file;
	//TODO: change normal evaluation strategy based on that
	private boolean smoothShading;

	public OBJMesh(String modelPath) {
		
		super();

		try {

			file = new RandomAccessFile(modelPath, "r");
			processData();

		} catch (FileNotFoundException e) {

			System.out.println("Are you sure you specified the path correctly?");
			e.printStackTrace();

		} catch (IOException e) {

			System.out.println("Ok I will admit that I can't read");
			e.printStackTrace();

		}
		
		calculate();

	}

	private void processData() throws IOException {
		
		String tmpString;
		int lineCount = 0;
		int verticeLineCount = 0;
		int uvLineCount = 0;
		int indiceLineCount = 0;
		
		//Counting the number of lines and other fancy things
		while ((tmpString = file.readLine()) != null) {
			
			if(tmpString.startsWith("v ")) {verticeLineCount++;}
			if(tmpString.startsWith("vt ")) {uvLineCount++;}
			if(tmpString.startsWith("f ")) {indiceLineCount++;}
			if(tmpString.startsWith("s ")) {
				if(tmpString.contains("off")) {smoothShading = false;} else {smoothShading = true;}
			}
			lineCount++;
			
		}
		file.seek(0);
		
		//Initializing the arrays used for "improper" reading
		float[] verticesX = new float[verticeLineCount];
		float[] verticesY = new float[verticeLineCount];
		float[] verticesZ = new float[verticeLineCount];
		float[] uvX = new float[uvLineCount];
		float[] uvY = new float[uvLineCount];
		indices = new byte[indiceLineCount * 3];
		byte[] uvIndices = new byte[indiceLineCount * 3];
		vertices = new float[indices.length * 3];
		uvs = new float[indices.length * 2];
		Arrays.fill(uvs, 0);
		
		//The index value for each vertice/uv/indices (increases whilst in the processing stages)
		int vIndex = 0;
		int uvIndex = 0;
		int iIndex = 0;
		
		//Main loop for the reading process
		for (int i = 0; i < lineCount; i++) {
			
			//Read Line
			tmpString = file.readLine();

			//the vertices processing
			if (tmpString.startsWith("v ")) {

				if(tmpString.startsWith("v  ")) {tmpString = tmpString.replaceAll("v  ", "");} else {tmpString = tmpString.replaceAll("v ", "");}
				
				verticesX[vIndex] = Float.parseFloat(tmpString.split(" ")[0]);
				verticesY[vIndex] = Float.parseFloat(tmpString.split(" ")[1]);
				verticesZ[vIndex] = Float.parseFloat(tmpString.split(" ")[2]);
				vIndex++;

			}
			
			//the uvs processing
			if (tmpString.startsWith("vt ")) {

				tmpString = tmpString.replaceAll("vt ", "");

				uvX[uvIndex] = Float.parseFloat(tmpString.split(" ")[0]);
				uvY[uvIndex] = Float.parseFloat(tmpString.split(" ")[1]);
				uvIndex++;

			}
			
			//the indices processing
			if (tmpString.startsWith("f ")) {

				tmpString = tmpString.replaceAll("f ", "");
				String[] tmpBuffer = tmpString.split(" ");
				
				for (int j = 0; j < tmpBuffer.length; j++) {

					indices[iIndex] = (byte)(Byte.parseByte(tmpBuffer[j].split("/")[0]) - 1);
					if(tmpString.contains("/")){uvIndices[iIndex] = (byte)(Byte.parseByte(tmpBuffer[j].split("/")[1]) - 1);}
					iIndex++;

				}

			}

		}
		
		//The process in which vertices and uvs are corrected
		vIndex = 0;
		for(int i = 0; i < indices.length; i++) {
			
			vertices[vIndex] = (verticesX[indices[i]]);
			vIndex = vIndex + 1;
			vertices[vIndex] = (verticesY[indices[i]]);
			vIndex = vIndex + 1;
			vertices[vIndex] = (verticesZ[indices[i]]);
			vIndex = vIndex + 1;
			
		}
		
		uvIndex = 0;
		for(int i = 0; i < uvIndices.length; i++) {
			
			uvs[uvIndex] = (uvX[uvIndices[i]]);
			uvIndex = uvIndex + 1;
			uvs[uvIndex] = (uvY[uvIndices[i]]);
			uvIndex = uvIndex + 1;
			
		}
		
		iIndex = 0;
		for(int i = 0; i < indices.length; i++) {
			
			indices[i] = (byte)i;
			
		}
		
	}

}
