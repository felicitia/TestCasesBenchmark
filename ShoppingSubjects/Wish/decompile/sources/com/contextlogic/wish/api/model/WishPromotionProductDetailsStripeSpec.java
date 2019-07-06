package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPromotionProductDetailsStripeSpec extends BaseModel implements Parcelable {
    public static final Creator<WishPromotionProductDetailsStripeSpec> CREATOR = new Creator<WishPromotionProductDetailsStripeSpec>() {
        public WishPromotionProductDetailsStripeSpec createFromParcel(Parcel parcel) {
            return new WishPromotionProductDetailsStripeSpec(parcel);
        }

        public WishPromotionProductDetailsStripeSpec[] newArray(int i) {
            return new WishPromotionProductDetailsStripeSpec[i];
        }
    };
    private String mProductDetailsText;

    public int describeContents() {
        return 0;
    }

    public WishPromotionProductDetailsStripeSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishPromotionProductDetailsStripeSpec(Parcel parcel) {
        this.mProductDetailsText = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mProductDetailsText);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mProductDetailsText = JsonUtil.optString(jSONObject, "product_details_text");
    }

    public String getProductDetailsText() {
        return this.mProductDetailsText;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishPromotionProductDetailsStripeSpec wishPromotionProductDetailsStripeSpec = (WishPromotionProductDetailsStripeSpec) obj;
        if (this.mProductDetailsText != null) {
            z = this.mProductDetailsText.equals(wishPromotionProductDetailsStripeSpec.mProductDetailsText);
        } else if (wishPromotionProductDetailsStripeSpec.mProductDetailsText != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.mProductDetailsText != null) {
            return this.mProductDetailsText.hashCode();
        }
        return 0;
    }
}
