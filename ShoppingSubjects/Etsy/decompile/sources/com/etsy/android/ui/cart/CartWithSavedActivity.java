package com.etsy.android.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.etsy.android.R;
import com.etsy.android.lib.config.b.e;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.Cart;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.messaging.CartRefreshDelegate;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.cart.googlewallet.a.C0087a;
import com.etsy.android.ui.util.d;
import com.etsy.android.uikit.view.EtsyTabLayout;
import com.etsy.android.uikit.view.FragmentViewPager;

public class CartWithSavedActivity extends BOENavDrawerActivity implements OnPageChangeListener, com.etsy.android.lib.core.b.a, com.etsy.android.messaging.CartRefreshDelegate.a, C0087a {
    public static final String CART_ERROR_MESSAGE = "cart_error_message";
    public static final String CART_IDS = "cart_ids";
    public static final String CHECKED_OUT_CART = "checked_out_cart";
    public static final String CHECKED_OUT_CART_GROUP_ID = "checked_out_cart_group_id";
    public static final String CHECKED_OUT_IS_MSCO = "checkout_out_is_msco";
    public static final String CHECKED_OUT_PAYMENT_METHOD = "checked_out_payment_method";
    public static final String CURRENT_CART_ID = "current_cart_id";
    private static final String FRAGMENT_MULTISHOP_TAG = "multishopCartFragment";
    private static final String FRAGMENT_TAG = "cartFragment";
    public static final String INT_CART_ID = "cart_id";
    private static final String KEY_COUNT_CART = "key_count_cart";
    private static final String KEY_COUNT_SAVED = "key_count_saved";
    private static final String KEY_ENABLE_MSCO = "key_enable_msco";
    public static final String LAST_ORDER_ID = "last_order_id";
    public static final String NAV_TO_SAVED = "nav_to_saved";
    public static final String PAYMENT_METHOD = "payment_method";
    private static final String TAG = f.a(CartWithSavedActivity.class);
    final int POS_CART = 0;
    final int POS_SAVED = 1;
    private a mAdapter;
    private CartRefreshDelegate mCartRefreshDelegate;
    private com.etsy.android.ui.cart.googlewallet.a mGoogleWalletHelper;
    private EtsyTabLayout mSlidingTabLayout;
    private FragmentViewPager mViewPager;

    class a extends FragmentPagerAdapter {
        int a = 0;
        int b = 0;
        MultiShopCartFragment c;
        SavedCartItemsFragment d;
        private final long f = 0;
        private final long g = 1;
        private final long h = 2;

        public long getItemId(int i) {
            switch (i) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                default:
                    return -1;
            }
        }

        public a() {
            super(CartWithSavedActivity.this.getSupportFragmentManager());
        }

        public Fragment getItem(int i) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(CartWithSavedActivity.CHECKED_OUT_IS_MSCO, true);
            switch (i) {
                case 0:
                    if (this.c == null) {
                        this.c = a(bundle);
                    }
                    return this.c;
                case 1:
                    if (this.d == null) {
                        this.d = new SavedCartItemsFragment();
                    }
                    return this.d;
                default:
                    return null;
            }
        }

        public int getCount() {
            return v.a().e() ? 2 : 1;
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return CartWithSavedActivity.this.getString(R.string.tab_title_cart, new Object[]{Integer.valueOf(this.a)});
                case 1:
                    return CartWithSavedActivity.this.getString(R.string.tab_title_saved, new Object[]{Integer.valueOf(this.b)});
                default:
                    return "<unknown>";
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        /* access modifiers changed from: private */
        @Nullable
        public Fragment a(FragmentViewPager fragmentViewPager, int i) {
            return CartWithSavedActivity.this.getSupportFragmentManager().findFragmentByTag(FragmentViewPager.createFragmentName(fragmentViewPager.getId(), getItemId(i)));
        }

        private MultiShopCartFragment a(@NonNull Bundle bundle) {
            MultiShopCartFragment multiShopCartFragment = new MultiShopCartFragment();
            Intent intent = CartWithSavedActivity.this.getIntent();
            if (intent != null && intent.hasExtra(CartWithSavedActivity.CHECKED_OUT_CART) && intent.hasExtra(CartWithSavedActivity.LAST_ORDER_ID)) {
                AndroidPayDataContract androidPayDataContract = (AndroidPayDataContract) intent.getSerializableExtra(CartWithSavedActivity.CHECKED_OUT_CART);
                EtsyId etsyId = (EtsyId) intent.getSerializableExtra(CartWithSavedActivity.LAST_ORDER_ID);
                bundle.putSerializable(CartWithSavedActivity.CHECKED_OUT_CART, androidPayDataContract);
                bundle.putSerializable(CartWithSavedActivity.LAST_ORDER_ID, etsyId);
            }
            multiShopCartFragment.setArguments(bundle);
            return multiShopCartFragment;
        }
    }

    public interface b {
        void onCartPageSelected();
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getString(R.string.title_cart));
        setContentView((int) R.layout.activity_cart_with_saved);
        this.mCartRefreshDelegate = new CartRefreshDelegate(this, this);
        this.mViewPager = (FragmentViewPager) findViewById(R.id.view_pager);
        this.mViewPager.setOffscreenPageLimit(2);
        this.mSlidingTabLayout = (EtsyTabLayout) getAppBarHelper().addTabLayout();
        this.mSlidingTabLayout.setVisibility(8);
        if (getConfigMap().c(e.a)) {
            this.mGoogleWalletHelper = new com.etsy.android.ui.cart.googlewallet.a(this);
        }
        displayCartWithTabs();
        if (bundle != null) {
            onCartCountsUpdated(bundle.getInt(KEY_COUNT_CART), bundle.getInt(KEY_COUNT_SAVED), false, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mCartRefreshDelegate.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mCartRefreshDelegate.onPause();
    }

    public void onDestroy() {
        this.mGoogleWalletHelper = null;
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mAdapter != null) {
            bundle.putInt(KEY_COUNT_SAVED, this.mAdapter.b);
            bundle.putInt(KEY_COUNT_CART, this.mAdapter.a);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        int i;
        if (this.mViewPager != null) {
            String stringExtra = intent.getStringExtra(ResponseConstants.PAGE_LINK);
            if (stringExtra != null) {
                i = stringExtra.equals("saved");
                intent.removeExtra(ResponseConstants.PAGE_LINK);
            } else {
                i = 0;
            }
            if (intent.getBooleanExtra(NAV_TO_SAVED, false)) {
                i = 1;
            }
            this.mViewPager.setCurrentItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onUserSignedIn() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
            showTabs();
        }
    }

    /* access modifiers changed from: protected */
    public void onUserSignedOut() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
            hideTabs();
        }
    }

    private void displayCartWithTabs() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CHECKED_OUT_CART) && (intent.getSerializableExtra(CHECKED_OUT_CART) instanceof Cart) && intent.getBooleanExtra("should_show_social_invites_prompt", false)) {
            d.a((FragmentActivity) this, (com.etsy.android.lib.logger.b) getAnalyticsContext(), (BaseModel) ((Cart) intent.getSerializableExtra(CHECKED_OUT_CART)).getCartListings().get(0), true);
        }
        this.mViewPager.setVisibility(0);
        if (this.mAdapter == null) {
            this.mAdapter = new a();
        }
        this.mViewPager.setAdapter(this.mAdapter);
        if (v.a().e()) {
            showTabs();
        }
        if (intent != null && intent.getBooleanExtra(NAV_TO_SAVED, false)) {
            this.mViewPager.setCurrentItem(1);
            intent.removeExtra(NAV_TO_SAVED);
        }
        this.mViewPager.addOnPageChangeListener(this);
    }

    private void showTabs() {
        if (this.mSlidingTabLayout != null) {
            this.mSlidingTabLayout.setVisibility(0);
            this.mSlidingTabLayout.setupWithViewPager(this.mViewPager);
        }
    }

    private void hideTabs() {
        if (this.mSlidingTabLayout != null) {
            this.mSlidingTabLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        int count = this.mAdapter.getCount();
        for (int i3 = 0; i3 < count; i3++) {
            this.mAdapter.getItem(i3).onActivityResult(i, i2, intent);
        }
    }

    public com.etsy.android.ui.cart.googlewallet.a getGoogleWalletHelper() {
        return this.mGoogleWalletHelper;
    }

    public void onCartCountsUpdated(int i, int i2, boolean z, int i3) {
        if (this.mSlidingTabLayout.getTabCount() > 0) {
            this.mSlidingTabLayout.setTabText(0, getString(R.string.tab_title_cart, new Object[]{Integer.valueOf(i)}));
            if (v.a().e()) {
                this.mSlidingTabLayout.setTabText(1, getString(R.string.tab_title_saved, new Object[]{Integer.valueOf(i2)}));
            }
            this.mAdapter.a(i, i2);
        }
    }

    public void onPageSelected(int i) {
        Fragment a2 = this.mAdapter.a(this.mViewPager, i);
        if (a2 != null && (a2 instanceof b)) {
            ((b) a2).onCartPageSelected();
        }
    }
}
