#version 430

layout(location = 0) in vec3 a_position;
layout(location = 1) in vec2 a_texCoord;

uniform mat4 projection;
out vec2 texels;

void main(void){
    gl_Position =  projection  * vec4(a_position,1.0);
    texels = a_texCoord;
}