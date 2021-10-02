package com.example.myapplicationformaps;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplicationformaps.databinding.ActivityMapsBinding;

import java.io.InputStream;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        int[] latarr = {20,30,60,70};
        int[] lngarr = {30,40,80,90};
        int[] coLables = {144,73,38,38};
        int[] temp = {37,38,7,7};
        String[] cityName = {"Sudan","Saudi Arabia","Russia","Russia"};

        String s = "";
        String p = "";
        for(int i =0 ;i < latarr.length;i++) {

            LatLng sydney = new LatLng(latarr[i], lngarr[i]);
            if(temp[i]>=25 && temp[i]<=30){
                s = "Green (Safe)";
                p = "Precaution: You are Safe";

                mMap.addMarker(new MarkerOptions().position(sydney).title("Location : "+cityName[i]).snippet("Status :"+s+"\n"+"AQI : "+coLables[i]+"\n"+"Temp :" + temp[i]+"°C"+"\n"+"Covid : "+s+"\n"+p).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
            if (temp[i]>=31 && temp[i]<=38 ){
                s = "Yellow ( Not Safe )";
                p = "Precaution: Staying Home";
                mMap.addMarker(new MarkerOptions().position(sydney).title("Location : "+cityName[i]).snippet("Status :"+s+"\n"+"AQI : "+coLables[i]+"\n"+"Temp :" + temp[i]+"°C"+"\n"+"Covid : "+s+"\n"+p).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }
            else{

                s = "Red ( Dangerous )";
                p = "Precaution: Staying Home";
                mMap.addMarker(new MarkerOptions().position(sydney).title("Location : "+cityName[i]).snippet("Status :"+s+"\n"+"AQI : "+coLables[i]+"\n"+"Temp :" + temp[i]+"°C"+"\n"+"Covid : "+s+"\n"+p).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }

        }


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(MapsActivity.this);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(MapsActivity.this);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(MapsActivity.this);
                snippet.setTextColor(Color.BLUE);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });


    }






}