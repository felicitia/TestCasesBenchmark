package com.contextlogic.wish.dialog.commerceloan;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.util.TimezoneUtil;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class CommerceLoanDatePickerDialog extends DatePickerDialog {
    public CommerceLoanDatePickerDialog(Context context, int i, Calendar calendar, OnDateSetListener onDateSetListener) {
        super(context, R.style.DateTimeDialogTheme, onDateSetListener, calendar.get(1), calendar.get(2), calendar.get(5));
        getDatePicker().setMinDate(TimezoneUtil.defaultTimeZoneToUTC(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
        getDatePicker().setMaxDate(TimezoneUtil.defaultTimeZoneToUTC(System.currentTimeMillis() + TimeUnit.DAYS.toMillis((long) (i - 1))));
        CommerceLoanDatePickerTitleView commerceLoanDatePickerTitleView = new CommerceLoanDatePickerTitleView(context);
        commerceLoanDatePickerTitleView.setNumDays(i);
        getDatePicker().setCalendarViewShown(true);
        getDatePicker().setSpinnersShown(false);
        setCustomTitle(commerceLoanDatePickerTitleView);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_COMMERCE_LOAN_DATE_PICKER);
    }
}
