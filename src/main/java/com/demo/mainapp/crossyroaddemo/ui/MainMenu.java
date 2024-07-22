package com.demo.mainapp.crossyroaddemo.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Button;
import javafx.scene.Node;

import javafx.beans.binding.StringBinding;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class MainMenu extends FXGLMenu implements MainMenu_Interfaces {

    public MainMenu() {
        super(MenuType.MAIN_MENU);

        Text title = getUIFactoryService().newText("Crossy Road Demo", Color.WHITE, 50);

        Button btnNewGame = new Button("New Game");
        btnNewGame.setOnAction(e -> fireNewGame());

        Button btnOptions = new Button("Options");
        btnOptions.setOnAction(e -> {
            // Mở menu tùy chọn
        });

        Button btnExtra = new Button("Extra");
        btnExtra.setOnAction(e -> {
            // Mở menu extra
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> FXGL.getGameController().exit());


        VBox menuBox = new VBox(10, btnNewGame, btnOptions, btnExtra, btnExit);
        menuBox.setTranslateX(getAppWidth() / 2 - 50);
        menuBox.setTranslateY(getAppHeight() / 2 - 50);

        getContentRoot().getChildren().addAll(title, menuBox);
    }
    @Override
    public Button createActionButton(StringBinding stringBinding, Runnable runnable){
        return new Button();
    }
    @Override
    public Button createActionButton(String s, Runnable runnable){
        return new Button();
    }
    @Override
    public Node createBackGround(double w, double h){
        return FXGL.texture("background/minecraft-crossy-road.png");
    }

    @Override
    public Node  createProfileView(String s) {
        return new Rectangle();
    }
    @Override
    public Node  createTitleView(String s) {
        return new Rectangle();
    }
    @Override
    public Node  createVersionView(String s) {
        return new Rectangle();
    }
}

