package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.Locale;


public class mapa extends AppCompatActivity {

    TextView mensaje1;
    TextView mensaje2;
    TextView mensaje3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        }

        // get my coordinates and show them in the textview

        mensaje1 = (TextView) findViewById(R.id.mensaje1);
        mensaje2 = (TextView) findViewById(R.id.mensaje2);
        mensaje3 = (TextView) findViewById(R.id.mensaje3);

        // get the location manager

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // define the location listener

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mensaje1.setText("" + location.getLatitude());
                mensaje2.setText("" + location.getLongitude());
                mensaje3.setText("" + location.getAltitude() + ", Precision: " + location.getAccuracy());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                switch (status) {
                    case LocationProvider.AVAILABLE:
                        Log.d("debug", "LocationProvider.AVAILABLE");
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                        break;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        // request location updates

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        // get the last known location

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // show the last known location

        if (location != null) {
            mensaje1.setText("" + location.getLatitude());
            mensaje2.setText("" + location.getLongitude());
            mensaje3.setText("Altitude: " + location.getAltitude() + ", Accuracy: " + location.getAccuracy());
        }

        // show the location in a map when the button is clicked

        findViewById(R.id.map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", location.getLatitude(), location.getLongitude());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            }
        });










    }
}

