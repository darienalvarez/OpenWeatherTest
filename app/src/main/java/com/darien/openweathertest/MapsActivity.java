package com.darien.openweathertest;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.darien.openweathertest.util.BundleConstants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private double mLatitude;
    private double mLongitude;
    private String mCity;
    private String mTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLatitude = getIntent().getDoubleExtra(BundleConstants.LATITUDE, 0);
        mLongitude = getIntent().getDoubleExtra(BundleConstants.LONGITUDE, 0);
        mCity = getIntent().getStringExtra(BundleConstants.CITY);
        mTemperature = getIntent().getStringExtra(BundleConstants.TEMPERATURE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng position = new LatLng(mLatitude, mLongitude);
        mMap.addMarker(new MarkerOptions().position(position).title(mCity).snippet(mTemperature));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)
                .zoom(12).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
