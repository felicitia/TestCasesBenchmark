package com.etsy.android.uikit.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.q;

public class SwitchToggle extends LinearLayout implements Checkable {
    /* access modifiers changed from: private */
    public a mOnCheckedChangeListener;
    /* access modifiers changed from: private */
    public ToggleButton mToggleOff;
    /* access modifiers changed from: private */
    public ToggleButton mToggleOn;

    public interface a {
        void a(SwitchToggle switchToggle, boolean z);
    }

    public SwitchToggle(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public SwitchToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0, 0);
    }

    public SwitchToggle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i, 0);
    }

    @TargetApi(21)
    public SwitchToggle(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        setOrientation(0);
        inflate(getContext(), k.switch_toggle, this);
        this.mToggleOn = (ToggleButton) findViewById(i.toggle_on);
        this.mToggleOff = (ToggleButton) findViewById(i.toggle_off);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, q.SwitchToggle, i, i2);
            CharSequence text = obtainStyledAttributes.getText(q.SwitchToggle_android_textOn);
            this.mToggleOn.setTextOn(text);
            this.mToggleOn.setTextOff(text);
            CharSequence text2 = obtainStyledAttributes.getText(q.SwitchToggle_android_textOff);
            this.mToggleOff.setTextOn(text2);
            this.mToggleOff.setTextOff(text2);
            obtainStyledAttributes.recycle();
        }
        this.mToggleOn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (SwitchToggle.this.mOnCheckedChangeListener != null) {
                    SwitchToggle.this.mOnCheckedChangeListener.a(SwitchToggle.this, z);
                }
                SwitchToggle.this.mToggleOn.setClickable(!z);
                SwitchToggle.this.mToggleOff.setClickable(z);
                SwitchToggle.this.mToggleOff.setChecked(!z);
            }
        });
        this.mToggleOff.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (SwitchToggle.this.mOnCheckedChangeListener != null) {
                    SwitchToggle.this.mOnCheckedChangeListener.a(SwitchToggle.this, !z);
                }
                SwitchToggle.this.mToggleOn.setClickable(z);
                SwitchToggle.this.mToggleOff.setClickable(!z);
                SwitchToggle.this.mToggleOn.setChecked(!z);
            }
        });
    }

    public void setChecked(boolean z) {
        this.mToggleOn.setChecked(z);
        this.mToggleOff.setChecked(!z);
    }

    public boolean isChecked() {
        return this.mToggleOn.isChecked();
    }

    public void toggle() {
        this.mToggleOn.toggle();
        this.mToggleOff.toggle();
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.mOnCheckedChangeListener = aVar;
    }
}
