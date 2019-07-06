package com.contextlogic.wish.activity.signup.redesign.SelectGender;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowBaseView;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView.FooterManager;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment.SignupPage;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class SelectGenderView extends SignupFlowBaseView implements FooterManager {
    private final String FEMALE_VALUE = "female";
    private final String MALE_VALUE = "male";
    private final String NEUTRAL_VALUE = "neutral";
    private boolean mClickedOnAGender;
    private AutoReleasableImageView mFemaleImage;
    private AutoReleasableImageView mFemaleImageHighlighter;
    /* access modifiers changed from: private */
    public boolean mFemaleSelected;
    private ThemedTextView mFemaleText;
    private SignupFlowFooterView mFooterView;
    private AutoReleasableImageView mMaleImage;
    private AutoReleasableImageView mMaleImageHighlighter;
    /* access modifiers changed from: private */
    public boolean mMaleSelected;
    private ThemedTextView mMaleText;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.redesign_signup_select_gender;
    }

    public SelectGenderView(FullScreenActivity fullScreenActivity, SignupFlowFragment signupFlowFragment) {
        super(fullScreenActivity, signupFlowFragment);
    }

    public void initializeLoadingContentView(View view) {
        this.mFooterView = (SignupFlowFooterView) findViewById(R.id.select_gender_footer_view);
        this.mMaleImage = (AutoReleasableImageView) findViewById(R.id.select_gender_male);
        this.mMaleImageHighlighter = (AutoReleasableImageView) findViewById(R.id.select_gender_male_highlight);
        this.mFemaleImage = (AutoReleasableImageView) findViewById(R.id.select_gender_female);
        this.mFemaleImageHighlighter = (AutoReleasableImageView) findViewById(R.id.select_gender_female_highlight);
        this.mMaleText = (ThemedTextView) findViewById(R.id.select_gender_male_text);
        this.mFemaleText = (ThemedTextView) findViewById(R.id.select_gender_female_text);
        this.mFooterView.setFooterManager(this);
        this.mClickedOnAGender = false;
        initInferredGender();
        setupFooter(SignupPage.SelectGender, this.mFooterView);
        Bundle savedInstanceState = this.mFragment.getSavedInstanceState(SignupPage.SelectGender.ordinal());
        if (savedInstanceState != null) {
            this.mMaleSelected = savedInstanceState.getBoolean("MaleSelected");
            this.mFemaleSelected = savedInstanceState.getBoolean("FemaleSelected");
        }
        float f = 0.0f;
        this.mMaleImageHighlighter.setAlpha(this.mMaleSelected ? 1.0f : 0.0f);
        AutoReleasableImageView autoReleasableImageView = this.mFemaleImageHighlighter;
        if (this.mFemaleSelected) {
            f = 1.0f;
        }
        autoReleasableImageView.setAlpha(f);
        ThemedTextView themedTextView = this.mMaleText;
        Resources resources = getContext().getResources();
        boolean z = this.mMaleSelected;
        int i = R.color.cool_gray3;
        themedTextView.setTextColor(resources.getColor(z ? R.color.main_primary : R.color.cool_gray3));
        ThemedTextView themedTextView2 = this.mFemaleText;
        Resources resources2 = getContext().getResources();
        if (this.mFemaleSelected) {
            i = R.color.main_primary;
        }
        themedTextView2.setTextColor(resources2.getColor(i));
        this.mMaleImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!SelectGenderView.this.mMaleSelected && SelectGenderView.this.mFemaleSelected) {
                    SelectGenderView.this.mMaleSelected = true;
                    SelectGenderView.this.mFemaleSelected = false;
                    SelectGenderView.this.animateToggle();
                }
            }
        });
        this.mFemaleImage.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SelectGenderView.this.mMaleSelected && !SelectGenderView.this.mFemaleSelected) {
                    SelectGenderView.this.mMaleSelected = false;
                    SelectGenderView.this.mFemaleSelected = true;
                    SelectGenderView.this.animateToggle();
                }
            }
        });
        markLoadingComplete();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_GENDER_IMPRESSION);
    }

    /* access modifiers changed from: private */
    public void animateToggle() {
        float f = 0.0f;
        this.mMaleImageHighlighter.animate().alpha(this.mMaleSelected ? 1.0f : 0.0f).setDuration(200);
        ViewPropertyAnimator animate = this.mFemaleImageHighlighter.animate();
        if (this.mFemaleSelected) {
            f = 1.0f;
        }
        animate.alpha(f).setDuration(200);
        ThemedTextView themedTextView = this.mMaleText;
        Resources resources = getContext().getResources();
        boolean z = this.mMaleSelected;
        int i = R.color.cool_gray3;
        themedTextView.setTextColor(resources.getColor(z ? R.color.main_primary : R.color.cool_gray3));
        ThemedTextView themedTextView2 = this.mFemaleText;
        Resources resources2 = getContext().getResources();
        if (this.mFemaleSelected) {
            i = R.color.main_primary;
        }
        themedTextView2.setTextColor(resources2.getColor(i));
        this.mClickedOnAGender = true;
    }

    public void handleFooterButtonClick() {
        this.mFragment.uploadProfileGender(getGenderSelected());
    }

    public String getGenderSelected() {
        if (this.mClickedOnAGender) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_SELECT_GENDER);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_SKIP_SELECT_GENDER);
        }
        if (this.mMaleSelected == this.mFemaleSelected) {
            return "neutral";
        }
        return this.mMaleSelected ? "male" : "female";
    }

    public void handleSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("MaleSelected", this.mMaleSelected);
        bundle2.putBoolean("FemaleSelected", this.mFemaleSelected);
        bundle.putBundle(this.mFragment.getPagerKey(SignupPage.SelectGender.ordinal()), bundle2);
    }

    private void initInferredGender() {
        if (((SignupFlowActivity) this.mFragment.getBaseActivity()).getSignupFlowContext().genderInferred == null || !((SignupFlowActivity) this.mFragment.getBaseActivity()).getSignupFlowContext().genderInferred.equals("1")) {
            this.mFemaleSelected = true;
            this.mMaleSelected = false;
            return;
        }
        this.mMaleSelected = true;
        this.mFemaleSelected = false;
    }
}
