package com.example.cawate14.cs4001project3;

import android.animation.ValueAnimator;
import android.view.View;

// Based on http://stackoverflow.com/a/28430661
public class FlipListener implements ValueAnimator.AnimatorUpdateListener {

    private final View mFrontView;
    private final View mBackView;
    private boolean mFlipped;

    public FlipListener(final View front, final View back, final boolean start) {
        this.mFrontView = front;
        this.mBackView = back;
        mFlipped = start;
        if(!mFlipped)
            this.mBackView.setVisibility(View.GONE);
        else
            this.mFrontView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationUpdate(final ValueAnimator animation) {
        final float value = animation.getAnimatedFraction();
        final float scaleValue = 0.625f + (1.5f * (value - 0.5f) * (value - 0.5f));

        if(value <= 0.5f){
            this.mFrontView.setRotationY(180 * value);
            this.mFrontView.setScaleX(scaleValue);
            this.mFrontView.setScaleY(scaleValue);
            if(mFlipped){
                setStateFlipped(false);
            }
        } else {
            this.mBackView.setRotationY(-180 * (1f- value));
            this.mBackView.setScaleX(scaleValue);
            this.mBackView.setScaleY(scaleValue);
            if(!mFlipped){
                setStateFlipped(true);
            }
        }
    }

    private void setStateFlipped(boolean flipped) {
        mFlipped = flipped;
        this.mFrontView.setVisibility(flipped ? View.GONE : View.VISIBLE);
        this.mBackView.setVisibility(flipped ? View.VISIBLE : View.GONE);
    }
}