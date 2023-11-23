#version 430
layout (location = 0) in vec3 a_position;
layout (location = 1) in vec2 a_texCoord0;

uniform mat4 combined;
uniform float light;
out vec4 pos;
out vec2 texels;


void main(void){
    pos = vec4(1+light,1.1f+light,2+light,1.0);
    texels = a_texCoord0;
    gl_Position =  combined  * vec4(a_position,1.0);
}