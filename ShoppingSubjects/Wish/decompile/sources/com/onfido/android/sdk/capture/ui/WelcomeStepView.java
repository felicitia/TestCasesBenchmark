package com.onfido.android.sdk.capture.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.util.HashMap;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public final class WelcomeStepView extends RelativeLayout {
    private HashMap a;

    public WelcomeStepView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context);
        RelativeLayout.inflate(context, R.layout.onfido_welcome_step_view, this);
    }

    public View _$_findCachedViewById(int i) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        View view = (View) this.a.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.a.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final void hideSeparator(boolean z) {
        ViewExtensionsKt.toGone(_$_findCachedViewById(z ? R.id.topSeparator : R.id.bottomSeparator));
    }

    public final void hideSeparators() {
        hideSeparator(true);
        hideSeparator(false);
    }

    public final void setIcon(int i) {
        ViewExtensionsKt.toGone((TextView) _$_findCachedViewById(R.id.stepNumber));
        ViewExtensionsKt.toVisible((ImageView) _$_findCachedViewById(R.id.stepIcon));
        ((ImageView) _$_findCachedViewById(R.id.stepIcon)).setImageDrawable(ContextCompat.getDrawable(getContext(), i));
    }

    public final void setStepInfo(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, StrongAuth.AUTH_TITLE);
        TextView textView = (TextView) _$_findCachedViewById(R.id.stepNumber);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(i);
        textView.setText(sb.toString());
        CharSequence charSequence = str;
        ((TextView) _$_findCachedViewById(R.id.stepTitle)).setText(charSequence);
        StaticLayout staticLayout = new StaticLayout(charSequence, ((TextView) _$_findCachedViewById(R.id.stepTitle)).getPaint(), Integer.MAX_VALUE, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        LayoutParams layoutParams = ((TextView) _$_findCachedViewById(R.id.stepTitle)).getLayoutParams();
        if (layoutParams == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(layoutParams2.leftMargin, (int) (((float) (getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_welcome_icon_container_dimen) - staticLayout.getHeight())) / 2.0f), 0, 0);
        ((TextView) _$_findCachedViewById(R.id.stepTitle)).setLayoutParams(layoutParams2);
    }
}
