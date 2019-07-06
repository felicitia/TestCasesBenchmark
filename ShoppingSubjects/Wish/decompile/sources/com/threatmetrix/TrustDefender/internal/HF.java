package com.threatmetrix.TrustDefender.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class HF {

    /* renamed from: byte reason: not valid java name */
    protected String f175byte;

    /* renamed from: new reason: not valid java name */
    List<String> f176new = null;

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public abstract JR m67new(Y y);

    /* renamed from: new reason: not valid java name */
    public abstract void m68new(int i, boolean z);

    HF(List<String> list) {
        ArrayList arrayList = null;
        if (list != null) {
            arrayList = new ArrayList();
            for (String lowerCase : list) {
                arrayList.add(lowerCase.toLowerCase(Locale.US));
            }
        }
        this.f176new = arrayList;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: do reason: not valid java name */
    public final String m66do() {
        return this.f175byte;
    }
}
