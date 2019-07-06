package com.etsy.android.uikit.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.n;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class EtsyLinkify {
    private static final String a = f.a(EtsyLinkify.class);

    public static class CustomColorUnderlineURLSpan extends UnderlineURLSpan {
        @ColorInt
        private final int mColor;

        public CustomColorUnderlineURLSpan(@ColorInt int i, @Nullable String str, boolean z) {
            super(str, z);
            this.mColor = i;
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(this.mColor);
        }
    }

    public static class UnderlineURLSpan extends URLSpan {
        private final boolean mShouldUnderline;

        public UnderlineURLSpan(String str, boolean z) {
            super(str);
            this.mShouldUnderline = z;
        }

        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            if (!this.mShouldUnderline) {
                textPaint.setUnderlineText(false);
            }
        }

        public void onClick(View view) {
            try {
                URL url = new URL(getURL());
                if (n.c(url.getHost())) {
                    EtsyLinkify.b(view.getContext(), n.a(url));
                } else {
                    EtsyLinkify.b(view.getContext(), url.toString(), EtsyLinkify.b(view.getContext()));
                }
            } catch (MalformedURLException unused) {
                super.onClick(view);
            }
        }
    }

    public static void a(Context context, TextView textView) {
        a(context, textView, true);
    }

    public static void a(Context context, TextView textView, boolean z) {
        a(context, textView, z, 3);
    }

    public static void a(Context context, TextView textView, boolean z, int i) {
        TextView textView2 = textView;
        Linkify.addLinks(textView2, i);
        textView2.setLinkTextColor(context.getResources().getColor(e.blue));
        URLSpan[] urls = textView.getUrls();
        if (urls.length > 0) {
            Builder b = b(context);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText());
            for (URLSpan uRLSpan : urls) {
                final String url = uRLSpan.getURL();
                int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
                int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
                spannableStringBuilder.removeSpan(uRLSpan);
                final Context context2 = context;
                final Builder builder = b;
                AnonymousClass2 r3 = new UnderlineURLSpan(url, z) {
                    public void onClick(View view) {
                        try {
                            URL url = new URL(url);
                            if (n.c(url.getHost())) {
                                EtsyLinkify.b(context2, n.a(url));
                            } else {
                                EtsyLinkify.b(context2, url, builder);
                            }
                        } catch (MalformedURLException unused) {
                            super.onClick(view);
                        }
                    }
                };
                spannableStringBuilder.setSpan(r3, spanStart, spanEnd, 33);
            }
            textView2.setText(spannableStringBuilder);
        }
    }

    /* access modifiers changed from: private */
    public static void b(final Context context, final String str, Builder builder) {
        builder.setPositiveButton(o.convo_external_link_warning_open_button, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EtsyLinkify.b(context, str);
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public static Builder b(Context context) {
        Builder builder = new Builder(context);
        builder.setTitle(o.convo_external_link_warning_title);
        builder.setMessage(o.convo_external_link_warning_message).setCancelable(true).setNegativeButton(o.convo_external_link_warning_cancel_button, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder;
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        String packageName = context.getPackageName();
        List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        boolean z = true;
        if (!queryIntentActivities.isEmpty()) {
            Iterator it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo resolveInfo = (ResolveInfo) it.next();
                if (resolveInfo.activityInfo.packageName.equals(packageName)) {
                    intent.setPackage(resolveInfo.activityInfo.packageName);
                    intent.putExtra("NAV_INTERNAL_LINK", true);
                    break;
                }
            }
        }
        z = false;
        if (z) {
            context.startActivity(intent);
            return;
        }
        ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(context.getPackageManager(), 0);
        if (resolveActivityInfo == null || !resolveActivityInfo.exported) {
            aj.b(context, o.whoops_somethings_wrong);
        } else {
            context.startActivity(intent);
        }
    }

    public static void a(TextView textView, String str, String str2, boolean z, View.OnClickListener onClickListener) {
        Linkify.addLinks(textView, Pattern.compile(str), str2);
        b(textView, z, onClickListener);
        textView.setLinkTextColor(textView.getResources().getColor(e.blue));
    }

    public static void a(TextView textView, boolean z, View.OnClickListener onClickListener) {
        a(textView, z, true, onClickListener);
    }

    public static void a(TextView textView, boolean z, boolean z2, View.OnClickListener onClickListener) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        b(textView, z, onClickListener);
        if (z2) {
            textView.setLinkTextColor(textView.getResources().getColor(e.blue));
        } else {
            textView.setLinkTextColor(textView.getTextColors().getDefaultColor());
        }
    }

    private static void b(TextView textView, boolean z, View.OnClickListener onClickListener) {
        TextView textView2 = textView;
        if (textView2 != null) {
            URLSpan[] urls = textView2.getUrls();
            Context context = textView2.getContext();
            if (urls.length > 0) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView2.getText());
                for (URLSpan uRLSpan : urls) {
                    final String url = uRLSpan.getURL();
                    int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
                    int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
                    spannableStringBuilder.removeSpan(uRLSpan);
                    final View.OnClickListener onClickListener2 = onClickListener;
                    final Context context2 = context;
                    AnonymousClass5 r2 = new UnderlineURLSpan(url, z) {
                        public void onClick(View view) {
                            if (onClickListener2 != null) {
                                onClickListener2.onClick(view);
                            } else {
                                EtsyLinkify.b(context2, url);
                            }
                        }
                    };
                    spannableStringBuilder.setSpan(r2, spanStart, spanEnd, 33);
                }
                textView2.setText(spannableStringBuilder);
            }
        }
    }

    public static Spanned a(@NonNull Context context, @NonNull Spanned spanned, boolean z, boolean z2, @Nullable View.OnClickListener onClickListener) {
        return a(context, spanned, z, z2, e.blue, onClickListener);
    }

    public static Spanned a(@NonNull Context context, @NonNull Spanned spanned, boolean z, boolean z2, @ColorRes int i, @Nullable View.OnClickListener onClickListener) {
        boolean z3;
        Spanned spanned2 = spanned;
        int color = ContextCompat.getColor(context, i);
        if (spanned.length() == 0) {
            return spanned2;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) spanned2.getSpans(0, spanned.length(), URLSpan.class);
        if (uRLSpanArr.length == 0) {
            return spanned2;
        }
        SpannableString spannableString = new SpannableString(spanned2);
        for (URLSpan uRLSpan : uRLSpanArr) {
            int spanStart = spanned2.getSpanStart(uRLSpan);
            int spanEnd = spanned2.getSpanEnd(uRLSpan);
            final String url = uRLSpan.getURL();
            spannableString.removeSpan(uRLSpan);
            if (z2) {
                try {
                    z3 = n.c(new URL(url).getHost());
                } catch (MalformedURLException unused) {
                }
            } else {
                z3 = true;
            }
            if (z3) {
                final View.OnClickListener onClickListener2 = onClickListener;
                AnonymousClass6 r1 = new CustomColorUnderlineURLSpan(color, url, z) {
                    public void onClick(View view) {
                        if (onClickListener2 != null) {
                            onClickListener2.onClick(view);
                        } else {
                            EtsyLinkify.b(view.getContext(), url);
                        }
                    }
                };
                spannableString.setSpan(r1, spanStart, spanEnd, 33);
            }
        }
        return spannableString;
    }
}
