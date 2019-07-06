package com.etsy.android.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.o;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.apiv3.cart.CartPage;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.ui.cardview.a;
import com.etsy.android.ui.cart.d;
import com.etsy.android.ui.cart.e;
import com.etsy.android.vespa.PositionList;
import com.etsy.android.vespa.VespaBaseFragment;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;
import java.io.InputStream;

public class BOEVespaDemoFragment extends VespaBaseFragment implements d {
    private final String MOCK_FILE_CARD_VIEW_DEMO_PAGE = "card_view_demo_page";
    private final String MOCK_FILE_EXPLORE_DEMO_PAGE = "explore_demo_page";
    private final String MOCK_FILE_MAIN_VESPA_DEMO_PAGE = "vespa_demo_page";
    private final String MOCK_FILE_MULTISHOP_CART_DEMO_PAGE = "multishop_demo_page";
    private final String MOCK_FILE_ONBOARDING_DEMO_PAGE = "onboarding_demo_page";
    private String mMockFileName;

    @NonNull
    public String getApiUrl() {
        return null;
    }

    public void proceedToCheckout(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        String stringExtra = getActivity().getIntent().getStringExtra(BOEVespaDemoActivity.MOCK_FILE_NAME);
        if (TextUtils.isEmpty(stringExtra)) {
            this.mMockFileName = "vespa_demo_page";
        } else {
            this.mMockFileName = stringExtra;
        }
        String str = this.mMockFileName;
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1781699393) {
            if (hashCode != -1622614457) {
                if (hashCode != 765274843) {
                    if (hashCode == 1799860064 && str.equals("card_view_demo_page")) {
                        c = 0;
                    }
                } else if (str.equals("multishop_demo_page")) {
                    c = 3;
                }
            } else if (str.equals("onboarding_demo_page")) {
                c = 2;
            }
        } else if (str.equals("explore_demo_page")) {
            c = 1;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                addDelegateViewHolderFactory(new a(getActivity(), getAdapter(), getAnalyticsContext(), this));
                break;
            case 3:
                addDelegateViewHolderFactory(new e(getActivity(), getAnalyticsContext(), this));
                break;
        }
        return onCreateView;
    }

    public com.etsy.android.vespa.b.a getPagination() {
        return new com.etsy.android.vespa.b.d();
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        Page page;
        int identifier = getResources().getIdentifier(this.mMockFileName, "raw", getActivity().getPackageName());
        char c = 65535;
        if (identifier == -1) {
            showErrorView();
            return;
        }
        try {
            InputStream openRawResource = getActivity().getResources().openRawResource(identifier);
            byte[] bArr = new byte[openRawResource.available()];
            openRawResource.read(bArr);
            JsonParser a = o.a().a(bArr);
            a.nextToken();
            String str = this.mMockFileName;
            if (str.hashCode() == 765274843) {
                if (str.equals("multishop_demo_page")) {
                    c = 0;
                }
            }
            if (c != 0) {
                page = new Page();
            } else {
                page = new CartPage();
            }
            page.parseData(a);
            onLoadComplete(page);
            this.mAdapter.notifyDataSetChanged();
        } catch (IOException e) {
            com.google.a.a.a.a.a.a.a(e);
            showErrorView();
        }
    }

    public void showVariationSelectDialog(PositionList positionList, ServerDrivenAction serverDrivenAction) {
        f.d(positionList.toString());
    }
}
