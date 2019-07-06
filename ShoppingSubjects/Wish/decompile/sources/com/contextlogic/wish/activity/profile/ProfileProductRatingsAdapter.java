package com.contextlogic.wish.activity.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.imageviewer.ImageViewerActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.http.ImageHttpPrefetcher;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.listview.ListViewTabStrip.TextTabProvider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProfileProductRatingsAdapter extends ArrayAdapter<WishRating> implements TextTabProvider {
    private Context mContext;
    private ImageHttpPrefetcher mImagePrefetcher;
    private ListView mListView;
    private ArrayList<WishRating> mProductRatings = new ArrayList<>();
    /* access modifiers changed from: private */
    public ProfileFragment mProfileFragment;

    static class ItemRowHolder {
        TextView comment;
        TextView editText;
        NetworkImageView productImage;
        TextView productName;
        NetworkImageView ratingImage;
        ImageView ratingStarFive;
        ImageView ratingStarFour;
        ImageView ratingStarOne;
        ImageView ratingStarThree;
        ImageView ratingStarTwo;
        TextView timeStamp;

        ItemRowHolder() {
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ProfileProductRatingsAdapter(Context context, ListView listView, ProfileFragment profileFragment) {
        super(context, R.layout.profile_product_rating_row);
        this.mContext = context;
        this.mListView = listView;
        this.mProfileFragment = profileFragment;
    }

    public void setProductRatings(ArrayList<WishRating> arrayList) {
        this.mProductRatings = arrayList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mProductRatings.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowHolder itemRowHolder;
        if (view == null) {
            ItemRowHolder itemRowHolder2 = new ItemRowHolder();
            View inflate = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.profile_product_rating_row, viewGroup, false);
            itemRowHolder2.ratingStarOne = (ImageView) inflate.findViewById(R.id.fragment_rating_image_one);
            itemRowHolder2.ratingStarTwo = (ImageView) inflate.findViewById(R.id.fragment_rating_image_two);
            itemRowHolder2.ratingStarThree = (ImageView) inflate.findViewById(R.id.fragment_rating_image_three);
            itemRowHolder2.ratingStarFour = (ImageView) inflate.findViewById(R.id.fragment_rating_image_four);
            itemRowHolder2.ratingStarFive = (ImageView) inflate.findViewById(R.id.fragment_rating_image_five);
            itemRowHolder2.productName = (TextView) inflate.findViewById(R.id.profile_fragment_rating_product_name);
            itemRowHolder2.timeStamp = (TextView) inflate.findViewById(R.id.profile_fragment_rating_timestamp);
            itemRowHolder2.comment = (TextView) inflate.findViewById(R.id.profile_fragment_item_text_body);
            itemRowHolder2.ratingImage = (NetworkImageView) inflate.findViewById(R.id.fragment_ratings_item_rating_image);
            itemRowHolder2.ratingImage.setImagePrefetcher(this.mImagePrefetcher);
            itemRowHolder2.productImage = (NetworkImageView) inflate.findViewById(R.id.profile_fragment_product_image);
            itemRowHolder2.productImage.setImagePrefetcher(this.mImagePrefetcher);
            itemRowHolder2.editText = (TextView) inflate.findViewById(R.id.profile_fragment_edit_rating);
            inflate.setTag(itemRowHolder2);
            View view2 = inflate;
            itemRowHolder = itemRowHolder2;
            view = view2;
        } else {
            itemRowHolder = (ItemRowHolder) view.getTag();
        }
        final WishRating wishRating = (WishRating) this.mProductRatings.get(i);
        int[] iArr = new int[5];
        int i2 = 0;
        while (i2 < 5) {
            int i3 = i2 + 1;
            if (wishRating.getRating() >= i3) {
                iArr[i2] = R.drawable.full_star_primary;
            } else {
                iArr[i2] = R.drawable.empty_star_primary;
            }
            i2 = i3;
        }
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (wishRating.getProductId() != null) {
                    ProfileProductRatingsAdapter.this.mProfileFragment.openProductDetailsPage(wishRating.getProductId());
                }
            }
        });
        itemRowHolder.ratingStarOne.setImageResource(iArr[0]);
        itemRowHolder.ratingStarTwo.setImageResource(iArr[1]);
        itemRowHolder.ratingStarThree.setImageResource(iArr[2]);
        itemRowHolder.ratingStarFour.setImageResource(iArr[3]);
        itemRowHolder.ratingStarFive.setImageResource(iArr[4]);
        itemRowHolder.productName.setText(wishRating.getProductName());
        itemRowHolder.timeStamp.setText(new SimpleDateFormat("MMM dd, yyyy").format(wishRating.getTimestamp()));
        String comment = wishRating.getComment();
        if (comment == null || comment.trim().equals("")) {
            itemRowHolder.comment.setText(getContext().getResources().getString(R.string.no_review_yet));
            itemRowHolder.comment.setTypeface(null, 2);
            itemRowHolder.comment.setTextColor(getContext().getResources().getColor(R.color.dark_gray_3));
        } else {
            itemRowHolder.comment.setText(wishRating.getComment());
            itemRowHolder.comment.setTextColor(getContext().getResources().getColor(R.color.dark_gray_2));
        }
        itemRowHolder.ratingImage.releaseImages();
        itemRowHolder.ratingImage.setImage(null);
        if (wishRating.getImageThumbnailUrlString() != null) {
            itemRowHolder.ratingImage.setVisibility(0);
            itemRowHolder.ratingImage.setImage(new WishImage(wishRating.getImageThumbnailUrlString()));
            itemRowHolder.ratingImage.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ProfileProductRatingsAdapter.this.showLargeImage(wishRating.getImageLargeUrlString());
                }
            });
        } else {
            itemRowHolder.ratingImage.setVisibility(8);
        }
        itemRowHolder.productImage.releaseImages();
        itemRowHolder.productImage.setImage(null);
        if (wishRating.getProductImageUrlString() != null) {
            itemRowHolder.productImage.setVisibility(0);
            itemRowHolder.productImage.setImage(new WishImage(wishRating.getProductImageUrlString()));
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void showLargeImage(final String str) {
        this.mProfileFragment.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_PROFILE_REDESIGN_PRODUCT_REVIEW_PHOTO);
        this.mProfileFragment.withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                Intent intent = new Intent();
                intent.setClass(profileActivity, ImageViewerActivity.class);
                intent.putExtra("ExtraImageUrl", str);
                intent.putExtra("ExtraStartIndex", 0);
                profileActivity.startActivity(intent);
            }
        });
    }

    public void setImagePrefetcher(ImageHttpPrefetcher imageHttpPrefetcher) {
        this.mImagePrefetcher = imageHttpPrefetcher;
    }

    public WishRating getItem(int i) {
        return (WishRating) this.mProductRatings.get(i);
    }

    public String getPageTitle() {
        return WishApplication.getInstance().getString(R.string.reviews);
    }
}
