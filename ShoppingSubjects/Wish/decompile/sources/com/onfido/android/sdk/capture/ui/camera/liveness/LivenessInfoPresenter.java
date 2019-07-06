package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.util.Log;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallenge.MovementType;
import com.onfido.android.sdk.capture.utils.ReactiveExtensionsKt;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.Challenge;
import com.onfido.api.client.data.Challenge.Type;
import com.onfido.api.client.data.LiveVideoLanguage;
import com.onfido.api.client.data.LiveVideoUpload;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

public final class LivenessInfoPresenter {
    public static final Companion Companion = new Companion(null);
    private OnfidoAPI a;
    private Applicant b;
    private View c;
    private final AnalyticsInteractor d;
    private final LivenessChallengesProvider e;
    private final IdentityInteractor f;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public interface View {
        void onLivenessVideoNotFound();

        void onVideoUploadError();

        void onVideoUploaded();

        void setActions(int i, int i2);

        void setLoading(boolean z);
    }

    static final class a<T> implements Consumer<LiveVideoUpload> {
        final /* synthetic */ LivenessInfoPresenter a;

        a(LivenessInfoPresenter livenessInfoPresenter) {
            this.a = livenessInfoPresenter;
        }

        /* renamed from: a */
        public final void accept(LiveVideoUpload liveVideoUpload) {
            LivenessInfoPresenter.access$getView$p(this.a).setLoading(false);
            LivenessInfoPresenter.access$getView$p(this.a).onVideoUploaded();
        }
    }

    static final class b<T> implements Consumer<Throwable> {
        final /* synthetic */ LivenessInfoPresenter a;

        b(LivenessInfoPresenter livenessInfoPresenter) {
            this.a = livenessInfoPresenter;
        }

        /* renamed from: a */
        public final void accept(Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error uploading liveness video: ");
            sb.append(th.getMessage());
            Log.e("LivenessInfoPresenter", sb.toString());
            LivenessInfoPresenter.access$getView$p(this.a).setLoading(false);
            LivenessInfoPresenter.access$getView$p(this.a).onVideoUploadError();
        }
    }

    public LivenessInfoPresenter(AnalyticsInteractor analyticsInteractor, LivenessChallengesProvider livenessChallengesProvider, IdentityInteractor identityInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        Intrinsics.checkParameterIsNotNull(livenessChallengesProvider, "challengesProvider");
        Intrinsics.checkParameterIsNotNull(identityInteractor, "identityInteractor");
        this.d = analyticsInteractor;
        this.e = livenessChallengesProvider;
        this.f = identityInteractor;
    }

    public static final /* synthetic */ View access$getView$p(LivenessInfoPresenter livenessInfoPresenter) {
        View view = livenessInfoPresenter.c;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        return view;
    }

    public static /* synthetic */ void init$default(LivenessInfoPresenter livenessInfoPresenter, View view, OnfidoAPI onfidoAPI, Applicant applicant, int i, Object obj) {
        if ((i & 2) != 0) {
            onfidoAPI = null;
        }
        if ((i & 4) != 0) {
            applicant = null;
        }
        livenessInfoPresenter.init(view, onfidoAPI, applicant);
    }

    public final void init(View view, OnfidoAPI onfidoAPI, Applicant applicant) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.c = view;
        this.a = onfidoAPI;
        this.b = applicant;
    }

    public final void start(boolean z, boolean z2) {
        if (z) {
            this.d.trackFaceIntro(true, CaptureType.VIDEO);
            int challengesCount = this.e.getChallengesCount();
            int spokenChallengesCount = this.e.getSpokenChallengesCount();
            View view = this.c;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            view.setActions(challengesCount, spokenChallengesCount);
            return;
        }
        this.d.trackFaceCapture(true, true, z2, CaptureType.VIDEO);
    }

    public final void stop(boolean z, boolean z2) {
        if (z) {
            this.d.trackFaceIntro(false, CaptureType.VIDEO);
        } else {
            this.d.trackFaceCapture(false, true, z2, CaptureType.VIDEO);
        }
    }

    public final void trackUploadStarted() {
        this.d.trackUploadingScreen(CaptureType.VIDEO);
    }

    public final void uploadLivenessVideo(String str, List<LivenessUploadChallenge> list) {
        Challenge challenge;
        Intrinsics.checkParameterIsNotNull(str, "videoFilePath");
        Intrinsics.checkParameterIsNotNull(list, "livenessUploadChallenges");
        File file = new File(str);
        if (file.exists()) {
            View view = this.c;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            view.setLoading(true);
            Iterable<LivenessUploadChallenge> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (LivenessUploadChallenge livenessUploadChallenge : iterable) {
                switch (livenessUploadChallenge.getType()) {
                    case RECITE:
                        Type type = Type.RECITE;
                        Object query = livenessUploadChallenge.getQuery();
                        if (query != null) {
                            challenge = new Challenge(type, (int[]) query);
                            break;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.IntArray");
                        }
                    case MOVEMENT:
                        Type type2 = Type.MOVEMENT;
                        Object query2 = livenessUploadChallenge.getQuery();
                        if (query2 != null) {
                            challenge = new Challenge(type2, ((MovementType) query2).getId());
                            break;
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallenge.MovementType");
                        }
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                arrayList.add(challenge);
            }
            Object[] array = ((List) arrayList).toArray(new Challenge[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            Challenge[] challengeArr = (Challenge[]) array;
            LiveVideoLanguage[] liveVideoLanguageArr = {new LiveVideoLanguage("sdk", this.f.getSdkLocaleCode())};
            if (!(this.b == null || this.a == null)) {
                OnfidoAPI onfidoAPI = this.a;
                if (onfidoAPI == null) {
                    Intrinsics.throwNpe();
                }
                ReactiveExtensionsKt.subscribeAndObserve$default(onfidoAPI.uploadLiveVideo(this.b, file.getName(), "video/mp4", FilesKt.readBytes(file), this.f.getSdkSource(), this.f.getSdkVersion(), challengeArr, Long.valueOf(((LivenessUploadChallenge) list.get(ArraysKt.getLastIndex((Object[]) challengeArr) - 1)).getEndChallengeTimestamp()), liveVideoLanguageArr), null, null, 3, null).subscribe(new a(this), new b(this));
            }
        } else {
            View view2 = this.c;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            view2.onLivenessVideoNotFound();
        }
    }
}
