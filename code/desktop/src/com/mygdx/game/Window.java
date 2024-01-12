package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.mygdx.game.practice.QuadTest1;
import com.mygdx.game.practice.project1.RenderQuad;
import com.mygdx.game.projects.RayCastCubeDemo.RayCastCube;
import com.mygdx.game.projects.TextureDemo.TextureTest;
import com.mygdx.game.projects.VectorReasoning.VectorReasoningDemo;
import com.mygdx.game.projects.VertexAttributesDemo.QuadTest;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
   private final JList<String> list;
   public String[] stringsList = {
    "QuadTest2"
    };
    public Window(){
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.GL32,3,2);
        config.setForegroundFPS(60);
        config.setTitle("My GDX Game");
        config.useVsync(true);
        config.setWindowedMode(1080,720);
        config.setWindowListener(new Lwjgl3WindowAdapter(){
            @Override
            public boolean closeRequested() {
                System.exit(0);
                return super.closeRequested();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(1080,720);

        list = new JList<>(stringsList);
        list.setBackground(new Color(233143));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> {
            String item = stringsList[e.getFirstIndex()];
            if ("QuadTest2".equals(item)){
                setVisible(false);
                new Lwjgl3Application(new RenderQuad(), config);
            }
        });
        JScrollPane pane = new JScrollPane(list);
        add(pane);
        setVisible(true);
    }
}
