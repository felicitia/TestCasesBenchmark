package com.onfido.android.sdk.capture.common;

import android.content.Context;
import com.onfido.android.sdk.capture.common.di.DaggerSdkComponent;
import com.onfido.android.sdk.capture.common.di.SdkComponent;
import com.onfido.android.sdk.capture.common.di.SdkModule;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public final class SdkController {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final Lazy b = LazyKt.lazy(a.a);
    private SdkComponent a;

    public static final class Companion {
        static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/onfido/android/sdk/capture/common/SdkController;"))};

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SdkController getInstance() {
            Lazy access$getInstance$cp = SdkController.b;
            KProperty kProperty = a[0];
            return (SdkController) access$getInstance$cp.getValue();
        }
    }

    static final class a extends Lambda implements Function0<SdkController> {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final SdkController invoke() {
            return b.a.a();
        }
    }

    private static final class b {
        public static final b a = new b();
        private static final SdkController b = new SdkController(null);

        private b() {
        }

        public final SdkController a() {
            return b;
        }
    }

    private SdkController() {
    }

    public /* synthetic */ SdkController(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final SdkController getInstance() {
        return Companion.getInstance();
    }

    public static /* synthetic */ SdkComponent getSdkComponent$default(SdkController sdkController, Context context, int i, Object obj) {
        if ((i & 1) != 0) {
            context = null;
        }
        return sdkController.getSdkComponent(context);
    }

    public final SdkComponent getSdkComponent(Context context) {
        SdkComponent sdkComponent = this.a;
        if (sdkComponent != null && sdkComponent != null) {
            return sdkComponent;
        }
        if (context != null) {
            init(context);
        }
        SdkComponent sdkComponent2 = this.a;
        if (sdkComponent2 == null) {
            Intrinsics.throwNpe();
        }
        return sdkComponent2;
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (this.a == null) {
            this.a = DaggerSdkComponent.builder().sdkModule(new SdkModule(context)).build();
        }
    }
}
