package com.demo.mainapp.crossyroaddemo;

import com.demo.mainapp.crossyroaddemo.components.CarComponent;
import com.demo.mainapp.crossyroaddemo.components.PlayerCpn;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class HelloController {
//    private Canvas canvas;
    private GraphicsContext gc;
    private Pane root = new Pane();
    private PlayerCpn player;
    private ArrayList<ImageView> obstacles;
    private AnimationTimer timer;
    private Runnable onGameOver;
    private int score;
    private Text scoreText;
//    private ArrayList<SpriteCustom[]> allStrips;
    private int numOfStrips = 9;
    //2D array for holding sprite strips.
    private SpriteCustom[][] allStrips = new SpriteCustom[numOfStrips][8];
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private ImageView[] roadStrips;
    //Create hero sprite.
    private ImageView heroImageView;
    private SpriteCustom heroCustom = new SpriteCustom("/assets/Test-textures/Player_boat/Player_boat_UP.png", 300, 440);
    private Sprite hero = new Sprite("/assets/Test-textures/Player_boat/Player_boat_UP.png");
    Sprite[] roadStrip;
    StripGenerator stripGen;
    public HelloController() {
//        heroImageView = new ImageView(new Image(getClass().getResourceAsStream("/assets/Test-textures/Player_boat/Player_boat_UP.png")));
//        heroImageView.setX(300);
//        heroImageView.setY(440);
//        root.getChildren().add(heroImageView);

        setInitialLocations();
        // Khởi tạo điểm số và văn bản điểm số
        score = 0;
        scoreText = new Text("Score: 0");
        scoreText.setFont(new Font(20));
        scoreText.setFill(Color.BLACK);
        scoreText.setX(10);
        scoreText.setY(30);

        // Thiết lập sự kiện KeyEvent
        root.setFocusTraversable(true);
        root.requestFocus();
        root.setOnKeyPressed(this::handleKeyPress);

//        allStrips = new ArrayList<>();
        // Cài đặt các dải sprite ban đầu
        stripGen = new StripGenerator();
//        roadStrips = stripGen.getStrip(); // Assuming getStrip() returns ImageView[]
//        for (ImageView roadStrip : roadStrips) {
//            roadStrip.setY(0);
//            root.getChildren().add(roadStrip);
//        }

        // Thiết lập vòng lặp AnimationTimer cho vòng lặp game
//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//            }
//        };
//
//        timer.start();

        //-------------------------------------------------------------------------------------
//        player = new PlayerCpn(300, 440);
//        root.getChildren().add(player.getShape());
//
//        obstacles = new ArrayList<>();
//        setupObstacles();
//        //score
//        score = 0;
//        scoreText = new Text("Score: 0");
//        scoreText.setFont(new Font(20));
//        scoreText.setFill(Color.BLACK);
//        scoreText.setX(500);
//        scoreText.setY(30);
//        root.getChildren().add(scoreText);
//
//        // Thiết lập focus và sự kiện KeyEvent
//        root.setFocusTraversable(true);
//        root.requestFocus();
//
//        // Thiết lập các phương thức di chuyển của player
//        root.setOnKeyPressed(this::handleKeyPress);
//
//        // Thiết lập vòng lặp AnimationTimer cho vòng lặp game
//        timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//            }
//        };
//
//        timer.start();
    }
    private void setInitialLocations(){
        root.getChildren().add(heroCustom);

        for (int i = 0; i < numOfStrips; i++) {
            //Creates a new land sprite strip.
            SpriteCustom[] strip = stripGen.getLandStrip();
            //Adds sprite strip to strips array.
            allStrips[i] = strip;

            allStrips[5][3].setImage("/assets/Materials/Glass/Safe_terrain.jpg");
            allStrips[4][3].setImage("/assets/Materials/Glass/Safe_terrain.jpg");
        }
    }
    private void setupStrips() {
        for (ImageView roadStrip : roadStrips) {
            roadStrip.setY(0);
            root.getChildren().add(roadStrip);
        }
    }

    // Xử lý khi phím được nhấn
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                movePlayerUp();
                break;
            case DOWN:
                movePlayerDown();
                break;
            case LEFT:
                movePlayerLeft();
                break;
            case RIGHT:
                movePlayerRight();
                break;
            default:
                break;
        }
    }

    public void moveHeroUp() {
        heroImageView.setY(heroImageView.getY() - 10);
        if (heroImageView.getY() <= 0) {
            score++;
            scoreText.setText("Score: " + score);
            hero.setYLoc(440);  // Reset hero position
        }
    }

    public void moveHeroDown() {
        heroImageView.setY(heroImageView.getY()+ 10);
    }

    public void moveHeroLeft() {
        heroImageView.setX(heroImageView.getX() - 10);
    }

    public void moveHeroRight() {
        heroImageView.setX(heroImageView.getX() + 10);
    }

    public void movePlayerUp() {
        player.moveUp();
        if (player.getShape().getY() <= 0) {
            score++;
            scoreText.setText("Score: " + score);
            player.getShape().setY(440);
        }
    }
    public void movePlayerDown() {
        player.moveDown();
    }
    public void movePlayerLeft() {
        player.moveLeft();
    }
    public void movePlayerRight() {
        player.moveRight();
    }
    //-----------------------------------------------------------------------------

    // Thiết lập các chướng ngại vật
    private void setupObstacles() {
        // Khởi tạo và thêm các chướng ngại vật vào root pane
        for (int i = 0; i < 5; i++) {
            ImageView obstacle = createRandomObstacle();
            obstacles.add(obstacle);
            root.getChildren().add(obstacle);
        }
    }

    // Tạo ngẫu nhiên một chướng ngại vật
    private ImageView  createRandomObstacle() {
        Random random = new Random();
        double x = 640 + random.nextDouble() * 200; // Random position off-screen to the right
        double y = 50 + random.nextDouble() * 300; // Random position within visible area

        ImageView obstacle = new  ImageView(new Image(getClass().getResourceAsStream("/assets/Materials/Car-obs/Car_2_Right.png"))); // Update with your image path
        obstacle.setX(x);
        obstacle.setY(y);
        return obstacle;
    }

    // Cập nhật logic game
//    private void update() {
//        // Cập nhật logic game, ví dụ: di chuyển chướng ngại vật, phát hiện va chạm, vv.
//        for (Obstacle obstacle : obstacles) {
//            obstacle.move();
//
//            // Kiểm tra va chạm với người chơi
//            if (player.getShape().getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
//                timer.stop();
//                System.out.println("Game Over");
//            }
//        }
//    }

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;

    }
    public int getScore() {
        return score;
    }

    private void update() {
        for (ImageView obstacle : obstacles) {
            obstacle.setX(obstacle.getX() - 2);
            // Kiểm tra va chạm với người chơi
            if (hero.getImageView().getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                timer.stop();
                System.out.println("Game Over");
            }
        }
    }

//    private void update() {
//        // Cập nhật logic game, ví dụ: di chuyển chướng ngại vật, phát hiện va chạm, vv.
//        for (CarComponent obstacle : obstacles) {
//            obstacle.move();
//
//            // Kiểm tra va chạm với người chơi
//            if (player.getShape().getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
//                timer.stop();
//                if (onGameOver != null) {
//                    onGameOver.run();
//                }
//            }
//        }
//    }
      public Pane getRoot() {
          return root;
      }
}