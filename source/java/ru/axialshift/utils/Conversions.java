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
package ru.axialshift.utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Quaternion;

public final class Conversions {

	public static Matrix4f toMatrix4f(Quaternion q){
		float x2 = q.x * q.x;
		float y2 = q.y * q.y;
		float z2 = q.z * q.z;
		float xy = q.x * q.y;
		float xz = q.x * q.z;
		float yz = q.y * q.z;
		float wx = q.w * q.x;
		float wy = q.w * q.y;
		float wz = q.w * q.z;
	 
		Matrix4f rslt = new Matrix4f();
		rslt.m00=1.0f - 2.0f * (y2 + z2);
		rslt.m01=2.0f * (xy - wz);
		rslt.m02=2.0f * (xz + wy);
		rslt.m03=0.0f;
		rslt.m10=2.0f * (xy + wz);
		rslt.m11=1.0f - 2.0f * (x2 + z2);
		rslt.m12=2.0f * (yz - wx);
		rslt.m13=0.0f;
		rslt.m20=2.0f * (xz - wy);
		rslt.m21=2.0f * (yz + wx);
		rslt.m22=1.0f - 2.0f * (x2 + y2);
		rslt.m23=0.0f;
		rslt.m30=0.0f;
		rslt.m31=0.0f;
		rslt.m32=0.0f;
		rslt.m33=1.0f;
		return rslt;
	}
	
}
