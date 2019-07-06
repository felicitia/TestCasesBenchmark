package com.etsy.android.ui.local.marketdetails;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalStore;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.c.a;
import com.etsy.android.lib.util.r;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.local.h;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.EtsyLinkify;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.text.DateFormat;
import java.util.Locale;

public class LocalStoreInfoDetailsFragment extends EtsyFragment {
    private LinearLayout mHoursLayout;
    /* access modifiers changed from: private */
    public LocalMarket mMarket;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mMarket = (LocalMarket) getArguments().getSerializable(ResponseConstants.LOCAL_MARKET);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_local_store_info_details, viewGroup, false);
        this.mHoursLayout = (LinearLayout) inflate.findViewById(R.id.hours_layout);
        inflate.findViewById(R.id.close_button).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a(LocalStoreInfoDetailsFragment.this.getActivity()).h();
            }
        });
        ((TextView) inflate.findViewById(R.id.store_name)).setText(this.mMarket.getTitle());
        bindAddress(inflate.findViewById(R.id.location_row), (TextView) inflate.findViewById(R.id.location));
        bindStorePhoneNumber(this.mMarket.getStore(), (TextView) inflate.findViewById(R.id.phone), inflate.findViewById(R.id.phone_row));
        bindWebsite(inflate.findViewById(R.id.website_row), (TextView) inflate.findViewById(R.id.website));
        bindDescription(inflate.findViewById(R.id.description_row), (TextView) inflate.findViewById(R.id.description));
        return inflate;
    }

    private void bindAddress(View view, TextView textView) {
        final String a = a.a(this.mMarket);
        textView.setText(a);
        view.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                h.a((Activity) LocalStoreInfoDetailsFragment.this.getActivity(), LocalStoreInfoDetailsFragment.this.getAnalyticsContext(), a);
            }
        });
    }

    private void bindStorePhoneNumber(@NonNull final LocalStore localStore, TextView textView, View view) {
        String a = a.a(localStore);
        if (af.b(localStore.getPhoneNumber())) {
            textView.setText(a);
            view.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    h.a((Activity) LocalStoreInfoDetailsFragment.this.getActivity(), localStore.getPhoneNumber());
                }
            });
            view.setVisibility(0);
            return;
        }
        view.setVisibility(8);
    }

    private void bindWebsite(View view, TextView textView) {
        if (af.b(this.mMarket.getExternalUrl())) {
            textView.setText(this.mMarket.getExternalUrl().toLowerCase(Locale.ROOT));
            view.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    r.a((Context) LocalStoreInfoDetailsFragment.this.getActivity(), LocalStoreInfoDetailsFragment.this.mMarket.getExternalUrl());
                }
            });
            view.setVisibility(0);
            return;
        }
        view.setVisibility(8);
    }

    private void bindDescription(View view, TextView textView) {
        if (af.b(this.mMarket.getDescription())) {
            view.setVisibility(0);
            textView.setText(Html.fromHtml(this.mMarket.getDescription()));
            EtsyLinkify.a((Context) getActivity(), textView, false);
            return;
        }
        view.setVisibility(8);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        fillDates();
    }

    private void fillDates() {
        if (this.mMarket.getScheduleRollups().size() < 1) {
            this.mHoursLayout.setVisibility(8);
            return;
        }
        LayoutInflater from = LayoutInflater.from(getActivity());
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getActivity());
        for (TimeRange timeRange : this.mMarket.getScheduleRollups()) {
            View inflate = from.inflate(R.layout.list_item_local_store_hours, this.mHoursLayout, false);
            TextView textView = (TextView) inflate.findViewById(R.id.hours);
            ((TextView) inflate.findViewById(R.id.day)).setText(a.a(timeRange, false));
            textView.setText(String.format("%s – %s", new Object[]{timeFormat.format(timeRange.getStartTime().getTime()), timeFormat.format(timeRange.getEndTime().getTime())}));
            this.mHoursLayout.addView(inflate);
        }
    }
}
