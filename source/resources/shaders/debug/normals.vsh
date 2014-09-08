#version 150 core

uniform mat4 M;
uniform mat4 VP;

in vec4 in_Position;

in vec2 in_uvs;
in vec3 in_normals;
out vec2 pass_uvs;
out vec3 pass_normals;

void main(void){
	pass_uvs=in_uvs;
	pass_normals=in_normals;
	gl_Position = VP * M * in_Position;
}