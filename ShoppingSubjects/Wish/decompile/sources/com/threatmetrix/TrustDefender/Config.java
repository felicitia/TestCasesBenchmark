package com.threatmetrix.TrustDefender;

import android.content.Context;
import com.threatmetrix.TrustDefender.internal.A;
import com.threatmetrix.TrustDefender.internal.TL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Config extends A {

    /* renamed from: case reason: not valid java name */
    static final long f1case = TimeUnit.MINUTES.toMillis(60);

    /* renamed from: do reason: not valid java name */
    static final int f2do = ((int) TimeUnit.SECONDS.toMillis(30));

    /* renamed from: else reason: not valid java name */
    private static final String f3else = TL.m331if(Config.class);

    /* renamed from: for reason: not valid java name */
    static final int f4for = ((int) TimeUnit.SECONDS.toMillis(30));

    /* renamed from: if reason: not valid java name */
    static final long f5if = TimeUnit.MINUTES.toMillis(15);

    /* renamed from: int reason: not valid java name */
    static final int f6int = ((int) TimeUnit.MINUTES.toMillis(30));

    /* renamed from: new reason: not valid java name */
    static final int f7new = ((int) TimeUnit.MINUTES.toMillis(3));

    /* renamed from: byte reason: not valid java name */
    private long f8byte = A.THM_OPTION_ALL;
    public int m_accuracy = 1;
    public String m_apiKey = null;
    public List<String> m_certificateHashes = null;
    public Context m_context = null;
    private boolean m_disableAppHash = false;
    boolean m_disableInitPackageScan = false;
    public boolean m_disableLocSerOnBatteryLow = false;
    boolean m_disableProfilePackageScan = false;
    private boolean m_disableWebView = false;
    public String m_fp_server = "h-sdk.online-metrix.net";
    public long m_highPowerUpdateTime = f1case;
    boolean m_initPackageScanInterruptible = true;
    int m_initPackageScanLimit = 0;
    int m_initPackageScanTimeLimit = f2do;
    public long m_lowPowerUpdateTime = f5if;
    public String m_orgId = null;
    int m_packageScanLimit = 0;
    int m_packageScanTimeLimit = f2do;
    public boolean m_registerForLocationServices = false;
    public boolean m_registerForPush = false;
    int m_screenOffTimeout = f7new;
    public int m_timeout = f4for;

    /* renamed from: try reason: not valid java name */
    boolean f9try = false;

    public Config setDisableInitPackageScan(boolean z) {
        this.m_disableInitPackageScan = z;
        return this;
    }

    public Config setDisableProfilePackageScan(boolean z) {
        this.m_disableProfilePackageScan = z;
        return this;
    }

    public Config setDisableWebView(boolean z) {
        this.m_disableWebView = z;
        return this;
    }

    public Config setDisableAppHashing(boolean z) {
        this.m_disableAppHash = z;
        return this;
    }

    public Config setTimeout(int i, TimeUnit timeUnit) {
        this.m_timeout = (int) timeUnit.toMillis((long) i);
        if (this.m_timeout == 0 && i != 0) {
            this.m_timeout = 1;
        }
        return this;
    }

    public Config setApiKey(String str) {
        this.m_apiKey = str;
        return this;
    }

    public Config setRegisterForLocationServices(boolean z) {
        this.m_registerForLocationServices = z;
        return this;
    }

    public Config setLowPowerUpdateTime(long j, TimeUnit timeUnit) {
        this.m_lowPowerUpdateTime = timeUnit.toMillis(j);
        return this;
    }

    public Config setHighPowerUpdateTime(long j, TimeUnit timeUnit) {
        this.m_highPowerUpdateTime = timeUnit.toMillis(j);
        return this;
    }

    public Config setLocationAccuracy(int i) {
        this.m_accuracy = i;
        return this;
    }

    /* renamed from: if reason: not valid java name */
    public final long m0if() {
        long j = this.f8byte;
        if (this.m_disableWebView) {
            j &= -39;
        }
        return this.m_disableAppHash ? j & -12289 : j;
    }

    public Config setContext(Context context) {
        this.m_context = context;
        return this;
    }

    public Config disablePackageScanTimeLimit() {
        this.m_packageScanTimeLimit = 0;
        return this;
    }

    public Config setPackageScanTimeLimit(int i, TimeUnit timeUnit) {
        this.m_packageScanTimeLimit = (int) timeUnit.toMillis((long) i);
        if (this.m_packageScanTimeLimit == 0 && i != 0) {
            this.m_packageScanTimeLimit = 1;
        }
        return this;
    }

    public Config disableInitPackageScanTimeLimit() {
        this.m_initPackageScanTimeLimit = 0;
        return this;
    }

    public Config setInitPackageScanTimeLimit(int i, TimeUnit timeUnit) {
        this.m_initPackageScanTimeLimit = (int) timeUnit.toMillis((long) i);
        if (this.m_initPackageScanTimeLimit == 0 && i != 0) {
            this.m_initPackageScanTimeLimit = 1;
        }
        return this;
    }

    public Config setRegisterForPush(boolean z) {
        this.m_registerForPush = z;
        return this;
    }

    public Config setFPServer(String str) {
        this.m_fp_server = str;
        return this;
    }

    public Config setDisableLocSerOnBatteryLow(boolean z) {
        this.m_disableLocSerOnBatteryLow = z;
        return this;
    }

    public Config setScreenOffTimeout(int i, TimeUnit timeUnit) {
        this.m_screenOffTimeout = (int) timeUnit.toMillis((long) i);
        return this;
    }

    public Config setOrgId(String str) {
        this.m_orgId = str;
        return this;
    }

    public Config enableOption(long j) {
        this.f8byte |= j;
        return this;
    }

    public Config disableOption(long j) {
        this.f8byte &= j ^ -1;
        return this;
    }

    public Config setCertificateHashes(List<String> list) {
        this.m_certificateHashes = list;
        return this;
    }

    public Config setCertificateHashes(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        this.m_certificateHashes = arrayList;
        return this;
    }

    public Config setUseOKHTTP(boolean z) {
        this.f9try = z;
        return this;
    }
}
