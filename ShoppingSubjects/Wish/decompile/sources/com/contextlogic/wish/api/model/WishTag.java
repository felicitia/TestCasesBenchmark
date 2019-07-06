package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.StringUtil;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishTag extends BaseModel implements Parcelable {
    public static final Creator<WishTag> CREATOR = new Creator<WishTag>() {
        public WishTag createFromParcel(Parcel parcel) {
            return new WishTag(parcel);
        }

        public WishTag[] newArray(int i) {
            return new WishTag[i];
        }
    };
    private String mName;
    private ArrayList<WishImage> mPreviewImages;
    private String mTagId;

    public int describeContents() {
        return 0;
    }

    public WishTag(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mPreviewImages = new ArrayList<>();
        this.mTagId = JsonUtil.optString(jSONObject, "id");
        if (jSONObject.has("name")) {
            this.mName = jSONObject.getString("name");
        } else {
            this.mName = jSONObject.getString("tag");
        }
        this.mName = StringUtil.toTitleCase(this.mName);
    }

    protected WishTag(Parcel parcel) {
        this.mPreviewImages = parcel.createTypedArrayList(WishImage.CREATOR);
        this.mName = parcel.readString();
        this.mTagId = parcel.readString();
    }

    public String getTagId() {
        return this.mTagId;
    }

    public String getName() {
        return this.mName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mPreviewImages);
        parcel.writeString(this.mName);
        parcel.writeString(this.mTagId);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishTag wishTag = (WishTag) obj;
        if (this.mPreviewImages == null ? wishTag.mPreviewImages != null : !this.mPreviewImages.equals(wishTag.mPreviewImages)) {
            return false;
        }
        if (this.mName == null ? wishTag.mName != null : !this.mName.equals(wishTag.mName)) {
            return false;
        }
        if (this.mTagId != null) {
            z = this.mTagId.equals(wishTag.mTagId);
        } else if (wishTag.mTagId != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.mPreviewImages != null ? this.mPreviewImages.hashCode() : 0) * 31) + (this.mName != null ? this.mName.hashCode() : 0)) * 31;
        if (this.mTagId != null) {
            i = this.mTagId.hashCode();
        }
        return hashCode + i;
    }
}
