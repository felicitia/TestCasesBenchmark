package com.contextlogic.wish.payments.ThreatMetrix;

import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.application.ForegroundWatcher.ForegroundListener;
import com.contextlogic.wish.application.WishApplication;
import com.threatmetrix.TrustDefender.Config;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.Profile.Result;
import com.threatmetrix.TrustDefender.ProfilingOptions;
import com.threatmetrix.TrustDefender.THMStatusCode;
import com.threatmetrix.TrustDefender.TrustDefender;
import java.util.UUID;

public class ThreatMetrixManager implements ForegroundListener, EndNotifier {
    private static ThreatMetrixManager sInstance = new ThreatMetrixManager();
    private String mPendingSessionToken;
    private String mSessionToken;

    public void onBackground() {
    }

    public void onLoggedInForeground() {
    }

    private ThreatMetrixManager() {
        ForegroundWatcher.getInstance().addListener(this);
    }

    public static ThreatMetrixManager getInstance() {
        return sInstance;
    }

    public void startProfiling() {
        if (this.mPendingSessionToken == null && this.mSessionToken == null) {
            this.mPendingSessionToken = createSessionToken();
            TrustDefender.getInstance().init(new Config().setOrgId("0gyenb54").setContext(WishApplication.getInstance()).setFPServer("imgs.signifyd.com"));
            TrustDefender.getInstance().doProfileRequest(new ProfilingOptions().setSessionID(this.mPendingSessionToken), (EndNotifier) this);
        }
    }

    private String createSessionToken() {
        StringBuilder sb = new StringBuilder();
        sb.append("7625-");
        sb.append(UUID.randomUUID().toString());
        return sb.toString();
    }

    public String getSessionToken() {
        return this.mSessionToken;
    }

    public void complete(Result result) {
        if (result.getStatus() == THMStatusCode.THM_OK) {
            this.mSessionToken = this.mPendingSessionToken;
        }
        this.mPendingSessionToken = null;
    }

    public void onForeground() {
        startProfiling();
    }
}
