/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.darien.openweathertest.view;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.inputmethod.InputMethodManager;

import com.darien.openweathertest.WeatherApplication;
import com.darien.openweathertest.view.activities.MainActivity;
import com.darien.openweathertest.view.activities.MapsActivity;
import com.darien.openweathertest.view.dialogs.ZipCodeFragmentDialog;
import com.darien.openweathertest.view.fragments.InfoFragment;
import com.darien.openweathertest.view.fragments.LocationFragment;

import dagger.Module;
import dagger.Provides;

/**
 * This module represents objects which exist only for the scope of a single activity. We can
 * safely create singletons using the activity instance because the entire object graph will only
 * ever exist inside of that activity.
 */
@Module(
        injects = {
                //activities
                MainActivity.class,
                MapsActivity.class,
                //fragments
                InfoFragment.class,
                LocationFragment.class,
                ZipCodeFragmentDialog.class
        },
        library = true,
        complete = false
)
public class ViewModule {

    @Provides
    public ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) WeatherApplication.CONTEXT.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    public InputMethodManager provideInputMethodManager() {
        return (InputMethodManager) WeatherApplication.CONTEXT.getSystemService(Activity.INPUT_METHOD_SERVICE);
    }
}
