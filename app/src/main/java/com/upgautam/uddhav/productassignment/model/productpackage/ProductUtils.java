package com.upgautam.uddhav.productassignment.model.productpackage;

import android.media.Image;

import com.upgautam.uddhav.productassignment.model.Utils;
import com.upgautam.uddhav.productassignment.model.storepackage.Store;

import java.util.List;

/**
 * Created by Administrator on 11/10/2017.
 */

public class ProductUtils implements Utils {

    @Override
    public void validateParameterString(String value) {
        if (!("".equals(value)) && !(value == null)) {
            //value is valid
        } else {
            throw new IllegalArgumentException("name is either null or blank!");
        }
    }


    public void validateColors(List<String> colors) { //Whenever we delete element from List, it does defragment list again.
        if (!("".equals(colors.get(0))) && !(colors.get(0) == null)) {
            //there is at least one color provided
        } else {
            throw new IllegalArgumentException("colors is either null or blank!");
        }
    }

    public void validateParameterPrice(Double price) {
        if (!("".equals(price)) && !(price == null)) {
            if (price >= 0.0) {
                //valid price
            } else {
                throw new IllegalArgumentException("price can't be negative!");
            }
        } else {
            throw new IllegalArgumentException("price is either null or blank!");
        }
    }


    public void validateParameterStores(List<Store> mStores) {
        if (!("".equals(mStores.get(0))) && !(mStores.get(0) == null)) {
            //there is at least one Stores provided
        } else {
            throw new IllegalArgumentException("Store is either null or blank!");
        }
    }

    public void validateImage(Image mImage) {

    }
}
