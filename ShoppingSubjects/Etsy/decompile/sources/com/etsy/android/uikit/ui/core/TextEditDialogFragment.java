package com.etsy.android.uikit.ui.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.uikit.view.CharacterCounterView;

public abstract class TextEditDialogFragment extends NetworkBaseDialogFragment {
    protected TextView mAfterDecimalText;
    protected TextView mBeforeDecimalText;
    protected CharacterCounterView mCharacterCounter;
    protected EditText mEditText;
    private TextView mErrorText;
    private View mHelpButton;
    private TextView mHelpSubtext;
    protected boolean mIsNumberText = false;

    /* access modifiers changed from: protected */
    @LayoutRes
    public int getLayoutRes() {
        return this.mIsNumberText ? k.fragment_text_edit_numeric : k.fragment_text_edit_sentences;
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(getLayoutRes(), viewGroup, false);
        this.mEditText = (EditText) inflate.findViewById(i.edit_text);
        this.mHelpSubtext = (TextView) inflate.findViewById(i.help_subtext);
        this.mErrorText = (TextView) inflate.findViewById(i.error_text);
        this.mHelpButton = inflate.findViewById(i.help_button);
        this.mCharacterCounter = (CharacterCounterView) inflate.findViewById(i.character_counter);
        if (this.mIsNumberText) {
            this.mBeforeDecimalText = (TextView) inflate.findViewById(i.before_decimal_text);
            this.mAfterDecimalText = (TextView) inflate.findViewById(i.after_decimal_text);
        }
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void setEditableText(String str) {
        this.mEditText.setText("");
        if (str != null) {
            this.mEditText.append(str);
        }
    }

    /* access modifiers changed from: protected */
    public void setHintText(int i) {
        this.mEditText.setHint(i);
    }

    /* access modifiers changed from: protected */
    public void setHelpSubtext(String str) {
        this.mHelpSubtext.setText(str);
        this.mHelpSubtext.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void setError(String str) {
        this.mErrorText.setText(str);
        this.mErrorText.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void setMaxChars(int i) {
        this.mCharacterCounter.setObservable(this.mEditText);
        this.mCharacterCounter.setMaxChars(i);
        this.mCharacterCounter.setVisibility(0);
    }

    /* access modifiers changed from: protected */
    public void enableHelpDialogButton(OnClickListener onClickListener) {
        this.mHelpButton.setVisibility(0);
        this.mHelpButton.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: protected */
    public String getEditedText() {
        return this.mEditText.getText().toString().trim();
    }
}
