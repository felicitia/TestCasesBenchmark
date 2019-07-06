package com.onfido.android.sdk.capture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.onfido.android.sdk.capture.Onfido.OnfidoResultListener;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.api.client.data.Applicant;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class OnfidoImpl implements Onfido {
    private final Context a;

    static final class a extends Lambda implements Function0<Unit> {
        final /* synthetic */ OnfidoResultListener a;
        final /* synthetic */ Intent b;
        final /* synthetic */ int c;
        final /* synthetic */ Applicant d;

        a(OnfidoResultListener onfidoResultListener, Intent intent, int i, Applicant applicant) {
            this.a = onfidoResultListener;
            this.b = intent;
            this.c = i;
            this.d = applicant;
            super(0);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x001b, code lost:
            if (r3 != null) goto L_0x0020;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a() {
            /*
                r4 = this;
                com.onfido.android.sdk.capture.Onfido$OnfidoResultListener r0 = r4.a
                com.onfido.android.sdk.capture.errors.OnfidoException r1 = new com.onfido.android.sdk.capture.errors.OnfidoException
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Unexpected result Intent. It might be a result of incorrect integration, make sure you only pass Onfido intent to handle"
                r2.append(r3)
                java.lang.String r3 = "ActivityResult. It might be due to unpredictable crash or error. Please report the problem to android-sdk@onfido.com. Intent: "
                r2.append(r3)
                android.content.Intent r3 = r4.b
                if (r3 == 0) goto L_0x001e
                java.lang.String r3 = r3.toString()
                if (r3 == 0) goto L_0x001e
                goto L_0x0020
            L_0x001e:
                java.lang.String r3 = "null"
            L_0x0020:
                r2.append(r3)
                java.lang.String r3 = " \n resultCode: "
                r2.append(r3)
                int r3 = r4.c
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                r1.<init>(r2)
                com.onfido.api.client.data.Applicant r2 = r4.d
                r0.onError(r1, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.OnfidoImpl.a.a():void");
        }

        public /* synthetic */ Object invoke() {
            a();
            return Unit.INSTANCE;
        }
    }

    public OnfidoImpl(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "appContext");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "appContext.applicationContext");
        this.a = applicationContext;
    }

    private final Applicant a(Intent intent) {
        return OnfidoActivity.Companion.getApplicantFrom(intent);
    }

    public Intent createIntent(OnfidoConfig onfidoConfig) {
        Intrinsics.checkParameterIsNotNull(onfidoConfig, "config");
        return OnfidoActivity.Companion.create(this.a, onfidoConfig);
    }

    public void handleActivityResult(int i, Intent intent, OnfidoResultListener onfidoResultListener) {
        Intrinsics.checkParameterIsNotNull(onfidoResultListener, "callback");
        Applicant a2 = a(intent);
        Function0 aVar = new a(onfidoResultListener, intent, i, a2);
        switch (i) {
            case -2:
                OnfidoException onfidoExceptionFrom = OnfidoActivity.Companion.getOnfidoExceptionFrom(intent);
                if (!(a2 == null || onfidoExceptionFrom == null)) {
                    onfidoResultListener.onError(onfidoExceptionFrom, a2);
                    return;
                }
            case -1:
                Captures uploadedCapturesFrom = OnfidoActivity.Companion.getUploadedCapturesFrom(intent);
                if (!(uploadedCapturesFrom == null || a2 == null)) {
                    onfidoResultListener.userCompleted(a2, uploadedCapturesFrom);
                    return;
                }
            case 0:
                ExitCode errorCodeFrom = OnfidoActivity.Companion.getErrorCodeFrom(intent);
                if (!(errorCodeFrom == null || a2 == null)) {
                    onfidoResultListener.userExited(errorCodeFrom, a2);
                    return;
                }
            default:
                return;
        }
        aVar.invoke();
    }

    public void startActivityForResult(Activity activity, int i, OnfidoConfig onfidoConfig) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(onfidoConfig, "onfidoConfig");
        activity.startActivityForResult(createIntent(onfidoConfig), i);
    }
}
