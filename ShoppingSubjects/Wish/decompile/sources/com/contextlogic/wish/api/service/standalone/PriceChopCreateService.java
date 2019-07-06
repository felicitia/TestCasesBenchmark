package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.google.gson.JsonSyntaxException;
import java.text.ParseException;
import org.json.JSONException;

public class PriceChopCreateService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(PriceChopProductDetail priceChopProductDetail);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("pricechop/create");
        apiRequest.addParameter("product_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    PriceChopCreateService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException, JsonSyntaxException {
                final PriceChopProductDetail priceChopProductDetail;
                try {
                    priceChopProductDetail = new PriceChopProductDetail(apiResponse.getData());
                } catch (JSONException unused) {
                    priceChopProductDetail = null;
                }
                if (successCallback != null) {
                    PriceChopCreateService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(priceChopProductDetail);
                        }
                    });
                }
            }
        });
    }
}
