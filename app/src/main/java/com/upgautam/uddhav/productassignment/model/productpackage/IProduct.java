package com.upgautam.uddhav.productassignment.model.productpackage;

import com.upgautam.uddhav.productassignment.model.storepackage.Store;

import java.util.List;

/**
 * Created by Administrator on 11/10/2017.
 */

interface IProduct {

    Integer getmId();

    String getmName();

    void setmName(String mName);

    String getmDescription();

    void setmDescription(String mDescription);

    Double getmRegPrice();

    void setmRegPrice(Double mRegPrice);

    Double getmSalePrice();

    void setmSalePrice(Double mSalePrice);

    List<String> getmColors();

    void setmColors(List<String> mColors);

    List<Store> getmStores();

    void setmStores(List<Store> mStores);


    public class ProductBuilder {

    }
}
