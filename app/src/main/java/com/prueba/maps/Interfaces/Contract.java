package com.prueba.maps.Interfaces;

import com.google.firebase.firestore.FirebaseFirestore;
import com.prueba.maps.VO.Location;

import java.util.ArrayList;

public interface Contract {

    interface View{
        void onLocationsRead(ArrayList<Location> locations);
    }

    interface Presenter {
        void readLocations(FirebaseFirestore firebaseFirestore);
    }

    interface Interactor {
        void performReadLocations(FirebaseFirestore firebaseFirestore);
    }

    interface onOperationListener {
        void onRead(ArrayList<Location>locations);
    }

}
