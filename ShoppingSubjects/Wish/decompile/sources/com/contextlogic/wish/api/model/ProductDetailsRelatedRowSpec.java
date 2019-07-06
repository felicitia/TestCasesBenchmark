package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailsRelatedRowSpec extends BaseModel implements Parcelable {
    public static final Creator<ProductDetailsRelatedRowSpec> CREATOR = new Creator<ProductDetailsRelatedRowSpec>() {
        public ProductDetailsRelatedRowSpec createFromParcel(Parcel parcel) {
            return new ProductDetailsRelatedRowSpec(parcel);
        }

        public ProductDetailsRelatedRowSpec[] newArray(int i) {
            return new ProductDetailsRelatedRowSpec[i];
        }
    };
    private String mFeedTitle;
    private boolean mHideViewAllButton;
    private int mImpressionEvent;
    private List<WishProduct> mProducts;
    private String mRowTitle;
    private int mViewAllClickEvent;

    public int describeContents() {
        return 0;
    }

    public ProductDetailsRelatedRowSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mRowTitle = jSONObject.optString("title_text");
        this.mProducts = JsonUtil.parseArray(jSONObject, "items", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishProduct(jSONObject);
            }
        });
        this.mFeedTitle = jSONObject.optString("feed_title");
        this.mViewAllClickEvent = jSONObject.optInt("view_all_click_event");
        this.mHideViewAllButton = jSONObject.optBoolean("hide_view_all", false);
        this.mImpressionEvent = jSONObject.optInt("impression_event", -1);
    }

    public List<WishProduct> getProducts() {
        return this.mProducts;
    }

    public String getRowTitle() {
        return this.mRowTitle;
    }

    public String getFeedTitle() {
        return this.mFeedTitle;
    }

    public int getViewAllClickEvent() {
        return this.mViewAllClickEvent;
    }

    public int getImpressionEvent() {
        return this.mImpressionEvent;
    }

    public boolean hideViewAll() {
        return this.mHideViewAllButton;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRowTitle);
        parcel.writeTypedList(this.mProducts);
        parcel.writeString(this.mFeedTitle);
        parcel.writeInt(this.mViewAllClickEvent);
        parcel.writeByte(this.mHideViewAllButton ? (byte) 1 : 0);
        parcel.writeInt(this.mImpressionEvent);
    }

    protected ProductDetailsRelatedRowSpec(Parcel parcel) {
        this.mRowTitle = parcel.readString();
        this.mProducts = parcel.createTypedArrayList(WishProduct.CREATOR);
        this.mFeedTitle = parcel.readString();
        this.mViewAllClickEvent = parcel.readInt();
        this.mHideViewAllButton = parcel.readByte() != 0;
        this.mImpressionEvent = parcel.readInt();
    }
}
