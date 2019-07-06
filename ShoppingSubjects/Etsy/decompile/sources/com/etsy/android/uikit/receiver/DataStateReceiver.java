package com.etsy.android.uikit.receiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import com.etsy.android.lib.logger.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataStateReceiver extends BroadcastReceiver {
    private static final int MESSAGE_DATA_CONNECTED = 1;
    private static final int MESSAGE_DATA_DISCONNECTED = 2;
    private static final String mTAG = "DataStateReceiver";
    private long mFailureThreshold;
    private boolean mIsDataConnected;
    private long mLastStateChange;
    private final List<a> mListeners;
    protected Handler mMessageHandler;
    private NetworkInfo mNetworkInfo;
    private int mNetworkType;
    private long mSuccessThreshold;

    public interface a {
        void a(boolean z);
    }

    public static abstract class b implements a {
        private Boolean a;

        public abstract void a();

        public abstract void b();

        public void a(boolean z) {
            if (this.a == null || this.a.booleanValue() != z) {
                if (z) {
                    a();
                } else {
                    b();
                }
                this.a = Boolean.valueOf(z);
            }
        }
    }

    public DataStateReceiver(boolean z) {
        this(z, 0, 0);
    }

    public DataStateReceiver(boolean z, long j, long j2) {
        this.mIsDataConnected = false;
        this.mLastStateChange = 0;
        this.mFailureThreshold = 0;
        this.mSuccessThreshold = 0;
        this.mNetworkInfo = null;
        this.mNetworkType = -1;
        this.mListeners = Collections.synchronizedList(new ArrayList());
        this.mMessageHandler = new Handler() {
            public final void handleMessage(Message message) {
                if (message.what == 1) {
                    DataStateReceiver.this.notifyObservers(true);
                } else if (message.what == 2) {
                    DataStateReceiver.this.notifyObservers(false);
                }
            }
        };
        this.mIsDataConnected = z;
        this.mFailureThreshold = j;
        this.mSuccessThreshold = j2;
    }

    public boolean addListener(a aVar) {
        return this.mListeners.add(aVar);
    }

    public boolean removeListener(a aVar) {
        return this.mListeners.remove(aVar);
    }

    /* access modifiers changed from: private */
    public void notifyObservers(boolean z) {
        for (a a2 : this.mListeners) {
            a2.a(z);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            if (intent.getBooleanExtra("noConnectivity", false)) {
                if (this.mIsDataConnected) {
                    this.mLastStateChange = System.currentTimeMillis();
                }
                this.mIsDataConnected = false;
                f.c(mTAG, "Data Connection Disconnected");
            } else {
                if (!this.mIsDataConnected) {
                    this.mLastStateChange = System.currentTimeMillis();
                }
                this.mIsDataConnected = true;
                f.c(mTAG, "Data Connection Connected");
            }
            updateNetworkInfo(context);
            notifyObserversDelayed(this.mIsDataConnected);
        }
    }

    @SuppressLint({"MissingPermission"})
    private void updateNetworkInfo(Context context) {
        this.mNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (this.mNetworkInfo != null) {
            this.mNetworkType = this.mNetworkInfo.getType();
        } else {
            this.mNetworkType = -1;
        }
    }

    public int getActiveNetworkType() {
        return this.mNetworkType;
    }

    public synchronized boolean isDataConnected() {
        if (this.mLastStateChange > 0) {
            if (!this.mIsDataConnected && System.currentTimeMillis() < this.mLastStateChange + this.mFailureThreshold) {
                return true;
            }
            if (this.mIsDataConnected && System.currentTimeMillis() < this.mLastStateChange + this.mSuccessThreshold) {
                return false;
            }
        }
        return this.mIsDataConnected;
    }

    /* access modifiers changed from: protected */
    public void notifyObserversDelayed(boolean z) {
        this.mMessageHandler.removeMessages(1);
        this.mMessageHandler.removeMessages(2);
        if (z) {
            this.mMessageHandler.sendMessageDelayed(this.mMessageHandler.obtainMessage(1), this.mSuccessThreshold);
        } else {
            this.mMessageHandler.sendMessageDelayed(this.mMessageHandler.obtainMessage(2), this.mFailureThreshold);
        }
    }
}
