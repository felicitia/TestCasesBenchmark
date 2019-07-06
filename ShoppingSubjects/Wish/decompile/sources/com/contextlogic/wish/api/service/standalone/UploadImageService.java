package com.contextlogic.wish.api.service.standalone;

import android.graphics.Bitmap;
import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.util.BitmapUtil;
import com.contextlogic.wish.util.DeviceUtil;
import org.json.JSONException;

public class UploadImageService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str, String str2);
    }

    public void requestService(Bitmap bitmap, String str, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("mobile/upload-image");
        apiRequest.addParameter("image_data", (Object) BitmapUtil.getBase64EncodedBitmap(bitmap));
        apiRequest.addParameter("upload_src", (Object) DeviceUtil.getClientUploadSource());
        apiRequest.addParameter("bucket", (Object) str);
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UploadImageService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("image_url");
                final String string2 = apiResponse.getData().getString("image_name");
                if (successCallback != null) {
                    UploadImageService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string, string2);
                        }
                    });
                }
            }
        });
    }
}
