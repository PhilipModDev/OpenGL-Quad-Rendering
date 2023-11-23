#version 430

layout(location = 0) in vec3 a_position;
layout(location = 1) in vec2 a_texCoord;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
out vec2 textureCoords;

void main(void){
    gl_Position = projection * view * model * vec4(a_position,1.0);
    textureCoords = a_texCoord;
}
