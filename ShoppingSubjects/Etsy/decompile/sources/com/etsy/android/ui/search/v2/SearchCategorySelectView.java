package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.a.p;
import com.etsy.android.lib.models.apiv3.FacetCount;
import com.etsy.android.lib.models.editable.EditableListing;
import com.etsy.android.stylekit.e;
import com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchCategorySelectView {

    private static class RootFacetCount extends FacetCount {
        RootFacetCount(String str, List<FacetCount> list) {
            super(EditableListing.LISTING_ID_DEVICE_DRAFT, str, 0, list);
        }
    }

    private static class a extends ViewHolder {
        /* access modifiers changed from: private */
        public final c a;
        /* access modifiers changed from: private */
        public FacetCount b;

        private static TextView a(Context context) {
            TextView textView = new TextView(context);
            e.a(textView, (int) R.string.sk_typeface_normal);
            textView.setLayoutParams(new LayoutParams(-1, -2));
            return textView;
        }

        public a(Context context, c cVar) {
            super(a(context));
            this.a = cVar;
            this.itemView.setOnClickListener(a());
        }

        /* access modifiers changed from: 0000 */
        public void a(FacetCount facetCount, boolean z, boolean z2) {
            this.b = facetCount;
            TextView textView = (TextView) this.itemView;
            textView.setText(facetCount.getName());
            textView.setTextAppearance(this.itemView.getContext(), z ? p.TextBlue_Large : p.TextGrey_Large);
            Resources resources = this.itemView.getResources();
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.fixed_large);
            int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.fixed_xlarge);
            if (!z2) {
                dimensionPixelOffset2 = dimensionPixelOffset;
            }
            textView.setPadding(dimensionPixelOffset2, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
        }

        private OnClickListener a() {
            return new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    a.this.a.a(a.this.b, a.this.getAdapterPosition());
                }
            };
        }
    }

    public interface b {
        void a();

        void a(List<FacetCount> list);
    }

    private static class c extends Adapter<a> implements com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView.a {
        private final b a;
        private final List<FacetCount> b = new ArrayList();
        private List<FacetCount> c = Collections.emptyList();
        private final View d;

        c(View view, Context context, List<FacetCount> list, List<FacetCount> list2, b bVar) {
            this.d = view;
            this.a = bVar;
            this.b.add(new RootFacetCount(context.getResources().getString(R.string.all_categories), list2));
            this.b.addAll(list);
            a();
        }

        /* renamed from: a */
        public a onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new a(viewGroup.getContext(), this);
        }

        private FacetCount d() {
            return (FacetCount) this.b.get(this.b.size() - 1);
        }

        /* renamed from: a */
        public void onBindViewHolder(a aVar, int i) {
            boolean z = true;
            if (i < this.b.size()) {
                FacetCount facetCount = (FacetCount) this.b.get(i);
                if (facetCount != d()) {
                    z = false;
                }
                aVar.a(facetCount, z, false);
                return;
            }
            FacetCount facetCount2 = (FacetCount) this.c.get(i - this.b.size());
            if (this.b.size() <= 1) {
                z = false;
            }
            aVar.a(facetCount2, false, z);
        }

        public int getItemCount() {
            return this.b.size() + this.c.size();
        }

        /* access modifiers changed from: 0000 */
        public void a(FacetCount facetCount, int i) {
            if (i < this.b.size()) {
                this.b.subList(i + 1, this.b.size()).clear();
            } else {
                this.b.add(facetCount);
            }
            a();
            notifyDataSetChanged();
            this.a.a(new ArrayList(this.b.subList(1, this.b.size())));
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.c = new ArrayList(d().getChildren());
            Collections.sort(this.c, FacetCount.byDecreasingCount);
        }

        public View b() {
            return this.d;
        }

        public void c() {
            this.a.a();
        }
    }

    public static void a(SelectView selectView, List<FacetCount> list, List<FacetCount> list2, b bVar, boolean z) {
        Context context = selectView.getContext();
        RecyclerView recyclerView = new RecyclerView(context);
        c cVar = new c(recyclerView, context, list, list2, bVar);
        recyclerView.setAdapter(cVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new ItemDividerDecoration(recyclerView.getResources().getDrawable(R.drawable.list_divider)));
        selectView.showWith(cVar, z);
    }
}
