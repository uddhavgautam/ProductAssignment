package com.upgautam.uddhav.productassignment.model.storepackage;

/**
 * Created by Administrator on 11/10/2017.
 */

//Store has a Product. HAS-A relationship between Store and Product
public class Store implements IStore {
    //fields can't be null, which I validate in StoreBuilder class

    private String mName;
    private String mAddress;
    private Integer mId;


    //default but private constructor. This restricts other class to crete Store via StoreBuilder
    private Store() {
        //generate unique id and assign it to mId
        mId = generateUniqueId();
    }

    public void provideStoreDetails(String mName, String mAddress) {
        //initialization
        this.mName = mName;
        this.mAddress = mAddress;
    }

    private Integer generateUniqueId() {
        if (getmId() == null) {
            mId = 1;
            return mId;
        }
        this.mId++;
        return mId;
    }


    @Override
    public Integer getmId() {
        return this.mId;
    }

    //getters/setters. No validation rules. Just to indirectly get/set the fields
    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String mName) {
        this.mName = mName;
    }

    @Override
    public String getAddress() {
        return mAddress;
    }

    @Override
    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public class StoreBuilder {
       /*
   This class is responsible to create the Store    */

        private StoreUtils mStoreUtils = new StoreUtils();
        private Store mStore;

        private boolean mStoreInitialized;

        //1st call
        // default constructor
        public StoreBuilder() {
            this.mStore = new Store();
        }

        //2nd call. We provide all product's details
        public void provideStoreDetails(String mName, String mAddress) {
            //validation
            this.mStoreUtils.validateParameterString(mName);
            this.mStoreUtils.validateParameterString(mAddress);

            this.mStore.provideStoreDetails(mName, mAddress);

            this.mStoreInitialized = true;

        }

        //final call
        //final build method
        public Store build() {
            //I must check that provideProductDetails() needs to be called before I return mProduct
            if (this.mStoreInitialized) {
                return this.mStore;
            } else
                throw new IllegalArgumentException("Please provide Stores's name and address first!");
        }

    }

}
