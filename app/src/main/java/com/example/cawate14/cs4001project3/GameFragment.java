package com.example.cawate14.cs4001project3;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class GameFragment extends Fragment implements GameViewControl {

    private static final Integer[] resimg = {
            R.drawable.badaxx,
            R.drawable.colorcube,
            R.drawable.colors,
            R.drawable.cute,
            R.drawable.firefox,
            R.drawable.fog,
            R.drawable.gone_fishing,
            R.drawable.illustris_box_dmdens_gasvel,
            R.drawable.sunset,
            R.drawable.torus,
    };

    private GameModel model = null;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Integer> images = new ArrayList<>(Arrays.asList(resimg));
    private Handler handler = null;
    private Timer fliptimer = null;

    private Drawable tileColorFront;
    private Drawable tileColorBack;
    private Drawable tileColorMatch;
    private Drawable tileColorNoMatch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        handler = new Handler();
        fliptimer = new Timer();
        Collections.shuffle(images);
    }

    public void setModel(GameModel gmodel){
        model = gmodel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        // Setup grid
        GridLayout grid = (GridLayout) root.findViewById(R.id.gamegrid);
        grid.setColumnCount(getClosestFactors(model.getNumTiles()));

        // Init image views
        tiles.clear();
        for(int i = 0; i < model.getNumTiles(); ++i){
            final int ti = i;
            final GameModel gmodel = model;

            // Create tile layout container
            final TileLayout tilelayout = new TileLayout(getContext());
            ViewGroup.LayoutParams tileParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tilelayout.setLayoutParams(tileParam);
            tilelayout.setPadding(10, 10, 10, 10);

            ViewGroup.LayoutParams imageParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tileColorFront = new ColorDrawable(ContextCompat.getColor(getContext(), R.color.tileFront));
            tileColorBack = new ColorDrawable(ContextCompat.getColor(getContext(), R.color.tileBack));
            tileColorMatch = new ColorDrawable(ContextCompat.getColor(getContext(), R.color.tileMatch));
            tileColorNoMatch = new ColorDrawable(ContextCompat.getColor(getContext(), R.color.tileNoMatch));

            // Front of tile
            final ImageView front = new ImageView(getContext());
            front.setLayoutParams(imageParam);
            front.setBackground(tileColorFront);

            // Back of tile
            final ImageView back = new ImageView(getContext());
            back.setLayoutParams(imageParam);
            back.setBackground(tileColorBack);
            //back.setAdjustViewBounds(true);
            back.setScaleType(ImageView.ScaleType.CENTER_CROP);
            back.setPadding(10, 10, 10, 10);
            back.setCropToPadding(true);

            // Setup layout callback for measurement
            ViewTreeObserver vto = front.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // Remove the layout listener
                    front.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    // Get real dimensions
                    int x = front.getMeasuredWidth();
                    int y = front.getMeasuredHeight();
                    //Log.d("DBG", "Resize to " + x + "," + y);

                    // Load image in background
                    ImageResourceWorkerTask imageWorker = new ImageResourceWorkerTask(getResources(), back, x, y);
                    imageWorker.execute(images.get(model.getImageId(ti)));

                    // Set fixed tile dimensions
                    int tilex = tilelayout.getMeasuredWidth();
                    int tiley = tilelayout.getMeasuredHeight();
                    tilelayout.setFixedDimensions(tilex, tiley);
                }
            });

            // Flip animator
            ValueAnimator flipAnimator = ValueAnimator.ofFloat(0f, 1f);
            flipAnimator.addUpdateListener(new FlipListener(front, back, model.getFlipped(i)));

            // Click listener
            front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Update game model
                    gmodel.clickTile(ti);
                }
            });

            // Add image views
            tilelayout.addView(front);
            tilelayout.addView(back);

            // Add tile to grid
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1, 1f), GridLayout.spec(GridLayout.UNDEFINED, 1, 1f));
            //gridParam.setMargins(10, 10, 10, 10);
            grid.addView(tilelayout, gridParam);

            // Store the views and animators
            Tile tile = new Tile();
            tile.layout = tilelayout;
            tile.front = front;
            tile.back = back;
            tile.anim = flipAnimator;
            tiles.add(tile);
        }

        return root;
    }

    // Based on http://stackoverflow.com/a/28328782/4526277
    private static int getClosestFactors(int in){
        int test = (int)Math.floor(Math.sqrt(in));
        while(in % test != 0){
            test--;
        }
        return test;
    }

    @Override
    public void flipTile(final int i) {
        tiles.get(i).anim.start();
        TextView score = (TextView) getActivity().findViewById(R.id.score);
        score.setText(Integer.toString(model.getFlipCount()));
    }

    @Override
    public void unFlipTile(int i) {
        fliptimer.schedule(new FlipTimer(i), 1000);
    }

    @Override
    public void tilesMatched(int i, int j) {
        tiles.get(i).back.setBackground(tileColorMatch);
        tiles.get(j).back.setBackground(tileColorMatch);
    }

    @Override
    public void tilesNotMatched(int i, int j) {
        tiles.get(i).back.setBackground(tileColorNoMatch);
        tiles.get(j).back.setBackground(tileColorNoMatch);
    }

    @Override
    public void gameFinished() {
        // Finish the activity
        Intent result = new Intent();
        result.putExtra("SCORE_RESULT", model.getFlipCount());
        getActivity().setResult(Activity.RESULT_OK, result);
        getActivity().finish();
    }

    class Tile {
        public TileLayout layout;
        public ImageView front;
        public ImageView back;
        public ValueAnimator anim;
    }

    class FlipTimer extends TimerTask {

        private int tile;

        public FlipTimer(int i){
            tile = i;
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // Reverse flip
                    tiles.get(tile).anim.reverse();
                    tiles.get(tile).back.setBackground(tileColorBack);
                }
            });
        }
    }

}
