#version 150 core

in vec2 pass_uvs;
in vec3 pass_normals;

out vec4 out_Color;

void main(void){
	out_Color = vec4(pass_normals.xyz,1);
}