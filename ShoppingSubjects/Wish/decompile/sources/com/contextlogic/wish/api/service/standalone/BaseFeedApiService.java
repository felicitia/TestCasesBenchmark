package com.contextlogic.wish.api.service.standalone;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCategory;
import com.contextlogic.wish.api.model.WishCollection;
import com.contextlogic.wish.api.model.WishCredit;
import com.contextlogic.wish.api.model.WishMerchant;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseFeedApiService<T> extends SingleApiService {

    public static class FeedExtraDataBundle implements Parcelable {
        public static final Creator<FeedExtraDataBundle> CREATOR = new Creator<FeedExtraDataBundle>() {
            public FeedExtraDataBundle createFromParcel(Parcel parcel) {
                return new FeedExtraDataBundle(parcel);
            }

            public FeedExtraDataBundle[] newArray(int i) {
                return new FeedExtraDataBundle[i];
            }
        };
        public String bannerText;
        public ArrayList<WishCategory> categories;
        public ArrayList<WishCollection> collections;
        public String collectionsTitle;
        public WishCredit credit;
        public WishMerchant merchant;
        public String navigationTitle;
        public int totalItemCount;

        public int describeContents() {
            return 0;
        }

        public FeedExtraDataBundle() {
        }

        protected FeedExtraDataBundle(Parcel parcel) {
            this.navigationTitle = parcel.readString();
            this.bannerText = parcel.readString();
            this.credit = (WishCredit) parcel.readParcelable(WishCredit.class.getClassLoader());
            this.totalItemCount = parcel.readInt();
            this.categories = parcel.createTypedArrayList(WishCategory.CREATOR);
            this.collections = parcel.createTypedArrayList(WishCollection.CREATOR);
            this.collectionsTitle = parcel.readString();
            this.merchant = (WishMerchant) parcel.readParcelable(WishMerchant.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.navigationTitle);
            parcel.writeString(this.bannerText);
            parcel.writeParcelable(this.credit, 0);
            parcel.writeInt(this.totalItemCount);
            parcel.writeTypedList(this.categories);
            parcel.writeTypedList(this.collections);
            parcel.writeString(this.collectionsTitle);
            parcel.writeParcelable(this.merchant, 0);
        }
    }

    public interface SuccessCallback<T> {
        void onSuccess(ArrayList<T> arrayList, boolean z, int i, FeedExtraDataBundle feedExtraDataBundle);
    }

    /* access modifiers changed from: protected */
    public void parseSuccess(ApiResponse apiResponse, ArrayList<T> arrayList, int i, boolean z, SuccessCallback successCallback) throws JSONException, ParseException {
        final FeedExtraDataBundle feedExtraDataBundle = new FeedExtraDataBundle();
        feedExtraDataBundle.collectionsTitle = JsonUtil.optString(apiResponse.getData(), "collections_title");
        feedExtraDataBundle.bannerText = JsonUtil.optString(apiResponse.getData(), "banner_title");
        feedExtraDataBundle.navigationTitle = JsonUtil.optString(apiResponse.getData(), "navigation_title", JsonUtil.optString(apiResponse.getData(), "tag"));
        feedExtraDataBundle.totalItemCount = apiResponse.getData().optInt("num_found");
        feedExtraDataBundle.collections = JsonUtil.parseArray(apiResponse.getData(), "collections", new DataParser<WishCollection, JSONObject>() {
            public WishCollection parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishCollection(jSONObject);
            }
        });
        feedExtraDataBundle.categories = JsonUtil.parseArray(apiResponse.getData(), "categories", new DataParser<WishCategory, JSONObject>() {
            public WishCategory parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishCategory(jSONObject);
            }
        });
        if (JsonUtil.hasValue(apiResponse.getData(), "merchant_info")) {
            feedExtraDataBundle.merchant = new WishMerchant(apiResponse.getData().getJSONObject("merchant_info"));
        }
        if (JsonUtil.hasValue(apiResponse.getData(), "credit")) {
            feedExtraDataBundle.credit = new WishCredit(apiResponse.getData().getJSONObject("credit"));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof WishProduct) {
                    ((WishProduct) next).setCredit(feedExtraDataBundle.credit);
                }
            }
        }
        if (successCallback != null) {
            final SuccessCallback successCallback2 = successCallback;
            final ArrayList<T> arrayList2 = arrayList;
            final boolean z2 = z;
            final int i2 = i;
            AnonymousClass3 r0 = new Runnable() {
                public void run() {
                    successCallback2.onSuccess(arrayList2, z2, i2, feedExtraDataBundle);
                }
            };
            postRunnable(r0);
        }
    }

    /* access modifiers changed from: protected */
    public void parseFailure(ApiResponse apiResponse, final String str, final DefaultFailureCallback defaultFailureCallback) {
        if (defaultFailureCallback != null) {
            postRunnable(new Runnable() {
                public void run() {
                    defaultFailureCallback.onFailure(str);
                }
            });
        }
    }
}
