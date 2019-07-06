package com.etsy.android.ui.convos.convoredesign;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import com.etsy.android.R;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.util.CameraHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.rxkotlin.c;
import java.io.File;
import kotlin.jvm.a.a;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadImageHelper.kt */
public final class ae {
    private Disposable a;
    /* access modifiers changed from: private */
    public final af b;
    /* access modifiers changed from: private */
    public final ConvoThreadFragment2 c;
    /* access modifiers changed from: private */
    public final CameraHelper d;
    /* access modifiers changed from: private */
    public final l e;

    public ae(af afVar, ConvoThreadFragment2 convoThreadFragment2, CameraHelper cameraHelper, l lVar) {
        p.b(afVar, "presenter");
        p.b(convoThreadFragment2, "fragment");
        p.b(cameraHelper, "cameraHelper");
        p.b(lVar, "logCat");
        this.b = afVar;
        this.c = convoThreadFragment2;
        this.d = cameraHelper;
        this.e = lVar;
        g resultObservable = this.d.getResultObservable();
        p.a((Object) resultObservable, "cameraHelper.resultObservable");
        this.a = c.a(resultObservable, (b) new ConvoThreadImageHelper$2(this), (a) null, new ConvoThreadImageHelper$1(this), 2, (Object) null);
    }

    public final void a() {
        com.etsy.android.extensions.c.a(this.c.getActivity(), R.string.camera_error_creating_file);
    }

    public final void a(Bitmap bitmap, File file) {
        p.b(bitmap, "bitmap");
        if (file != null) {
            this.b.a(bitmap, file);
        }
    }

    public final void a(int i, int i2, Intent intent) {
        this.d.onActivityResult(i, i2, intent);
    }

    public final void b() {
        this.d.startImagePicker((Fragment) this.c, (int) R.string.choose_image);
    }

    public final void c() {
        Disposable disposable = this.a;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
