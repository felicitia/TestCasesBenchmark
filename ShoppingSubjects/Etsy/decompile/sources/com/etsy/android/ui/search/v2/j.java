package com.etsy.android.ui.search.v2;

import io.reactivex.functions.Consumer;

final /* synthetic */ class j implements Consumer {
    private final SearchResultsListingsFragment a;

    j(SearchResultsListingsFragment searchResultsListingsFragment) {
        this.a = searchResultsListingsFragment;
    }

    public void accept(Object obj) {
        this.a.lambda$onListingCardShown$2$SearchResultsListingsFragment((Throwable) obj);
    }
}
