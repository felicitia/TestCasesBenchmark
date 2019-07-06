package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class GetProductReviewInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishProductReviewItem> arrayList);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("rating/get-camera-review-items"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                GetProductReviewInfoService.this.postRunnable(new Runnable() {
                    public void run() {
                        defaultFailureCallback.onFailure(str);
                    }
                });
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "items", new DataParser<WishProductReviewItem, JSONObject>() {
                    public WishProductReviewItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishProductReviewItem(jSONObject);
                    }
                });
                GetProductReviewInfoService.this.postRunnable(new Runnable() {
                    public void run() {
                        successCallback.onSuccess(parseArray);
                    }
                });
            }
        });
    }
}
