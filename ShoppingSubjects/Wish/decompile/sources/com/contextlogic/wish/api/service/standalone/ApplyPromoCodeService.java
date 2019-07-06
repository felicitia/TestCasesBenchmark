package com.contextlogic.wish.api.service.standalone;

import android.support.v4.app.Fragment;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class ApplyPromoCodeService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishCart wishCart);
    }

    public void requestService(final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback, final Fragment fragment, String str) {
        ApiRequest apiRequest = new ApiRequest("promo-code/apply");
        apiRequest.addParameter("promo_code", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    ApplyPromoCodeService.this.postRunnable(new Runnable() {
                        public void run() {
                            String str = str;
                            if (str == null) {
                                str = fragment.getString(R.string.promo_code_failed_to_apply);
                            }
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishCart wishCart = new WishCart(apiResponse.getData().getJSONObject("cart_info"));
                if (successCallback != null) {
                    ApplyPromoCodeService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishCart);
                        }
                    });
                }
            }
        });
    }
}
