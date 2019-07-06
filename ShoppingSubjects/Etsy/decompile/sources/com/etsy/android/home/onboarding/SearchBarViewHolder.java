package com.etsy.android.home.onboarding;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.etsy.android.R;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.vespa.SearchBar;
import com.etsy.android.ui.search.b;
import com.etsy.android.ui.search.b.a;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: SearchBarViewHolder.kt */
public final class SearchBarViewHolder extends BaseViewHolder<SearchBar> {
    private final ViewGroup parent;
    private final b searchViewHelper;

    public SearchBarViewHolder(ViewGroup viewGroup) {
        p.b(viewGroup, ResponseConstants.PARENT);
        super(new FrameLayout(viewGroup.getContext()));
        this.parent = viewGroup;
        Context context = this.parent.getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
        }
        this.searchViewHelper = new a((FragmentActivity) context).a().b();
        View view = this.itemView;
        if (view == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        ViewGroup viewGroup2 = (ViewGroup) view;
        b bVar = this.searchViewHelper;
        p.a((Object) bVar, "searchViewHelper");
        viewGroup2.addView(bVar.e());
        Context context2 = ((ViewGroup) this.itemView).getContext();
        p.a((Object) context2, ResponseConstants.CONTEXT);
        int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.sk_space_1);
        int dimensionPixelSize2 = context2.getResources().getDimensionPixelSize(R.dimen.sk_space_6);
        this.itemView.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
    }

    public final ViewGroup getParent() {
        return this.parent;
    }

    public void bind(SearchBar searchBar) {
        p.b(searchBar, "data");
        this.searchViewHelper.a(searchBar.getHint());
        this.searchViewHelper.a((OnQueryTextListener) new SearchBarViewHolder$bind$1(this, searchBar));
    }
}
