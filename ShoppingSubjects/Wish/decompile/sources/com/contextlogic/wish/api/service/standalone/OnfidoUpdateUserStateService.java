package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import java.text.ParseException;
import org.json.JSONException;

public class OnfidoUpdateUserStateService extends SingleApiService {

    public enum UserState {
        NONE(0),
        ERROR(1),
        ABANDONED(2),
        REJECTED(3);
        
        private int mValue;

        private UserState(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public void requestService(UserState userState, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("onfido/update-user-state");
        apiRequest.addParameter("user_state", userState.getValue());
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                OnfidoUpdateUserStateService.this.postRunnable(new Runnable() {
                    public void run() {
                        if (defaultFailureCallback != null) {
                            defaultFailureCallback.onFailure(str);
                        }
                    }
                });
            }

            public void handleSuccess(ApiResponse apiResponse) throws JSONException, ParseException {
                OnfidoUpdateUserStateService.this.postRunnable(new Runnable() {
                    public void run() {
                        if (defaultSuccessCallback != null) {
                            defaultSuccessCallback.onSuccess();
                        }
                    }
                });
            }
        });
    }
}
