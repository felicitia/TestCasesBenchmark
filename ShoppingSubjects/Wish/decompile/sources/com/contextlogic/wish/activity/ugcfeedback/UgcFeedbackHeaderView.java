package com.contextlogic.wish.activity.ugcfeedback;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.ArrayList;
import java.util.Iterator;

public class UgcFeedbackHeaderView extends RelativeLayout {
    private final int SPARKLE_ANIMATION_DURATION;
    private ArrayList<Animator> mAnims;
    private Context mContext;
    private LinearLayout mCounterContainer;
    private ThemedTextView mDescriptionText;
    private HeaderTheme mHeaderTheme;
    private AutoReleasableImageView mLeftSparkle;
    private int mNumElements;
    private AutoReleasableImageView mRightSparkle;
    private ThemedTextView mTimePeriodText;
    private int mTotalCount;

    public enum HeaderTheme {
        BLUE,
        YELLOW
    }

    public UgcFeedbackHeaderView(Context context) {
        this(context, null);
    }

    public UgcFeedbackHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.SPARKLE_ANIMATION_DURATION = 500;
        this.mContext = context;
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.ugc_feedback_dialog_header, this);
        this.mTimePeriodText = (ThemedTextView) inflate.findViewById(R.id.ugc_feedback_time_period_text);
        this.mCounterContainer = (LinearLayout) inflate.findViewById(R.id.ugc_feedback_counter_container);
        this.mDescriptionText = (ThemedTextView) inflate.findViewById(R.id.ugc_feedback_description_text);
        this.mRightSparkle = (AutoReleasableImageView) inflate.findViewById(R.id.ugc_feedback_right_sparkle);
        this.mLeftSparkle = (AutoReleasableImageView) inflate.findViewById(R.id.ugc_feedback_left_sparkle);
        this.mAnims = new ArrayList<>();
    }

    public void setup(int i, String str, String str2, boolean z) {
        this.mTotalCount = i;
        this.mTimePeriodText.setText(str.toUpperCase());
        this.mDescriptionText.setText(str2);
        char[] charArray = Integer.toString(i).toCharArray();
        this.mNumElements = charArray.length;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            UgcFeedbackCounterElementView ugcFeedbackCounterElementView = new UgcFeedbackCounterElementView(getContext());
            if (z) {
                ugcFeedbackCounterElementView.setCount("0");
            } else {
                ugcFeedbackCounterElementView.setCount(Character.toString(charArray[i2]));
            }
            if (this.mHeaderTheme == HeaderTheme.BLUE) {
                ugcFeedbackCounterElementView.setBackgroundResource(R.drawable.ugc_shadow_blue);
                ugcFeedbackCounterElementView.setTextColor(ContextCompat.getColor(this.mContext, R.color.blue_dark));
            } else {
                ugcFeedbackCounterElementView.setBackgroundResource(R.drawable.ugc_shadow_yellow);
                ugcFeedbackCounterElementView.setTextColor(ContextCompat.getColor(this.mContext, R.color.yellow_dark));
            }
            this.mCounterContainer.addView(ugcFeedbackCounterElementView);
        }
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public int getNumElements() {
        return this.mNumElements;
    }

    public void updateCounterElementTextAtIndex(int i, String str) {
        if (this.mCounterContainer.getChildAt(i) instanceof UgcFeedbackCounterElementView) {
            ((UgcFeedbackCounterElementView) this.mCounterContainer.getChildAt(i)).setCount(str);
        }
    }

    public void setTheme(HeaderTheme headerTheme) {
        this.mHeaderTheme = headerTheme;
    }

    public void reset() {
        if (this.mHeaderTheme == HeaderTheme.BLUE) {
            setBackgroundResource(R.drawable.ugc_dialog_blue_background);
        } else {
            setBackgroundResource(R.drawable.ugc_dialog_yellow_background);
        }
    }

    public void setContentVisibility(int i) {
        this.mCounterContainer.setVisibility(i);
        this.mDescriptionText.setVisibility(i);
        this.mTimePeriodText.setVisibility(i);
    }

    public void animateSparkles() {
        if (getResources() != null) {
            float dimension = getResources().getDimension(R.dimen.ugc_feedback_sparkle_translation);
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mRightSparkle, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat("translationY", new float[]{dimension, 0.0f})});
            ofPropertyValuesHolder.setStartDelay(500);
            ofPropertyValuesHolder.setDuration(500);
            ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this.mLeftSparkle, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofFloat("translationY", new float[]{dimension, 0.0f})});
            ofPropertyValuesHolder2.setStartDelay(500);
            ofPropertyValuesHolder2.setDuration(500);
            ofPropertyValuesHolder2.start();
            ofPropertyValuesHolder.start();
            this.mAnims.add(ofPropertyValuesHolder2);
            this.mAnims.add(ofPropertyValuesHolder2);
        }
    }

    public void clearAnimations() {
        if (this.mAnims != null && !this.mAnims.isEmpty()) {
            Iterator it = this.mAnims.iterator();
            while (it.hasNext()) {
                Animator animator = (Animator) it.next();
                animator.removeAllListeners();
                animator.cancel();
            }
            this.mAnims.clear();
        }
    }
}
