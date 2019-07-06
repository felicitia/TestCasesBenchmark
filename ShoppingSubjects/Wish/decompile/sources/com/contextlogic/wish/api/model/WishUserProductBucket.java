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

public class WishUserProductBucket extends BaseModel implements Parcelable {
    public static final Creator<WishUserProductBucket> CREATOR = new Creator<WishUserProductBucket>() {
        public WishUserProductBucket createFromParcel(Parcel parcel) {
            return new WishUserProductBucket(parcel);
        }

        public WishUserProductBucket[] newArray(int i) {
            return new WishUserProductBucket[i];
        }
    };
    private String mBucketId;
    private boolean mEditable;
    private boolean mModifiable;
    private String mName;
    private String mPermalink;
    private int mProductCount;
    private ArrayList<WishProduct> mProductPreviews;
    private String mSetId;
    private WishTag mTag;
    private String mUserId;
    private String mUserName;

    public int describeContents() {
        return 0;
    }

    public WishUserProductBucket(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mProductCount = jSONObject.getInt("size");
        this.mName = jSONObject.getString("name");
        this.mSetId = jSONObject.getString("set_id");
        this.mBucketId = jSONObject.getString("bid");
        this.mProductPreviews = new ArrayList<>();
        this.mProductPreviews = JsonUtil.parseArray(jSONObject, "preview", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException {
                return new WishProduct(jSONObject.getString("id"), jSONObject.getString("img"));
            }
        });
        if (jSONObject.has("tag")) {
            this.mTag = new WishTag(jSONObject.getJSONObject("tag"));
        }
        if (JsonUtil.hasValue(jSONObject, "permalink")) {
            this.mPermalink = jSONObject.getString("permalink");
        }
        this.mEditable = jSONObject.optBoolean("editable_by_user");
        this.mModifiable = jSONObject.optBoolean("modifiable_by_user");
    }

    protected WishUserProductBucket(Parcel parcel) {
        boolean z = false;
        this.mEditable = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mModifiable = z;
        this.mProductCount = parcel.readInt();
        this.mProductPreviews = parcel.createTypedArrayList(WishProduct.CREATOR);
        this.mBucketId = parcel.readString();
        this.mName = parcel.readString();
        this.mPermalink = parcel.readString();
        this.mSetId = parcel.readString();
        this.mUserId = parcel.readString();
        this.mUserName = parcel.readString();
        this.mTag = (WishTag) parcel.readParcelable(WishTag.class.getClassLoader());
    }

    public int getProductCount() {
        return this.mProductCount;
    }

    public WishTag getTag() {
        return this.mTag;
    }

    public void setUserName(String str) {
        this.mUserName = str;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mEditable ? (byte) 1 : 0);
        parcel.writeByte(this.mModifiable ? (byte) 1 : 0);
        parcel.writeInt(this.mProductCount);
        parcel.writeTypedList(this.mProductPreviews);
        parcel.writeString(this.mBucketId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mPermalink);
        parcel.writeString(this.mSetId);
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mUserName);
        parcel.writeParcelable(this.mTag, 0);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishUserProductBucket wishUserProductBucket = (WishUserProductBucket) obj;
        if (this.mEditable != wishUserProductBucket.mEditable || this.mModifiable != wishUserProductBucket.mModifiable || this.mProductCount != wishUserProductBucket.mProductCount) {
            return false;
        }
        if (this.mProductPreviews == null ? wishUserProductBucket.mProductPreviews != null : !this.mProductPreviews.equals(wishUserProductBucket.mProductPreviews)) {
            return false;
        }
        if (this.mBucketId == null ? wishUserProductBucket.mBucketId != null : !this.mBucketId.equals(wishUserProductBucket.mBucketId)) {
            return false;
        }
        if (this.mName == null ? wishUserProductBucket.mName != null : !this.mName.equals(wishUserProductBucket.mName)) {
            return false;
        }
        if (this.mPermalink == null ? wishUserProductBucket.mPermalink != null : !this.mPermalink.equals(wishUserProductBucket.mPermalink)) {
            return false;
        }
        if (this.mSetId == null ? wishUserProductBucket.mSetId != null : !this.mSetId.equals(wishUserProductBucket.mSetId)) {
            return false;
        }
        if (this.mUserId == null ? wishUserProductBucket.mUserId != null : !this.mUserId.equals(wishUserProductBucket.mUserId)) {
            return false;
        }
        if (this.mUserName == null ? wishUserProductBucket.mUserName != null : !this.mUserName.equals(wishUserProductBucket.mUserName)) {
            return false;
        }
        if (this.mTag != null) {
            z = this.mTag.equals(wishUserProductBucket.mTag);
        } else if (wishUserProductBucket.mTag != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((((this.mEditable ? 1 : 0) * true) + (this.mModifiable ? 1 : 0)) * 31) + this.mProductCount) * 31) + (this.mProductPreviews != null ? this.mProductPreviews.hashCode() : 0)) * 31) + (this.mBucketId != null ? this.mBucketId.hashCode() : 0)) * 31) + (this.mName != null ? this.mName.hashCode() : 0)) * 31) + (this.mPermalink != null ? this.mPermalink.hashCode() : 0)) * 31) + (this.mSetId != null ? this.mSetId.hashCode() : 0)) * 31) + (this.mUserId != null ? this.mUserId.hashCode() : 0)) * 31) + (this.mUserName != null ? this.mUserName.hashCode() : 0)) * 31;
        if (this.mTag != null) {
            i = this.mTag.hashCode();
        }
        return hashCode + i;
    }
}
