package com.etsy.android.uikit.ui.bughunt;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.etsy.android.lib.core.http.loader.NetworkLoader.a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BugHuntLeaderboardAdapter;
import com.etsy.etsyapi.api.pub.BugHuntLeadersGetSpec;
import com.etsy.etsyapi.models.resource.pub.BugHuntLeader;
import java.util.List;

public class BugHuntLeaderboardFragment extends BaseRecyclerViewListFragment<BugHuntLeader> {
    private static final int GET_LEADERS = 0;
    @Nullable
    BugHuntActivity mBugHuntActivity;
    String mSignedInUser;

    public static BugHuntLeaderboardFragment newInstance() {
        return new BugHuntLeaderboardFragment();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mBugHuntActivity = (BugHuntActivity) activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (v.a().e()) {
            this.mSignedInUser = SharedPreferencesUtility.e(getActivity());
            this.mAdapter = new BugHuntLeaderboardAdapter(getActivity(), this.mSignedInUser, getImageBatch());
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mSignedInUser != null) {
            onRefresh();
            return;
        }
        this.mEmptyText.setText("Sign In to View Leaderboard");
        this.mEmptySubtext.setText("You can still submit bug reports by tapping the Bug Icon");
        showEmptyView();
    }

    public void onDetach() {
        this.mBugHuntActivity = null;
        super.onDetach();
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        setRefreshing(true);
        showLoadingView();
        loadDataFromNetwork(0, BugHuntLeadersGetSpec.builder().a().request(), (a<T>) new a<BugHuntLeader>() {
            public void a(@NonNull List<BugHuntLeader> list, int i, @NonNull com.etsy.android.lib.core.a.a<BugHuntLeader> aVar) {
                BugHuntLeaderboardFragment.this.onLoadSuccess(list, i);
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<BugHuntLeader> aVar) {
                BugHuntLeaderboardFragment.this.onLoadFailure();
            }
        });
    }

    public void onRefresh() {
        getActivity().getSupportLoaderManager().destroyLoader(0);
        super.onRefresh();
    }
}
