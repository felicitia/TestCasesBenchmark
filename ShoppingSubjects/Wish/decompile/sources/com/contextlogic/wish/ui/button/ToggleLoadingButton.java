package com.contextlogic.wish.ui.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.ui.loading.PrimaryProgressBar;
import com.contextlogic.wish.ui.text.ThemedTextView;

public abstract class ToggleLoadingButton extends FrameLayout {
    protected PrimaryProgressBar buttonProgress;
    protected ThemedTextView buttonText;
    protected ButtonMode mButtonMode;
    /* access modifiers changed from: private */
    public OnToggleLoadingButtonClickListener mOnToggleLoadingButtonClickListener;

    public enum ButtonMode {
        Unselected,
        UnselectedLoading,
        Selected,
        SelectedLoading
    }

    public interface OnToggleLoadingButtonClickListener {
        void onToggleLoadingButtonClicked(ToggleLoadingButton toggleLoadingButton, boolean z);
    }

    public ToggleLoadingButton(Context context) {
        this(context, null);
    }

    public ToggleLoadingButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ToggleLoadingButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        View inflate = inflate(getContext(), R.layout.toggle_loading_button, this);
        setLayoutParams(new LayoutParams(-1, -1));
        this.buttonText = (ThemedTextView) inflate.findViewById(R.id.toggle_loading_button_text);
        this.buttonProgress = (PrimaryProgressBar) inflate.findViewById(R.id.toggle_loading_button_progress_bar);
        setButtonMode(ButtonMode.Unselected);
        setClickable(true);
    }

    public ButtonMode getButtonMode() {
        return this.mButtonMode;
    }

    public void setButtonMode(ButtonMode buttonMode) {
        this.mButtonMode = buttonMode;
        switch (this.mButtonMode) {
            case Unselected:
                onUnselectedModeSet();
                return;
            case Selected:
                onSelectedModeSet();
                return;
            case UnselectedLoading:
                setBackgroundResource(R.drawable.main_button_selector);
                this.buttonText.setVisibility(8);
                this.buttonProgress.setColorFilter(R.color.white);
                this.buttonProgress.setVisibility(0);
                return;
            case SelectedLoading:
                setBackgroundResource(R.drawable.gray_button_selector);
                this.buttonText.setVisibility(8);
                this.buttonProgress.setColorFilter(R.color.main_primary);
                this.buttonProgress.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onUnselectedModeSet() {
        setBackgroundResource(R.drawable.main_button_selector);
        this.buttonProgress.setVisibility(8);
        this.buttonText.setVisibility(0);
        this.buttonText.setTextColor(getResources().getColor(R.color.follow_button_text_follow));
    }

    /* access modifiers changed from: protected */
    public void onSelectedModeSet() {
        setBackgroundResource(R.drawable.user_list_gray_button_selector);
        this.buttonProgress.setVisibility(8);
        this.buttonText.setVisibility(0);
        this.buttonText.setTextColor(getResources().getColor(R.color.follow_button_text_following));
    }

    public CharSequence getText() {
        return this.buttonText.getText();
    }

    public void setText(CharSequence charSequence) {
        this.buttonText.setText(charSequence);
    }

    public void setTypeface(int i) {
        this.buttonText.setTypeface(i);
    }

    public void setOnFollowButtonClickListener(OnToggleLoadingButtonClickListener onToggleLoadingButtonClickListener) {
        this.mOnToggleLoadingButtonClickListener = onToggleLoadingButtonClickListener;
    }

    public boolean performClick() {
        performToggleLoadingButtonClicked();
        return super.performClick();
    }

    /* access modifiers changed from: protected */
    public void performToggleLoadingButtonClicked() {
        if (this.mButtonMode == ButtonMode.Unselected) {
            post(new Runnable() {
                public void run() {
                    if (ToggleLoadingButton.this.mOnToggleLoadingButtonClickListener != null) {
                        ToggleLoadingButton.this.mOnToggleLoadingButtonClickListener.onToggleLoadingButtonClicked(ToggleLoadingButton.this, true);
                    }
                }
            });
        } else if (this.mButtonMode == ButtonMode.Selected) {
            post(new Runnable() {
                public void run() {
                    if (ToggleLoadingButton.this.mOnToggleLoadingButtonClickListener != null) {
                        ToggleLoadingButton.this.mOnToggleLoadingButtonClickListener.onToggleLoadingButtonClicked(ToggleLoadingButton.this, false);
                    }
                }
            });
        }
    }
}
