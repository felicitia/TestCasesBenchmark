package com.etsy.android.ui.adapters;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.w;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.FavoriteList;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.models.interfaces.ShopLike;
import com.etsy.android.ui.util.a;
import com.etsy.android.ui.util.a.C0108a;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.adapter.AdvancedModelArrayAdapter;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class CardListAdapter extends AdvancedModelArrayAdapter<BaseModel> {
    private static final String TAG = f.a(CardListAdapter.class);
    Reference<Fragment> mCallingFragment;
    private a mCardUtil;
    private int mItemCountIntResource;
    private int mNumCardsShown;
    private int mNumItemsPerCard;
    private boolean mRemoveFirstRowTopPadding;
    private boolean mShowRatingsOnShop;

    public CardListAdapter(FragmentActivity fragmentActivity, int i, c cVar, @NonNull w wVar) {
        super(fragmentActivity, i, cVar);
        this.mShowRatingsOnShop = false;
        this.mRemoveFirstRowTopPadding = false;
        this.mCallingFragment = null;
        this.mCardUtil = new a(fragmentActivity, cVar, wVar);
        this.mItemCountIntResource = R.integer.card_item_list_count;
        setItemCounts();
    }

    public CardListAdapter(Fragment fragment, BaseActivity baseActivity, int i, c cVar, @NonNull w wVar) {
        this(baseActivity, i, cVar, wVar);
        this.mCallingFragment = new WeakReference(fragment);
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        setItemCounts();
    }

    private void setItemCounts() {
        Resources resources = getContext().getResources();
        this.mNumItemsPerCard = resources.getInteger(this.mItemCountIntResource);
        this.mNumCardsShown = resources.getInteger(R.integer.search_summary_cards_visible);
    }

    public void setItemCountIntResource(int i) {
        this.mItemCountIntResource = i;
    }

    public void setShowRatingsOnShop(boolean z) {
        this.mShowRatingsOnShop = z;
    }

    public void setRemoveFirstRowTopPadding(boolean z) {
        this.mRemoveFirstRowTopPadding = z;
    }

    public int getItemViewType(int i) {
        if (getContext().getResources().getConfiguration().orientation == 1) {
            return super.getItemViewType(i) + 0;
        }
        return super.getItemViewType(i) + 3;
    }

    public int getRealCount() {
        if (!this.mHasFooter || this.mNumCardsShown >= super.getRealCount()) {
            return super.getRealCount();
        }
        return this.mNumCardsShown;
    }

    public View getViewDefault(int i, View view, ViewGroup viewGroup) {
        C0108a aVar;
        Fragment fragment = null;
        if (view == null) {
            view = getLayoutInflater().inflate(getLayoutId(), null, false);
            aVar = a.a(view);
            view.setTag(aVar);
        } else {
            aVar = (C0108a) view.getTag();
        }
        BaseModel item = getItem(i);
        if (this.mRemoveFirstRowTopPadding) {
            if (i == 0) {
                view.setPadding(view.getPaddingLeft(), 0, view.getPaddingRight(), view.getPaddingBottom());
            } else {
                view.setPadding(view.getPaddingLeft(), getContext().getResources().getDimensionPixelSize(R.dimen.margin_medium_large), view.getPaddingRight(), view.getPaddingBottom());
            }
        }
        if (item instanceof ShopLike) {
            this.mCardUtil.a(aVar, (ShopLike) item, this.mNumItemsPerCard, this.mShowRatingsOnShop);
        } else if (item instanceof User) {
            this.mCardUtil.a(aVar, (User) item, this.mNumItemsPerCard);
        } else if (item instanceof Collection) {
            a aVar2 = this.mCardUtil;
            Collection collection = (Collection) item;
            int i2 = this.mNumItemsPerCard;
            if (this.mCallingFragment != null) {
                fragment = (Fragment) this.mCallingFragment.get();
            }
            aVar2.a(aVar, collection, i2, fragment);
        } else if (item instanceof FavoriteList) {
            this.mCardUtil.a(aVar, (FavoriteList) item, this.mNumItemsPerCard);
        } else {
            throw new ClassCastException("CardListAdapter cannot be used with unsupported BaseModel types.");
        }
        return view;
    }

    @CallSuper
    public void refreshActivity(FragmentActivity fragmentActivity) {
        super.refreshActivity(fragmentActivity);
        this.mCardUtil.a((Activity) fragmentActivity);
    }
}
