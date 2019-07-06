package com.contextlogic.wish.activity.cart.items;

import android.content.Context;
import android.graphics.Rect;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.contextlogic.wish.api.model.WishCartItem;
import com.contextlogic.wish.api.model.WishSavedForLaterProduct;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.util.DisplayUtil;
import java.util.ArrayList;

public class CartItemsAdapterModular extends BaseAdapter {
    /* access modifiers changed from: private */
    public CartCheckoutView mCartCheckoutView;
    private CartContext mCartContext;
    private ArrayList<Object> mCartItems;
    private CartItemsFooterView mCartItemsFooterView;
    /* access modifiers changed from: private */
    public CartItemsView mCartItemsView;
    private ListView mCartListView;
    private Context mContext;
    private boolean mIsHeaderVisible;
    private int mScreenHeight;
    private boolean mShowStaticCheckoutButton = true;

    private enum ModularCarItemType {
        CART_ITEM(0),
        ORDER_SUMMARY(1),
        CHECKOUT(2),
        SAVE_FOR_LATER(3);
        
        private int mValue;

        private ModularCarItemType(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static ModularCarItemType fromInteger(int i) {
            switch (i) {
                case 0:
                    return CART_ITEM;
                case 1:
                    return ORDER_SUMMARY;
                case 2:
                    return CHECKOUT;
                case 3:
                    return SAVE_FOR_LATER;
                default:
                    return null;
            }
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public CartItemsAdapterModular(Context context, CartItemsView cartItemsView, CartContext cartContext, CartItemsFooterView cartItemsFooterView, ListView listView) {
        this.mCartItemsFooterView = cartItemsFooterView;
        this.mContext = context;
        this.mCartItemsView = cartItemsView;
        this.mCartContext = cartContext;
        this.mCartCheckoutView = new CartCheckoutView(this.mContext, this.mCartItemsView);
        this.mCartItems = new ArrayList<>();
        this.mCartListView = listView;
        this.mScreenHeight = DisplayUtil.getDisplayHeight();
        this.mCartListView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                CartItemsAdapterModular.this.updateCartButtonVisibility(i, i2);
            }
        });
    }

    public int getCount() {
        if (this.mCartItems != null) {
            return this.mCartItems.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (i < 0 || i >= this.mCartItems.size()) {
            return null;
        }
        return this.mCartItems.get(i);
    }

    public int getItemViewType(int i) {
        if (getItem(i) instanceof WishCartItem) {
            return ModularCarItemType.CART_ITEM.getValue();
        }
        if (getItem(i) instanceof CartItemsFooterView) {
            return ModularCarItemType.ORDER_SUMMARY.getValue();
        }
        if (getItem(i) instanceof CartCheckoutView) {
            return ModularCarItemType.CHECKOUT.getValue();
        }
        if (getItem(i) instanceof WishSavedForLaterProduct) {
            return ModularCarItemType.SAVE_FOR_LATER.getValue();
        }
        return -1;
    }

    public int getViewTypeCount() {
        return ModularCarItemType.values().length;
    }

    public void updateCartButtonVisibility() {
        int firstVisiblePosition = this.mCartListView.getFirstVisiblePosition();
        updateCartButtonVisibility(firstVisiblePosition, (this.mCartListView.getLastVisiblePosition() - firstVisiblePosition) + 1);
    }

    /* access modifiers changed from: private */
    public void updateCartButtonVisibility(final int i, final int i2) {
        if (this.mCartContext.getSavedForLaterProducts() == null || this.mCartContext.getSavedForLaterProducts().size() == 0) {
            this.mShowStaticCheckoutButton = true;
            this.mCartItemsView.setCheckoutContainerVisibility(0);
            return;
        }
        updateCartButtonVisibilityHelper(i, i2);
        this.mCartCheckoutView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                CartItemsAdapterModular.this.updateCartButtonVisibilityHelper(i, i2);
                CartItemsAdapterModular.this.mCartCheckoutView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.mCartItemsView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                CartItemsAdapterModular.this.updateCartButtonVisibilityHelper(i, i2);
                CartItemsAdapterModular.this.mCartItemsView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateCartButtonVisibilityHelper(int i, int i2) {
        Rect rect = new Rect();
        this.mCartCheckoutView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        this.mCartItemsView.getCartButton().getGlobalVisibleRect(rect2);
        if (rect.top <= rect2.top && rect.top != 0) {
            this.mShowStaticCheckoutButton = false;
            this.mCartItemsView.setCheckoutContainerVisibility(8);
        }
        int i3 = this.mScreenHeight - rect.top;
        if (this.mShowStaticCheckoutButton || i3 <= rect.height() || (i + i2) - 1 < this.mCartItems.indexOf(this.mCartCheckoutView)) {
            this.mShowStaticCheckoutButton = true;
            this.mCartItemsView.setCheckoutContainerVisibility(0);
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r6v1, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r6v2, types: [com.contextlogic.wish.activity.cart.items.CartItemView] */
    /* JADX WARNING: type inference failed for: r6v3, types: [com.contextlogic.wish.activity.cart.items.CartItemView] */
    /* JADX WARNING: type inference failed for: r6v4, types: [com.contextlogic.wish.activity.cart.items.CartItemView] */
    /* JADX WARNING: type inference failed for: r6v6, types: [com.contextlogic.wish.activity.cart.items.CartItemsFooterView] */
    /* JADX WARNING: type inference failed for: r6v8, types: [com.contextlogic.wish.activity.cart.items.CartCheckoutView] */
    /* JADX WARNING: type inference failed for: r6v9, types: [com.contextlogic.wish.activity.cart.items.SaveForLaterItemView] */
    /* JADX WARNING: type inference failed for: r6v10, types: [com.contextlogic.wish.activity.cart.items.SaveForLaterItemView] */
    /* JADX WARNING: type inference failed for: r6v11, types: [com.contextlogic.wish.activity.cart.items.SaveForLaterItemView] */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: type inference failed for: r6v13 */
    /* JADX WARNING: type inference failed for: r6v14 */
    /* JADX WARNING: type inference failed for: r6v15 */
    /* JADX WARNING: type inference failed for: r6v16 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=android.view.View, code=null, for r6v0, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r6v2, types: [com.contextlogic.wish.activity.cart.items.CartItemView]
      assigns: [com.contextlogic.wish.activity.cart.items.CartItemView, android.view.View, com.contextlogic.wish.activity.cart.items.CartItemsFooterView, com.contextlogic.wish.activity.cart.items.CartCheckoutView]
      uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.contextlogic.wish.activity.cart.items.CartItemView, android.view.View, ?[OBJECT, ARRAY], com.contextlogic.wish.activity.cart.items.SaveForLaterItemView]
      mth insns count: 64
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/871566395.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$34/1534130292.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1037163664.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r5, android.view.View r6, android.view.ViewGroup r7) {
        /*
            r4 = this;
            int r7 = r4.getItemViewType(r5)
            com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular$ModularCarItemType r7 = com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular.ModularCarItemType.fromInteger(r7)
            if (r7 != 0) goto L_0x000b
            return r6
        L_0x000b:
            int[] r0 = com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular.AnonymousClass4.$SwitchMap$com$contextlogic$wish$activity$cart$items$CartItemsAdapterModular$ModularCarItemType
            int r1 = r7.ordinal()
            r0 = r0[r1]
            r1 = 1
            switch(r0) {
                case 1: goto L_0x0062;
                case 2: goto L_0x005a;
                case 3: goto L_0x0052;
                case 4: goto L_0x0019;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x009f
        L_0x0019:
            com.contextlogic.wish.activity.cart.items.SaveForLaterItemView r6 = (com.contextlogic.wish.activity.cart.items.SaveForLaterItemView) r6
            if (r6 != 0) goto L_0x002a
            com.contextlogic.wish.activity.cart.items.SaveForLaterItemView r6 = new com.contextlogic.wish.activity.cart.items.SaveForLaterItemView
            android.content.Context r7 = r4.mContext
            com.contextlogic.wish.activity.cart.items.CartItemsView r0 = r4.mCartItemsView
            com.contextlogic.wish.activity.cart.CartFragment r0 = r0.getCartFragment()
            r6.<init>(r7, r0, r4)
        L_0x002a:
            java.lang.Object r7 = r4.getItem(r5)
            com.contextlogic.wish.api.model.WishSavedForLaterProduct r7 = (com.contextlogic.wish.api.model.WishSavedForLaterProduct) r7
            r0 = 0
            if (r5 == 0) goto L_0x0043
            int r2 = r5 + -1
            int r2 = r4.getItemViewType(r2)
            com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular$ModularCarItemType r3 = com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular.ModularCarItemType.SAVE_FOR_LATER
            int r3 = r3.getValue()
            if (r2 == r3) goto L_0x0043
            r2 = 1
            goto L_0x0044
        L_0x0043:
            r2 = 0
        L_0x0044:
            java.util.ArrayList<java.lang.Object> r3 = r4.mCartItems
            int r3 = r3.size()
            int r3 = r3 - r1
            if (r5 != r3) goto L_0x004e
            r0 = 1
        L_0x004e:
            r6.setup(r7, r2, r0)
            goto L_0x009f
        L_0x0052:
            java.lang.Object r5 = r4.getItem(r5)
            r6 = r5
            com.contextlogic.wish.activity.cart.items.CartCheckoutView r6 = (com.contextlogic.wish.activity.cart.items.CartCheckoutView) r6
            goto L_0x009f
        L_0x005a:
            java.lang.Object r5 = r4.getItem(r5)
            r6 = r5
            com.contextlogic.wish.activity.cart.items.CartItemsFooterView r6 = (com.contextlogic.wish.activity.cart.items.CartItemsFooterView) r6
            goto L_0x009f
        L_0x0062:
            com.contextlogic.wish.activity.cart.items.CartItemView r6 = (com.contextlogic.wish.activity.cart.items.CartItemView) r6
            if (r6 != 0) goto L_0x0073
            com.contextlogic.wish.activity.cart.items.CartItemView r6 = new com.contextlogic.wish.activity.cart.items.CartItemView
            android.content.Context r0 = r4.mContext
            com.contextlogic.wish.activity.cart.items.CartItemsView r2 = r4.mCartItemsView
            com.contextlogic.wish.activity.cart.CartFragment r2 = r2.getCartFragment()
            r6.<init>(r0, r2)
        L_0x0073:
            java.lang.Object r0 = r4.getItem(r5)
            com.contextlogic.wish.api.model.WishCartItem r0 = (com.contextlogic.wish.api.model.WishCartItem) r0
            r6.setItem(r0)
            if (r5 == 0) goto L_0x0082
            r6.showTopSeparator()
            goto L_0x0087
        L_0x0082:
            boolean r0 = r4.mIsHeaderVisible
            r6.showHeader(r0)
        L_0x0087:
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r0 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r0 = r0.shouldSeeRedesignedCartNotices()
            if (r0 == 0) goto L_0x009f
            int r5 = r5 + r1
            int r5 = r4.getItemViewType(r5)
            com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular$ModularCarItemType r5 = com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular.ModularCarItemType.fromInteger(r5)
            if (r7 == r5) goto L_0x009f
            r6.hideSeperator()
        L_0x009f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.cart.items.CartItemsAdapterModular.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public void updateCartContext(CartContext cartContext) {
        this.mCartContext = cartContext;
        if (this.mCartContext.getCart() != null) {
            this.mCartItems.clear();
            if (this.mCartContext.getCart().getItems().size() != 0) {
                this.mCartItems.addAll(this.mCartContext.getCart().getItems());
                this.mCartItems.add(this.mCartItemsFooterView);
                if (!(cartContext.getSavedForLaterProducts() == null || cartContext.getSavedForLaterProducts().size() == 0)) {
                    this.mCartItems.add(this.mCartCheckoutView);
                }
            }
            if (!(this.mCartContext.getSavedForLaterProducts() == null || this.mCartContext.getSavedForLaterProducts().size() == 0)) {
                this.mCartItems.addAll(this.mCartContext.getSavedForLaterProducts());
            }
        }
        if (this.mCartContext.getSavedForLaterProducts() == null || this.mCartContext.getSavedForLaterProducts().size() == 0) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.mCartListView.getLayoutParams();
            this.mCartItemsView.getCheckoutContainer().measure(-1, 0);
            marginLayoutParams.bottomMargin = this.mCartItemsView.getCheckoutContainer().getMeasuredHeight();
            this.mCartListView.setLayoutParams(marginLayoutParams);
        } else {
            MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) this.mCartListView.getLayoutParams();
            marginLayoutParams2.bottomMargin = 0;
            this.mCartListView.setLayoutParams(marginLayoutParams2);
        }
        notifyDataSetChanged();
    }

    public void setIsHeaderVisible(boolean z) {
        this.mIsHeaderVisible = z;
    }
}
