package com.contextlogic.wish.api.service.standalone;

import android.content.Intent;
import android.graphics.Bitmap;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.LocalApiService;
import com.contextlogic.wish.util.IntentUtil;

public class ParseImageChooserIntentService extends LocalApiService<Void, Bitmap> {

    public interface SuccessCallback {
        void onSuccess(Bitmap bitmap);
    }

    public void requestService(Intent intent, Intent intent2, SuccessCallback successCallback, DefaultFailureCallback defaultFailureCallback) {
        final Intent intent3 = intent;
        final Intent intent4 = intent2;
        final SuccessCallback successCallback2 = successCallback;
        final DefaultFailureCallback defaultFailureCallback2 = defaultFailureCallback;
        AnonymousClass1 r0 = new LocalApiCallback<Void, Bitmap>() {
            public Bitmap processRequest(Void... voidArr) {
                return IntentUtil.parseImageChooserIntentResult(intent3, intent4);
            }

            public void processResult(Bitmap bitmap) {
                if (bitmap != null) {
                    if (successCallback2 != null) {
                        successCallback2.onSuccess(bitmap);
                    }
                } else if (defaultFailureCallback2 != null) {
                    defaultFailureCallback2.onFailure(null);
                }
            }
        };
        startService(r0, new Void[0]);
    }
}
