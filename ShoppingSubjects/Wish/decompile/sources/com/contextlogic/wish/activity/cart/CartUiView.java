package com.contextlogic.wish.activity.cart;

import android.os.Bundle;
import android.widget.FrameLayout;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.List;

public abstract class CartUiView extends FrameLayout implements ImageRestorable, Recyclable {
    private CartFragment mCartFragment;

    public List<WishAnalyticsEvent> getWishAnalyticImpressionEvents() {
        return null;
    }

    public void handleSaveInstanceState(Bundle bundle) {
    }

    public abstract void initializeUi(Bundle bundle);

    public abstract boolean onBackPressed();

    public void onKeyboardVisiblityChanged(boolean z) {
    }

    public abstract void refreshUi();

    public abstract void updateActionBar();

    public CartUiView(CartFragment cartFragment, CartActivity cartActivity, Bundle bundle) {
        super(cartActivity);
        this.mCartFragment = cartFragment;
    }

    public CartFragment getCartFragment() {
        return this.mCartFragment;
    }
}
