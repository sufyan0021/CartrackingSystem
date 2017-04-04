package com.google.android.gms.samples.vision.ocrreader.VehicleListView;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.samples.vision.ocrreader.R;
import com.google.android.gms.samples.vision.ocrreader.database.VehicleDatabase;

public class VehicleListActivity extends AppCompatActivity implements VehicleFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Reached","database opened");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
    }

    @Override
    public void onListFragmentInteraction(VehicleDatabase item) {
        item.delete();
        VehicleFragment fragment = (VehicleFragment) getSupportFragmentManager().findFragmentByTag("listFragment");
        fragment.refreshList();
    }
}
