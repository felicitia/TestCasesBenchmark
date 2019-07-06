package com.etsy.android.uikit.adapter;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.lib.a.e;
import com.etsy.android.lib.a.f;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.core.img.c;
import com.etsy.etsyapi.models.resource.pub.BugHuntLeader;

public class BugHuntLeaderboardAdapter extends TypedViewHolderRecyclerViewAdapter<BugHuntLeader, LeaderViewHolder> {
    int mAvatarSize;
    int mDefaultColor;
    String mSignedInUser;
    int mUserColor;

    public static class LeaderViewHolder extends ViewHolder {
        public ImageView avatar;
        public TextView name;
        public TextView score;

        public LeaderViewHolder(View view) {
            super(view);
            this.avatar = (ImageView) view.findViewById(i.bughunt_leaderboard_avatar);
            this.name = (TextView) view.findViewById(i.bughunt_leaderboard_primary);
            this.score = (TextView) view.findViewById(i.bughunt_leaderboard_secondary);
        }
    }

    /* access modifiers changed from: protected */
    public int getListItemViewType(int i) {
        return 0;
    }

    public BugHuntLeaderboardAdapter(FragmentActivity fragmentActivity, String str, c cVar) {
        super(fragmentActivity, cVar);
        this.mSignedInUser = str;
        Resources resources = fragmentActivity.getResources();
        this.mAvatarSize = (int) resources.getDimension(f.conversation_avatar);
        this.mUserColor = resources.getColor(e.bughunt_primary_color);
        this.mDefaultColor = resources.getColor(e.text_dark_grey);
    }

    /* access modifiers changed from: protected */
    public LeaderViewHolder onCreateListItemViewHolder(ViewGroup viewGroup, int i) {
        return new LeaderViewHolder(this.mInflater.inflate(k.list_item_bughunt_leaderboard, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void onBindTypedViewHolder(LeaderViewHolder leaderViewHolder, int i) {
        BugHuntLeader bugHuntLeader = (BugHuntLeader) getItem(i);
        leaderViewHolder.name.setText(bugHuntLeader.fullname());
        leaderViewHolder.score.setText(Integer.toString(bugHuntLeader.score().intValue()));
        if (bugHuntLeader.username().equals(this.mSignedInUser)) {
            leaderViewHolder.name.setTextColor(this.mUserColor);
            leaderViewHolder.score.setTextColor(this.mUserColor);
        } else {
            leaderViewHolder.name.setTextColor(this.mDefaultColor);
            leaderViewHolder.score.setTextColor(this.mDefaultColor);
        }
        leaderViewHolder.avatar.setVisibility(8);
    }
}
