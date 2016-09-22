package com.example.cawate14.cs4001project3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

public class GameLayout extends RelativeLayout {

    public GameLayout(Context context) {
        super(context);
    }

    public GameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int dimen = Math.min(widthSpec, heightSpec);
        super.onMeasure(dimen, dimen);
//        RelativeLayout layout = (RelativeLayout) getParent();
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
//        params.width = dimen;
//        params.height = dimen;
//        layout.setLayoutParams(params);
    }
}
