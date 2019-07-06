package com.usebutton.merchant;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: ButtonMerchant */
public final class e {
    @VisibleForTesting
    static c a = new d();
    private static ExecutorService b = Executors.newSingleThreadExecutor();

    /* compiled from: ButtonMerchant */
    public interface a {
        void a(@NonNull String str);
    }

    public static void a(@NonNull Context context, @NonNull String str) {
        a.a(b(context), str);
    }

    public static void a(@NonNull Context context, @NonNull Intent intent) {
        a.a(b(context), intent);
    }

    @Nullable
    public static String a(@NonNull Context context) {
        return a.a(b(context));
    }

    public static void a(@NonNull Context context, @NonNull n nVar) {
        a.a(b(context), nVar, context.getPackageName(), c(context));
    }

    private static f b(Context context) {
        return g.a(b.a(c(context).e()), m.a(context.getApplicationContext()), b);
    }

    private static h c(Context context) {
        return i.a(context.getApplicationContext());
    }
}
