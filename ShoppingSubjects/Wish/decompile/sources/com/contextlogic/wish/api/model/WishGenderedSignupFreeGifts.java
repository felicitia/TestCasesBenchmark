package com.contextlogic.wish.api.model;

import android.os.Parcel;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishGenderedSignupFreeGifts extends WishSignupFreeGifts {
    private String mDefaultGenderTab;
    private List<WishProduct> mFemaleSignupGifts;
    private List<WishProduct> mMaleSignupGifts;

    public WishGenderedSignupFreeGifts(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        super.parseJson(jSONObject);
        this.mMaleSignupGifts = JsonUtil.parseArray(jSONObject, "items_male", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishProduct(jSONObject);
            }
        });
        this.mFemaleSignupGifts = JsonUtil.parseArray(jSONObject, "items_female", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishProduct(jSONObject);
            }
        });
        this.mDefaultGenderTab = jSONObject.optString("default_gender_tab", "female");
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.mMaleSignupGifts);
        parcel.writeTypedList(this.mFemaleSignupGifts);
        parcel.writeString(this.mDefaultGenderTab);
    }

    public List<WishProduct> getFemaleFreeGifts() {
        return this.mFemaleSignupGifts;
    }

    public List<WishProduct> getMaleFreeGifts() {
        return this.mMaleSignupGifts;
    }

    public String getDefaultGenderTab() {
        return this.mDefaultGenderTab;
    }
}
