package com.etsy.android.ui.local;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.support.annotation.NonNull;
import com.etsy.android.R;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.Attendee;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule;
import com.etsy.android.lib.models.LocalMarketAttendeeSchedule.Day;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.c.a;
import com.etsy.android.lib.util.r;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.Calendar;

/* compiled from: LocalMarketIntentLauncher */
public class h {
    private static final String a = f.a(h.class);

    public static void a(Activity activity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(String.format("tel:%s", new Object[]{str.trim()})));
        r.a((Context) activity, intent);
    }

    public static void a(Activity activity, @NonNull w wVar, String str) {
        String format = String.format(wVar.c().b(b.L), new Object[]{Uri.encode(str)});
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(format));
        intent.setFlags(ErrorDialogData.BINDER_CRASH);
        r.a((Context) activity, intent);
    }

    public static void a(Activity activity, LocalMarket localMarket) {
        if (localMarket != null) {
            r.a((Context) activity, new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", a(localMarket).getTimeInMillis()).putExtra("endTime", b(localMarket).getTimeInMillis()).putExtra("allDay", !localMarket.isOneDayEvent() || localMarket.getScheduleRollups().size() == 0).putExtra("title", localMarket.getTitle()).putExtra("description", a.a((Context) activity, localMarket)).putExtra("eventLocation", a.a(localMarket)));
        }
    }

    public static void a(Activity activity, Attendee attendee, LocalMarket localMarket) {
        if (localMarket != null && attendee != null) {
            boolean z = false;
            String string = activity.getResources().getString(R.string.seller_at_event, new Object[]{attendee.getShopName(), localMarket.getTitle()});
            if (af.b(localMarket.getCity())) {
                StringBuilder sb = new StringBuilder();
                sb.append(string);
                sb.append(", ");
                sb.append(localMarket.getCity());
                string = sb.toString();
            }
            Intent putExtra = new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("beginTime", a(attendee.getSchedule(), localMarket).getTimeInMillis()).putExtra("endTime", b(attendee.getSchedule(), localMarket).getTimeInMillis());
            String str = "allDay";
            if (!localMarket.isOneDayEvent() || localMarket.getScheduleRollups().size() == 0) {
                z = true;
            }
            r.a((Context) activity, putExtra.putExtra(str, z).putExtra("title", string).putExtra("description", a.a((Context) activity, attendee, localMarket)).putExtra("eventLocation", a.a(localMarket)));
        }
    }

    private static Calendar a(LocalMarket localMarket) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(localMarket.getStartDate());
        TimeRange timeRangeForDay = localMarket.getTimeRangeForDay(instance);
        if (timeRangeForDay == null) {
            instance.set(11, 0);
            instance.set(12, 0);
        } else {
            instance.set(11, timeRangeForDay.getStartTime().get(11));
            instance.set(12, timeRangeForDay.getStartTime().get(12));
        }
        return instance;
    }

    private static Calendar b(LocalMarket localMarket) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(localMarket.getEndDate());
        TimeRange timeRangeForDay = localMarket.getTimeRangeForDay(instance);
        if (timeRangeForDay == null) {
            instance.set(11, 23);
            instance.set(12, 59);
        } else {
            instance.set(11, timeRangeForDay.getEndTime().get(11));
            instance.set(12, timeRangeForDay.getEndTime().get(12));
        }
        return instance;
    }

    private static Calendar a(LocalMarketAttendeeSchedule localMarketAttendeeSchedule, @NonNull LocalMarket localMarket) {
        Calendar calendar;
        if (localMarketAttendeeSchedule == null || localMarketAttendeeSchedule.getDays().size() < 1 || (localMarketAttendeeSchedule.isDaysOfWeekType() && !localMarket.isOneDayEvent())) {
            return a(localMarket);
        }
        Day day = (Day) localMarketAttendeeSchedule.getDays().get(0);
        if (localMarketAttendeeSchedule.isDaysOfWeekType()) {
            calendar = a(localMarket);
        } else {
            calendar = Calendar.getInstance();
            calendar.setTime(day.getSpecificDate());
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(day.getFrom());
        calendar.set(11, instance.get(11));
        calendar.set(12, instance.get(12));
        return calendar;
    }

    private static Calendar b(LocalMarketAttendeeSchedule localMarketAttendeeSchedule, @NonNull LocalMarket localMarket) {
        Calendar calendar;
        if (localMarketAttendeeSchedule == null || localMarketAttendeeSchedule.getDays().size() < 1 || (localMarketAttendeeSchedule.isDaysOfWeekType() && !localMarket.isOneDayEvent())) {
            return b(localMarket);
        }
        Day day = (Day) localMarketAttendeeSchedule.getDays().get(0);
        if (localMarketAttendeeSchedule.isDaysOfWeekType()) {
            calendar = b(localMarket);
        } else {
            calendar = Calendar.getInstance();
            calendar.setTime(day.getSpecificDate());
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(day.getTo());
        calendar.set(11, instance.get(11));
        calendar.set(12, instance.get(12));
        return calendar;
    }
}
