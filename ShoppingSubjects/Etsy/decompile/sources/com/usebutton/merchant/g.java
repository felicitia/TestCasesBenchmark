package com.usebutton.merchant;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.ExecutorService;

/* compiled from: ButtonRepositoryImpl */
final class g implements f {
    private static f e;
    private final a a;
    private final l b;
    private final ExecutorService c;
    private String d;

    static f a(a aVar, l lVar, ExecutorService executorService) {
        if (e == null) {
            e = new g(aVar, lVar, executorService);
        }
        return e;
    }

    @VisibleForTesting
    g(a aVar, l lVar, ExecutorService executorService) {
        this.a = aVar;
        this.b = lVar;
        this.c = executorService;
    }

    public void a(String str) {
        this.d = str;
    }

    @Nullable
    public String a() {
        return this.d;
    }

    public void b(String str) {
        this.b.a(str);
    }

    @Nullable
    public String b() {
        return this.b.a();
    }

    public void a(a<o> aVar, h hVar) {
        this.c.submit(new j(aVar, this.a, a(), hVar));
    }

    public boolean c() {
        return this.b.b();
    }

    public void a(boolean z) {
        this.b.a(z);
    }
}
