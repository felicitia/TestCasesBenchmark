package com.crittercism.internal;

import android.support.annotation.NonNull;
import com.crittercism.internal.ap.a;
import com.crittercism.internal.bi;
import java.util.concurrent.ExecutorService;

public abstract class bo<T extends bi> implements Runnable {
    private av a;
    private ay<T> b;
    private ExecutorService c;
    private ap d;
    private a e;

    /* access modifiers changed from: protected */
    public abstract T a(@NonNull av avVar);

    public bo(@NonNull av avVar, @NonNull ay<T> ayVar, @NonNull ExecutorService executorService, @NonNull ap apVar, @NonNull a aVar) {
        this.a = avVar;
        this.b = ayVar;
        this.c = executorService;
        this.d = apVar;
        this.e = aVar;
    }

    public final void a() {
        this.c.submit(this);
    }

    public void run() {
        if (((Boolean) this.d.a(this.e)).booleanValue()) {
            this.b.a(a(this.a));
        }
    }

    public static bo<au> a(@NonNull av avVar, @NonNull ay<au> ayVar, @NonNull ExecutorService executorService, @NonNull ap apVar, @NonNull a aVar) {
        AnonymousClass1 r0 = new bo<au>(avVar, ayVar, executorService, apVar, aVar) {
            /* access modifiers changed from: protected */
            public final /* synthetic */ bi a(@NonNull av avVar) {
                return new au(avVar);
            }
        };
        return r0;
    }

    public static bo<ax> b(@NonNull av avVar, @NonNull ay<ax> ayVar, @NonNull ExecutorService executorService, @NonNull ap apVar, @NonNull a aVar) {
        AnonymousClass2 r0 = new bo<ax>(avVar, ayVar, executorService, apVar, aVar) {
            /* access modifiers changed from: protected */
            public final /* synthetic */ bi a(@NonNull av avVar) {
                return new ax(avVar);
            }
        };
        return r0;
    }

    public static bo<aw> c(@NonNull av avVar, @NonNull ay<aw> ayVar, @NonNull ExecutorService executorService, @NonNull ap apVar, @NonNull a aVar) {
        AnonymousClass3 r0 = new bo<aw>(avVar, ayVar, executorService, apVar, aVar) {
            /* access modifiers changed from: protected */
            public final /* synthetic */ bi a(@NonNull av avVar) {
                return new aw(avVar);
            }
        };
        return r0;
    }
}
