package com.etsy.android.ui.favorites;

public class FavoriteShopsFragment extends FavoriteItemsFragment {
    public String getApiUrl() {
        return String.format("/etsyapps/v3/bespoke/member/users/%s/favorite-shops-page", new Object[]{getUserId().getId()});
    }
}
