package com.contextlogic.wish.activity.referralprogram;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.commercecash.CommerceCashActivity;
import com.contextlogic.wish.activity.menu.MenuAdapter;
import com.contextlogic.wish.dialog.bottomsheet.BottomSheetUtil;

public class WhatsWishCashBottomSheet extends BottomSheetDialog {
    private TextView mBody;
    protected TextView mTitle;
    private View mWishCashLayout;

    protected WhatsWishCashBottomSheet(BaseActivity baseActivity) {
        super(baseActivity);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        setContentView((int) R.layout.whats_wish_cash_bottom_sheet);
        this.mTitle = (TextView) findViewById(R.id.whats_wish_cash_bottom_sheet_title);
        this.mBody = (TextView) findViewById(R.id.whats_wish_cash_bottom_sheet_body);
        this.mWishCashLayout = findViewById(R.id.whats_wish_cash_bottom_sheet_menu_layout);
        ((TextView) findViewById(R.id.whats_wish_cash_bottom_sheet_wish_cash_text)).setText(MenuAdapter.getCommerceCashSpannable());
    }

    public static WhatsWishCashBottomSheet create(final BaseActivity baseActivity) {
        WhatsWishCashBottomSheet whatsWishCashBottomSheet = new WhatsWishCashBottomSheet(baseActivity);
        whatsWishCashBottomSheet.mWishCashLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(baseActivity, CommerceCashActivity.class);
                baseActivity.startActivity(intent);
            }
        });
        BottomSheetUtil.expandDialogFully(whatsWishCashBottomSheet);
        return whatsWishCashBottomSheet;
    }

    public WhatsWishCashBottomSheet setTitle(String str) {
        this.mTitle.setText(str);
        return this;
    }

    public WhatsWishCashBottomSheet setBody(String str) {
        this.mBody.setText(str);
        return this;
    }
}
