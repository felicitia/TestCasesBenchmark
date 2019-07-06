package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class GetExampleReviewsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishRating> arrayList, HashMap<String, String> hashMap);
    }

    public void requestService(Integer num, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("rating/get-example-ratings");
        if (num != null) {
            apiRequest.addParameter("num_items", num.intValue());
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                GetExampleReviewsService.this.postRunnable(new Runnable() {
                    public void run() {
                        defaultFailureCallback.onFailure(str);
                    }
                });
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                String optString = JsonUtil.optString(apiResponse.getData(), "share_purchase_title");
                String optString2 = JsonUtil.optString(apiResponse.getData(), "share_purchase_description");
                String optString3 = JsonUtil.optString(apiResponse.getData(), "points_title");
                String optString4 = JsonUtil.optString(apiResponse.getData(), "points_description");
                String optString5 = JsonUtil.optString(apiResponse.getData(), "example_ugc_items_title");
                String optString6 = JsonUtil.optString(apiResponse.getData(), "example_ugc_items_description");
                final HashMap hashMap = new HashMap();
                hashMap.put("SharePurchaseTitleKey", optString);
                hashMap.put("SharePurchaseDescKey", optString2);
                hashMap.put("PointsTitle", optString3);
                hashMap.put("PointsDescKey", optString4);
                hashMap.put("ExampleUgcItemsKey", optString5);
                hashMap.put("ExampleUgcItemsDesc", optString6);
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "results", new DataParser<WishRating, JSONObject>() {
                    public WishRating parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishRating(jSONObject);
                    }
                });
                GetExampleReviewsService.this.postRunnable(new Runnable() {
                    public void run() {
                        successCallback.onSuccess(parseArray, hashMap);
                    }
                });
            }
        });
    }
}
