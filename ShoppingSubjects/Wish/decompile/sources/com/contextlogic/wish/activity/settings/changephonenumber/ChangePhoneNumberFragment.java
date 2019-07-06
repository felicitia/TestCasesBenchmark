package com.contextlogic.wish.activity.settings.changephonenumber;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.settings.changephonenumber.ChangePhoneNumberActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.ui.button.ToggleLoadingButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.OnToggleLoadingButtonClickListener;
import com.contextlogic.wish.ui.text.ThemedTextView;
import java.util.Locale;

public class ChangePhoneNumberFragment<A extends ChangePhoneNumberActivity> extends UiFragment<A> {
    private ThemedTextView mCurrentPhoneNumber;
    /* access modifiers changed from: private */
    public GetResetKeyButton mGetRestKeyButton;
    /* access modifiers changed from: private */
    public String mPhoneResetKey;
    /* access modifiers changed from: private */
    public String mShortCode;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.change_phone_number_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mCurrentPhoneNumber = (ThemedTextView) findViewById(R.id.change_phone_number_current_phone_number);
        this.mCurrentPhoneNumber.setText(ConfigDataCenter.getInstance().getUserPhoneNumber());
        this.mGetRestKeyButton = (GetResetKeyButton) findViewById(R.id.change_phone_number_get_reset_key_button);
        this.mGetRestKeyButton.setOnFollowButtonClickListener(new OnToggleLoadingButtonClickListener() {
            public void onToggleLoadingButtonClicked(ToggleLoadingButton toggleLoadingButton, boolean z) {
                if (!z) {
                    ChangePhoneNumberFragment.this.sendResetSms();
                } else if (ChangePhoneNumberFragment.this.mPhoneResetKey == null || ChangePhoneNumberFragment.this.mShortCode == null) {
                    ChangePhoneNumberFragment.this.mGetRestKeyButton.setButtonMode(ButtonMode.UnselectedLoading);
                    ChangePhoneNumberFragment.this.fetchResetKey();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void fetchResetKey() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_GET_PHONE_RESET_KEY);
        withServiceFragment(new ServiceTask<ChangePhoneNumberActivity, ChangePhoneNumberServiceFragment>() {
            public void performTask(ChangePhoneNumberActivity changePhoneNumberActivity, ChangePhoneNumberServiceFragment changePhoneNumberServiceFragment) {
                changePhoneNumberServiceFragment.getPhoneResetKey();
            }
        });
    }

    public void handleGetPhoneResetKeySuccess(String str, String str2) {
        this.mPhoneResetKey = str.toUpperCase(Locale.ENGLISH);
        this.mShortCode = str2;
        this.mGetRestKeyButton.setButtonMode(ButtonMode.Selected);
        this.mGetRestKeyButton.setText(generateSmsInstructionText(getContext(), this.mPhoneResetKey, this.mShortCode));
    }

    public void handleGetPhoneResetKeyFailure() {
        this.mGetRestKeyButton.setButtonMode(ButtonMode.Unselected);
    }

    public void sendResetSms() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_RESET_PHONE_NATIVE_SMS_DIALOG_IMPRESSION);
        final Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse(String.format("smsto: %s", new Object[]{this.mShortCode})));
        intent.putExtra("sms_body", this.mPhoneResetKey);
        withActivity(new ActivityTask<A>() {
            public void performTask(A a) {
                if (intent.resolveActivity(a.getPackageManager()) != null) {
                    a.startActivity(intent);
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_FAILED_PHONE_RESET_SMS);
                }
            }
        });
    }

    private static SpannableString generateSmsInstructionText(Context context, String str, String str2) {
        String string = context.getString(R.string.text_reset_key_to_short_code, new Object[]{str, str2});
        SpannableString spannableString = new SpannableString(string);
        int indexOf = string.indexOf(str);
        if (indexOf != -1) {
            spannableString.setSpan(new StyleSpan(1), indexOf, str.length() + indexOf, 33);
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.main_primary)), indexOf, str.length() + indexOf, 33);
        }
        int indexOf2 = string.indexOf(str2);
        if (indexOf2 != -1) {
            spannableString.setSpan(new StyleSpan(1), indexOf2, str2.length() + indexOf2, 33);
        }
        return spannableString;
    }
}
