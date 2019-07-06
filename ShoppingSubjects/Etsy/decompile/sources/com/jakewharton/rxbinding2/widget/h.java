package com.jakewharton.rxbinding2.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.jakewharton.rxbinding2.internal.a;
import com.jakewharton.rxbinding2.internal.b;
import io.reactivex.functions.i;
import io.reactivex.q;

/* compiled from: RxTextView */
public final class h {
    @CheckResult
    @NonNull
    public static q<q> a(@NonNull TextView textView) {
        b.a(textView, "view == null");
        return a(textView, a.b);
    }

    @CheckResult
    @NonNull
    public static q<q> a(@NonNull TextView textView, @NonNull i<? super q> iVar) {
        b.a(textView, "view == null");
        b.a(iVar, "handled == null");
        return new r(textView, iVar);
    }

    @CheckResult
    @NonNull
    public static com.jakewharton.rxbinding2.a<CharSequence> b(@NonNull TextView textView) {
        b.a(textView, "view == null");
        return new u(textView);
    }

    @CheckResult
    @NonNull
    public static com.jakewharton.rxbinding2.a<s> c(@NonNull TextView textView) {
        b.a(textView, "view == null");
        return new t(textView);
    }

    @CheckResult
    @NonNull
    public static com.jakewharton.rxbinding2.a<n> d(@NonNull TextView textView) {
        b.a(textView, "view == null");
        return new o(textView);
    }
}
