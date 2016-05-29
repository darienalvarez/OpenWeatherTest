package com.darien.openweathertest.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.darien.openweathertest.BundleConstants;
import com.darien.openweathertest.IntentActions;
import com.darien.openweathertest.R;
import com.darien.openweathertest.controllers.WeatherController;
import com.darien.openweathertest.db.Zip;
import com.darien.openweathertest.util.DialogUtil;
import com.darien.openweathertest.view.activities.MainActivity;
import com.darien.openweathertest.view.adapter.ZipCodesAdapter;
import com.darien.openweathertest.view.dialogs.ZipCodeFragmentDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends BaseFragment implements OnFragmentInteractionListener {

    @BindView(R.id.selectForecastList)
    ListView listView;
    @BindView(R.id.empty)
    TextView emptyView;

    @Inject
    WeatherController controller;

    private List<String> mZipCodes;

    public LocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LocationFragment.
     */
    public static LocationFragment newInstance() {
        return new LocationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState, getView());

        loadZipCodeList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onItemSelected(i);
            }
        });

        if (!isConnected()) {
            DialogUtil.showNoConnectionDialog(getActivity());
        }
    }

    @Override
    public void onFragmentInteraction() {
        loadZipCodeList();
    }

    private void onItemSelected(int position) {
        MainActivity activity = (MainActivity)getActivity();
        if (isConnected()) {
            if (activity != null && mZipCodes != null) {
                String zipCode = mZipCodes.get(position);

                activity.showInfoFragment();
                notifyZipCodeChange(activity, zipCode);
            }
        } else {
            DialogUtil.showErrorDialog(activity, R.string.error_connection);
        }
    }

    @OnClick(R.id.addZipCodeBtn)
    void addZipCode() {
        showAddZipCodeDialog();
    }

    private void showAddZipCodeDialog() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            FragmentManager manager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            Fragment prev = manager.findFragmentByTag("addZipCodeDialog");
            if (prev != null) {
                fragmentTransaction.remove(prev);
            }
            fragmentTransaction.addToBackStack(null);

            // Create and show the dialog.
            ZipCodeFragmentDialog zipCodeFragmentDialog = ZipCodeFragmentDialog.newInstance();
            zipCodeFragmentDialog.setOnFragmentInteractionListener(this);
            //advancedSearchFragment.setCallback(this);
            zipCodeFragmentDialog.show(fragmentTransaction, "addZipCodeDialog");
        }
    }

    private void loadZipCodeList() {
        List<String> zipList = new ArrayList<>();
        for (Zip zip : controller.getZipCodeOrdered()) {
            zipList.add(zip.getZipCode());
        }

        this.mZipCodes = zipList;
        updateZipCodeList(zipList);
    }

    private void updateZipCodeList(List<String> zipCodes) {
        emptyView.setVisibility(zipCodes.isEmpty() ? View.VISIBLE: View.GONE);

        ZipCodesAdapter adapter = new ZipCodesAdapter(getActivity(), zipCodes);
        listView.setAdapter(adapter);
    }

    private void notifyZipCodeChange(MainActivity mainActivity, String zipCode) {
        Intent intent = new Intent(IntentActions.ACTION_UPDATE_ZIP);
        intent.putExtra(BundleConstants.ZIP_CODE, zipCode);
        LocalBroadcastManager.getInstance(mainActivity).sendBroadcast(intent);
    }

}
