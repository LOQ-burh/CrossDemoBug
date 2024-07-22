//package com.demo.mainapp.crossyroaddemo;
//
//import com.demo.mainapp.crossyroaddemo.Score.Score;
//import javafx.animation.AnimationTimer;
//import javafx.scene.Scene;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//import javafx.scene.canvas.Canvas;
//import javafx.event.EventHandler;
//import javafx.scene.input.KeyEvent;
//
//import javafx.geometry.Bounds;
//import javafx.geometry.Rectangle2D;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Random;
//
//import static jdk.jfr.internal.consumer.EventLog.update;
//
//public class Display {
//    //Variable for the game logo 'Froggy Road'.
//    private Sprite logo = new Sprite("Misc/Logo.png");
//    private boolean showLogo = false;
//    //New game variables.
//    private boolean newGame = false;
//    //Creates a strip generator object.
//    private StripGenerator stripGen = new StripGenerator();
//    //Holds Number of strips on screen.
//    private int numOfStrips = 9;
//    //2D array for holding sprite strips.
//    private Sprite[][] allStrips = new Sprite[numOfStrips][8];
//    //Holds the index values of special strip images.
//    private ArrayList<Integer> special = new ArrayList<>();
//    //Holds number of special images in special strip.
//    private int land = 1, water = 0;
//    //Array that holds the cars.
//    private ArrayList<Sprite> cars = new ArrayList<>();
//    //Array that holds the trains.
//    private ArrayList<Sprite> trains = new ArrayList<>();
//    private Button startButton, controlsButton;
//    private ManageVehicles vManager = new ManageVehicles();
//    //Create hero sprite.
//    private Sprite hero = new Sprite("Frog/Frog_up.png");
//    //Variable to hold score and travel.
//    private int score = 0, movement = 0;
//    private Score scoreManager = new Score();
//    //Variables for directional control.
//    private int up = 0, down = 0, left = 0, right = 0;
//    private boolean press = false;
//    //Variables for hero invincibility power.
//    private boolean invincibility = false;
//    private int invDuration = 0, invTimeLeft = 0;
//
//    //Create AnimationTimer.
//    private AnimationTimer gameLoop;
//    private boolean isGameLoopRunning = false;
//    //Create random generator.
//    private Random rand = new Random();
//    private Canvas canvas;
//    GraphicsContext g;
////    @Override
//    public void start(Stage primaryStage) {
//        Pane root = new Pane();
//        root.setPrefSize(800, 600);
//        startButton = new Button("Start");
//        controlsButton = new Button("Controls");
//        startButton.setLayoutX(250);
//        startButton.setLayoutY(175);
//        startButton.setPrefSize(300, 200);
//        controlsButton.setLayoutX(300);
//        controlsButton.setLayoutY(390);
//        controlsButton.setPrefSize(200, 100);
//        startButton.setOnAction(event -> {
//            newGame = true;
//            newGame();
//        });
//        controlsButton.setOnAction(event -> {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Controls");
//            alert.setHeaderText(null);
//            alert.setContentText("Arrow Keys:  Move the frog.\nCtrl:  Activates 3 seconds of invincibility once per game.\nShift:  Pause / Resume the game.\nEnter:  Start game / Restart game while paused.");
//            alert.showAndWait();
//        });
//        root.getChildren().addAll(startButton, controlsButton);
//        setInitialLocations();
//        gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                if (newGame) {
//                    newGame();
//                } else {
//                    heroBounds();
//                    jumpHero();
//                    hero.move();
//                    manageCars();
//                    manageTrains();
//                    for (int i = 0; i < numOfStrips; i++) {
//                        for (int x = 0; x < 8; x++) {
//                            allStrips[i][x].move();
//                        }
//                    }
//                    manageStrips();
//                    scrollScreen();
//                    if (movement > score) score = movement;
//                    paintComponent();
//                }
//            }
//        };
//        if (!showLogo) {
//            startButton.setVisible(false);
//            controlsButton.setVisible(false);
//            gameLoop.start();
//        }
//        Scene scene = new Scene(root);
//        scene.setOnKeyPressed(event -> {
//            switch (event.getCode()) {
//                case UP -> up = 1;
//                case DOWN -> down = 1;
//                case LEFT -> left = 1;
//                case RIGHT -> right = 1;
//                case SHIFT -> {
//                    if (press) {
//                        press = false;
//                        hero.setXDir(0);
//                        hero.setYDir(0);
//                    }
//                }
//                case ENTER -> newGame();
//                case CONTROL -> invincibility = true;
//            }
//            press = true;
//        });
//        scene.setOnKeyReleased(event -> {
//            switch (event.getCode()) {
//                case UP -> up = 0;
//                case DOWN -> down = 0;
//                case LEFT -> left = 0;
//                case RIGHT -> right = 0;
//            }
//        });
//        primaryStage.setTitle("Froggy Road");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//    private void setInitialLocations() {
//        hero.setXLoc(298);
//        hero.setYLoc(400);
//        for (int i = 0; i < numOfStrips; i++) {
//            Sprite[] strip = stripGen.getLandStrip();
//            allStrips[i] = strip;
//        }
//        allStrips[5][3].setImage("Misc/Grass.png");
//        allStrips[4][3].setImage("Misc/Grass.png");
//        int x = 0;
//        int y = -100;
//        for (int i = 0; i < numOfStrips; i++) {
//            for (int z = 0; z < 8; z++) {
//                allStrips[i][z].setXLoc(x);
//                allStrips[i][z].setYLoc(y);
//                x += 100;
//            }
//            x = 0;
//            y += 100;
//        }
//        for (int i = 0; i < 8; i++) {
//            if (allStrips[0][i].getFileName().equals("Misc/Grass.png")) {
//                special.add(i);
//                land++;
//            }
//        }
//    }
//    private void newGame() {
//        if (newGame) {
//            Stage stage = (Stage) startButton.getScene().getWindow();
//            stage.close();
//            start(new Stage());
//        }
//    }
//    private void killMsg(String killer) {
//        gameLoop.stop();
//        scoreManager.updateScores(score);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Game Over");
//        alert.setHeaderText(null);
//        alert.setContentText(switch (killer) {
//            case "water" -> "You drowned!";
//            case "tooFarDown" -> "You were trapped!";
//            case "tooFarUp" -> "You left the game!";
//            case "car" -> "You got hit by a car!";
//            case "train" -> "You got hit by a train!";
//            default -> "Game Over!";
//        } + "\nScore: " + score);
//        alert.showAndWait();
//        startButton.setVisible(true);
//        controlsButton.setVisible(true);
//        showLogo = true;
//    }
//    private void heroBounds() {
//        if (invincibility) {
//            invDuration++;
//            if (invDuration == 1) invTimeLeft = 3;
//            if (invDuration == 50) invTimeLeft = 2;
//            if (invDuration == 100) invTimeLeft = 1;
//            if (invDuration == 150) {
//                invTimeLeft = 0;
//                invincibility = false;
//            }
//        }
//        for (int i = 0; i < numOfStrips; i++) {
//            for (Sprite s : allStrips[i]) {
//                if (!invincibility) {
//                    //Prevents hero from jumping through trees.
//                    if (s.getFileName().equals("Misc/Tree_One.png") || s.getFileName().equals("Misc/Tree_Two.png")) {
//                        if (collision(hero, s)) {
//                            if ((s.getYLoc() + 100) - (hero.getYLoc()) < 5 && (s.getXLoc() + 100) - hero.getXLoc() < 125 && (s.getXLoc() + 100) - hero.getXLoc() > 20) {
//                                up = 0;
//                            } else if ((hero.getYLoc() + 105) > (s.getYLoc()) && (hero.getXLoc() + 100) - s.getXLoc() < 125 && (hero.getXLoc() + 100) - s.getXLoc() > 20) {
//                                down = 0;
//                            } else if (hero.getXLoc() - (s.getXLoc() + 100) > -5 && (s.getYLoc() + 100) - hero.getYLoc() < 125 && (s.getYLoc() + 100) - hero.getYLoc() > 20) {
//                                left = 0;
//                            } else if (s.getXLoc() - (hero.getXLoc() + 100) > -5 && (hero.getYLoc() + 100) - s.getYLoc() < 125 && (hero.getYLoc() + 100) - s.getYLoc() > 20) {
//                                right = 0;
//                            }
//                        }
//                    }
//                    //Ends game if user lands on water.
//                    if (s.getFileName().equals("Misc/Water.png")) {
//                        if (s.getXLoc() - hero.getXLoc() > 0 && s.getXLoc() - hero.getXLoc() < 10) {
//                            if (s.getYLoc() - hero.getYLoc() > 0 && s.getYLoc() - hero.getYLoc() < 10) {
//
//                                //Method to end game.
//                                killMsg("water");
//                            }
//                        }
//                    }
//                }
//                //Ends game if user goes too far down.
//                if (hero.getYLoc() > 800) {
//
//                    //Reset hero location.
//                    hero.setYLoc(500);
//                    hero.setXLoc(900);
//
//                    //Method to end game.
//                    killMsg("tooFarDown");
//                }
//
//                //Ends game if user goes too far up.
//                if (hero.getYLoc() < -110) {
//
//                    //Reset hero location.
//                    hero.setYLoc(500);
//                    hero.setXLoc(900);
//
//                    //Method to end game.
//                    killMsg("tooFarUp");
//                }
//            }
//        }
//    }
//    private void jumpHero() {
//        //Holds the hero's location.
//        int location;
//        //If left/right is pressed.
//        if (left > 0 && press) {
//            hero.setXDir(-12.5);
//            left--;
//            hero.setImage("Frog/Frog_Left.png");
//        } else if (right > 0 && press) {
//            hero.setXDir(12.5);
//            right--;
//            hero.setImage("Frog/Frog_Right.png");
//        } else if (left == 0 && right == 0 && up == 0 && down == 0) {
//            hero.setXDir(0);
//            press = false;
//        }
//        //If up is pressed.
//        if (up > 0 && press) {
//            //Set hero speed.
//            hero.setYDir(-10);
//            hero.move();
//            hero.setImage("Frog/Frog_up.png");
//            //Get hero Y location.
//            location = hero.getYLoc();
//            //Sets the hero's location up one strip.
//            for (int i = 0; i < numOfStrips; i++) {
//                Sprite x = allStrips[i][0];
//                //Aligns hero to strip after movement.
//                if (location - x.getYLoc() > 95 && location - x.getYLoc() < 105) {
//                    hero.setYDir(0);
//                    up = 0;
//                    press = false;
//                    hero.setYLoc(x.getYLoc() + 101);
//                    //Increases travel keeper.
//                    movement++;
//                    i = numOfStrips;
//                }
//            }
//        }
//        //If down in pressed.
//        else if (down > 0 && press) {
//            //Set hero speed.
//            hero.setYDir(10);
//            hero.move();
//            hero.setImage("Frog/Frog_Down.png");
//            //Get hero location
//            location = hero.getYLoc();
//            //Sets the heros location down one strip.
//            for (int i = 0; i < numOfStrips; i++) {
//                Sprite x = allStrips[i][0];
//                //Align hero to strip after movement.
//                if (location - x.getYLoc() < -95 && location - x.getYLoc() > -105) {
//                    hero.setYDir(0);
//                    down = 0;
//                    press = false;
//                    hero.setYLoc(x.getYLoc() - 99);
//                    //location = x.getYLoc() - 100;
//                    //Decreases travel keeper.
//                    movement--;
//                    i = numOfStrips;
//                }
//            }
//        }
//    }
//    private void manageCars() {
//        //Cycles through car sprites.
//        for (int i = 0; i < cars.size(); i++) {
//            Sprite car = cars.get(i);
//            //Removes cars passing Y bounds.
//            if (car.getYLoc() > 800) {
//                cars.remove(i);
//            }else {
//                //Moves car sprites.
//                car.move();
//                //Reset cars passing X bounds.
//                if (car.getXLoc() < -(rand.nextInt(700) + 400)){
//                    //Right to left.
//                    car.setXDir(-(rand.nextInt(10) + 10));
//                    car.setXLoc(900);
//                    car.setImage(vManager.randomCar("left"));
//                } else if (car.getXLoc() > (rand.nextInt(700) + 1100)) {
//                    //Left to right.
//                    car.setXDir((rand.nextInt(10) + 10));
//                    car.setXLoc(-200);
//                    car.setImage(vManager.randomCar("right"));
//                }
//            }
//            //Checks for car collisions.
//            if (collision(car, hero) && !invincibility) {
//                //Method to end game.
//                killMsg("car");
//            }
//        }
//    }
//    /**
//     * Method that:
//     * Moves trains.
//     * Removes trains passing Y bounds.
//     * Checks for train collisions.
//     */
//    private void manageTrains() {
//        //Cycles through car sprites.
//        for (int i = 0; i < trains.size(); i++) {
//            Sprite train = trains.get(i);
//            //Removes trains passing Y bounds.
//            if (train.getYLoc() > 800) {
//                trains.remove(i);
//            }else {
//                //Moves train sprites.
//                train.move();
//                //Reset X bounds.
//                if (train.getXLoc() < -(rand.nextInt(2500) + 2600)) {
//                    train.setXDir(-(rand.nextInt(10) + 30));
//                    train.setXLoc(900);
//                    train.setImage(vManager.randomTrain());
//                } else if (train.getXLoc() > rand.nextInt(2500) + 1800) {
//                    train.setXDir((rand.nextInt(10) + 30));
//                    train.setXLoc(-1500);
//                    train.setImage(vManager.randomTrain());
//                }
//            }
//            //Checks for train collisions.
//            if (collision(train, hero) && !invincibility) {
//                //Method to end game.
//                killMsg("train");
//            }
//        }
//    }
//    /**
//     * Method that correctly resets the strips.
//     */
//    private void manageStrips() {
//        //Blank strip test variables.
//        int allWater;
//        int allGrass;
//        //Cycles through each strip.
//        for (int v = 0; v < numOfStrips; v++) {
//            //Checks if strip is out of bounds.
//            if (allStrips[v][0].getYLoc() > 800) {
//                //Generates a new strip.
//                allStrips[v] = stripGen.getStrip();
//                //Prevents an all water or grass strip.
//                do {
//                    //Reset variables.
//                    allWater = 0;
//                    allGrass = 0;
//                    //Check sprites in strip.
//                    for (Sprite s : allStrips[v]) {
//                        if (s.getFileName().equals("Misc/Water.png"))
//                            allWater++;
//                        if (s.getFileName().equals("Misc/Grass.png"))
//                            allGrass++;
//                    }
//                    if (allWater == 8)
//                        allStrips[v] = stripGen.getWaterStrip();
//                    if (allGrass == 8)
//                        allStrips[v] = stripGen.getLandStrip();
//                } while (allWater == 8 || allGrass == 8);
//                //If there was previously a water strip, and this strip is a water strip, match this strips lillypads to the previous strip.
//                if (water > 0) {
//                    if (allStrips[v][0].getFileName().equals("Misc/Water.png") || allStrips[v][0].getFileName().equals("Misc/Lillypad.png")) {
//                        water = 0;
//                        for (int i : special) {
//                            allStrips[v][i].setImage("Misc/Lillypad.png");
//                        }
//                    }
//                }
//                //If there was previously a water strip, and this strip is a land strip, match the grass to the previous strips lillypads.
//                if (water > 0) {
//                    if (allStrips[v][0].getFileName().equals("Misc/Grass.png") || allStrips[v][0].getFileName().equals("Misc/Shrub.png") ||
//                            allStrips[v][0].getFileName().equals("Misc/Tree_One.png") || allStrips[v][0].getFileName().equals("Misc/Tree_Two.png")) {
//                        allStrips[v] = stripGen.getSpecialLandStrip();
//                        water = 0;
//                        for (int i : special) {
//                            allStrips[v][i].setImage("Misc/Grass.png");
//                        }
//                    }
//                }
//                //If there was previously a land strip, and this strip is a water strip, match the lillypads to the grass.
//                if (land > 0) {
//                    if (allStrips[v][0].getFileName().equals("Misc/Water.png") || allStrips[v][0].getFileName().equals("LilyPad.png")) {
//                        land = 0;
//                        int val = 0;
//                        while (val == 0) {
//                            allStrips[v] = stripGen.getWaterStrip();
//                            for (int i = 0; i < 8; i++) {
//                                if (allStrips[v][i].getFileName().equals("Misc/Lillypad.png")) {
//                                    //TODO: Remove
//                                    for(int s : special){
//                                        if (i == s) {
//                                            val++;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                //if there is a water strip, write down the index of the Lillypads.
//                if (allStrips[v][0].getFileName().equals("Misc/Water.png") || allStrips[v][0].getFileName().equals("Misc/Lillypad.png")) {
//                    special.clear();
//                    water = 0;
//                    for (int i = 0; i < 8; i++) {
//                        if (allStrips[v][i].getFileName().equals("Misc/Lillypad.png")) {
//                            special.add(i);
//                            water++;
//                        }
//                    }
//                } else
//                    water = 0;
//                //if there is a land strip, write down the index of the grass.
//                if (allStrips[v][0].getFileName().equals("Misc/Grass.png") || allStrips[v][0].getFileName().equals("Misc/Shrub.png") ||
//                        allStrips[v][0].getFileName().equals("Misc/Tree_One.png") || allStrips[v][0].getFileName().equals("Misc/Tree_Two.png")) {
//                    special.clear();
//                    land = 0;
//                    for (int i = 0; i < 8; i++) {
//                        if (allStrips[v][i].getFileName().equals("Misc/Grass.png")) {
//                            special.add(i);
//                            land++;
//                        }
//                    }
//                }
//                //Variable to reset horizontal strip location.
//                int X = 0;
//                //Reset the location of the strip.
//                for (int i = 0; i < 8; i++) {
//                    allStrips[v][i].setYLoc(-99);
//                    allStrips[v][i].setXLoc(X);
//                    X += 100;
//                }
//                //Set car.
//                if (allStrips[v][0].getFileName().equals("Misc/Road.png")){
//                    cars.add(vManager.setCar(allStrips[v][0].getYLoc() + 10));
//                }
//                //Set train.
//                if (allStrips[v][0].getFileName().equals("Misc/Tracks.png")) {
//                    trains.add(vManager.setTrain(allStrips[v][0].getYLoc() + 10));
//                }
//            }
//        }
//    }
//    /**
//     * Scrolls the strips and the hero.
//     */
//    private void scrollScreen() {
//        //Cycles through strip array.
//        for (int v = 0; v < numOfStrips; v++) {
//            for (int x = 0; x < 8; x++) {
//                allStrips[v][x].setYDir(2);
//            }
//        }
//        //Sets scrolling if hero is not moving.
//        if (!press) {
//            hero.setYDir(2);
//        }
//    }
//    /**
//     * Checks for sprite collisions.
//     */
//    private boolean collision(Sprite one, Sprite two) {
//        //Creates rectangles around sprites and checks for interesection.
//        Rectangle first = new Rectangle(one.getXLoc(), one.getYLoc(), one.getWidth(), one.getHeight());
//        Rectangle second = new Rectangle(two.getXLoc(), two.getYLoc(), two.getWidth(), two.getHeight());
//        // Lấy bounds trong parent để kiểm tra sự giao nhau
//        Bounds boundsFirst = first.getBoundsInParent();
//        Bounds boundsSecond = second.getBoundsInParent();
//        return boundsFirst.intersects(boundsSecond);
////        // Tạo bounds từ thông tin của các sprite
////        Bounds boundsFirst = new Rectangle2D(one.getXLoc(), one.getYLoc(), one.getWidth(), one.getHeight());
////        Bounds boundsSecond = new Rectangle2D(two.getXLoc(), two.getYLoc(), two.getWidth(), two.getHeight());
////
////        // Kiểm tra sự giao nhau
////        return boundsFirst.intersects(boundsSecond);
//    }
//    /**
//     * Draws graphics onto screen.
//     */
//    public Display(double width, double height) {
//        super();
//        // Khởi tạo các sprite và strip nếu cần thiết
//        startButton.setFocusTraversable(true);
//        controlsButton.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressing());
//        controlsButton.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleasing());
//        gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//                paintComponent();
//            }
//        };
//    }
//    public void paintComponent() {
//        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        //Erases the previous screen.
//        g.clearRect(0, 0, hero.getWidth(),hero.getHeight());
//        //Draws strips.
//        for (int i = 0; i < numOfStrips; i++) {
//            for (int x = 0; x < 8; x++) {
//                allStrips[i][x].paint(g);
//            }
//        }
//        //Draw hero sprite.
//        hero.paint(g);
//        //Draw car sprites.
//        for (Sprite s : cars)
//            s.paint(g);
//        //Draw train sprites.
//        for (Sprite s : trains)
//            s.paint(g);
//        // Set the font size and color for the score.
//        Font currentFont = g.getFont();
//        Font newFont = new Font(currentFont.getName(), currentFont.getSize() * 3);
//        g.setFont(newFont);
////        g.setFill(Color.YELLOW);
//        // Draw the score on the screen.
//        g.fillText("" + score, 50, 150);
//        // Set the font size and color for invincibility status.
//        newFont = new Font(currentFont.getName(), currentFont.getSize());
//        g.setFont(newFont);
////        g.setFill(Color.RED);
//        // Draw invincibility status.
//        if (invincibility)
//            g.fillText("" + invTimeLeft, 350, 350);
//        // Draw the logo on the screen.
////        if (showLogo) {
////            g.drawImage(logo, 175, 75);
////        }
//        // Stop stuttering (Linux issue).
//    }
//    /**
//     * Reads keyboard input for moving
//     * when key is pressed down.
//     */
//    private class KeyPressing implements EventHandler<KeyEvent> {
//        @Override
//        public void handle(KeyEvent e) {
//            switch (e.getCode()) {
//                case RIGHT:
//                    if (!press && hero.getXLoc() < 695) {
//                        right = 8;
//                        press = true;
//                    }
//                    break;
//                case LEFT:
//                    if (!press && hero.getXLoc() > 0) {
//                        left = 8;
//                        press = true;
//                    }
//                    break;
//                case UP:
//                    if (!press) {
//                        up = 10;
//                        press = true;
//                    }
//                    break;
//                case DOWN:
//                    if (!press) {
//                        down = 10;
//                        press = true;
//                    }
//                    break;
//                case CONTROL:
//                    if (!invincibility && invDuration < 150)
//                        invincibility = true;
//                    break;
//                case SHIFT:
//                    if (isGameLoopRunning) {
//                        gameLoop.stop();
//                        isGameLoopRunning = false;
//                    } else {
//                        gameLoop.start();
//                        isGameLoopRunning = true;
//                    }
//                    break;
//                case ENTER:
//                    if (!isGameLoopRunning) {
//                        newGame = true;
//                        newGame();
//                    }
//                    break;
//            }
//        }
//    }
//    private class KeyReleasing implements EventHandler<KeyEvent> {
//        @Override
//        public void handle(KeyEvent e) {
//            switch (e.getCode()) {
//                case RIGHT:
//                    hero.setXDir(0);
//                    break;
//                case LEFT:
//                    hero.setXDir(0);
//                    break;
//                case UP:
//                    hero.setYDir(2);
//                    break;
//                case DOWN:
//                    hero.setYDir(2);
//                    break;
//            }
//        }
//    }
//}
