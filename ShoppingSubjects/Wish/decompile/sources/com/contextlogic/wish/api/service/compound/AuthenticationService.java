package com.contextlogic.wish.api.service.compound;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Patterns;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.analytics.FeedTileLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.DeleteAccountService;
import com.contextlogic.wish.api.service.standalone.EmailLoginService;
import com.contextlogic.wish.api.service.standalone.EmailSignupService;
import com.contextlogic.wish.api.service.standalone.FacebookLoginService;
import com.contextlogic.wish.api.service.standalone.GetProfileService;
import com.contextlogic.wish.api.service.standalone.GetUserStatusService;
import com.contextlogic.wish.api.service.standalone.GoogleLoginService;
import com.contextlogic.wish.api.service.standalone.LogExternalApiEventService;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext;
import com.contextlogic.wish.api.service.standalone.LoginService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.PushRegistrationService;
import com.contextlogic.wish.api.service.standalone.PushUnregistrationService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.cache.StateStoreCache;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.social.SmartLockManager;
import com.contextlogic.wish.social.SocialSession.ErrorContext;
import com.contextlogic.wish.social.SocialSession.LoginCallback;
import com.contextlogic.wish.social.facebook.FacebookManager;
import com.contextlogic.wish.social.google.GoogleManager;
import com.contextlogic.wish.social.google.GoogleSignInApiClient;
import com.contextlogic.wish.util.PreferenceUtil;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credential.Builder;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.PendingResult;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

public class AuthenticationService {
    /* access modifiers changed from: private */
    public AuthenticationCallback mAuthenticationCallback;
    private DeleteAccountService mDeleteAccountService = new DeleteAccountService();
    private EmailLoginService mEmailLoginService = new EmailLoginService();
    private EmailSignupService mEmailSignupService = new EmailSignupService();
    private FacebookLoginService mFacebookLoginService = new FacebookLoginService();
    private GetProfileService mGetProfileService = new GetProfileService();
    private GetUserStatusService mGetUserStatusService = new GetUserStatusService();
    private GoogleLoginService mGoogleLoginService = new GoogleLoginService();
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper());
    private LogExternalApiEventService mLogExternalApiEventService = new LogExternalApiEventService();
    /* access modifiers changed from: private */
    public LogoutCallback mLogoutCallback;
    /* access modifiers changed from: private */
    public PendingResult mPendingGoogleDeleteAccountResult;
    private PushUnregistrationService mPushUnregistrationService = new PushUnregistrationService();

    public interface AuthenticationCallback {
        void onCancel();

        void onFailure(ErrorContext errorContext);

        void onSuccess(String str, boolean z, SignupFlowContext signupFlowContext);
    }

    public enum LoginMode implements Parcelable {
        FACEBOOK,
        GOOGLE,
        EMAIL;
        
        public static final Creator<LoginMode> CREATOR = null;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<LoginMode>() {
                public LoginMode createFromParcel(Parcel parcel) {
                    return LoginMode.values()[parcel.readInt()];
                }

                public LoginMode[] newArray(int i) {
                    return new LoginMode[i];
                }
            };
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    private static class LoginProgress {
        public ErrorContext errorContext;
        public boolean isNewSession;
        public boolean isNewUser;
        public String lastLoggedInUserId;
        public LoginMode loginMode;
        public boolean profileFetched;
        public SignupFlowContext signupFlowContext;
        public boolean statusFetched;
        public String userId;

        private LoginProgress() {
        }

        public boolean isLoginComplete() {
            return this.statusFetched && this.profileFetched;
        }
    }

    public interface LogoutCallback {
        void onFailure(String str);

        void onSuccess();
    }

    public void cancelAllRequests() {
        this.mAuthenticationCallback = null;
        this.mLogoutCallback = null;
        if (this.mPendingGoogleDeleteAccountResult != null) {
            this.mPendingGoogleDeleteAccountResult.cancel();
            this.mPendingGoogleDeleteAccountResult = null;
        }
        cancelLoginServiceRequests();
    }

    private void cancelLoginServiceRequests() {
        this.mFacebookLoginService.cancelAllRequests();
        this.mEmailLoginService.cancelAllRequests();
        this.mEmailSignupService.cancelAllRequests();
        this.mGoogleLoginService.cancelAllRequests();
        this.mGetUserStatusService.cancelAllRequests();
        this.mGetProfileService.cancelAllRequests();
        this.mDeleteAccountService.cancelAllRequests();
        this.mPushUnregistrationService.cancelAllRequests();
    }

    public void ensureLoggedIn(BaseActivity baseActivity, AuthenticationCallback authenticationCallback) {
        LoginMode loginMode;
        LoginContext loginContext = new LoginContext();
        if (isUsingEmailLogin()) {
            loginMode = LoginMode.EMAIL;
            loginContext.email = PreferenceUtil.getString("user_login_email");
            loginContext.password = PreferenceUtil.getString("user_login_password");
        } else if (isUsingFacebookLogin()) {
            loginMode = LoginMode.FACEBOOK;
            loginContext.fbId = PreferenceUtil.getString("fb_user_id");
        } else if (isUsingGoogleLogin()) {
            loginMode = LoginMode.GOOGLE;
            loginContext.googleId = PreferenceUtil.getString("google_plus_user_id");
        } else {
            loginMode = null;
        }
        LoginMode loginMode2 = loginMode;
        loginContext.sessionRefresh = true;
        login(baseActivity, loginContext, loginMode2, authenticationCallback, false);
    }

    public void login(BaseActivity baseActivity, LoginContext loginContext, LoginMode loginMode, AuthenticationCallback authenticationCallback) {
        String str;
        switch (loginMode) {
            case FACEBOOK:
                str = "LoginModeFB";
                break;
            case GOOGLE:
                str = "LoginModeGooglePlus";
                break;
            case EMAIL:
                str = "LoginModeEmail";
                break;
            default:
                str = null;
                break;
        }
        PreferenceUtil.setString("login_mode", str);
        login(baseActivity, loginContext, loginMode, authenticationCallback, true);
    }

    /* access modifiers changed from: protected */
    public void login(BaseActivity baseActivity, LoginContext loginContext, LoginMode loginMode, AuthenticationCallback authenticationCallback, boolean z) {
        final AuthenticationCallback authenticationCallback2 = authenticationCallback;
        final LoginMode loginMode2 = loginMode;
        final LoginContext loginContext2 = loginContext;
        final boolean z2 = z;
        final BaseActivity baseActivity2 = baseActivity;
        AnonymousClass1 r0 = new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                AuthenticationService.this.mAuthenticationCallback = authenticationCallback2;
                if (loginMode2 == LoginMode.FACEBOOK) {
                    if (loginContext2.fbId != null || z2) {
                        FacebookManager.getInstance().getLoginSession().login(new LoginCallback() {
                            public void onSuccess() {
                                loginContext2.fbId = FacebookManager.getInstance().getLoginSession().getFbId();
                                AuthenticationService.this.loginToWish(baseActivity2, loginContext2, loginMode2);
                            }

                            public void onFailure(final ErrorContext errorContext) {
                                AuthenticationService.this.resetData(errorContext);
                                if (AuthenticationService.this.mAuthenticationCallback != null) {
                                    final AuthenticationCallback access$000 = AuthenticationService.this.mAuthenticationCallback;
                                    AuthenticationService.this.mAuthenticationCallback = null;
                                    AuthenticationService.this.mHandler.post(new Runnable() {
                                        public void run() {
                                            access$000.onFailure(errorContext);
                                        }
                                    });
                                }
                                AuthenticationService.this.logExternalApiError(loginMode2, "login_api_error", errorContext);
                            }

                            public void onCancel() {
                                if (AuthenticationService.this.mAuthenticationCallback != null) {
                                    final AuthenticationCallback access$000 = AuthenticationService.this.mAuthenticationCallback;
                                    AuthenticationService.this.mAuthenticationCallback = null;
                                    AuthenticationService.this.mHandler.post(new Runnable() {
                                        public void run() {
                                            access$000.onCancel();
                                        }
                                    });
                                }
                            }

                            public BaseActivity getActivityForResolutions() {
                                return baseActivity2;
                            }

                            public boolean isResolutionAllowed() {
                                return z2;
                            }
                        });
                    } else {
                        final ErrorContext errorContext = new ErrorContext();
                        errorContext.facebookCommunicationError = true;
                        AuthenticationService.this.resetData(errorContext);
                        if (AuthenticationService.this.mAuthenticationCallback != null) {
                            final AuthenticationCallback access$000 = AuthenticationService.this.mAuthenticationCallback;
                            AuthenticationService.this.mAuthenticationCallback = null;
                            AuthenticationService.this.mHandler.post(new Runnable() {
                                public void run() {
                                    access$000.onFailure(errorContext);
                                }
                            });
                        }
                        return null;
                    }
                } else if (loginMode2 == LoginMode.GOOGLE) {
                    if (loginContext2.googleId != null || z2) {
                        GoogleManager.getInstance().getLoginSession().login(new LoginCallback() {
                            public void onSuccess() {
                                loginContext2.googleId = GoogleManager.getInstance().getLoginSession().getLoggedInAccount().getId();
                                loginContext2.googleAuthToken = GoogleManager.getInstance().getLoginSession().getLoggedInAccount().getServerAuthCode();
                                if (loginContext2.googleId == null || loginContext2.googleAuthToken == null) {
                                    final ErrorContext errorContext = new ErrorContext();
                                    errorContext.googlePreauthorizationMissing = true;
                                    AuthenticationService.this.logExternalApiError(loginMode2, "authentication_error", errorContext);
                                    AuthenticationService.this.resetData(errorContext);
                                    if (AuthenticationService.this.mAuthenticationCallback != null) {
                                        final AuthenticationCallback access$000 = AuthenticationService.this.mAuthenticationCallback;
                                        AuthenticationService.this.mAuthenticationCallback = null;
                                        AuthenticationService.this.mHandler.post(new Runnable() {
                                            public void run() {
                                                access$000.onFailure(errorContext);
                                            }
                                        });
                                        return;
                                    }
                                    return;
                                }
                                AuthenticationService.this.loginToWish(baseActivity2, loginContext2, loginMode2);
                            }

                            public void onFailure(final ErrorContext errorContext) {
                                AuthenticationService.this.resetData(errorContext);
                                AuthenticationService.this.logExternalApiError(loginMode2, "login_api_error", errorContext);
                                if (AuthenticationService.this.mAuthenticationCallback != null) {
                                    final AuthenticationCallback access$000 = AuthenticationService.this.mAuthenticationCallback;
                                    AuthenticationService.this.mAuthenticationCallback = null;
                                    AuthenticationService.this.mHandler.post(new Runnable() {
                                        public void run() {
                                            access$000.onFailure(errorContext);
                                        }
                                    });
                                }
                            }

                            public void onCancel() {
                                if (AuthenticationService.this.mAuthenticationCallback != null) {
                                    final AuthenticationCallback access$000 = AuthenticationService.this.mAuthenticationCallback;
                                    AuthenticationService.this.mAuthenticationCallback = null;
                                    AuthenticationService.this.mHandler.post(new Runnable() {
                                        public void run() {
                                            access$000.onCancel();
                                        }
                                    });
                                }
                            }

                            public BaseActivity getActivityForResolutions() {
                                return baseActivity2;
                            }

                            public boolean isResolutionAllowed() {
                                return z2;
                            }
                        });
                    } else {
                        final ErrorContext errorContext2 = new ErrorContext();
                        errorContext2.googlePreauthorizationMissing = true;
                        AuthenticationService.this.resetData(errorContext2);
                        if (AuthenticationService.this.mAuthenticationCallback != null) {
                            final AuthenticationCallback access$0002 = AuthenticationService.this.mAuthenticationCallback;
                            AuthenticationService.this.mAuthenticationCallback = null;
                            AuthenticationService.this.mHandler.post(new Runnable() {
                                public void run() {
                                    access$0002.onFailure(errorContext2);
                                }
                            });
                        }
                        return null;
                    }
                } else if (loginMode2 == LoginMode.EMAIL) {
                    if (loginContext2.email == null || loginContext2.password == null) {
                        final ErrorContext errorContext3 = new ErrorContext();
                        AuthenticationService.this.resetData(errorContext3);
                        if (AuthenticationService.this.mAuthenticationCallback != null) {
                            final AuthenticationCallback access$0003 = AuthenticationService.this.mAuthenticationCallback;
                            AuthenticationService.this.mAuthenticationCallback = null;
                            AuthenticationService.this.mHandler.post(new Runnable() {
                                public void run() {
                                    access$0003.onFailure(errorContext3);
                                }
                            });
                        }
                    } else {
                        AuthenticationService.this.loginToWish(baseActivity2, loginContext2, loginMode2);
                    }
                }
                return null;
            }
        };
        r0.execute(new Void[0]);
    }

    public void logExternalApiError(LoginMode loginMode, String str, ErrorContext errorContext) {
        if (errorContext == null) {
            this.mLogExternalApiEventService.logApiError(loginMode, str, null, null);
        } else if (errorContext.googleErrorCode > 0) {
            this.mLogExternalApiEventService.logApiError(loginMode, str, Integer.valueOf(errorContext.googleErrorCode), null);
        } else if (errorContext.facebookErrorCode > 0) {
            this.mLogExternalApiEventService.logApiError(loginMode, str, null, Integer.valueOf(errorContext.facebookErrorCode));
        } else {
            this.mLogExternalApiEventService.logApiError(loginMode, str, null, null);
        }
    }

    public void logExternalApiSuccess(LoginMode loginMode) {
        this.mLogExternalApiEventService.logApiSuccess(loginMode);
    }

    public void loginToWish(BaseActivity baseActivity, final LoginContext loginContext, LoginMode loginMode) {
        Bundle bundle = new Bundle();
        if (loginContext.fbId != null) {
            bundle.putString("FbId", loginContext.fbId);
        }
        if (loginContext.email != null) {
            bundle.putString("Email", loginContext.email);
        }
        if (loginContext.googleId != null) {
            bundle.putString("GoogleId", loginContext.googleId);
        }
        final LoginProgress loginProgress = new LoginProgress();
        loginProgress.loginMode = loginMode;
        loginProgress.lastLoggedInUserId = PreferenceUtil.getString("LoggedInUser");
        loginProgress.isNewSession = !AuthenticationDataCenter.getInstance().isLoggedIn() && !AuthenticationDataCenter.getInstance().deserialize(bundle);
        if (!loginProgress.isNewSession) {
            loginProgress.userId = AuthenticationDataCenter.getInstance().getUserId();
            initializeDataCenters(loginContext, loginProgress);
        } else if (loginMode == LoginMode.FACEBOOK && loginContext.fbId != null) {
            logExternalApiSuccess(loginMode);
            this.mFacebookLoginService.requestService(loginContext.fbId, loginContext.sessionRefresh, new SuccessCallback() {
                public void onSuccess(String str, boolean z, SignupFlowContext signupFlowContext) {
                    loginProgress.userId = str;
                    loginProgress.isNewUser = z;
                    loginProgress.signupFlowContext = signupFlowContext;
                    AuthenticationService.this.initializeDataCenters(loginContext, loginProgress);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.errorMessage = str;
                    loginProgress.errorContext = errorContext;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            });
        } else if (loginMode == LoginMode.GOOGLE && loginContext.googleId != null) {
            logExternalApiSuccess(loginMode);
            this.mGoogleLoginService.requestService(loginContext.googleId, loginContext.googleAuthToken, GoogleManager.getInstance().getLoginSession().getAccountEmail(), loginContext.sessionRefresh, new SuccessCallback() {
                public void onSuccess(String str, boolean z, SignupFlowContext signupFlowContext) {
                    loginProgress.userId = str;
                    loginProgress.isNewUser = z;
                    loginProgress.signupFlowContext = signupFlowContext;
                    AuthenticationService.this.initializeDataCenters(loginContext, loginProgress);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.errorMessage = str;
                    errorContext.googlePreauthorizationMissing = true;
                    loginProgress.errorContext = errorContext;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            });
        } else if (loginMode == LoginMode.EMAIL && loginContext.createNewUser && loginContext.email != null && loginContext.password != null && loginContext.firstName != null && loginContext.lastName != null) {
            this.mEmailSignupService.requestService(loginContext.firstName, loginContext.lastName, loginContext.email, loginContext.password, new SuccessCallback() {
                public void onSuccess(String str, boolean z, SignupFlowContext signupFlowContext) {
                    loginProgress.userId = str;
                    loginProgress.isNewUser = z;
                    loginProgress.signupFlowContext = signupFlowContext;
                    AuthenticationService.this.initializeDataCenters(loginContext, loginProgress);
                }
            }, new DefaultCodeFailureCallback() {
                public void onFailure(String str, int i) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.errorMessage = str;
                    errorContext.errorCode = i;
                    loginProgress.errorContext = errorContext;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            });
        } else if (loginMode != LoginMode.EMAIL || loginContext.email == null || loginContext.password == null) {
            loginProgress.errorContext = new ErrorContext();
            updateLoginProgress(loginContext, loginProgress);
        } else {
            this.mEmailLoginService.requestService(loginContext.email, loginContext.password, loginContext.sessionRefresh, new SuccessCallback() {
                public void onSuccess(String str, boolean z, SignupFlowContext signupFlowContext) {
                    loginProgress.userId = str;
                    loginProgress.isNewUser = z;
                    loginProgress.signupFlowContext = signupFlowContext;
                    AuthenticationService.this.initializeDataCenters(loginContext, loginProgress);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.errorMessage = str;
                    loginProgress.errorContext = errorContext;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void initializeDataCenters(final LoginContext loginContext, final LoginProgress loginProgress) {
        boolean z = false;
        boolean z2 = !ProfileDataCenter.getInstance().isDataInitialized() && !ProfileDataCenter.getInstance().deserialize();
        if (loginProgress.isNewUser) {
            WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_CREATE_ACCOUNT_DONE);
        }
        if (z2) {
            this.mGetProfileService.requestService(loginProgress.userId, new GetProfileService.SuccessCallback() {
                public void onSuccess(WishUser wishUser) {
                    loginProgress.profileFetched = true;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            }, new DefaultCodeFailureCallback() {
                public void onFailure(String str, int i) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.errorMessage = str;
                    loginProgress.errorContext = errorContext;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            });
        } else {
            loginProgress.profileFetched = true;
        }
        if ((!StatusDataCenter.getInstance().isDataInitialized() && !StatusDataCenter.getInstance().deserialize()) || ((!ExperimentDataCenter.getInstance().isDataInitialized() && !ExperimentDataCenter.getInstance().deserialize()) || (!ConfigDataCenter.getInstance().isDataInitialized() && !ConfigDataCenter.getInstance().deserialize()))) {
            z = true;
        }
        if (z) {
            this.mGetUserStatusService.requestService(new DefaultSuccessCallback() {
                public void onSuccess() {
                    loginProgress.statusFetched = true;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    ErrorContext errorContext = new ErrorContext();
                    errorContext.errorMessage = str;
                    loginProgress.errorContext = errorContext;
                    AuthenticationService.this.updateLoginProgress(loginContext, loginProgress);
                }
            });
        } else {
            loginProgress.statusFetched = true;
        }
        updateLoginProgress(loginContext, loginProgress);
    }

    /* access modifiers changed from: protected */
    public void updateLoginProgress(final LoginContext loginContext, final LoginProgress loginProgress) {
        if (loginProgress.errorContext != null) {
            cancelLoginServiceRequests();
            resetData(loginProgress.errorContext);
            if (this.mAuthenticationCallback != null) {
                final AuthenticationCallback authenticationCallback = this.mAuthenticationCallback;
                this.mAuthenticationCallback = null;
                this.mHandler.post(new Runnable() {
                    public void run() {
                        authenticationCallback.onFailure(loginProgress.errorContext);
                    }
                });
            }
        } else if (loginProgress.isLoginComplete()) {
            PreferenceUtil.setBoolean("ForceRelogin", false);
            PreferenceUtil.setBoolean("ReferrerLoginSent", true);
            if (this.mAuthenticationCallback != null) {
                final AuthenticationCallback authenticationCallback2 = this.mAuthenticationCallback;
                this.mAuthenticationCallback = null;
                this.mHandler.post(new Runnable() {
                    public void run() {
                        if (!PreferenceUtil.getBoolean("SmartLockSaveAttempted")) {
                            SmartLockManager.getInstance().saveCredentialsForLater(AuthenticationService.this.getLoggedInCredentials(loginContext));
                        }
                        authenticationCallback2.onSuccess(loginProgress.userId, loginProgress.isNewUser, loginProgress.signupFlowContext);
                    }
                });
            }
            if (loginProgress.isNewSession) {
                performPostLoginTasks();
            }
        }
    }

    private void performPostLoginTasks() {
        FeedTileLogger.getInstance().deserialize();
        PushRegistrationService.registerPushNotificationToken();
    }

    /* access modifiers changed from: private */
    public Credential getLoggedInCredentials(LoginContext loginContext) {
        String str;
        if (isUsingGoogleLogin()) {
            GoogleSignInAccount loggedInAccount = GoogleManager.getInstance().getLoginSession().getLoggedInAccount();
            if (!(loggedInAccount == null || loggedInAccount.getEmail() == null)) {
                return new Builder(loggedInAccount.getEmail()).setAccountType("https://accounts.google.com").setName(loggedInAccount.getDisplayName()).setProfilePictureUri(loggedInAccount.getPhotoUrl()).build();
            }
        } else if (isUsingFacebookLogin()) {
            String name = ProfileDataCenter.getInstance().getName();
            String email = ProfileDataCenter.getInstance().getEmail();
            if (ProfileDataCenter.getInstance().getProfileImage() == null) {
                str = "";
            } else {
                str = ProfileDataCenter.getInstance().getProfileImage().getBaseUrlString();
            }
            if (email != null) {
                return new Builder(email).setAccountType("https://www.facebook.com").setProfilePictureUri(Uri.parse(str)).setName(name).build();
            }
        } else if (isUsingEmailLogin()) {
            String name2 = ProfileDataCenter.getInstance().getName();
            String email2 = ProfileDataCenter.getInstance().getEmail();
            if (email2 != null) {
                return new Builder(email2).setName(name2).setPassword(loginContext.password).build();
            }
        }
        return null;
    }

    public void updatePasswordForSmartLock(String str) {
        if (isUsingEmailLogin()) {
            SmartLockManager.getInstance().deleteCredentials();
            LoginContext loginContext = new LoginContext();
            loginContext.password = str;
            SmartLockManager.getInstance().saveCredentials(getLoggedInCredentials(loginContext));
        }
    }

    public void quickLogout() {
        resetData(true, false, false);
    }

    public void logout(final boolean z, final boolean z2, LogoutCallback logoutCallback) {
        cancelAllRequests();
        this.mLogoutCallback = logoutCallback;
        PreferenceUtil.setBoolean("DisableSmartLock", true);
        if (z) {
            SmartLockManager.getInstance().deleteCredentials();
        }
        FirebaseAppIndex.getInstance().removeAll();
        String token = FirebaseInstanceId.getInstance().getToken();
        String string = PreferenceUtil.getString("LoggedInUser");
        if (token != null) {
            this.mPushUnregistrationService.requestService(string, token, new DefaultSuccessCallback() {
                public void onSuccess() {
                    AuthenticationService.this.deleteAccountIfNeeded(z, z2);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    if (z2) {
                        AuthenticationService.this.deleteAccountIfNeeded(z, z2);
                    } else if (AuthenticationService.this.mLogoutCallback != null) {
                        LogoutCallback access$400 = AuthenticationService.this.mLogoutCallback;
                        AuthenticationService.this.mLogoutCallback = null;
                        access$400.onFailure(str);
                    }
                }
            });
        } else {
            deleteAccountIfNeeded(z, z2);
        }
    }

    /* access modifiers changed from: protected */
    public void deleteAccountIfNeeded(final boolean z, final boolean z2) {
        if (z) {
            this.mDeleteAccountService.requestService(new DefaultSuccessCallback() {
                public void onSuccess() {
                    AuthenticationService.this.deleteSocialAccountsIfNeeded(z, z2);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    if (z2) {
                        AuthenticationService.this.deleteSocialAccountsIfNeeded(z, z2);
                    } else if (AuthenticationService.this.mLogoutCallback != null) {
                        LogoutCallback access$400 = AuthenticationService.this.mLogoutCallback;
                        AuthenticationService.this.mLogoutCallback = null;
                        access$400.onFailure(str);
                    }
                }
            });
        } else {
            deleteSocialAccountsIfNeeded(z, z2);
        }
    }

    /* access modifiers changed from: protected */
    public void deleteSocialAccountsIfNeeded(final boolean z, final boolean z2) {
        if (!z || !GoogleManager.getInstance().getLoginSession().isLoggedIn()) {
            finishLogout(z);
        } else {
            GoogleSignInApiClient.getInstance().runOnConnected(new Runnable() {
                public void run() {
                    AuthenticationService.this.mPendingGoogleDeleteAccountResult = GoogleManager.getInstance().getLoginSession().deleteAccount(new LogoutCallback() {
                        public void onSuccess() {
                            AuthenticationService.this.mPendingGoogleDeleteAccountResult = null;
                            AuthenticationService.this.deleteSocialAccountsIfNeeded(z, z2);
                        }

                        public void onFailure(String str) {
                            AuthenticationService.this.mPendingGoogleDeleteAccountResult = null;
                            if (z2) {
                                AuthenticationService.this.deleteSocialAccountsIfNeeded(z, z2);
                            } else if (AuthenticationService.this.mLogoutCallback != null) {
                                LogoutCallback access$400 = AuthenticationService.this.mLogoutCallback;
                                AuthenticationService.this.mLogoutCallback = null;
                                access$400.onFailure(str);
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void finishLogout(boolean z) {
        PreferenceUtil.setBoolean("LoggedInUserDeleted", z);
        PreferenceUtil.setBoolean("SmartLockSaveAttempted", false);
        resetData(true, true, true);
        if (this.mLogoutCallback != null) {
            LogoutCallback logoutCallback = this.mLogoutCallback;
            this.mLogoutCallback = null;
            logoutCallback.onSuccess();
        }
    }

    /* access modifiers changed from: protected */
    public void resetData(ErrorContext errorContext) {
        boolean z = false;
        boolean z2 = errorContext != null && errorContext.facebookCommunicationError;
        if (errorContext != null && (errorContext.googleErrorCode > 0 || errorContext.googlePreauthorizationMissing)) {
            z = true;
        }
        resetData(true, z2, z);
    }

    /* access modifiers changed from: protected */
    public void resetData(boolean z, boolean z2, boolean z3) {
        StateStoreCache.getInstance().clearCache();
        HttpCookieManager.getInstance().setSessionCookie(null);
        AuthenticationDataCenter.getInstance().reset(true);
        ProfileDataCenter.getInstance().reset(true);
        ExperimentDataCenter.getInstance().reset(true);
        ConfigDataCenter.getInstance().reset(true);
        StatusDataCenter.getInstance().reset(true);
        if (z) {
            PreferenceUtil.setString("user_login_password", null);
        }
        if (z2) {
            FacebookManager.getInstance().getLoginSession().logout();
            PreferenceUtil.setString("fb_user_id", null);
        }
        if (z3) {
            GoogleManager.getInstance().getLoginSession().logout();
            PreferenceUtil.setString("google_plus_user_id", null);
        }
    }

    public static ArrayList<String> getDeviceEmails() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Account[] accounts = AccountManager.get(WishApplication.getInstance()).getAccounts();
            HashSet hashSet = new HashSet();
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            if (accounts != null) {
                for (Account account : accounts) {
                    if (!hashSet.contains(account.name) && pattern.matcher(account.name).matches()) {
                        arrayList.add(account.name);
                        hashSet.add(account.name);
                    }
                }
            }
        } catch (SecurityException unused) {
        }
        return arrayList;
    }

    protected static String getLoginMode() {
        return PreferenceUtil.getString("login_mode", "LoginModeEmail");
    }

    public static boolean isUsingFacebookLogin() {
        return getLoginMode().equals("LoginModeFB");
    }

    public static boolean isUsingEmailLogin() {
        return getLoginMode().equals("LoginModeEmail");
    }

    public static boolean isUsingGoogleLogin() {
        return getLoginMode().equals("LoginModeGooglePlus");
    }

    public void showErrorDialog(BaseActivity baseActivity, ErrorContext errorContext) {
        MultiButtonDialogFragment multiButtonDialogFragment;
        if (errorContext.facebookCommunicationError) {
            FacebookManager.getInstance().getLoginSession().showErrorDialog(baseActivity, errorContext);
        } else if (errorContext.googlePreauthorizationMissing || errorContext.googleErrorCode > 0) {
            GoogleManager.getInstance().getLoginSession().showErrorDialog(baseActivity, errorContext);
        } else {
            String str = errorContext.errorMessage;
            if (str == null || str.isEmpty()) {
                str = baseActivity.getString(R.string.authentication_error);
            }
            if (ExperimentDataCenter.getInstance().showNewSignupFlow()) {
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, baseActivity.getString(R.string.got_it), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                multiButtonDialogFragment = new MultiButtonDialogFragmentBuilder().setTitle(baseActivity.getString(R.string.something_went_wrong)).setSubTitle(str).setButtons(arrayList).hideXButton().build();
            } else {
                multiButtonDialogFragment = MultiButtonDialogFragment.createMultiButtonErrorDialog(str);
            }
            baseActivity.startDialog(multiButtonDialogFragment);
        }
    }
}
