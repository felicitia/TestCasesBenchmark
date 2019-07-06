package com.etsy.android.ui.search.v2;

import com.etsy.android.ui.search.SortOrder;

public final /* synthetic */ class d {
    public static final /* synthetic */ int[] a = new int[SortOrder.values().length];

    static {
        a[SortOrder.RELEVANCY.ordinal()] = 1;
        a[SortOrder.MOST_RECENT.ordinal()] = 2;
        a[SortOrder.HIGHEST_PRICE.ordinal()] = 3;
        a[SortOrder.LOWEST_PRICE.ordinal()] = 4;
    }
}
