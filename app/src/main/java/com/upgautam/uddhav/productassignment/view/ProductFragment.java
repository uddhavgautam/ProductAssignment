package com.upgautam.uddhav.productassignment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 11/11/2017.
 */

public class ProductFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "product_id";

    /**
     * Creates product fragment for specific product ID
     */
    public static ProductFragment forProduct(int productId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, productId);
        fragment.setArguments(args);
        return fragment;
    }
}
