package com.demo.mainapp.crossyroaddemo;

import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.util.Duration;

import java.net.URL;

public class HelloApplication extends Application {
    private MediaPlayer mediaPlayer;
    private HelloController game;
    private Stage primaryStage;
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private boolean pause;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("Crossy Road - Version: 1.0.1 ");
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
    // Khởi tạo display
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        URL resource = getClass().getResource("/assets/bgMusic.mp3");

        if (resource == null) {
            System.out.println("Audio file not found!");
        }
        Media media = new Media(resource.toString());
        MediaPlayer a =new MediaPlayer(media);
//        a.setOnEndOfMedia( () -> a.seek(Duration.ZERO));
        a.setCycleCount(MediaPlayer.INDEFINITE);
        a.play();

        // Thêm background
        Image bgImage = new Image(
                getClass().getResource("/assets/minecraft-crossy-road.png").toExternalForm(),
                WIDTH, HEIGHT,
                false, true
        );

        // Tạo ImageView cho logo - 1280 - 720 => 640 - 360 => 320 - 180 => 160 - 90
        Image logoImage = new Image(getClass().getResource("/assets/Main-logo-2.png").toExternalForm(),
                400, 260, false, true
        );
        ImageView logoView = new ImageView(logoImage);

        VBox box = new VBox(10);
        box.setBackground(new Background(
                new BackgroundFill(Color.web("black", 0.6), null, null)
        ));
        box.setTranslateX(WIDTH - 360);
        box.setTranslateY(HEIGHT - 260);
        MenuItem playItem = new MenuItem("PLAY", () -> initGame());
        MenuItem settingsItem = new MenuItem("SETTING", () -> initSettings());
        MenuItem quitItem  = new MenuItem("QUIT", () -> Platform.exit());
        box.getChildren().addAll(playItem, settingsItem, quitItem);

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(WIDTH, 90);
        stackPane.getChildren().add(logoView);
        StackPane.setAlignment(logoView, Pos.CENTER);

        root.getChildren().addAll(new ImageView(bgImage), stackPane, box);
        return root;
    }

    private static class MenuItem extends StackPane{
        MenuItem(String name, Runnable action){
            LinearGradient gradient = new LinearGradient(
                    0, 0.5, 1, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0.1, Color.web("white", 0.5)),
                    new Stop(1.0, Color.web("black", 0.5))
            );

            Rectangle bg0 = new Rectangle(250, 30, gradient);
            Rectangle bg1 = new Rectangle(250, 30, Color.web("black", 0.2));

            FillTransition ft = new FillTransition(Duration.seconds(0.6666), bg1,
                    Color.web("black", 0.2), Color.web("white", 0.3));
            ft.setAutoReverse(true);
            ft.setCycleCount(Integer.MAX_VALUE);

            hoverProperty().addListener((o, oldValue, isHovering) -> {
                if(isHovering){
                    ft.playFromStart();
                } else {
                    ft.stop();
                    bg1.setFill(Color.web("black", 0.2));
                }
            });
            Rectangle line = new Rectangle(5, 30);
            line.widthProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(8)
                            .otherwise(5)
            );
            line.fillProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(Color.RED)
                            .otherwise(Color.GRAY)
            );
            Text text = new Text(name);
            text.setFont(Font.font(22.0));
            text.fillProperty().bind(
                    Bindings.when(hoverProperty())
                            .then(Color.WHITE)
                            .otherwise(Color.GRAY)
            );
            setOnMouseClicked(e -> action.run());
            setOnMousePressed(e -> bg0.setFill(Color.LIGHTGREEN));
            setOnMouseReleased(e -> bg0.setFill(gradient));
            setAlignment(Pos.CENTER_LEFT);

            HBox box =new HBox(15, line, text);
            box.setAlignment(Pos.CENTER_LEFT);

            getChildren().addAll(bg0, bg1, box);
        }
    }
    private void showMainMenu() {
        VBox mainMenu = new VBox(20);
        mainMenu.setAlignment(Pos.CENTER);

        Text title = new Text("Đồ Án Game");
        title.setFont(new Font(30)); // Title game

        Button startButton = new Button("Bắt đầu");
        Button settingsButton = new Button("Cài đặt");
        Button exitButton = new Button("Thoát");

        // size font  button
        startButton.setFont(new Font(20));
        settingsButton.setFont(new Font(20));
        exitButton.setFont(new Font(20));

        // Thực hiện chức năng
        startButton.setOnAction(event -> initGame());
        settingsButton.setOnAction(event -> initSettings());
        exitButton.setOnAction(event -> primaryStage.close());

        mainMenu.getChildren().addAll(title, startButton, settingsButton, exitButton);

        StackPane root = new StackPane(mainMenu);
        root.setFocusTraversable(true);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
//    GameSettings settings
    protected void initSettings() {
        VBox settingsMenu = new VBox(20);
        settingsMenu.setAlignment(Pos.CENTER);

        Button backButton = new Button("Quay lại");
        backButton.setFont(new Font(20)); // font size quay lại  button
        backButton.setOnAction(event -> showMainMenu());

        settingsMenu.getChildren().add(backButton);

        StackPane root = new StackPane(settingsMenu);
        Scene scene = new Scene(root, 640, 480);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Settings");
        primaryStage.show();
    }

    private VBox createMenuBox() {
        VBox box = new VBox(10);
        box.setBackground(new Background(new BackgroundFill(Color.web("black", 0.6), null, null)));
        box.setTranslateX(WIDTH - 360);
        box.setTranslateY(HEIGHT - 260);

        MenuItem playItem = new MenuItem("Bắt đầu", this::initGame);
        MenuItem settingsItem = new MenuItem("Cài đặt", this::initSettings);
        MenuItem quitItem = new MenuItem("Thoát", () -> {
            mediaPlayer.stop(); // Stop the music when quitting
            Platform.exit();
        });

        box.getChildren().addAll(playItem, settingsItem, quitItem);
        return box;
    }

    private VBox createSettingsBox() {
        VBox settingsBox = new VBox(20);
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.setPadding(new Insets(20));
        settingsBox.setMaxSize(1000, 700); // Đặt kích thước tối đa cho khung cài đặt
        settingsBox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.8), new CornerRadii(10), Insets.EMPTY)));
        settingsBox.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        // Mute Music Label
        Label muteMusicLabel = new Label("Tắt âm nhạc");
        muteMusicLabel.setFont(new Font(20));
        muteMusicLabel.setTextFill(Color.WHITE);
        muteMusicLabel.setOnMouseClicked(event -> {
            isMuted = !isMuted;
            mediaPlayer.setMute(isMuted);
            muteMusicLabel.setText(isMuted ? "Bật âm nhạc" : "Tắt âm nhạc");
        });

        // Volume Slider for Music
        Slider volumeSlider = new Slider(0, 1, mediaPlayer.getVolume());
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> mediaPlayer.setVolume(newVal.doubleValue()));

        // Mute SFX Label
        Label muteSFXLabel = new Label("Tắt SFX");
        muteSFXLabel.setFont(new Font(20));
        muteSFXLabel.setTextFill(Color.WHITE);
        muteSFXLabel.setOnMouseClicked(event -> {
            isSFXMuted = !isSFXMuted;
            muteSFXLabel.setText(isSFXMuted ? "Bật SFX" : "Tắt SFX");
            // You might want to integrate the functionality to handle SFX mute state here
        });

        // Volume Slider for SFX
        Slider sfxVolumeSlider = new Slider(0, 1, sfxVolume); // Initial SFX volume
        sfxVolumeSlider.setShowTickLabels(true);
        sfxVolumeSlider.setShowTickMarks(true);
        sfxVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            sfxVolume = newVal.doubleValue();
            // Update SFX volume here
        });

        settingsBox.getChildren().addAll(muteMusicLabel, volumeSlider, muteSFXLabel, sfxVolumeSlider);

        return settingsBox;
    }


    protected void initGame() {
        game = new HelloController();

        Scene gameScene = new Scene(game.getRoot(), WIDTH, HEIGHT);

        gameScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    game.movePlayerUp();
                    break;
                case DOWN:
                    game.movePlayerDown();
                    break;
                case LEFT:
                    game.movePlayerLeft();
                    break;
                case RIGHT:
                    game.movePlayerRight();
                    break;
                default:
                    break;
            }
        });
        game.getRoot().requestFocus();

        primaryStage.setScene(gameScene);
        primaryStage.setTitle("Crossy Road");
        primaryStage.show();

        // Listen for game over event from Game instance
        game.setOnGameOver(() -> showGameOver(game.getScore()));
    }

    private void showGameOver(int score) {
        VBox gameOverMenu = new VBox(20);
        gameOverMenu.setAlignment(Pos.CENTER);

        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(new Font(30));
        gameOverText.setFill(Color.RED);

        Text scoreText = new Text("Your Score: " + score);
        scoreText.setFont(new Font(20));
        scoreText.setFill(Color.BLACK);

        //-------------------------------------------------------------------------------------------------
        Button backButton = new Button("Quay lại");
        backButton.setFont(new Font(20));
        backButton.setOnAction(event -> showMainMenu());
        //-------------------------------------------------------------------------------------------------

        gameOverMenu.getChildren().addAll(gameOverText, scoreText, backButton);

        StackPane root = new StackPane(gameOverMenu);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Over");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}