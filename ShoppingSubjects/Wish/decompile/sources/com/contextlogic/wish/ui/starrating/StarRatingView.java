package com.contextlogic.wish.ui.starrating;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.util.ValueUtil;
import java.util.ArrayList;

public abstract class StarRatingView extends LinearLayout {
    private ImageView mProductRatingStarFive;
    private ImageView mProductRatingStarFour;
    private ImageView mProductRatingStarOne;
    private ImageView mProductRatingStarThree;
    private ImageView mProductRatingStarTwo;
    protected TextView mProductRatingText;

    public interface Callback {
        void onRatingClick(double d);
    }

    public enum Size {
        EXTRA_SMALL,
        SMALL,
        INTERMEDIATE,
        MEDIUM,
        LARGE
    }

    /* access modifiers changed from: protected */
    public int spaceBetweenStars() {
        return 0;
    }

    public StarRatingView(Context context) {
        super(context);
        init();
    }

    public StarRatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public StarRatingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public void setupStarImages(int[] iArr, Size size) {
        int starWidth = getStarWidth(size);
        int starHeight = getStarHeight(size);
        int spaceBetweenStars = spaceBetweenStars();
        this.mProductRatingStarOne.setImageResource(iArr[0]);
        this.mProductRatingStarTwo.setImageResource(iArr[1]);
        this.mProductRatingStarThree.setImageResource(iArr[2]);
        this.mProductRatingStarFour.setImageResource(iArr[3]);
        this.mProductRatingStarFive.setImageResource(iArr[4]);
        LayoutParams layoutParams = (LayoutParams) this.mProductRatingStarOne.getLayoutParams();
        layoutParams.width = starWidth;
        layoutParams.height = starHeight;
        layoutParams.setMargins(0, 0, spaceBetweenStars, 0);
        this.mProductRatingStarOne.setLayoutParams(layoutParams);
        LayoutParams layoutParams2 = (LayoutParams) this.mProductRatingStarTwo.getLayoutParams();
        layoutParams2.width = starWidth;
        layoutParams2.height = starHeight;
        layoutParams2.setMargins(0, 0, spaceBetweenStars, 0);
        this.mProductRatingStarTwo.setLayoutParams(layoutParams2);
        LayoutParams layoutParams3 = (LayoutParams) this.mProductRatingStarThree.getLayoutParams();
        layoutParams3.width = starWidth;
        layoutParams3.height = starHeight;
        layoutParams3.setMargins(0, 0, spaceBetweenStars, 0);
        this.mProductRatingStarThree.setLayoutParams(layoutParams3);
        LayoutParams layoutParams4 = (LayoutParams) this.mProductRatingStarFour.getLayoutParams();
        layoutParams4.width = starWidth;
        layoutParams4.height = starHeight;
        layoutParams4.setMargins(0, 0, spaceBetweenStars, 0);
        this.mProductRatingStarFour.setLayoutParams(layoutParams4);
        LayoutParams layoutParams5 = (LayoutParams) this.mProductRatingStarFive.getLayoutParams();
        layoutParams5.width = starWidth;
        layoutParams5.height = starHeight;
        this.mProductRatingStarFive.setLayoutParams(layoutParams5);
    }

    /* access modifiers changed from: protected */
    public int getStarWidth(Size size) {
        switch (size) {
            case EXTRA_SMALL:
                return (int) ValueUtil.convertDpToPx(11.0f);
            case SMALL:
                return (int) ValueUtil.convertDpToPx(14.0f);
            case INTERMEDIATE:
                return (int) ValueUtil.convertDpToPx(16.0f);
            case MEDIUM:
                return (int) ValueUtil.convertDpToPx(19.0f);
            case LARGE:
                return (int) ValueUtil.convertDpToPx(34.0f);
            default:
                return (int) ValueUtil.convertDpToPx(19.0f);
        }
    }

    private int getStarHeight(Size size) {
        switch (size) {
            case EXTRA_SMALL:
                return (int) ValueUtil.convertDpToPx(10.0f);
            case SMALL:
                return (int) ValueUtil.convertDpToPx(13.0f);
            case INTERMEDIATE:
                return (int) ValueUtil.convertDpToPx(16.0f);
            case MEDIUM:
                return (int) ValueUtil.convertDpToPx(18.0f);
            case LARGE:
                return (int) ValueUtil.convertDpToPx(34.0f);
            default:
                return (int) ValueUtil.convertDpToPx(18.0f);
        }
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.star_rating_view, this);
        this.mProductRatingText = (TextView) inflate.findViewById(R.id.star_rating_view_count);
        this.mProductRatingStarOne = (ImageView) inflate.findViewById(R.id.star_rating_view_image_one);
        this.mProductRatingStarTwo = (ImageView) inflate.findViewById(R.id.star_rating_view_image_two);
        this.mProductRatingStarThree = (ImageView) inflate.findViewById(R.id.star_rating_view_image_three);
        this.mProductRatingStarFour = (ImageView) inflate.findViewById(R.id.star_rating_view_image_four);
        this.mProductRatingStarFive = (ImageView) inflate.findViewById(R.id.star_rating_view_image_five);
    }

    /* access modifiers changed from: protected */
    public ArrayList<ImageView> getStarViews() {
        ArrayList<ImageView> arrayList = new ArrayList<>();
        arrayList.add(this.mProductRatingStarOne);
        arrayList.add(this.mProductRatingStarTwo);
        arrayList.add(this.mProductRatingStarThree);
        arrayList.add(this.mProductRatingStarFour);
        arrayList.add(this.mProductRatingStarFive);
        return arrayList;
    }
}
