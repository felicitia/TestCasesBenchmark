package com.contextlogic.wish.social.facebook;

import android.content.Context;
import android.content.Intent;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.social.SocialSession;
import com.contextlogic.wish.social.SocialSession.ErrorContext;
import com.contextlogic.wish.social.SocialSession.LoginCallback;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.Arrays;
import org.json.JSONObject;

public class FacebookLoginSession extends SocialSession implements FacebookCallback<LoginResult> {
    private static final String[] FB_READ_PERMISSIONS = {"email"};
    private AppEventsLogger mAppEventsLogger;
    /* access modifiers changed from: private */
    public CallbackManager mCallbackManager;
    /* access modifiers changed from: private */
    public LoginCallback mCurrentCallback;
    /* access modifiers changed from: private */
    public String mFbId;
    private GraphRequest mPendingGraphRequest;

    public FacebookLoginSession() {
        reset();
    }

    public void login(LoginCallback loginCallback) {
        if (isLoggedIn()) {
            if (this.mFbId == null) {
                this.mFbId = PreferenceUtil.getString("fb_user_id");
            }
            if (this.mFbId != null) {
                this.mCurrentCallback = null;
                loginCallback.onSuccess();
                return;
            }
        }
        try {
            this.mCurrentCallback = loginCallback;
            if (loginCallback.getActivityForResolutions() != null) {
                loginCallback.getActivityForResolutions().addResultCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        FacebookLoginSession.this.mCallbackManager.onActivityResult(i, i2, intent);
                    }
                }, this.mCallbackManager);
            }
            LoginManager.getInstance().registerCallback(this.mCallbackManager, this);
            LoginManager.getInstance().logInWithReadPermissions(loginCallback.getActivityForResolutions(), Arrays.asList(FB_READ_PERMISSIONS));
        } catch (Throwable unused) {
            this.mCurrentCallback = null;
            ErrorContext errorContext = new ErrorContext();
            errorContext.facebookCommunicationError = true;
            loginCallback.onFailure(errorContext);
        }
    }

    public void logout() {
        try {
            LoginManager.getInstance().logOut();
        } catch (Throwable unused) {
        }
        reset();
    }

    public boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired();
    }

    public AppEventsLogger getLogger() {
        return this.mAppEventsLogger;
    }

    public String getFbId() {
        return this.mFbId;
    }

    private void reset() {
        if (this.mPendingGraphRequest != null) {
            this.mPendingGraphRequest.setCallback(new Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                }
            });
            this.mPendingGraphRequest = null;
        }
        if (!(this.mCallbackManager == null || this.mCurrentCallback == null || this.mCurrentCallback.getActivityForResolutions() == null)) {
            this.mCurrentCallback.getActivityForResolutions().removeResultCallbackTag(this.mCallbackManager);
        }
        FacebookSdk.setApplicationId(FacebookManager.getAppId());
        this.mCallbackManager = Factory.create();
        this.mAppEventsLogger = null;
    }

    public void showErrorDialog(BaseActivity baseActivity, ErrorContext errorContext) {
        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(baseActivity.getString(R.string.facebook_error)));
    }

    private void handleSessionOpened() {
        this.mFbId = PreferenceUtil.getString("fb_user_id");
        if (this.mFbId == null) {
            Profile currentProfile = Profile.getCurrentProfile();
            if (currentProfile == null || currentProfile.getId() == null) {
                this.mPendingGraphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphJSONObjectCallback() {
                    public void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
                        String optString = jSONObject != null ? JsonUtil.optString(jSONObject, "id") : null;
                        if (optString != null) {
                            FacebookLoginSession.this.mFbId = optString;
                            PreferenceUtil.setString("fb_user_id", FacebookLoginSession.this.mFbId);
                            if (FacebookLoginSession.this.mCurrentCallback != null) {
                                LoginCallback access$200 = FacebookLoginSession.this.mCurrentCallback;
                                FacebookLoginSession.this.mCurrentCallback = null;
                                access$200.onSuccess();
                                return;
                            }
                            return;
                        }
                        ErrorContext errorContext = new ErrorContext();
                        if (graphResponse.getError() != null) {
                            errorContext.facebookErrorCode = graphResponse.getError().getErrorCode();
                        }
                        errorContext.facebookCommunicationError = true;
                        if (FacebookLoginSession.this.mCurrentCallback != null) {
                            LoginCallback access$2002 = FacebookLoginSession.this.mCurrentCallback;
                            FacebookLoginSession.this.mCurrentCallback = null;
                            access$2002.onFailure(errorContext);
                        }
                    }
                });
                this.mPendingGraphRequest.executeAsync();
                return;
            }
            this.mFbId = currentProfile.getId();
            PreferenceUtil.setString("fb_user_id", this.mFbId);
            if (this.mCurrentCallback != null) {
                LoginCallback loginCallback = this.mCurrentCallback;
                this.mCurrentCallback = null;
                loginCallback.onSuccess();
            }
        } else if (this.mCurrentCallback != null) {
            LoginCallback loginCallback2 = this.mCurrentCallback;
            this.mCurrentCallback = null;
            loginCallback2.onSuccess();
        }
    }

    public void onSuccess(LoginResult loginResult) {
        this.mAppEventsLogger = AppEventsLogger.newLogger((Context) WishApplication.getInstance(), AccessToken.getCurrentAccessToken());
        if (this.mCurrentCallback != null) {
            if (this.mCurrentCallback.getActivityForResolutions() != null) {
                this.mCurrentCallback.getActivityForResolutions().removeResultCallbackTag(this.mCallbackManager);
            }
            handleSessionOpened();
        }
    }

    public void onCancel() {
        if (this.mCurrentCallback != null) {
            if (this.mCurrentCallback.getActivityForResolutions() != null) {
                this.mCurrentCallback.getActivityForResolutions().removeResultCallbackTag(this.mCallbackManager);
            }
            LoginCallback loginCallback = this.mCurrentCallback;
            this.mCurrentCallback = null;
            loginCallback.onCancel();
        }
        reset();
    }

    public void onError(FacebookException facebookException) {
        if (this.mCurrentCallback != null) {
            if (this.mCurrentCallback.getActivityForResolutions() != null) {
                this.mCurrentCallback.getActivityForResolutions().removeResultCallbackTag(this.mCallbackManager);
            }
            LoginCallback loginCallback = this.mCurrentCallback;
            this.mCurrentCallback = null;
            ErrorContext errorContext = new ErrorContext();
            errorContext.facebookCommunicationError = true;
            loginCallback.onFailure(errorContext);
        }
        reset();
    }
}
