package com.demo.mainapp.crossyroaddemo.ui;

import com.almasb.fxgl.app.scene.LoadingScene;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class loadingScene extends LoadingScene {
    public loadingScene(){
        super();

        Text text = new Text("Loading . . .");
        text.setFill(Color.WHITE);
        text.setStyle("--fx-font-size: 48px;");

        StackPane stackPane = new StackPane(text);
        stackPane.setAlignment(Pos.CENTER);

        getContentRoot().getChildren().add(stackPane);
    }
}
