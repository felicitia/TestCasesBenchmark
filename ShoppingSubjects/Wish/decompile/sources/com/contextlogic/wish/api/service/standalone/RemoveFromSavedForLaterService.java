package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishSavedForLaterProduct;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoveFromSavedForLaterService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ArrayList<WishSavedForLaterProduct> arrayList);
    }

    public void requestService(WishSavedForLaterProduct wishSavedForLaterProduct, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        String str;
        ApiRequest apiRequest = new ApiRequest("user/wishlist/remove-from-saved-for-later");
        apiRequest.addParameter("product_id", (Object) wishSavedForLaterProduct.getProductId());
        apiRequest.addParameter("variation_id", (Object) wishSavedForLaterProduct.getVariationId());
        if (wishSavedForLaterProduct.getSelectedShippingOption() == null) {
            str = "standard";
        } else {
            str = wishSavedForLaterProduct.getSelectedShippingOption().getOptionId();
        }
        apiRequest.addParameter("shipping_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ArrayList parseArray = JsonUtil.parseArray(apiResponse.getData(), "items", new DataParser<WishSavedForLaterProduct, JSONObject>() {
                    public WishSavedForLaterProduct parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new WishSavedForLaterProduct(jSONObject);
                    }
                });
                if (successCallback != null) {
                    RemoveFromSavedForLaterService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(parseArray);
                        }
                    });
                }
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    RemoveFromSavedForLaterService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }
        });
    }
}
