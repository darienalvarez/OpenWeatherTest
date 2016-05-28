package com.darien.openweathertest.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Darien
 * on 5/27/16.
 */
public class TwitterUtil {

    private static final String TAG = "TwitterUtil";

    public static void sendTweet(Context context, String text) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=" + urlEncode(text)));
        context.startActivity(browserIntent);

    }

    private static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.wtf(TAG, "UTF-8 should always be supported", e);
            return "";
        }
    }

}
