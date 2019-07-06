package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPromotionAddToCartSpec extends BaseModel implements Parcelable {
    public static final Creator<WishPromotionAddToCartSpec> CREATOR = new Creator<WishPromotionAddToCartSpec>() {
        public WishPromotionAddToCartSpec createFromParcel(Parcel parcel) {
            return new WishPromotionAddToCartSpec(parcel);
        }

        public WishPromotionAddToCartSpec[] newArray(int i) {
            return new WishPromotionAddToCartSpec[i];
        }
    };
    private String mAddToCartText;

    public int describeContents() {
        return 0;
    }

    public WishPromotionAddToCartSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishPromotionAddToCartSpec(Parcel parcel) {
        this.mAddToCartText = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAddToCartText);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mAddToCartText = JsonUtil.optString(jSONObject, "add_to_cart_text");
    }

    public String getAddToCartText() {
        return this.mAddToCartText;
    }
}
