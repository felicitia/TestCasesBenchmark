package com.onfido.android.sdk.capture.ui.country_selection;

import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.ui.widget.RecyclerView;
import com.onfido.android.sdk.capture.utils.BottomSheetExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class CountrySelectionFragment$onCreateOptionsMenu$$inlined$let$lambda$1 implements OnActionExpandListener {
    final /* synthetic */ SearchView a;
    final /* synthetic */ CountrySelectionFragment b;
    final /* synthetic */ MenuInflater c;
    final /* synthetic */ Menu d;

    CountrySelectionFragment$onCreateOptionsMenu$$inlined$let$lambda$1(SearchView searchView, CountrySelectionFragment countrySelectionFragment, MenuInflater menuInflater, Menu menu) {
        this.a = searchView;
        this.b = countrySelectionFragment;
        this.c = menuInflater;
        this.d = menu;
    }

    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        this.a.setOnQueryTextListener(null);
        CountrySelectionFragment.access$getCountriesAdapter$p(this.b).setSearchTerm("");
        CountrySelectionFragment.access$getCountriesAdapter$p(this.b).removeItems();
        this.b.getPresenter().getCountrySuggestion(CountrySelectionFragment.access$getDocumentType$p(this.b));
        RecyclerView recyclerView = (RecyclerView) this.b._$_findCachedViewById(R.id.countriesList);
        if (recyclerView != null) {
            recyclerView.setPadding(0, 0, 0, this.b.getResources().getDimensionPixelOffset(R.dimen.onfido_country_selection_list_margin_bottom));
        }
        this.b.a().postDelayed(new Runnable(this) {
            final /* synthetic */ CountrySelectionFragment$onCreateOptionsMenu$$inlined$let$lambda$1 a;

            {
                this.a = r1;
            }

            public final void run() {
                BottomSheetExtensionsKt.collapse(CountrySelectionFragment.access$getBottomSheetBehavior$p(this.a.b));
            }
        }, 200);
        return true;
    }

    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        this.a.setOnQueryTextListener(this.b);
        this.b.onQueryTextChange("");
        RecyclerView recyclerView = (RecyclerView) this.b._$_findCachedViewById(R.id.countriesList);
        if (recyclerView != null) {
            recyclerView.setPadding(0, 0, 0, 0);
        }
        BottomSheetExtensionsKt.hide(CountrySelectionFragment.access$getBottomSheetBehavior$p(this.b));
        return true;
    }
}
