package com.contextlogic.wish.activity.feed;

import com.contextlogic.wish.activity.DrawerActivity;
import com.contextlogic.wish.api.model.WishProduct;
import java.util.ArrayList;

public class BaseProductFeedAdapter extends BaseProductFeedItemsAdapter<DrawerActivity, BaseProductFeedFragment> {
    private DataProvider mDataProvider;

    public interface DataProvider {
        ArrayList<WishProduct> getData();
    }

    public BaseProductFeedAdapter(DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment) {
        super(drawerActivity, baseProductFeedFragment);
    }

    public BaseProductFeedAdapter(DrawerActivity drawerActivity, BaseProductFeedFragment baseProductFeedFragment, int i) {
        super(drawerActivity, baseProductFeedFragment, i);
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.mDataProvider = dataProvider;
    }

    public ArrayList<WishProduct> getItems() {
        if (this.mDataProvider != null) {
            return this.mDataProvider.getData();
        }
        return null;
    }
}
