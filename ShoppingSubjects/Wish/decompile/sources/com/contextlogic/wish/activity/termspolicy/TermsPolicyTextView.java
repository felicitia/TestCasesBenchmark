package com.contextlogic.wish.activity.termspolicy;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.webview.WebViewActivity;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.IllegalFormatException;

public class TermsPolicyTextView extends ThemedTextView {
    public static final String PRIVACY_POLICY = WishApplication.getInstance().getResources().getString(R.string.privacy_policy);
    public static final String TERMS_OF_USE = WishApplication.getInstance().getResources().getString(R.string.terms_of_use);
    private String mFullTextPlaceHolder;
    private int mHyperlinksType;

    public TermsPolicyTextView(Context context) {
        this(context, -1);
    }

    public TermsPolicyTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, -1, null);
    }

    public TermsPolicyTextView(Context context, int i) {
        this(context, null, 0, i, null);
    }

    public TermsPolicyTextView(Context context, String str) {
        this(context, null, 0, -1, str);
    }

    public TermsPolicyTextView(Context context, int i, String str) {
        this(context, null, 0, i, str);
    }

    public TermsPolicyTextView(Context context, AttributeSet attributeSet, int i, int i2, String str) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TermsPolicyTextView);
        if (i2 == -1) {
            i2 = obtainStyledAttributes.getInt(1, 0);
        }
        this.mHyperlinksType = i2;
        if (str == null) {
            str = obtainStyledAttributes.getString(0);
        }
        this.mFullTextPlaceHolder = str;
        obtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        setText(generateTermsPolicyText(getContext(), this.mHyperlinksType, this.mFullTextPlaceHolder));
        setLinkTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setFullTextPlaceHolder(String str) {
        this.mFullTextPlaceHolder = str;
    }

    public static void showTermsPolicyPage(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        intent.putExtra("ExtraUrl", str);
        intent.putExtra("ExtraHideActionBarItems", true);
        intent.putExtra("ExtraActionBarTitle", str2);
        context.startActivity(intent);
    }

    public static SpannableString generateTermsPolicyText(final Context context, int i, String str) {
        String str2;
        switch (i) {
            case 1:
                str2 = TERMS_OF_USE;
                break;
            case 2:
                str2 = PRIVACY_POLICY;
                break;
            default:
                str2 = String.format(WishApplication.getInstance().getResources().getString(R.string.a_and_b), new Object[]{TERMS_OF_USE, PRIVACY_POLICY});
                break;
        }
        if (str != null) {
            try {
                str2 = String.format(str, new Object[]{str2});
            } catch (IllegalFormatException unused) {
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(" ");
        String sb2 = sb.toString();
        SpannableString spannableString = new SpannableString(sb2);
        int indexOf = sb2.indexOf(TERMS_OF_USE);
        int indexOf2 = sb2.indexOf(PRIVACY_POLICY);
        if (indexOf != -1) {
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    TermsPolicyTextView.showTermsPolicyPage(context, WebViewActivity.getTermsUrl(), TermsPolicyTextView.TERMS_OF_USE);
                }

                public void updateDrawState(TextPaint textPaint) {
                    if (!ExperimentDataCenter.getInstance().shouldSeeNewSignUpScreen()) {
                        super.updateDrawState(textPaint);
                        textPaint.setUnderlineText(false);
                    }
                }
            }, indexOf, TERMS_OF_USE.length() + indexOf, 33);
        }
        if (indexOf2 != -1) {
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    TermsPolicyTextView.showTermsPolicyPage(context, WebViewActivity.getPrivacyPolicyUrl(), TermsPolicyTextView.PRIVACY_POLICY);
                }

                public void updateDrawState(TextPaint textPaint) {
                    if (!ExperimentDataCenter.getInstance().shouldSeeNewSignUpScreen()) {
                        super.updateDrawState(textPaint);
                        textPaint.setUnderlineText(false);
                    }
                }
            }, indexOf2, PRIVACY_POLICY.length() + indexOf2, 33);
        }
        return spannableString;
    }
}
