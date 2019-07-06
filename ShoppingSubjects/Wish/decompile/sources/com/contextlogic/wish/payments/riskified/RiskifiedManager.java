package com.contextlogic.wish.payments.riskified;

import com.android.riskifiedbeacon.RiskifiedBeaconMain;
import com.android.riskifiedbeacon.RiskifiedBeaconMainInterface;
import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.application.ForegroundWatcher.ForegroundListener;
import com.contextlogic.wish.application.WishApplication;
import com.crashlytics.android.Crashlytics;
import java.util.UUID;

public class RiskifiedManager implements ForegroundListener {
    private static RiskifiedManager sInstance = new RiskifiedManager();
    private long SESSION_TIMEOUT_MS = 1800000;
    private RiskifiedBeaconMainInterface mBeacon;
    private long mLastBeaconUpdate;
    private String mSessionToken;

    public void onBackground() {
    }

    public void onLoggedInForeground() {
    }

    private RiskifiedManager() {
        ForegroundWatcher.getInstance().addListener(this);
    }

    public static RiskifiedManager getInstance() {
        return sInstance;
    }

    public void startBeacon() {
        if (this.mBeacon == null) {
            this.mBeacon = new RiskifiedBeaconMain();
            this.mSessionToken = UUID.randomUUID().toString();
            this.mLastBeaconUpdate = System.currentTimeMillis();
            try {
                this.mBeacon.startBeacon("www.wish.com", this.mSessionToken, false, WishApplication.getInstance());
            } catch (Throwable th) {
                StringBuilder sb = new StringBuilder();
                sb.append("Riskified start beacon: ");
                sb.append(th.toString());
                Crashlytics.logException(new Exception(sb.toString()));
                this.mBeacon = null;
                this.mSessionToken = null;
            }
        }
    }

    public void logPurchaseComplete() {
        try {
            if (this.mBeacon != null) {
                this.mBeacon.logSensitiveDeviceInfo();
            }
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Riskified log purchase complete: ");
            sb.append(th.toString());
            Crashlytics.logException(new Exception(sb.toString()));
        }
    }

    private void updateSessionToken() {
        if (this.mBeacon != null) {
            this.mSessionToken = UUID.randomUUID().toString();
            this.mLastBeaconUpdate = System.currentTimeMillis();
            this.mBeacon.updateSessionToken(this.mSessionToken);
        }
    }

    public String getSessionToken() {
        return this.mSessionToken;
    }

    public void onForeground() {
        if (this.mBeacon != null && this.mLastBeaconUpdate > 0 && this.mLastBeaconUpdate < System.currentTimeMillis() - this.SESSION_TIMEOUT_MS) {
            updateSessionToken();
        }
    }
}
