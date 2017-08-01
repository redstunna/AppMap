package com.example.dmitry.emptyapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.sample.basiclocationsample.R;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    //private FusedLocationProviderClient mFusedLocationClient;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    /*//
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    //*/
    private FusedLocationProviderClient mFusedLocationClient;

    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 99;
    //private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 98;
    //----satckoverflow's adviced code----
    LocationRequest mLocationRequest;
    //GoogleApiClient mGoogleApiClient;
    FusedLocationProviderClient mLocataionClient;
    Location mLastLocation;

    //----For location services----
    private GoogleApiClient googleApiClient;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //----Permission for location manager----
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }

        //-----Build google API client----
        ///--///googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
        /*
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationClient = new FusedLocationProviderClient(this, this, this);

        checkProviders();
        */
    }

    //----Check permission already granted----
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    //----Override lifecycle method----
    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }
    //----
    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }
    //-----


    //----Change button----
    public void pressButtonChange(View v) {
        TextView t = (TextView) findViewById(R.id.textView1);
        t.setText("This happens!");
    }

    //----Activity button----
    public void pressButtonVis(View v) {
        Intent intent = new Intent(this, bottomActivity.class);
        startActivity(intent);
    }

    public void pressButtonItem(View v) {
        TextView t = (TextView) findViewById(R.id.textView1);
        t.setText("Items buttom is work");
        //double nums = locFinder;
        locFinder loc = new locFinder();
        t.setText("Value = " + Double.toString(loc.calcValue()));
    }

    public void pressButtonMaps(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        TextView t = (TextView) findViewById(R.id.textView1);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            //Location lastLocation = LocationServices.FusedLocationApi.getLastLocation();
            //----check null location
            /*
            while (lastLocation == null){
                t.setText("Nulll");
                lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            }*/

            if(lastLocation == null){
                t.setText("Nulll");
            }
            else{
                double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
                //t.setText(Double.toString(lat) +" " + Double.toString(lon));
            }

            //double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
            /*
            //TextView t = (TextView) findViewById(R.id.textView1);
            //t.setText(Double.toString(lat));
        }
        */
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        TextView t = (TextView) findViewById(R.id.textView1);
        if(location == null){
            t.setText("nulll");
        }
        else{
            //t.setText("Got location");
            double lat = location.getLatitude(), lon = location.getLongitude();
            t.setText(Double.toString(lat) +" " + Double.toString(lon));
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    /*
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    */
}
