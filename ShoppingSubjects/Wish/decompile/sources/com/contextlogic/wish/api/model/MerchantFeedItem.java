package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MerchantFeedItem extends BaseModel {
    public static final Creator<MerchantFeedItem> CREATOR = new Creator<MerchantFeedItem>() {
        public MerchantFeedItem createFromParcel(Parcel parcel) {
            return new MerchantFeedItem(parcel);
        }

        public MerchantFeedItem[] newArray(int i) {
            return new MerchantFeedItem[i];
        }
    };
    private String mDisplayName;
    private WishImage mDisplayPic;
    private String mMerchantId;
    private String mMerchantName;
    private List<WishProduct> mProducts;

    public int describeContents() {
        return 0;
    }

    public MerchantFeedItem(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public MerchantFeedItem(Parcel parcel) {
        this.mDisplayName = parcel.readString();
        this.mDisplayPic = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mDisplayName = parcel.readString();
        this.mMerchantName = parcel.readString();
        this.mProducts = parcel.createTypedArrayList(WishProduct.CREATOR);
    }

    public String getMerchantId() {
        return this.mMerchantId;
    }

    public WishImage getDisplayPic() {
        return this.mDisplayPic;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getMerchantName() {
        return this.mMerchantName;
    }

    public List<WishProduct> getProducts() {
        return this.mProducts;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mMerchantId = jSONObject.optString("merchant_id");
        String optString = jSONObject.optString("display_pic", null);
        if (optString != null) {
            this.mDisplayPic = new WishImage(optString);
        } else {
            this.mDisplayPic = null;
        }
        this.mDisplayName = jSONObject.optString("display_name");
        this.mMerchantName = jSONObject.optString("merchant_name");
        this.mProducts = JsonUtil.parseArray(jSONObject, "products", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishProduct(jSONObject);
            }
        });
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMerchantId);
        parcel.writeParcelable(this.mDisplayPic, 0);
        parcel.writeString(this.mDisplayName);
        parcel.writeString(this.mMerchantName);
        parcel.writeTypedList(this.mProducts);
    }
}
