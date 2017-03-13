package com.google.android.gms.samples.vision.ocrreader.database;

import com.orm.SugarRecord;

/**
 * Created by kanav on 3/9/2017.
 */

public class FraudVehicleDatabase extends SugarRecord {
    public String number;

    public FraudVehicleDatabase(){
    }

    public FraudVehicleDatabase(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }
}
