package com.example.cawate14.cs4001project3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class ImageResourceWorkerTask extends AsyncTask<Integer, Void, Drawable> {

    private final Resources resources;
    private final WeakReference<ImageView> imageViewReference;
    private final int dimx, dimy;
    private int resid = 0;

    public ImageResourceWorkerTask(Resources resource, ImageView imageView, int x, int y){
        resources = resource;
        imageViewReference = new WeakReference<>(imageView);
        dimx = x;
        dimy = y;
    }

    @Override
    protected Drawable doInBackground(Integer... params) {
        resid = params[0];
        return ImageUtility.decodeSampledBitmapFromResource(resources, resid, dimx, dimy);
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        Log.d("DBG", "Image loaded " + resid);
        if (imageViewReference != null && drawable != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setBackground(drawable);
            }
        }
    }

}
