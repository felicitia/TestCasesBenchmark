package io.branch.referral;

import android.app.Application;
import android.content.Context;

public class BranchApp extends Application {
    public void onCreate() {
        super.onCreate();
        if (!h.a((Context) this)) {
            Branch.a((Context) this);
        } else {
            Branch.b((Context) this);
        }
    }
}
