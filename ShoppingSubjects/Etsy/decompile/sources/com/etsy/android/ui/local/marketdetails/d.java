package com.etsy.android.ui.local.marketdetails;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.LocalStore;
import com.etsy.android.lib.models.datatypes.TimeRange;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.l;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.e;
import java.util.Locale;

/* compiled from: LocalDetailsHeaderViewHolder */
class d extends ViewHolder {
    private static final String u = f.a(d.class);
    @Nullable
    SupportMapFragment a;
    a b;
    TextView c = ((TextView) this.itemView.findViewById(R.id.title));
    TextView d = ((TextView) this.itemView.findViewById(R.id.type_time_text));
    View e = this.itemView.findViewById(R.id.calendar_button);
    View f = this.itemView.findViewById(R.id.location_row);
    TextView g = ((TextView) this.itemView.findViewById(R.id.location));
    View h = this.itemView.findViewById(R.id.hours_row);
    LinearLayout i = ((LinearLayout) this.itemView.findViewById(R.id.hours));
    View j = this.itemView.findViewById(R.id.description_row);
    TextView k = ((TextView) this.itemView.findViewById(R.id.description));
    View l = this.itemView.findViewById(R.id.team_row);
    TextView m = ((TextView) this.itemView.findViewById(R.id.team));
    View n = this.itemView.findViewById(R.id.phone_row);
    TextView o = ((TextView) this.itemView.findViewById(R.id.phone));
    View p = this.itemView.findViewById(R.id.website_row);
    TextView q = ((TextView) this.itemView.findViewById(R.id.website));
    View r = this.itemView.findViewById(R.id.more_details_row);
    View s = this.itemView.findViewById(R.id.map_container);
    @Nullable
    TextView t = ((TextView) this.itemView.findViewById(R.id.about_text));

    /* compiled from: LocalDetailsHeaderViewHolder */
    interface a extends e {
        void a();

        void a(TextView textView);

        void a(String str);

        void b();

        void b(String str);

        void c(String str);
    }

    public d(LayoutInflater layoutInflater, ViewGroup viewGroup, a aVar) {
        super(layoutInflater.inflate(R.layout.header_local_market, viewGroup, false));
        this.b = aVar;
    }

    public void a(Fragment fragment, FragmentManager fragmentManager, LocalMarket localMarket) {
        if (localMarket != null) {
            Resources resources = this.c.getResources();
            this.c.setText(localMarket.getTitle());
            if (af.b(localMarket.getNextOpenText())) {
                this.d.setVisibility(0);
                this.d.setText(localMarket.getNextOpenText());
            } else {
                this.d.setVisibility(8);
            }
            if (this.t != null) {
                this.t.setText(localMarket.isWholesaleStore() ? R.string.about_this_store : R.string.about_this_event);
            }
            boolean a2 = com.etsy.android.lib.messaging.a.a(this.itemView.getContext());
            if (this.a == null && a2) {
                this.s.setVisibility(0);
                GoogleMapOptions googleMapOptions = new GoogleMapOptions();
                googleMapOptions.liteMode(true);
                googleMapOptions.mapToolbarEnabled(false);
                try {
                    googleMapOptions.camera(new com.google.android.gms.maps.model.CameraPosition.a().a(localMarket.getLatLng()).a((float) resources.getInteger(R.integer.local_market_info_map_zoom)).a());
                } catch (NumberFormatException e2) {
                    String str = u;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Bad latitude and longitude for local market with ID ");
                    sb.append(localMarket.getLocalMarketId());
                    f.e(str, sb.toString(), e2);
                    b a3 = b.a();
                    String str2 = u;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("NumberFormatException in latitude / longitude for local market with ID ");
                    sb2.append(localMarket.getLocalMarketId());
                    a3.a(str2, sb2.toString());
                }
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                this.a = SupportMapFragment.newInstance(googleMapOptions);
                beginTransaction.add((int) R.id.map_container, (Fragment) this.a);
                beginTransaction.commit();
                this.a.getMapAsync(this.b);
            } else if (!a2) {
                this.s.setVisibility(8);
            }
            final String a4 = com.etsy.android.lib.util.c.a.a(localMarket);
            this.g.setText(a4);
            this.f.setOnClickListener(new TrackingOnClickListener(new i[]{localMarket}) {
                public void onViewClick(View view) {
                    d.this.b.c(a4);
                }
            });
            if (localMarket.isWholesaleStore()) {
                if (!l.c((Activity) fragment.getActivity())) {
                    this.j.setVisibility(8);
                    this.p.setVisibility(8);
                    c(localMarket);
                } else {
                    a(localMarket);
                    b(localMarket);
                }
                this.e.setVisibility(8);
                b(l.c((Activity) fragment.getActivity()), localMarket);
            } else {
                a(l.c((Activity) fragment.getActivity()), localMarket);
                this.e.setVisibility(0);
                this.e.setOnClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        d.this.b.a();
                    }
                });
                a(localMarket);
                b(localMarket);
            }
            c(l.c((Activity) fragment.getActivity()), localMarket);
            if (localMarket.isOrganizedByTeam()) {
                this.l.setVisibility(0);
                this.m.setText(resources.getString(R.string.organized_by, new Object[]{localMarket.getOrganizingTeam().getName()}));
            } else {
                this.l.setVisibility(8);
            }
        }
    }

    private void a(boolean z, LocalMarket localMarket) {
        this.n.setVisibility((!z || TextUtils.isEmpty(localMarket.getExternalUrl())) ? 8 : 4);
    }

    private void a(LocalMarket localMarket) {
        this.k.setText(Html.fromHtml(localMarket.getDescription()));
        this.b.a(this.k);
    }

    private void b(boolean z, @NonNull LocalMarket localMarket) {
        final LocalStore store = localMarket.getStore();
        String a2 = com.etsy.android.lib.util.c.a.a(store);
        if (af.b(store.getPhoneNumber())) {
            this.o.setText(a2);
            this.n.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    d.this.b.b(store.getPhoneNumber());
                }
            });
            this.n.setVisibility(0);
            return;
        }
        a(z, localMarket);
    }

    private void c(boolean z, LocalMarket localMarket) {
        if (!localMarket.isWholesaleStore() || localMarket.getScheduleRollups().size() != 0) {
            this.i.removeAllViews();
            Resources resources = this.i.getResources();
            if (!localMarket.isWholesaleStore()) {
                TextView a2 = a(z, resources);
                a2.setText(com.etsy.android.lib.util.c.a.b(localMarket));
                this.i.addView(a2);
            } else if (z) {
                TextView a3 = a(z, resources);
                a3.setText(R.string.hours_title);
                this.i.addView(a3);
            }
            LayoutInflater from = LayoutInflater.from(this.i.getContext());
            for (TimeRange timeRange : localMarket.getScheduleRollups()) {
                View inflate = from.inflate(R.layout.list_item_local_day_hours, this.i, false);
                ((TextView) inflate.findViewById(R.id.day)).setText(com.etsy.android.lib.util.c.a.a(timeRange, z));
                TextView textView = (TextView) inflate.findViewById(R.id.time_range);
                textView.setText(com.etsy.android.lib.util.c.a.a(textView.getContext(), timeRange.getStartTime().getTime(), timeRange.getEndTime().getTime()));
                this.i.addView(inflate);
            }
            this.h.setVisibility(0);
            return;
        }
        this.h.setVisibility(8);
    }

    private TextView a(boolean z, Resources resources) {
        int i2;
        TextView textView = new TextView(this.i.getContext());
        if (z) {
            i2 = R.color.text_black;
        } else {
            i2 = R.color.grey;
        }
        textView.setTextColor(resources.getColor(i2));
        textView.setTextSize(0, (float) resources.getDimensionPixelSize(R.dimen.text_medium));
        if (!z) {
            com.etsy.android.stylekit.e.a(textView, (int) R.string.sk_typeface_bold);
        }
        return textView;
    }

    private void b(final LocalMarket localMarket) {
        if (af.b(localMarket.getExternalUrl())) {
            this.q.setText(localMarket.getExternalUrl().toLowerCase(Locale.ROOT));
            this.p.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    d.this.b.a(localMarket.getExternalUrl());
                }
            });
            this.p.setVisibility(0);
            return;
        }
        this.p.setVisibility(8);
    }

    private void c(LocalMarket localMarket) {
        if (af.b(localMarket.getExternalUrl()) || af.b(localMarket.getDescription())) {
            this.r.setVisibility(0);
            this.r.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    d.this.b.b();
                }
            });
            return;
        }
        this.r.setVisibility(8);
    }
}
