package com.contextlogic.wish.social.google;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LogoutCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.social.SocialSession;
import com.contextlogic.wish.social.SocialSession.ErrorContext;
import com.contextlogic.wish.social.SocialSession.LoginCallback;
import com.contextlogic.wish.social.google.GoogleSignInApiClient.ApiClientCallback;
import com.contextlogic.wish.util.PreferenceUtil;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class GoogleLoginSession extends SocialSession {
    /* access modifiers changed from: private */
    public LoginCallback mCurrentCallback = null;
    private GoogleSignInAccount mGoogleSignInAccount;
    private boolean mHasRetried = false;
    /* access modifiers changed from: private */
    public boolean mResolvingError;

    public static class GoogleErrorDialogFragment extends DialogFragment {
        public Dialog onCreateDialog(Bundle bundle) {
            return GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), getArguments().getInt("dialog_error"), 1001);
        }
    }

    public GoogleLoginSession() {
        registerWithSignInClient();
    }

    public void login(LoginCallback loginCallback) {
        if (isLoggedIn()) {
            this.mCurrentCallback = null;
            loginCallback.onSuccess();
            return;
        }
        this.mCurrentCallback = loginCallback;
        String string = PreferenceUtil.getString("GoogleSignInEmail");
        if (string != null) {
            GoogleSignInApiClient.getInstance().updateSignInOptions(new Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestServerAuthCode(WishApplication.getInstance().getString(R.string.google_server_key)).setAccountName(string).build());
        }
        GoogleSignInApiClient.getInstance().runOnConnected(new Runnable() {
            public void run() {
                Auth.GoogleSignInApi.silentSignIn(GoogleSignInApiClient.getInstance().getClient()).setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    public void onResult(GoogleSignInResult googleSignInResult) {
                        GoogleLoginSession.this.handleLoginResult(googleSignInResult);
                    }
                });
            }
        }, new Runnable() {
            public void run() {
                if (GoogleLoginSession.this.mCurrentCallback != null) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.errorMessage = WishApplication.getInstance().getString(R.string.loading_error);
                    GoogleLoginSession.this.mCurrentCallback.onFailure(errorContext);
                    GoogleLoginSession.this.mCurrentCallback = null;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleLoginActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
        baseActivity.removeResultCodeCallback(i);
        if (i2 == -1) {
            GoogleSignInResult signInResultFromIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            if (signInResultFromIntent != null && signInResultFromIntent.isSuccess()) {
                updateSignInAccount(signInResultFromIntent.getSignInAccount());
                PreferenceUtil.setString("google_plus_user_id", getLoggedInAccount().getId());
            } else if (signInResultFromIntent == null) {
                if (this.mHasRetried || this.mCurrentCallback == null) {
                    updateSignInAccount(null);
                    if (this.mCurrentCallback != null) {
                        ErrorContext errorContext = new ErrorContext();
                        errorContext.errorMessage = WishApplication.getInstance().getString(R.string.general_error);
                        this.mCurrentCallback.onFailure(errorContext);
                        this.mCurrentCallback = null;
                    }
                } else {
                    this.mHasRetried = true;
                    retryLogin(this.mCurrentCallback);
                    return;
                }
            }
            if (this.mCurrentCallback != null) {
                this.mCurrentCallback.onSuccess();
                this.mCurrentCallback = null;
            }
        } else if (i2 == 0) {
            updateSignInAccount(null);
            if (this.mCurrentCallback != null) {
                this.mCurrentCallback.onCancel();
                this.mCurrentCallback = null;
            }
        }
    }

    private void retryLogin(final LoginCallback loginCallback) {
        login(new LoginCallback() {
            public void onSuccess() {
                Crashlytics.logException(new Exception("GoogleLogin success after retry"));
                loginCallback.onSuccess();
            }

            public void onFailure(ErrorContext errorContext) {
                Crashlytics.logException(new Exception("GoogleLogin fail after retry"));
                loginCallback.onFailure(errorContext);
            }

            public void onCancel() {
                loginCallback.onSuccess();
            }

            public BaseActivity getActivityForResolutions() {
                return loginCallback.getActivityForResolutions();
            }

            public boolean isResolutionAllowed() {
                return loginCallback.isResolutionAllowed();
            }
        });
    }

    public void handleLoginResult(GoogleSignInResult googleSignInResult) {
        if (isLoggedIn()) {
            if (this.mCurrentCallback != null) {
                this.mCurrentCallback.onSuccess();
                this.mCurrentCallback = null;
            }
            return;
        }
        if (googleSignInResult.isSuccess()) {
            updateSignInAccount(googleSignInResult.getSignInAccount());
            PreferenceUtil.setString("google_plus_user_id", getLoggedInAccount().getId());
            if (this.mCurrentCallback != null) {
                this.mCurrentCallback.onSuccess();
                this.mCurrentCallback = null;
            }
        } else if (this.mCurrentCallback != null && this.mCurrentCallback.isResolutionAllowed() && googleSignInResult.getStatus().hasResolution()) {
            BaseActivity activityForResolutions = this.mCurrentCallback.getActivityForResolutions();
            if (activityForResolutions != null) {
                try {
                    googleSignInResult.getStatus().startResolutionForResult(activityForResolutions, activityForResolutions.addResultCodeCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            GoogleLoginSession.this.handleLoginActivityResult(baseActivity, i, i2, intent);
                        }
                    }));
                } catch (SendIntentException unused) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.googleErrorCode = googleSignInResult.getStatus().getStatusCode();
                    errorContext.errorMessage = googleSignInResult.getStatus().getStatusMessage();
                    this.mCurrentCallback.onFailure(errorContext);
                    this.mCurrentCallback = null;
                }
            }
        } else if (this.mCurrentCallback != null && this.mCurrentCallback.isResolutionAllowed() && googleSignInResult.getStatus().getStatusCode() == 4) {
            GoogleSignInApiClient.getInstance().runOnConnected(new Runnable() {
                public void run() {
                    GoogleLoginSession.this.mCurrentCallback.getActivityForResolutions().startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(GoogleSignInApiClient.getInstance().getClient()), GoogleLoginSession.this.mCurrentCallback.getActivityForResolutions().addResultCodeCallback(new ActivityResultCallback() {
                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                            GoogleLoginSession.this.handleLoginActivityResult(baseActivity, i, i2, intent);
                        }
                    }));
                }
            });
        } else if (this.mCurrentCallback != null) {
            ErrorContext errorContext2 = new ErrorContext();
            errorContext2.errorMessage = WishApplication.getInstance().getString(R.string.loading_error);
            this.mCurrentCallback.onFailure(errorContext2);
            this.mCurrentCallback = null;
        }
    }

    /* access modifiers changed from: private */
    public void handleConnectionErrorResolution(BaseActivity baseActivity, int i, int i2) {
        baseActivity.removeResultCodeCallback(i);
        this.mResolvingError = false;
        if (i2 == -1) {
            GoogleSignInApiClient.getInstance().connect();
        }
    }

    public void logout() {
        this.mHasRetried = false;
        updateSignInAccount(null);
        GoogleSignInApiClient.getInstance().runOnConnected(new Runnable() {
            public void run() {
                try {
                    Auth.GoogleSignInApi.signOut(GoogleSignInApiClient.getInstance().getClient());
                } catch (Throwable unused) {
                }
                GoogleSignInApiClient.getInstance().initializeApiClient();
            }
        });
    }

    public PendingResult deleteAccount(final LogoutCallback logoutCallback) {
        if (!isLoggedIn()) {
            if (logoutCallback != null) {
                logoutCallback.onFailure(null);
            }
            return null;
        }
        PendingResult revokeAccess = Auth.GoogleSignInApi.revokeAccess(GoogleSignInApiClient.getInstance().getClient());
        revokeAccess.setResultCallback(new ResultCallback<Status>() {
            public void onResult(Status status) {
                GoogleSignInApiClient.getInstance().initializeApiClient();
                if (logoutCallback != null) {
                    logoutCallback.onSuccess();
                }
            }
        });
        updateSignInAccount(null);
        return revokeAccess;
    }

    public void showErrorDialog(BaseActivity baseActivity, ErrorContext errorContext) {
        GoogleErrorDialogFragment googleErrorDialogFragment;
        if (errorContext == null || errorContext.googleErrorCode <= 0) {
            googleErrorDialogFragment = null;
        } else {
            googleErrorDialogFragment = new GoogleErrorDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("dialog_error", errorContext.googleErrorCode);
            googleErrorDialogFragment.setArguments(bundle);
        }
        boolean z = false;
        if (googleErrorDialogFragment != null) {
            try {
                googleErrorDialogFragment.show(baseActivity.getFragmentManager(), "errordialog");
                z = true;
            } catch (Throwable unused) {
            }
        }
        if (!z) {
            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(baseActivity.getString(R.string.authentication_error)));
        }
    }

    public boolean isLoggedIn() {
        return this.mGoogleSignInAccount != null;
    }

    public GoogleSignInAccount getLoggedInAccount() {
        return this.mGoogleSignInAccount;
    }

    public String getAccountEmail() {
        if (this.mGoogleSignInAccount != null) {
            return this.mGoogleSignInAccount.getEmail();
        }
        return null;
    }

    private void updateSignInAccount(GoogleSignInAccount googleSignInAccount) {
        PreferenceUtil.setString("GoogleSignInEmail", googleSignInAccount == null ? null : googleSignInAccount.getEmail());
        this.mGoogleSignInAccount = googleSignInAccount;
    }

    private void registerWithSignInClient() {
        GoogleSignInApiClient.getInstance().register(new ApiClientCallback() {
            public void onConnected(Bundle bundle) {
            }

            public void onConnectionSuspended(int i) {
            }

            public void onConnectionFailed(ConnectionResult connectionResult) {
                if (!GoogleLoginSession.this.mResolvingError) {
                    if (connectionResult.hasResolution()) {
                        if (GoogleLoginSession.this.mCurrentCallback != null && !GoogleLoginSession.this.mCurrentCallback.isResolutionAllowed()) {
                            LoginCallback access$000 = GoogleLoginSession.this.mCurrentCallback;
                            GoogleLoginSession.this.mCurrentCallback = null;
                            access$000.onFailure(null);
                        } else if (GoogleLoginSession.this.mCurrentCallback != null) {
                            try {
                                GoogleLoginSession.this.mResolvingError = true;
                                connectionResult.startResolutionForResult(GoogleLoginSession.this.mCurrentCallback.getActivityForResolutions(), GoogleLoginSession.this.mCurrentCallback.getActivityForResolutions().addResultCodeCallback(new ActivityResultCallback() {
                                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                        GoogleLoginSession.this.handleConnectionErrorResolution(baseActivity, i, i2);
                                    }
                                }));
                            } catch (Throwable unused) {
                                ErrorContext errorContext = new ErrorContext();
                                errorContext.googleErrorCode = connectionResult.getErrorCode();
                                errorContext.errorMessage = connectionResult.getErrorMessage();
                                GoogleLoginSession.this.showErrorDialog(GoogleLoginSession.this.mCurrentCallback.getActivityForResolutions(), errorContext);
                                GoogleLoginSession.this.mResolvingError = true;
                            }
                        }
                    } else if (GoogleLoginSession.this.mCurrentCallback != null) {
                        ErrorContext errorContext2 = new ErrorContext();
                        errorContext2.googleErrorCode = connectionResult.getErrorCode();
                        errorContext2.errorMessage = connectionResult.getErrorMessage();
                        GoogleLoginSession.this.showErrorDialog(GoogleLoginSession.this.mCurrentCallback.getActivityForResolutions(), errorContext2);
                        GoogleLoginSession.this.mResolvingError = true;
                    }
                }
            }
        });
    }
}
