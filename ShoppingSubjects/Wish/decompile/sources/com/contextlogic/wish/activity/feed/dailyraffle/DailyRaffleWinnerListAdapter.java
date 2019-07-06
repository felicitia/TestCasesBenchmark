package com.contextlogic.wish.activity.feed.dailyraffle;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.profile.ProfileActivity;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.view.ProfileImageView;
import java.util.ArrayList;

public class DailyRaffleWinnerListAdapter extends ArrayAdapter<WishUser> {
    /* access modifiers changed from: private */
    public BaseActivity mBaseActivity;
    private boolean mIsDiscount;
    private ArrayList<WishUser> mUsers;

    static class ItemRowHolder implements ImageRestorable {
        ProfileImageView profilePicture;
        TextView ticketNumber;
        TextView userName;

        ItemRowHolder() {
        }

        public void releaseImages() {
            if (this.profilePicture != null) {
                this.profilePicture.releaseImages();
            }
        }

        public void restoreImages() {
            if (this.profilePicture != null) {
                this.profilePicture.restoreImages();
            }
        }
    }

    DailyRaffleWinnerListAdapter(BaseActivity baseActivity, ArrayList<WishUser> arrayList, boolean z) {
        super(baseActivity, R.layout.daily_raffle_free_item_winner_list_item, arrayList);
        this.mUsers = arrayList;
        this.mIsDiscount = z;
        this.mBaseActivity = baseActivity;
    }

    public int getCount() {
        if (this.mUsers != null) {
            return this.mUsers.size();
        }
        return 0;
    }

    public WishUser getItem(int i) {
        if (this.mUsers == null || this.mUsers.size() <= i) {
            return null;
        }
        return (WishUser) this.mUsers.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.daily_raffle_free_item_winner_list_item, viewGroup, false);
            itemRowHolder = new ItemRowHolder();
            itemRowHolder.profilePicture = (ProfileImageView) view.findViewById(R.id.daily_raffle_winners_list_profile_photo);
            itemRowHolder.userName = (TextView) view.findViewById(R.id.daily_raffle_winners_list_user_name);
            itemRowHolder.ticketNumber = (TextView) view.findViewById(R.id.daily_raffle_ticket_number_text);
            view.setTag(itemRowHolder);
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        final WishUser item = getItem(i);
        if (item != null) {
            if (this.mIsDiscount) {
                itemRowHolder.profilePicture.setVisibility(8);
            } else {
                itemRowHolder.profilePicture.setVisibility(0);
                itemRowHolder.profilePicture.setup(item.getProfileImage(), item.getName());
            }
            itemRowHolder.userName.setText(item.getName());
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (DailyRaffleWinnerListAdapter.this.mBaseActivity != null) {
                        Intent intent = new Intent();
                        intent.setClass(DailyRaffleWinnerListAdapter.this.mBaseActivity, ProfileActivity.class);
                        intent.putExtra(ProfileActivity.EXTRA_USER_ID, item.getUserId());
                        DailyRaffleWinnerListAdapter.this.mBaseActivity.startActivity(intent);
                    }
                }
            });
        }
        return view;
    }
}
