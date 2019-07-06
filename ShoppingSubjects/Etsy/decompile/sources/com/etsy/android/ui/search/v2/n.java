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
import com.etsy.android.ui.search.SortOrder;
import com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import com.etsy.android.uikit.viewholder.TextViewHolder;
import com.etsy.android.uikit.viewholder.TextViewHolder.c;

/* compiled from: SearchSortOrderSelectView */
public class n {

    /* compiled from: SearchSortOrderSelectView */
    public interface a {
        void a(SortOrder sortOrder);
    }

    /* compiled from: SearchSortOrderSelectView */
    private static class b extends Adapter<TextViewHolder> implements com.etsy.android.ui.search.v2.SearchFiltersSheet.SelectView.a, c {
        private static final SortOrder[] d = {SortOrder.RELEVANCY, SortOrder.MOST_RECENT, SortOrder.HIGHEST_PRICE, SortOrder.LOWEST_PRICE};
        private final View a;
        private SortOrder b;
        private final a c;

        public void c() {
        }

        b(View view, SortOrder sortOrder, a aVar) {
            this.a = view;
            this.b = sortOrder;
            this.c = aVar;
            setHasStableIds(true);
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

        public long getItemId(int i) {
            return (long) d[i].ordinal();
        }

        /* renamed from: a */
        public void onBindViewHolder(TextViewHolder textViewHolder, int i) {
            SortOrder sortOrder = d[i];
            TextView textView = textViewHolder.getTextView();
            textView.setText(SortOrder.getSortOrderLabel(textView.getContext(), sortOrder));
            textView.setTextAppearance(textView.getContext(), sortOrder == this.b ? p.TextBlue_Large : p.TextGrey_Large);
        }

        public int getItemCount() {
            return d.length;
        }

        public void a(TextViewHolder textViewHolder) {
            SortOrder sortOrder = d[textViewHolder.getAdapterPosition()];
            SortOrder sortOrder2 = this.b;
            this.b = sortOrder;
            int length = d.length;
            for (int i = 0; i < length; i++) {
                if (d[i] == sortOrder || d[i] == sortOrder2) {
                    notifyItemChanged(i);
                }
            }
            this.c.a(sortOrder);
        }

        public View b() {
            return this.a;
        }
    }

    public static void a(SelectView selectView, SortOrder sortOrder, a aVar) {
        Context context = selectView.getContext();
        RecyclerView recyclerView = new RecyclerView(context);
        b bVar = new b(recyclerView, sortOrder, aVar);
        recyclerView.setAdapter(bVar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new ItemDividerDecoration(recyclerView.getResources().getDrawable(R.drawable.list_divider_light)));
        selectView.showWith(bVar);
    }
}
