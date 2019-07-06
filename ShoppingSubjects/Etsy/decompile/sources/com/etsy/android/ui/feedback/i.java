package com.etsy.android.ui.feedback;

import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.d;
import com.etsy.android.lib.core.http.request.d.b;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.Feedback;
import io.reactivex.w;
import io.reactivex.y;
import java.util.List;
import kotlin.jvm.internal.p;

/* compiled from: FeedbackRepository.kt */
public final class i {
    private final com.etsy.android.lib.core.http.request.EtsyApiV3Request.a<Feedback> a;
    private final com.etsy.android.lib.core.http.request.d.a<Feedback> b;
    private final v c;

    /* compiled from: FeedbackRepository.kt */
    static final class a<T> implements y<T> {
        final /* synthetic */ i a;
        final /* synthetic */ String b;

        /* renamed from: com.etsy.android.ui.feedback.i$a$a reason: collision with other inner class name */
        /* compiled from: FeedbackRepository.kt */
        public static final class C0096a extends b<Feedback> {
            final /* synthetic */ w a;

            C0096a(w wVar) {
                this.a = wVar;
            }

            public void a(List<Feedback> list, int i, com.etsy.android.lib.core.a.a<Feedback> aVar) {
                p.b(list, ResponseConstants.RESULTS);
                p.b(aVar, "result");
                w wVar = this.a;
                String text = ((Feedback) list.get(0)).getText();
                p.a((Object) text, "results[0].text");
                wVar.onSuccess(new j.b(text));
            }

            public void a(int i, String str, com.etsy.android.lib.core.a.a<Feedback> aVar) {
                p.b(aVar, "result");
                this.a.onSuccess(new com.etsy.android.ui.feedback.j.a(i, str, aVar));
            }
        }

        a(i iVar, String str) {
            this.a = iVar;
            this.b = str;
        }

        public final void a(w<j> wVar) {
            p.b(wVar, "emitter");
            this.a.a().a(1);
            com.etsy.android.lib.core.http.body.FormBody.a aVar = new com.etsy.android.lib.core.http.body.FormBody.a();
            aVar.b(ResponseConstants.FEEDBACK, this.b);
            this.a.a().a((BaseHttpBody) aVar.f());
            this.a.b().a((EtsyApiV3Request) this.a.a().d());
            this.a.c().j().a((com.etsy.android.lib.core.http.request.a<?, ?, ?>) (d) ((com.etsy.android.lib.core.http.request.d.a) this.a.b().a((C0065a<ResultType>) new C0096a<ResultType>(wVar))).c());
        }
    }

    public i(com.etsy.android.lib.core.http.request.EtsyApiV3Request.a<Feedback> aVar, com.etsy.android.lib.core.http.request.d.a<Feedback> aVar2, v vVar) {
        p.b(aVar, "requestBuilder");
        p.b(aVar2, "jobBuilder");
        p.b(vVar, "session");
        this.a = aVar;
        this.b = aVar2;
        this.c = vVar;
    }

    public final com.etsy.android.lib.core.http.request.EtsyApiV3Request.a<Feedback> a() {
        return this.a;
    }

    public final com.etsy.android.lib.core.http.request.d.a<Feedback> b() {
        return this.b;
    }

    public final v c() {
        return this.c;
    }

    public final io.reactivex.v<j> a(String str) {
        p.b(str, ResponseConstants.FEEDBACK);
        io.reactivex.v<j> a2 = io.reactivex.v.a((y<T>) new a<T>(this, str));
        p.a((Object) a2, "Single.create<FeedbackRe…tQueue.add(job)\n        }");
        return a2;
    }
}
