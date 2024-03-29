package com.example.udomiba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentContainerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_REQUEST_CODE = 123;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private GoogleMap mMap;
    private SupportMapFragment mSupportMapFragment;


    public static List<Address> myLocation;
    String city;
    Button saveLocation;
    Location location;
    public static final String EXTRA_LOCATION="";
    LatLng currentLocation;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.main_map);
        mSupportMapFragment.getMapAsync(this);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        saveLocation = findViewById(R.id.save_location_button);
        saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent intent = new Intent(Map.this, AddPet.class);
                //intent.putExtra(EXTRA_LOCATION, currentLocation);
                intent.putExtra(EXTRA_LOCATION, temp);
                startActivity(intent);
            }
        });
    }

    private void getLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(isLocationEnabled()){
                mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                location = task.getResult();
                                if(location != null){
                                    currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                    mMap.clear();
                                    mMap.addMarker(new MarkerOptions().position(currentLocation).title("Marker on current location"));
                                   // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15.0f));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
                                    try {
                                        Geocoder geocoder = new Geocoder(Map.this, Locale.getDefault());
                                        city = geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1).toString();
                                        int start_index = city.indexOf("locality");
                                        temp = "";
                                        boolean reached_equal = false;

                                        for( int i = start_index; i < city.length();i++){

                                            if(city.charAt(i) == ','){
                                                break;
                                            }
                                            if(reached_equal){
                                                temp += city.charAt(i);
                                            }
                                            if(city.charAt(i) == '='){
                                                reached_equal = true;
                                            }
                                        }


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(Map.this,"Unable to get location",Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                );
            }else {
                Toast.makeText(this,"Location is disabled",Toast.LENGTH_LONG).show();
            }
        }else {
            getPermissions();
        }

    }

    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

    }

    private void getPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                LOCATION_REQUEST_CODE
        );
    }

    public void onGetMyLocation(View view){
        getLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // have permission to use location
                getLocation();
            }else{
                Toast.makeText(this,"Permission not accepted",Toast.LENGTH_LONG).show();
            }
        }
    }


    /*@Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }*/
}