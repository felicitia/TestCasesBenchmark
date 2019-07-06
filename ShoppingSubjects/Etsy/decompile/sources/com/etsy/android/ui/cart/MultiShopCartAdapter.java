package com.etsy.android.ui.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.R;
import com.etsy.android.lib.logger.b;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.apiv3.cart.CartGroup;
import com.etsy.android.lib.models.apiv3.cart.CartReceipt;
import com.etsy.android.lib.models.apiv3.cart.CartThankYouGroup;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.vespa.BaseViewHolderFactoryRecyclerViewAdapter;
import com.etsy.android.vespa.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultiShopCartAdapter extends BaseViewHolderFactoryRecyclerViewAdapter {
    private static final String LAST_LOADED_PAGE_START_INDEX = "last_loaded_page_start_index";
    private static final String TAG = f.a(MultiShopCartAdapter.class);
    private int mLastLoadedPageStartIndex = 0;

    public boolean canRemoveItems() {
        return true;
    }

    public MultiShopCartAdapter(FragmentActivity fragmentActivity, @NonNull b bVar, @NonNull d dVar) {
        super(fragmentActivity, bVar, dVar);
        setEditable(true);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(LAST_LOADED_PAGE_START_INDEX, this.mLastLoadedPageStartIndex);
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.mLastLoadedPageStartIndex = bundle.getInt(LAST_LOADED_PAGE_START_INDEX);
        }
        super.onRestoreInstanceState(bundle);
    }

    public void addPage(Page page) {
        this.mLastLoadedPageStartIndex = getItems().size();
        for (ListSection addListSection : page.getListSections()) {
            addListSection(addListSection);
        }
    }

    public void addListSection(ListSection listSection) {
        addItems(listSection.getItems());
    }

    public void updatePage(int i, Page page) {
        if (page == null) {
            f.e(TAG, "Attempt to update the page with a null page");
        } else if (i < 0 || i >= this.mItems.size()) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Attempt to update item at an invalid position ");
            sb.append(i);
            sb.append(" of ");
            sb.append(this.mItems.size());
            f.e(str, sb.toString());
        } else {
            if (page.getListSections().size() > 0) {
                boolean z = false;
                for (ListSection listSection : page.getListSections()) {
                    if (!z) {
                        Iterator it = listSection.getItems().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            k kVar = (k) it.next();
                            if (!z) {
                                String resourceEntryName = ((FragmentActivity) this.mContext).getResources().getResourceEntryName(((k) this.mItems.get(i)).getViewType());
                                String str2 = TAG;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Replacing ");
                                sb2.append(resourceEntryName);
                                sb2.append(" at position ");
                                sb2.append(i);
                                f.c(str2, sb2.toString());
                                replaceItem(i, kVar);
                                z = true;
                                break;
                            }
                        }
                    }
                }
            } else {
                String resourceEntryName2 = ((FragmentActivity) this.mContext).getResources().getResourceEntryName(((k) this.mItems.get(i)).getViewType());
                String str3 = TAG;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Removing ");
                sb3.append(resourceEntryName2);
                sb3.append(" at position ");
                sb3.append(i);
                sb3.append(" in ");
                sb3.append(this.mItems.size());
                f.c(str3, sb3.toString());
                if (i < this.mLastLoadedPageStartIndex) {
                    this.mLastLoadedPageStartIndex--;
                }
                removeItem(i);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<String> getLastLoadedPageCartIds() {
        return getLoadedCartIds(true);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<String> getAllLoadedCartIds() {
        return getLoadedCartIds(false);
    }

    @NonNull
    private List<String> getLoadedCartIds(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (getItemCount() == 0) {
            return arrayList;
        }
        List items = getItems();
        for (int i = z ? this.mLastLoadedPageStartIndex : 0; i < items.size(); i++) {
            k kVar = (k) items.get(i);
            if (R.id.view_type_multishop_cart_group == kVar.getViewType()) {
                arrayList.add(((CartGroup) kVar).getCartGroupId());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<CartReceipt> getCartReceipts() {
        List<CartReceipt> arrayList = new ArrayList<>();
        for (k kVar : getItems()) {
            if (R.id.view_type_multishop_cart_thank_you_group == kVar.getViewType()) {
                arrayList = ((CartThankYouGroup) kVar).getReceipts();
            }
        }
        return arrayList;
    }
}
