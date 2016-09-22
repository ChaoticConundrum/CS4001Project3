package com.example.cawate14.cs4001project3;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class GameFragment extends Fragment {

    private final int[][] tileids = {
            { R.id.tile11, R.id.tile21, R.id.tile31, R.id.tile41 },
            { R.id.tile12, R.id.tile22, R.id.tile32, R.id.tile42 },
            { R.id.tile13, R.id.tile23, R.id.tile33, R.id.tile43 },
            { R.id.tile14, R.id.tile24, R.id.tile34, R.id.tile44 },
    };

    private ArrayList<ImageView> tileviews = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        // Init image views
        tileviews.clear();
        for(int i = 0; i < 4; ++i){
            for(int j = 0; j < 4; ++j){
                ImageView tile = (ImageView) root.findViewById(tileids[i][j]);

                // This is some black magic here
                final Animator anim = AnimatorInflater.loadAnimator(getContext(), R.animator.tile_bounce);
                anim.setTarget(tile);
                final int x = i;
                final int y = j;
                tile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        anim.cancel();
                        anim.start();
                        Log.d("DBG", "Clicked " + x + ", " + y);
                    }
                });
                // Need to store the references
                tileviews.add(tile);
            }
        }

        return root;
    }

}
