package com.darien.openweathertest.services;

import com.darien.openweathertest.pojo.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Darien
 * on 5/26/16.
 */
public interface OpenWeatherMapService {

    /**
     * Request a weather by zip
     * /weather?zip=94040,us
     *
     * @param zip zip code, country code
     * @return Forecast instance
     */
    @GET("weather")
    Call<Forecast> getForecastByZip(@Query("zip") String zip);

}
