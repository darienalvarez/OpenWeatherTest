package com.darien.openweathertest.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.darien.openweathertest.MapsActivity;
import com.darien.openweathertest.R;
import com.darien.openweathertest.controllers.WeatherController;
import com.darien.openweathertest.db.Zip;
import com.darien.openweathertest.pojo.Forecast;
import com.darien.openweathertest.pojo.Weather;
import com.darien.openweathertest.util.BundleConstants;
import com.darien.openweathertest.util.IntentActions;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends BaseFragment implements Callback<Forecast> {

    private static final String TAG = "InfoFragment";

    @Inject
    WeatherController controller;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.weatherImg)
    ImageView weatherImg;

    @BindView(R.id.cityTextView) TextView cityTextView;

    @BindView(R.id.windTextView) TextView windTextView;
    @BindView(R.id.cloudTextView) TextView cloudTextView;
    @BindView(R.id.pressureTextView) TextView pressureTextView;
    @BindView(R.id.humidityTextView) TextView humidityTextView;
    @BindView(R.id.currentTempTextView) TextView currentTempTextView;
    @BindView(R.id.minTempTextView) TextView minTempTextView;
    @BindView(R.id.maxTempTextView) TextView maxTempTextView;
    @BindView(R.id.coordTextView) TextView coordTextView;

    private String mZipCode;
    private Forecast mForecast;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String zipCode = intent.getStringExtra(BundleConstants.ZIP_CODE);
            if (!TextUtils.isEmpty(zipCode)) {
                updateForecast(zipCode);
            }
        }
    };

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InfoFragment.
     */
    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState, getView());

        Zip defaultZipCode = controller.getDefaultZipCode();

        if (defaultZipCode != null) {
            updateForecast(defaultZipCode.getZipCode());
        }

        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(receiver, new IntentFilter(IntentActions.ACTION_UPDATE_ZIP));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(receiver);
    }

    @Override
    public void onResponse(Call<Forecast> call, Response<Forecast> response) {
        progressBar.setVisibility(View.GONE);
        String error = null;
        if (response.errorBody() != null) {
            try {
                error = response.errorBody().string();
            } catch (IOException e) {
                Log.e(TAG, "error body exception", e);
            }
        }

        if (TextUtils.isEmpty(error)) {
            updateInterfaceData(response.body());
        }
    }

    @Override
    public void onFailure(Call<Forecast> call, Throwable t) {
        Log.e(TAG, "error", t);
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.viewInMapBtn)
    void showMap(View view) {
        if (mForecast != null) {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble(BundleConstants.LATITUDE, mForecast.getCoord().getLat());
            bundle.putDouble(BundleConstants.LONGITUDE, mForecast.getCoord().getLon());
            bundle.putString(BundleConstants.CITY, mForecast.getName());
            bundle.putString(BundleConstants.TEMPERATURE,
                    getString(R.string.current_temperature, mForecast.getMain().getTemp(), "f"));
            intent.putExtras(bundle);
            getActivity().startActivity(intent);
        }
    }

    private void updateForecast(String zipCode) {
        this.mZipCode = zipCode;
        Call<Forecast> call = controller.getForecast(zipCode);
        call.enqueue(this);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void updateInterfaceData(Forecast forecast) {
        mForecast = forecast;
        Log.i(TAG, "updating forecast");

        cityTextView.setText(forecast.getName());

        currentTempTextView.setText(getString(R.string.current_temperature, forecast.getMain().getTemp(), "f"));
        minTempTextView.setText(getString(R.string.min_temperature, forecast.getMain().getTempMin(), "f"));
        maxTempTextView.setText(getString(R.string.max_temperature, forecast.getMain().getTempMax(), "f"));

        windTextView.setText(getString(R.string.wind, forecast.getWind().getSpeed() ));
        cloudTextView.setText(getString(R.string.cloud, forecast.getClouds().getAll() ));
        pressureTextView.setText(getString(R.string.pressure, forecast.getMain().getPressure()));
        humidityTextView.setText(getString(R.string.humidity, forecast.getMain().getHumidity()));
        coordTextView.setText(getString(R.string.geo_coords, forecast.getCoord().getLat(), forecast.getCoord().getLon()));

        List<Weather> weathers = forecast.getWeather();
        if (weathers != null && !weathers.isEmpty()) {
            Weather firstWeather = weathers.get(0);


            Picasso.with(getActivity())
                    .load(String.format("http://openweathermap.org/img/w/%s.png", firstWeather.getIcon()))
                    .into(weatherImg);
        }
    }

}
