package com.contextlogic.wish.activity.signup.redesign.BirthdayPicker;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.FullScreenActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowBaseView;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView.FooterManager;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment.SignupPage;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;

public class BirthdayPickerView extends SignupFlowBaseView implements FooterManager {
    private DatePicker mBirthdayDatePicker;
    /* access modifiers changed from: private */
    public boolean mDateChangedTracked;
    private SignupFlowFooterView mFooterView;
    private int mInitialDay;
    private int mInitialMonth;
    private int mInitialYear;

    public int getLoadingContentLayoutResourceId() {
        return R.layout.redesign_signup_birthday_picker;
    }

    public BirthdayPickerView(FullScreenActivity fullScreenActivity, SignupFlowFragment signupFlowFragment) {
        super(fullScreenActivity, signupFlowFragment);
    }

    public void initializeLoadingContentView(View view) {
        this.mFooterView = (SignupFlowFooterView) findViewById(R.id.signup_footer_view);
        setupFooter(SignupPage.BirthdayPicker, this.mFooterView);
        this.mFooterView.setFooterManager(this);
        this.mBirthdayDatePicker = (DatePicker) findViewById(R.id.signup_flow_date_picker);
        this.mBirthdayDatePicker.setMaxDate(System.currentTimeMillis());
        this.mInitialMonth = this.mBirthdayDatePicker.getMonth();
        this.mInitialDay = this.mBirthdayDatePicker.getDayOfMonth();
        this.mInitialYear = this.mBirthdayDatePicker.getYear() - 19;
        this.mBirthdayDatePicker.init(this.mInitialYear, this.mInitialMonth, this.mInitialDay, new OnDateChangedListener() {
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                if (!BirthdayPickerView.this.mDateChangedTracked) {
                    WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_EDIT_BIRTHDAY);
                    BirthdayPickerView.this.mDateChangedTracked = true;
                }
            }
        });
        Bundle savedInstanceState = this.mFragment.getSavedInstanceState(SignupPage.BirthdayPicker.ordinal());
        if (savedInstanceState != null) {
            this.mBirthdayDatePicker.updateDate(savedInstanceState.getInt("DateOfBirthYear"), savedInstanceState.getInt("DateOfBirthMonth") - 1, savedInstanceState.getInt("DateOfBirthDate"));
        }
        handleOnPageSelected();
        markLoadingComplete();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_BIRTHDAY_IMPRESSION);
    }

    public void handleFooterButtonClick() {
        if (!(this.mInitialDay == getDobDay() && this.mInitialMonth + 1 == getDobMonth() && this.mInitialYear == getDobYear()) && this.mDateChangedTracked) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_UPDATE_BIRTHDAY);
        } else {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_SKIP_UPDATE_BIRTHDAY);
        }
        this.mFragment.uploadProfileBirthday(getDobDay(), getDobMonth(), getDobYear());
    }

    public int getDobDay() {
        return this.mBirthdayDatePicker.getDayOfMonth();
    }

    public int getDobMonth() {
        return this.mBirthdayDatePicker.getMonth() + 1;
    }

    public int getDobYear() {
        return this.mBirthdayDatePicker.getYear();
    }

    public void handleOnPageSelected() {
        this.mBaseActivity.getSupportActionBar().show();
    }

    public void handleSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("DateOfBirthDate", getDobDay());
        bundle2.putInt("DateOfBirthMonth", getDobMonth());
        bundle2.putInt("DateOfBirthYear", getDobYear());
        bundle.putBundle(this.mFragment.getPagerKey(SignupPage.BirthdayPicker.ordinal()), bundle2);
    }
}
