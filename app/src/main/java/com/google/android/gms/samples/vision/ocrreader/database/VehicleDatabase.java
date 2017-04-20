package com.google.android.gms.samples.vision.ocrreader.database;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by kanav on 3/5/2017.
 */
//@Table
public class VehicleDatabase extends SugarRecord{

    //private Long id;
    String number;
    String ownerName;
    String brandName;
    String engineNumber;
    String time;

    public VehicleDatabase(){
    }

    public VehicleDatabase(String number, String time) {
        this.number = number;
        this.time = time;
    }

    public VehicleDatabase(String number, String brandName, String ownerName, String engineNumber) {
        this.number = number;
        this.brandName = brandName;
        this.ownerName = ownerName;
        this.engineNumber = engineNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public String getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    public String getOwnerName() {
        return ownerName;
    }
    //    public Long getId() {
//        return id;
//    }
}
