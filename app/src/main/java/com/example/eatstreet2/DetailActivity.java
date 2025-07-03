package com.example.eatstreet2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String placeName = getIntent().getStringExtra("place_name");
        double rating = getIntent().getDoubleExtra("place_rating", 0.0);
        String photoRef = getIntent().getStringExtra("place_photo");

        ImageView placePhoto = findViewById(R.id.place_photo);
        if(!photoRef.isEmpty()) {
            String photoUrl = "https://maps.googleapis.com/maps/api/place/photo"
                    + "?maxwidth=1600"
                    + "&photo_reference=" + photoRef
                    + "&key=" + this.getString(R.string.maps_api_key);

            Glide.with(this)
                    .load(photoUrl)
                    .into(placePhoto);
        } else {
            placePhoto.setImageResource(R.drawable.no_image);
        }

        TextView placeInfo = findViewById(R.id.placeInfo);
        placeInfo.setText("Place: " + placeName + "\nRating: " + rating);
    }
}
