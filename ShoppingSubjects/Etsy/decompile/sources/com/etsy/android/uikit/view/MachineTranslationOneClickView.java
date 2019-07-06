package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.t;
import com.etsy.android.uikit.util.MachineTranslationViewState;

public class MachineTranslationOneClickView extends MachineTranslationButton {
    public TextView mTranslatedContent;

    public MachineTranslationOneClickView(Context context) {
        super(context);
    }

    public MachineTranslationOneClickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MachineTranslationOneClickView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public MachineTranslationOneClickView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        super.init(context);
        this.mInactiveButtonColor = context.getResources().getColor(e.text_mid_grey);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mTranslatedContent = (TextView) findViewById(i.translated_content);
        super.onFinishInflate();
    }

    @Deprecated
    public void showButtonElements() {
        this.mTranslateButton.setVisibility(0);
    }

    @Deprecated
    public void hideAllElements() {
        this.mTranslateButton.setVisibility(8);
        this.mTranslationLoadingSpinner.setVisibility(8);
        this.mTranslationErrorMessage.setVisibility(8);
        this.mTranslatedContent.setVisibility(8);
    }

    public void setTranslatedStateWithString(String str) {
        setTranslatedState();
        this.mTranslatedContent.setVisibility(0);
        this.mTranslatedContent.setText(Html.fromHtml(str));
    }

    public void setTranslatedState() {
        super.setTranslatedState();
        this.mTranslateButton.setEnabled(false);
        this.mTranslateButton.setText(o.machine_translation_one_click_translated);
    }

    public void setUntranslatedState() {
        super.setUntranslatedState();
        this.mTranslatedContent.setText("");
        this.mTranslatedContent.setVisibility(8);
        this.mTranslateButton.setEnabled(true);
    }

    public void setListingTranslationState(boolean z, String str) {
        int i;
        int i2;
        if (z) {
            i = o.machine_translation_disclaimer;
            i2 = o.machine_translation_listing_untranslate;
        } else {
            i = o.machine_translation_explainer;
            i2 = o.machine_translation_listing_translate;
        }
        this.mTranslationDisclaimer.setText(i);
        this.mTranslateButton.setText(getResources().getString(i2, new Object[]{t.a(str)}));
        this.mTranslatedContent.setVisibility(8);
    }

    public void configureForStateAndMessage(@NonNull MachineTranslationViewState machineTranslationViewState, @Nullable String str) {
        if (!machineTranslationViewState.hasLoadedTranslation() || !af.b(str)) {
            setUntranslatedState();
        } else {
            setTranslatedStateWithString(str);
        }
        super.configureForState(machineTranslationViewState);
    }

    /* access modifiers changed from: protected */
    public void scaleElements() {
        super.scaleElements();
        this.mTranslatedContent.setTextSize(0, this.mTextSize);
    }
}
