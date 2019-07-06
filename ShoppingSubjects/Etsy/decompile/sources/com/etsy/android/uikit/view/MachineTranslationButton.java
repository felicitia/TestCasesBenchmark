package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.a.q;
import com.etsy.android.lib.util.af;
import com.etsy.android.stylekit.CompoundVectorTextView;
import com.etsy.android.uikit.util.MachineTranslationViewState;

public class MachineTranslationButton extends RelativeLayout {
    protected int mActiveButtonColor;
    private String mDisclaimerText;
    protected int mInactiveButtonColor;
    protected float mTextSize;
    protected CompoundVectorTextView mTranslateButton;
    protected TextView mTranslationDisclaimer;
    protected TextView mTranslationErrorMessage;
    protected LoadingIndicatorView mTranslationLoadingSpinner;

    public interface a {
        MachineTranslationViewState getTranslationState();

        boolean isTranslationEligible();
    }

    public MachineTranslationButton(Context context) {
        super(context);
        init(context);
        initFromAttrs(context, null);
    }

    public MachineTranslationButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        initFromAttrs(context, attributeSet);
    }

    public MachineTranslationButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
        initFromAttrs(context, attributeSet);
    }

    @TargetApi(21)
    public MachineTranslationButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
        initFromAttrs(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        this.mTextSize = (float) context.getResources().getDimensionPixelSize(f.text_medium);
        this.mActiveButtonColor = context.getResources().getColor(e.blue);
        this.mInactiveButtonColor = context.getResources().getColor(e.blue);
    }

    /* access modifiers changed from: protected */
    public void initFromAttrs(Context context, AttributeSet attributeSet) {
        inflate(context, k.machine_translation_one_click, this);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, q.MachineTranslationOneClickView, 0, 0);
        if (obtainStyledAttributes != null) {
            try {
                this.mTextSize = (float) obtainStyledAttributes.getDimensionPixelSize(q.MachineTranslationOneClickView_textSize, (int) this.mTextSize);
                this.mActiveButtonColor = obtainStyledAttributes.getColor(q.MachineTranslationOneClickView_activeButtonColor, this.mActiveButtonColor);
                this.mInactiveButtonColor = obtainStyledAttributes.getColor(q.MachineTranslationOneClickView_inactiveButtonColor, this.mInactiveButtonColor);
                this.mDisclaimerText = obtainStyledAttributes.getString(q.MachineTranslationOneClickView_disclaimerText);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mTranslateButton = (CompoundVectorTextView) findViewById(i.translate_button);
        this.mTranslationDisclaimer = (TextView) findViewById(i.translation_disclaimer);
        if (this.mDisclaimerText != null) {
            this.mTranslationDisclaimer.setText(this.mDisclaimerText);
        }
        this.mTranslationLoadingSpinner = (LoadingIndicatorView) findViewById(i.loading_translation_spinner);
        this.mTranslationErrorMessage = (TextView) findViewById(i.machine_translation_error);
        scaleElements();
        setTranslateButtonColor(this.mActiveButtonColor);
        super.onFinishInflate();
    }

    public void showSpinner() {
        this.mTranslationLoadingSpinner.setVisibility(0);
    }

    public void hideSpinner() {
        this.mTranslationLoadingSpinner.setVisibility(8);
    }

    public void showErrorMessage() {
        this.mTranslationErrorMessage.setVisibility(0);
    }

    public void hideErrorMessage() {
        this.mTranslationErrorMessage.setVisibility(8);
    }

    public void showDisclaimer() {
        this.mTranslationDisclaimer.setVisibility(0);
    }

    public void setTranslatedState() {
        setTranslateButtonColor(this.mInactiveButtonColor);
        this.mTranslateButton.setText(getResources().getString(o.untranslate));
        if (af.b(this.mDisclaimerText)) {
            this.mTranslationDisclaimer.setVisibility(0);
        }
    }

    public void setUntranslatedState() {
        setTranslateButtonColor(this.mActiveButtonColor);
        this.mTranslateButton.setText(o.machine_translation_one_click_translate);
        this.mTranslationDisclaimer.setVisibility(8);
    }

    public void configureForState(@NonNull MachineTranslationViewState machineTranslationViewState) {
        if (!machineTranslationViewState.hasLoadedTranslation() || machineTranslationViewState.isShowingOriginal()) {
            setUntranslatedState();
        } else {
            setTranslatedState();
        }
        if (machineTranslationViewState.isLoadingTranslation()) {
            showSpinner();
        } else {
            hideSpinner();
        }
        if (machineTranslationViewState.errorOccurredLoadingTranslation()) {
            showErrorMessage();
        } else {
            hideErrorMessage();
        }
    }

    /* access modifiers changed from: protected */
    public void setTranslateButtonColor(int i) {
        this.mTranslateButton.setTextColor(i);
        this.mTranslateButton.setDrawableTint(i);
    }

    /* access modifiers changed from: protected */
    public void scaleElements() {
        this.mTranslateButton.setTextSize(0, this.mTextSize);
        this.mTranslationErrorMessage.setTextSize(0, this.mTextSize);
        this.mTranslationLoadingSpinner.getLayoutParams().height = (int) this.mTextSize;
        this.mTranslationDisclaimer.setTextSize(0, this.mTextSize);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mTranslateButton.setOnClickListener(onClickListener);
    }
}
