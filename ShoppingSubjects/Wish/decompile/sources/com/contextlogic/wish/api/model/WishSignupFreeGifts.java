package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishSignupFreeGifts extends BaseModel implements Parcelable {
    public static final Creator<WishSignupFreeGifts> CREATOR = new Creator<WishSignupFreeGifts>() {
        public WishSignupFreeGifts createFromParcel(Parcel parcel) {
            return new WishSignupFreeGifts(parcel);
        }

        public WishSignupFreeGifts[] newArray(int i) {
            return new WishSignupFreeGifts[i];
        }
    };
    private WishSignupFreeGiftsAbandon mAbandonInfo;
    private String mClaimGiftText;
    private String mMessage;
    private SIGNUP_FLOW mSignupFlow;
    private ArrayList<WishProduct> mSignupGifts;
    private String mSubtitle;
    private String mTitle;

    public enum SIGNUP_FLOW {
        FREE_GIFT
    }

    public int describeContents() {
        return 0;
    }

    public WishSignupFreeGifts(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mSignupGifts = JsonUtil.parseArray(jSONObject, "items", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishProduct(jSONObject);
            }
        });
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mSubtitle = jSONObject.getString("subtitle");
        this.mMessage = jSONObject.getString("message");
        this.mClaimGiftText = jSONObject.getString("claim_gift_text");
        this.mSignupFlow = SIGNUP_FLOW.FREE_GIFT;
        if (JsonUtil.hasValue(jSONObject, "abandon_modal")) {
            this.mAbandonInfo = new WishSignupFreeGiftsAbandon(jSONObject.getJSONObject("abandon_modal"));
        }
    }

    protected WishSignupFreeGifts(Parcel parcel) {
        this.mSignupGifts = parcel.createTypedArrayList(WishProduct.CREATOR);
        this.mClaimGiftText = parcel.readString();
        this.mMessage = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public ArrayList<WishProduct> getSignupGiftProducts() {
        return this.mSignupGifts;
    }

    public WishSignupFreeGiftsAbandon getAbandonInfo() {
        return this.mAbandonInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mSignupGifts);
        parcel.writeString(this.mClaimGiftText);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mTitle);
    }
}
