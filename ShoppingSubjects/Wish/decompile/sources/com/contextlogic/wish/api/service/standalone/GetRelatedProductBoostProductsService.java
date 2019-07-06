package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.ProductDetailsRelatedRowSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetRelatedProductBoostProductsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec);
    }

    public void requestService(String str, int i, long j, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("pb_related/get");
        apiRequest.addParameter("cid", (Object) str);
        apiRequest.addParameter("offset", i);
        apiRequest.addParameter("count", j);
        startService(apiRequest, new ApiCallback() {
            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetRelatedProductBoostProductsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final ProductDetailsRelatedRowSpec productDetailsRelatedRowSpec = new ProductDetailsRelatedRowSpec(apiResponse.getData());
                if (successCallback != null) {
                    GetRelatedProductBoostProductsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(productDetailsRelatedRowSpec);
                        }
                    });
                }
            }

            public String getCallIdentifier() {
                return getClass().getName();
            }
        });
    }
}
