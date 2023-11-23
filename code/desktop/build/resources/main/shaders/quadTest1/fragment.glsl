#version 430

layout(binding = 0) uniform sampler2D textureSamplier;

in vec2 texels;
out vec4 color;

void main(void){
    color = texture(textureSamplier,texels);
}