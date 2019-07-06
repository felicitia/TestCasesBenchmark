package com.onfido.android.sdk.capture.ui.privacy;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

public final class PrivacyPolicyView extends RelativeLayout {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PrivacyPolicyView.class), "valueAnimator", "getValueAnimator()Landroid/animation/ValueAnimator;"))};
    /* access modifiers changed from: private */
    public Listener b;
    private final AnimationListener c;
    private final AnimationListener d;
    private final Lazy e;
    private HashMap f;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public interface Listener {
        void onContinuePressed();

        void onDeclinePressed();
    }

    static final class a implements Runnable {
        final /* synthetic */ PrivacyPolicyView a;

        a(PrivacyPolicyView privacyPolicyView) {
            this.a = privacyPolicyView;
        }

        public final void run() {
            ViewExtensionsKt.toGone((LinearLayout) this.a._$_findCachedViewById(R.id.container));
            this.a.getValueAnimator().reverse();
            this.a.getHandler().postDelayed(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void run() {
                    ViewExtensionsKt.alphaAnimator$default((FrameLayout) this.a.a._$_findCachedViewById(R.id.root), 0.0f, 0, 2, null).withEndAction(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            ViewExtensionsKt.toGone(this.a.a.a);
                        }
                    });
                }
            }, 500);
        }
    }

    static final class b implements Runnable {
        final /* synthetic */ PrivacyPolicyView a;

        b(PrivacyPolicyView privacyPolicyView) {
            this.a = privacyPolicyView;
        }

        public final void run() {
            PrivacyPolicyView privacyPolicyView = this.a;
            View _$_findCachedViewById = this.a._$_findCachedViewById(R.id.circularReveal);
            Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "circularReveal");
            privacyPolicyView.a(_$_findCachedViewById, false);
            this.a.getValueAnimator().start();
        }
    }

    static final class c extends Lambda implements Function0<ValueAnimator> {
        final /* synthetic */ PrivacyPolicyView a;

        static final class a implements AnimatorUpdateListener {
            final /* synthetic */ c a;

            a(c cVar) {
                this.a = cVar;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                TypedValue typedValue = new TypedValue();
                this.a.a.getResources().getValue(R.dimen.onfido_privacy_policy_oval_ratio, typedValue, true);
                float f = typedValue.getFloat();
                LayoutParams layoutParams = this.a.a._$_findCachedViewById(R.id.circularReveal).getLayoutParams();
                Object animatedValue = valueAnimator.getAnimatedValue();
                if (animatedValue == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                layoutParams.width = (int) (((float) ((Integer) animatedValue).intValue()) * f);
                Object animatedValue2 = valueAnimator.getAnimatedValue();
                if (animatedValue2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                layoutParams.height = ((Integer) animatedValue2).intValue();
                this.a.a._$_findCachedViewById(R.id.circularReveal).setLayoutParams(layoutParams);
                View _$_findCachedViewById = this.a.a._$_findCachedViewById(R.id.circularReveal);
                Object animatedValue3 = valueAnimator.getAnimatedValue();
                if (animatedValue3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                _$_findCachedViewById.setX(((float) (-((Integer) animatedValue3).intValue())) / f);
                View _$_findCachedViewById2 = this.a.a._$_findCachedViewById(R.id.circularReveal);
                float height = (float) this.a.a.getHeight();
                Object animatedValue4 = valueAnimator.getAnimatedValue();
                if (animatedValue4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                _$_findCachedViewById2.setY(height - (((float) ((Integer) animatedValue4).intValue()) / 2.0f));
            }
        }

        c(PrivacyPolicyView privacyPolicyView) {
            this.a = privacyPolicyView;
            super(0);
        }

        /* renamed from: a */
        public final ValueAnimator invoke() {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, (int) (((float) (((LinearLayout) this.a._$_findCachedViewById(R.id.container)).getHeight() + this.a.getContext().getResources().getDimensionPixelOffset(R.dimen.onfido_privacy_policy_content_top_margin))) * 2.0f)});
            ofInt.addUpdateListener(new a(this));
            ofInt.addListener(new PrivacyPolicyView$valueAnimator$2$$special$$inlined$apply$lambda$2(this));
            ofInt.setDuration(500);
            ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
            return ofInt;
        }
    }

    public PrivacyPolicyView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        super(context);
        View.inflate(getContext(), R.layout.onfido_privacy_policy_view, this);
        ((LinearLayout) _$_findCachedViewById(R.id.container)).setAlpha(0.0f);
        ((Button) _$_findCachedViewById(R.id.continueButton)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PrivacyPolicyView a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                Listener access$getListener$p = this.a.b;
                if (access$getListener$p != null) {
                    access$getListener$p.onContinuePressed();
                }
                this.a.a();
            }
        });
        ((Button) _$_findCachedViewById(R.id.declineButton)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PrivacyPolicyView a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                Listener access$getListener$p = this.a.b;
                if (access$getListener$p != null) {
                    access$getListener$p.onDeclinePressed();
                }
            }
        });
        ((WebView) _$_findCachedViewById(R.id.webView)).setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ PrivacyPolicyView a;

            {
                this.a = r1;
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                ViewExtensionsKt.toGone((ProgressBar) this.a._$_findCachedViewById(R.id.progress));
                ViewExtensionsKt.toVisible((WebView) this.a._$_findCachedViewById(R.id.webView));
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                ViewExtensionsKt.toGone((WebView) this.a._$_findCachedViewById(R.id.webView));
                ViewExtensionsKt.toVisible((ProgressBar) this.a._$_findCachedViewById(R.id.progress));
            }
        });
        ((WebView) _$_findCachedViewById(R.id.webView)).getSettings().setJavaScriptEnabled(true);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.onfido_close);
        if (drawable != null) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            wrap.setColorFilter(ContextCompat.getColor(getContext(), R.color.onfido_white), Mode.SRC_ATOP);
            DrawableCompat.setTint(wrap, ContextCompat.getColor(getContext(), R.color.onfidoTextColorPrimary));
            DrawableCompat.setTintMode(wrap, Mode.SRC_IN);
            ((Toolbar) _$_findCachedViewById(R.id.toolbar)).setNavigationIcon(wrap);
        }
        ((Toolbar) _$_findCachedViewById(R.id.toolbar)).setNavigationOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PrivacyPolicyView a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.onBackPressed();
            }
        });
        String string = getContext().getString(R.string.onfido_privacy_policy);
        String string2 = getContext().getString(R.string.onfido_terms_of_use);
        String string3 = getContext().getString(R.string.onfido_privacy_policy_terms_extended, new Object[]{string2, string});
        SpannableString spannableString = new SpannableString(string3);
        Intrinsics.checkExpressionValueIsNotNull(string3, "extendedText");
        Intrinsics.checkExpressionValueIsNotNull(string2, "onfidoPrivacyTerms");
        setSpan(spannableString, string3, string2, getClickableSpan(string2, "https://onfido.com/termsofuse"));
        setSpan(spannableString, string3, string2, new TextAppearanceSpan(getContext(), R.style.OnfidoTextStyle_Bold_Privacy));
        Intrinsics.checkExpressionValueIsNotNull(string, "onfidoPrivacyPolicy");
        setSpan(spannableString, string3, string, getClickableSpan(string, "https://onfido.com/privacy"));
        setSpan(spannableString, string3, string, new TextAppearanceSpan(getContext(), R.style.OnfidoTextStyle_Bold_Privacy));
        ((TextView) _$_findCachedViewById(R.id.textContent)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) _$_findCachedViewById(R.id.textContent)).setText(spannableString, BufferType.SPANNABLE);
        this.c = new PrivacyPolicyView$inAnimationListener$1(this);
        this.d = new PrivacyPolicyView$outAnimationListener$1(this);
        this.e = LazyKt.lazy(new c(this));
    }

    /* access modifiers changed from: private */
    public final void a() {
        ViewExtensionsKt.alphaAnimator$default((LinearLayout) _$_findCachedViewById(R.id.container), 0.0f, 0, 2, null).withEndAction(new a(this));
    }

    /* access modifiers changed from: private */
    public final void a(View view, boolean z) {
        while (view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.setClipChildren(z);
            view = viewGroup;
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        View view = (View) this.f.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.f.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final ClickableSpan getClickableSpan(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, StrongAuth.AUTH_TITLE);
        Intrinsics.checkParameterIsNotNull(str2, "url");
        return new PrivacyPolicyView$getClickableSpan$1(this, str, str2);
    }

    public final AnimationListener getInAnimationListener() {
        return this.c;
    }

    public final AnimationListener getOutAnimationListener() {
        return this.d;
    }

    public final ValueAnimator getValueAnimator() {
        Lazy lazy = this.e;
        KProperty kProperty = a[0];
        return (ValueAnimator) lazy.getValue();
    }

    public final boolean onBackPressed() {
        if (!ViewExtensionsKt.isVisible((LinearLayout) _$_findCachedViewById(R.id.webViewContainer))) {
            return false;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) getHeight());
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setAnimationListener(this.d);
        ((LinearLayout) _$_findCachedViewById(R.id.webViewContainer)).startAnimation(translateAnimation);
        ViewExtensionsKt.toGone((LinearLayout) _$_findCachedViewById(R.id.webViewContainer));
        return true;
    }

    public final void removeLastStep() {
        ViewExtensionsKt.toGone((LinearLayout) _$_findCachedViewById(R.id.lastStep));
    }

    public final void setListener(Listener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.b = listener;
    }

    public final void setSpan(SpannableString spannableString, String str, String str2, Object obj) {
        Intrinsics.checkParameterIsNotNull(spannableString, "spannableString");
        Intrinsics.checkParameterIsNotNull(str, "wholeText");
        Intrinsics.checkParameterIsNotNull(str2, "string");
        Intrinsics.checkParameterIsNotNull(obj, "span");
        CharSequence charSequence = str;
        String str3 = str2;
        spannableString.setSpan(obj, StringsKt.indexOf$default(charSequence, str3, 0, false, 6, (Object) null), StringsKt.indexOf$default(charSequence, str3, 0, false, 6, (Object) null) + str2.length(), 33);
    }

    public final void show() {
        ViewExtensionsKt.alphaAnimator$default((FrameLayout) _$_findCachedViewById(R.id.root), 1.0f, 0, 2, null).withEndAction(new b(this));
    }
}
