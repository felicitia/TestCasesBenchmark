package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchLandingPageService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<String> arrayList, ArrayList<WishProduct> arrayList2, ArrayList<WishProduct> arrayList3);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("search/landing-page"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    SearchLandingPageService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                final ArrayList arrayList;
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "search_history", new DataParser<String, String>() {
                    public String parseData(String str) throws JSONException {
                        return str;
                    }
                });
                final ArrayList parseArray2 = JsonUtil.parseArray(apiResponse.getData(), "recently_viewed", new DataParser<WishProduct, JSONObject>() {
                    public WishProduct parseData(JSONObject jSONObject) throws JSONException {
                        try {
                            return new WishProduct(jSONObject);
                        } catch (Throwable unused) {
                            throw new JSONException("Ignore");
                        }
                    }
                });
                if (ExperimentDataCenter.getInstance().shouldSeeRecentWishlist()) {
                    arrayList = JsonUtil.parseArray(apiResponse.getData(), "wishlist_products", new DataParser<WishProduct, JSONObject>() {
                        public WishProduct parseData(JSONObject jSONObject) throws JSONException {
                            try {
                                return new WishProduct(jSONObject);
                            } catch (Throwable unused) {
                                throw new JSONException("Ignore");
                            }
                        }
                    });
                } else {
                    arrayList = null;
                }
                if (successCallback != null) {
                    SearchLandingPageService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray, parseArray2, arrayList);
                        }
                    });
                }
            }
        });
    }
}
