package com.example.downloadingimages;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;




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
    EditText editText;

    //function to download image
    public void downloadImage(View view) {

                //https://food.jumia.co.ke/blog/wp-content/uploads/2016/06/lake-naivasha-kenya-660x400.jpg
        //object creation for class ImageDownloader
       ImageDownloader imageDownloader = new ImageDownloader();
       String userURL = editText.getText().toString();

       Bitmap picture;
        try {
            //getting image URL from the user
            picture = imageDownloader.execute(userURL).get();
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
        editText = findViewById(R.id.editText);



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
