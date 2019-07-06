package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishPartnerSummary;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetWishPartnerSummaryService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishPartnerSummary wishPartnerSummary);
    }

    public void requestService(String str, int i, boolean z, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest;
        if (z) {
            apiRequest = new ApiRequest("partner/get-community-summary");
        } else {
            apiRequest = new ApiRequest("partner/get-summary");
        }
        if (str != null) {
            apiRequest.addParameter("offset", (Object) str);
        }
        apiRequest.addParameter("count", i);
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
                final WishPartnerSummary wishPartnerSummary = new WishPartnerSummary(apiResponse.getData());
                if (successCallback != null) {
                    GetWishPartnerSummaryService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishPartnerSummary);
                        }
                    });
                }
            }
        });
    }
}
