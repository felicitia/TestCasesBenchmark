package com.contextlogic.wish.activity.feed.dealdash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.dialog.BaseDialogFragment;

public class DealDashHelpDialogFragment extends BaseDialogFragment {
    private ImageView mCancelButton;
    private ImageView mLeftButton;
    private TextView mPageText;
    private ImageView mRightButton;
    /* access modifiers changed from: private */
    public int mState;
    private View mStep1View;
    private View mStep2View;

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.deal_dash_help_dialog_fragment, viewGroup, false);
        this.mCancelButton = (ImageView) inflate.findViewById(R.id.deal_dash_educational_modal_cancel_button);
        this.mCancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DealDashHelpDialogFragment.this.cancel();
            }
        });
        this.mStep1View = inflate.findViewById(R.id.deal_dash_educational_modal_1);
        this.mStep2View = inflate.findViewById(R.id.deal_dash_educational_modal_2);
        this.mLeftButton = (ImageView) inflate.findViewById(R.id.deal_dash_educational_modal_left_button);
        this.mLeftButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DealDashHelpDialogFragment.this.mState = 1;
                DealDashHelpDialogFragment.this.refreshView();
            }
        });
        this.mRightButton = (ImageView) inflate.findViewById(R.id.deal_dash_educational_modal_right_button);
        this.mRightButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DealDashHelpDialogFragment.this.mState = 2;
                DealDashHelpDialogFragment.this.refreshView();
            }
        });
        this.mPageText = (TextView) inflate.findViewById(R.id.deal_dash_educational_modal_page_text);
        this.mState = 1;
        refreshView();
        return inflate;
    }

    /* access modifiers changed from: private */
    public void refreshView() {
        if (this.mState == 1) {
            this.mLeftButton.setVisibility(8);
            this.mRightButton.setVisibility(0);
            this.mStep1View.setVisibility(0);
            this.mStep2View.setVisibility(8);
        } else {
            this.mLeftButton.setVisibility(0);
            this.mRightButton.setVisibility(8);
            this.mStep1View.setVisibility(8);
            this.mStep2View.setVisibility(0);
        }
        this.mPageText.setText(String.format(getContext().getString(R.string.add_to_cart_modal_item_number), new Object[]{Integer.valueOf(this.mState), Integer.valueOf(2)}));
    }
}
