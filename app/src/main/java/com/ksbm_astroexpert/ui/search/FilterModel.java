package com.ksbm_astroexpert.ui.search;

public class FilterModel {

    private int ratingType;
    private int minPrice;
    private int maxPrice;

    public FilterModel(int ratingType, int minPrice, int maxPrice) {
        this.ratingType = ratingType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getRatingType() {
        return ratingType;
    }

    public void setRatingType(int ratingType) {
        this.ratingType = ratingType;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
}
