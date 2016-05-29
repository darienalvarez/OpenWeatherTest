package com.darien.openweathertest.util;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.darien.openweathertest.view.TextDrawable;

/**
 * Created by Darien
 * on 5/27/16.
 *
 * Helper to take pictures
 */
public class CameraUtil {

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    public static void dispatchTakePictureIntent(Fragment fragment) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    /**
     * Overlay a text on a corner of a image
     *
     * @param resources   Resources instance
     * @param source      Background bitmap
     * @param temperature Overlay text (temperature)
     * @return Drawable with and image with text overlay
     */
    public static Drawable overlayText(Resources resources, Bitmap source, String temperature) {
        Drawable[] layers = new Drawable[2];
        layers[0] = new BitmapDrawable(resources, source);
        layers[1] = new TextDrawable(resources, temperature);
        return new LayerDrawable(layers);
    }

}
