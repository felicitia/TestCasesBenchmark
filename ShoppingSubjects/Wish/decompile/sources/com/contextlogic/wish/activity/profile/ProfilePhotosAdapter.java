package com.contextlogic.wish.activity.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.imageviewer.ImageViewerActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProductExtraImage;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.TextTabProvider;
import com.contextlogic.wish.util.DisplayUtil;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.TabletUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class ProfilePhotosAdapter extends ArrayAdapter<WishImage> implements TextTabProvider {
    private Context mContext;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ListView mListView;
    private ArrayList<WishImage> mPhotos = new ArrayList<>();
    private ProfileFragment mProfileFragment;

    static class ItemRowHolder {
        ArrayList<NetworkImageView> imageViews;
        LinearLayout row;

        ItemRowHolder() {
        }

        public NetworkImageView getImage(int i) {
            return (NetworkImageView) this.imageViews.get(i);
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ProfilePhotosAdapter(Context context, ListView listView, ProfileFragment profileFragment) {
        super(context, R.layout.profile_product_rating_row);
        this.mContext = context;
        this.mListView = listView;
        this.mProfileFragment = profileFragment;
    }

    public void setPhotos(ArrayList<WishImage> arrayList) {
        this.mPhotos = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return (int) Math.ceil((double) (((float) this.mPhotos.size()) / ((float) getNumColumn())));
    }

    private int getNumColumn() {
        return TabletUtil.isTablet() ? 5 : 3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.profile_photo_row, viewGroup, false);
            itemRowHolder = new ItemRowHolder();
            itemRowHolder.row = (LinearLayout) view.findViewById(R.id.profile_photo_row_layout);
            float dimension = this.mProfileFragment.getResources().getDimension(R.dimen.eight_padding);
            itemRowHolder.row.setLayoutParams(new LayoutParams(-1, (int) ((((float) DisplayUtil.getDisplayWidth()) - ((((float) (getNumColumn() - 1)) * dimension) + (this.mProfileFragment.getResources().getDimension(R.dimen.sixteen_padding) * 2.0f))) / ((float) getNumColumn()))));
            view.setTag(itemRowHolder);
            itemRowHolder.imageViews = new ArrayList<>();
            int numColumn = getNumColumn() * i;
            while (numColumn < (i + 1) * getNumColumn()) {
                NetworkImageView networkImageView = new NetworkImageView(getContext());
                LayoutParams layoutParams = new LayoutParams(0, -1);
                layoutParams.weight = 1.0f;
                networkImageView.setImagePrefetcher(this.mImagePrefetcher);
                layoutParams.setMargins(numColumn != getNumColumn() * i ? (int) dimension : 0, 0, 0, 0);
                networkImageView.setLayoutParams(layoutParams);
                itemRowHolder.imageViews.add(networkImageView);
                itemRowHolder.row.addView(networkImageView);
                numColumn++;
            }
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        for (int i2 = 0; i2 < getNumColumn(); i2++) {
            final int numColumn2 = (getNumColumn() * i) + i2;
            itemRowHolder.getImage(i2).releaseImages();
            if (numColumn2 < this.mPhotos.size()) {
                itemRowHolder.getImage(i2).setImage((WishImage) this.mPhotos.get(numColumn2));
                itemRowHolder.getImage(i2).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ProfilePhotosAdapter.this.showLargeImage(numColumn2);
                    }
                });
            } else {
                itemRowHolder.getImage(i2).setOnClickListener(null);
            }
        }
        return view;
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public WishImage getItem(int i) {
        return (WishImage) this.mPhotos.get(i);
    }

    public String getPageTitle() {
        return WishApplication.getInstance().getString(R.string.uploads);
    }

    /* access modifiers changed from: private */
    public void showLargeImage(final int i) {
        this.mProfileFragment.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_PRODUCT_PHOTO);
        final ArrayList arrayList = new ArrayList();
        Iterator it = this.mPhotos.iterator();
        while (it.hasNext()) {
            arrayList.add(new WishProductExtraImage((WishImage) it.next()));
        }
        this.mProfileFragment.withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                Intent intent = new Intent();
                intent.setClass(profileActivity, ImageViewerActivity.class);
                IntentUtil.putParcelableArrayListExtra(intent, "ExtraMediaSources", arrayList);
                intent.putExtra("ExtraStartIndex", i);
                profileActivity.startActivity(intent);
            }
        });
    }
}
