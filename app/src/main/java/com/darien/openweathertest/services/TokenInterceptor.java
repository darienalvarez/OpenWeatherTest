package com.darien.openweathertest.services;

import com.darien.openweathertest.WeatherApplication;
import com.darien.openweathertest.util.PreferencesUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by darien
 * on 12/27/15.
 */
public class TokenInterceptor implements Interceptor {

    private final String APP_ID = "3b535043693316ba125a0513276aa62d";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("appid", APP_ID)
                .addQueryParameter("units", PreferencesUtil.getInstance(WeatherApplication.CONTEXT)
                        .getTemperatureUnit())
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
