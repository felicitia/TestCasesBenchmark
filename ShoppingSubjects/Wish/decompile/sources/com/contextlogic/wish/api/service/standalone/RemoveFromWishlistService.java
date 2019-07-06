package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.ApiRequest;
import com.contextlogic.wish.api.ApiResponse;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.SingleApiService;
import com.contextlogic.wish.application.ApplicationEventManager;
import com.contextlogic.wish.application.ApplicationEventManager.EventType;
import java.util.ArrayList;
import java.util.Iterator;

public class RemoveFromWishlistService extends SingleApiService {
    public void requestService(final ArrayList<String> arrayList, String str, final DefaultSuccessCallback defaultSuccessCallback, final DefaultFailureCallback defaultFailureCallback) {
        ApiRequest apiRequest = new ApiRequest("user/wishlist/remove-product");
        apiRequest.addParameter("product_ids[]", arrayList);
        if (str != null) {
            apiRequest.addParameter("wishlist_id", (Object) str);
        }
        startService(apiRequest, new ApiCallback() {
            public String getCallIdentifier() {
                return null;
            }

            public void handleFailure(ApiResponse apiResponse, final String str) {
                if (defaultFailureCallback != null) {
                    RemoveFromWishlistService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultFailureCallback.onFailure(str);
                        }
                    });
                }
            }

            public void handleSuccess(ApiResponse apiResponse) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ApplicationEventManager.getInstance().triggerEvent(EventType.PRODUCT_UNWISH, (String) it.next(), null);
                }
                if (defaultSuccessCallback != null) {
                    RemoveFromWishlistService.this.postRunnable(new Runnable() {
                        public void run() {
                            defaultSuccessCallback.onSuccess();
                        }
                    });
                }
            }
        });
    }
}
