package com.upgautam.uddhav.productassignment.model.productpackage;

import android.media.Image;

import com.upgautam.uddhav.productassignment.model.storepackage.Store;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 11/10/2017.
 */

/* Each Class are designed based on SOLID principles:
Single Responsibility Principle
Open Closed Principle
Liskov's Substitution Principle
Interface Segregation Principle
Dependency Inversion Principle */


public class Product implements IProduct {

    private static final String DEFAULT_COLOR = "Transparent";
    // We should generate unique id for each product inside current process
    private Integer mId; //product id. Not provided by the user.

    //must provide, no blank, no null fields, , which I validate in ProductBuilder class
    private String mName; //product name
    private String mDescription; //product description
    private Double mRegPrice; //product regular price
    private Double mSalePrice; //product sale price

    //Decoration using image and colors. HAS-A property
    private List<String> mColors; //product's different colors
    private Image mImage; //product image

    //Optional HAS-A property
    private List<Store> mStores; //list of Stores

    //map or dictionary
    //I generate the id for the product first, find the store for the product and then only I map product and store
    private Map<Integer, Integer> mapProductStore; //maps Product ID and Store ID

    //default but private constructor. This restricts other class to crete Product via ProductBuilder
    private Product() {
        //generate unique id and assign it to mId
        mId = generateUniqueId();
    }

    private Integer generateUniqueId() {
        if (getmId() == null) {
            mId = 1;
            return mId;
        }
        this.mId++;
        return mId;
    }

    // initializes all must have fields
    private void provideProductDetails(String mName, String mDescription, Double mRegPrice, Double mSalePrice) {

        //initialization
        this.mName = mName;
        this.mDescription = mDescription;
        this.mRegPrice = mRegPrice;
        this.mSalePrice = mSalePrice;

        mColors.add(Product.DEFAULT_COLOR);
    }

    @Override
    public Integer getmId() {
        return mId;
    }

    @Override
    public String getmName() {
        return mName;
    }

    @Override
    public void setmName(String mName) {
        this.mName = mName;
    }

    @Override
    public String getmDescription() {
        return mDescription;
    }

    @Override
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public Double getmRegPrice() {
        return mRegPrice;
    }

    @Override
    public void setmRegPrice(Double mRegPrice) {
        this.mRegPrice = mRegPrice;
    }

    @Override
    public Double getmSalePrice() {
        return mSalePrice;
    }

    @Override
    public void setmSalePrice(Double mSalePrice) {
        this.mSalePrice = mSalePrice;
    }

    @Override
    public List<String> getmColors() {
        return mColors;
    }

    //decoration using colors
    @Override
    public void setmColors(List<String> mColors) {
        //initialization
        this.mColors = mColors;
    }

    @Override
    public List<Store> getmStores() {
        return mStores;
    }

    @Override
    public void setmStores(List<Store> mStores) {
        //initialization
        this.mStores = mStores;
    }

    public Image getmImage() {
        return mImage;
    }

    public void setmImage(Image mImage) {
        //initialization
        this.mImage = mImage;
    }

    //decoration change using color removing
    public void removeColor(String color) {
        mColors.remove(color);
    }

    public Map<Integer, Integer> getMapProductStore() {
        return mapProductStore;
    }

    public void setMapProductStore(List<Store> stores) {
        for (Store store : stores
                ) {
            //one product can be found on multiple store. 1 to many maps
            this.mapProductStore.put(this.getmId(), store.getmId());
        }

    }


    public class ProductBuilder {
        /*
   This class is responsible to create the Product in varieties of ways,
    or decorate the Product with necessary and optional fields later.
   Note: Every other classes should use this ProductBuilder to create Product instance.
    */

        /* First product building way:
            1) Call Product Constructor
            2) Setup colors if necessary
            3) Create Store and assign Stores for the product if necessary
            */

        private ProductUtils mProductUtils = new ProductUtils();
        private Product mProduct;

        private boolean mProductInitialized;
        private boolean mStoreAlreadySet;
        private List<Store> myStores;

        //1st call
        // default constructor
        public ProductBuilder() {
            this.mProduct = new Product();
        }

        //2nd call. We provide all product's details
        public void provideProductDetails(String productName, String productDesc, Double productRegPrice, Double productSalePrice) {
            //validation
            this.mProductUtils.validateParameterString(productName);
            this.mProductUtils.validateParameterString(productDesc);
            this.mProductUtils.validateParameterPrice(productRegPrice);
            this.mProductUtils.validateParameterPrice(productSalePrice);

            this.mProduct.provideProductDetails(productName, productDesc, productRegPrice, productSalePrice);

            this.mProductInitialized = true;

        }

        //final call
        //final build method
        public Product build() {
            //I must check that provideProductDetails() needs to be called before I return mProduct
            if (this.mProductInitialized) {
                return this.mProduct;
            } else
                throw new IllegalArgumentException("Please provide product's name, description, regular price, and sale price first!");
        }

        //optional calls
        public void setColors(List<String> colors) {
            if (colors.contains("Transparent")) {
                colors.remove("Transparent");

                //validation
                this.mProductUtils.validateColors(colors /* color list except Transparent */);
            }
            this.mProduct.setmColors(colors);
        }

        //decoration change using color removing
        public void removeColor(String color) {
            if (!"Transparent".equals(color)) {
                this.mProduct.removeColor(color);
            }
        }

        public void setmImage(Image mImage) {
            //validation
            this.mProductUtils.validateImage(mImage);

            this.mProduct.setmImage(mImage);
        }

        public void setmStores(List<Store> mStores) {
            this.mProductUtils.validateParameterStores(mStores);
            this.myStores = mStores;
            this.mProduct.setmStores(mStores);

            this.mStoreAlreadySet = true;
        }

        public void setMapProductStore() {
            if (this.mStoreAlreadySet && this.mProductInitialized) {
                this.mProduct.setMapProductStore(this.myStores);
            }
        }

    }
}
