package com.contextlogic.wish.payments.braintree;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.interfaces.BraintreeListener;
import com.contextlogic.wish.api.model.WishCachedBillingInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BraintreeManager {
    private static BraintreeManager sInstance = new BraintreeManager();
    private HashMap<Integer, HashSet<BraintreeListener>> mBraintreeListeners = new HashMap<>();
    private WishCachedBillingInfo mCachedBillingInfo;

    private BraintreeManager() {
    }

    public static BraintreeManager getInstance() {
        return sInstance;
    }

    public void setCachedBillingInfo(WishCachedBillingInfo wishCachedBillingInfo) {
        this.mCachedBillingInfo = wishCachedBillingInfo;
    }

    public WishCachedBillingInfo getCachedBillingInfo() {
        return this.mCachedBillingInfo;
    }

    public void clearBraintreeListeners(BraintreeFragment braintreeFragment) {
        HashSet hashSet = (HashSet) this.mBraintreeListeners.get(Integer.valueOf(braintreeFragment.hashCode()));
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                braintreeFragment.removeListener((BraintreeListener) it.next());
            }
        }
    }

    public void addBraintreeListener(BraintreeFragment braintreeFragment, BraintreeListener braintreeListener) {
        HashSet hashSet = (HashSet) this.mBraintreeListeners.get(Integer.valueOf(braintreeFragment.hashCode()));
        if (hashSet == null) {
            hashSet = new HashSet();
        }
        if (!hashSet.contains(braintreeListener)) {
            hashSet.add(braintreeListener);
            this.mBraintreeListeners.put(Integer.valueOf(braintreeFragment.hashCode()), hashSet);
            braintreeFragment.addListener(braintreeListener);
        }
    }
}
