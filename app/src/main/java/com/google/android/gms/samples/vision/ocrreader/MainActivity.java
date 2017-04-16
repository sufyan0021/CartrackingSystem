/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.vision.ocrreader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.samples.vision.ocrreader.VehicleListView.VehicleListActivity;
import com.google.android.gms.samples.vision.ocrreader.database.FraudVehicleDatabase;
import com.google.android.gms.samples.vision.ocrreader.database.VehicleDatabase;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.mlsdev.rximagepicker.RxImageConverters;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * recognizes text.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    // Use a compound button so either checkbox or switch widgets work.
    public static Bitmap bmp;
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private EditText etVehicleNumber;
    private EditText etFraudVehicleNumber;
    private LinearLayout linearLayout;
    ArrayList<Uri> path = new ArrayList<>();

    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "MainActivity";
    private String mPath;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etVehicleNumber=(EditText)findViewById(R.id.et_vehicle_numb);
        etFraudVehicleNumber=(EditText)findViewById(R.id.et_fraud_vehicle_numb);
        autoFocus = (CompoundButton) findViewById(R.id.auto_focus);
        useFlash = (CompoundButton) findViewById(R.id.use_flash);
        linearLayout = (LinearLayout) findViewById(R.id.llHide);

        findViewById(R.id.read_text).setOnClickListener(this);
        findViewById(R.id.show_list).setOnClickListener(this);
        findViewById(R.id.add_vehicle).setOnClickListener(this);
        findViewById(R.id.add_fraud_vehicle).setOnClickListener(this);
        findViewById(R.id.hide).setOnClickListener(this);
        findViewById(R.id.gallery).setOnClickListener(this);
       // createFraudVehicleDatabase();
    }

    private void createFraudVehicleDatabase() {
       // FraudVehicleDatabase.deleteAll(FraudVehicleDatabase.class);
       // saveFraudNumber("TN 57\nAD 3604");
    }

    private void saveFraudNumber(String number){
        FraudVehicleDatabase fraudVehicleDatabase=new FraudVehicleDatabase(number);
        fraudVehicleDatabase.save();
    }

    private void saveNumber(String number){
        VehicleDatabase vehicleDatabase=new VehicleDatabase(number);
        vehicleDatabase.save();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_text) {
            // launch Ocr capture activity.
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            intent.putExtra(OcrCaptureActivity.AutoFocus, autoFocus.isChecked());
            intent.putExtra(OcrCaptureActivity.UseFlash, useFlash.isChecked());
            startActivityForResult(intent, RC_OCR_CAPTURE);
        }
        else if (v.getId() == R.id.show_list) {
            // launch Ocr capture activity.

            Intent intent = new Intent(this, VehicleListActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.add_vehicle) {
            saveNumber(etVehicleNumber.getText().toString());
            etVehicleNumber.setText("");
        }
        else if (v.getId() == R.id.add_fraud_vehicle) {
            saveFraudNumber(etFraudVehicleNumber.getText().toString());
            saveNumber(etFraudVehicleNumber.getText().toString());
            etFraudVehicleNumber.setText("");
        }
        else if (v.getId() == R.id.hide) {
            showHide();
        }
        else if (v.getId() == R.id.gallery) {
            launchGallery();
        }


    }

    private void launchGallery() {
        RxImagePicker.with(this).requestImage(Sources.GALLERY)
                .flatMap(new Func1<Uri, Observable<Bitmap>>() {
                    @Override
                    public Observable<Bitmap> call(Uri uri) {
                        return RxImageConverters.uriToBitmap(MainActivity.this, uri);
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        // Do something with Bitmap
                        ocrBitmap(bitmap);
                    }
                });
    }

    private void showHide() {
        if(linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
        }
        else{
            linearLayout.setVisibility(View.VISIBLE);
        }
    }
    public void showProgressDialog(){
        progress = new ProgressDialog(this);
        progress.setMessage("Analyzing Text");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.show();
    }

    private void ocrBitmap(Bitmap bitmap) {
        showProgressDialog();
        Frame frame = new Frame.Builder()
                .setBitmap(bitmap)
                .build();

        TextRecognizer textRecognizer = new TextRecognizer.Builder(MainActivity.this).build();

        SparseArray<TextBlock> detectBlocks = textRecognizer.detect(frame);

        for (int i = 0; i < detectBlocks.size(); ++i) {
            TextBlock item = detectBlocks.valueAt(i);
            Log.d("TEST",item.getValue().toString());
            if(isVehicle(item.getValue().toString())){
                saveToDatabase(item.getValue().toString());
            }
        }
        bmp=bitmap;
        progress.hide();
        Intent intent=new Intent(this,GalleryActivity.class);
        startActivity(intent);
    }
    public void saveToDatabase(String number) {
        VehicleDatabase vehicleDatabase = new VehicleDatabase(number);
        vehicleDatabase.save();
    }

    public boolean isVehicle(String s){
        if((s.length()>10)&&(s.length()<16)){
            return true;
        }
        return false;
    }
}
