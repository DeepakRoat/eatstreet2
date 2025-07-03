    package com.example.eatstreet2;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.ImageButton;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.eatstreet2.adapters.VendorAdapter;
    import com.example.eatstreet2.models.Vendor;
    import com.google.android.material.bottomnavigation.BottomNavigationView;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;

    import java.util.ArrayList;
    import java.util.List;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();

            if (user == null) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }

            setContentView(R.layout.activity_main);

            // Bind UI
            EditText searchEditText = findViewById(R.id.searchEditText);
            ImageButton favTrendingIcon = findViewById(R.id.favTrendingIcon);
            RecyclerView vendorRecyclerView = findViewById(R.id.vendorRecyclerView);
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

            // Dummy vendor list
            List<Vendor> vendorList = new ArrayList<>();
            Vendor vendor1 = new Vendor();
            vendor1.name = "Biryani Bhai";
            vendor1.category = "North Indian";
            vendor1.is_veg = false;
            vendor1.rating = 4.6f;
            vendor1.hygiene_score = 4.5f;
            vendor1.is_verified = true;
            //vendor1.photo_urls = Array.asList("https://via.placeholder.com/150");

            Vendor vendor2 = new Vendor();
            vendor2.name = "Momos Point";
            vendor2.category = "Tibetan";
            vendor2.is_veg = true;
            vendor2.rating = 4.2f;
            vendor2.hygiene_score = 4.0f;
            vendor2.is_verified = false;
            //vendor2.photo_urls = Arrays.asList("https://via.placeholder.com/150");

            // Add vendors to list
            vendorList.add(vendor1);
            vendorList.add(vendor2);


            // RecyclerView setup
            vendorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            VendorAdapter vendorAdapter = new VendorAdapter(this, vendorList);
            vendorRecyclerView.setAdapter(vendorAdapter);

            // Top star icon
            favTrendingIcon.setOnClickListener(v -> {
                Toast.makeText(this, "Trending clicked!", Toast.LENGTH_SHORT).show();
                // TODO: Load trending vendors or favorites
            });

            // Search input click
            searchEditText.setOnClickListener(v -> {
                Toast.makeText(this, "Search clicked!", Toast.LENGTH_SHORT).show();
                // TODO: Open search screen or show location picker
            });

            // Bottom navigation
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.nav_top) {
                    Toast.makeText(this, "Top Restaurants", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            });

        }

    }
