#version 150 core

uniform mat4 compositeMatrix;

in vec4 in_Position;
in vec2 in_TextureCoord;

out vec2 pass_TextureCoord;

void main(void) {
	gl_Position = compositeMatrix * in_Position;
	pass_TextureCoord = in_TextureCoord;
}