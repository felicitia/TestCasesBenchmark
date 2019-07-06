package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishMerchantTopCategory;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;

public class GetMerchantTopCategoriesService extends BaseFeedApiService<WishMerchantTopCategory> {

    public static class FeedContext {
        public String merchantId;
    }

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishMerchantTopCategory> arrayList, String str);
    }

    public void requestService(FeedContext feedContext, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("merchant_top_categories");
        if (feedContext.merchantId != null) {
            apiRequest.addParameter("query", (Object) feedContext.merchantId);
            startService(apiRequest, new ApiCallback() {
                public String getCallIdentifier() {
                    return null;
                }

                public void handleFailure(ApiResponse apiResponse, String str) {
                    GetMerchantTopCategoriesService.this.parseFailure(apiResponse, str, defaultFailureCallback);
                }

                public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                    final ArrayList topCategoriesFromResponse = WishMerchantTopCategory.getTopCategoriesFromResponse(apiResponse.getData());
                    final String string = apiResponse.getData().getString("request_id");
                    if (successCallback != null) {
                        GetMerchantTopCategoriesService.this.postRunnable(new Runnable() {
                            public void run() {
                                successCallback.onSuccess(topCategoriesFromResponse, string);
                            }
                        });
                    }
                }
            });
            return;
        }
        parseFailure(null, null, defaultFailureCallback);
    }
}
