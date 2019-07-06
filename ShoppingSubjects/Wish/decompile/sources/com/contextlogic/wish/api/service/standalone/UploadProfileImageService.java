package com.contextlogic.wish.api.service.standalone;

import android.graphics.Bitmap;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.BitmapUtil;
import com.contextlogic.wish.util.DeviceUtil;
import java.text.ParseException;
import org.json.JSONException;

public class UploadProfileImageService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(WishUser wishUser);
    }

    public void requestService(Bitmap bitmap, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/upload-profile-image");
        apiRequest.addParameter("image_data", (Object) BitmapUtil.getBase64EncodedBitmap(bitmap));
        apiRequest.addParameter("upload_src", (Object) DeviceUtil.getClientUploadSource());
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UploadProfileImageService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                final WishUser wishUser = new WishUser(apiResponse.getData().getJSONObject("user"));
                if (AuthenticationDataCenter.getInstance().getUserId() != null && AuthenticationDataCenter.getInstance().getUserId().equals(wishUser.getUserId())) {
                    ProfileDataCenter.getInstance().initializeData(wishUser);
                }
                if (successCallback != null) {
                    UploadProfileImageService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(wishUser);
                        }
                    });
                }
            }
        });
    }
}
