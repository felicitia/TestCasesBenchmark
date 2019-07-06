package com.onfido.android.sdk.capture.ui.camera;

import android.app.Activity;
import android.util.Log;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.common.preferences.PreferencesManager;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInteractor;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge;
import com.onfido.android.sdk.capture.upload.UploadErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.ReactiveExtensionsKt;
import com.onfido.android.sdk.capture.validation.BackendValidationsManager;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidation;
import com.onfido.android.sdk.capture.validation.PostCaptureValidationResults;
import com.onfido.api.client.ValidationLevel;
import com.onfido.api.client.ValidationType;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocumentUpload;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public final class CapturePresenter {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(CapturePresenter.class), "compositeSubscription", "getCompositeSubscription()Lio/reactivex/disposables/CompositeDisposable;"))};
    private final Lazy b;
    /* access modifiers changed from: private */
    public final String c;
    /* access modifiers changed from: private */
    public final EdgeDetectionResults d;
    /* access modifiers changed from: private */
    public Disposable e;
    private View f;
    /* access modifiers changed from: private */
    public final NativeDetector g;
    private final BarcodeDetector h;
    private final AnalyticsInteractor i;
    /* access modifiers changed from: private */
    public final LivenessInteractor j;
    private final PreferencesManager k;
    private final BackendValidationsManager l;
    private final RuntimePermissionsManager m;
    /* access modifiers changed from: private */
    public final Scheduler n;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class State implements Serializable {
        private final int a;

        public State(int i) {
            this.a = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof State) {
                if (this.a == ((State) obj).a) {
                    return true;
                }
            }
            return false;
        }

        public final int getNumValidationErrors() {
            return this.a;
        }

        public int hashCode() {
            return this.a;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("State(numValidationErrors=");
            sb.append(this.a);
            sb.append(")");
            return sb.toString();
        }
    }

    public interface View {
        void clearEdges();

        void onChallengesCompleted();

        void onDocumentUploadWithGlareWarning(DocumentUpload documentUpload);

        void onImageProcessingFinished();

        void onImageProcessingResult(ImageProcessingResults imageProcessingResults);

        void onIntroductionDelayFinished();

        void onLastChallenge();

        void onManualFallbackDelayFinished();

        void onNextChallenge(int i, LivenessChallenge livenessChallenge);

        void onPermissionsDenied();

        void onPermissionsGranted();

        void onPostCaptureValidationsFinished(PostCaptureValidationResults postCaptureValidationResults);

        void onStorageThresholdReached();

        void onValidDocumentUpload(DocumentUpload documentUpload);
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CaptureType.values().length];

        static {
            $EnumSwitchMapping$0[CaptureType.VIDEO.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[CaptureType.values().length];
            $EnumSwitchMapping$1[CaptureType.DOCUMENT.ordinal()] = 1;
            $EnumSwitchMapping$1[CaptureType.VIDEO.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[PostCaptureDocumentValidation.values().length];
            $EnumSwitchMapping$2[PostCaptureDocumentValidation.BLUR.ordinal()] = 1;
            $EnumSwitchMapping$2[PostCaptureDocumentValidation.BARCODE.ordinal()] = 2;
        }
    }

    static final class a<T, R> implements Function<Object[], R> {
        public static final a a = new a();

        a() {
        }

        /* renamed from: a */
        public final PostCaptureValidationResults apply(Object[] objArr) {
            Boolean bool = objArr[0];
            if (bool != null) {
                return new PostCaptureValidationResults(bool.booleanValue(), (Boolean) ArraysKt.getOrNull(objArr, 1));
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
        }
    }

    static final class b<T> implements Consumer<PostCaptureValidationResults> {
        final /* synthetic */ CapturePresenter a;

        b(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(PostCaptureValidationResults postCaptureValidationResults) {
            View access$getView$p = CapturePresenter.access$getView$p(this.a);
            Intrinsics.checkExpressionValueIsNotNull(postCaptureValidationResults, "it");
            access$getView$p.onPostCaptureValidationsFinished(postCaptureValidationResults);
        }
    }

    static final class c<T> implements Consumer<Throwable> {
        final /* synthetic */ CapturePresenter a;

        c(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Throwable th) {
            String access$getTAG$p = this.a.c;
            StringBuilder sb = new StringBuilder();
            sb.append("Error on post processing validations: ");
            sb.append(th.getMessage());
            Log.e(access$getTAG$p, sb.toString());
        }
    }

    static final class d extends Lambda implements Function0<CompositeDisposable> {
        public static final d a = new d();

        d() {
            super(0);
        }

        /* renamed from: a */
        public final CompositeDisposable invoke() {
            return new CompositeDisposable();
        }
    }

    static final class e<T, R> implements Function<T, R> {
        final /* synthetic */ CapturePresenter a;
        final /* synthetic */ DocumentType b;

        e(CapturePresenter capturePresenter, DocumentType documentType) {
            this.a = capturePresenter;
            this.b = documentType;
        }

        /* renamed from: a */
        public final ImageProcessingResults apply(PreviewFrameData previewFrameData) {
            boolean detectGlare = this.a.g.detectGlare(previewFrameData.getData(), previewFrameData.getFrameWidth(), previewFrameData.getFrameHeight(), previewFrameData.getInnerRect().getWidth(), previewFrameData.getInnerRect().getHeight(), previewFrameData.getInnerRect().getLeft(), previewFrameData.getInnerRect().getTop());
            EdgeDetectionResults detectEdges = Intrinsics.areEqual(this.b, DocumentType.PASSPORT) ? this.a.g.detectEdges(previewFrameData.getData(), previewFrameData.getFrameWidth(), previewFrameData.getFrameHeight(), previewFrameData.getOuterRect().getWidth(), previewFrameData.getOuterRect().getHeight(), previewFrameData.getOuterRect().getLeft(), previewFrameData.getOuterRect().getTop()) : this.a.d;
            boolean detectBlur = this.a.g.detectBlur(previewFrameData.getData(), previewFrameData.getFrameWidth(), previewFrameData.getFrameHeight(), previewFrameData.getOuterRect().getWidth(), previewFrameData.getOuterRect().getHeight(), previewFrameData.getOuterRect().getLeft(), previewFrameData.getOuterRect().getTop());
            if (detectEdges.getHasAny() && this.a.e == null) {
                ReactiveExtensionsKt.subscribeAndObserve$default(Observable.timer(10000, TimeUnit.MILLISECONDS, this.a.n), null, null, 3, null).subscribe(new Consumer<Long>(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    /* renamed from: a */
                    public final void accept(Long l) {
                        CapturePresenter.access$getView$p(this.a.a).onManualFallbackDelayFinished();
                    }
                }, new Consumer<Throwable>(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    /* renamed from: a */
                    public final void accept(Throwable th) {
                        String access$getTAG$p = this.a.a.c;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Error on autocapture fallback subscription: ");
                        sb.append(th.getMessage());
                        Log.e(access$getTAG$p, sb.toString());
                    }
                }, AnonymousClass3.a, new Consumer<Disposable>(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    /* renamed from: a */
                    public final void accept(Disposable disposable) {
                        this.a.a.e = disposable;
                    }
                });
            }
            Intrinsics.checkExpressionValueIsNotNull(detectEdges, "edgeDetectionResults");
            return new ImageProcessingResults(detectGlare, detectEdges, detectBlur);
        }
    }

    static final class f<T, R> implements Function<T, R> {
        final /* synthetic */ CapturePresenter a;

        f(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        public final long a(Long l) {
            return this.a.j.getAvailableStorageSpace();
        }

        public /* synthetic */ Object apply(Object obj) {
            return Long.valueOf(a((Long) obj));
        }
    }

    static final class g<T> implements Predicate<Long> {
        public static final g a = new g();

        g() {
        }

        /* renamed from: a */
        public final boolean test(Long l) {
            return Intrinsics.compare(l.longValue(), (long) 500000) < 0;
        }
    }

    static final class h<T> implements Consumer<Long> {
        final /* synthetic */ CapturePresenter a;

        h(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Long l) {
            CapturePresenter.access$getView$p(this.a).onStorageThresholdReached();
        }
    }

    static final class i<T> implements Consumer<Throwable> {
        final /* synthetic */ CapturePresenter a;

        i(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Throwable th) {
            String access$getTAG$p = this.a.c;
            StringBuilder sb = new StringBuilder();
            sb.append("Error on available storage calculation: ");
            sb.append(th.getMessage());
            Log.e(access$getTAG$p, sb.toString());
        }
    }

    static final class j implements Action {
        final /* synthetic */ CapturePresenter a;

        j(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        public final void run() {
            CapturePresenter.access$getView$p(this.a).onChallengesCompleted();
        }
    }

    static final class k<T> implements Consumer<Pair<? extends Integer, ? extends LivenessChallenge>> {
        final /* synthetic */ CapturePresenter a;

        k(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Pair<Integer, ? extends LivenessChallenge> pair) {
            CapturePresenter.access$getView$p(this.a).onNextChallenge(((Number) pair.getFirst()).intValue(), (LivenessChallenge) pair.getSecond());
        }
    }

    static final class l<T> implements Consumer<Throwable> {
        final /* synthetic */ CapturePresenter a;

        l(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Throwable th) {
            String access$getTAG$p = this.a.c;
            StringBuilder sb = new StringBuilder();
            sb.append("Error on liveness challenge provider: ");
            sb.append(th.getMessage());
            Log.e(access$getTAG$p, sb.toString());
        }
    }

    static final class m<T> implements Consumer<Boolean> {
        final /* synthetic */ CapturePresenter a;

        m(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Boolean bool) {
            CapturePresenter.access$getView$p(this.a).onLastChallenge();
        }
    }

    static final class n<T> implements Consumer<Throwable> {
        final /* synthetic */ CapturePresenter a;

        n(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Throwable th) {
            String access$getTAG$p = this.a.c;
            StringBuilder sb = new StringBuilder();
            sb.append("Error on liveness challenge provider: ");
            sb.append(th.getMessage());
            Log.e(access$getTAG$p, sb.toString());
        }
    }

    static final class o implements Action {
        final /* synthetic */ CapturePresenter a;

        o(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        public final void run() {
            CapturePresenter.access$getView$p(this.a).onImageProcessingFinished();
        }
    }

    static final class p<T> implements Consumer<ImageProcessingResults> {
        final /* synthetic */ CapturePresenter a;

        p(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(ImageProcessingResults imageProcessingResults) {
            View access$getView$p = CapturePresenter.access$getView$p(this.a);
            Intrinsics.checkExpressionValueIsNotNull(imageProcessingResults, "it");
            access$getView$p.onImageProcessingResult(imageProcessingResults);
        }
    }

    static final class q<T> implements Consumer<Throwable> {
        final /* synthetic */ CapturePresenter a;

        q(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Throwable th) {
            String access$getTAG$p = this.a.c;
            StringBuilder sb = new StringBuilder();
            sb.append("Error on glare detector: ");
            sb.append(th.getMessage());
            Log.e(access$getTAG$p, sb.toString());
        }
    }

    static final class r<T1, T2, R> implements BiFunction<Integer, LivenessChallenge, Pair<? extends Integer, ? extends LivenessChallenge>> {
        public static final r a = new r();

        r() {
        }

        public final Pair<Integer, LivenessChallenge> a(int i, LivenessChallenge livenessChallenge) {
            Intrinsics.checkParameterIsNotNull(livenessChallenge, "challenge");
            return TuplesKt.to(Integer.valueOf(i), livenessChallenge);
        }

        public /* synthetic */ Object apply(Object obj, Object obj2) {
            return a(((Number) obj).intValue(), (LivenessChallenge) obj2);
        }
    }

    static final class s<T> implements Consumer<Long> {
        final /* synthetic */ CapturePresenter a;

        s(CapturePresenter capturePresenter) {
            this.a = capturePresenter;
        }

        /* renamed from: a */
        public final void accept(Long l) {
            CapturePresenter.access$getView$p(this.a).onIntroductionDelayFinished();
        }
    }

    static final class t<T, R> implements Function<T, ObservableSource<? extends R>> {
        final /* synthetic */ CapturePresenter a;
        final /* synthetic */ DocumentType b;

        t(CapturePresenter capturePresenter, DocumentType documentType) {
            this.a = capturePresenter;
            this.b = documentType;
        }

        /* renamed from: a */
        public final Observable<ImageProcessingResults> apply(Long l) {
            return this.a.a(this.b);
        }
    }

    static final class u<T, R> implements Function<T, ObservableSource<? extends R>> {
        final /* synthetic */ CapturePresenter a;
        final /* synthetic */ DocumentType b;

        u(CapturePresenter capturePresenter, DocumentType documentType) {
            this.a = capturePresenter;
            this.b = documentType;
        }

        /* renamed from: a */
        public final Observable<ImageProcessingResults> apply(Long l) {
            return this.a.a(this.b);
        }
    }

    public CapturePresenter(NativeDetector nativeDetector, BarcodeDetector barcodeDetector, AnalyticsInteractor analyticsInteractor, LivenessInteractor livenessInteractor, PreferencesManager preferencesManager, BackendValidationsManager backendValidationsManager, RuntimePermissionsManager runtimePermissionsManager, Scheduler scheduler) {
        Intrinsics.checkParameterIsNotNull(nativeDetector, "nativeDetector");
        Intrinsics.checkParameterIsNotNull(barcodeDetector, "barcodeDetector");
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        Intrinsics.checkParameterIsNotNull(livenessInteractor, "livenessInteractor");
        Intrinsics.checkParameterIsNotNull(preferencesManager, "preferencesManager");
        Intrinsics.checkParameterIsNotNull(backendValidationsManager, "backendValidationsManager");
        Intrinsics.checkParameterIsNotNull(runtimePermissionsManager, "runtimePermissionsManager");
        Intrinsics.checkParameterIsNotNull(scheduler, "scheduler");
        this.g = nativeDetector;
        this.h = barcodeDetector;
        this.i = analyticsInteractor;
        this.j = livenessInteractor;
        this.k = preferencesManager;
        this.l = backendValidationsManager;
        this.m = runtimePermissionsManager;
        this.n = scheduler;
        this.b = LazyKt.lazy(d.a);
        this.c = getClass().getName();
        this.d = new EdgeDetectionResults(false, false, false, false);
    }

    public /* synthetic */ CapturePresenter(NativeDetector nativeDetector, BarcodeDetector barcodeDetector, AnalyticsInteractor analyticsInteractor, LivenessInteractor livenessInteractor, PreferencesManager preferencesManager, BackendValidationsManager backendValidationsManager, RuntimePermissionsManager runtimePermissionsManager, Scheduler scheduler, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        Scheduler scheduler2;
        if ((i2 & 128) != 0) {
            Scheduler io2 = Schedulers.io();
            Intrinsics.checkExpressionValueIsNotNull(io2, "Schedulers.io()");
            scheduler2 = io2;
        } else {
            scheduler2 = scheduler;
        }
        this(nativeDetector, barcodeDetector, analyticsInteractor, livenessInteractor, preferencesManager, backendValidationsManager, runtimePermissionsManager, scheduler2);
    }

    /* access modifiers changed from: private */
    public final Observable<ImageProcessingResults> a(DocumentType documentType) {
        Observable<ImageProcessingResults> map = this.g.getFrameData().sample(350, TimeUnit.MILLISECONDS, this.n).map(new e(this, documentType));
        Intrinsics.checkExpressionValueIsNotNull(map, "nativeDetector.frameData…asBlur)\n                }");
        return map;
    }

    private final CompositeDisposable a() {
        Lazy lazy = this.b;
        KProperty kProperty = a[0];
        return (CompositeDisposable) lazy.getValue();
    }

    public static final /* synthetic */ View access$getView$p(CapturePresenter capturePresenter) {
        View view = capturePresenter.f;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        return view;
    }

    public final void applyPostCaptureValidations(CaptureFrameData captureFrameData, DocumentType documentType, DocSide docSide, CountryCode countryCode) {
        Observable observable;
        Intrinsics.checkParameterIsNotNull(captureFrameData, "frameData");
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        Intrinsics.checkParameterIsNotNull(docSide, "docSide");
        Iterable<PostCaptureDocumentValidation> postCaptureValidationsNeeded = documentType.postCaptureValidationsNeeded(docSide, countryCode);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(postCaptureValidationsNeeded, 10));
        for (PostCaptureDocumentValidation postCaptureDocumentValidation : postCaptureValidationsNeeded) {
            switch (postCaptureDocumentValidation) {
                case BLUR:
                    observable = Observable.just(Boolean.valueOf(this.g.detectBlurCapture(captureFrameData.getData(), captureFrameData.getFrameWidth(), captureFrameData.getFrameHeight(), captureFrameData.getOuterRect().getWidth(), captureFrameData.getOuterRect().getHeight(), captureFrameData.getOuterRect().getLeft(), captureFrameData.getOuterRect().getTop(), captureFrameData.getRotation())));
                    if (observable != null) {
                        break;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type io.reactivex.Observable<kotlin.Boolean>");
                    }
                case BARCODE:
                    observable = this.h.detect(captureFrameData);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            arrayList.add(observable);
        }
        a().add(ReactiveExtensionsKt.subscribeAndObserve$default(Observable.combineLatest((List) arrayList, a.a), null, null, 3, null).subscribe(new b(this), new c(this)));
    }

    public final void attachView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.f = view;
    }

    public final void checkDocumentUploadResult(DocumentUpload documentUpload) {
        Intrinsics.checkParameterIsNotNull(documentUpload, "documentUpload");
        if (this.l.hasGlareWarning(documentUpload)) {
            View view = this.f;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            view.onDocumentUploadWithGlareWarning(documentUpload);
            return;
        }
        View view2 = this.f;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view2.onValidDocumentUpload(documentUpload);
    }

    public final void checkPermissions(Activity activity, CaptureType captureType) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        boolean z = false;
        Object[] objArr = (Object[]) (WhenMappings.$EnumSwitchMapping$0[captureType.ordinal()] != 1 ? new String[]{"android.permission.CAMERA"} : new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"});
        Collection arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (!this.m.hasPermission((String) obj)) {
                arrayList.add(obj);
            }
        }
        Object[] array = ((List) arrayList).toArray(new String[0]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        String[] strArr = (String[]) array;
        if (((Object[]) strArr).length == 0) {
            z = true;
        }
        if (!z) {
            this.m.requestPermissions(activity, strArr, 432);
            return;
        }
        View view = this.f;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view.onPermissionsGranted();
    }

    public final void clearEdges() {
        this.g.clearEdges();
        View view = this.f;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view.clearEdges();
    }

    public final void deleteFile(String str) {
        Intrinsics.checkParameterIsNotNull(str, "filePath");
        new File(str).delete();
    }

    public final Map<ValidationType, ValidationLevel> getRequiredDocumentValidations(DocSide docSide) {
        Intrinsics.checkParameterIsNotNull(docSide, "documentSide");
        return this.l.getRequiredDocumentValidations(docSide);
    }

    public final State getState() {
        return new State(this.l.getRejectedUploads());
    }

    public final LivenessUploadChallenge[] getUploadChallengesList() {
        Collection uploadChallengesList = this.j.getUploadChallengesList();
        if (uploadChallengesList == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Object[] array = uploadChallengesList.toArray(new LivenessUploadChallenge[0]);
        if (array != null) {
            return (LivenessUploadChallenge[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final void handlePermissionsResult(int i2, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i2 == 432) {
            if (this.m.werePermissionsGranted(iArr)) {
                View view = this.f;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                }
                view.onPermissionsGranted();
                return;
            }
            View view2 = this.f;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            view2.onPermissionsDenied();
        }
    }

    public final boolean hasNativeLibrary() {
        return this.g.hasNativeLibrary();
    }

    public final void issueNextChallenge() {
        this.j.issueNextChallenge();
    }

    public final void onNextFrame(PreviewFrameData previewFrameData) {
        Intrinsics.checkParameterIsNotNull(previewFrameData, "frameData");
        this.g.getFrameData().onNext(previewFrameData);
    }

    public final void onPolicyContinuePressed() {
        this.k.hasAcceptedPrivacyPolicy();
    }

    public final void onRecordingStarted() {
        if (this.j.getAvailableStorageSpace() < 5500000) {
            a().add(ReactiveExtensionsKt.subscribeAndObserve$default(Observable.interval(1000, TimeUnit.MILLISECONDS, this.n).map(new f(this)).filter(g.a).take(1), null, null, 3, null).subscribe(new h(this), new i(this)));
        }
    }

    public final void onUploadValidationError() {
        this.l.onValidationError();
    }

    public final void setState(State state) {
        Intrinsics.checkParameterIsNotNull(state, "value");
        this.l.setRejectedUploads(state.getNumValidationErrors());
    }

    public final boolean shouldAutocapture(int i2) {
        return this.g.shouldAutocapture(i2);
    }

    public final boolean shouldPerformFaceValidation() {
        return this.l.shouldPerformFaceValidation();
    }

    public final boolean shouldShowPrivacyPolicy(CaptureType captureType, DocSide docSide) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        return Intrinsics.areEqual(captureType, CaptureType.DOCUMENT) && Intrinsics.areEqual(docSide, DocSide.FRONT) && !this.k.getHasAcceptedPrivacyPolicy();
    }

    public final void start(CaptureType captureType, DocumentType documentType, boolean z) {
        Observable observable;
        Observable timer;
        Function uVar;
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        switch (captureType) {
            case DOCUMENT:
                if (!this.g.hasNativeLibrary()) {
                    observable = Observable.empty();
                } else if (Intrinsics.areEqual(documentType, DocumentType.PASSPORT)) {
                    if (z) {
                        timer = Observable.timer(4000, TimeUnit.MILLISECONDS, this.n).observeOn(AndroidSchedulers.mainThread()).doOnNext(new s(this)).observeOn(Schedulers.io());
                        uVar = new t(this, documentType);
                    } else {
                        timer = Observable.timer(2000, TimeUnit.MILLISECONDS, this.n);
                        uVar = new u(this, documentType);
                    }
                    observable = timer.flatMap(uVar);
                } else {
                    observable = a(documentType);
                }
                a().add(ReactiveExtensionsKt.subscribeAndObserve$default(observable.doFinally(new o(this)), this.n, null, 2, null).subscribe(new p(this), new q(this)));
                return;
            case VIDEO:
                CompositeDisposable a2 = a();
                a2.add(ReactiveExtensionsKt.subscribeAndObserve$default(Observable.zip(Observable.range(1, this.j.getChallengesCount() + 1), this.j.getChallenges(), r.a).doOnComplete(new j(this)), null, null, 3, null).subscribe(new k(this), new l(this)));
                a2.add(ReactiveExtensionsKt.subscribeAndObserve$default(this.j.getChallengesRemainder(), null, null, 3, null).subscribe(new m(this), new n(this)));
                return;
            default:
                return;
        }
    }

    public final void stop() {
        a().clear();
        this.g.clearEdges();
        View view = this.f;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view.clearEdges();
        View view2 = this.f;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        view2.onIntroductionDelayFinished();
        Disposable disposable = this.e;
        if (disposable != null) {
            disposable.dispose();
        }
        this.e = null;
    }

    public final void trackBarcodeNotReadable(DocumentType documentType, CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(documentType, "docType");
        this.i.trackBarcodeNotReadable(documentType, countryCode);
    }

    public final void trackCameraPermission(boolean z, Boolean bool) {
        this.i.trackCameraPermission(z, bool);
    }

    public final void trackCaptureError(CaptureType captureType, boolean z) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        trackCaptureError(captureType, z, null);
    }

    public final void trackCaptureError(CaptureType captureType, boolean z, UploadErrorType uploadErrorType) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        if (uploadErrorType != null) {
            if (Intrinsics.areEqual(captureType, CaptureType.DOCUMENT)) {
                this.i.trackDocumentCaptureError(z, uploadErrorType);
                return;
            }
            this.i.trackFaceCaptureError(z, captureType, uploadErrorType);
        }
    }

    public final void trackChallenge(int i2, LivenessChallenge livenessChallenge) {
        Intrinsics.checkParameterIsNotNull(livenessChallenge, "challenge");
        AnalyticsInteractor analyticsInteractor = this.i;
        String name = livenessChallenge.getType().name();
        if (name == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = name.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        analyticsInteractor.trackChallenge(i2, lowerCase);
    }

    public final void trackDocumentCapture(boolean z, boolean z2, boolean z3, DocumentType documentType, CountryCode countryCode, DocSide docSide) {
        Intrinsics.checkParameterIsNotNull(documentType, "docType");
        this.i.trackDocumentCapture(z, z2, z3, documentType, countryCode, docSide);
    }

    public final void trackFaceCapture(boolean z, boolean z2, boolean z3, CaptureType captureType) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        this.i.trackFaceCapture(z, z2, z3, captureType);
    }

    public final void trackUploadStarted(CaptureType captureType) {
        Intrinsics.checkParameterIsNotNull(captureType, "captureType");
        this.i.trackUploadingScreen(captureType);
    }
}
