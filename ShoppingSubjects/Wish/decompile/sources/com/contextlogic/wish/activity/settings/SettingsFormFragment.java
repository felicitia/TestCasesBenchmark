package com.contextlogic.wish.activity.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.LoadingUiFragment;

public abstract class SettingsFormFragment<A extends BaseActivity> extends LoadingUiFragment<A> {
    protected Button mButton;

    public boolean canPullToRefresh() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean enableButtonByDefault() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract int getContentLayoutResourceId();

    public int getLoadingContentLayoutResourceId() {
        return R.layout.settings_form_fragment;
    }

    public void handleReload() {
    }

    public boolean hasItems() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void initializeContent(View view);

    /* access modifiers changed from: protected */
    public abstract void onSaveButtonClicked();

    public void initializeLoadingContentView(View view) {
        this.mButton = (Button) view.findViewById(R.id.settings_form_save_button);
        setButtonState(enableButtonByDefault());
        this.mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SettingsFormFragment.this.onSaveButtonClicked();
            }
        });
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.settings_form_scrollview);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        if (layoutInflater != null) {
            View inflate = layoutInflater.inflate(getContentLayoutResourceId(), scrollView);
            if (inflate != null) {
                initializeContent(inflate);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void setButtonState(boolean z) {
        if (this.mButton != null) {
            this.mButton.setEnabled(z);
        }
    }
}
