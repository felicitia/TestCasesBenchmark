package com.contextlogic.wish.activity.productdetails;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;

public enum FilterType {
    ALL(R.string.filter_selection_all, 0, 0, null, WishAnalyticsEvent.CLICK_REVIEW_FILTER_ALL),
    PHOTO(R.string.filter_selection_photo, R.drawable.sort_media_image, 0, "has_image", WishAnalyticsEvent.CLICK_REVIEW_FILTER_PHOTO),
    TOP(R.string.filter_selection_top, 0, 0, "top_rated", WishAnalyticsEvent.CLICK_REVIEW_FILTER_TOP),
    RATING_1(R.string.filter_selection_rating, R.drawable.sortreview_1star, 1, "rating", WishAnalyticsEvent.CLICK_REVIEW_FILTER_STAR_1),
    RATING_2(R.string.filter_selection_rating, R.drawable.sortreview_2stars, 2, "rating", WishAnalyticsEvent.CLICK_REVIEW_FILTER_STAR_2),
    RATING_3(R.string.filter_selection_rating, R.drawable.sortreview_3stars, 3, "rating", WishAnalyticsEvent.CLICK_REVIEW_FILTER_STAR_3),
    RATING_4(R.string.filter_selection_rating, R.drawable.sortreview_4stars, 4, "rating", WishAnalyticsEvent.CLICK_REVIEW_FILTER_STAR_4),
    RATING_5(R.string.filter_selection_rating, R.drawable.sortreview_5stars, 5, "rating", WishAnalyticsEvent.CLICK_REVIEW_FILTER_STAR_5);
    
    private final int mDrawableRes;
    public final String mFilterType;
    public final WishAnalyticsEvent mLogEvent;
    public final int mRatingValue;
    private final int mStringRes;

    private FilterType(int i, int i2, int i3, String str, WishAnalyticsEvent wishAnalyticsEvent) {
        this.mStringRes = i;
        this.mDrawableRes = i2;
        this.mRatingValue = i3;
        this.mFilterType = str;
        this.mLogEvent = wishAnalyticsEvent;
    }

    public String toDisplayString(Context context) {
        if (this.mRatingValue <= 0) {
            return context.getString(this.mStringRes);
        }
        return context.getString(this.mStringRes, new Object[]{Integer.valueOf(this.mRatingValue)});
    }

    public Drawable toDisplayDrawable(Context context) {
        if (this.mDrawableRes > 0) {
            return ContextCompat.getDrawable(context, this.mDrawableRes);
        }
        return null;
    }
}
