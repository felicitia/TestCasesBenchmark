package com.threatmetrix.TrustDefender.internal;

import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class EF implements ValueCallback<String> {

    /* renamed from: do reason: not valid java name */
    private static final String f153do = TL.m331if(EF.class);

    /* renamed from: for reason: not valid java name */
    public String f154for;

    /* renamed from: if reason: not valid java name */
    CountDownLatch f155if = null;

    /* renamed from: int reason: not valid java name */
    public final ArrayList<String> f156int = new ArrayList<>();

    public /* synthetic */ void onReceiveValue(Object obj) {
        String str = (String) obj;
        if (str != null) {
            if (str.length() == 2 && str.equals("\"\"")) {
                str = "";
            } else if (str.length() > 1) {
                str = str.substring(1, str.length() - 1);
            }
        }
        m51int(str, "onReceiveValue");
    }

    EF(CountDownLatch countDownLatch) {
        m52do(countDownLatch);
    }

    /* renamed from: do reason: not valid java name */
    public final void m52do(CountDownLatch countDownLatch) {
        if (this.f155if != null) {
            String str = f153do;
            StringBuilder sb = new StringBuilder("existing latch: ");
            sb.append(this.f155if.hashCode());
            sb.append(" with count: ");
            sb.append(this.f155if.getCount());
            TL.m338new(str, sb.toString());
            TL.m338new(f153do, "Setting latch when latch already has non-null value");
        }
        this.f155if = countDownLatch;
        if (this.f155if != null) {
            String str2 = f153do;
            StringBuilder sb2 = new StringBuilder("new latch: ");
            sb2.append(countDownLatch.hashCode());
            sb2.append(" with count: ");
            sb2.append(countDownLatch.getCount());
            TL.m338new(str2, sb2.toString());
        }
    }

    /* renamed from: int reason: not valid java name */
    private void m51int(String str, String str2) {
        try {
            CountDownLatch countDownLatch = this.f155if;
            String str3 = str == null ? "null" : str;
            long j = 0;
            if (countDownLatch != null) {
                j = countDownLatch.getCount();
            }
            String str4 = f153do;
            StringBuilder sb = new StringBuilder("in ");
            sb.append(str2);
            sb.append("(");
            sb.append(str3);
            sb.append(") count = ");
            sb.append(j);
            TL.m338new(str4, sb.toString());
            this.f154for = str;
            if (str == null) {
                this.f156int.add("");
            } else {
                this.f156int.add(str);
            }
            if (countDownLatch != null) {
                String str5 = f153do;
                StringBuilder sb2 = new StringBuilder("countdown latch: ");
                sb2.append(countDownLatch.hashCode());
                sb2.append(" with count: ");
                sb2.append(countDownLatch.getCount());
                TL.m338new(str5, sb2.toString());
                countDownLatch.countDown();
                String str6 = f153do;
                StringBuilder sb3 = new StringBuilder("in ");
                sb3.append(str2);
                sb3.append("() count = ");
                sb3.append(countDownLatch.getCount());
                sb3.append(" and ");
                sb3.append(countDownLatch == this.f155if ? "latch constant" : "latch changed");
                TL.m338new(str6, sb3.toString());
                return;
            }
            String str7 = f153do;
            StringBuilder sb4 = new StringBuilder("in ");
            sb4.append(str2);
            sb4.append("() latch == null");
            TL.m332if(str7, sb4.toString());
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public void getString(String str) {
        m51int(str, "getString");
    }
}
