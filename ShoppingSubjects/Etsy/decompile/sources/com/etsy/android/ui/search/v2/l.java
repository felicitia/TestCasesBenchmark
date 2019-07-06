package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.a.p;
import com.etsy.android.lib.models.Country;
import com.etsy.android.lib.util.CountryUtil;
import com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.TextViewHolder.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* compiled from: SearchShipToSelectView */
public class l {

    /* compiled from: SearchShipToSelectView */
    public interface a {
        void a(Country country);
    }

    /* compiled from: SearchShipToSelectView */
    private static class b extends Adapter<TextViewHolder> implements com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView.a, c, Comparator<Country> {
        private List<Country> a;
        private final a b;
        private final View c;
        private int d;

        public void c() {
        }

        b(View view, a aVar) {
            this.c = view;
            this.b = aVar;
            setHasStableIds(true);
        }

        public long getItemId(int i) {
            return (long) ((Country) this.a.get(i)).getCountryId();
        }

        /* renamed from: a */
        public TextViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Context context = viewGroup.getContext();
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.fixed_large);
            TextViewHolder textViewHolder = new TextViewHolder(context);
            textViewHolder.setListener(this);
            TextView textView = textViewHolder.getTextView();
            textView.setLayoutParams(new LayoutParams(-1, -2));
            textView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            return textViewHolder;
        }

        /* renamed from: a */
        public void onBindViewHolder(TextViewHolder textViewHolder, int i) {
            TextView textView = textViewHolder.getTextView();
            textView.setText(((Country) this.a.get(i)).getName());
            textView.setTextAppearance(textView.getContext(), i == this.d ? p.TextBlue_Large : p.TextGrey_Large);
        }

        public int getItemCount() {
            if (this.a == null) {
                return 0;
            }
            return this.a.size();
        }

        /* access modifiers changed from: private */
        public void a(List<Country> list, String str) {
            this.a = new ArrayList(list);
            Collections.sort(this.a, this);
            int size = this.a.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                } else if (((Country) this.a.get(i)).getIsoCountryCode().equals(str)) {
                    this.d = i;
                    break;
                } else {
                    i++;
                }
            }
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public int compare(Country country, Country country2) {
            String country3 = Locale.getDefault().getCountry();
            if (country3.equals(country.getIsoCountryCode())) {
                return -1;
            }
            if (country3.equals(country2.getIsoCountryCode())) {
                return 1;
            }
            return country.compareTo(country2);
        }

        public void a(TextViewHolder textViewHolder) {
            int i = this.d;
            int adapterPosition = textViewHolder.getAdapterPosition();
            this.d = adapterPosition;
            notifyItemChanged(i);
            notifyItemChanged(adapterPosition);
            this.b.a((Country) this.a.get(this.d));
        }

        public View b() {
            return this.c;
        }
    }

    public static void a(final SelectView selectView, final String str, a aVar) {
        Context context = selectView.getContext();
        RecyclerView recyclerView = new RecyclerView(context);
        final b bVar = new b(recyclerView, aVar);
        recyclerView.setAdapter(bVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new ItemDividerDecoration(recyclerView.getResources().getDrawable(R.drawable.list_divider_light)));
        selectView.showWith(bVar);
        selectView.loadingWillBegin();
        CountryUtil.a((com.etsy.android.lib.util.CountryUtil.a) new com.etsy.android.lib.util.CountryUtil.a() {
            public void onCountriesError() {
            }

            public void onCountriesLoaded(ArrayList<Country> arrayList) {
                selectView.loadingDidComplete();
                bVar.a((List<Country>) arrayList, str);
            }
        });
    }
}
