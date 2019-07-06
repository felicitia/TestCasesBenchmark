package com.contextlogic.wish.activity.referralprogram;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishReferralProgramInfoSpec;
import com.contextlogic.wish.api.model.WishReferralProgramInfoSpec.ReferralProgramHistoryItem;
import com.contextlogic.wish.api.model.WishTextViewSpec;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.util.ClipboardUtil;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.StringUtil;
import java.util.List;

public class ReferralProgramFragment extends LoadingUiFragment<BaseActivity> {
    ReferralProgramHistoryAdapter mAdapter;
    TextView mBodyText;
    TextView mCardText;
    TextView mCashEarnedText;
    TextView mCopyCouponButton;
    TextView mCouponCodeText;
    RecyclerView mHistoryList;
    View mShareCouponButton;
    ViewGroup mWarningGroup;
    TextView mWarningText;
    TextView mWhatsWishCash;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.referral_program_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    public void handleReload() {
        loadReferralProgramInfoSpec();
    }

    public void initializeLoadingContentView(View view) {
        this.mCardText = (TextView) findViewById(R.id.referral_program_wish_cash_card_text);
        this.mBodyText = (TextView) findViewById(R.id.referral_program_info_body);
        this.mWhatsWishCash = (TextView) findViewById(R.id.referral_program_info_whats_wish_cash);
        this.mCouponCodeText = (TextView) findViewById(R.id.referral_program_coupon_code_text);
        this.mCopyCouponButton = (TextView) findViewById(R.id.referral_program_coupon_copy_text);
        this.mShareCouponButton = findViewById(R.id.referral_program_coupon_share_button);
        this.mCashEarnedText = (TextView) findViewById(R.id.referral_program_cash_earned_text);
        this.mWarningGroup = (ViewGroup) findViewById(R.id.referral_program_warning_container);
        this.mWarningText = (TextView) findViewById(R.id.referral_program_warning_text);
        this.mHistoryList = (RecyclerView) findViewById(R.id.referral_program_history_list);
        this.mAdapter = new ReferralProgramHistoryAdapter(getContext());
        loadReferralProgramInfoSpec();
        PreferenceUtil.setBoolean("SawCashReferral", true);
    }

    private void loadReferralProgramInfoSpec() {
        withServiceFragment(new ServiceTask<ReferralProgramActivity, ReferralProgramServiceFragment>() {
            public void performTask(ReferralProgramActivity referralProgramActivity, ReferralProgramServiceFragment referralProgramServiceFragment) {
                referralProgramServiceFragment.requestReferralProgramInfo();
            }
        });
    }

    public void handleSuccess(WishReferralProgramInfoSpec wishReferralProgramInfoSpec) {
        if (getContext() != null) {
            setupCardText(wishReferralProgramInfoSpec.getFormattedEarnableAmount());
            setupBodyTexts(wishReferralProgramInfoSpec.getInfoBodyTextSpecs());
            setupWhatsWishCashText(wishReferralProgramInfoSpec.getWishCashInfoTitle(), wishReferralProgramInfoSpec.getWishCashInfoBody());
            setupCouponCodeText(wishReferralProgramInfoSpec.getCouponCode());
            setupCouponShareButton(wishReferralProgramInfoSpec.getShareCouponSubject(), wishReferralProgramInfoSpec.getShareCouponMessage());
            setupCashEarnedText(wishReferralProgramInfoSpec.getWishCashEarnedTextSpec());
            setupHistoryList(wishReferralProgramInfoSpec.getReferralHistoryItems());
            setupWarningTexts(wishReferralProgramInfoSpec.getWarningTextSpecs());
            getLoadingPageView().markLoadingComplete();
        }
    }

    public void handleFailure(String str) {
        if (str == null) {
            str = getString(R.string.general_error);
        }
        getLoadingPageView().setErrorMessage(str, true);
        getLoadingPageView().markLoadingErrored();
    }

    private void setupCardText(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mCardText.setVisibility(8);
            return;
        }
        WishApplication.getInstance();
        String capitalize = StringUtil.capitalize(WishApplication.getAppType());
        String string = WishApplication.getInstance().getString(R.string.commerce_cash, new Object[]{capitalize});
        SpannableString spannableString = new SpannableString(str);
        SpannableString spannableString2 = new SpannableString(string);
        spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, str.length(), 17);
        spannableString.setSpan(new StyleSpan(1), 0, str.length(), 17);
        this.mCardText.setText(TextUtils.concat(new CharSequence[]{spannableString, "\n", spannableString2}));
    }

    private void setupBodyTexts(List<WishTextViewSpec> list) {
        WishTextViewSpec.applyTextViewSpecs(this.mBodyText, list, "\n\n");
    }

    private void setupWarningTexts(List<WishTextViewSpec> list) {
        WishTextViewSpec.applyTextViewSpecs(this.mWarningText, list, "\n");
        this.mWarningGroup.setVisibility(this.mWarningText.getVisibility());
    }

    private void setupWhatsWishCashText(final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            this.mWhatsWishCash.setVisibility(8);
            return;
        }
        this.mWhatsWishCash.setText(str);
        this.mWhatsWishCash.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WhatsWishCashBottomSheet.create(ReferralProgramFragment.this.getBaseActivity()).setTitle(str).setBody(str2).show();
            }
        });
    }

    private void setupCouponCodeText(final String str) {
        this.mCouponCodeText.setText(StringUtil.join(str, " "));
        this.mCopyCouponButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_INVITE_BY_COUPON_COPY);
                if (ReferralProgramFragment.this.copyToClipboard(str)) {
                    ReferralProgramFragment.this.mCopyCouponButton.setText(R.string.copied_exclamation);
                }
            }
        });
    }

    private void setupCouponShareButton(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.mShareCouponButton.setVisibility(8);
            return;
        }
        final Intent shareIntent = IntentUtil.getShareIntent(str, str2);
        AnonymousClass4 r3 = new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_INVITE_BY_COUPON_SEND_INVITES);
                if (shareIntent != null && ReferralProgramFragment.this.getContext() != null) {
                    ReferralProgramFragment.this.getContext().startActivity(shareIntent);
                }
            }
        };
        this.mShareCouponButton.setOnClickListener(r3);
        this.mAdapter.setOnInviteClickListener(r3);
    }

    private void setupCashEarnedText(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mCashEarnedText.setVisibility(8);
        } else {
            this.mCashEarnedText.setText(str);
        }
    }

    private void setupHistoryList(List<ReferralProgramHistoryItem> list) {
        if (list == null || list.isEmpty()) {
            this.mHistoryList.setVisibility(8);
            return;
        }
        this.mAdapter.setItems(list);
        this.mHistoryList.setAdapter(this.mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.mHistoryList.setLayoutManager(linearLayoutManager);
        this.mHistoryList.setNestedScrollingEnabled(false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), 1);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.default_listview_divider));
        this.mHistoryList.addItemDecoration(dividerItemDecoration);
    }

    /* access modifiers changed from: 0000 */
    public boolean copyToClipboard(String str) {
        return ClipboardUtil.copyToClipboard(str, str);
    }

    public boolean hasItems() {
        return this.mAdapter != null && this.mAdapter.getItemCount() > 0;
    }
}
