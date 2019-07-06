package com.contextlogic.wish.social;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.social.google.GoogleSignInApiClient;
import com.contextlogic.wish.util.PreferenceUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequest.Builder;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.Iterator;

public class SmartLockManager implements ConnectionCallbacks, OnConnectionFailedListener {
    private static SmartLockManager sInstance = new SmartLockManager();
    /* access modifiers changed from: private */
    public Callback mCallback;
    /* access modifiers changed from: private */
    public Credential mCredential;
    /* access modifiers changed from: private */
    public CredentialRequest mCredentialRequest;
    /* access modifiers changed from: private */
    public GoogleApiClient mCredentialsApiClient;
    private ArrayList<Runnable> mOnConnectedTasks = new ArrayList<>();
    private Callback mSaveCallback;
    private boolean mShouldSaveCredentials;

    public interface Callback {
        void onFailure();

        void onSuccess(Credential credential);

        void withActivityForResolution(ResolutionActivityTask resolutionActivityTask);
    }

    public interface ResolutionActivityTask {
        void performTask(BaseActivity baseActivity);
    }

    public void onConnectionSuspended(int i) {
    }

    private SmartLockManager() {
        reset();
    }

    public static SmartLockManager getInstance() {
        return sInstance;
    }

    private void reset() {
        resetCredentialsClient();
        this.mCredentialRequest = new Builder().setIdTokenRequested(true).setPasswordLoginSupported(true).setAccountTypes("https://accounts.google.com", "https://www.facebook.com").build();
    }

    public void connectCredentialsClient() {
        if (this.mCredentialsApiClient != null && !this.mCredentialsApiClient.isConnected() && !this.mCredentialsApiClient.isConnecting()) {
            this.mCredentialsApiClient.connect();
        }
    }

    public void disconnectCredentialsClient() {
        if (this.mCredentialsApiClient == null) {
            return;
        }
        if (this.mCredentialsApiClient.isConnected() || this.mCredentialsApiClient.isConnecting()) {
            try {
                this.mCredentialsApiClient.disconnect();
            } catch (Throwable unused) {
            }
        }
    }

    public void requestCredentials(Callback callback, final boolean z) {
        this.mCallback = callback;
        if (!z || this.mCredential == null) {
            GoogleSignInApiClient.getInstance().connect();
            runOnConnected(new Runnable() {
                public void run() {
                    Auth.CredentialsApi.request(SmartLockManager.this.mCredentialsApiClient, SmartLockManager.this.mCredentialRequest).setResultCallback(new ResultCallback<CredentialRequestResult>() {
                        public void onResult(CredentialRequestResult credentialRequestResult) {
                            if (credentialRequestResult.getStatus().isSuccess()) {
                                SmartLockManager.this.onCredentialRetrieved(credentialRequestResult.getCredential());
                            } else if (!z) {
                                SmartLockManager.this.resolveResult(credentialRequestResult.getStatus());
                            } else if (SmartLockManager.this.mCallback != null) {
                                Callback access$200 = SmartLockManager.this.mCallback;
                                SmartLockManager.this.mCallback = null;
                                access$200.onFailure();
                            }
                        }
                    });
                }
            });
            return;
        }
        if (this.mCallback != null) {
            Callback callback2 = this.mCallback;
            this.mCallback = null;
            callback2.onSuccess(this.mCredential);
        }
    }

    /* access modifiers changed from: private */
    public void resolveResult(final Status status) {
        if (status.getStatusCode() == 6 && this.mCallback != null) {
            this.mCallback.withActivityForResolution(new ResolutionActivityTask() {
                public void performTask(BaseActivity baseActivity) {
                    try {
                        status.startResolutionForResult(baseActivity, baseActivity.addResultCodeCallback(new ActivityResultCallback() {
                            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                SmartLockManager.this.onCredentialRetrieved(intent != null ? (Credential) intent.getParcelableExtra("com.google.android.gms.credentials.Credential") : null);
                            }
                        }));
                    } catch (SendIntentException unused) {
                    }
                }
            });
        } else if (this.mCallback != null) {
            Callback callback = this.mCallback;
            this.mCallback = null;
            callback.onFailure();
        }
    }

    /* access modifiers changed from: private */
    public void onCredentialRetrieved(Credential credential) {
        this.mCredential = credential;
        if (this.mCallback != null && credential != null) {
            Callback callback = this.mCallback;
            this.mCallback = null;
            callback.onSuccess(credential);
        } else if (this.mCallback != null) {
            Callback callback2 = this.mCallback;
            this.mCallback = null;
            callback2.onFailure();
        }
    }

    public void saveCredentials(final Credential credential) {
        this.mCredential = credential;
        PreferenceUtil.setBoolean("DisableSmartLock", false);
        PreferenceUtil.setBoolean("SmartLockSaveAttempted", true);
        if (credential != null) {
            runOnConnected(new Runnable() {
                public void run() {
                    Auth.CredentialsApi.save(SmartLockManager.this.mCredentialsApiClient, credential).setResultCallback(new ResultCallback<Status>() {
                        public void onResult(Status status) {
                            SmartLockManager.this.handleCredentialSaveResult(status);
                        }
                    });
                }
            });
        }
    }

    public void handleCredentialSaveResult(final Status status) {
        if (status.isSuccess()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_SAVE_CREDENTIALS_SUCCESS);
        } else if (!status.hasResolution() || this.mSaveCallback == null) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_SAVE_CREDENTIALS_CANCEL);
        } else {
            this.mSaveCallback.withActivityForResolution(new ResolutionActivityTask() {
                public void performTask(BaseActivity baseActivity) {
                    try {
                        status.startResolutionForResult(baseActivity, baseActivity.addResultCodeCallback(new ActivityResultCallback() {
                            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                if (i2 == -1) {
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_SAVE_CREDENTIALS_SUCCESS);
                                } else if (i2 == 0) {
                                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_SAVE_CREDENTIALS_CANCEL);
                                }
                            }
                        }));
                    } catch (SendIntentException unused) {
                    }
                }
            });
        }
        this.mSaveCallback = null;
    }

    public void deleteCredentials() {
        PreferenceUtil.setBoolean("DisableSmartLock", true);
        runOnConnected(new Runnable() {
            public void run() {
                if (SmartLockManager.this.mCredential != null) {
                    Auth.CredentialsApi.delete(SmartLockManager.this.mCredentialsApiClient, SmartLockManager.this.mCredential);
                    SmartLockManager.this.mCredential = null;
                }
            }
        });
    }

    public void retrieveSignInHints(Callback callback) {
        this.mCallback = callback;
        runOnConnected(new Runnable() {
            public void run() {
                final PendingIntent hintPickerIntent = Auth.CredentialsApi.getHintPickerIntent(SmartLockManager.this.mCredentialsApiClient, new HintRequest.Builder().setIdTokenRequested(true).setHintPickerConfig(new CredentialPickerConfig.Builder().setShowCancelButton(true).build()).setEmailAddressIdentifierSupported(true).setAccountTypes("https://accounts.google.com", "https://www.facebook.com").build());
                if (SmartLockManager.this.mCallback != null) {
                    SmartLockManager.this.mCallback.withActivityForResolution(new ResolutionActivityTask() {
                        public void performTask(BaseActivity baseActivity) {
                            int addResultCodeCallback = baseActivity.addResultCodeCallback(new ActivityResultCallback() {
                                public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                    baseActivity.hideLoadingDialog();
                                    if (i2 == -1) {
                                        if (SmartLockManager.this.mCallback != null) {
                                            Credential credential = (Credential) intent.getParcelableExtra("com.google.android.gms.credentials.Credential");
                                            if (credential != null) {
                                                Callback access$200 = SmartLockManager.this.mCallback;
                                                SmartLockManager.this.mCallback = null;
                                                access$200.onSuccess(credential);
                                            }
                                        }
                                    } else if (i2 == 0 && SmartLockManager.this.mCallback != null) {
                                        Callback access$2002 = SmartLockManager.this.mCallback;
                                        SmartLockManager.this.mCallback = null;
                                        access$2002.onFailure();
                                    }
                                }
                            });
                            if (hintPickerIntent != null) {
                                try {
                                    baseActivity.showLoadingDialog();
                                    baseActivity.startIntentSenderForResult(hintPickerIntent.getIntentSender(), addResultCodeCallback, null, 0, 0, 0);
                                } catch (SendIntentException unused) {
                                    SmartLockManager.this.mCallback.onFailure();
                                    SmartLockManager.this.mCallback = null;
                                }
                            } else {
                                SmartLockManager.this.mCallback.onFailure();
                            }
                        }
                    });
                }
            }
        });
    }

    public void runOnConnected(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (this.mCredentialsApiClient.isConnected()) {
            runnable.run();
            return;
        }
        this.mOnConnectedTasks.add(runnable);
        connectCredentialsClient();
    }

    private void resetCredentialsClient() {
        disconnectCredentialsClient();
        this.mCredentialsApiClient = new GoogleApiClient.Builder(WishApplication.getInstance()).addApi(Auth.CREDENTIALS_API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
    }

    public void onConnected(Bundle bundle) {
        Iterator it = new ArrayList(this.mOnConnectedTasks).iterator();
        while (it.hasNext()) {
            Runnable runnable = (Runnable) it.next();
            if (runnable != null) {
                runnable.run();
            }
            this.mOnConnectedTasks.remove(runnable);
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        resetCredentialsClient();
        if (this.mCallback != null) {
            Callback callback = this.mCallback;
            this.mCallback = null;
            callback.onFailure();
        }
    }

    public void saveCredentialsForLater(Credential credential) {
        this.mCredential = credential;
        this.mShouldSaveCredentials = true;
    }

    public void saveCredentials(Callback callback) {
        if (this.mCredential != null && this.mShouldSaveCredentials) {
            this.mSaveCallback = callback;
            this.mShouldSaveCredentials = false;
            saveCredentials(this.mCredential);
        }
    }

    public boolean showCredentialSavedConfirmation() {
        return this.mShouldSaveCredentials;
    }
}
