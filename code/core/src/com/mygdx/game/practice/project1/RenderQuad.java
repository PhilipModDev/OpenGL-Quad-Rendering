package com.mygdx.game.practice.project1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import javax.sound.sampled.FloatControl;

import static com.badlogic.gdx.Gdx.gl32;

public class RenderQuad implements ApplicationListener {

    private PerspectiveCamera camera;
    private FirstPersonCameraController controller;
    private QuadFace quadFace;
    private ShaderProgram shader;
    private Texture texture;

    @Override
    public void create() {
        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(0,0,0);
        camera.near = 0.1f;
        camera.far = 1000f;
        camera.update();
        controller = new FirstPersonCameraController(camera);
        Gdx.input.setInputProcessor(controller);
        texture = new Texture(Gdx.files.internal("textures/game_textures/moon.png"));
        shader = loadedShader();

        quadFace = new QuadFace(0,1,0,shader,texture);
    }


    private ShaderProgram loadedShader(){
        FileHandle vertex = Gdx.files.internal("shaders/quadTest1/vertex.glsl");
        FileHandle fragment = Gdx.files.internal("shaders/quadTest1/fragment.glsl");
        return new ShaderProgram(vertex,fragment);
    }

    @Override
    public void resize(int width, int height) {
       camera.viewportWidth = width;
       camera.viewportHeight = height;
       camera.update();
       controller.update();
    }

    @Override
    public void render() {
        gl32.glClear(GL32.GL_COLOR_BUFFER_BIT | GL32.GL_DEPTH_BUFFER_BIT);
        gl32.glClearColor(0.1f,0.1f,0.1f,1);
        //Render quad.
        quadFace.render(camera);
        camera.update();
        controller.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
