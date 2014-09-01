package ru.axialshift.utils;

import org.lwjgl.util.vector.Quaternion;

public class QuaternionHelper {
	
	 public static void rotate(Quaternion q, float pitch, float yaw, float roll) {
		 // Assuming the angles are in radians.
		 float c1 = (float)Math.cos(pitch/2);
		 float s1 = (float)Math.sin(pitch/2);
		 float c2 = (float)Math.cos(yaw/2);
		 float s2 = (float)Math.sin(yaw/2);
		 float c3 = (float)Math.cos(roll/2);
		 float s3 = (float)Math.sin(roll/2);
		 float c1c2 = c1*c2;
		 float s1s2 = s1*s2;
		 q.w =(c1c2*c3 - s1s2*s3);
		 q.x =(c1c2*s3 + s1s2*c3);
		 q.y =(s1*c2*c3 + c1*s2*s3);
		 q.z =(c1*s2*c3 - s1*c2*s3);
	}
	 
}
