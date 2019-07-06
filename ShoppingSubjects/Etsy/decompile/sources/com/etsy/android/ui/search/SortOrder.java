package com.etsy.android.ui.search;

import android.content.Context;
import com.etsy.android.R;

public enum SortOrder {
    MOST_RECENT,
    RELEVANCY,
    HIGHEST_PRICE,
    LOWEST_PRICE;
    
    public static final SortOrder DEFAULT = null;

    static {
        DEFAULT = RELEVANCY;
    }

    public static String getSortOrderLabel(Context context, SortOrder sortOrder) {
        switch (sortOrder) {
            case RELEVANCY:
                return context.getResources().getString(R.string.sort_order_relevancy);
            case MOST_RECENT:
                return context.getResources().getString(R.string.sort_order_most_recent);
            case HIGHEST_PRICE:
                return context.getResources().getString(R.string.sort_order_highest_price);
            case LOWEST_PRICE:
                return context.getResources().getString(R.string.sort_order_lowest_price);
            default:
                return "";
        }
    }
}
