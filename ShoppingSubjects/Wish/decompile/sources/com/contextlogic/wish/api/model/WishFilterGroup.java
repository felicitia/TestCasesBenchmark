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

public class WishFilterGroup extends BaseModel implements Parcelable {
    public static final Creator<WishFilterGroup> CREATOR = new Creator<WishFilterGroup>() {
        public WishFilterGroup createFromParcel(Parcel parcel) {
            return new WishFilterGroup(parcel);
        }

        public WishFilterGroup[] newArray(int i) {
            return new WishFilterGroup[i];
        }
    };
    private ArrayList<WishFilter> mFilters;
    private String mIconUrl;
    private boolean mIsExclusive;
    private boolean mIsSubCategory;
    private String mName;

    public int describeContents() {
        return 0;
    }

    public WishFilterGroup(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mIsExclusive = jSONObject.optBoolean("is_exclusive", false);
        if (jSONObject.has("name")) {
            this.mName = jSONObject.getString("name");
        }
        if (jSONObject.has("icon_url")) {
            this.mIconUrl = jSONObject.getString("icon_url");
        }
        this.mFilters = JsonUtil.parseArray(jSONObject, "filters", new DataParser<WishFilter, JSONObject>() {
            public WishFilter parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishFilter(jSONObject);
            }
        });
        this.mIsSubCategory = jSONObject.optBoolean("is_sub_category", false);
    }

    protected WishFilterGroup(Parcel parcel) {
        boolean z = false;
        this.mIsExclusive = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mIsSubCategory = z;
        this.mFilters = parcel.createTypedArrayList(WishFilter.CREATOR);
        this.mIconUrl = parcel.readString();
        this.mName = parcel.readString();
    }

    public boolean isExclusive() {
        return this.mIsExclusive;
    }

    public boolean isSubCategory() {
        return this.mIsSubCategory;
    }

    public String getName() {
        return this.mName;
    }

    public ArrayList<WishFilter> getFilters() {
        return this.mFilters;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsExclusive ? (byte) 1 : 0);
        parcel.writeByte(this.mIsSubCategory ? (byte) 1 : 0);
        parcel.writeTypedList(this.mFilters);
        parcel.writeString(this.mIconUrl);
        parcel.writeString(this.mName);
    }
}
