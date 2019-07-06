package com.contextlogic.wish.api.service.standalone;

import android.text.TextUtils;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishProductReviewItem;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadProductReviewService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishProductReviewItem> arrayList, int i);
    }

    public void requestService(WishProductReviewItem wishProductReviewItem, int i, int i2, String str, int i3, String str2, String str3, String str4, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("rating/mobile-upload-rating");
        apiRequest.addParameter("user_id", (Object) ProfileDataCenter.getInstance().getUserId());
        apiRequest.addParameter("transaction_id", (Object) wishProductReviewItem.getTransactionId());
        apiRequest.addParameter("product_id", (Object) wishProductReviewItem.getProductId());
        if (!wishProductReviewItem.hasRating()) {
            if (i != 0) {
                apiRequest.addParameter("product_rating_star", i);
            }
            if (i2 != 0) {
                apiRequest.addParameter("size_choice", i2);
            }
            if (!TextUtils.isEmpty(str)) {
                apiRequest.addParameter("product_rating_comment", (Object) str);
            }
            if (i3 != 0) {
                apiRequest.addParameter("merchant_rating_star", i3);
            }
            if (!TextUtils.isEmpty(str2)) {
                apiRequest.addParameter("merchant_rating_comment", (Object) str2);
            }
        }
        if (str3 != null) {
            apiRequest.addParameter("image_name", (Object) str3);
        }
        if (str4 != null) {
            apiRequest.addParameter("video_id", (Object) str4);
        }
        apiRequest.addParameter("has_rating", wishProductReviewItem.hasRating());
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                UploadProductReviewService.this.postRunnable(new Runnable() {
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
                final int i = apiResponse.getData().getInt("points");
                UploadProductReviewService.this.postRunnable(new Runnable() {
                    public void run() {
                        successCallback.onSuccess(parseArray, i);
                    }
                });
            }
        });
    }
}
