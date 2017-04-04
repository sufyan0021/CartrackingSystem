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

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.samples.vision.ocrreader.VehicleListView.VehicleListActivity;
import com.google.android.gms.samples.vision.ocrreader.database.FraudVehicleDatabase;
import com.google.android.gms.samples.vision.ocrreader.database.VehicleDatabase;
import com.google.android.gms.vision.text.Line;
import com.orm.SugarContext;

import static android.R.attr.button;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * recognizes text.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    // Use a compound button so either checkbox or switch widgets work.
    private CompoundButton autoFocus;
    private CompoundButton useFlash;
    private EditText etVehicleNumber;
    private EditText etFraudVehicleNumber;
    private LinearLayout linearLayout;


    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "MainActivity";

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
    }

    private void showHide() {
        if(linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
        }
        else{
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == RC_OCR_CAPTURE) {
//            if (resultCode == CommonStatusCodes.SUCCESS) {
//                if (data != null) {
//                    String text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);
//                    statusMessage.setText(R.string.ocr_success);
//                    textValue.setText(text);
//                    Log.d(TAG, "Text read: " + text);
//                } else {
//                    statusMessage.setText(R.string.ocr_failure);
//                    Log.d(TAG, "No Text captured, intent data is null");
//                }
//            } else {
//                statusMessage.setText(String.format(getString(R.string.ocr_error),
//                        CommonStatusCodes.getStatusCodeString(resultCode)));
//            }
//        }
//        else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
    }
}
