package com.etsy.android.ui.homescreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.FormBody;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.url.EtsyApiV3Url.a;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.cardviewelement.ListSection;
import com.etsy.android.lib.models.cardviewelement.Page;
import com.etsy.android.lib.models.homescreen.LandingPageInfo;
import com.etsy.android.lib.models.homescreen.LandingPageLink;
import com.etsy.android.vespa.b.c;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import org.parceler.d;

public class LandingPageFragment extends CardRecyclerViewBaseFragment {
    private static final String TAG = f.a(LandingPageFragment.class);
    LandingPageInfo mPageLink;
    private c mPagination = new c();

    /* access modifiers changed from: protected */
    public Map<String, String> getBodyParams() {
        return null;
    }

    @NonNull
    public String getTrackingName() {
        if (this.mPageLink != null) {
            return this.mPageLink.getEventName();
        }
        if (!getArguments().containsKey(ResponseConstants.PAGE_LINK)) {
            return super.getTrackingName();
        }
        this.mPageLink = (LandingPageInfo) d.a(getArguments().getParcelable(ResponseConstants.PAGE_LINK));
        return this.mPageLink.getEventName();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mPageLink = (LandingPageInfo) d.a(getArguments().getParcelable(ResponseConstants.PAGE_LINK));
        if (this.mPageLink != null) {
            getActivity().setTitle(this.mPageLink.getPageTitle());
            if (this.mPageLink.getLayout() == 0) {
                setLayoutManager(StaggeredGridLayoutManager.class);
            } else if (this.mPageLink.getLayout() == 2) {
                setLayoutManager(GridLayoutManager.class);
            } else if (this.mPageLink.getLayout() == 1) {
                setLayoutManager(LinearLayoutManager.class);
            }
            if (this.mAdapter.getItemCount() == 0) {
                resetAndLoadContent();
            }
        }
        return onCreateView;
    }

    public void onResume() {
        super.onResume();
        b.a().a(this.mPageLink.getEventName());
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        Class classForPageType = LandingPageLink.getClassForPageType(this.mPageLink.getPageType());
        if (classForPageType == null) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("This card type cannot be rendered in a Landing Page yet: ");
            sb.append(this.mPageLink.getPageType());
            f.e(str, sb.toString());
            onLoadFailure();
            return;
        }
        a aVar = (a) ((a) new a(getContentUrl()).a(getParams())).a((Map<String, String>) getPagination().getPaginationParams());
        getRequestQueue().a((com.etsy.android.lib.core.http.request.a<?, ?, ?>) (com.etsy.android.lib.core.http.request.d) createJobBuilder((EtsyApiV3Request.a) ((EtsyApiV3Request.a) new EtsyApiV3Request.a(classForPageType, aVar).a(this.mPageLink.getRequestMethod())).a((BaseHttpBody) ((FormBody.a) new FormBody.a().a(getBodyParams())).f())).c());
    }

    public boolean canLoadContent() {
        return this.mPageLink != null && super.canLoadContent();
    }

    /* access modifiers changed from: protected */
    public <T extends BaseModel> com.etsy.android.lib.core.http.request.d.a<T> createJobBuilder(EtsyApiV3Request.a<T> aVar) {
        aVar.a(true);
        com.etsy.android.lib.core.http.request.d.a<T> a = com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) aVar.d());
        a.a((C0065a<ResultType>) new com.etsy.android.lib.core.http.request.d.b<T>() {
            WeakReference<LandingPageFragment> a = new WeakReference<>(LandingPageFragment.this);

            public void a(@NonNull List<T> list, int i, @NonNull com.etsy.android.lib.core.a.a<T> aVar) {
                LandingPageFragment landingPageFragment = (LandingPageFragment) this.a.get();
                if (landingPageFragment != null) {
                    if (list.isEmpty()) {
                        LandingPageFragment.this.stopEndless();
                        LandingPageFragment.this.getPagination().setContentExhausted(true);
                        return;
                    }
                    Page page = new Page();
                    ListSection listSection = new ListSection();
                    listSection.getItems().addAll(list);
                    page.getListSections().add(listSection);
                    landingPageFragment.onLoadComplete(page);
                    landingPageFragment.getPagination().onSuccess(aVar, list.size());
                }
            }

            public void a(int i, String str, @NonNull com.etsy.android.lib.core.a.a<T> aVar) {
                LandingPageFragment landingPageFragment = (LandingPageFragment) this.a.get();
                if (landingPageFragment != null) {
                    landingPageFragment.onLoadFailure();
                }
            }
        }, (Fragment) this);
        return a;
    }

    @NonNull
    public String getApiUrl() {
        return this.mPageLink.getAPIPath();
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() {
        return this.mPageLink.getParams();
    }

    public com.etsy.android.vespa.b.a getPagination() {
        return this.mPagination;
    }
}
