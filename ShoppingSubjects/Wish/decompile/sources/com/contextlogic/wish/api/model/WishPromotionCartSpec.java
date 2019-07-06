package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPromotionCartSpec extends BaseModel implements Parcelable {
    public static final Creator<WishPromotionCartSpec> CREATOR = new Creator<WishPromotionCartSpec>() {
        public WishPromotionCartSpec createFromParcel(Parcel parcel) {
            return new WishPromotionCartSpec(parcel);
        }

        public WishPromotionCartSpec[] newArray(int i) {
            return new WishPromotionCartSpec[i];
        }
    };
    private String mCartText;

    public int describeContents() {
        return 0;
    }

    public WishPromotionCartSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishPromotionCartSpec(Parcel parcel) {
        this.mCartText = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCartText);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mCartText = JsonUtil.optString(jSONObject, "cart_text");
    }

    public String getCartText() {
        return this.mCartText;
    }
}
