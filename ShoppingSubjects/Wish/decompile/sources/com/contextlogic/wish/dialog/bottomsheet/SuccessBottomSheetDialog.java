package com.contextlogic.wish.dialog.bottomsheet;

import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class SuccessBottomSheetDialog extends BottomSheetDialog {
    protected static long DEFAULT_DELAY = 3000;
    private View mCheckmark;
    private SuccessBottomSheetDialogDismissCallback mDismissCallback;
    private NetworkImageView mImageView;
    private View mSheet;
    private TextView mSubtitle;
    protected TextView mTitle;

    public interface SuccessBottomSheetDialogDismissCallback {
        void onDismiss();
    }

    SuccessBottomSheetDialog(BaseActivity baseActivity) {
        super(baseActivity);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        setContentView((int) R.layout.success_bottom_sheet);
        this.mSheet = findViewById(R.id.success_bottom_sheet_parent);
        this.mSheet.setBackground(new ColorDrawable(getContext().getResources().getColor(R.color.white)));
        this.mTitle = (TextView) findViewById(R.id.success_bottom_sheet_title);
        this.mSubtitle = (TextView) findViewById(R.id.success_bottom_sheet_subtitle);
        this.mCheckmark = findViewById(R.id.success_bottom_sheet_check);
        this.mImageView = (NetworkImageView) findViewById(R.id.success_bottom_sheet_custom_image);
    }

    public static SuccessBottomSheetDialog create(BaseActivity baseActivity) {
        SuccessBottomSheetDialog successBottomSheetDialog = new SuccessBottomSheetDialog(baseActivity);
        BottomSheetUtil.expandDialogFully(successBottomSheetDialog);
        return successBottomSheetDialog;
    }

    public SuccessBottomSheetDialog setTitle(String str) {
        if (this.mTitle != null && !TextUtils.isEmpty(str)) {
            this.mTitle.setText(str);
            this.mTitle.setVisibility(0);
        }
        return this;
    }

    public SuccessBottomSheetDialog setImage(WishImage wishImage) {
        if (!(this.mImageView == null || wishImage == null)) {
            hideCheckImage();
            this.mImageView.setVisibility(0);
            this.mImageView.setImage(wishImage);
        }
        return this;
    }

    public SuccessBottomSheetDialog setMessage(String str) {
        if (this.mSubtitle != null && !TextUtils.isEmpty(str)) {
            this.mSubtitle.setText(str);
            this.mSubtitle.setVisibility(0);
            this.mSubtitle.setGravity(17);
        }
        return this;
    }

    public SuccessBottomSheetDialog setSubtitleTextColor(int i) {
        if (this.mSubtitle != null) {
            this.mSubtitle.setTextColor(i);
        }
        return this;
    }

    public SuccessBottomSheetDialog hideCheckImage() {
        if (this.mCheckmark != null) {
            this.mCheckmark.setVisibility(8);
        }
        return this;
    }

    public SuccessBottomSheetDialog autoDismiss() {
        return hideAfter(DEFAULT_DELAY);
    }

    public SuccessBottomSheetDialog hideAfter(long j) {
        if (j > 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    SuccessBottomSheetDialog.this.dismiss();
                }
            }, j);
        }
        return this;
    }

    public SuccessBottomSheetDialog setDismissCallback(SuccessBottomSheetDialogDismissCallback successBottomSheetDialogDismissCallback) {
        this.mDismissCallback = successBottomSheetDialogDismissCallback;
        return this;
    }

    public SuccessBottomSheetDialog setClickCallback(OnClickListener onClickListener) {
        this.mSheet.setOnClickListener(onClickListener);
        return this;
    }

    public void dismiss() {
        if (this.mDismissCallback != null) {
            this.mDismissCallback.onDismiss();
        }
        super.dismiss();
    }
}
