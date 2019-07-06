package com.contextlogic.wish.activity.pricechop;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.PriceChopProductDetail;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.StringUtil;
import com.crashlytics.android.Crashlytics;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class PriceChopMessageBottomSheet extends BaseDialogFragment<BaseActivity> {
    /* access modifiers changed from: private */
    public TextView mCharCountText;
    /* access modifiers changed from: private */
    public EditText mEditText;
    /* access modifiers changed from: private */
    public TextView mShareButton;

    public int getGravity() {
        return 81;
    }

    /* access modifiers changed from: protected */
    public boolean requiresKeyboard() {
        return true;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.price_chop_message_bottom_sheet, viewGroup, false);
        this.mEditText = (EditText) inflate.findViewById(R.id.price_chop_message_edit_text);
        this.mShareButton = (TextView) inflate.findViewById(R.id.price_chop_message_share_button);
        this.mCharCountText = (TextView) inflate.findViewById(R.id.price_chop_message_char_count_text);
        setupShareButton();
        setupEditTextBehavior();
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setSoftInputMode(21);
        }
    }

    public int getDialogWidth() {
        int displayWidth = DisplayUtil.getDisplayWidth();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.bottom_dialog_fragment_max_width);
        return displayWidth > dimensionPixelSize ? dimensionPixelSize : displayWidth;
    }

    private void setupCharCounter() {
        this.mCharCountText.setText(getString(R.string.price_chop_message_count_text, Integer.valueOf(0), Integer.valueOf(50)));
        this.mEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                PriceChopMessageBottomSheet.this.mCharCountText.setText(PriceChopMessageBottomSheet.this.getString(R.string.price_chop_message_count_text, Integer.valueOf(PriceChopMessageBottomSheet.this.mEditText.getText().length()), Integer.valueOf(50)));
            }
        });
    }

    private void setupEditTextBehavior() {
        this.mEditText.setFilters(new InputFilter[]{new LengthFilter(50)});
        setupCharCounter();
        if (ExperimentDataCenter.getInstance().shouldShowPriceChopMessageV2()) {
            this.mShareButton.setEnabled(true);
            this.mEditText.setHint(getShareBody());
            return;
        }
        this.mEditText.setHint(R.string.price_chop_message_ask_friend);
        this.mShareButton.setEnabled(false);
        this.mEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                PriceChopMessageBottomSheet.this.mShareButton.setEnabled(!TextUtils.isEmpty(charSequence));
            }
        });
    }

    /* access modifiers changed from: private */
    public String getShareTitle() {
        return getArguments() != null ? getArguments().getString("share_title", "") : "";
    }

    /* access modifiers changed from: private */
    public String getShareBody() {
        return getArguments() != null ? getArguments().getString("share_body", "") : "";
    }

    private void setupShareButton() {
        this.mShareButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PRICE_CHOP_DETAIL_MESSAGE_SHARE);
                PriceChopMessageBottomSheet.this.withActivity(new ActivityTask<BaseActivity>() {
                    public void performTask(BaseActivity baseActivity) {
                        String access$300 = PriceChopMessageBottomSheet.this.getShareTitle();
                        String access$400 = PriceChopMessageBottomSheet.this.getShareBody();
                        if (!TextUtils.isEmpty(PriceChopMessageBottomSheet.this.mEditText.getText())) {
                            List pullLinks = StringUtil.pullLinks(access$400);
                            String obj = PriceChopMessageBottomSheet.this.mEditText.getText().toString();
                            if (!pullLinks.isEmpty()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(obj);
                                sb.append("\n");
                                String sb2 = sb.toString();
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(sb2);
                                sb3.append((String) pullLinks.get(0));
                                access$400 = sb3.toString();
                                try {
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append(access$400);
                                    sb4.append("?title=");
                                    sb4.append(URLEncoder.encode(obj, "utf-8"));
                                    access$400 = sb4.toString();
                                } catch (UnsupportedEncodingException e) {
                                    StringBuilder sb5 = new StringBuilder();
                                    sb5.append("Failed to encode user message, message=");
                                    sb5.append(obj);
                                    Crashlytics.log(sb5.toString());
                                    Crashlytics.logException(e);
                                }
                            } else {
                                access$400 = obj;
                            }
                        }
                        baseActivity.showShareDialog(access$300, access$400);
                    }
                });
                PriceChopMessageBottomSheet.this.dismiss();
            }
        });
    }

    public static PriceChopMessageBottomSheet create(PriceChopProductDetail priceChopProductDetail) {
        PriceChopMessageBottomSheet priceChopMessageBottomSheet = new PriceChopMessageBottomSheet();
        Bundle bundle = new Bundle();
        bundle.putString("share_title", priceChopProductDetail.getShareTitle());
        bundle.putString("share_body", priceChopProductDetail.getShareBody());
        priceChopMessageBottomSheet.setArguments(bundle);
        return priceChopMessageBottomSheet;
    }
}
