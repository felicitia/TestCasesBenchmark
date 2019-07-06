package com.contextlogic.wish.ui.timer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.util.DateUtil.TimeUnit;
import java.util.Locale;

public class FreeGiftsCountdownTimerView extends BaseCountdownTimerView {
    TextView mDaysView;
    TextView mHoursView;
    TextView mMinutesView;
    TextView mSecondsView;

    public FreeGiftsCountdownTimerView(Context context) {
        this(context, null);
    }

    public FreeGiftsCountdownTimerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.free_gifts_countdown_timer_view, this);
        this.mDaysView = (TextView) inflate.findViewById(R.id.free_gifts_countdown_timer_view_days);
        this.mHoursView = (TextView) inflate.findViewById(R.id.free_gifts_countdown_timer_view_hrs);
        this.mMinutesView = (TextView) inflate.findViewById(R.id.free_gifts_countdown_timer_view_min);
        this.mSecondsView = (TextView) inflate.findViewById(R.id.free_gifts_countdown_timer_view_sec);
    }

    /* access modifiers changed from: protected */
    public void updateTimeUi(boolean z) {
        this.mDaysView.setText(String.format(Locale.getDefault(), "%02d", new Object[]{Long.valueOf(this.mTimeParts.days)}));
        this.mHoursView.setText(String.format(Locale.getDefault(), "%02d", new Object[]{Long.valueOf(this.mTimeParts.hours)}));
        this.mMinutesView.setText(String.format(Locale.getDefault(), "%02d", new Object[]{Long.valueOf(this.mTimeParts.minutes)}));
        this.mSecondsView.setText(String.format(Locale.getDefault(), "%02d", new Object[]{Long.valueOf(this.mTimeParts.seconds)}));
    }

    /* access modifiers changed from: protected */
    public TimeUnit getMaxTimeUnit() {
        return TimeUnit.DAY;
    }
}
