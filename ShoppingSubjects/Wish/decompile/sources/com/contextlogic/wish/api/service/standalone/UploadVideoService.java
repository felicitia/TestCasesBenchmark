package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.FileUploadApiRequest;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import org.json.JSONException;

public class UploadVideoService extends SingleApiService {

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(String str, String str2, final SuccessCallback successCallback, final DefaultFailureCallback defaultFailureCallback) {
        startService(new FileUploadApiRequest(str, str2), new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    UploadVideoService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException {
                final String string = apiResponse.getData().getString("file_id");
                if (successCallback != null) {
                    UploadVideoService.this.postRunnable(new Runnable() {
                        public void run() {
                            successCallback.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
