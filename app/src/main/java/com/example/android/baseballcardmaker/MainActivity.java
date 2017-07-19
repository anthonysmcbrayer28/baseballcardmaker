package com.example.android.baseballcardmaker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText atbat;
    EditText hits;
    TextView score;
    Button advrage;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        atbat = (EditText) findViewById(R.id.atbat);
        hits = (EditText) findViewById(R.id.hits);
        score = (TextView) findViewById(R.id.score);
        advrage = (Button) findViewById(R.id.avrage);
        this.imageView = (ImageView) this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent pickIntent = new Intent();
                pickIntent.setType("image/*");
                pickIntent.setAction(Intent.ACTION_GET_CONTENT);
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String pickTitle = "Take or select a photo";
                Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePhotoIntent});
                startActivityForResult(chooserIntent, REQUEST_CODE_PICTURE);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == REQUEST_CODE_PICTURE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public void advrage(View v) {
        calculate();


    }

    private static final int REQUEST_CODE_PICTURE = 1;

    /**
     * Click on View to change photo. Sets into View of your layout, android:onClick="clickOnPhoto"
     *
     * @param view View
     */
    public void share(View view) {
        EditText text = (EditText) findViewById(R.id.player_name);
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        TextView ba = (TextView) findViewById(R.id.ba);
        TextView score = (TextView) findViewById(R.id.score);
        String fox = shareapp(text, imageView1, ba, score);
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "This is the text that will be shared.");
        startActivity(Intent.createChooser(sharingIntent, "com.facebook.katana"));
    }

    private String shareapp(EditText text, ImageView imageView1, TextView ba, TextView score) {
        String fox = shareapp(text, imageView1, ba, score);
        return fox;


    }

    public void calculate() {
        //get entered texts from the edittexts,and convert to integers.
        Double value1 = Double.parseDouble(atbat.getText().toString());
        Double value2 = Double.parseDouble(hits.getText().toString());

        //do the calculation
        Double calculatedValue = (value2 / value1);
        //set the value to the textview, to display on screen.


        score.setText(String.format("%.3f", calculatedValue));

    }


    }







