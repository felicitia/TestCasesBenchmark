package com.onfido.android.sdk.capture.ui.camera.liveness;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public class LivenessChallengesProvider {
    private int a;
    private PublishSubject<LivenessChallenge> b;
    private PublishSubject<Boolean> c;
    private long d;
    private final List<LivenessChallenge> e = CollectionsKt.listOf((Object[]) new LivenessChallenge[]{LivenessChallenge.DIGITS, LivenessChallenge.TURN_FACE});
    private final List<LivenessUploadChallenge> f = new ArrayList();
    private final TimestampProvider g;

    /* JADX INFO: used method not loaded: kotlin.collections.CollectionsKt__CollectionsKt.listOf(java.lang.Object[]):null, types can be incorrect */
    public LivenessChallengesProvider(TimestampProvider timestampProvider) {
        Intrinsics.checkParameterIsNotNull(timestampProvider, "timestampProvider");
        this.g = timestampProvider;
    }

    private final void a() {
        this.a = 0;
        this.d = 0;
        PublishSubject<LivenessChallenge> create = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "PublishSubject.create()");
        this.b = create;
        PublishSubject<Boolean> create2 = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "PublishSubject.create()");
        this.c = create2;
        this.f.clear();
        Collections.shuffle(getChallengesList());
    }

    public Observable<LivenessChallenge> getChallenges() {
        a();
        return getChallengesSubject();
    }

    public int getChallengesCount() {
        return getChallengesList().size();
    }

    public List<LivenessChallenge> getChallengesList() {
        return this.e;
    }

    public PublishSubject<Boolean> getChallengesRemainder() {
        PublishSubject<Boolean> publishSubject = this.c;
        if (publishSubject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengesRemainderSubject");
        }
        return publishSubject;
    }

    public PublishSubject<LivenessChallenge> getChallengesSubject() {
        PublishSubject<LivenessChallenge> publishSubject = this.b;
        if (publishSubject == null) {
            Intrinsics.throwUninitializedPropertyAccessException("challengesSubject");
        }
        return publishSubject;
    }

    public int getSpokenChallengesCount() {
        Iterable<LivenessChallenge> challengesList = getChallengesList();
        int i = 0;
        if ((challengesList instanceof Collection) && ((Collection) challengesList).isEmpty()) {
            return 0;
        }
        for (LivenessChallenge isSpoken : challengesList) {
            if (isSpoken.isSpoken()) {
                i++;
            }
        }
        return i;
    }

    public List<LivenessUploadChallenge> getUploadChallengesList() {
        return this.f;
    }

    public void issueNextChallenge() {
        if (this.a == 0) {
            this.d = this.g.getCurrentTimestamp();
        }
        if (CollectionsKt.getLastIndex(getChallengesList()) >= this.a) {
            LivenessChallenge livenessChallenge = (LivenessChallenge) getChallengesList().get(this.a);
            livenessChallenge.reload();
            getUploadChallengesList().add(new LivenessUploadChallenge(livenessChallenge.getType(), livenessChallenge.getQuery()));
            getChallengesSubject().onNext(livenessChallenge);
            if (this.a == CollectionsKt.getLastIndex(getChallengesList())) {
                ((LivenessUploadChallenge) getUploadChallengesList().get(this.a - 1)).setEndChallengeTimestamp(this.g.getCurrentTimestamp() - this.d);
                getChallengesRemainder().onNext(Boolean.valueOf(true));
            }
            this.a++;
            return;
        }
        getChallengesSubject().onComplete();
    }
}
