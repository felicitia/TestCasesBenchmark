package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishBrandedFeedInfo extends BaseModel implements Parcelable {
    public static final Creator<WishBrandedFeedInfo> CREATOR = new Creator<WishBrandedFeedInfo>() {
        public WishBrandedFeedInfo createFromParcel(Parcel parcel) {
            return new WishBrandedFeedInfo(parcel);
        }

        public WishBrandedFeedInfo[] newArray(int i) {
            return new WishBrandedFeedInfo[i];
        }
    };
    private String mBannerText;
    private String mBannerTextSubtitle;
    private ArrayList<WishCategory> mBrandedCategories;
    private boolean mShowBanner;

    public int describeContents() {
        return 0;
    }

    public WishBrandedFeedInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        if (JsonUtil.hasValue(jSONObject, "branded_categories")) {
            this.mBrandedCategories = JsonUtil.parseArray(jSONObject, "branded_categories", new DataParser<WishCategory, JSONObject>() {
                public WishCategory parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishCategory(jSONObject);
                }
            });
        }
        this.mShowBanner = jSONObject.getBoolean("show_banner");
        if (ExperimentDataCenter.getInstance().shouldSeeNewLayoutOutletBanner()) {
            this.mBannerText = jSONObject.optString("branded_banner_text", WishApplication.getInstance().getString(R.string.brand_banner_text));
            this.mBannerTextSubtitle = jSONObject.optString("branded_banner_text_subtitle", WishApplication.getInstance().getString(R.string.brand_banner_subtitle));
            return;
        }
        this.mBannerText = jSONObject.optString("branded_banner_text", WishApplication.getInstance().getString(R.string.brand_banner));
    }

    protected WishBrandedFeedInfo(Parcel parcel) {
        this.mBrandedCategories = parcel.createTypedArrayList(WishCategory.CREATOR);
        this.mShowBanner = parcel.readByte() != 0;
    }

    public ArrayList<WishCategory> getBrandedCategories() {
        return this.mBrandedCategories;
    }

    public boolean shouldShowBanner() {
        return this.mShowBanner;
    }

    public String getBannerText() {
        if (this.mBannerText == null || this.mBannerText.isEmpty()) {
            return WishApplication.getInstance().getString(R.string.brand_banner_text);
        }
        return this.mBannerText;
    }

    public String getBannerTextSubtitle() {
        if (this.mBannerTextSubtitle == null || this.mBannerTextSubtitle.isEmpty()) {
            return WishApplication.getInstance().getString(R.string.brand_banner_subtitle);
        }
        return this.mBannerTextSubtitle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mBrandedCategories);
        parcel.writeByte(this.mShowBanner ? (byte) 1 : 0);
    }
}
