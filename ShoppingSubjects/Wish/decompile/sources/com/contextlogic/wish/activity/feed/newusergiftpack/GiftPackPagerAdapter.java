package com.contextlogic.wish.activity.feed.newusergiftpack;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.contextlogic.wish.activity.BaseFragment;
import com.contextlogic.wish.api.model.NewUserGiftPackSpec.CardSpec;
import java.util.List;

public class GiftPackPagerAdapter extends PagerAdapter {
    private List<CardSpec> mCardSpecs;
    private BaseFragment mFragment;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public GiftPackPagerAdapter(BaseFragment baseFragment, List<CardSpec> list) {
        this.mFragment = baseFragment;
        this.mCardSpecs = list;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        CardSpec item = getItem(i);
        if (item == null || !item.isDailyLoginCard()) {
            GiftPackHeaderCardView giftPackHeaderCardView = new GiftPackHeaderCardView(viewGroup.getContext());
            if (item != null) {
                giftPackHeaderCardView.setup(item, this.mFragment);
                viewGroup.addView(giftPackHeaderCardView);
            }
            return giftPackHeaderCardView;
        }
        GiftPackHeaderDailyLoginView giftPackHeaderDailyLoginView = new GiftPackHeaderDailyLoginView(viewGroup.getContext());
        giftPackHeaderDailyLoginView.setup(item);
        viewGroup.addView(giftPackHeaderDailyLoginView);
        return giftPackHeaderDailyLoginView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof GiftPackHeaderCardView) {
            ((GiftPackHeaderCardView) obj).releaseImages();
        }
    }

    public int getCount() {
        if (this.mCardSpecs == null) {
            return 0;
        }
        return this.mCardSpecs.size();
    }

    public CardSpec getItem(int i) {
        if (i < getCount()) {
            return (CardSpec) this.mCardSpecs.get(i);
        }
        return null;
    }
}
