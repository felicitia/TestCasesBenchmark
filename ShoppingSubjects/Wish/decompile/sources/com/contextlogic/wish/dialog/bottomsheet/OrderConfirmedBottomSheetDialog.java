package com.contextlogic.wish.dialog.bottomsheet;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.ui.image.NetworkImageView;

public class OrderConfirmedBottomSheetDialog extends SuccessBottomSheetDialog {
    protected static long DEFAULT_DELAY = 20000;
    private TextView mAddress;
    private TextView mEmail;
    private NetworkImageView mImage;
    private TextView mTitle;

    protected OrderConfirmedBottomSheetDialog(BaseActivity baseActivity) {
        super(baseActivity);
    }

    public static OrderConfirmedBottomSheetDialog create(BaseActivity baseActivity) {
        OrderConfirmedBottomSheetDialog orderConfirmedBottomSheetDialog = new OrderConfirmedBottomSheetDialog(baseActivity);
        BottomSheetUtil.expandDialogFully(orderConfirmedBottomSheetDialog);
        return orderConfirmedBottomSheetDialog;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        setContentView((int) R.layout.order_confirmed_bottom_sheet);
        this.mImage = (NetworkImageView) findViewById(R.id.order_confirmed_bottom_sheet_image);
        this.mTitle = (TextView) findViewById(R.id.order_confirmed_bottom_sheet_title);
        this.mEmail = (TextView) findViewById(R.id.order_confirmed_bottom_sheet_email);
        this.mAddress = (TextView) findViewById(R.id.order_confirmed_bottom_sheet_address);
    }

    public OrderConfirmedBottomSheetDialog setOrderConfirmedInformation(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.mEmail.setText(str);
            this.mAddress.setText(str2);
        }
        return this;
    }

    public OrderConfirmedBottomSheetDialog setImageUrl(String str) {
        if (this.mImage == null) {
            return this;
        }
        if (TextUtils.isEmpty(str)) {
            this.mImage.setVisibility(8);
        } else {
            this.mImage.setVisibility(0);
            this.mImage.setImageUrl(str);
        }
        return this;
    }

    public OrderConfirmedBottomSheetDialog setTitle(String str) {
        if (this.mTitle != null && !TextUtils.isEmpty(str)) {
            this.mTitle.setText(str);
            this.mTitle.setVisibility(0);
        }
        return this;
    }

    public OrderConfirmedBottomSheetDialog autoDismiss() {
        return hideAfter(DEFAULT_DELAY);
    }

    public OrderConfirmedBottomSheetDialog hideAfter(long j) {
        if (j > 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    OrderConfirmedBottomSheetDialog.this.dismiss();
                }
            }, j);
        }
        return this;
    }
}
