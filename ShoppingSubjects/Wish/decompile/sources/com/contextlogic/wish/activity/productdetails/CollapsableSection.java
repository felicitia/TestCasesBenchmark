package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.scrollview.ObservableScrollView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class CollapsableSection extends LinearLayout {
    private AutoReleasableImageView mChevronImage;
    protected WishAnalyticsEvent mCloseClickSectionEvent;
    /* access modifiers changed from: private */
    public LinearLayout mContentSection;
    protected WishAnalyticsEvent mOpenClickSectionEvent;
    private Resources mResources;
    /* access modifiers changed from: private */
    public ObservableScrollView mScrollView;
    /* access modifiers changed from: private */
    public View mSectionHeader;
    private ThemedTextView mSectionTitle;
    /* access modifiers changed from: private */
    public int mTargetHeight;

    public CollapsableSection(Context context) {
        this(context, null);
    }

    public CollapsableSection(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        setOrientation(1);
        View inflate = layoutInflater.inflate(R.layout.collapsable_section, this);
        this.mContentSection = (LinearLayout) inflate.findViewById(R.id.section_content);
        this.mChevronImage = (AutoReleasableImageView) inflate.findViewById(R.id.section_chevron);
        this.mSectionHeader = inflate.findViewById(R.id.section_header);
        this.mSectionTitle = (ThemedTextView) inflate.findViewById(R.id.section_title);
        this.mResources = WishApplication.getInstance().getResources();
        this.mSectionHeader.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (CollapsableSection.this.mContentSection.getVisibility() == 0) {
                    CollapsableSection.this.closeSectionWithAnimation();
                    if (CollapsableSection.this.mCloseClickSectionEvent != null) {
                        WishAnalyticsLogger.trackEvent(CollapsableSection.this.mCloseClickSectionEvent);
                        return;
                    }
                    return;
                }
                CollapsableSection.this.openSectionWithAnimation();
                if (CollapsableSection.this.mOpenClickSectionEvent != null) {
                    WishAnalyticsLogger.trackEvent(CollapsableSection.this.mOpenClickSectionEvent);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void setParentScrollView(ObservableScrollView observableScrollView) {
        this.mScrollView = observableScrollView;
    }

    /* access modifiers changed from: protected */
    public void openSection() {
        this.mContentSection.setVisibility(0);
        this.mChevronImage.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.chevron_flipped_up));
    }

    /* access modifiers changed from: protected */
    public void closeSection() {
        this.mContentSection.setVisibility(8);
        this.mChevronImage.setImageDrawable(WishApplication.getInstance().getResources().getDrawable(R.drawable.new_spinner_chevron));
    }

    /* access modifiers changed from: protected */
    public void openSectionWithAnimation() {
        final int[] iArr = new int[2];
        final int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        this.mScrollView.getLocationInWindow(iArr3);
        this.mContentSection.getLayoutParams().height = 1;
        this.mContentSection.setVisibility(0);
        final int height = iArr3[1] + this.mScrollView.getHeight();
        AnonymousClass2 r3 = new Animation() {
            public boolean willChangeBounds() {
                return true;
            }

            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                int i;
                int i2;
                LayoutParams layoutParams = CollapsableSection.this.mContentSection.getLayoutParams();
                double d = (double) f;
                if (d == 1.0d) {
                    i = -2;
                } else {
                    i = (int) (((float) CollapsableSection.this.mTargetHeight) * f);
                }
                layoutParams.height = i;
                if (d == 1.0d) {
                    i2 = CollapsableSection.this.mTargetHeight;
                } else {
                    i2 = CollapsableSection.this.mContentSection.getLayoutParams().height;
                }
                CollapsableSection.this.mContentSection.requestLayout();
                CollapsableSection.this.mSectionHeader.getLocationInWindow(iArr);
                CollapsableSection.this.mContentSection.getLocationInWindow(iArr2);
                if (CollapsableSection.this.mScrollView != null) {
                    final int i3 = (iArr2[1] + i2) - height;
                    if (i3 > 0) {
                        CollapsableSection.this.mScrollView.post(new Runnable() {
                            public void run() {
                                CollapsableSection.this.mScrollView.smoothScrollBy(0, i3);
                            }
                        });
                    }
                }
            }
        };
        r3.setDuration((long) ((((float) this.mTargetHeight) / this.mResources.getDisplayMetrics().density) + 100.0f));
        this.mContentSection.startAnimation(r3);
        this.mChevronImage.setImageDrawable(this.mResources.getDrawable(R.drawable.chevron_flipped_up));
    }

    /* access modifiers changed from: protected */
    public void closeSectionWithAnimation() {
        this.mContentSection.measure(-1, -2);
        AnonymousClass3 r0 = new Animation() {
            public boolean willChangeBounds() {
                return true;
            }

            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                if (f == 1.0f) {
                    CollapsableSection.this.mContentSection.setVisibility(8);
                    return;
                }
                CollapsableSection.this.mContentSection.getLayoutParams().height = CollapsableSection.this.mTargetHeight - ((int) (((float) CollapsableSection.this.mTargetHeight) * f));
                CollapsableSection.this.mContentSection.requestLayout();
            }
        };
        r0.setDuration((long) ((((float) this.mTargetHeight) / this.mResources.getDisplayMetrics().density) + 100.0f));
        this.mContentSection.startAnimation(r0);
        this.mChevronImage.setImageDrawable(this.mResources.getDrawable(R.drawable.new_spinner_chevron));
    }

    public void setTitle(String str) {
        this.mSectionTitle.setText(str);
    }

    public void addContent(View view) {
        this.mContentSection.addView(view);
    }

    public void setTargetHeight(int i) {
        this.mTargetHeight = i - this.mSectionHeader.getHeight();
    }
}
