package com.etsy.android.ui.convos.convolistredesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.img.c;
import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.util.HashMap;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: ConvosListFragment.kt */
public final class ConvosListFragment extends BaseRecyclerViewListFragment<e> implements a, o {
    private HashMap _$_findViewCache;
    public m presenter;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final m getPresenter() {
        m mVar = this.presenter;
        if (mVar == null) {
            p.b("presenter");
        }
        return mVar;
    }

    public final void setPresenter(m mVar) {
        p.b(mVar, "<set-?>");
        this.presenter = mVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentActivity activity = getActivity();
        p.a((Object) activity, "activity");
        c imageBatch = getImageBatch();
        p.a((Object) imageBatch, "imageBatch");
        ConvoAdapter convoAdapter = new ConvoAdapter(activity, imageBatch, new ConvosListFragment$onCreateView$1(this), new ConvosListFragment$onCreateView$2(this), new ConvosListFragment$onCreateView$3(this));
        this.mAdapter = convoAdapter;
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.etsy.android.uikit.BaseActivity");
        }
        ((BaseActivity) activity2).getAppBarHelper().setTitle((int) R.string.conversations);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), 1);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vertical_list_divider));
        this.mRecyclerView.addItemDecoration(dividerItemDecoration);
        loadContent();
        p.a((Object) onCreateView, "view");
        return onCreateView;
    }

    public void onStart() {
        super.onStart();
        m mVar = this.presenter;
        if (mVar == null) {
            p.b("presenter");
        }
        mVar.c();
    }

    public void onStop() {
        m mVar = this.presenter;
        if (mVar == null) {
            p.b("presenter");
        }
        mVar.d();
        super.onStop();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        p.b(menu, "menu");
        p.b(menuInflater, "inflater");
        menuInflater.inflate(R.menu.convo_message_action_bar, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (menu != null) {
            MenuItem findItem = menu.findItem(R.id.menu_new_message);
            if (findItem != null) {
                findItem.setIcon(AppCompatResources.getDrawable(getContext(), R.drawable.sk_ic_compose));
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf == null || valueOf.intValue() != R.id.menu_new_message) {
            return super.onOptionsItemSelected(menuItem);
        }
        e.a(getActivity()).a().u();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        BaseRecyclerViewAdapter baseRecyclerViewAdapter = this.mAdapter;
        p.a((Object) baseRecyclerViewAdapter, "mAdapter");
        if (baseRecyclerViewAdapter.getDataItemCount() > 0 && isRefreshing()) {
            this.mAdapter.clear();
            m mVar = this.presenter;
            if (mVar == null) {
                p.b("presenter");
            }
            mVar.b();
        }
        m mVar2 = this.presenter;
        if (mVar2 == null) {
            p.b("presenter");
        }
        mVar2.a();
    }

    public void stopRefreshing() {
        SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
        p.a((Object) swipeRefreshLayout, "mSwipeRefreshLayout");
        swipeRefreshLayout.setRefreshing(false);
        setLoading(false);
        setRefreshing(false);
    }

    public void addItemsToAdapter(List<? extends e> list) {
        p.b(list, ResponseConstants.ITEMS);
        this.mAdapter.addItems(list);
    }

    public void navigateToUserWebView(EtsyId etsyId) {
        p.b(etsyId, "userId");
        e.a(getActivity()).a().c(etsyId);
    }

    public void launchConversation(Conversation3 conversation3) {
        p.b(conversation3, ResponseConstants.CONVO);
        e.a(getActivity()).a().a(6, (Fragment) this).a(conversation3.getConversationId(), true);
    }

    public void showErrorSnackbar(int i) {
        aj.a(getView(), i);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 6 && i2 == -1) {
            refreshConvos();
        }
    }

    public void refreshConvos() {
        setRefreshing(true);
        loadContent();
    }
}
