package com.example.eatstreet2.models;

import java.util.List;

public class Vendor {
    public String distance="0";

    public int id;
    public String name="Unknown";
    public String description="";
    public String category="Not Defined";
    public boolean is_veg=true;
    public float rating=0;
    public List<String> photo_urls;
    public int price_level=1;
    public String vendor_name="Unknown";
    public String open_time="Unknown";
    public String close_time="Unknown";
    public List<String> reviews;
    public float hygiene_score=0;
    public boolean is_verified=false;
    public String create_at="Unknown";
    public String last_updated="Unknown";

}
