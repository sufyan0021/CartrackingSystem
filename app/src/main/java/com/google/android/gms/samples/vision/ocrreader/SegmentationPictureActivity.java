package com.google.android.gms.samples.vision.ocrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SegmentationPictureActivity extends AppCompatActivity {

    private ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segmentation_picture);
        img4=(ImageView)findViewById(R.id.image4);
        img4.setImageBitmap(MainActivity.bmp);
    }
}
