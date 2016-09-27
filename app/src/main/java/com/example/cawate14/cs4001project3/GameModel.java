package com.example.cawate14.cs4001project3;

import java.util.Random;

public class GameModel {

    private int[] tiles = null;

    public GameModel(int image_count) {
        tiles = new int[image_count * 2];
        for(int i = 0; i < image_count; ++i){
            tiles[i*2] = i;
            tiles[i*2+1] = i;
        }
        shuffleTiles(tiles);
    }

    // http://stackoverflow.com/a/1520212
    private static void shuffleTiles(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
}
