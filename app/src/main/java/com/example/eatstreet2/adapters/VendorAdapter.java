package com.example.eatstreet2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatstreet2.R;
import com.example.eatstreet2.models.Vendor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VendorAdapter extends RecyclerView.Adapter<VendorAdapter.VendorViewHolder> {

    private final Context context;
    private final List<Vendor> vendorList;

    public VendorAdapter(Context context, List<Vendor> vendorList) {
        this.context = context;
        this.vendorList = vendorList;
    }

    @NonNull
    @Override
    public VendorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vendor, parent, false);
        return new VendorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VendorViewHolder holder, int position) {
        Vendor vendor = vendorList.get(position);
        holder.nameText.setText(vendor.name);
        holder.categoryText.setText(vendor.category != null ? vendor.category : "N/A");
        holder.vegText.setText(vendor.is_veg ? "Veg" : "Non-Veg");
        holder.ratingBar.setRating(vendor.rating);
        holder.hygieneText.setText("Hygiene: " + vendor.hygiene_score);
        holder.verifiedText.setVisibility(vendor.is_verified ? View.VISIBLE : View.GONE);

        // Load first photo if available
        if (vendor.photo_urls != null && !vendor.photo_urls.isEmpty()) {
            Picasso.get().load(vendor.photo_urls.get(0)).into(holder.photoView);
        } else {
            holder.photoView.setImageResource(R.drawable.no_image); // Placeholder image
        }
    }

    @Override
    public int getItemCount() {
        return vendorList.size();
    }

    public static class VendorViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, categoryText, vegText, hygieneText, verifiedText;
        RatingBar ratingBar;
        ImageView photoView;

        public VendorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.vendor_name);
            categoryText = itemView.findViewById(R.id.vendor_category);
            vegText = itemView.findViewById(R.id.vendor_veg_status);
            hygieneText = itemView.findViewById(R.id.vendor_hygiene);
            verifiedText = itemView.findViewById(R.id.vendor_verified);
            ratingBar = itemView.findViewById(R.id.vendor_rating_bar);
            photoView = itemView.findViewById(R.id.vendor_photo);
        }
    }
}
