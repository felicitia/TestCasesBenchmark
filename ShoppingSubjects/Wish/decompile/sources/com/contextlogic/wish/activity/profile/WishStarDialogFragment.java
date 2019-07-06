package com.contextlogic.wish.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.ui.button.ThemedButton;

public class WishStarDialogFragment extends BaseDialogFragment {
    private TextView mAnswer;
    private ImageView mCancelButton;
    private TextView mQuestion;
    private TextView mUserName;
    private ThemedButton mViewProfileButton;
    private TextView mWishStarDescription;
    private String name;
    private boolean popup;

    public static WishStarDialogFragment createWishStarDialogFragment(String str) {
        WishStarDialogFragment wishStarDialogFragment = new WishStarDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ArgumentUser", str);
        bundle.putBoolean("ArgumentPopup", false);
        wishStarDialogFragment.setArguments(bundle);
        return wishStarDialogFragment;
    }

    public static WishStarDialogFragment createOneTimePopup() {
        WishStarDialogFragment wishStarDialogFragment = new WishStarDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("ArgumentPopup", true);
        wishStarDialogFragment.setArguments(bundle);
        return wishStarDialogFragment;
    }

    private boolean initializeState() {
        Bundle arguments = getArguments();
        this.name = arguments.getString("ArgumentUser");
        this.popup = arguments.getBoolean("ArgumentPopup");
        if (!this.popup && TextUtils.isEmpty(this.name)) {
            return false;
        }
        return true;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (!initializeState()) {
            return null;
        }
        View inflate = layoutInflater.inflate(R.layout.profile_wish_star_dialog, viewGroup, false);
        if (this.popup) {
            inflate.findViewById(R.id.popup_container).setVisibility(0);
            inflate.findViewById(R.id.profile_container).setVisibility(8);
            this.mViewProfileButton = (ThemedButton) inflate.findViewById(R.id.wish_star_view_badge);
            this.mViewProfileButton.setAllCaps(false);
            this.mViewProfileButton.setText(getString(R.string.wish_star_view_badge));
            this.mViewProfileButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishStarDialogFragment.this.withActivity(new ActivityTask<BaseActivity>() {
                        public void performTask(BaseActivity baseActivity) {
                            Intent intent = new Intent();
                            intent.setClass(baseActivity, ProfileActivity.class);
                            baseActivity.startActivity(intent);
                        }
                    });
                }
            });
            this.mCancelButton = (ImageView) inflate.findViewById(R.id.wish_star_dialog_cancel_popup);
            this.mCancelButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishStarDialogFragment.this.cancel();
                }
            });
        } else {
            inflate.findViewById(R.id.profile_container).setVisibility(0);
            inflate.findViewById(R.id.popup_container).setVisibility(8);
            this.mCancelButton = (ImageView) inflate.findViewById(R.id.wish_star_dialog_cancel);
            this.mCancelButton.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    WishStarDialogFragment.this.cancel();
                }
            });
            this.mUserName = (TextView) inflate.findViewById(R.id.wish_star_user_name);
            this.mUserName.setText(getString(R.string.user_is_wish_star, this.name));
            this.mWishStarDescription = (TextView) inflate.findViewById(R.id.wish_star_description);
            this.mWishStarDescription.setText(getString(R.string.wish_star_description));
            this.mQuestion = (TextView) inflate.findViewById(R.id.wish_star_question);
            this.mQuestion.setText(getString(R.string.wish_star_question));
            this.mAnswer = (TextView) inflate.findViewById(R.id.wish_star_answer);
            this.mAnswer.setText(getString(R.string.wish_star_answer));
        }
        return inflate;
    }
}
