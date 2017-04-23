package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GalleryActivity extends AppCompatActivity {

    ImageView img1,img2,img3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        img1=(ImageView)findViewById(R.id.image1);
        img2=(ImageView)findViewById(R.id.image2);
        img3=(ImageView)findViewById(R.id.image3);
        button=(Button) findViewById(R.id.button);
        Bitmap bitmap=createContrast(MainActivity.bmp,50);
        img1.setImageBitmap(MainActivity.bmp);
        img2.setImageBitmap(bitmap);
        img3.setImageBitmap(toBinary(bitmap));
        img3.setImageBitmap(createContrast(MainActivity.bmp,25));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GalleryActivity.this,SegmentationPictureActivity.class);
                startActivity(intent);
            }
        });
    }

    public Bitmap toBinary(Bitmap bmpOriginal) {
        int width, height, threshold;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();
        threshold = 127;
        Bitmap bmpBinary = Bitmap.createBitmap(bmpOriginal);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                int pixel = bmpOriginal.getPixel(x, y);
                int red = Color.red(pixel);

                //get binary value
                if(red < threshold){
                    bmpBinary.setPixel(x, y, 0xFF000000);
                } else{
                    bmpBinary.setPixel(x, y, 0xFFFFFFFF);
                }

            }
        }
        return bmpBinary;
    }


    public static Bitmap createContrast(Bitmap src, double value) {
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        int A, R, G, B;
        int pixel;
        double contrast = Math.pow((100 + value) / 100, 2);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel);
                R = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(R < 0) { R = 0; }
                else if(R > 255) { R = 255; }

                G = Color.red(pixel);
                G = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(G < 0) { G = 0; }
                else if(G > 255) { G = 255; }

                B = Color.red(pixel);
                B = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(B < 0) { B = 0; }
                else if(B > 255) { B = 255; }

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        return bmOut;
    }
}
