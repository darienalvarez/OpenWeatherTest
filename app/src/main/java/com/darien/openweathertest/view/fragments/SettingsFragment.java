package com.darien.openweathertest.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.content.LocalBroadcastManager;

import com.darien.openweathertest.IntentActions;
import com.darien.openweathertest.R;
import com.darien.openweathertest.util.PreferencesUtil;
import com.darien.openweathertest.view.activities.SettingsActivity;

/**
 * Created by Darien
 * on 5/27/16.
 */
public class SettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() { }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        if (key.equals(SettingsActivity.PREFERENCE_TEMPERATURE_UNIT)) {
            Context context = getActivity().getApplicationContext();
            PreferencesUtil.getInstance()
                    .setTemperatureUnit(sharedPreferences
                            .getString(key, SettingsActivity.DEFAULT_TEMPERATURE_UNIT));

            // update the interface if any weather was loaded
            Intent intent = new Intent(IntentActions.ACTION_UPDATE_ZIP);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}
