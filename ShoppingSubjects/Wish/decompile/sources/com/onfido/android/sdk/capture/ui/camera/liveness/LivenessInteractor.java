package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.os.Environment;
import android.os.StatFs;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.io.File;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public class LivenessInteractor {
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LivenessInteractor.class), "livenessDataDirectory", "getLivenessDataDirectory()Ljava/io/File;"))};
    private final Lazy b = LazyKt.lazy(a.a);
    private final LivenessChallengesProvider c;

    static final class a extends Lambda implements Function0<File> {
        public static final a a = new a();

        a() {
            super(0);
        }

        /* renamed from: a */
        public final File invoke() {
            return Environment.getDataDirectory();
        }
    }

    public LivenessInteractor(LivenessChallengesProvider livenessChallengesProvider) {
        Intrinsics.checkParameterIsNotNull(livenessChallengesProvider, "livenessChallengesProvider");
        this.c = livenessChallengesProvider;
    }

    private final File a() {
        Lazy lazy = this.b;
        KProperty kProperty = a[0];
        return (File) lazy.getValue();
    }

    public long getAvailableStorageSpace() {
        StatFs statFs = new StatFs(a().getPath());
        if (ContextUtilsKt.apilevelAtLeast(18)) {
            return statFs.getAvailableBytes();
        }
        return (long) (statFs.getAvailableBlocks() * statFs.getBlockSize());
    }

    public Observable<LivenessChallenge> getChallenges() {
        return this.c.getChallenges();
    }

    public int getChallengesCount() {
        return this.c.getChallengesCount();
    }

    public PublishSubject<Boolean> getChallengesRemainder() {
        return this.c.getChallengesRemainder();
    }

    public List<LivenessUploadChallenge> getUploadChallengesList() {
        return this.c.getUploadChallengesList();
    }

    public void issueNextChallenge() {
        this.c.issueNextChallenge();
    }
}
