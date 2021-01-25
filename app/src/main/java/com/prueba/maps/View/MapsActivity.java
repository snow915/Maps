package com.prueba.maps.View;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prueba.maps.Interfaces.Contract;
import com.prueba.maps.Presenter.MapsActivityPresenter;
import com.prueba.maps.R;
import com.prueba.maps.VO.Location;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Contract.View {

    private GoogleMap mMap;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<Location> locations;
    private MapsActivityPresenter mapsActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mapsActivityPresenter = new MapsActivityPresenter(this);
        mapsActivityPresenter.readLocations(firebaseFirestore);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        for(int i = 0; i<locations.size(); i++){
            double latitude =  Double.parseDouble(locations.get(i).getLatitude());
            double longitude = Double.parseDouble(locations.get(i).getLongitude());
            LatLng location = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(location).title(latitude + ", " + longitude));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f));
        }

    }

    @Override
    public void onLocationsRead(ArrayList<Location> locations) {
        this.locations = locations;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}