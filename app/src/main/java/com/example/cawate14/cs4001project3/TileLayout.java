package com.example.cawate14.cs4001project3;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

public class TileLayout extends GridLayout {

    int fixWidth = 0;
    int fixHeight = 0;

    public TileLayout(Context context) {
        super(context);
    }
    public TileLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public TileLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public TileLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setFixedDimensions(int width, int height){
        fixWidth = width;
        fixHeight = height;
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int width = MeasureSpec.getSize(widthSpec);
        int height = MeasureSpec.getSize(heightSpec);
        if(fixWidth == 0 || fixHeight == 0){
            // Fixed dimensions unset, make sure view layout sizes itself correctly
            super.onMeasure(widthSpec, heightSpec);
        } else {
            // Force layout size to measured fixed dimensions
            super.onMeasure(MeasureSpec.makeMeasureSpec(fixWidth, MeasureSpec.EXACTLY),
                            MeasureSpec.makeMeasureSpec(fixHeight, MeasureSpec.EXACTLY));
        }
    }
}
