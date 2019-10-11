package com.project.eubanksms.Models;

public class ItemDetail {
    private static final String TAG = "ItemDetail";

    private int imageResource;
    private String textDetail;
    private String textDate;

    public ItemDetail (int imageResource, String textDetail, String textDate){
        this.imageResource = imageResource;
        this.textDetail = textDetail;
        this.textDate = textDate;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTextDetail() {
        return textDetail;
    }

    public String getTextDate() {
        return textDate;
    }
}
