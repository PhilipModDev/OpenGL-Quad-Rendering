package com.mygdx.game.practice.project1;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.FloatArray;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.badlogic.gdx.Gdx.gl32;

public class QuadFace {
    private int x;
    private int y;
    private int z;
    private final int[] vao = new int[1];
    private final int[] location;
    private int vbo;
    private boolean isUploadedToGPU;
    private final ShaderProgram shader;
    private final Matrix4 model = new Matrix4();
    private final VertexAttributes attributes = new VertexAttributes(
            new VertexAttribute(VertexAttributes.Usage.Position,3,ShaderProgram.POSITION_ATTRIBUTE),
            new VertexAttribute(VertexAttributes.Usage.TextureCoordinates,2,ShaderProgram.TEXCOORD_ATTRIBUTE)
    );

    private final FloatBuffer buffer;
    private final Texture texture;

    public QuadFace(int x, int y, int z, ShaderProgram shader, Texture texture){
        this.shader = shader;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.z = z;
        model.setToTranslation(this.x,this.y,this.z);
        FloatArray array = new FloatArray();
        array.addAll(getVertices());
        buffer = BufferUtils.newFloatBuffer(attributes.vertexSize * (array.size/attributes.size()));
        buffer.put(array.items);
        buffer.flip();
        location = new int[attributes.size()];
        for (int i = 0; i < location.length; i++) {
            VertexAttribute attribute = attributes.get(i);
            location[i] = shader.getAttributeLocation(attribute.alias);
        }
    }


    private void uploadToGPU(){
        isUploadedToGPU = true;
        gl32.glGenVertexArrays(vao.length,vao,vao[0]);
        gl32.glBindVertexArray(vao[0]);
        vbo = gl32.glGenBuffer();
        gl32.glBindBuffer(GL32.GL_ARRAY_BUFFER,vbo);
        gl32.glBufferData(GL32.GL_ARRAY_BUFFER,buffer.limit() * Float.BYTES,buffer,GL32.GL_STATIC_DRAW);
        int ebo = gl32.glGenBuffer();
        gl32.glBindBuffer(GL32.GL_ELEMENT_ARRAY_BUFFER,ebo);
        int[] ints = getIndices();
        IntBuffer indices = BufferUtils.newIntBuffer(ints.length);
        indices.put(ints);
        indices.flip();
        gl32.glBufferData(GL32.GL_ELEMENT_ARRAY_BUFFER,indices.limit() * Float.BYTES,indices,GL32.GL_STATIC_DRAW);
    }

    public void render(Camera camera){
        model.setToTranslation(this.x,this.y,this.z);
       if (!isUploadedToGPU) uploadToGPU();
       shader.bind();
       texture.bind();
       shader.setUniformMatrix("model",model);
       shader.setUniformMatrix("view",camera.view);
       shader.setUniformMatrix("projection",camera.projection);
       //Binds the buffers.
       gl32.glBindVertexArray(vao[0]);
       gl32.glBindBuffer(GL32.GL_ARRAY_BUFFER,vbo);
        for (int iLocation : location) {
            VertexAttribute attribute = attributes.get(iLocation);
            shader.setVertexAttribute(attribute.alias, attribute.numComponents, attribute.type, attribute.normalized, attributes.vertexSize, attribute.offset);
            shader.enableVertexAttribute(iLocation);
        }
        gl32.glEnable(GL32.GL_DEPTH_TEST);
        gl32.glDepthFunc(GL32.GL_LEQUAL);
        gl32.glDrawElements(GL32.GL_TRIANGLES,getIndices().length,GL32.GL_UNSIGNED_INT,0);
         //unbinds the buffers.
        gl32.glBindVertexArray(0);
        gl32.glBindBuffer(GL32.GL_ARRAY_BUFFER,0);
    }


    private float[] getVertices(){
        return new float[]{
            0,1,0, 0,0,
            0,0,0, 0,1,
            1,0,0, 1,1,
            1,1,0, 1,0,
        };
    }

    private int[] getIndices(){
        return new int[]{
            0,1,2,2,3,0
        };
    }
}
