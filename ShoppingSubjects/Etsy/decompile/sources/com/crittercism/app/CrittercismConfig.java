package com.crittercism.app;

import android.os.Build.VERSION;
import com.crittercism.internal.cm;
import java.util.LinkedList;
import java.util.List;

public class CrittercismConfig {
    public static final int APM_EARLIEST_ALLOWED_API_LEVEL = 10;
    public static final int APM_LATEST_ALLOWED_API_LEVEL = 23;
    private String a = null;
    private boolean b = false;
    private boolean c = false;
    private boolean d = true;
    private boolean e = true;
    private boolean f = false;
    private boolean g = a();
    private List<String> h = new LinkedList();
    private List<String> i = new LinkedList();

    public CrittercismConfig() {
        this.h.add("vmwservices");
    }

    public CrittercismConfig(CrittercismConfig crittercismConfig) {
        this.a = crittercismConfig.a;
        this.b = crittercismConfig.b;
        this.c = crittercismConfig.c;
        this.d = crittercismConfig.d;
        this.e = crittercismConfig.e;
        this.f = crittercismConfig.f;
        this.g = crittercismConfig.g;
        setURLBlacklistPatterns(crittercismConfig.h);
        setPreserveQueryStringPatterns(crittercismConfig.i);
    }

    public List<String> getURLBlacklistPatterns() {
        return new LinkedList(this.h);
    }

    public void setURLBlacklistPatterns(List<String> list) {
        this.h.clear();
        if (list != null) {
            this.h.addAll(list);
        }
    }

    public void setPreserveQueryStringPatterns(List<String> list) {
        this.i.clear();
        if (list != null) {
            this.i.addAll(list);
        }
    }

    public List<String> getPreserveQueryStringPatterns() {
        return new LinkedList(this.i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CrittercismConfig)) {
            return false;
        }
        CrittercismConfig crittercismConfig = (CrittercismConfig) obj;
        if (this.b == crittercismConfig.b && this.f == crittercismConfig.f && this.d == crittercismConfig.d && this.e == crittercismConfig.e && isServiceMonitoringEnabled() == crittercismConfig.isServiceMonitoringEnabled() && isVersionCodeToBeIncludedInVersionString() == crittercismConfig.isVersionCodeToBeIncludedInVersionString()) {
            String str = this.a;
            String str2 = crittercismConfig.a;
            boolean z = str == null ? str2 == null : str.equals(str2);
            if (!z || !this.h.equals(crittercismConfig.h) || !this.i.equals(crittercismConfig.i)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2;
        String str = this.a;
        if (str != null) {
            i2 = str.hashCode();
        } else {
            i2 = 0;
        }
        return ((((((i2 + 0) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + Integer.valueOf(((((((((((false + (this.b ? 1 : 0)) << 1) + (this.f ? 1 : 0)) << 1) + (this.d ? 1 : 0)) << 1) + (this.e ? 1 : 0)) << 1) + (isServiceMonitoringEnabled() ? 1 : 0)) << 1) + (isVersionCodeToBeIncludedInVersionString() ? 1 : 0)).hashCode();
    }

    public final String getCustomVersionName() {
        return this.a;
    }

    public final void setCustomVersionName(String str) {
        this.a = str;
    }

    public final boolean delaySendingAppLoad() {
        return this.b;
    }

    public final void setDelaySendingAppLoad(boolean z) {
        this.b = z;
    }

    public final boolean isVersionCodeToBeIncludedInVersionString() {
        return this.c;
    }

    public final void setVersionCodeToBeIncludedInVersionString(boolean z) {
        this.c = z;
    }

    public final boolean allowsCellularAccess() {
        return this.d;
    }

    public final void setAllowsCellularAccess(boolean z) {
        this.d = z;
    }

    public boolean reportLocationData() {
        return this.e;
    }

    public final void setReportLocationData(boolean z) {
        this.e = z;
    }

    public final boolean isLogcatReportingEnabled() {
        return this.f;
    }

    public final void setLogcatReportingEnabled(boolean z) {
        this.f = z;
    }

    private static boolean a() {
        return VERSION.SDK_INT >= 10 && VERSION.SDK_INT <= 23;
    }

    public final boolean isServiceMonitoringEnabled() {
        return this.g;
    }

    public final void setServiceMonitoringEnabled(boolean z) {
        if (a() || !z) {
            this.g = z;
        } else {
            cm.c("OPTMZ is currently only allowed for api levels 10 to 23.  APM will not be installed");
        }
    }

    public final void disableNougatServiceMonitoring() {
        if (VERSION.SDK_INT > 23) {
            setServiceMonitoringEnabled(false);
        }
    }
}
