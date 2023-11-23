#version 430

layout(location = 0) in vec3 a_position;
layout(location = 1) in vec2 a_texCoord;

out vec2 texels;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main(void){
    texels = a_texCoord;
    gl_Position =  projection * view * model * vec4(a_position,1.0);
}
