package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishPartnerInfoSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetWishPartnerInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishPartnerInfoSpec wishPartnerInfoSpec);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("partner/create-link");
        apiRequest.addParameter("product_id", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (defaultFailureCallback != null) {
                    defaultFailureCallback.onFailure(str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishPartnerInfoSpec wishPartnerInfoSpec = new WishPartnerInfoSpec(apiResponse.getData().getJSONObject("partner_code_details"));
                if (successCallback != null) {
                    GetWishPartnerInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishPartnerInfoSpec);
                        }
                    });
                }
            }
        });
    }
}
