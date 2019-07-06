package com.contextlogic.wish.activity.pricewatch;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.LoadingUiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarManager.Theme;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishPriceWatchSpec;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.addtocart.AddToCartDialogFragment;
import com.contextlogic.wish.dialog.addtocart.Source;
import com.contextlogic.wish.dialog.addtocart.sizecolorselector.SizeColorSelectorDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import java.util.HashMap;

public class PriceWatchFragment extends LoadingUiFragment<PriceWatchActivity> {
    private PriceWatchItemAdapter mAdapter;
    private View mContentView;
    private PriceWatchHeaderView mHeaderView;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ListView mListView;
    private HashMap<String, WishProduct> mLoadedProducts;
    private TextView mNoItemsActionButton;
    private TextView mNoItemsMessageText;
    private WishPriceWatchSpec mPriceWatchSpec;

    public boolean canPullToRefresh() {
        return false;
    }

    public int getLoadingContentLayoutResourceId() {
        return R.layout.price_watch_fragment;
    }

    public void initializeLoadingContentView(View view) {
        this.mContentView = view.findViewById(R.id.price_watch_fragment);
        this.mContentView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.gray7));
        this.mListView = (ListView) view.findViewById(R.id.price_watch_listview);
        this.mNoItemsMessageText = (TextView) view.findViewById(R.id.price_watch_message);
        this.mNoItemsActionButton = (TextView) view.findViewById(R.id.price_watch_action_button);
        this.mNoItemsActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PriceWatchFragment.this.handleContinueShopping();
            }
        });
        this.mImagePrefetcher = new ImageHttpPrefetcher();
        this.mLoadedProducts = new HashMap<>();
        this.mPriceWatchSpec = null;
        this.mAdapter = new PriceWatchItemAdapter(this, this.mImagePrefetcher);
        this.mHeaderView = new PriceWatchHeaderView(getContext());
        this.mListView.addHeaderView(this.mHeaderView);
        withActivity(new ActivityTask<PriceWatchActivity>() {
            public void performTask(PriceWatchActivity priceWatchActivity) {
                priceWatchActivity.updateToolBarPadding();
                priceWatchActivity.getActionBarManager().setTheme(Theme.TRANSPARENT_BACKGROUND_LIGHT_BUTTONS);
            }
        });
        reload();
    }

    public void restoreImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.resumePrefetching();
        }
    }

    public void releaseImages() {
        if (this.mImagePrefetcher != null) {
            this.mImagePrefetcher.pausePrefetching();
        }
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        super.handleResume();
        handleReload();
    }

    public void handleReload() {
        reload();
    }

    public void reload() {
        this.mPriceWatchSpec = null;
        withServiceFragment(new ServiceTask<BaseActivity, PriceWatchServiceFragment>() {
            public void performTask(BaseActivity baseActivity, PriceWatchServiceFragment priceWatchServiceFragment) {
                priceWatchServiceFragment.loadInfo();
            }
        });
    }

    public boolean hasItems() {
        return this.mPriceWatchSpec != null;
    }

    /* access modifiers changed from: private */
    public void handleContinueShopping() {
        withActivity(new ActivityTask<PriceWatchActivity>() {
            public void performTask(PriceWatchActivity priceWatchActivity) {
                Intent intent = new Intent();
                intent.setClass(priceWatchActivity, BrowseActivity.class);
                priceWatchActivity.startActivity(intent);
            }
        });
    }

    public void handleInfoLoaded(WishPriceWatchSpec wishPriceWatchSpec) {
        this.mPriceWatchSpec = wishPriceWatchSpec;
        this.mHeaderView.setup(wishPriceWatchSpec);
        if (wishPriceWatchSpec.getItems().size() > 0) {
            this.mContentView.setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
            this.mNoItemsActionButton.setVisibility(8);
            this.mNoItemsMessageText.setVisibility(8);
        } else {
            this.mNoItemsActionButton.setVisibility(0);
            this.mNoItemsMessageText.setVisibility(0);
        }
        this.mAdapter.setPriceWatchItems(wishPriceWatchSpec.getItems());
        this.mListView.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
        getLoadingPageView().markLoadingComplete();
    }

    public void handleItemRemove(final String str) {
        withServiceFragment(new ServiceTask<BaseActivity, PriceWatchServiceFragment>() {
            public void performTask(BaseActivity baseActivity, PriceWatchServiceFragment priceWatchServiceFragment) {
                priceWatchServiceFragment.removeItemFromPriceWatch(str);
            }
        });
    }

    public void handleBuyClick(final String str) {
        if (this.mLoadedProducts.get(str) != null) {
            addProductToCart((WishProduct) this.mLoadedProducts.get(str));
        } else {
            withServiceFragment(new ServiceTask<BaseActivity, PriceWatchServiceFragment>() {
                public void performTask(BaseActivity baseActivity, PriceWatchServiceFragment priceWatchServiceFragment) {
                    priceWatchServiceFragment.getProduct(str);
                }
            });
        }
    }

    public void addProductToCart(final WishProduct wishProduct) {
        if (wishProduct != null) {
            if (!wishProduct.isInStock()) {
                withActivity(new ActivityTask<PriceWatchActivity>() {
                    public void performTask(PriceWatchActivity priceWatchActivity) {
                        priceWatchActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(priceWatchActivity.getString(R.string.this_item_is_out_of_stock)));
                    }
                });
            } else if (wishProduct.isCommerceProduct()) {
                final Source source = Source.DEFAULT;
                withActivity(new ActivityTask<PriceWatchActivity>() {
                    public void performTask(PriceWatchActivity priceWatchActivity) {
                        BaseDialogFragment baseDialogFragment;
                        if (ExperimentDataCenter.getInstance().shouldShowSizeColorSelector() || ExperimentDataCenter.getInstance().shouldShowSizeColorSelectorWithTextSwatchesOnly()) {
                            baseDialogFragment = SizeColorSelectorDialogFragment.createSizeColorSelectorDialogFragment(wishProduct, source, false);
                        } else {
                            baseDialogFragment = AddToCartDialogFragment.createAddToCartDialog(wishProduct, source, false);
                        }
                        if (baseDialogFragment != null) {
                            priceWatchActivity.startDialog(baseDialogFragment);
                        }
                    }
                });
            }
        }
    }

    public void handleProductLoadingSuccess(WishProduct wishProduct) {
        this.mLoadedProducts.put(wishProduct.getProductId(), wishProduct);
        addProductToCart(wishProduct);
    }

    public void handleLoadingFailure() {
        withActivity(new ActivityTask<PriceWatchActivity>() {
            public void performTask(PriceWatchActivity priceWatchActivity) {
                priceWatchActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(priceWatchActivity.getString(R.string.could_not_add_to_cart)));
            }
        });
    }
}
