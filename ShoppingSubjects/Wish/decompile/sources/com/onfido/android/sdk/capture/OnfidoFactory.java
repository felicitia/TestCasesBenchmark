package com.onfido.android.sdk.capture;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;

public final class OnfidoFactory {
    public static final Companion Companion = new Companion(null);
    private final Context a;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final OnfidoFactory create(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
            return new OnfidoFactory(applicationContext, null);
        }
    }

    private OnfidoFactory(Context context) {
        this.a = context;
    }

    public /* synthetic */ OnfidoFactory(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final OnfidoFactory create(Context context) {
        return Companion.create(context);
    }

    public final Onfido getClient() {
        return new OnfidoImpl(this.a);
    }
}
