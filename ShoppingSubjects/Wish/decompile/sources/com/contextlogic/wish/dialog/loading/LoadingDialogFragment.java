package com.contextlogic.wish.dialog.loading;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.dialog.prompt.PromptDialogFragment;
import com.contextlogic.wish.util.ValueUtil;

public class LoadingDialogFragment extends PromptDialogFragment {
    private View mProgressView;

    public int getDimColor() {
        return R.color.white_dialog_dim;
    }

    public boolean isCancelable() {
        return false;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.loading_dialog_fragment, viewGroup, false);
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            this.mProgressView = inflate.findViewById(R.id.loading_dialog_fragment_three_dot_progress);
        } else {
            this.mProgressView = inflate.findViewById(R.id.loading_dialog_fragment_progress);
        }
        this.mProgressView.setVisibility(0);
        return inflate;
    }

    public int getDialogWidth() {
        return (int) ValueUtil.convertDpToPx(getResources().getDimension(R.dimen.progress_bar_medium));
    }

    public void onResume() {
        super.onResume();
        this.mProgressView.setVisibility(8);
        this.mProgressView.setVisibility(0);
    }
}
