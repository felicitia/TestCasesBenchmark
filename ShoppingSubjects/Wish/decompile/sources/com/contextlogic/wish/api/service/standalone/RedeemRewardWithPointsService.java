package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishRewardsRedeemableInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class RedeemRewardWithPointsService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishRewardsRedeemableInfo wishRewardsRedeemableInfo);
    }

    public void requestService(int i, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("redeemable-rewards/redeem");
        apiRequest.addParameter("reward_type", (Object) Integer.toString(i));
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    RedeemRewardWithPointsService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishRewardsRedeemableInfo wishRewardsRedeemableInfo = new WishRewardsRedeemableInfo(apiResponse.getData());
                if (successCallback != null) {
                    RedeemRewardWithPointsService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishRewardsRedeemableInfo);
                        }
                    });
                }
            }
        });
    }
}
