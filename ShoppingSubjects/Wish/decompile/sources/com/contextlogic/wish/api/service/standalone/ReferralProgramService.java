package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishReferralProgramInfoSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class ReferralProgramService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishReferralProgramInfoSpec wishReferralProgramInfoSpec);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        startService(new ApiRequest("referral-program-info/get"), new ApiCallback() {
            public String getCallIdentifier() {
                return "referral-program-info/get";
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (defaultCodeFailureCallback != null) {
                    ReferralProgramService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultCodeFailureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                if (successCallback != null) {
                    final WishReferralProgramInfoSpec wishReferralProgramInfoSpec = new WishReferralProgramInfoSpec(apiResponse.getData());
                    ReferralProgramService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishReferralProgramInfoSpec);
                        }
                    });
                }
            }
        });
    }
}
