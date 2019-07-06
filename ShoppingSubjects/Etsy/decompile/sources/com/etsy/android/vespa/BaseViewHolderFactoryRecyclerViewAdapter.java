package com.etsy.android.vespa;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.cardviewelement.LoadingCardViewElement;
import com.etsy.android.lib.models.cardviewelement.PageLink;
import com.etsy.android.lib.util.b.a;
import com.etsy.android.uikit.adapter.EndlessRecyclerViewAdapter;
import com.etsy.android.vespa.viewholders.BaseViewHolder;
import java.util.ArrayList;
import org.parceler.d;

public class BaseViewHolderFactoryRecyclerViewAdapter extends EndlessRecyclerViewAdapter<k> implements d {
    private static final String KEY_ADAPTER_ID = "KEY_ADAPTER_ID";
    private static final String TAG = f.a(BaseViewHolderFactoryRecyclerViewAdapter.class);
    @Nullable
    private a mAdapterCache;
    private String mAdapterCacheId;
    private boolean mEditable;
    private boolean mIsNestedSectionAdapter;
    @Nullable
    private f mServerDrivenActionDelegate;
    protected SpanSizeLookup mSpanSizeLookup;
    private c mViewHolderFactory;
    @NonNull
    protected b mViewTracker;

    /* access modifiers changed from: protected */
    @NonNull
    public c createViewHolderFactory(@Nullable a aVar) {
        return new c(this.mContext, this.mViewTracker, this, this.mServerDrivenActionDelegate);
    }

    public BaseViewHolderFactoryRecyclerViewAdapter(FragmentActivity fragmentActivity, @NonNull b bVar) {
        this(fragmentActivity, bVar, null);
    }

    public BaseViewHolderFactoryRecyclerViewAdapter(FragmentActivity fragmentActivity, @NonNull b bVar, @Nullable f fVar) {
        this(fragmentActivity, bVar, fVar, a.a(), null);
    }

    public BaseViewHolderFactoryRecyclerViewAdapter(FragmentActivity fragmentActivity, @NonNull b bVar, @Nullable f fVar, @Nullable a aVar) {
        this(fragmentActivity, bVar, fVar, a.a(), aVar);
    }

    protected BaseViewHolderFactoryRecyclerViewAdapter(FragmentActivity fragmentActivity, @NonNull b bVar, @Nullable f fVar, @Nullable a aVar, @Nullable a aVar2) {
        super(fragmentActivity, new c());
        this.mEditable = false;
        this.mSpanSizeLookup = new SpanSizeLookup() {
            public int getSpanSize(int i) {
                return BaseViewHolderFactoryRecyclerViewAdapter.this.getViewHolderFactory().a(((k) BaseViewHolderFactoryRecyclerViewAdapter.this.getItem(i)).getViewType(), i);
            }
        };
        this.mViewTracker = bVar;
        this.mServerDrivenActionDelegate = fVar;
        this.mViewHolderFactory = createViewHolderFactory(aVar2);
        this.mAdapterCache = aVar;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mViewTracker.b());
        sb.append(this.mViewTracker.a());
        this.mAdapterCacheId = sb.toString();
    }

    public int getListItemViewType(int i) {
        return this.mViewHolderFactory.a((k) getItems().get(i));
    }

    /* access modifiers changed from: protected */
    public void onBindListItemViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof BaseViewHolder) {
            ((BaseViewHolder) viewHolder).bind(getItems().get(i));
        }
    }

    public BaseViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return this.mViewHolderFactory.a(viewGroup, i);
    }

    public void addLoadingIndicator() {
        addItem(new LoadingCardViewElement());
    }

    public void removeLoadingIndicator() {
        ArrayList arrayList = this.mItems;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((k) arrayList.get(size)).getViewType() == i.view_type_loading) {
                removeItem(size);
                return;
            }
        }
    }

    public void setIsNestedSectionAdapter(boolean z) {
        this.mIsNestedSectionAdapter = z;
    }

    public void setAdapterCacheId(String str) {
        this.mAdapterCacheId = str;
    }

    public Parcelable onSaveInstanceState() {
        removeLoadingIndicator();
        if (this.mAdapterCache != null) {
            try {
                this.mAdapterCache.a(this.mAdapterCacheId, d.a(this.mItems));
            } catch (Exception e) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("One of the ICardViewElements is not parcelable: ");
                sb.append(e);
                f.e(str, sb.toString());
                com.google.a.a.a.a.a.a.a(e);
            }
        }
        return d.a(this.mAdapterCacheId);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        String str = (String) d.a(parcelable);
        if (str != null && this.mAdapterCache != null) {
            Parcelable a = this.mAdapterCache.a(str);
            if (a != null) {
                this.mItems.clear();
                addItems((ArrayList) d.a(a));
            }
        }
    }

    @Deprecated
    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable(KEY_ADAPTER_ID, onSaveInstanceState());
        }
    }

    @Deprecated
    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            onRestoreInstanceState(bundle.getParcelable(KEY_ADAPTER_ID));
        }
    }

    @NonNull
    public c getViewHolderFactory() {
        return this.mViewHolderFactory;
    }

    public void registerClickHandler(int i, b bVar) {
        this.mViewHolderFactory.a(i, bVar);
    }

    public void addPage(j jVar) {
        for (h addListSection : jVar.getListSections()) {
            addListSection(addListSection);
        }
        notifyDataSetChanged();
    }

    public void addListSection(h hVar) {
        if (hVar.getItems() != null && hVar.getItems().size() > 0) {
            if (hVar.getHeader() != null) {
                if (hVar.isNested() && this.mIsNestedSectionAdapter) {
                    addItem(hVar.getHeader());
                } else if (!hVar.isNested() && !this.mIsNestedSectionAdapter) {
                    addItem(hVar.getHeader());
                }
            }
            if ((hVar.isHorizontal() || hVar.isNested()) && !this.mIsNestedSectionAdapter) {
                addItem(hVar);
                return;
            }
            addItems(hVar.getItems());
            PageLink pageLink = hVar.getPageLink();
            if (pageLink != null) {
                addItem(pageLink);
            }
        }
    }

    public boolean canRemoveItems() {
        return this.mEditable;
    }

    public void onRemoveItem(int i) {
        if (canRemoveItems()) {
            super.removeItem(i);
        }
    }

    public void onItemChanged(int i) {
        super.notifyItemChanged(i);
    }

    public void setEditable(boolean z) {
        this.mEditable = z;
    }

    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }
}
