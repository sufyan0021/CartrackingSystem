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

    /*<vehicleJson>{"Description":"FORD FUSION ","RegistrationYear":"2009","CarMake":{"CurrentTextValue":"FORD"},"CarModel":{"CurrentTextValue":"FUSION"},"EngineSize":{"CurrentTextValue":"1596"},"MakeDescription":{"CurrentTextValue":"FORD"},"ModelDescription":{"CurrentTextValue":"FUSION"},"VechileIdentificationNumber":"MAJBXXMRJB9E44727","NumberOfSeats":{"CurrentTextValue":"5"},"Colour":"MORELLO","EngineNumber":"MAJBXXMRJB9E44727","FuelType":{"CurrentTextValue":"DIESEL"},"RegistrationDate":"22/2/2010","Location":"RTO JAMMU","ImageUrl":"http://in.carregistrationapi.com/image.aspx/@Rk9SRCBGVVNJT04g"}</vehicleJson>*/
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
