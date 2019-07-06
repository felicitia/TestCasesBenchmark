package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.R;
import com.etsy.android.lib.a.p;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView;
import com.etsy.android.ui.search.v2.SearchOptions.Location;
import com.etsy.android.ui.search.v2.SearchOptions.Location.LocationType;
import com.etsy.android.uikit.c;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.Locale;

/* compiled from: SearchShopLocationSelectView */
public class m {

    /* compiled from: SearchShopLocationSelectView */
    public interface a {
        void a(LocationType locationType, String str);
    }

    /* compiled from: SearchShopLocationSelectView */
    private static class b extends TrackingOnClickListener implements OnFocusChangeListener, OnKeyListener, OnEditorActionListener, com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView.a {
        private final View a;
        private final EditText b;
        private final TextView c;
        private final TextView d;
        private final a e;
        private LocationType f;

        private static int a(boolean z) {
            return z ? p.TextBlue_Large : p.TextGrey_Large;
        }

        b(View view, Location location, a aVar) {
            this.e = aVar;
            this.a = view;
            this.f = location.getType();
            this.c = (TextView) view.findViewById(R.id.search_filters_shop_location_anywhere_option);
            this.c.setOnClickListener(this);
            this.d = (TextView) view.findViewById(R.id.search_filters_shop_location_local_option);
            this.d.setOnClickListener(this);
            this.d.setText(Locale.getDefault().getDisplayCountry());
            this.b = (EditText) view.findViewById(R.id.search_filters_shop_location_custom_option);
            this.b.setOnEditorActionListener(this);
            this.b.setOnKeyListener(this);
            this.b.setOnFocusChangeListener(this);
            if (location.getType() == LocationType.CUSTOM) {
                this.b.setText(location.getLocation(view.getResources()));
            }
            this.b.setCompoundDrawablesWithIntrinsicBounds(c.a(view.getContext(), R.drawable.sk_ic_location, R.color.sk_gray_50), null, null, null);
            d();
        }

        public void onViewClick(View view) {
            int id = view.getId();
            if (id == R.id.search_filters_shop_location_anywhere_option) {
                a(LocationType.ANYWHERE, "");
            } else if (id == R.id.search_filters_shop_location_local_option) {
                a(LocationType.LOCAL, Locale.getDefault().getDisplayCountry());
            }
        }

        private void a() {
            s.a((View) this.b);
            this.b.clearFocus();
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6) {
                return false;
            }
            a(LocationType.CUSTOM, this.b.getText().toString());
            return true;
        }

        private void a(LocationType locationType, String str) {
            if (locationType == LocationType.CUSTOM && TextUtils.isEmpty(str)) {
                locationType = LocationType.ANYWHERE;
            }
            this.f = locationType;
            this.e.a(locationType, str);
            a();
            d();
        }

        private void d() {
            boolean z = false;
            this.c.setTextAppearance(this.c.getContext(), a(this.f == LocationType.ANYWHERE));
            TextView textView = this.d;
            Context context = this.d.getContext();
            if (this.f == LocationType.ANYWHERE) {
                z = true;
            }
            textView.setTextAppearance(context, a(z));
            if (this.f != LocationType.CUSTOM) {
                this.b.setText("");
            }
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66) {
                return false;
            }
            this.b.onEditorAction(6);
            return true;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.f = LocationType.CUSTOM;
                d();
            }
        }

        public View b() {
            return this.a;
        }

        public void c() {
            if (this.b.hasFocus()) {
                a(LocationType.CUSTOM, this.b.getText().toString().trim());
            }
        }
    }

    public static void a(SelectView selectView, Location location, a aVar) {
        selectView.showWith(new b(LayoutInflater.from(selectView.getContext()).inflate(R.layout.new_search_shop_location, null, false), location, aVar));
    }
}
