package com.onfido.android.sdk.capture.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.flow.FlowStepDirection;
import com.onfido.android.sdk.capture.ui.ErrorDialogFeature.Listener;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionFragment;
import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.android.sdk.capture.upload.DocumentSide;
import com.onfido.android.sdk.capture.utils.ApiTokenUtil;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.api.client.data.Applicant;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.io.Serializable;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public final class OnfidoActivity extends BaseActivity implements Listener, NextActionListener, OnfidoView {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OnfidoActivity.class), "emptyFragment", "getEmptyFragment()Lcom/onfido/android/sdk/capture/ui/EmptyFragment;"))};
    private Handler b;
    private ErrorDialogFeature c;
    private OnfidoConfig d;
    private final Lazy e = LazyKt.lazy(a.a);
    private HashMap f;
    public OnfidoPresenter presenter;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent create(Context context, OnfidoConfig onfidoConfig) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(onfidoConfig, "config");
            Intent intent = new Intent(context, OnfidoActivity.class);
            intent.putExtra("onfido_config", onfidoConfig);
            return intent;
        }

        public final Applicant getApplicantFrom(Intent intent) {
            return (Applicant) (intent != null ? intent.getSerializableExtra("onfido_applicant") : null);
        }

        public final ExitCode getErrorCodeFrom(Intent intent) {
            return (ExitCode) (intent != null ? intent.getSerializableExtra("onfido_exit_code") : null);
        }

        public final OnfidoConfig getOnfidoConfigFrom(Intent intent) {
            Serializable serializableExtra = intent != null ? intent.getSerializableExtra("onfido_config") : null;
            if (serializableExtra != null) {
                return (OnfidoConfig) serializableExtra;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.OnfidoConfig");
        }

        public final OnfidoException getOnfidoExceptionFrom(Intent intent) {
            return (OnfidoException) (intent != null ? intent.getSerializableExtra("onfido_exception_result") : null);
        }

        public final Captures getUploadedCapturesFrom(Intent intent) {
            return (Captures) (intent != null ? intent.getSerializableExtra("onfido_upload_result") : null);
        }
    }

    static final class a extends Lambda implements Function0<EmptyFragment> {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final EmptyFragment invoke() {
            return new EmptyFragment();
        }
    }

    static final class b implements Runnable {
        final /* synthetic */ OnfidoActivity a;

        b(OnfidoActivity onfidoActivity) {
            this.a = onfidoActivity;
        }

        public final void run() {
            ViewExtensionsKt.animateToAlpha$default((FrameLayout) this.a._$_findCachedViewById(R.id.fl_content), 1.0f, 0, 2, null);
        }
    }

    static final class c extends Lambda implements Function0<Unit> {
        final /* synthetic */ OnfidoActivity a;
        final /* synthetic */ Bundle b;

        c(OnfidoActivity onfidoActivity, Bundle bundle) {
            this.a = onfidoActivity;
            this.b = bundle;
            super(0);
        }

        public final void a() {
            this.a.a(this.b);
        }

        public /* synthetic */ Object invoke() {
            a();
            return Unit.INSTANCE;
        }
    }

    static final class d extends Lambda implements Function0<Unit> {
        final /* synthetic */ OnfidoActivity a;

        d(OnfidoActivity onfidoActivity) {
            this.a = onfidoActivity;
            super(0);
        }

        public final void a() {
            this.a.a((Exception) new SecurityException("You are using an unsupported TLS < 1.2 version"));
        }

        public /* synthetic */ Object invoke() {
            a();
            return Unit.INSTANCE;
        }
    }

    static final class e implements Runnable {
        final /* synthetic */ OnfidoActivity a;
        final /* synthetic */ FlowStepDirection b;
        final /* synthetic */ Fragment c;

        e(OnfidoActivity onfidoActivity, FlowStepDirection flowStepDirection, Fragment fragment) {
            this.a = onfidoActivity;
            this.b = flowStepDirection;
            this.c = fragment;
        }

        public final void run() {
            if (!this.a.isFinishing()) {
                FragmentTransaction beginTransaction = this.a.getSupportFragmentManager().beginTransaction();
                switch (this.b) {
                    case LEFT_TO_RIGHT:
                    case RIGHT_TO_LEFT:
                        Integer slideInAnimation = this.b.getSlideInAnimation();
                        if (slideInAnimation == null) {
                            Intrinsics.throwNpe();
                        }
                        int intValue = slideInAnimation.intValue();
                        Integer slideOutAnimation = this.b.getSlideOutAnimation();
                        if (slideOutAnimation == null) {
                            Intrinsics.throwNpe();
                        }
                        beginTransaction.setCustomAnimations(intValue, slideOutAnimation.intValue());
                        break;
                }
                beginTransaction.replace(R.id.fl_content, this.c, "CURRENTLY_DISPLAYED_FRAGMENT").addToBackStack(null);
                beginTransaction.commitAllowingStateLoss();
            }
        }
    }

    static final class f implements Runnable {
        final /* synthetic */ OnfidoActivity a;

        f(OnfidoActivity onfidoActivity) {
            this.a = onfidoActivity;
        }

        public final void run() {
            ViewExtensionsKt.animateToAlpha$default((FrameLayout) this.a._$_findCachedViewById(R.id.fl_content), 0.0f, 0, 2, null);
        }
    }

    static final class g implements Runnable {
        final /* synthetic */ OnfidoActivity a;

        g(OnfidoActivity onfidoActivity) {
            this.a = onfidoActivity;
        }

        public final void run() {
            ViewExtensionsKt.animateToAlpha$default((FrameLayout) this.a._$_findCachedViewById(R.id.fl_content), 0.0f, 0, 2, null);
        }
    }

    static final class h implements Runnable {
        final /* synthetic */ OnfidoActivity a;

        h(OnfidoActivity onfidoActivity) {
            this.a = onfidoActivity;
        }

        public final void run() {
            ViewExtensionsKt.animateToAlpha$default((FrameLayout) this.a._$_findCachedViewById(R.id.fl_content), 0.0f, 0, 2, null);
        }
    }

    private final Fragment a() {
        return getSupportFragmentManager().findFragmentByTag("CURRENTLY_DISPLAYED_FRAGMENT");
    }

    private final void a(int i, Intent intent) {
        setResult(i, intent);
        finish();
    }

    private final void a(Intent intent) {
        DocumentSide documentResultFrom = CaptureActivity.getDocumentResultFrom(intent);
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Intrinsics.checkExpressionValueIsNotNull(documentResultFrom, "documentSide");
        onfidoPresenter.onDocumentCaptured(documentResultFrom);
    }

    /* access modifiers changed from: private */
    public final void a(Bundle bundle) {
        if (bundle != null) {
            OnfidoPresenter onfidoPresenter = this.presenter;
            if (onfidoPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            onfidoPresenter.continueFlow();
            return;
        }
        OnfidoPresenter onfidoPresenter2 = this.presenter;
        if (onfidoPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter2.initFlow();
    }

    private final void a(Fragment fragment, FlowStepDirection flowStepDirection) {
        Handler handler = this.b;
        if (handler != null) {
            handler.post(new e(this, flowStepDirection, fragment));
        }
    }

    /* access modifiers changed from: private */
    public final void a(Exception exc) {
        Intent e2 = e();
        String localizedMessage = exc.getLocalizedMessage();
        Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "exception.localizedMessage");
        e2.putExtra("onfido_exception_result", new OnfidoException(localizedMessage));
        a(-2, e2);
    }

    private final void a(String str) {
        LoadingFragment createInstance = LoadingFragment.createInstance(str);
        Intrinsics.checkExpressionValueIsNotNull(createInstance, "LoadingFragment.createInstance(message)");
        a((Fragment) createInstance, FlowStepDirection.RIGHT_TO_LEFT);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r4.invoke();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(kotlin.jvm.functions.Function0<kotlin.Unit> r3, kotlin.jvm.functions.Function0<kotlin.Unit> r4) {
        /*
            r2 = this;
            javax.net.ssl.SSLContext r0 = javax.net.ssl.SSLContext.getDefault()     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            javax.net.ssl.SSLParameters r0 = r0.getDefaultSSLParameters()     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            java.lang.String[] r0 = r0.getProtocols()     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            okhttp3.TlsVersion r1 = okhttp3.TlsVersion.TLS_1_2     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            java.lang.String r1 = r1.javaName()     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            boolean r0 = kotlin.collections.ArraysKt.contains(r0, r1)     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            if (r0 != 0) goto L_0x0031
            java.lang.String r0 = "com.google.android.gms.security.ProviderInstaller"
            java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x002d }
            r0 = r2
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ ClassNotFoundException -> 0x002d }
            com.onfido.android.sdk.capture.ui.OnfidoActivity$installSecurityUpdates$1 r1 = new com.onfido.android.sdk.capture.ui.OnfidoActivity$installSecurityUpdates$1     // Catch:{ ClassNotFoundException -> 0x002d }
            r1.<init>(r4, r3)     // Catch:{ ClassNotFoundException -> 0x002d }
            com.google.android.gms.security.ProviderInstaller$ProviderInstallListener r1 = (com.google.android.gms.security.ProviderInstaller.ProviderInstallListener) r1     // Catch:{ ClassNotFoundException -> 0x002d }
            com.google.android.gms.security.ProviderInstaller.installIfNeededAsync(r0, r1)     // Catch:{ ClassNotFoundException -> 0x002d }
            return
        L_0x002d:
            r4.invoke()     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            return
        L_0x0031:
            r3.invoke()     // Catch:{ NoSuchAlgorithmException -> 0x0035 }
            return
        L_0x0035:
            r4.invoke()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.OnfidoActivity.a(kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0):void");
    }

    private final EmptyFragment b() {
        Lazy lazy = this.e;
        KProperty kProperty = a[0];
        return (EmptyFragment) lazy.getValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004e, code lost:
        if (r10 != null) goto L_0x0077;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void b(android.os.Bundle r10) {
        /*
            r9 = this;
            com.onfido.android.sdk.capture.common.SdkController$Companion r0 = com.onfido.android.sdk.capture.common.SdkController.Companion
            com.onfido.android.sdk.capture.common.SdkController r0 = r0.getInstance()
            android.content.Context r1 = r9.getApplicationContext()
            java.lang.String r2 = "applicationContext"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r0.init(r1)
            com.onfido.android.sdk.capture.common.SdkController$Companion r0 = com.onfido.android.sdk.capture.common.SdkController.Companion
            com.onfido.android.sdk.capture.common.SdkController r0 = r0.getInstance()
            r1 = 0
            r2 = 1
            com.onfido.android.sdk.capture.common.di.SdkComponent r0 = com.onfido.android.sdk.capture.common.SdkController.getSdkComponent$default(r0, r1, r2, r1)
            r0.inject(r9)
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r0 = r9.presenter
            if (r0 != 0) goto L_0x002a
            java.lang.String r1 = "presenter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x002a:
            r0.resetPreferences()
            r0 = r9
            android.content.Context r0 = (android.content.Context) r0
            com.onfido.android.sdk.capture.OnfidoConfig r1 = r9.d
            com.onfido.api.client.OnfidoAPI r0 = com.onfido.android.sdk.capture.utils.OnfidoApiUtil.createOnfidoApiClient(r0, r1)
            if (r10 == 0) goto L_0x0051
            java.lang.Class<com.onfido.android.sdk.capture.ui.OnfidoPresenter> r1 = com.onfido.android.sdk.capture.ui.OnfidoPresenter.class
            java.lang.String r1 = r1.getSimpleName()
            java.io.Serializable r10 = r10.getSerializable(r1)
            if (r10 != 0) goto L_0x004c
            kotlin.TypeCastException r10 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.OnfidoPresenter.State"
            r10.<init>(r0)
            throw r10
        L_0x004c:
            com.onfido.android.sdk.capture.ui.OnfidoPresenter$State r10 = (com.onfido.android.sdk.capture.ui.OnfidoPresenter.State) r10
            if (r10 == 0) goto L_0x0051
            goto L_0x0077
        L_0x0051:
            com.onfido.android.sdk.capture.ui.OnfidoPresenter$State r10 = new com.onfido.android.sdk.capture.ui.OnfidoPresenter$State
            com.onfido.android.sdk.capture.OnfidoConfig r1 = r9.d
            if (r1 != 0) goto L_0x005a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x005a:
            com.onfido.api.client.data.Applicant r2 = r1.getApplicant()
            com.onfido.android.sdk.capture.OnfidoConfig r1 = r9.d
            if (r1 != 0) goto L_0x0065
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0065:
            java.util.List r3 = r1.getFlowSteps()
            r4 = 0
            com.onfido.android.sdk.capture.upload.Captures r5 = new com.onfido.android.sdk.capture.upload.Captures
            r5.<init>()
            r6 = 0
            r7 = 16
            r8 = 0
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
        L_0x0077:
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r1 = r9.presenter
            if (r1 != 0) goto L_0x0080
            java.lang.String r2 = "presenter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x0080:
            r2 = r9
            com.onfido.android.sdk.capture.ui.OnfidoView r2 = (com.onfido.android.sdk.capture.ui.OnfidoView) r2
            com.onfido.android.sdk.capture.OnfidoConfig r3 = r9.d
            if (r3 != 0) goto L_0x008a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x008a:
            java.lang.String r4 = "onfidoApi"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            r1.setup(r2, r3, r0, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.OnfidoActivity.b(android.os.Bundle):void");
    }

    private final void b(String str) {
        if (getSupportActionBar() != null) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar == null) {
                Intrinsics.throwNpe();
            }
            supportActionBar.setTitle((CharSequence) str);
        }
    }

    private final boolean b(Intent intent) {
        CountryCode countryCode;
        boolean isDocumentFrontSide = CaptureActivity.getIsDocumentFrontSide(intent);
        DocumentType docTypeFrom = CaptureActivity.getDocTypeFrom(intent);
        if (intent.getSerializableExtra("doc_country") != null) {
            Serializable serializableExtra = intent.getSerializableExtra("doc_country");
            if (serializableExtra == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.utils.CountryCode");
            }
            countryCode = (CountryCode) serializableExtra;
        } else {
            countryCode = null;
        }
        return isDocumentFrontSide && docTypeFrom.backSideCaptureNeeded(countryCode);
    }

    private final void c() {
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private final void d() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fl_content, b(), "CURRENTLY_DISPLAYED_FRAGMENT").addToBackStack(null);
        beginTransaction.commitAllowingStateLoss();
    }

    private final Intent e() {
        Intent intent = new Intent();
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        OnfidoConfig onfidoConfig = onfidoPresenter.getOnfidoConfig();
        String onfidoTokenFrom = ApiTokenUtil.getOnfidoTokenFrom(this, onfidoConfig);
        intent.putExtra("onfido_config", onfidoConfig);
        String str = "onfido_applicant";
        OnfidoPresenter onfidoPresenter2 = this.presenter;
        if (onfidoPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        intent.putExtra(str, onfidoPresenter2.getState().getApplicant());
        intent.putExtra("onfido_token", onfidoTokenFrom);
        String str2 = "onfido_upload_result";
        OnfidoPresenter onfidoPresenter3 = this.presenter;
        if (onfidoPresenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        intent.putExtra(str2, onfidoPresenter3.getState().getCaptures());
        return intent;
    }

    public View _$_findCachedViewById(int i) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        View view = (View) this.f.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.f.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public void completeFlow() {
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.trackFlowCompletion();
        a(-1, e());
    }

    public void exitFlow(ExitCode exitCode) {
        Intrinsics.checkParameterIsNotNull(exitCode, "exitCode");
        Intent e2 = e();
        e2.putExtra("onfido_exit_code", exitCode);
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.trackFlowCancellation();
        a(0, e2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        if (r5 == null) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008f, code lost:
        if (r5 == null) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00cf, code lost:
        if (r5 == null) goto L_0x00d1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r5, int r6, android.content.Intent r7) {
        /*
            r4 = this;
            android.os.Handler r0 = r4.b
            if (r0 == 0) goto L_0x0010
            com.onfido.android.sdk.capture.ui.OnfidoActivity$b r1 = new com.onfido.android.sdk.capture.ui.OnfidoActivity$b
            r1.<init>(r4)
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            r2 = 500(0x1f4, double:2.47E-321)
            r0.postDelayed(r1, r2)
        L_0x0010:
            r0 = 433(0x1b1, float:6.07E-43)
            if (r6 == r0) goto L_0x00f4
            switch(r6) {
                case -2: goto L_0x00da;
                case -1: goto L_0x0024;
                default: goto L_0x0017;
            }
        L_0x0017:
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r5 = r4.presenter
            if (r5 != 0) goto L_0x0020
            java.lang.String r6 = "presenter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x0020:
            r5.onBackPressed()
            return
        L_0x0024:
            r6 = 1
            r0 = 0
            if (r5 == r6) goto L_0x0092
            r6 = 4
            if (r5 == r6) goto L_0x0031
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r5 = r4.presenter
            if (r5 != 0) goto L_0x00d6
            goto L_0x00d1
        L_0x0031:
            if (r7 != 0) goto L_0x0036
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0036:
            java.lang.String r5 = "video_path"
            java.lang.String r5 = r7.getStringExtra(r5)
            java.lang.String r6 = "onfido_liveness_challenges"
            android.os.Parcelable[] r6 = r7.getParcelableArrayExtra(r6)
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            java.util.ArrayList r7 = new java.util.ArrayList
            int r1 = r6.length
            r7.<init>(r1)
            java.util.Collection r7 = (java.util.Collection) r7
            int r1 = r6.length
            r2 = 0
        L_0x004e:
            if (r2 >= r1) goto L_0x0066
            r3 = r6[r2]
            android.os.Parcelable r3 = (android.os.Parcelable) r3
            if (r3 != 0) goto L_0x005e
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge"
            r5.<init>(r6)
            throw r5
        L_0x005e:
            com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge r3 = (com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge) r3
            r7.add(r3)
            int r2 = r2 + 1
            goto L_0x004e
        L_0x0066:
            java.util.List r7 = (java.util.List) r7
            java.util.Collection r7 = (java.util.Collection) r7
            com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge[] r6 = new com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge[r0]
            java.lang.Object[] r6 = r7.toArray(r6)
            if (r6 != 0) goto L_0x007a
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.Array<T>"
            r5.<init>(r6)
            throw r5
        L_0x007a:
            com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge[] r6 = (com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge[]) r6
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r7 = r4.presenter
            if (r7 != 0) goto L_0x0085
            java.lang.String r0 = "presenter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
        L_0x0085:
            java.lang.String r0 = "videoFilePath"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            r7.setLivenessVideoOptions(r5, r6)
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r5 = r4.presenter
            if (r5 != 0) goto L_0x00d6
            goto L_0x00d1
        L_0x0092:
            r4.a(r7)
            com.onfido.android.sdk.capture.DocumentType r5 = com.onfido.android.sdk.capture.ui.camera.CaptureActivity.getDocTypeFrom(r7)
            if (r7 == 0) goto L_0x00cd
            boolean r6 = r4.b(r7)
            if (r6 == 0) goto L_0x00cd
            java.lang.String r6 = "doc_country"
            java.io.Serializable r6 = r7.getSerializableExtra(r6)
            if (r6 != 0) goto L_0x00b1
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type com.onfido.android.sdk.capture.utils.CountryCode"
            r5.<init>(r6)
            throw r5
        L_0x00b1:
            com.onfido.android.sdk.capture.utils.CountryCode r6 = (com.onfido.android.sdk.capture.utils.CountryCode) r6
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r7 = r4.presenter
            if (r7 != 0) goto L_0x00bc
            java.lang.String r1 = "presenter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
        L_0x00bc:
            com.onfido.android.sdk.capture.ui.OnfidoPresenter$State r7 = r7.getState()
            com.onfido.api.client.data.Applicant r7 = r7.getApplicant()
            if (r7 != 0) goto L_0x00c9
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00c9:
            r4.showDocumentCapture(r7, r0, r5, r6)
            return
        L_0x00cd:
            com.onfido.android.sdk.capture.ui.OnfidoPresenter r5 = r4.presenter
            if (r5 != 0) goto L_0x00d6
        L_0x00d1:
            java.lang.String r6 = "presenter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x00d6:
            r5.nextAction()
            return
        L_0x00da:
            if (r7 == 0) goto L_0x010f
            java.lang.String r5 = "onfido_exception_result"
            java.io.Serializable r5 = r7.getSerializableExtra(r5)
            if (r5 != 0) goto L_0x00ec
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.face.UnknownCameraException"
            r5.<init>(r6)
            throw r5
        L_0x00ec:
            com.onfido.android.sdk.capture.ui.camera.face.UnknownCameraException r5 = (com.onfido.android.sdk.capture.ui.camera.face.UnknownCameraException) r5
            java.lang.Exception r5 = (java.lang.Exception) r5
            r4.a(r5)
            return
        L_0x00f4:
            if (r7 == 0) goto L_0x010f
            android.os.Bundle r6 = r7.getExtras()
            java.lang.String r7 = "onfido_intent_extra"
            java.lang.Object r6 = r6.get(r7)
            if (r6 != 0) goto L_0x010a
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type android.content.Intent"
            r5.<init>(r6)
            throw r5
        L_0x010a:
            android.content.Intent r6 = (android.content.Intent) r6
            r4.startActivityForResult(r6, r5)
        L_0x010f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.OnfidoActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onAlternateDocumentSelected() {
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Applicant applicant = onfidoPresenter.getState().getApplicant();
        if (applicant == null) {
            Intrinsics.throwNpe();
        }
        showDocumentTypeSelection(applicant, FlowStepDirection.LEFT_TO_RIGHT);
    }

    public void onBackPressed() {
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.onBackPressed();
    }

    public void onCountrySelected(DocumentType documentType, CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        Intrinsics.checkParameterIsNotNull(countryCode, "countryCode");
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Applicant applicant = onfidoPresenter.getState().getApplicant();
        if (applicant == null) {
            Intrinsics.throwNpe();
        }
        showDocumentCapture(applicant, true, documentType, countryCode);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.onfido_activity_onfido);
        this.c = new ErrorDialogFeature();
        ErrorDialogFeature errorDialogFeature = this.c;
        if (errorDialogFeature == null) {
            Intrinsics.throwNpe();
        }
        errorDialogFeature.attach(this);
        this.b = new Handler();
        this.d = Companion.getOnfidoConfigFrom(getIntent());
        c();
        b(bundle);
        a((Function0<Unit>) new c<Unit>(this, bundle), (Function0<Unit>) new d<Unit>(this));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ErrorDialogFeature errorDialogFeature = this.c;
        if (errorDialogFeature == null) {
            Intrinsics.throwNpe();
        }
        errorDialogFeature.release();
        Handler handler = this.b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.cleanFiles(getFilesDir());
    }

    public void onDocumentCaptureBackPressed(DocumentType documentType) {
        Fragment a2 = a();
        if (a2 instanceof CountrySelectionFragment) {
            OnfidoPresenter onfidoPresenter = this.presenter;
            if (onfidoPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            Applicant applicant = onfidoPresenter.getState().getApplicant();
            if (applicant == null) {
                Intrinsics.throwNpe();
            }
            showDocumentTypeSelection(applicant, FlowStepDirection.LEFT_TO_RIGHT);
        } else if (a2 instanceof DocumentSelectionFragment) {
            OnfidoPresenter onfidoPresenter2 = this.presenter;
            if (onfidoPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            onfidoPresenter2.previousAction();
        } else {
            OnfidoPresenter onfidoPresenter3 = this.presenter;
            if (onfidoPresenter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            onfidoPresenter3.onDocumentCaptureCameraBackPressed(documentType);
        }
    }

    public void onDocumentTypeSelected(DocumentType documentType) {
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.onDocumentTypeSelected(documentType);
    }

    public void onErrorDialogClose() {
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.onBackPressed();
    }

    public void onNextClicked() {
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.nextAction();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.onBackPressed();
        return true;
    }

    public void onPreviousClicked() {
        onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        String simpleName = OnfidoPresenter.class.getSimpleName();
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        bundle.putSerializable(simpleName, onfidoPresenter.getState());
    }

    public void showCaptureFaceMessage(FlowStepDirection flowStepDirection) {
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        String string = getString(R.string.onfido_welcome_view_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…lcome_view_toolbar_title)");
        b(string);
        com.onfido.android.sdk.capture.ui.FaceIntroFragment.Companion companion = FaceIntroFragment.Companion;
        String string2 = getString(R.string.onfido_welcome_view_face_capture_title);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.onfid…_view_face_capture_title)");
        String string3 = getString(R.string.onfido_capture_face_subtitle);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.onfido_capture_face_subtitle)");
        a((Fragment) companion.createInstance(string2, string3, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.onfido_capture_face_step_1), getString(R.string.onfido_capture_face_step_2)})), flowStepDirection);
    }

    public void showCaptureLivenessConfirmation(FlowStepDirection flowStepDirection, String str, LivenessUploadChallenge[] livenessUploadChallengeArr) {
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        Intrinsics.checkParameterIsNotNull(str, "videoFilePath");
        Intrinsics.checkParameterIsNotNull(livenessUploadChallengeArr, "livenessUploadChallenges");
        String string = getString(R.string.onfido_welcome_view_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…lcome_view_toolbar_title)");
        b(string);
        com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoFragment.Companion companion = LivenessInfoFragment.Companion;
        OnfidoConfig onfidoConfig = this.d;
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        a((Fragment) companion.createInstance(false, str, onfidoConfig, onfidoPresenter.getState().getApplicant(), livenessUploadChallengeArr), flowStepDirection);
    }

    public void showCaptureLivenessMessage(FlowStepDirection flowStepDirection) {
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        String string = getString(R.string.onfido_welcome_view_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…lcome_view_toolbar_title)");
        b(string);
        a((Fragment) com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoFragment.Companion.createInstance$default(LivenessInfoFragment.Companion, true, null, null, null, null, 30, null), flowStepDirection);
    }

    public void showDocumentCapture(Applicant applicant, boolean z, DocumentType documentType, CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(applicant, "currentApplicant");
        d();
        Handler handler = this.b;
        if (handler != null) {
            handler.postDelayed(new f(this), 500);
        }
        startActivityForResult(CaptureActivity.createDocumentIntent(this, applicant, this.d, z, documentType, countryCode), 1);
    }

    public void showDocumentCountrySelection(DocumentType documentType, FlowStepDirection flowStepDirection) {
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        String string = getString(R.string.onfido_country_selection_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…_selection_toolbar_title)");
        b(string);
        a((Fragment) CountrySelectionFragment.Companion.createInstance(documentType), flowStepDirection);
    }

    public void showDocumentTypeSelection(Applicant applicant, FlowStepDirection flowStepDirection) {
        Intrinsics.checkParameterIsNotNull(applicant, "currentApplicant");
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        String string = getString(R.string.onfido_welcome_view_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…lcome_view_toolbar_title)");
        b(string);
        a((Fragment) DocumentSelectionFragment.Companion.createInstance(), flowStepDirection);
    }

    public void showError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.trackFlowError();
        ErrorDialogFeature errorDialogFeature = this.c;
        if (errorDialogFeature == null) {
            Intrinsics.throwNpe();
        }
        errorDialogFeature.show(str, this);
    }

    public void showFaceCapture(Applicant applicant) {
        Intrinsics.checkParameterIsNotNull(applicant, "currentApplicant");
        d();
        Handler handler = this.b;
        if (handler != null) {
            handler.postDelayed(new g(this), 500);
        }
        startActivityForResult(CaptureActivity.createFaceIntent(this, applicant, this.d), 2);
    }

    public void showFinalScreen(FlowStepDirection flowStepDirection) {
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        String string = getString(R.string.onfido_welcome_view_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…lcome_view_toolbar_title)");
        b(string);
        com.onfido.android.sdk.capture.ui.FinalScreenFragment.Companion companion = FinalScreenFragment.Companion;
        String string2 = getString(R.string.onfido_thank_you);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.onfido_thank_you)");
        String string3 = getString(R.string.onfido_final_screen_subtitle);
        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.onfido_final_screen_subtitle)");
        a((Fragment) companion.createInstance(string2, string3), flowStepDirection);
    }

    public void showLivenessCapture(Applicant applicant) {
        Intrinsics.checkParameterIsNotNull(applicant, "currentApplicant");
        d();
        Handler handler = this.b;
        if (handler != null) {
            handler.postDelayed(new h(this), 500);
        }
        startActivityForResult(CaptureActivity.createLivenessIntent(this, applicant, this.d), 4);
    }

    public void showLoading() {
        b("");
        String string = getString(R.string.onfido_message_loading);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfido_message_loading)");
        a(string);
    }

    public void showMessageScreen(String str, String str2, FlowStepDirection flowStepDirection) {
        Intrinsics.checkParameterIsNotNull(str, StrongAuth.AUTH_TITLE);
        Intrinsics.checkParameterIsNotNull(str2, "message");
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        String string = getString(R.string.onfido_welcome_view_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…lcome_view_toolbar_title)");
        b(string);
        a((Fragment) MessageFragment.Companion.createInstance(str, str2), flowStepDirection);
    }

    public void showNetworkError() {
        OnfidoPresenter onfidoPresenter = this.presenter;
        if (onfidoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        onfidoPresenter.trackFlowError();
        ErrorDialogFeature errorDialogFeature = this.c;
        if (errorDialogFeature == null) {
            Intrinsics.throwNpe();
        }
        errorDialogFeature.show(getString(R.string.onfido_error_connection_message), this);
    }

    public void showWelcomeScreen(WelcomeScreenOptions welcomeScreenOptions, FlowStepDirection flowStepDirection) {
        Intrinsics.checkParameterIsNotNull(welcomeScreenOptions, "options");
        Intrinsics.checkParameterIsNotNull(flowStepDirection, "flowStepDirection");
        String string = getString(R.string.onfido_welcome_view_toolbar_title);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.onfid…lcome_view_toolbar_title)");
        b(string);
        a((Fragment) WelcomeFragment.Companion.createInstance(this, welcomeScreenOptions), flowStepDirection);
    }
}
