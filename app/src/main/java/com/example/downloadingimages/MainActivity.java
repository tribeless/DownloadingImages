package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;



/*
********************************************************
********************************************************
************** CODE TO DOWNLOAD AN**********************
***************IMAGE FROM THE INTERNET******************
********************************************************
********************************************************
********************************************************
***/
public class MainActivity extends AppCompatActivity {


    ImageView myImage;

    //function to download image
    public void downloadImage(View view) {


        //object creation for class ImageDownloader
       ImageDownloader imageDownloader = new ImageDownloader();
       Bitmap picture;
        try {
            picture = imageDownloader.execute("https://d3hne3c382ip58.cloudfront.net/files/uploads/bookmundi/resized/cmsfeatured/places-to-travel-in-2018-1522385995-390X219.jpg").get();
            myImage.setImageBitmap(picture);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myImage = findViewById(R.id.imageView);


    }


    // extending AsyncTask to run on a background thread

       public static class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
           protected Bitmap doInBackground(String...urls){

            URL url;
            try {
                url = new URL(urls[0]);

                //opening the connection to the browser
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                //downloading the image directly
                InputStream inputStream = urlConnection.getInputStream();
                // converting the image into a bitmap
                return BitmapFactory.decodeStream(inputStream);
            }

            catch (Exception e) {

                e.printStackTrace();
            }

            return null;
        }


       }

}
