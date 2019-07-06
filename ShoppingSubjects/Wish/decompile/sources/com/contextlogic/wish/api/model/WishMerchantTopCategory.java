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

public class WishMerchantTopCategory extends BaseModel implements Parcelable {
    public static final Creator<WishMerchantTopCategory> CREATOR = new Creator<WishMerchantTopCategory>() {
        public WishMerchantTopCategory createFromParcel(Parcel parcel) {
            return new WishMerchantTopCategory(parcel);
        }

        public WishMerchantTopCategory[] newArray(int i) {
            return new WishMerchantTopCategory[i];
        }
    };
    private int mCategoryItemCount;
    private String mCategoryTitle;
    private ArrayList<String> mTags;
    private ArrayList<WishProduct> mTopCategoryProducts;

    public int describeContents() {
        return 0;
    }

    public WishMerchantTopCategory(String str, int i, ArrayList<String> arrayList, ArrayList<WishProduct> arrayList2) {
        this.mCategoryTitle = str;
        this.mCategoryItemCount = i;
        this.mTags = arrayList;
        this.mTopCategoryProducts = arrayList2;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mCategoryTitle = jSONObject.getString("name");
        this.mTopCategoryProducts = JsonUtil.parseArray(jSONObject, "products", new DataParser<WishProduct, JSONObject>() {
            public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishProduct(jSONObject);
            }
        });
    }

    public static ArrayList<WishMerchantTopCategory> getTopCategoriesFromResponse(JSONObject jSONObject) {
        return JsonUtil.parseArray(jSONObject, "top_categories", new DataParser<WishMerchantTopCategory, JSONObject>() {
            public WishMerchantTopCategory parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishMerchantTopCategory(jSONObject.getString("name"), jSONObject.getInt("item_count"), JsonUtil.parseArray(jSONObject, "tags", new DataParser<String, String>() {
                    public String parseData(String str) throws JSONException, ParseException {
                        return str;
                    }
                }), JsonUtil.parseArray(jSONObject, "products", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProduct(jSONObject);
                    }
                }));
            }
        });
    }

    protected WishMerchantTopCategory(Parcel parcel) {
        this.mCategoryTitle = parcel.readString();
        this.mCategoryItemCount = parcel.readInt();
        this.mTags = parcel.createStringArrayList();
        this.mTopCategoryProducts = parcel.createTypedArrayList(WishProduct.CREATOR);
    }

    public String getCategoryTitle() {
        return this.mCategoryTitle;
    }

    public int getCategoryItemCount() {
        return this.mCategoryItemCount;
    }

    public ArrayList<String> getTags() {
        return this.mTags;
    }

    public ArrayList<WishProduct> getTopCategoryProducts() {
        return this.mTopCategoryProducts;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCategoryTitle);
        parcel.writeInt(this.mCategoryItemCount);
        parcel.writeStringList(this.mTags);
        parcel.writeTypedList(this.mTopCategoryProducts);
    }
}
