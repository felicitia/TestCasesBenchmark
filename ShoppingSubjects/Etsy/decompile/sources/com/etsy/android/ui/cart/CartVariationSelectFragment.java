package com.etsy.android.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.JsonBody;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.http.loader.NetworkLoader.b;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.models.EtsyAssociativeArray;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.OfferingOption;
import com.etsy.android.lib.models.apiv3.OfferingSelect;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.etsy.android.lib.models.apiv3.vespa.ServerDrivenAction;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.cart.viewholders.CartVariationSelectOptionViewHolder.a;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import java.util.List;
import org.parceler.d;

public class CartVariationSelectFragment extends NetworkLoaderFragment implements a {
    private static final int REQUEST_OFFERINGS_DATA = 0;
    private ServerDrivenAction mAction;
    private int mActionPosition;
    private CartVariationSelectAdapter mAdapter;
    /* access modifiers changed from: private */
    public CartGroupItem mCartGroupItem;
    private View mLoadingView;
    /* access modifiers changed from: private */
    public OfferingSelect mOfferingSelect;
    /* access modifiers changed from: private */
    public TextView mPrompt;
    private RecyclerView mRecyclerView;
    private View mRootView;

    @NonNull
    public String getTrackingName() {
        return "listing_variation_options";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mActionPosition = arguments.getInt("cart_action_position");
        this.mAction = (ServerDrivenAction) d.a(arguments.getParcelable("cart_action"));
        this.mAdapter = new CartVariationSelectAdapter(getActivity(), this);
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_cart_variation_select, viewGroup, false);
        this.mRecyclerView = (RecyclerView) this.mRootView.findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mPrompt = (TextView) this.mRootView.findViewById(R.id.prompt);
        this.mLoadingView = this.mRootView.findViewById(R.id.loading_view);
        loadOfferings();
        return this.mRootView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    private void loadOfferings() {
        showLoadingView();
        EtsyApiV3Request.a a = EtsyApiV3Request.a.a(CartGroupItem.class, this.mAction.getPath());
        a.a(this.mAction.getRequestMethod());
        if (this.mAction.getRequestMethod().equals(BaseHttpRequest.POST)) {
            a.a((BaseHttpBody) new JsonBody.a().a(this.mAction.getParams()).f());
        } else {
            a.a(this.mAction.getParams());
        }
        loadDataFromNetwork(0, a.d(), (b<ResultType>) new NetworkLoader.a<CartGroupItem>() {
            public void a(@NonNull List<CartGroupItem> list, int i, @NonNull com.etsy.android.lib.core.a.a<CartGroupItem> aVar) {
                if (!list.isEmpty()) {
                    CartVariationSelectFragment.this.mCartGroupItem = (CartGroupItem) list.get(0);
                    CartVariationSelectFragment.this.mOfferingSelect = (OfferingSelect) CartVariationSelectFragment.this.mCartGroupItem.getData();
                    CartVariationSelectFragment.this.mPrompt.setText(CartVariationSelectFragment.this.mOfferingSelect.getPrompt());
                    CartVariationSelectFragment.this.setContent();
                    CartVariationSelectFragment.this.showListView();
                    return;
                }
                CartVariationSelectFragment.this.showErrorAndDismiss(R.string.variations_none_found);
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<CartGroupItem> aVar) {
                CartVariationSelectFragment.this.showErrorAndDismiss(R.string.whoops_somethings_wrong);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setContent() {
        if (this.mAdapter != null && this.mRecyclerView != null && this.mOfferingSelect != null) {
            this.mAdapter.addItems(this.mOfferingSelect.getOptions());
        }
    }

    public void onVariationOptionClicked(@NonNull OfferingOption offeringOption) {
        ServerDrivenAction action = this.mCartGroupItem.getAction(ServerDrivenAction.TYPE_RESOLVE_CUSTOMIZATION);
        if (action != null) {
            addSelectedVariationToAction(action, this.mOfferingSelect.getPropertyId().getId(), offeringOption.getValue().getId());
            Bundle arguments = getArguments();
            arguments.putParcelable("cart_action", d.a(action));
            e.a(getActivity()).f().a(arguments).h();
            return;
        }
        ServerDrivenAction action2 = this.mCartGroupItem.getAction(ServerDrivenAction.TYPE_UPDATE_CUSTOMIZATION);
        if (action2 != null) {
            Intent intent = new Intent();
            intent.putExtra("cart_action_position", this.mActionPosition);
            addSelectedVariationToAction(action2, this.mOfferingSelect.getPropertyId().getId(), offeringOption.getValue().getId());
            intent.putExtra("cart_action", d.a(action2));
            getActivity().setResult(810, intent);
        }
        getActivity().finish();
    }

    private void addSelectedVariationToAction(ServerDrivenAction serverDrivenAction, String str, String str2) {
        EtsyAssociativeArray optHashMap = serverDrivenAction.getParams().optHashMap(ResponseConstants.VARIATIONS, new EtsyAssociativeArray());
        optHashMap.put(str, str2);
        serverDrivenAction.getParams().put(ResponseConstants.VARIATIONS, optHashMap);
    }

    /* access modifiers changed from: private */
    public void showErrorAndDismiss(@StringRes int i) {
        aj.b((Context) getActivity(), i);
        getActivity().finish();
    }

    public void showListView() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(0);
        }
    }

    public void showLoadingView() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.setVisibility(8);
        }
    }
}
