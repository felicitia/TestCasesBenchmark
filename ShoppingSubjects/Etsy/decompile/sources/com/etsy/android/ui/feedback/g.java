package com.etsy.android.ui.feedback;

import android.app.Activity;
import android.os.Bundle;
import com.etsy.android.R;
import com.etsy.android.uikit.nav.b;
import io.reactivex.rxkotlin.c;
import io.reactivex.u;
import io.reactivex.v;
import kotlin.TypeCastException;
import kotlin.h;
import kotlin.jvm.internal.p;
import kotlin.text.m;

/* compiled from: FeedbackPresenter.kt */
public final class g {
    public static final a a = new a(null);
    private FeedbackView b;
    private Activity c;
    private Bundle d;
    private b e;
    private u f;
    private final i g;

    /* compiled from: FeedbackPresenter.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    public g(i iVar) {
        p.b(iVar, "repository");
        this.g = iVar;
    }

    public final void a(FeedbackView feedbackView, Activity activity, Bundle bundle, b bVar, u uVar) {
        p.b(feedbackView, "view");
        p.b(activity, "activity");
        p.b(bVar, "navigator");
        this.b = feedbackView;
        this.c = activity;
        this.d = bundle;
        this.e = bVar;
        this.f = uVar;
        feedbackView.focus();
        a(bundle);
    }

    public final Bundle a() {
        c();
        this.b = null;
        return this.d;
    }

    private final void c() {
        FeedbackView feedbackView = this.b;
        if (feedbackView != null) {
            String feedback = feedbackView.getFeedback();
            if (feedback == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String obj = m.b(feedback).toString();
            Bundle bundle = this.d;
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.d = bundle;
            Bundle bundle2 = this.d;
            if (bundle2 != null) {
                bundle2.putString("feedback_text", obj);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void d() {
        this.d = null;
    }

    private final void a(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("feedback_text", "");
            p.a((Object) string, "previousText");
            if (string == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            if (m.b(string).toString().length() > 0) {
                FeedbackView feedbackView = this.b;
                if (feedbackView != null) {
                    feedbackView.setFeedback(string);
                }
            }
        }
    }

    public final void b() {
        e();
    }

    private final void e() {
        FeedbackView feedbackView = this.b;
        if (feedbackView != null) {
            String feedback = feedbackView.getFeedback();
            if (feedback == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String obj = m.b(feedback).toString();
            if (obj.length() > 0) {
                v a2 = this.g.a(obj).b(io.reactivex.e.a.b()).a(this.f);
                p.a((Object) a2, "repository\n             ….observeOn(mainScheduler)");
                c.a(a2, (kotlin.jvm.a.b<? super Throwable, h>) FeedbackPresenter$sendFeedback$1$2.INSTANCE, new FeedbackPresenter$sendFeedback$$inlined$let$lambda$1<>(this));
                return;
            }
            feedbackView.showError(R.string.error_feedback_empty);
        }
    }

    /* access modifiers changed from: private */
    public final void f() {
        Activity activity = this.c;
        if (activity != null) {
            b bVar = this.e;
            if (bVar != null) {
                bVar.h();
            }
            com.etsy.android.extensions.c.a(activity, R.string.feedback_sent);
        }
    }
}
