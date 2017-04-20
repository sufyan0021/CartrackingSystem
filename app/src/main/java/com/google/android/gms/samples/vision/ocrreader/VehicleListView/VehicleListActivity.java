package com.google.android.gms.samples.vision.ocrreader.VehicleListView;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.samples.vision.ocrreader.R;
import com.google.android.gms.samples.vision.ocrreader.VehicleDetails;
import com.google.android.gms.samples.vision.ocrreader.database.VehicleDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VehicleListActivity extends AppCompatActivity implements VehicleFragment.OnListFragmentInteractionListener {


    public static String vehicleDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Reached","database opened");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);
    }

    @Override
    public void onListFragmentInteraction(final VehicleDatabase item) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a option");
        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                makeRequest(item.getNumber());
            }
        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                item.delete();
                VehicleFragment fragment = (VehicleFragment) getSupportFragmentManager().findFragmentByTag("listFragment");
                fragment.refreshList();
            }
        }).show();
    }

    protected void makeRequest(String number) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://www.regcheck.org.uk/api/reg.asmx/CheckIndia?RegistrationNumber=\""+number+"\"&username=ajitesh";
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent=new Intent(VehicleListActivity.this, VehicleDetails.class);
                        vehicleDetails=response;
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VehicleListActivity.this,"Error-"+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}
