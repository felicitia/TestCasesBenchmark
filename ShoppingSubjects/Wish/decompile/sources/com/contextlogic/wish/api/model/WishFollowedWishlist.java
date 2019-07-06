package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishFollowedWishlist extends WishWishlist {
    public static final Creator<WishFollowedWishlist> CREATOR = new Creator<WishFollowedWishlist>() {
        public WishFollowedWishlist createFromParcel(Parcel parcel) {
            return new WishFollowedWishlist(parcel);
        }

        public WishFollowedWishlist[] newArray(int i) {
            return new WishFollowedWishlist[i];
        }
    };
    private int mNewProductsCount;

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        super.parseJson(jSONObject);
        this.mNewProductsCount = jSONObject.getInt("new_products");
    }

    public WishFollowedWishlist(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishFollowedWishlist(Parcel parcel) {
        super(parcel);
        this.mNewProductsCount = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mNewProductsCount);
    }

    public int getNewProductsCount() {
        return this.mNewProductsCount;
    }
}
