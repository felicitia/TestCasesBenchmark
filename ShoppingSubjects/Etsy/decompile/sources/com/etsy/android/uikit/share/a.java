package com.etsy.android.uikit.share;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.etsy.android.lib.a.g;
import com.etsy.android.lib.a.o;
import com.etsy.android.stylekit.alerts.AlertLayout;
import com.etsy.android.uikit.util.AnimationUtil;
import io.reactivex.functions.Consumer;
import io.reactivex.q;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import kotlin.h;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.u;

/* compiled from: ShareBanner.kt */
public final class a {
    private AlertLayout a;

    /* renamed from: com.etsy.android.uikit.share.a$a reason: collision with other inner class name */
    /* compiled from: ShareBanner.kt */
    static final class C0110a<T> implements Consumer<Long> {
        final /* synthetic */ a a;

        C0110a(a aVar) {
            this.a = aVar;
        }

        /* renamed from: a */
        public final void accept(Long l) {
            this.a.b();
        }
    }

    public a(AlertLayout alertLayout, kotlin.jvm.a.a<h> aVar, final kotlin.jvm.a.a<h> aVar2) {
        p.b(alertLayout, "alert");
        p.b(aVar, "shareClickedEvent");
        p.b(aVar2, "dismissClicked");
        this.a = alertLayout;
        TextView messageView = this.a.getMessageView();
        p.a((Object) messageView, "mAlertLayout.messageView");
        messageView.setMovementMethod(LinkMovementMethod.getInstance());
        TextView messageView2 = this.a.getMessageView();
        Context context = alertLayout.getContext();
        p.a((Object) context, "alert.context");
        messageView2.setText(a(context, aVar), BufferType.SPANNABLE);
        this.a.setIcon(g.sk_ic_androidshare);
        this.a.setVisibility(0);
        this.a.setDismissListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                aVar2.invoke();
                this.a.b();
            }
        });
        AnimationUtil.c(this.a, 0);
        a();
    }

    private final SpannableStringBuilder a(Context context, kotlin.jvm.a.a<h> aVar) {
        String string = context.getString(o.social_organic_send_friends);
        u uVar = u.a;
        Object[] objArr = {context.getString(o.social_organic_share_screenshot)};
        String format = String.format("\n%s", Arrays.copyOf(objArr, objArr.length));
        p.a((Object) format, "java.lang.String.format(format, *args)");
        u uVar2 = u.a;
        Object[] objArr2 = {string, format};
        String format2 = String.format("%s%s", Arrays.copyOf(objArr2, objArr2.length));
        p.a((Object) format2, "java.lang.String.format(format, *args)");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format2);
        spannableStringBuilder.setSpan(new ShareBanner$buildSpannedString$1(this, aVar), string.length(), string.length() + format.length(), 0);
        return spannableStringBuilder;
    }

    private final void a() {
        q.a(15, TimeUnit.SECONDS).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new C0110a<Object>(this));
    }

    /* access modifiers changed from: private */
    public final void b() {
        AnimationUtil.b((View) this.a, 0);
    }
}
