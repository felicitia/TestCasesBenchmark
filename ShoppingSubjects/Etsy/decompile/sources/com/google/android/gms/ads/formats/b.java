package com.google.android.gms.ads.formats;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.j;
import com.google.android.gms.internal.ads.bu;

@bu
public final class b {
    private final boolean a;
    private final int b;
    private final boolean c;
    private final int d;
    private final j e;

    public static final class a {
        /* access modifiers changed from: private */
        public boolean a = false;
        /* access modifiers changed from: private */
        public int b = -1;
        /* access modifiers changed from: private */
        public boolean c = false;
        /* access modifiers changed from: private */
        public j d;
        /* access modifiers changed from: private */
        public int e = 1;

        public final a a(int i) {
            this.b = i;
            return this;
        }

        public final a a(j jVar) {
            this.d = jVar;
            return this;
        }

        public final a a(boolean z) {
            this.a = z;
            return this;
        }

        public final b a() {
            return new b(this);
        }

        public final a b(int i) {
            this.e = i;
            return this;
        }

        public final a b(boolean z) {
            this.c = z;
            return this;
        }
    }

    private b(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.e;
        this.e = aVar.d;
    }

    public final boolean a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final boolean c() {
        return this.c;
    }

    public final int d() {
        return this.d;
    }

    @Nullable
    public final j e() {
        return this.e;
    }
}
