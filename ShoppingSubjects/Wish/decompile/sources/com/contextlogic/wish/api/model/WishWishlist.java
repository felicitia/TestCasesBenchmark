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

public class WishWishlist extends BaseModel implements Parcelable {
    public static final Creator<WishWishlist> CREATOR = new Creator<WishWishlist>() {
        public WishWishlist createFromParcel(Parcel parcel) {
            return new WishWishlist(parcel);
        }

        public WishWishlist[] newArray(int i) {
            return new WishWishlist[i];
        }
    };
    private String mName;
    private String mPermalink;
    private boolean mPrivate;
    private int mProductCount;
    private ArrayList<WishProduct> mProductPreviews;
    private WishUser mUser;
    private String mUserId;
    private int mViews;
    private String mWishlistId;

    public int describeContents() {
        return 0;
    }

    public WishWishlist(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mWishlistId = jSONObject.getString("id");
        this.mName = jSONObject.getString("name");
        this.mProductCount = jSONObject.getInt("size");
        this.mUserId = jSONObject.getString("user_id");
        this.mViews = jSONObject.optInt("views", 0);
        this.mProductPreviews = JsonUtil.parseArray(jSONObject, "preview", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException {
                return new WishProduct(jSONObject.getString("id"), jSONObject.getString("img"));
            }
        });
        if (JsonUtil.hasValue(jSONObject, "permalink")) {
            this.mPermalink = jSONObject.getString("permalink");
        }
        if (JsonUtil.hasValue(jSONObject, "private")) {
            this.mPrivate = jSONObject.getBoolean("private");
        }
        if (JsonUtil.hasValue(jSONObject, "user")) {
            this.mUser = new WishUser(jSONObject.getJSONObject("user"));
        }
    }

    protected WishWishlist(Parcel parcel) {
        this.mProductCount = parcel.readInt();
        this.mProductPreviews = parcel.createTypedArrayList(WishProduct.CREATOR);
        this.mName = parcel.readString();
        this.mPermalink = parcel.readString();
        this.mWishlistId = parcel.readString();
        this.mUserId = parcel.readString();
        this.mPrivate = parcel.readByte() != 0;
        this.mUser = (WishUser) parcel.readParcelable(WishUser.class.getClassLoader());
    }

    public String getWishlistId() {
        return this.mWishlistId;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public ArrayList<WishProduct> getProductPreviews() {
        return this.mProductPreviews;
    }

    public int getProductCount() {
        return this.mProductCount;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getPermalink() {
        return this.mPermalink;
    }

    public WishUser getUserObject() {
        return this.mUser;
    }

    public int getViewCount() {
        return this.mViews;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mProductCount);
        parcel.writeTypedList(this.mProductPreviews);
        parcel.writeString(this.mName);
        parcel.writeString(this.mPermalink);
        parcel.writeString(this.mWishlistId);
        parcel.writeString(this.mUserId);
        parcel.writeByte(this.mPrivate ? (byte) 1 : 0);
        parcel.writeParcelable(this.mUser, i);
    }

    public boolean isPrivate() {
        return this.mPrivate;
    }

    public void setPrivate(boolean z) {
        this.mPrivate = z;
    }

    public void setUserObject(WishUser wishUser) {
        this.mUser = wishUser;
    }
}
