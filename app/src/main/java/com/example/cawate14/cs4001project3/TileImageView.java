package com.example.cawate14.cs4001project3;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TileImageView extends ImageView {

    public TileImageView(Context context) {
        super(context);
    }
    public TileImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TileImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public TileImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int maxWidth = getMaxWidth();
        int maxHeight = getMaxHeight();

        if (specWidth > maxWidth)
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.getMode(widthMeasureSpec));

        if (specHeight > maxHeight)
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.getMode(heightMeasureSpec));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
