package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRewardsRedeemableInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetRedeemableRewardsInfoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishRewardsRedeemableInfo wishRewardsRedeemableInfo);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new ApiRequest("redeemable-rewards/get-redeemable"), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    GetRedeemableRewardsInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishRewardsRedeemableInfo wishRewardsRedeemableInfo = new WishRewardsRedeemableInfo(apiResponse.getData());
                if (successCallback != null) {
                    GetRedeemableRewardsInfoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishRewardsRedeemableInfo);
                        }
                    });
                }
            }
        });
    }
}
