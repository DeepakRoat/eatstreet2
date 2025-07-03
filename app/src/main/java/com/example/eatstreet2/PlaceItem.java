package com.example.eatstreet2;

public class PlaceItem {
    public final String name;
    public final double rating;
    public String photoReference;
    public String placeId;

    public PlaceItem(String name, double rating, String photoReference, String placeId) {
        this.name = name;
        this.rating = rating;
        this.photoReference = photoReference;
        this.placeId = placeId;
    }
}
