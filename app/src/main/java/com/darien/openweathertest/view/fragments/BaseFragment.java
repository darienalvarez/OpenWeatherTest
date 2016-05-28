package com.darien.openweathertest.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.darien.openweathertest.view.activities.BaseActivity;

import butterknife.ButterKnife;

/**
 * BaseResPOJO fragment which performs injection using the activity object graph of its parent.
 */
public class BaseFragment extends Fragment {

    private BaseActivity baseActivity;

    public void onActivityCreated(Bundle savedInstanceState, View fragmentView) {
        super.onActivityCreated(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        baseActivity.inject(this);
        ButterKnife.bind(this, fragmentView);
    }

    public boolean isConnected() {
        return baseActivity != null && baseActivity.isConnected();
    }

    public void hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard();
        }
    }

}
