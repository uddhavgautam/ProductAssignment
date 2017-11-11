package com.upgautam.uddhav.productassignment.model.storepackage;

import com.upgautam.uddhav.productassignment.model.Utils;

/**
 * Created by Administrator on 11/10/2017.
 */

public class StoreUtils implements Utils {

    @Override
    public void validateParameterString(String string) {
        if (!("".equals(string)) && !(string == null)) {
            //value is valid
        } else {
            throw new IllegalArgumentException("name is either null or blank!");
        }
    }

}
