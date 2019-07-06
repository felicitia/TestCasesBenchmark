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

public class WishCategory extends BaseModel implements Parcelable {
    public static final Creator<WishCategory> CREATOR = new Creator<WishCategory>() {
        public WishCategory createFromParcel(Parcel parcel) {
            return new WishCategory(parcel);
        }

        public WishCategory[] newArray(int i) {
            return new WishCategory[i];
        }
    };
    private ArrayList<WishCategory> mChildren;
    private String mFilterId;
    private String mImageUrl;
    private String mName;

    public int describeContents() {
        return 0;
    }

    public WishCategory(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mName = jSONObject.getString("name");
        this.mFilterId = jSONObject.getString("filter_id");
        this.mImageUrl = JsonUtil.optString(jSONObject, "image_url");
        this.mChildren = JsonUtil.parseArray(jSONObject, "children", new DataParser<WishCategory, JSONObject>() {
            public WishCategory parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishCategory(jSONObject);
            }
        });
    }

    protected WishCategory(Parcel parcel) {
        this.mChildren = parcel.createTypedArrayList(CREATOR);
        this.mImageUrl = parcel.readString();
        this.mFilterId = parcel.readString();
        this.mName = parcel.readString();
    }

    public String getName() {
        return this.mName;
    }

    public String getFilterId() {
        return this.mFilterId;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mChildren);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mFilterId);
        parcel.writeString(this.mName);
    }
}
