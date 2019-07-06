package com.etsy.android.contentproviders;

import com.etsy.android.lib.convos.contentprovider.ConvoProvider;

public class EtsyConvoProvider extends ConvoProvider {
    private static final String AUTHORITY = "com.etsy.android.contentproviders.EtsyConvoProvider";

    public String getAuthority() {
        return AUTHORITY;
    }
}
