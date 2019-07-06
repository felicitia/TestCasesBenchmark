package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRewardsHelpInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetRedeemableRewardsHelpInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishRewardsHelpInfo wishRewardsHelpInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("redeemable-rewards/get-info"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetRedeemableRewardsHelpInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishRewardsHelpInfo wishRewardsHelpInfo = new WishRewardsHelpInfo(apiResponse.getData());
                if (successCallback != null) {
                    GetRedeemableRewardsHelpInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishRewardsHelpInfo);
                        }
                    });
                }
            }
        });
    }
}
