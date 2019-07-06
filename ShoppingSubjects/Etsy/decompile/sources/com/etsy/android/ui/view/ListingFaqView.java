package com.etsy.android.ui.view;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.apiv3.FAQ;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.a.b;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.j;

public class ListingFaqView extends LinearLayout {
    private static final int ARROW_CLOSED_ORIENTATION = 0;
    private static final int ARROW_OPEN_ORIENTATION = 180;
    private static final String TAG = f.a(ListingFaqView.class);
    /* access modifiers changed from: private */
    public int mAnswerExpandedHeight = 0;
    @Nullable
    private View mFaqDivider;
    /* access modifiers changed from: private */
    public View mFaqHeader;
    /* access modifiers changed from: private */
    public ImageView mFaqIcon;
    /* access modifiers changed from: private */
    public boolean mIsExpanded = false;
    /* access modifiers changed from: private */
    public TextView mTxtFaqAnswer;
    private TextView mTxtFaqQuestion;

    public ListingFaqView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ListingFaqView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public ListingFaqView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public ListingFaqView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        inflate(context, R.layout.view_listing_faq, this);
        this.mFaqDivider = findViewById(R.id.faq_divider);
        this.mFaqHeader = findViewById(R.id.faq_header);
        this.mTxtFaqQuestion = (TextView) findViewById(R.id.txt_faq_question);
        this.mTxtFaqAnswer = (TextView) findViewById(R.id.txt_faq_answer);
        this.mFaqIcon = (ImageView) findViewById(R.id.img_faq_open);
        j.a(getViewTreeObserver(), (OnGlobalLayoutListener) new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                j.b(ListingFaqView.this.getViewTreeObserver(), (OnGlobalLayoutListener) this);
                ListingFaqView.this.mAnswerExpandedHeight = ListingFaqView.this.mTxtFaqAnswer.getMeasuredHeight();
                ListingFaqView.this.setExpanded(ListingFaqView.this.mIsExpanded);
            }
        });
    }

    public void setFaq(@NonNull FAQ faq, boolean z) {
        if (this.mFaqDivider != null) {
            this.mFaqDivider.setVisibility(z ? 0 : 8);
        }
        this.mTxtFaqQuestion.setText(faq.getQuestion());
        this.mTxtFaqAnswer.setText(faq.getAnswer());
    }

    /* access modifiers changed from: private */
    public void setExpanded(boolean z) {
        this.mIsExpanded = z;
        if (z) {
            this.mFaqHeader.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    ListingFaqView.this.mFaqHeader.setOnClickListener(null);
                    ListingFaqView.this.animateExpanded(false);
                }
            });
            this.mTxtFaqAnswer.setVisibility(0);
        } else {
            this.mFaqHeader.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    ListingFaqView.this.mFaqHeader.setOnClickListener(null);
                    ListingFaqView.this.animateExpanded(true);
                }
            });
            this.mTxtFaqAnswer.setVisibility(8);
        }
        this.mFaqIcon.setRotation(z ? 180.0f : 0.0f);
    }

    /* access modifiers changed from: private */
    public void animateExpanded(final boolean z) {
        int i;
        int i2 = 0;
        if (this.mTxtFaqAnswer.getHeight() > new l(getContext()).e()) {
            f.c(TAG, "Not animating since the View is larger than the window. In some cases this will cause a crash on texture size");
            setExpanded(false);
            return;
        }
        if (z) {
            i = 0;
        } else {
            i = this.mTxtFaqAnswer.getMeasuredHeight();
        }
        if (z) {
            i2 = this.mAnswerExpandedHeight;
        }
        b.a((View) this.mTxtFaqAnswer).a(i, i2).a((HardwareAnimatorListener) new HardwareAnimatorListener(this.mTxtFaqAnswer) {
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                ListingFaqView.this.mFaqIcon.setRotation(z ? 180.0f : 0.0f);
                if (z) {
                    ListingFaqView.this.mTxtFaqAnswer.setHeight(0);
                    ListingFaqView.this.mTxtFaqAnswer.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ListingFaqView.this.setExpanded(z);
            }
        }).b();
    }
}
