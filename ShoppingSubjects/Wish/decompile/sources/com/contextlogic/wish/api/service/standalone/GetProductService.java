package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishGoogleAppIndexingData;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.MultiApiService;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONException;

public class GetProductService extends MultiApiService {

    public static class FeedExtraInfo {
        public WishGoogleAppIndexingData appIndexingData;
    }

    public interface SuccessCallback {
        void onSuccess(WishProduct wishProduct, FeedExtraInfo feedExtraInfo);
    }

    public void requestService(String str, HashMap<String, String> hashMap, SuccessCallback successCallback, DefaultCodeFailureCallback defaultCodeFailureCallback) {
        requestService(str, false, hashMap, successCallback, defaultCodeFailureCallback);
    }

    public void requestService(final String str, boolean z, HashMap<String, String> hashMap, final SuccessCallback successCallback, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("product/get");
        if (z) {
            apiRequest.addParameter("do_not_track", z);
        }
        apiRequest.addParameter("cid", (Object) str);
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                apiRequest.addParameter((String) entry.getKey(), entry.getValue());
            }
        }
        startService(apiRequest, new ApiCallback() {
            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (defaultCodeFailureCallback != null) {
                    GetProductService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultCodeFailureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishProduct wishProduct = new WishProduct(apiResponse.getData().getJSONObject("contest"));
                final FeedExtraInfo feedExtraInfo = new FeedExtraInfo();
                if (JsonUtil.hasValue(apiResponse.getData(), "app_indexing_data")) {
                    feedExtraInfo.appIndexingData = new WishGoogleAppIndexingData(apiResponse.getData().getJSONObject("app_indexing_data"));
                }
                if (successCallback != null) {
                    GetProductService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishProduct, feedExtraInfo);
                        }
                    });
                }
            }

            public String getCallIdentifier() {
                return str;
            }
        });
    }
}
