package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSignupFreeGiftCart extends BaseModel implements Parcelable {
    public static final Creator<WishSignupFreeGiftCart> CREATOR = new Creator<WishSignupFreeGiftCart>() {
        public WishSignupFreeGiftCart createFromParcel(Parcel parcel) {
            return new WishSignupFreeGiftCart(parcel);
        }

        public WishSignupFreeGiftCart[] newArray(int i) {
            return new WishSignupFreeGiftCart[i];
        }
    };
    private String mAlmostDone;
    private WishCart mCart;
    private String mFreeGiftShippingAmount;
    private String mFreeGiftShippingText;
    private String mModalButtonText;
    private String mModalMessage;
    private String mModalTitle;
    private String mRedeemGiftText;
    private String mWhereToShipTitle;

    public int describeContents() {
        return 0;
    }

    public WishSignupFreeGiftCart(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mCart = new WishCart(jSONObject.getJSONObject("cart").getJSONObject("cart_info"));
        this.mFreeGiftShippingText = jSONObject.getString("free_gift_shipping_text");
        this.mModalTitle = jSONObject.getString("free_gift_modal_title");
        this.mModalMessage = jSONObject.getString("free_gift_modal_message");
        this.mModalButtonText = jSONObject.getString("free_gift_modal_button_text");
        this.mRedeemGiftText = jSONObject.getString("free_gift_redeem_gift_text");
        this.mWhereToShipTitle = jSONObject.getString("free_gift_where_to_ship_title");
        this.mAlmostDone = jSONObject.getString("free_gift_almost_done_text");
        this.mFreeGiftShippingAmount = jSONObject.optString("free_gift_shipping_amount", "");
    }

    protected WishSignupFreeGiftCart(Parcel parcel) {
        this.mFreeGiftShippingAmount = parcel.readString();
        this.mAlmostDone = parcel.readString();
        this.mFreeGiftShippingText = parcel.readString();
        this.mModalButtonText = parcel.readString();
        this.mModalMessage = parcel.readString();
        this.mModalTitle = parcel.readString();
        this.mRedeemGiftText = parcel.readString();
        this.mWhereToShipTitle = parcel.readString();
        this.mCart = (WishCart) parcel.readParcelable(WishCart.class.getClassLoader());
    }

    public WishCart getCart() {
        return this.mCart;
    }

    public String getFreeGiftShippingText() {
        return this.mFreeGiftShippingText;
    }

    public String getModalTitle() {
        return this.mModalTitle;
    }

    public String getModalMessage() {
        return this.mModalMessage;
    }

    public String getModalButtonText() {
        return this.mModalButtonText;
    }

    public String getWhereToShipTitle() {
        return this.mWhereToShipTitle;
    }

    public String getAlmostDone() {
        return this.mAlmostDone;
    }

    public String getShippingAmount() {
        return this.mFreeGiftShippingAmount;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mFreeGiftShippingAmount);
        parcel.writeString(this.mAlmostDone);
        parcel.writeString(this.mFreeGiftShippingText);
        parcel.writeString(this.mModalButtonText);
        parcel.writeString(this.mModalMessage);
        parcel.writeString(this.mModalTitle);
        parcel.writeString(this.mRedeemGiftText);
        parcel.writeString(this.mWhereToShipTitle);
        parcel.writeParcelable(this.mCart, 0);
    }
}
