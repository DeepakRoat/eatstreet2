package com.example.eatstreet2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eatstreet2.models.Vendor;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.eatstreet2.network.ApiService;

public class AddVendorActivity extends AppCompatActivity {

    private EditText nameInput, descriptionInput, categoryInput, priceLevelInput,
            ratingInput, hygieneInput, openTimeInput, closeTimeInput, photoUrlInput;
    private CheckBox isVegCheckbox;
    private Button submitButton;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vendor);

        nameInput = findViewById(R.id.input_name);
        descriptionInput = findViewById(R.id.input_description);
        categoryInput = findViewById(R.id.input_category);
        priceLevelInput = findViewById(R.id.input_price);
        ratingInput = findViewById(R.id.input_rating);
        hygieneInput = findViewById(R.id.input_hygiene);
        openTimeInput = findViewById(R.id.input_open_time);
        closeTimeInput = findViewById(R.id.input_close_time);
        photoUrlInput = findViewById(R.id.input_photo_url);
        isVegCheckbox = findViewById(R.id.checkbox_is_veg);
        submitButton = findViewById(R.id.btn_submit);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        submitButton.setOnClickListener(v -> submitVendor());
    }

    private void submitVendor() {
        float a = 0;
        Vendor vendor = new Vendor();
        vendor.name = nameInput.getText().toString().trim();
        vendor.description = descriptionInput.getText().toString().trim();
        vendor.category = categoryInput.getText().toString().trim();
        vendor.price_level = Integer.parseInt(priceLevelInput.getText().toString().trim());
        vendor.rating = Float.parseFloat(ratingInput.getText().toString().trim());
        vendor.hygiene_score = Float.parseFloat(hygieneInput.getText().toString().trim());
        vendor.open_time = openTimeInput.getText().toString().trim();
        vendor.close_time = closeTimeInput.getText().toString().trim();
        vendor.photo_urls = Collections.singletonList(photoUrlInput.getText().toString().trim());
        vendor.is_veg = isVegCheckbox.isChecked();

        SharedPreferences prefs = getSharedPreferences("auth", MODE_PRIVATE);
        String token = prefs.getString("jwt", "");

        Call<Void> call = apiService.addVendor("Bearer " + token, vendor);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                runOnUiThread(() -> {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddVendorActivity.this, "Vendor submitted!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddVendorActivity.this, "Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                runOnUiThread(() -> Toast.makeText(AddVendorActivity.this, "Failed to submit vendor", Toast.LENGTH_SHORT).show());
                Log.e("AddVendor", "Error: ", t);
            }
        });
    }
}