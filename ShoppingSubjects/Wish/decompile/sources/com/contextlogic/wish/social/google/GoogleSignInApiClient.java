package com.contextlogic.wish.social.google;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoogleSignInApiClient implements ConnectionCallbacks, OnConnectionFailedListener {
    private static GoogleSignInApiClient sInstance = new GoogleSignInApiClient();
    private GoogleApiClient mApiClient;
    private List<ApiClientCallback> mCallbacks = new ArrayList();
    private GoogleSignInOptions mGoogleSignInOptions;
    private List<Runnable> mOnConnectedFailedTasks = new ArrayList();
    private List<Runnable> mOnConnectedTasks = new ArrayList();

    public interface ApiClientCallback {
        void onConnected(Bundle bundle);

        void onConnectionFailed(ConnectionResult connectionResult);

        void onConnectionSuspended(int i);
    }

    private GoogleSignInApiClient() {
        initializeApiClient();
    }

    public static GoogleSignInApiClient getInstance() {
        return sInstance;
    }

    public void initializeApiClient() {
        updateSignInOptions(new Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestServerAuthCode(WishApplication.getInstance().getString(R.string.google_server_key)).requestEmail().build());
    }

    public GoogleApiClient getClient() {
        return this.mApiClient;
    }

    public void connect() {
        if (this.mApiClient != null && !this.mApiClient.isConnected() && !this.mApiClient.isConnecting()) {
            this.mApiClient.connect();
        }
    }

    public void disconnect() {
        if (this.mApiClient == null) {
            return;
        }
        if (this.mApiClient.isConnected() || this.mApiClient.isConnecting()) {
            this.mApiClient.disconnect();
        }
    }

    public void runOnConnected(Runnable runnable) {
        runOnConnected(runnable, null);
    }

    public void runOnConnected(Runnable runnable, Runnable runnable2) {
        if (runnable == null) {
            return;
        }
        if (this.mApiClient.isConnected()) {
            runnable.run();
            return;
        }
        this.mOnConnectedTasks.add(runnable);
        if (runnable2 != null) {
            this.mOnConnectedFailedTasks.add(runnable2);
        }
        connect();
    }

    public void register(ApiClientCallback apiClientCallback) {
        if (this.mCallbacks != null) {
            this.mCallbacks.add(apiClientCallback);
        }
    }

    public void updateSignInOptions(GoogleSignInOptions googleSignInOptions) {
        disconnect();
        this.mGoogleSignInOptions = googleSignInOptions;
        this.mApiClient = new GoogleApiClient.Builder(WishApplication.getInstance()).addApi(Auth.GOOGLE_SIGN_IN_API, this.mGoogleSignInOptions).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
    }

    public void onConnected(Bundle bundle) {
        for (ApiClientCallback apiClientCallback : this.mCallbacks) {
            if (apiClientCallback != null) {
                apiClientCallback.onConnected(bundle);
            }
        }
        Iterator it = new ArrayList(this.mOnConnectedTasks).iterator();
        while (it.hasNext()) {
            Runnable runnable = (Runnable) it.next();
            if (runnable != null) {
                runnable.run();
            }
            this.mOnConnectedTasks.remove(runnable);
        }
        this.mOnConnectedFailedTasks.clear();
    }

    public void onConnectionSuspended(int i) {
        for (ApiClientCallback apiClientCallback : this.mCallbacks) {
            if (apiClientCallback != null) {
                apiClientCallback.onConnectionSuspended(i);
            }
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        initializeApiClient();
        for (ApiClientCallback apiClientCallback : this.mCallbacks) {
            if (apiClientCallback != null) {
                apiClientCallback.onConnectionFailed(connectionResult);
            }
        }
        Iterator it = new ArrayList(this.mOnConnectedFailedTasks).iterator();
        while (it.hasNext()) {
            Runnable runnable = (Runnable) it.next();
            if (runnable != null) {
                runnable.run();
            }
            this.mOnConnectedFailedTasks.remove(runnable);
        }
        this.mOnConnectedTasks.clear();
    }
}
