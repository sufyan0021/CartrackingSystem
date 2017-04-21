package com.google.android.gms.samples.vision.ocrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.samples.vision.ocrreader.VehicleListView.VehicleListActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class VehicleDetails extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        textView= (TextView) findViewById(R.id.vehicleDetails);
        String s = VehicleListActivity.vehicleDetails.substring(VehicleListActivity.vehicleDetails.indexOf("<vehicleJson>"),VehicleListActivity.vehicleDetails.indexOf("</vehicleJson>")).replace("<vehicleJson>","");
        try {
            JSONObject jsonObject=new JSONObject(s);
            textView.setText("Description - "+jsonObject.getString("Description")+"\nRegistrationYear - "+jsonObject.getString("RegistrationYear")+"\nCarMake - "+jsonObject.getJSONObject("CarMake").getString("CurrentTextValue")+"\nCarModel - "+jsonObject.getJSONObject("CarModel").getString("CurrentTextValue")+"\nLocation - "+jsonObject.getString("Location")+"\nEngineNumber - "+jsonObject.getString("EngineNumber")+"\nRegistrationDate - "+jsonObject.getString("RegistrationDate"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /*<vehicleJson>{"Description":"FORD FUSION ","RegistrationYear":"2009","CarMake":{"CurrentTextValue":"FORD"},"CarModel":{"CurrentTextValue":"FUSION"},"EngineSize":{"CurrentTextValue":"1596"},"MakeDescription":{"CurrentTextValue":"FORD"},"ModelDescription":{"CurrentTextValue":"FUSION"},"VechileIdentificationNumber":"MAJBXXMRJB9E44727","NumberOfSeats":{"CurrentTextValue":"5"},"Colour":"MORELLO","EngineNumber":"MAJBXXMRJB9E44727","FuelType":{"CurrentTextValue":"DIESEL"},"RegistrationDate":"22/2/2010","Location":"RTO JAMMU","ImageUrl":"http://in.carregistrationapi.com/image.aspx/@Rk9SRCBGVVNJT04g"}</vehicleJson>*/
}
