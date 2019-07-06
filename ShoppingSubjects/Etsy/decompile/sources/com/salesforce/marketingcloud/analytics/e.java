package com.salesforce.marketingcloud.analytics;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.e.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestrictTo({Scope.LIBRARY})
public final class e {
    public static final List<Integer> a = Collections.unmodifiableList(Arrays.asList(new Integer[]{Integer.valueOf(3), Integer.valueOf(14)}));
    private final Date b;
    private final int c;
    private final int d;
    private final List<String> e = new ArrayList();
    private int f;
    private int g;
    private boolean h;
    private String i;
    private String j;

    private e(Date date, int i2, int i3, List<String> list, String str, boolean z) {
        this.b = (Date) f.a(date, "The Date is null.");
        boolean z2 = false;
        f.a(i2 == 0 || i2 == 1, "The Product Type must be one of AnalyticProductType");
        this.c = i2;
        if (i3 > 0) {
            z2 = true;
        }
        f.a(z2, "AnalyticType must be a valid int > 0.");
        this.d = i3;
        if (list != null && !list.isEmpty()) {
            this.e.addAll(list);
        }
        this.j = str;
        this.h = z;
    }

    @RestrictTo({Scope.LIBRARY})
    public static e a(@NonNull Date date, int i2, int i3) {
        return a(date, i2, i3, Collections.emptyList(), null, false);
    }

    @RestrictTo({Scope.LIBRARY})
    public static e a(@NonNull Date date, int i2, int i3, List<String> list, String str, boolean z) {
        e eVar = new e(date, i2, i3, list, str, z);
        return eVar;
    }

    @RestrictTo({Scope.LIBRARY})
    public static e a(@NonNull Date date, int i2, int i3, List<String> list, boolean z) {
        return a(date, i2, i3, list, null, z);
    }

    public int a() {
        return this.f;
    }

    public void a(int i2) {
        this.f = i2;
    }

    public void a(String str) {
        this.i = str;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public Date b() {
        return this.b;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.g;
    }

    public List<String> f() {
        List<String> list;
        synchronized (this.e) {
            list = this.e;
        }
        return list;
    }

    public boolean g() {
        return this.h;
    }

    public String h() {
        return this.i;
    }

    public String i() {
        return this.j;
    }
}
