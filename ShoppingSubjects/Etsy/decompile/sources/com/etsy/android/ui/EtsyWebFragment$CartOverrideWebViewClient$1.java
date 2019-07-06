package com.etsy.android.ui;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.HashMap;

class EtsyWebFragment$CartOverrideWebViewClient$1 extends HashMap<String, Object> {
    final /* synthetic */ a this$1;
    final /* synthetic */ EtsyId val$cartId;
    final /* synthetic */ String val$errorMessage;

    EtsyWebFragment$CartOverrideWebViewClient$1(a aVar, EtsyId etsyId, String str) {
        this.this$1 = aVar;
        this.val$cartId = etsyId;
        this.val$errorMessage = str;
        put("cart_id", this.val$cartId.getId());
        put(ResponseConstants.ERROR_MESSAGE, this.val$errorMessage);
    }
}
