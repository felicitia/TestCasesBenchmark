package com.contextlogic.wish.social;

import com.contextlogic.wish.activity.BaseActivity;

public abstract class SocialSession {

    public static class ErrorContext {
        public int errorCode;
        public String errorMessage;
        public boolean facebookCommunicationError;
        public int facebookErrorCode;
        public int googleErrorCode;
        public boolean googlePreauthorizationMissing;
    }

    public interface LoginCallback {
        BaseActivity getActivityForResolutions();

        boolean isResolutionAllowed();

        void onCancel();

        void onFailure(ErrorContext errorContext);

        void onSuccess();
    }
}
