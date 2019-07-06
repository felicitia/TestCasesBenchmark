package com.contextlogic.wish.api.service.standalone;

import android.content.Intent;
import com.contextlogic.wish.api.service.LocalApiService;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.VideoUtil.VideoParseCallback;
import com.contextlogic.wish.util.VideoUtil.VideoSpecification;

public class ParseVideoChooserIntentService extends LocalApiService<Void, Void> {

    public interface FailureCallback {
        void onFailure(boolean z);
    }

    public interface SuccessCallback {
        void onSuccess(String str);
    }

    public void requestService(Intent intent, Intent intent2, VideoSpecification videoSpecification, final SuccessCallback successCallback, final FailureCallback failureCallback) {
        final AnonymousClass1 r5 = new VideoParseCallback() {
            public void onVideoTooLong() {
                ParseVideoChooserIntentService.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (failureCallback != null) {
                            failureCallback.onFailure(true);
                        }
                    }
                });
            }

            public void onVideoParsed(final String str) {
                ParseVideoChooserIntentService.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (successCallback != null) {
                            successCallback.onSuccess(str);
                        }
                    }
                });
            }

            public void onVideoParseFailed() {
                ParseVideoChooserIntentService.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (failureCallback != null) {
                            failureCallback.onFailure(false);
                        }
                    }
                });
            }
        };
        final Intent intent3 = intent;
        final Intent intent4 = intent2;
        final VideoSpecification videoSpecification2 = videoSpecification;
        AnonymousClass2 r0 = new LocalApiCallback<Void, Void>() {
            public void processResult(Void voidR) {
            }

            public Void processRequest(Void... voidArr) {
                IntentUtil.parseVideoChooserIntentResult(intent3, intent4, videoSpecification2, r5);
                return null;
            }
        };
        startService(r0, new Void[0]);
    }
}
