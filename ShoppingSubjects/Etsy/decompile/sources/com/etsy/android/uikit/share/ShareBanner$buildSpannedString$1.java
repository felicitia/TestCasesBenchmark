package com.etsy.android.uikit.share;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import kotlin.jvm.a.a;
import kotlin.jvm.internal.p;

/* compiled from: ShareBanner.kt */
public final class ShareBanner$buildSpannedString$1 extends ClickableSpan {
    final /* synthetic */ a $shareClickedEvent;
    final /* synthetic */ a this$0;

    ShareBanner$buildSpannedString$1(a aVar, a aVar2) {
        this.this$0 = aVar;
        this.$shareClickedEvent = aVar2;
    }

    public void onClick(View view) {
        p.b(view, "widget");
        this.$shareClickedEvent.invoke();
        this.this$0.b();
    }

    public void updateDrawState(TextPaint textPaint) {
        p.b(textPaint, "ds");
        textPaint.setUnderlineText(true);
        textPaint.setTypeface(Typeface.defaultFromStyle(1));
    }
}
