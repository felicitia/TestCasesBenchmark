package com.etsy.android.stylekit.views;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.stylekit.a.C0085a;
import com.etsy.android.stylekit.a.e;
import com.etsy.android.stylekit.a.i;

public class FavoriteView extends AppCompatImageButton {
    /* access modifiers changed from: private */
    @Nullable
    public a mRequestListener;

    public interface a {
        void a(boolean z);
    }

    public FavoriteView(Context context) {
        super(context);
        init();
    }

    public FavoriteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FavoriteView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setBackground(null);
        setImageDrawable(AppCompatResources.getDrawable(getContext(), e.ic_sl_favorite_states));
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FavoriteView.this.mRequestListener != null) {
                    FavoriteView.this.mRequestListener.a(!view.isSelected());
                }
                FavoriteView.this.setFavoriteState(!view.isSelected(), true);
            }
        });
    }

    public void setRequestFavoriteChangeListener(a aVar) {
        this.mRequestListener = aVar;
    }

    public void setFavoriteState(boolean z) {
        setFavoriteState(z, false);
    }

    public void setFavoriteState(boolean z, boolean z2) {
        if (isSelected() != z) {
            final String string = getResources().getString(z ? i.content_description_listing_favorited : i.content_description_listing_favorite);
            if (!z2 || !z) {
                setSelected(z);
                setContentDescription(string);
            } else {
                animate().cancel();
                AnonymousClass2 r4 = new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        FavoriteView.this.setSelected(true);
                        FavoriteView.this.setContentDescription(string);
                    }
                };
                Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), C0085a.favorite_grow);
                loadAnimator.setTarget(this);
                loadAnimator.addListener(r4);
                Animator loadAnimator2 = AnimatorInflater.loadAnimator(getContext(), C0085a.favorite_shrink);
                loadAnimator2.setTarget(this);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(new Animator[]{loadAnimator, loadAnimator2});
                animatorSet.start();
            }
        }
    }
}
