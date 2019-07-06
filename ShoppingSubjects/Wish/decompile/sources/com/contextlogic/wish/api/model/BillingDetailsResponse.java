package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class BillingDetailsResponse extends BaseModel {
    public static final Creator<BillingDetailsResponse> CREATOR = new Creator<BillingDetailsResponse>() {
        public BillingDetailsResponse createFromParcel(Parcel parcel) {
            return new BillingDetailsResponse(parcel);
        }

        public BillingDetailsResponse[] newArray(int i) {
            return new BillingDetailsResponse[i];
        }
    };
    private PaymentProcessor mPaymentType;
    private WishUserBillingInfo mWishUserBillingInfo;

    public int describeContents() {
        return 0;
    }

    public BillingDetailsResponse(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public WishUserBillingInfo getWishUserBillingInfo() {
        return this.mWishUserBillingInfo;
    }

    public PaymentProcessor getPaymentType() {
        return this.mPaymentType;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mWishUserBillingInfo = new WishUserBillingInfo(jSONObject.getJSONObject("billing_details"));
        this.mPaymentType = PaymentProcessor.getCreditCardProcessor(jSONObject.getInt("payment_type"));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mWishUserBillingInfo, i);
        parcel.writeInt(this.mPaymentType == null ? -1 : this.mPaymentType.ordinal());
    }

    protected BillingDetailsResponse(Parcel parcel) {
        PaymentProcessor paymentProcessor;
        this.mWishUserBillingInfo = (WishUserBillingInfo) parcel.readParcelable(WishUserBillingInfo.class.getClassLoader());
        int readInt = parcel.readInt();
        if (readInt == -1) {
            paymentProcessor = null;
        } else {
            paymentProcessor = PaymentProcessor.values()[readInt];
        }
        this.mPaymentType = paymentProcessor;
    }
}
