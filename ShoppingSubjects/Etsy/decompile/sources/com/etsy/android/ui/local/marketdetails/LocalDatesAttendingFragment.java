package com.etsy.android.ui.local.marketdetails;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.c.a;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.local.h;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocalDatesAttendingFragment extends EtsyFragment {
    /* access modifiers changed from: private */
    public Attendee mAttendee;
    private ImageView mAvatar;
    private TextView mComment;
    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mDayOfWeekFormat;
    private LinearLayout mHoursLayout;
    /* access modifiers changed from: private */
    public LocalMarket mMarket;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAttendee = (Attendee) getArguments().getSerializable("attendee");
        this.mMarket = (LocalMarket) getArguments().getSerializable(ResponseConstants.LOCAL_MARKET);
        this.mDateFormat = new SimpleDateFormat("MMM d", Locale.getDefault());
        this.mDayOfWeekFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = 0;
        View inflate = layoutInflater.inflate(R.layout.fragment_local_dates_attending, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.shop_name)).setText(this.mAttendee.getShopName());
        inflate.findViewById(R.id.view_shop_button).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a(LocalDatesAttendingFragment.this.getActivity()).a().b(LocalDatesAttendingFragment.this.mAttendee.getShopId());
            }
        });
        inflate.findViewById(R.id.add_to_calendar_button).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                h.a((Activity) LocalDatesAttendingFragment.this.getActivity(), LocalDatesAttendingFragment.this.mAttendee, LocalDatesAttendingFragment.this.mMarket);
            }
        });
        View findViewById = inflate.findViewById(R.id.close_button);
        if (l.c((Activity) getActivity())) {
            i = 8;
        }
        findViewById.setVisibility(i);
        findViewById.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a(LocalDatesAttendingFragment.this.getActivity()).h();
            }
        });
        this.mAvatar = (ImageView) inflate.findViewById(R.id.avatar);
        this.mHoursLayout = (LinearLayout) inflate.findViewById(R.id.hours_layout);
        this.mComment = (TextView) inflate.findViewById(R.id.comment);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof EtsyDialogFragment) {
            setUpDialog((EtsyDialogFragment) getParentFragment());
        }
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.local_avatar_size);
        getImageBatch().a(this.mAttendee.getAvatarUrl(), this.mAvatar, dimensionPixelOffset, dimensionPixelOffset);
        fillComment();
        fillDates();
    }

    private void setUpDialog(EtsyDialogFragment etsyDialogFragment) {
        etsyDialogFragment.hideHeader();
        etsyDialogFragment.setWindowMode(WindowMode.MEDIUM);
        etsyDialogFragment.setWindowBackgroundDim(0.6f);
    }

    private void fillComment() {
        if (af.b(this.mAttendee.getComment())) {
            this.mComment.setVisibility(0);
            this.mComment.setText(String.format("\"%s\"", new Object[]{this.mAttendee.getComment().trim()}));
            EtsyLinkify.a((Context) getActivity(), this.mComment, false);
            return;
        }
        this.mComment.setVisibility(8);
    }

    private void fillDates() {
        if (this.mAttendee.getSchedule() == null || this.mAttendee.getSchedule().getDays().size() == 0) {
            this.mHoursLayout.setVisibility(8);
            return;
        }
        LayoutInflater from = LayoutInflater.from(getActivity());
        String[] stringArray = getResources().getStringArray(R.array.weekdays_pluralized);
        boolean z = this.mMarket.isHappeningNow() || this.mMarket.isBetweenStartAndEnd();
        Calendar instance = Calendar.getInstance();
        int i = instance.get(7);
        for (Day day : this.mAttendee.getSchedule().getDays()) {
            View inflate = from.inflate(R.layout.list_item_local_attending_hours, this.mHoursLayout, false);
            TextView textView = (TextView) inflate.findViewById(R.id.day);
            TextView textView2 = (TextView) inflate.findViewById(R.id.date);
            TextView textView3 = (TextView) inflate.findViewById(R.id.hours);
            textView3.setText(a.a((Context) getActivity(), day.getFrom(), day.getTo()));
            if (this.mAttendee.getSchedule().isDaysOfWeekType()) {
                int weekIndex = day.getDay().getWeekIndex();
                textView.setText(stringArray[weekIndex]);
                if (z && i == weekIndex) {
                    boldFields(textView, textView2, textView3);
                }
            } else {
                textView2.setText(this.mDateFormat.format(day.getSpecificDate()));
                if (!z || !isSameDay(instance, day.getSpecificDate())) {
                    textView.setText(this.mDayOfWeekFormat.format(day.getSpecificDate()));
                } else {
                    textView.setText(R.string.today);
                    boldFields(textView, textView2, textView3);
                }
            }
            this.mHoursLayout.addView(inflate);
        }
    }

    private boolean isSameDay(Calendar calendar, Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return calendar.get(6) == instance.get(6) && calendar.get(1) == instance.get(1);
    }

    private void boldFields(TextView textView, TextView textView2, TextView textView3) {
        com.etsy.android.stylekit.e.a(textView, (int) R.string.sk_typeface_bold);
        com.etsy.android.stylekit.e.a(textView2, (int) R.string.sk_typeface_bold);
        com.etsy.android.stylekit.e.a(textView3, (int) R.string.sk_typeface_bold);
    }
}
