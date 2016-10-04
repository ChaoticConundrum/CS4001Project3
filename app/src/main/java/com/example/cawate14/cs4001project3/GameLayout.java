package com.example.cawate14.cs4001project3;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/*  This layout just overloads the onMeasure method, and resizes the layout to
 *  be square with width and height equal to the smallest dimension of the parent.
 */
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
    }
}
