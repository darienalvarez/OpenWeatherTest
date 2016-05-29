package com.darien.openweathertest.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.darien.openweathertest.WeatherApplication;
import com.darien.openweathertest.view.activities.SettingsActivity;

/**
 * Created by Darien
 * on 5/28/16.
 *
 * Reads preferences
 */
public class PreferencesUtil {

    private static PreferencesUtil INSTANCE = null;

    private String mTemperatureUnit;

    private PreferencesUtil(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        mTemperatureUnit = sharedPref.getString(SettingsActivity.PREFERENCE_TEMPERATURE_UNIT,
                SettingsActivity.DEFAULT_TEMPERATURE_UNIT);
    }

    /**
     * Return a single instance of PreferenceUtil
     *
     * @return PreferenceUtil instance
     */
    public static PreferencesUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesUtil(WeatherApplication.CONTEXT);
        }

        return INSTANCE;
    }

    /**
     * Use this method when you going to print the temperature unit in the Interface
     * @return F for Fahrenheit, C for Celsius
     */
    public String getTemperatureUnitReadable() {
        if (SettingsActivity.DEFAULT_TEMPERATURE_UNIT.equals(mTemperatureUnit)) {
            return "F";
        }
        return "C";
    }

    /**
     * Use this method to get the real parameter value for the web service
     *
     * @return "imperial" for Fahrenheit and "metric" for Celsius
     */
    public String getTemperatureUnit() {
        return mTemperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.mTemperatureUnit = temperatureUnit;
    }
}
