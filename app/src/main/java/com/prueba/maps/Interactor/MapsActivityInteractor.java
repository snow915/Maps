package com.prueba.maps.Interactor;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.prueba.maps.Interfaces.Contract;
import com.prueba.maps.VO.Location;

import java.util.ArrayList;

public class MapsActivityInteractor implements Contract.Interactor {

    private Contract.onOperationListener onOperationListener;

    public MapsActivityInteractor(Contract.onOperationListener onOperationListener){
        this.onOperationListener = onOperationListener;
    }

    @Override
    public void performReadLocations(FirebaseFirestore firebaseFirestore) {
        firebaseFirestore.collection("locations")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        ArrayList<Location> locations = new ArrayList<>();
                        for(QueryDocumentSnapshot doc : value){
                            locations.add(doc.toObject(Location.class));
                        }
                        onOperationListener.onRead(locations);
                    }
                });
    }

}
