package com.example.cawate14.cs4001project3;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class GameActivity extends Activity {

    private int imageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment instanceof GameFragment) {
            imageCount = getIntent().getIntExtra("IMAGE_COUNT", 8);

            GameFragment gameFragment = (GameFragment) fragment;
            GameModel model = new GameModel(gameFragment, imageCount);
            gameFragment.setModel(model);
        }
    }

}
