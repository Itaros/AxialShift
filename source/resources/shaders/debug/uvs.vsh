#version 150 core

uniform mat4 MVP;

in vec4 in_Position;

in vec2 in_uvs;
out vec2 pass_uvs;


void main(void){
	pass_uvs=in_uvs;
	gl_Position = MVP * in_Position;
}