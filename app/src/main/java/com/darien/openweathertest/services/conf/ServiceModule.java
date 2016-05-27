package com.darien.openweathertest.services.conf;

import com.darien.openweathertest.services.OpenWeatherMapService;
import com.darien.openweathertest.services.TokenInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Darien
 * on 5/26/16.
 */
@Module(
        library = true,
        complete = false
)
public class ServiceModule {

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .build(); //create OKHTTPClient
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    OpenWeatherMapService provideOpenWeatherMapService(Retrofit retrofit) {
        return retrofit.create(OpenWeatherMapService.class);
    }
}
