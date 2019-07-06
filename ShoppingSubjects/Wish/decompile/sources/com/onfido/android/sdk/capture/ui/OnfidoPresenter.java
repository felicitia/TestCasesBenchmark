package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.common.preferences.PreferencesManager;
import com.onfido.android.sdk.capture.flow.FlowStepDirection;
import com.onfido.android.sdk.capture.ui.OnfidoView.DefaultImpls;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationOptions;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge;
import com.onfido.android.sdk.capture.ui.options.BaseOptions;
import com.onfido.android.sdk.capture.ui.options.CaptureScreenOptions;
import com.onfido.android.sdk.capture.ui.options.FlowAction;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.ui.options.MessageScreenOptions;
import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.android.sdk.capture.upload.Captures.Document;
import com.onfido.android.sdk.capture.upload.DocumentSide;
import com.onfido.android.sdk.capture.utils.ListExtensionsKt;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.DocSide;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class OnfidoPresenter {
    private OnfidoAPI a;
    /* access modifiers changed from: private */
    public Applicant b;
    private List<FlowStep> c = new ArrayList();
    private int d;
    private DocumentType e;
    private Captures f = new Captures();
    private OnfidoView g;
    private final AnalyticsInteractor h;
    private final PreferencesManager i;
    public OnfidoConfig onfidoConfig;

    public static final class State implements Serializable {
        private final Applicant a;
        private final List<FlowStep> b;
        private final int c;
        private final Captures d;
        private final DocumentType e;

        public State(Applicant applicant, List<? extends FlowStep> list, int i, Captures captures, DocumentType documentType) {
            Intrinsics.checkParameterIsNotNull(list, "flowSteps");
            Intrinsics.checkParameterIsNotNull(captures, "captures");
            this.a = applicant;
            this.b = list;
            this.c = i;
            this.d = captures;
            this.e = documentType;
        }

        public /* synthetic */ State(Applicant applicant, List list, int i, Captures captures, DocumentType documentType, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i2 & 16) != 0) {
                documentType = null;
            }
            this(applicant, list, i, captures, documentType);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof State) {
                State state = (State) obj;
                if (Intrinsics.areEqual(this.a, state.a) && Intrinsics.areEqual(this.b, state.b)) {
                    return (this.c == state.c) && Intrinsics.areEqual(this.d, state.d) && Intrinsics.areEqual(this.e, state.e);
                }
            }
        }

        public final Applicant getApplicant() {
            return this.a;
        }

        public final Captures getCaptures() {
            return this.d;
        }

        public final DocumentType getDocumentType() {
            return this.e;
        }

        public final int getFlowIndex() {
            return this.c;
        }

        public final List<FlowStep> getFlowSteps() {
            return this.b;
        }

        public int hashCode() {
            Applicant applicant = this.a;
            int i = 0;
            int hashCode = (applicant != null ? applicant.hashCode() : 0) * 31;
            List<FlowStep> list = this.b;
            int hashCode2 = (((hashCode + (list != null ? list.hashCode() : 0)) * 31) + this.c) * 31;
            Captures captures = this.d;
            int hashCode3 = (hashCode2 + (captures != null ? captures.hashCode() : 0)) * 31;
            DocumentType documentType = this.e;
            if (documentType != null) {
                i = documentType.hashCode();
            }
            return hashCode3 + i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("State(applicant=");
            sb.append(this.a);
            sb.append(", flowSteps=");
            sb.append(this.b);
            sb.append(", flowIndex=");
            sb.append(this.c);
            sb.append(", captures=");
            sb.append(this.d);
            sb.append(", documentType=");
            sb.append(this.e);
            sb.append(")");
            return sb.toString();
        }
    }

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[FlowAction.values().length];

        static {
            $EnumSwitchMapping$0 = new int[FlowAction.values().length];
            $EnumSwitchMapping$0[FlowAction.CAPTURE_DOCUMENT.ordinal()] = 1;
            $EnumSwitchMapping$0[FlowAction.CAPTURE_FACE.ordinal()] = 2;
            $EnumSwitchMapping$1[FlowAction.WELCOME.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[FlowAction.values().length];
            $EnumSwitchMapping$2[FlowAction.WELCOME.ordinal()] = 1;
            $EnumSwitchMapping$2[FlowAction.CAPTURE_DOCUMENT.ordinal()] = 2;
            $EnumSwitchMapping$2[FlowAction.MESSAGE_FACE_VERIFICATION.ordinal()] = 3;
            $EnumSwitchMapping$2[FlowAction.MESSAGE_LIVENESS_VERIFICATION.ordinal()] = 4;
            $EnumSwitchMapping$2[FlowAction.CAPTURE_FACE.ordinal()] = 5;
            $EnumSwitchMapping$2[FlowAction.CAPTURE_LIVENESS.ordinal()] = 6;
            $EnumSwitchMapping$2[FlowAction.CAPTURE_LIVENESS_CONFIRMATION.ordinal()] = 7;
            $EnumSwitchMapping$2[FlowAction.MESSAGE.ordinal()] = 8;
            $EnumSwitchMapping$2[FlowAction.FINAL.ordinal()] = 9;
        }
    }

    static final class a implements FilenameFilter {
        public static final a a = new a();

        a() {
        }

        public final boolean accept(File file, String str) {
            return StringsKt.startsWith$default(str, "ONFIDO_VID_", false, 2, null);
        }
    }

    public OnfidoPresenter(AnalyticsInteractor analyticsInteractor, PreferencesManager preferencesManager) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        Intrinsics.checkParameterIsNotNull(preferencesManager, "preferencesManager");
        this.h = analyticsInteractor;
        this.i = preferencesManager;
    }

    private final FlowStep a() {
        return (FlowStep) this.c.get(this.d);
    }

    private final void a(int i2) {
        FlowStepDirection flowStepDirection = this.d > i2 ? FlowStepDirection.LEFT_TO_RIGHT : (this.d < i2 || this.d == 0) ? FlowStepDirection.RIGHT_TO_LEFT : FlowStepDirection.NO_DIRECTION;
        this.d = i2;
        a((FlowStep) this.c.get(i2), flowStepDirection);
    }

    private final void a(FlowStep flowStep, FlowStepDirection flowStepDirection) {
        FlowAction action = flowStep.getAction();
        BaseOptions options = flowStep.getOptions();
        switch (action) {
            case WELCOME:
                if (options != null) {
                    WelcomeScreenOptions welcomeScreenOptions = (WelcomeScreenOptions) options;
                    OnfidoView onfidoView = this.g;
                    if (onfidoView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    }
                    onfidoView.showWelcomeScreen(welcomeScreenOptions, flowStepDirection);
                    break;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions");
                }
            case CAPTURE_DOCUMENT:
                if (d()) {
                    CaptureScreenOptions captureScreenOptions = (CaptureScreenOptions) options;
                    if (captureScreenOptions != null) {
                        OnfidoView onfidoView2 = this.g;
                        if (onfidoView2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("view");
                        }
                        Applicant applicant = this.b;
                        if (applicant == null) {
                            Intrinsics.throwNpe();
                        }
                        onfidoView2.showDocumentCapture(applicant, true, captureScreenOptions.getDocumentType(), captureScreenOptions.getCountry());
                        return;
                    }
                    OnfidoView onfidoView3 = this.g;
                    if (onfidoView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    }
                    Applicant applicant2 = this.b;
                    if (applicant2 == null) {
                        Intrinsics.throwNpe();
                    }
                    onfidoView3.showDocumentTypeSelection(applicant2, flowStepDirection);
                    return;
                }
                break;
            case MESSAGE_FACE_VERIFICATION:
                OnfidoView onfidoView4 = this.g;
                if (onfidoView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                }
                onfidoView4.showCaptureFaceMessage(flowStepDirection);
                return;
            case MESSAGE_LIVENESS_VERIFICATION:
                OnfidoView onfidoView5 = this.g;
                if (onfidoView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                }
                onfidoView5.showCaptureLivenessMessage(flowStepDirection);
                return;
            case CAPTURE_FACE:
                if (d()) {
                    OnfidoView onfidoView6 = this.g;
                    if (onfidoView6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    }
                    Applicant applicant3 = this.b;
                    if (applicant3 == null) {
                        Intrinsics.throwNpe();
                    }
                    onfidoView6.showFaceCapture(applicant3);
                    return;
                }
                break;
            case CAPTURE_LIVENESS:
                if (d()) {
                    OnfidoView onfidoView7 = this.g;
                    if (onfidoView7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("view");
                    }
                    Applicant applicant4 = this.b;
                    if (applicant4 == null) {
                        Intrinsics.throwNpe();
                    }
                    onfidoView7.showLivenessCapture(applicant4);
                    return;
                }
                break;
            case CAPTURE_LIVENESS_CONFIRMATION:
                if (options == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConfirmationOptions");
                }
                LivenessConfirmationOptions livenessConfirmationOptions = (LivenessConfirmationOptions) options;
                OnfidoView onfidoView8 = this.g;
                if (onfidoView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                }
                onfidoView8.showCaptureLivenessConfirmation(flowStepDirection, livenessConfirmationOptions.getVideoFilePath(), livenessConfirmationOptions.getLivenessUploadChallenges());
                return;
            case MESSAGE:
                if (options == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.options.MessageScreenOptions");
                }
                MessageScreenOptions messageScreenOptions = (MessageScreenOptions) options;
                OnfidoView onfidoView9 = this.g;
                if (onfidoView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                }
                onfidoView9.showMessageScreen(messageScreenOptions.getTitle(), messageScreenOptions.getMessage(), flowStepDirection);
                return;
            case FINAL:
                OnfidoView onfidoView10 = this.g;
                if (onfidoView10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                }
                onfidoView10.showFinalScreen(flowStepDirection);
                return;
            default:
                return;
        }
    }

    public static final /* synthetic */ OnfidoView access$getView$p(OnfidoPresenter onfidoPresenter) {
        OnfidoView onfidoView = onfidoPresenter.g;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        return onfidoView;
    }

    private final FlowAction b() {
        return a().getAction();
    }

    private final void c() {
        Object obj;
        Object obj2;
        CaptureType captureType;
        OnfidoConfig onfidoConfig2 = this.onfidoConfig;
        if (onfidoConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoConfig");
        }
        List<FlowStep> mutableList = CollectionsKt.toMutableList((Collection) onfidoConfig2.getFlowSteps());
        Iterable<FlowStep> iterable = mutableList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (FlowStep action : iterable) {
            arrayList.add(action.getAction());
        }
        List list = (List) arrayList;
        Iterable iterable2 = list;
        Iterator it = iterable2.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((FlowAction) obj, FlowAction.CAPTURE_LIVENESS)) {
                break;
            }
        }
        boolean z = false;
        if (((FlowAction) obj) != null) {
            mutableList.add(list.indexOf(FlowAction.CAPTURE_LIVENESS), new FlowStep(FlowAction.MESSAGE_LIVENESS_VERIFICATION));
            Iterator it2 = mutableList.iterator();
            int i2 = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i2 = -1;
                    break;
                } else if (Intrinsics.areEqual(((FlowStep) it2.next()).getAction(), FlowAction.CAPTURE_LIVENESS)) {
                    break;
                } else {
                    i2++;
                }
            }
            mutableList.add(i2 + 1, new FlowStep(FlowAction.CAPTURE_LIVENESS_CONFIRMATION));
        }
        Iterator it3 = iterable2.iterator();
        while (true) {
            if (!it3.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it3.next();
            if (Intrinsics.areEqual((FlowAction) obj2, FlowAction.CAPTURE_FACE)) {
                break;
            }
        }
        if (((FlowAction) obj2) != null) {
            mutableList.add(list.indexOf(FlowAction.CAPTURE_FACE), new FlowStep(FlowAction.MESSAGE_FACE_VERIFICATION));
        }
        for (FlowStep flowStep : iterable) {
            if (WhenMappings.$EnumSwitchMapping$1[flowStep.getAction().ordinal()] == 1) {
                Iterable iterable3 = this.c;
                Collection arrayList2 = new ArrayList();
                for (Object next : iterable3) {
                    if (CollectionsKt.listOf((Object[]) new FlowAction[]{FlowAction.CAPTURE_DOCUMENT, FlowAction.CAPTURE_FACE, FlowAction.CAPTURE_LIVENESS}).contains(((FlowStep) next).getAction())) {
                        arrayList2.add(next);
                    }
                }
                Iterable<FlowStep> iterable4 = (List) arrayList2;
                Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
                for (FlowStep action2 : iterable4) {
                    switch (action2.getAction()) {
                        case CAPTURE_DOCUMENT:
                            captureType = CaptureType.DOCUMENT;
                            break;
                        case CAPTURE_FACE:
                            captureType = CaptureType.FACE;
                            break;
                        default:
                            captureType = CaptureType.VIDEO;
                            break;
                    }
                    arrayList3.add(captureType);
                }
                flowStep.setOptions(new WelcomeScreenOptions((List) arrayList3));
            }
        }
        this.c = mutableList;
        Object[] objArr = (Object[]) FlowAction.Companion.getNonDuplicable();
        int length = objArr.length;
        int i3 = 0;
        while (true) {
            if (i3 < length) {
                FlowAction flowAction = (FlowAction) objArr[i3];
                Iterable<FlowStep> iterable5 = this.c;
                Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable5, 10));
                for (FlowStep action3 : iterable5) {
                    arrayList4.add(action3.getAction());
                }
                if (ListExtensionsKt.hasDuplicate((List) arrayList4, flowAction)) {
                    z = true;
                } else {
                    i3++;
                }
            }
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("Custom flow cannot have duplicates of:");
            sb.append(Arrays.toString((Object[]) FlowAction.Companion.getNonDuplicable()));
            throw new IllegalArgumentException(sb.toString());
        }
        Iterable<FlowStep> iterable6 = this.c;
        Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable6, 10));
        for (FlowStep action4 : iterable6) {
            arrayList5.add(action4.getAction());
        }
        List list2 = (List) arrayList5;
        if (list2.contains(FlowAction.CAPTURE_LIVENESS) && list2.contains(FlowAction.CAPTURE_FACE)) {
            throw new IllegalArgumentException("Custom flow cannot contain both video and photo variants of face capture");
        }
    }

    private final boolean d() {
        if (this.b != null) {
            return true;
        }
        throw new IllegalStateException("Applicant should not be null when capturing documents/face");
    }

    private final void e() {
        if (!this.c.isEmpty()) {
            OnfidoView onfidoView = this.g;
            if (onfidoView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            onfidoView.showLoading();
            Applicant applicant = this.b;
            if ((applicant != null ? applicant.getId() : null) == null) {
                g();
            } else {
                resetAction();
            }
        }
    }

    private final List<FlowStep> f() {
        return this.c.subList(0, this.d + 1);
    }

    private final void g() {
        h();
        OnfidoAPI onfidoAPI = this.a;
        if (onfidoAPI == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoApi");
        }
        onfidoAPI.create(this.b, new OnfidoPresenter$issueCreateApplicantRequest$1(this));
    }

    private final void h() {
        if (this.b == null) {
            throw new IllegalArgumentException("OnfidoConfig should contain the Applicant object");
        }
    }

    public final void cleanFiles(File file) {
        boolean z;
        Iterable<FlowStep> iterable = this.c;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (FlowStep action : iterable) {
            arrayList.add(action.getAction());
        }
        Iterable iterable2 = (List) arrayList;
        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
            Iterator it = iterable2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual((FlowAction) it.next(), FlowAction.CAPTURE_LIVENESS)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = false;
        if (z && file != null) {
            File[] listFiles = file.listFiles(a.a);
            if (listFiles != null) {
                for (Object obj : (Object[]) listFiles) {
                    ((File) obj).delete();
                }
            }
        }
    }

    public final void continueFlow() {
        this.h.identifyUser();
        if (!b().isCapture()) {
            resetAction();
        }
    }

    public final OnfidoConfig getOnfidoConfig() {
        OnfidoConfig onfidoConfig2 = this.onfidoConfig;
        if (onfidoConfig2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onfidoConfig");
        }
        return onfidoConfig2;
    }

    public final State getState() {
        State state = new State(this.b, this.c, this.d, this.f, this.e);
        return state;
    }

    public final void initFlow() {
        AnalyticsInteractor analyticsInteractor = this.h;
        analyticsInteractor.identifyUser();
        analyticsInteractor.trackFlowStart();
        c();
        e();
    }

    public final void nextAction() {
        if (this.d >= CollectionsKt.getLastIndex(this.c)) {
            OnfidoView onfidoView = this.g;
            if (onfidoView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            onfidoView.completeFlow();
            return;
        }
        a(this.d + 1);
    }

    public final void onBackPressed() {
        if (Intrinsics.areEqual(b(), FlowAction.CAPTURE_DOCUMENT)) {
            OnfidoView onfidoView = this.g;
            if (onfidoView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            onfidoView.onDocumentCaptureBackPressed(this.e);
        } else if (this.d == 0 || f().contains(FlowStep.FINAL)) {
            OnfidoView onfidoView2 = this.g;
            if (onfidoView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            onfidoView2.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
        } else {
            previousAction();
        }
    }

    public final void onDocumentCaptureCameraBackPressed(DocumentType documentType) {
        if (a().getOptions() != null) {
            previousAction();
            return;
        }
        DocumentType documentType2 = this.e;
        if (documentType2 != null ? documentType2.countrySelectionNeeded() : false) {
            OnfidoView onfidoView = this.g;
            if (onfidoView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            if (documentType == null) {
                Intrinsics.throwNpe();
            }
            onfidoView.showDocumentCountrySelection(documentType, FlowStepDirection.LEFT_TO_RIGHT);
            return;
        }
        OnfidoView onfidoView2 = this.g;
        if (onfidoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        Applicant applicant = this.b;
        if (applicant == null) {
            Intrinsics.throwNpe();
        }
        onfidoView2.showDocumentTypeSelection(applicant, FlowStepDirection.LEFT_TO_RIGHT);
    }

    public final void onDocumentCaptured(DocumentSide documentSide) {
        Intrinsics.checkParameterIsNotNull(documentSide, "documentResult");
        if (Intrinsics.areEqual(documentSide.getSide(), DocSide.FRONT)) {
            Captures captures = this.f;
            Document document = new Document();
            document.setType(documentSide.getType());
            document.setFront(documentSide);
            document.setBack(null);
            captures.setDocument(document);
            return;
        }
        Document document2 = this.f.getDocument();
        if (document2 != null) {
            document2.setBack(documentSide);
        }
    }

    public final void onDocumentTypeSelected(DocumentType documentType) {
        Intrinsics.checkParameterIsNotNull(documentType, "docType");
        this.e = documentType;
        if (documentType.countrySelectionNeeded()) {
            OnfidoView onfidoView = this.g;
            if (onfidoView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            onfidoView.showDocumentCountrySelection(documentType, FlowStepDirection.RIGHT_TO_LEFT);
            return;
        }
        OnfidoView onfidoView2 = this.g;
        if (onfidoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        Applicant applicant = this.b;
        if (applicant == null) {
            Intrinsics.throwNpe();
        }
        DefaultImpls.showDocumentCapture$default(onfidoView2, applicant, true, documentType, null, 8, null);
    }

    public final void previousAction() {
        if (this.d > 0) {
            a(this.d - 1);
            return;
        }
        OnfidoView onfidoView = this.g;
        if (onfidoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        onfidoView.exitFlow(ExitCode.USER_LEFT_ACTIVITY);
    }

    public final void resetAction() {
        a(this.d);
    }

    public final void resetPreferences() {
        this.i.resetPrivacyPolicy();
    }

    public final void setLivenessVideoOptions(String str, LivenessUploadChallenge[] livenessUploadChallengeArr) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(str, "videoPath");
        Intrinsics.checkParameterIsNotNull(livenessUploadChallengeArr, "livenessUploadChallenges");
        Iterator it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((FlowStep) obj).getAction(), FlowAction.CAPTURE_LIVENESS_CONFIRMATION)) {
                break;
            }
        }
        FlowStep flowStep = (FlowStep) obj;
        if (flowStep != null) {
            flowStep.setOptions(new LivenessConfirmationOptions(str, livenessUploadChallengeArr));
        }
    }

    public final void setState(State state) {
        Intrinsics.checkParameterIsNotNull(state, "value");
        this.b = state.getApplicant();
        this.c = CollectionsKt.toMutableList((Collection) state.getFlowSteps());
        this.d = state.getFlowIndex();
        this.f = state.getCaptures();
        this.e = state.getDocumentType();
    }

    public final void setup(OnfidoView onfidoView, OnfidoConfig onfidoConfig2, OnfidoAPI onfidoAPI, State state) {
        Intrinsics.checkParameterIsNotNull(onfidoView, "view");
        Intrinsics.checkParameterIsNotNull(onfidoConfig2, "onfidoConfig");
        Intrinsics.checkParameterIsNotNull(onfidoAPI, "onfidoApi");
        this.g = onfidoView;
        this.onfidoConfig = onfidoConfig2;
        this.a = onfidoAPI;
        if (state != null) {
            setState(state);
        }
    }

    public final void trackFlowCancellation() {
        this.h.trackFlowCancellation();
    }

    public final void trackFlowCompletion() {
        this.h.trackFlowCompletion();
    }

    public final void trackFlowError() {
        this.h.trackFlowError();
    }
}
