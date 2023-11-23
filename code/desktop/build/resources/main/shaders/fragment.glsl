#version 430

layout (binding = 0) uniform sampler2D samp;
out vec4 color;
in vec4 pos;
in vec2 texels;

void main(void){

    color = texture(samp,texels) * pos;
}