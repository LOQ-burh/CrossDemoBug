package com.demo.mainapp.crossyroaddemo.ui;

import com.almasb.fxgl.core.UpdatableRunner;
import com.almasb.fxgl.core.fsm.State;
import com.almasb.fxgl.scene.Scene;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;

public interface MainMenu_Interfaces extends State<Scene>, UpdatableRunner {
    Button createActionButton(StringBinding stringBinding, Runnable runnable);

    Button createActionButton(String s, Runnable runnable);

    Node createBackGround(double w, double h);

    Node createProfileView(String s);

    Node createTitleView(String s);

    Node createVersionView(String s);
}
