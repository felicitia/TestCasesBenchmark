package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishPartnerLearnMoreSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetWishPartnerLearnMoreService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishPartnerLearnMoreSpec wishPartnerLearnMoreSpec);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("partner/learn-more"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, String str) {
                if (defaultFailureCallback != null) {
                    defaultFailureCallback.onFailure(str);
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishPartnerLearnMoreSpec wishPartnerLearnMoreSpec = new WishPartnerLearnMoreSpec(apiResponse.getData().getJSONObject("learn_more_details"));
                if (successCallback != null) {
                    GetWishPartnerLearnMoreService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishPartnerLearnMoreSpec);
                        }
                    });
                }
            }
        });
    }
}
