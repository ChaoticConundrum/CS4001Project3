package com.example.cawate14.cs4001project3;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;

public class GameModel {

    public enum TileState {
        HIDE,
        FLIP,
        MATCH,
    }

    private GameViewControl view = null;
    private int count;
    private Timer timer = null;
    private int[] images = null;
    private TileState[] state = null;
    private int lastflip = -1;
    private int flipcount = 0;
    private int rempairs;

    public GameModel(GameViewControl gameview, int image_count) {
        view = gameview;
        count = image_count;
        rempairs = count;
        timer = new Timer();

        // Random image array
        ArrayList<Integer> tmpimg = new ArrayList<>();
        for(int i = 0; i < count; ++i){
            tmpimg.add(i);
            tmpimg.add(i);
        }
        Collections.shuffle(tmpimg);

        images = new int[tmpimg.size()];
        for(int i = 0; i < tmpimg.size(); ++i){
            images[i] = tmpimg.get(i);
        }

        // State array
        state = new TileState[count * 2];
        for(int i = 0; i < count * 2; ++i){
            state[i] = TileState.HIDE;
        }
    }

    public void clickTile(int i){
        Log.d("DBG", "Clicked " + i);
        // Flip the tile
        if(state[i] == TileState.HIDE) {
            state[i] = TileState.FLIP;
            ++flipcount;
            view.flipTile(i);

            if (lastflip >= 0) {
                if (images[i] == images[lastflip]) {
                    Log.d("DBG", "Matched " + i + "," + lastflip);
                    // Images match
                    state[i] = TileState.MATCH;
                    state[lastflip] = TileState.MATCH;
                    view.tilesMatched(i, lastflip);

                    // Check if game is finished
                    --rempairs;
                    if(rempairs == 0)
                        view.gameFinished();
                } else {
                    Log.d("DBG", "Not Matched " + i + "," + lastflip);
                    // No match, unflip
                    view.tilesNotMatched(i, lastflip);
                    state[i] = TileState.HIDE;
                    state[lastflip] = TileState.HIDE;
                    view.unFlipTile(i);
                    view.unFlipTile(lastflip);
                }
                lastflip = -1;
            } else {
                lastflip = i;
            }
        }
    }

    public int getImageId(int i){
        return images[i];
    }

    public boolean getFlipped(int i){
        return (state[i] == TileState.FLIP) || (state[i] == TileState.MATCH);
    }

    public int getFlipCount(){
        return flipcount;
    }

    public int getNumTiles(){
        return count * 2;
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
