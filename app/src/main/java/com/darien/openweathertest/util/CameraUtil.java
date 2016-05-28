package com.darien.openweathertest.util;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by Darien
 * on 5/27/16.
 */
public class CameraUtil {

    public static final int REQUEST_IMAGE_CAPTURE = 1;

    public static void dispatchTakePictureIntent(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
