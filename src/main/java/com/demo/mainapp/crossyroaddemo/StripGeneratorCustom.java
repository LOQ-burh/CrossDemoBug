package com.demo.mainapp.crossyroaddemo;

import javafx.scene.image.Image;
import java.util.Random;

class StripGeneratorCustom {
    // Tạo ra một dải sprite với 8 phần tử. Dải này có thể là dải đường, dải đường sắt, dải đất đặc biệt hoặc dải nước
    // tùy thuộc vào số ngẫu nhiên được sinh ra.

    // Tùy thuộc vào giá trị ngẫu nhiên, dải sprite được tạo ra có thể là một trong bốn loại cảnh: đường, đường sắt, đất
    // đặc biệt, hoặc nước.
    SpriteCustom[] getStrip() {
        // Array to hold strip.
        SpriteCustom[] spriteStrip = new SpriteCustom[8];
        Random gen = new Random();
        // Number of grids wide.
        int y = spriteStrip.length;
        // Selects landscape.
        int x = gen.nextInt(4);
        // Sets landscape.
        switch (x) {
            // Road.
            case 0:
                for (int i = 0; i < y; i++) {
                    SpriteCustom strip = new SpriteCustom("/assets/Materials/Road/road-1.png");
                    spriteStrip[i] = strip;
                }
                break;
            // Tracks.
            case 1:
                for (int i = 0; i < y; i++) {
                    SpriteCustom strip = new SpriteCustom("/assets/Materials/Road/road-1.png");
                    spriteStrip[i] = strip;
                }
                break;
            // Special Land.
            case 2:
                for (int i = 0; i < y; i++) {
                    // Holds random number.
                    x = gen.nextInt(5);
                    spriteStrip[i] = makeSpecialStrip(i, x, "/assets/Materials/Glass/Safe_terrain.jpg",
                            "/assets/Materials/Glass/Safe_terrain.jpg",
                            "/assets/Materials/Glass/Safe_terrain.jpg");
                }
                break;
            // Special Water.
            case 3:
                for (int i = 0; i < y; i++) {
                    // Holds random number.
                    x = gen.nextInt(5);
                    spriteStrip[i] = makeSpecialStrip(i, x,
                            "/assets/Materials/Water/water-main.png",
                            "/assets/Materials/Water/water-main.png",
                            "/assets/Materials/Water/water-main.png");
                }
        }
        return spriteStrip;
    }

    // Tạo ra một dải đặc biệt với các khối chướng ngại hoặc nền đặc biệt. -> land/water
    // Đây là phương thức tạo ra các Sprite với hình ảnh khác nhau tùy thuộc vào giá trị ngẫu nhiên.
    // Các hình ảnh chướng ngại hoặc vật phẩm đặc biệt. -> specialBlockOne, specialBlockTwo
    private SpriteCustom makeSpecialStrip(int i, int x, String background, String specialBlockOne, String specialBlockTwo) {
        SpriteCustom oneBlock = new SpriteCustom();
        switch (x) {
            case 0:
                oneBlock.setImage(new Image(SpriteCustom.class.getResourceAsStream(background)));
                break;
            case 1:
                oneBlock.setImage(new Image(SpriteCustom.class.getResourceAsStream(background)));
                break;
            case 2:
                oneBlock.setImage(new Image(SpriteCustom.class.getResourceAsStream(background)));
                break;
            // Add blockage.
            case 3:
                oneBlock.setImage(new Image(SpriteCustom.class.getResourceAsStream(specialBlockOne)));
                break;
            // Add blockage.
            case 4:
                oneBlock.setImage(new Image(SpriteCustom.class.getResourceAsStream(specialBlockTwo)));
                break;
        }
        // Adds image to strip.
        return oneBlock;
    }

    // Tạo ra một dải đất với các Sprite chứa hình ảnh của cỏ và các vật phẩm đặc biệt,
    // như cây cối hoặc bụi cây, tùy thuộc vào giá trị ngẫu nhiên.
    SpriteCustom[] getLandStrip() {
        Random gen = new Random();
        // Array to hold strip.
        SpriteCustom[] spriteStrip = new SpriteCustom[8];
        for (int i = 0; i < 8; i++) {
            // Holds random number.
            int x = gen.nextInt(5);
            spriteStrip[i] = makeSpecialStrip(i, x, "/assets/Materials/Glass/Safe_terrain.jpg",
                    "/assets/Materials/Glass/Safe_terrain.jpg",
                    "/assets/Materials/Glass/Safe_terrain.jpg");
        }
        return spriteStrip;
    }

    // Tạo ra một dải đất đặc biệt với các hình ảnh khác nhau, như cỏ, cây, hoặc bụi cây, để làm cho dải trở nên đa dạng hơn.
    SpriteCustom[] getSpecialLandStrip() {
        Random gen = new Random();
        // Array to hold strip.
        SpriteCustom[] spriteStrip = new SpriteCustom[8];
        for (int i = 0; i < 8; i++) {
            // Holds random number.
            int x = gen.nextInt(5);
            spriteStrip[i] = makeSpecialStrip(i, x, "/assets/Materials/Glass/Safe_terrain.jpg",
                    "/assets/Materials/Glass/Safe_terrain.jpg",
                    "/assets/Materials/Glass/Safe_terrain.jpg");
        }
        return spriteStrip;
    }

    // Tạo ra một dải nước với các Sprite chứa hình ảnh của nước và các vật phẩm đặc biệt như lillypad.
    SpriteCustom[] getWaterStrip() {
        Random gen = new Random();
        // Array to hold strip.
        SpriteCustom[] spriteStrip = new SpriteCustom[8];
        for (int i = 0; i < 8; i++) {
            // Holds random number.
            int x = gen.nextInt(5);
            spriteStrip[i] = makeSpecialStrip(i, x, "/assets/Materials/Water/water-main.png",
                    "/assets/Materials/Water/water-main.png",
                    "/assets/Materials/Water/water-main.png");
        }
        return spriteStrip;
    }
}
