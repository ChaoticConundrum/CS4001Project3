package com.example.cawate14.cs4001project3;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class GameFragment extends Fragment {

    /*
    private final int[][] tileids = {
            { R.id.tile11, R.id.tile21, R.id.tile31, R.id.tile41 },
            { R.id.tile12, R.id.tile22, R.id.tile32, R.id.tile42 },
            { R.id.tile13, R.id.tile23, R.id.tile33, R.id.tile43 },
            { R.id.tile14, R.id.tile24, R.id.tile34, R.id.tile44 },
    };
    */

    private GameModel model = null;

    private ArrayList<ImageView> tileviews = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        model = new GameModel(8);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        GridLayout grid = (GridLayout) root.findViewById(R.id.gamegrid);
        ImageView first = (ImageView) root.findViewById(R.id.tile0);

        // Init image views
        tileviews.clear();
        for(int i = 1; i < 16; ++i){
            //ImageView tile = (ImageView) root.findViewById(tileids[i][j]);
            ImageView front = new ImageView(getActivity());
            front.setLayoutParams(first.getLayoutParams());
            front.setBackground(first.getBackground());

            // This is some black magic here
            final Animator anim = AnimatorInflater.loadAnimator(getContext(), R.animator.tile_bounce);
            anim.setTarget(front);
            final int x = i;
            front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    anim.cancel();
                    anim.start();
                    Log.d("DBG", "Clicked " + x);
                }
            });

            grid.addView(front, i);
            // Need to store the references
                tileviews.add(front);
        }

        return root;
    }

}
