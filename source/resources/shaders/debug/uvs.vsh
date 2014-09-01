#version 150 core

uniform mat4 M;
uniform mat4 VP;

in vec4 in_Position;

in vec2 in_uvs;
out vec2 pass_uvs;


void main(void){
	pass_uvs=in_uvs;
	gl_Position = VP * M * in_Position;
}