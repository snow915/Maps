package com.prueba.maps.Presenter;

import com.google.firebase.firestore.FirebaseFirestore;
import com.prueba.maps.Interactor.MapsActivityInteractor;
import com.prueba.maps.Interfaces.Contract;
import com.prueba.maps.VO.Location;

import java.util.ArrayList;

public class MapsActivityPresenter implements Contract.Presenter, Contract.onOperationListener {

    private MapsActivityInteractor mapsActivityInteractor;
    private Contract.View view;

    public MapsActivityPresenter(Contract.View view) {
        this.view = view;
        mapsActivityInteractor = new MapsActivityInteractor(this);
    }

    @Override
    public void readLocations(FirebaseFirestore firebaseFirestore) {
        mapsActivityInteractor.performReadLocations(firebaseFirestore);
    }

    @Override
    public void onRead(ArrayList<Location> locations) {
        view.onLocationsRead(locations);
    }
}
