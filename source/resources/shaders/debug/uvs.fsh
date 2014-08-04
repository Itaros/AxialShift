#version 150 core

in vec2 pass_uvs;

out vec4 out_Color;

void main(void){
	out_Color = vec4(pass_uvs.xy,1,1);
}