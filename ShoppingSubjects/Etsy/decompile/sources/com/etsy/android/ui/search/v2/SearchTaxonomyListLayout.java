package com.etsy.android.ui.search.v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.b;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.TaxonomyNode;
import com.etsy.android.uikit.viewholder.ItemDividerDecoration;
import java.util.ArrayList;
import java.util.List;
import org.parceler.d;

public class SearchTaxonomyListLayout extends LinearLayout {
    private static final String STATE_DATA = "data";
    private static final String STATE_SUPER = "super";
    private View mError;
    private RecyclerView mList;
    private a mListener;
    private View mLoading;
    private boolean mShowTrendingSearches;
    private ArrayList<TaxonomyNode> mTaxonomyData;

    public interface a {
        void onTaxonomySelected(TaxonomyNode taxonomyNode);
    }

    public SearchTaxonomyListLayout(Context context) {
        super(context);
        init(context);
    }

    public SearchTaxonomyListLayout(Context context, boolean z) {
        super(context);
        this.mShowTrendingSearches = z;
        init(context);
    }

    public SearchTaxonomyListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SearchTaxonomyListLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public SearchTaxonomyListLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    private void init(Context context) {
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.layout_search_taxonomy_list, this, true);
        this.mLoading = findViewById(R.id.loading_indicator);
        this.mError = findViewById(R.id.error_view);
        this.mList = (RecyclerView) findViewById(R.id.new_search_taxonomy_recyclerview);
        this.mList.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.mList.addItemDecoration(new ItemDividerDecoration((Drawable) new ColorDrawable(0)));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
            if (bundle.containsKey("data")) {
                setTaxonomyData((List) d.a(bundle.getParcelable("data")));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        displayLoading();
        if (this.mTaxonomyData == null) {
            loadTaxonomy();
        } else {
            displayTaxonomy();
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        if (this.mTaxonomyData != null) {
            bundle.putParcelable("data", d.a(this.mTaxonomyData));
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        v.a().j().a((Object) this);
    }

    private void loadTaxonomy() {
        v.a().j().a((Object) this, (g<Result>) m.a(TaxonomyNode.class, "/etsyapps/v3/public/taxonomy/buyer").a(true).a("roots_only", Boolean.toString(true)).a((c<Result>) new c<TaxonomyNode>() {
            public void a(List<TaxonomyNode> list, int i, k<TaxonomyNode> kVar) {
                SearchTaxonomyListLayout.this.setTaxonomyData(list);
                SearchTaxonomyListLayout.this.displayTaxonomy();
            }
        }).a((b) new b() {
            public void a(int i, String str, k kVar) {
                SearchTaxonomyListLayout.this.displayError();
            }
        }).a());
    }

    /* access modifiers changed from: private */
    public void setTaxonomyData(List<TaxonomyNode> list) {
        this.mTaxonomyData = new ArrayList<>(list);
    }

    private void displayLoading() {
        this.mLoading.setVisibility(0);
        this.mError.setVisibility(8);
        this.mList.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void displayTaxonomy() {
        this.mList.setAdapter(new SearchTaxonomyListAdapter(getContext(), this.mTaxonomyData, this.mListener, this.mShowTrendingSearches));
        this.mList.setVisibility(0);
        this.mLoading.setVisibility(8);
        this.mError.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void displayError() {
        this.mList.setVisibility(8);
        this.mLoading.setVisibility(8);
        this.mError.setVisibility(0);
    }

    public void setListener(a aVar) {
        this.mListener = aVar;
    }
}
