package com.google.android.gms.samples.vision.ocrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.samples.vision.ocrreader.VehicleListView.VehicleListActivity;

public class VehicleDetails extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        textView= (TextView) findViewById(R.id.vehicleDetails);
        textView.setText(VehicleListActivity.vehicleDetails);
    }
}
