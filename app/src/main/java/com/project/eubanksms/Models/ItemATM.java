package com.project.eubanksms.Models;

public class ItemATM {
    private static final String TAG = "ItemATM";

    private int imageResource;
    private String textATM;
    private String textStatus;
    private String textDate;

    public ItemATM (int imageResource, String textATM, String textStatus, String textDate){
        this.imageResource = imageResource;
        this.textATM = textATM;
        this.textStatus = textStatus;
        this.textDate = textDate;

    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTextATM() {
        return textATM;
    }

    public String getTextStatus() {
        return textStatus;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
