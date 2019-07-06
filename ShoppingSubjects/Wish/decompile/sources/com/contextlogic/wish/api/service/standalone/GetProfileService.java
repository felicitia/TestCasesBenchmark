package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class GetProfileService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishUser wishUser);
    }

    public void requestService(String str, final SuccessCallback successCallback, final DefaultCodeFailureCallback defaultCodeFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("profile/get");
        apiRequest.addParameter("uid", (Object) str);
        apiRequest.addParameter("show_friend_js_link", (Object) "true");
        apiRequest.addParameter("image_size", (Object) "medium");
        apiRequest.addParameter("preview_size", (Object) "4");
        apiRequest.addParameter("supports_bucket_management", (Object) "true");
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(final ApiResponse apiResponse, final String str) {
                if (defaultCodeFailureCallback != null) {
                    GetProfileService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultCodeFailureCallback.onFailure(str, apiResponse != null ? apiResponse.getCode() : -1);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishUser wishUser = new WishUser(apiResponse.getData());
                if (AuthenticationDataCenter.getInstance().getUserId() != null && AuthenticationDataCenter.getInstance().getUserId().equals(wishUser.getUserId())) {
                    ProfileDataCenter.getInstance().initializeData(wishUser);
                }
                if (successCallback != null) {
                    GetProfileService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUser);
                        }
                    });
                }
            }
        });
    }
}
