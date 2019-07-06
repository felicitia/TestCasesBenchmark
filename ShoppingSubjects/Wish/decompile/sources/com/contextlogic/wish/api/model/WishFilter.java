package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishFilter extends BaseModel implements Parcelable {
    public static final Creator<WishFilter> CREATOR = new Creator<WishFilter>() {
        public WishFilter createFromParcel(Parcel parcel) {
            return new WishFilter(parcel);
        }

        public WishFilter[] newArray(int i) {
            return new WishFilter[i];
        }
    };
    private WishBrandedFeedInfo mBrandedFeedInfo;
    private ArrayList<WishFilterGroup> mChildFilterGroups;
    private String mFilterId;
    private String mName;
    private String mWishExpressBannerInfo;

    public int describeContents() {
        return 0;
    }

    public WishFilter(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mFilterId = jSONObject.getString("filter_id");
        if (JsonUtil.hasValue(jSONObject, "child_filter_groups")) {
            this.mChildFilterGroups = JsonUtil.parseArray(jSONObject, "child_filter_groups", new DataParser<WishFilterGroup, JSONObject>() {
                public WishFilterGroup parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new WishFilterGroup(jSONObject);
                }
            });
        }
        if (jSONObject.has("branded_feed_info")) {
            this.mBrandedFeedInfo = new WishBrandedFeedInfo(jSONObject.getJSONObject("branded_feed_info"));
        }
        this.mWishExpressBannerInfo = JsonUtil.optString(jSONObject, "wish_express_banner_info");
        this.mName = JsonUtil.optString(jSONObject, "name");
    }

    protected WishFilter(Parcel parcel) {
        this.mChildFilterGroups = parcel.createTypedArrayList(WishFilterGroup.CREATOR);
        this.mBrandedFeedInfo = (WishBrandedFeedInfo) parcel.readParcelable(WishBrandedFeedInfo.class.getClassLoader());
        this.mName = parcel.readString();
        this.mFilterId = parcel.readString();
    }

    public WishFilter(String str) {
        this(str, "");
    }

    public WishFilter(String str, String str2) {
        this.mFilterId = str;
        this.mName = str2;
    }

    public String getName() {
        return this.mName;
    }

    public String getFilterId() {
        return this.mFilterId;
    }

    public ArrayList<WishFilterGroup> getChildFilterGroups() {
        return this.mChildFilterGroups;
    }

    public WishBrandedFeedInfo getBrandedFeedInfo() {
        return this.mBrandedFeedInfo;
    }

    public String getWishExpressBannerInfo() {
        return this.mWishExpressBannerInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mChildFilterGroups);
        parcel.writeParcelable(this.mBrandedFeedInfo, 0);
        parcel.writeString(this.mName);
        parcel.writeString(this.mFilterId);
    }
}
