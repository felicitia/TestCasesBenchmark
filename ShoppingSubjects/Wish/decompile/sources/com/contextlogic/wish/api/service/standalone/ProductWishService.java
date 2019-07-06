package com.contextlogic.wish.api.service.standalone;

import com.contextlogic.wish.api.service.MultiApiService;

public class ProductWishService extends MultiApiService {
    /* access modifiers changed from: protected */
    public int[] getSuccessLikeErrorCodes() {
        return new int[]{10};
    }
}
