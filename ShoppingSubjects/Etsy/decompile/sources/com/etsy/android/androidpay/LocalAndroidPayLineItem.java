package com.etsy.android.androidpay;

import com.etsy.android.lib.core.EtsyMoney;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayLineItemContract;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.io.Serializable;
import org.parceler.Parcel;

@Parcel
public class LocalAndroidPayLineItem implements AndroidPayLineItemContract, Serializable {
    private static final long serialVersionUID = -5870694906460035836L;
    protected String currencyCode;
    protected String description;
    protected EtsyId listingId;
    protected EtsyMoney price;
    protected int quantity;
    protected int role;

    public EtsyId getListingId() {
        return this.listingId;
    }

    public void setListingId(EtsyId etsyId) {
        this.listingId = etsyId;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public EtsyMoney getPrice() {
        return this.price;
    }

    public void setPrice(EtsyMoney etsyMoney) {
        this.price = etsyMoney;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int getRole() {
        return this.role;
    }

    public void setRole(int i) {
        this.role = i;
    }
}
