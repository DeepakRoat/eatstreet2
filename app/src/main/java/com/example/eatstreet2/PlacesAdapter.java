package com.example.eatstreet2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import com.bumptech.glide.Glide;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private final List<PlaceItem> placeList;
    private final Context context;
    public PlacesAdapter(List<PlaceItem> placeList, Context context) {
        this.context = context;
        this.placeList = placeList;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, ratingText;
        ImageView photoImageView;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.place_name);
            ratingText = itemView.findViewById(R.id.place_rating);
            photoImageView = itemView.findViewById(R.id.place_photo);

            itemView.setOnClickListener(v -> {
                int position = getAbsoluteAdapterPosition();
                if(position != RecyclerView.NO_POSITION) {
                    PlaceItem clickedPlace = placeList.get(position);

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("place_name", clickedPlace.name);
                    intent.putExtra("place_rating", clickedPlace.rating);
                    intent.putExtra("place_photo", clickedPlace.photoReference);
                    context.startActivity(intent);
                }
            });
        }

        void bind(PlaceItem place) {
            nameText.setText(place.name);
        }
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        PlaceItem item = placeList.get(position);
        String photoRef = item.photoReference;
        holder.nameText.setText(item.name);
        holder.ratingText.setText("Rating: " + item.rating);

        if(!photoRef.isEmpty()) {
            String photoUrl = "https://maps.googleapis.com/maps/api/place/photo"
                    + "?maxwidth=400"
                    + "&photo_reference=" + photoRef
                    + "&key=" + this.context.getString(R.string.maps_api_key);

            Glide.with(this.context)
                    .load(photoUrl)
                    .into(holder.photoImageView);
        } else {
            holder.photoImageView.setImageResource(R.drawable.no_image);
        }
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }
}
