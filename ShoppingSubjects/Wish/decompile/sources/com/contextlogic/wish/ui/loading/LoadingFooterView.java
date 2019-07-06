package com.contextlogic.wish.ui.loading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.ui.button.ThemedButton;
import com.contextlogic.wish.ui.text.ThemedTextView;

public class LoadingFooterView extends FrameLayout {
    private LoadingFooterViewCallback mCallback;
    private View mContentContainer;
    private View mLoadingSpinner;
    private ThemedTextView mNoMoreItemsText;
    private View mPlaceholder;
    private boolean mReserveSpaceWhenHidden;
    private ThemedButton mTapToLoadButton;
    private TapToLoadStyle mTapToLoadStyle;
    private TextView mTapToLoadText;
    private VisibilityMode mVisibilityMode;

    public interface LoadingFooterViewCallback {
        void onTapToLoad();
    }

    public enum TapToLoadStyle {
        TEXT,
        SOLID_BUTTON
    }

    public enum VisibilityMode {
        HIDDEN,
        LOADING,
        TAP_TO_LOAD,
        NO_MORE_ITEMS
    }

    public LoadingFooterView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.loading_footer, this);
        this.mReserveSpaceWhenHidden = true;
        this.mVisibilityMode = VisibilityMode.HIDDEN;
        this.mTapToLoadStyle = TapToLoadStyle.TEXT;
        this.mTapToLoadText = (TextView) inflate.findViewById(R.id.loading_footer_tap_to_load_text);
        this.mTapToLoadButton = (ThemedButton) inflate.findViewById(R.id.loading_footer_tap_to_load_button);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            this.mLoadingSpinner = inflate.findViewById(R.id.loading_footer_three_dot_loading_spinner);
        } else {
            this.mLoadingSpinner = inflate.findViewById(R.id.loading_footer_loading_spinner);
        }
        this.mLoadingSpinner.setVisibility(0);
        this.mContentContainer = inflate.findViewById(R.id.loading_footer_container);
        this.mContentContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoadingFooterView.this.handleTapToLoad();
            }
        });
        this.mTapToLoadButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoadingFooterView.this.handleTapToLoad();
            }
        });
        this.mPlaceholder = inflate.findViewById(R.id.loading_footer_place_holder);
        this.mNoMoreItemsText = (ThemedTextView) inflate.findViewById(R.id.loading_footer_no_more_items_text);
    }

    private void hideAllVisibilityModes() {
        this.mTapToLoadText.setVisibility(8);
        this.mTapToLoadButton.setVisibility(8);
        this.mLoadingSpinner.setVisibility(8);
        this.mPlaceholder.setVisibility(8);
        this.mNoMoreItemsText.setVisibility(8);
    }

    public void setCallback(LoadingFooterViewCallback loadingFooterViewCallback) {
        this.mCallback = loadingFooterViewCallback;
    }

    public void setVisibilityMode(VisibilityMode visibilityMode) {
        this.mVisibilityMode = visibilityMode;
        refreshVisibilityMode();
    }

    private void refreshVisibilityMode() {
        hideAllVisibilityModes();
        switch (this.mVisibilityMode) {
            case LOADING:
                this.mContentContainer.setVisibility(0);
                this.mLoadingSpinner.setVisibility(0);
                break;
            case TAP_TO_LOAD:
                this.mContentContainer.setVisibility(0);
                switch (this.mTapToLoadStyle) {
                    case TEXT:
                        this.mTapToLoadText.setVisibility(0);
                        break;
                    case SOLID_BUTTON:
                        this.mTapToLoadButton.setVisibility(0);
                        break;
                }
            case HIDDEN:
                if (!this.mReserveSpaceWhenHidden) {
                    this.mContentContainer.setVisibility(8);
                    break;
                } else {
                    this.mContentContainer.setVisibility(0);
                    this.mPlaceholder.setVisibility(0);
                    break;
                }
            case NO_MORE_ITEMS:
                this.mNoMoreItemsText.setVisibility(0);
                break;
        }
        if (this.mVisibilityMode != VisibilityMode.LOADING && (this.mLoadingSpinner instanceof ThreeDotProgressBar)) {
            ((ThreeDotProgressBar) this.mLoadingSpinner).reset();
            this.mLoadingSpinner.setVisibility(8);
        }
    }

    public void setReserveSpaceWhenHidden(boolean z) {
        this.mReserveSpaceWhenHidden = z;
    }

    public void setNoMoreItemsText(String str) {
        this.mNoMoreItemsText.setText(str);
    }

    public void setTapToLoadText(String str) {
        this.mTapToLoadText.setText(str);
        this.mTapToLoadButton.setText(str);
    }

    /* access modifiers changed from: private */
    public void handleTapToLoad() {
        if (this.mVisibilityMode == VisibilityMode.TAP_TO_LOAD && this.mCallback != null) {
            this.mCallback.onTapToLoad();
        }
    }

    public void setTapToLoadStyle(TapToLoadStyle tapToLoadStyle) {
        this.mTapToLoadStyle = tapToLoadStyle;
    }
}
