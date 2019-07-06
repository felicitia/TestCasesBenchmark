package com.contextlogic.wish.activity.profile.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.cart.CartServiceFragment;
import com.contextlogic.wish.activity.feed.BaseProductFeedServiceFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import java.util.ArrayList;

public class SelectWishlistDialogFragment extends BaseDialogFragment {
    private SelectWishlistDialogAdapter mAdapter;
    private ImageView mCancelButton;
    private View mCreateWishlistButton;
    private View mDialogView;
    private View mEmptyMessage;
    /* access modifiers changed from: private */
    public int mLastItem;
    private ListView mListView;
    /* access modifiers changed from: private */
    public boolean mNoMoreItems;
    /* access modifiers changed from: private */
    public int mOffset;
    private View mProgressBar;
    private View mTopDivider;
    private ArrayList<WishWishlist> mWishlists;

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.select_wishlist_dialog_fragment, viewGroup, false);
        this.mDialogView = inflate.findViewById(R.id.select_wishlist_dialog_view);
        this.mCancelButton = (ImageView) inflate.findViewById(R.id.select_wishlist_cancel_button);
        this.mCancelButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SelectWishlistDialogFragment.this.cancel();
            }
        });
        this.mCreateWishlistButton = inflate.findViewById(R.id.select_wishlist_add_button);
        this.mCreateWishlistButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SelectWishlistDialogFragment.this.handleCreateWishlist();
            }
        });
        this.mEmptyMessage = inflate.findViewById(R.id.select_wishlist_empty_message);
        this.mTopDivider = inflate.findViewById(R.id.select_wishlist_bottom_divider);
        this.mListView = (ListView) inflate.findViewById(R.id.select_wishlist_listview);
        this.mAdapter = new SelectWishlistDialogAdapter(getContext());
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SelectWishlistDialogFragment.this.handleAddToWishlist(i);
            }
        });
        this.mListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                int i4 = i + i2;
                if (i3 >= 2) {
                    i3 -= 2;
                }
                if (i4 == i3 && SelectWishlistDialogFragment.this.mLastItem != i4) {
                    if (SelectWishlistDialogFragment.this.mNoMoreItems) {
                        SelectWishlistDialogFragment.this.loadWishlists();
                    }
                    SelectWishlistDialogFragment.this.mLastItem = i4;
                }
            }
        });
        if (ExperimentDataCenter.getInstance().shouldSeeNewProgressBar()) {
            this.mProgressBar = inflate.findViewById(R.id.select_wishlist_three_dot_progress_bar);
        } else {
            this.mProgressBar = inflate.findViewById(R.id.select_wishlist_progress_bar);
        }
        this.mWishlists = new ArrayList<>();
        this.mOffset = 0;
        this.mNoMoreItems = false;
        showLoadingSpinner();
        loadWishlists();
        return inflate;
    }

    /* access modifiers changed from: private */
    public void handleCreateWishlist() {
        makeSelection(2000);
    }

    /* access modifiers changed from: private */
    public void handleAddToWishlist(int i) {
        showLoadingSpinner();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ResultWishlist", this.mAdapter.getItem(i));
        makeSelection(bundle);
    }

    /* access modifiers changed from: private */
    public void loadWishlists() {
        if (!this.mNoMoreItems) {
            withServiceFragment(new ServiceTask() {
                public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                    if (serviceFragment instanceof BaseProductFeedServiceFragment) {
                        ((BaseProductFeedServiceFragment) serviceFragment).loadDialogWishlists(SelectWishlistDialogFragment.this.mOffset);
                    } else if (serviceFragment instanceof CartServiceFragment) {
                        ((CartServiceFragment) serviceFragment).loadDialogWishlists(SelectWishlistDialogFragment.this.mOffset);
                    }
                }
            });
        }
    }

    public void handleLoadingSuccess(ArrayList<WishWishlist> arrayList, int i, boolean z) {
        this.mWishlists.addAll(arrayList);
        this.mOffset = i;
        this.mNoMoreItems = z;
        if (this.mWishlists.size() > 0 || !this.mNoMoreItems) {
            this.mDialogView.setVisibility(0);
            this.mAdapter.setWishlists(this.mWishlists);
            hideLoadingSpinner();
            return;
        }
        showNoWishlistsUI();
    }

    private void showLoadingSpinner() {
        this.mProgressBar.setVisibility(0);
        this.mListView.setVisibility(4);
    }

    private void hideLoadingSpinner() {
        this.mProgressBar.setVisibility(8);
        this.mListView.setVisibility(0);
    }

    private void showNoWishlistsUI() {
        hideLoadingSpinner();
        this.mDialogView.setVisibility(0);
        this.mEmptyMessage.setVisibility(0);
        LayoutParams layoutParams = (LayoutParams) this.mCreateWishlistButton.getLayoutParams();
        layoutParams.gravity = 17;
        this.mCreateWishlistButton.setLayoutParams(layoutParams);
        this.mListView.setVisibility(8);
        this.mTopDivider.setVisibility(8);
    }
}
