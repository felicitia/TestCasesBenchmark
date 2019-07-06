package com.etsy.android.uikit.messaging;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.etsy.android.lib.a.i;
import com.etsy.android.uikit.ui.toast.PersistentToastView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class EasyOptOutToastView extends PersistentToastView {
    private View mBtnDismiss;
    /* access modifiers changed from: private */
    public OnClickListener mOptOutClickListener;
    private TextView mTxtMessage;

    public EasyOptOutToastView(Context context) {
        super(context);
    }

    public EasyOptOutToastView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EasyOptOutToastView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mTxtMessage = (TextView) findViewById(i.message);
        this.mBtnDismiss = findViewById(i.dismiss_icon);
    }

    /* access modifiers changed from: 0000 */
    public void setTextContent(CharSequence charSequence, CharSequence charSequence2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence2);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (EasyOptOutToastView.this.mOptOutClickListener != null) {
                    EasyOptOutToastView.this.mOptOutClickListener.onClick(view);
                }
            }

            public void updateDrawState(@NonNull TextPaint textPaint) {
                textPaint.setUnderlineText(true);
            }
        }, 0, charSequence2.length(), 0);
        CharSequence concat = TextUtils.concat(new CharSequence[]{charSequence, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, spannableStringBuilder, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR});
        this.mTxtMessage.setMovementMethod(LinkMovementMethod.getInstance());
        this.mTxtMessage.setText(concat, BufferType.SPANNABLE);
    }

    /* access modifiers changed from: protected */
    public void setActionClickListener(OnClickListener onClickListener) {
        this.mOptOutClickListener = onClickListener;
    }

    /* access modifiers changed from: protected */
    public void setDismissClickListener(OnClickListener onClickListener) {
        this.mBtnDismiss.setOnClickListener(onClickListener);
    }
}
