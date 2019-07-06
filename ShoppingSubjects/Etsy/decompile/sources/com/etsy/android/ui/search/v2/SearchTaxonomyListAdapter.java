package com.etsy.android.ui.search.v2;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.logger.i;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.vespa.k;
import java.util.List;

public class SearchTaxonomyListAdapter extends AbstractContextRecyclerViewAdapter<k, Context> {
    private static final int VIEW_TYPE_NAV_GIFT_CARD_CREATE = 3;
    private static final int VIEW_TYPE_TAXONOMY = 1;
    private static final int VIEW_TYPE_TAXONOMY_HEADER = 2;
    /* access modifiers changed from: private */
    public final com.etsy.android.ui.search.v2.SearchTaxonomyListLayout.a mListener;
    /* access modifiers changed from: private */
    public final boolean mShowTrendingSearches;

    private static class a extends ViewHolder {
        private final TextView a;

        public a(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
            super(layoutInflater.inflate(z ? R.layout.list_item_search_taxonomy_header : R.layout.layout_header_search_taxonomy, viewGroup, false));
            this.a = (TextView) this.itemView.findViewById(R.id.search_taxonomy_header);
        }

        public void a(d dVar) {
            this.a.setText(dVar.a);
        }
    }

    class b implements k {
        public int getViewType() {
            return R.id.view_type_undefined;
        }

        b() {
        }
    }

    private class c extends ViewHolder {
        private final ImageView b;
        private final TextView c;
        private final TextView d;

        public c(ViewGroup viewGroup) {
            super(SearchTaxonomyListAdapter.this.mInflater.inflate(SearchTaxonomyListAdapter.this.mShowTrendingSearches ? R.layout.list_item_search_nav_item : R.layout.layout_header_search_top_nav, viewGroup, false));
            this.b = (ImageView) this.itemView.findViewById(R.id.nav_item_image);
            this.c = (TextView) this.itemView.findViewById(R.id.nav_item_text);
            this.d = (TextView) this.itemView.findViewById(R.id.nav_item_subtext);
        }

        public void a() {
            if (getItemViewType() == 3) {
                if (this.b != null) {
                    this.b.setImageResource(R.drawable.ic_taxonomy_node_giftcard);
                }
                this.c.setText(R.string.etsy_gift_cards);
                if (this.d != null) {
                    this.d.setVisibility(8);
                }
                this.itemView.setOnClickListener(new TrackingOnClickListener() {
                    public void onViewClick(View view) {
                        com.etsy.android.ui.nav.e.a((FragmentActivity) view.getContext()).a().h();
                        com.etsy.android.lib.logger.legacy.b.a().d("open_create_gift_card", "search");
                    }
                });
            }
        }
    }

    class d implements k {
        int a;

        public int getViewType() {
            return R.id.view_type_undefined;
        }

        public d(int i) {
            this.a = i;
        }
    }

    private class e extends ViewHolder {
        private final ImageView b;
        private final TextView c;

        public e(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(SearchTaxonomyListAdapter.this.mShowTrendingSearches ? R.layout.list_item_search_taxonomy_category : R.layout.item_search_taxonomy, viewGroup, false));
            this.b = (ImageView) this.itemView.findViewById(R.id.search_taxonomy_image);
            this.c = (TextView) this.itemView.findViewById(R.id.search_taxonomy_text);
        }

        public void a(final TaxonomyNode taxonomyNode) {
            this.c.setText(taxonomyNode.getName());
            if (this.b != null) {
                this.b.setImageResource(o.a(taxonomyNode.getPath()));
            }
            this.itemView.setOnClickListener(new TrackingOnClickListener(new i[]{taxonomyNode}) {
                public void onViewClick(View view) {
                    if (SearchTaxonomyListAdapter.this.mListener != null) {
                        SearchTaxonomyListAdapter.this.mListener.onTaxonomySelected(taxonomyNode);
                    }
                }
            });
        }
    }

    public ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    public SearchTaxonomyListAdapter(Context context, List<TaxonomyNode> list, com.etsy.android.ui.search.v2.SearchTaxonomyListLayout.a aVar, boolean z) {
        super(context, null);
        this.mListener = aVar;
        this.mShowTrendingSearches = z;
        addItem(new d(this.mShowTrendingSearches ? R.string.shop_by_category : R.string.all_categories));
        for (TaxonomyNode addItem : list) {
            addItem(addItem);
        }
        if (com.etsy.android.lib.config.e.c()) {
            addItem(new d(R.string.new_search_more_ways_to_shop));
            addItem(new b());
        }
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        k kVar = (k) getItem(i);
        if (kVar.getClass().equals(TaxonomyNode.class)) {
            return 1;
        }
        if (kVar.getClass().equals(d.class)) {
            return 2;
        }
        return kVar.getClass().equals(b.class) ? 3 : 0;
    }

    public ViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new e(this.mInflater, viewGroup);
            case 2:
                return new a(this.mInflater, viewGroup, this.mShowTrendingSearches);
            case 3:
                return new c(viewGroup);
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        switch (getListItemViewType(i)) {
            case 1:
                ((e) viewHolder).a((TaxonomyNode) getItem(i));
                return;
            case 2:
                ((a) viewHolder).a((d) getItem(i));
                return;
            case 3:
                ((c) viewHolder).a();
                return;
            default:
                return;
        }
    }
}
