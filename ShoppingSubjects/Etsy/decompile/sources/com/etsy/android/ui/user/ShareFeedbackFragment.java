package com.etsy.android.ui.user;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.m;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.ShortenedUrl;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.apiv3.AppreciationPhoto;
import com.etsy.android.lib.models.interfaces.AppreciationPhotoLike;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.l;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.util.AnimationUtil;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.SocialShareUtil;
import com.etsy.android.uikit.util.SocialShareUtil.ShareType;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import java.util.List;

public class ShareFeedbackFragment extends EtsyFragment {
    /* access modifiers changed from: private */
    public AppreciationPhoto mAppreciationPhoto;
    private OnClickListener mClickListener = new TrackingOnClickListener() {
        public void onPreTrack() {
            addEventTrackedObjects(ShareFeedbackFragment.this.mTransaction);
        }

        public void onViewClick(View view) {
            if (view.getId() == R.id.share_feedback_share_button) {
                SocialShareUtil.a(ShareFeedbackFragment.this.getActivity().getLocalClassName(), ShareType.APPRECIATION_PHOTO, ShareFeedbackFragment.this.mTransaction.getTransactionId().getId());
                e.a(ShareFeedbackFragment.this.getActivity()).a().a((AppreciationPhotoLike) ShareFeedbackFragment.this.mAppreciationPhoto);
            } else if (view.getId() != R.id.share_feedback_dismiss_button) {
            } else {
                if (ShareFeedbackFragment.this.getActivity() instanceof BOENavDrawerActivity) {
                    ((BOENavDrawerActivity) ShareFeedbackFragment.this.getActivity()).popOrGoBack();
                } else {
                    ShareFeedbackFragment.this.getActivity().finish();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public RelativeLayout mShareFeedbackContainer;
    /* access modifiers changed from: private */
    public TextView mShareFeedbackCta;
    /* access modifiers changed from: private */
    public ImageView mShareFeedbackDismissButton;
    /* access modifiers changed from: private */
    public ImageView mShareFeedbackPhoto;
    /* access modifiers changed from: private */
    public TextView mShareFeedbackReason;
    /* access modifiers changed from: private */
    public Button mShareFeedbackShareButton;
    /* access modifiers changed from: private */
    public Transaction mTransaction;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTransaction = (Transaction) getArguments().getSerializable("transaction");
        if (bundle != null) {
            this.mAppreciationPhoto = (AppreciationPhoto) bundle.getSerializable(ResponseConstants.APPRECIATION_PHOTO);
        } else {
            this.mAppreciationPhoto = this.mTransaction.getReview().getAppreciationPhoto();
            this.mAppreciationPhoto.setShopName(getArguments().getString(ResponseConstants.SHOP_NAME, getString(R.string.review_share_default_shopname)));
            this.mAppreciationPhoto.setShortenedShareUrl(new ShortenedUrl(AppreciationPhoto.buildShareUrl(this.mTransaction.getTransactionId())));
            this.mAppreciationPhoto.setSellerAvatarUrl(getArguments().getString("seller_avatar_url"));
            this.mAppreciationPhoto.setListingTitle(this.mTransaction.getTitle());
        }
        if (!this.mAppreciationPhoto.getShortenedShareUrl().isShortened()) {
            getRequestQueue().a((Object) this, (g<Result>) m.a(ShortenedUrl.class, "/etsyapps/v3/public/shorten-url").a("url", this.mAppreciationPhoto.getShortenedShareUrl().getLongUrl()).a((c<Result>) new c<ShortenedUrl>() {
                public void a(List<ShortenedUrl> list, int i, k<ShortenedUrl> kVar) {
                    if (ShareFeedbackFragment.this.mAppreciationPhoto.getShortenedShareUrl() != null) {
                        ShareFeedbackFragment.this.mAppreciationPhoto.setShortenedShareUrl((ShortenedUrl) list.get(0));
                    }
                }
            }).a());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle);
        if (viewGroup2 == null) {
            viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.fragment_share_feedback_dialog, viewGroup, false);
        }
        this.mShareFeedbackDismissButton = (ImageView) viewGroup2.findViewById(R.id.share_feedback_dismiss_button);
        this.mShareFeedbackDismissButton.setOnClickListener(this.mClickListener);
        this.mShareFeedbackShareButton = (Button) viewGroup2.findViewById(R.id.share_feedback_share_button);
        this.mShareFeedbackShareButton.setOnClickListener(this.mClickListener);
        ImageView imageView = (ImageView) viewGroup2.findViewById(R.id.share_feedback_shop_avatar);
        if (!TextUtils.isEmpty(this.mAppreciationPhoto.getSellerAvatarUrl())) {
            getImageBatch().a(this.mAppreciationPhoto.getSellerAvatarUrl(), imageView);
        }
        this.mShareFeedbackPhoto = (ImageView) viewGroup2.findViewById(R.id.share_feedback_photo);
        this.mShareFeedbackReason = (TextView) viewGroup2.findViewById(R.id.share_feedback_reason);
        this.mShareFeedbackCta = (TextView) viewGroup2.findViewById(R.id.share_feedback_cta);
        this.mShareFeedbackCta.setText(String.format(getString(R.string.review_share_cta), new Object[]{this.mAppreciationPhoto.getShopName()}));
        aj.a(viewGroup2);
        this.mShareFeedbackContainer = (RelativeLayout) viewGroup2.findViewById(R.id.share_feedback_container);
        this.mShareFeedbackContainer.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                ((LayoutParams) ShareFeedbackFragment.this.mShareFeedbackContainer.getLayoutParams()).setMargins(0, ((new l(ShareFeedbackFragment.this.getActivity()).e() - l.b((Context) ShareFeedbackFragment.this.getActivity())) - ShareFeedbackFragment.this.mShareFeedbackContainer.getHeight()) / 2, 0, 0);
                ShareFeedbackFragment.this.mShareFeedbackContainer.getViewTreeObserver().removeOnPreDrawListener(this);
                ShareFeedbackFragment.this.mShareFeedbackContainer.setVisibility(0);
                ShareFeedbackFragment.this.startIntroductionAnimation();
                return true;
            }
        });
        return viewGroup2;
    }

    public void onDestroyView() {
        if (this.mShareFeedbackCta != null) {
            this.mShareFeedbackCta.animate().cancel();
        }
        super.onDestroyView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(ResponseConstants.APPRECIATION_PHOTO, this.mAppreciationPhoto);
    }

    /* access modifiers changed from: private */
    public void startIntroductionAnimation() {
        AnonymousClass3 r0 = new HardwareAnimatorListener(this.mShareFeedbackCta) {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ShareFeedbackFragment.this.mShareFeedbackCta.setVisibility(8);
                ((LayoutParams) ShareFeedbackFragment.this.mShareFeedbackContainer.getLayoutParams()).setMargins(0, 0, 0, 0);
                ShareFeedbackFragment.this.getImageBatch().a(ShareFeedbackFragment.this.mAppreciationPhoto.getImageUrl(), ShareFeedbackFragment.this.mShareFeedbackPhoto);
                AnimationUtil.a((View) ShareFeedbackFragment.this.mShareFeedbackReason, 300);
                AnimationUtil.a((View) ShareFeedbackFragment.this.mShareFeedbackDismissButton, 300);
                AnimationUtil.a((View) ShareFeedbackFragment.this.mShareFeedbackPhoto, 300);
                AnimationUtil.a((View) ShareFeedbackFragment.this.mShareFeedbackShareButton, 300);
            }
        };
        this.mShareFeedbackCta.animate().cancel();
        this.mShareFeedbackCta.setAlpha(1.0f);
        this.mShareFeedbackCta.animate().setDuration(200).setStartDelay(3500).alpha(0.0f).setListener(r0).start();
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }
}
