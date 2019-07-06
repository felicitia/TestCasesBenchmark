package com.contextlogic.wish.ui.slideshow;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishImageSlideshow;
import com.contextlogic.wish.api.model.WishImageSlideshowSlide;
import com.contextlogic.wish.api.model.WishImageSlideshowSlide.TransitionType;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.ui.image.ImageRestorable;
import com.contextlogic.wish.ui.view.Recyclable;
import java.util.ArrayList;

public class ImageSlideshowView extends FrameLayout implements ImageRestorable, Recyclable {
    /* access modifiers changed from: private */
    public AnimatorSet mCurrentAnimationSet;
    /* access modifiers changed from: private */
    public ImageSlideshowSlideView mCurrentSlide;
    private int mCurrentSlideIndex;
    private View mErrorMessage;
    private Handler mHandler;
    private View mLoadingSpinner;
    /* access modifiers changed from: private */
    public ImageSlideshowSlideView mNextSlide;
    private Runnable mNextSlideRunnable;
    private int mSlideRetryCount;
    private WishImageSlideshow mSlideshow;
    private boolean mStarted;
    /* access modifiers changed from: private */
    public boolean mTimerElapsed;

    public ImageSlideshowView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.image_slideshow, this);
        this.mLoadingSpinner = inflate.findViewById(R.id.image_slideshow_loading_view);
        this.mErrorMessage = inflate.findViewById(R.id.image_slideshow_error_view);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mNextSlideRunnable = new Runnable() {
            public void run() {
                if (ImageSlideshowView.this.mNextSlide == null || !ImageSlideshowView.this.mNextSlide.isReady()) {
                    ImageSlideshowView.this.mTimerElapsed = true;
                } else {
                    ImageSlideshowView.this.performTransition();
                }
            }
        };
        this.mCurrentSlide = new ImageSlideshowSlideView(getContext());
        this.mCurrentSlide.setImageSlideshowView(this);
        this.mNextSlide = new ImageSlideshowSlideView(getContext());
        this.mNextSlide.setImageSlideshowView(this);
    }

    public void setSlideshow(WishImageSlideshow wishImageSlideshow) {
        this.mSlideshow = wishImageSlideshow;
        startSlideshow();
    }

    private void startSlideshow() {
        cancelDelayedAction();
        if (this.mCurrentAnimationSet != null) {
            this.mCurrentAnimationSet.cancel();
        }
        this.mCurrentSlide.setSlide(null);
        removeView(this.mCurrentSlide);
        this.mNextSlide.setSlide(null);
        removeView(this.mNextSlide);
        this.mCurrentSlideIndex = 0;
        this.mStarted = false;
        this.mSlideRetryCount = 0;
        if (this.mSlideshow != null && this.mSlideshow.getSlides() != null && this.mSlideshow.getSlides().size() > 0) {
            showLoadingSpinner();
            this.mCurrentSlide.setVisibility(4);
            addView(this.mCurrentSlide, new LayoutParams(-1, -1));
            this.mCurrentSlide.setSlide((WishImageSlideshowSlide) this.mSlideshow.getSlides().get(0));
        }
    }

    private void cancelDelayedAction() {
        if (this.mHandler != null && this.mNextSlideRunnable != null) {
            this.mHandler.removeCallbacks(this.mNextSlideRunnable);
        }
    }

    /* access modifiers changed from: private */
    public void performTransition() {
        if (this.mNextSlide != null && this.mNextSlide.getSlide() != null) {
            TransitionType transitionType = this.mNextSlide.getSlide().getTransitionType();
            long transitionDuration = this.mNextSlide.getSlide().getTransitionDuration();
            if (transitionType == TransitionType.None) {
                this.mNextSlide.setVisibility(0);
                completeTransition();
            } else if (transitionType == TransitionType.SlideLeft || transitionType == TransitionType.SlideRight || transitionType == TransitionType.SlideDown || transitionType == TransitionType.SlideUp) {
                performSlideAnimation(transitionType, transitionDuration);
            } else if (transitionType == TransitionType.Fade) {
                performFadeAnimation(transitionDuration);
            } else if (transitionType == TransitionType.FlipLeft || transitionType == TransitionType.FlipRight || transitionType == TransitionType.FlipDown || transitionType == TransitionType.FlipUp) {
                performFlipAnimation(transitionType, transitionDuration);
            }
        }
    }

    private void performFadeAnimation(long j) {
        performSimpleObjectAnimatorAnimation("alpha", 1.0f, 0.0f, 0.0f, 1.0f, j);
    }

    private void performSlideAnimation(TransitionType transitionType, long j) {
        String str;
        int i;
        String str2;
        String str3;
        int width = this.mCurrentSlide.getWidth();
        int height = this.mCurrentSlide.getHeight();
        if (transitionType == TransitionType.SlideLeft) {
            str3 = "translationX";
            height = -width;
        } else {
            if (transitionType == TransitionType.SlideRight) {
                str2 = "translationX";
                height = -width;
            } else if (transitionType == TransitionType.SlideDown) {
                str3 = "translationY";
                width = -height;
            } else if (transitionType == TransitionType.SlideUp) {
                str2 = "translationY";
                width = -height;
            } else {
                str = null;
                i = 0;
                height = 0;
                performSimpleObjectAnimatorAnimation(str, (float) 0, (float) height, (float) i, (float) 0, j);
            }
            int i2 = width;
            str = str2;
            i = height;
            height = i2;
            performSimpleObjectAnimatorAnimation(str, (float) 0, (float) height, (float) i, (float) 0, j);
        }
        int i3 = width;
        str = str3;
        i = i3;
        performSimpleObjectAnimatorAnimation(str, (float) 0, (float) height, (float) i, (float) 0, j);
    }

    private void performSimpleObjectAnimatorAnimation(String str, float f, float f2, float f3, float f4, long j) {
        if (str != null) {
            this.mNextSlide.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.mCurrentSlide, str, new float[]{f, f2}));
            arrayList.add(ObjectAnimator.ofFloat(this.mNextSlide, str, new float[]{f3, f4}));
            this.mCurrentAnimationSet = new AnimatorSet();
            this.mCurrentAnimationSet.playTogether((Animator[]) arrayList.toArray(new ObjectAnimator[arrayList.size()]));
            this.mCurrentAnimationSet.setDuration(j);
            this.mCurrentAnimationSet.addListener(new AnimatorListener() {
                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    ImageSlideshowView.this.mCurrentSlide.setVisibility(4);
                    ImageSlideshowView.this.mCurrentSlide.setTranslationX(0.0f);
                    ImageSlideshowView.this.mCurrentSlide.setTranslationY(0.0f);
                    ImageSlideshowView.this.mCurrentSlide.setAlpha(1.0f);
                    ImageSlideshowView.this.completeTransition();
                }

                public void onAnimationCancel(Animator animator) {
                    ImageSlideshowView.this.mNextSlide.setVisibility(4);
                    ImageSlideshowView.this.mNextSlide.setTranslationX(0.0f);
                    ImageSlideshowView.this.mNextSlide.setTranslationY(0.0f);
                    ImageSlideshowView.this.mNextSlide.setAlpha(1.0f);
                    ImageSlideshowView.this.mCurrentSlide.setTranslationX(0.0f);
                    ImageSlideshowView.this.mCurrentSlide.setTranslationY(0.0f);
                    ImageSlideshowView.this.mCurrentSlide.setAlpha(1.0f);
                }
            });
            this.mCurrentAnimationSet.start();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void performFlipAnimation(com.contextlogic.wish.api.model.WishImageSlideshowSlide.TransitionType r9, final long r10) {
        /*
            r8 = this;
            com.contextlogic.wish.ui.slideshow.ImageSlideshowSlideView r0 = r8.mCurrentSlide
            int r0 = r0.getWidth()
            com.contextlogic.wish.ui.slideshow.ImageSlideshowSlideView r1 = r8.mCurrentSlide
            int r1 = r1.getHeight()
            com.contextlogic.wish.api.model.WishImageSlideshowSlide$TransitionType r2 = com.contextlogic.wish.api.model.WishImageSlideshowSlide.TransitionType.FlipLeft
            r3 = 90
            r4 = -90
            r5 = 0
            if (r9 != r2) goto L_0x001e
            java.lang.String r9 = "rotationY"
            int r0 = r0 * 10
        L_0x0019:
            r3 = -90
            r4 = 90
            goto L_0x003d
        L_0x001e:
            com.contextlogic.wish.api.model.WishImageSlideshowSlide$TransitionType r2 = com.contextlogic.wish.api.model.WishImageSlideshowSlide.TransitionType.FlipRight
            if (r9 != r2) goto L_0x0027
            java.lang.String r9 = "rotationY"
            int r0 = r0 * 10
            goto L_0x003d
        L_0x0027:
            com.contextlogic.wish.api.model.WishImageSlideshowSlide$TransitionType r0 = com.contextlogic.wish.api.model.WishImageSlideshowSlide.TransitionType.FlipDown
            if (r9 != r0) goto L_0x0030
            java.lang.String r9 = "rotationX"
            int r0 = r1 * 10
            goto L_0x0019
        L_0x0030:
            com.contextlogic.wish.api.model.WishImageSlideshowSlide$TransitionType r0 = com.contextlogic.wish.api.model.WishImageSlideshowSlide.TransitionType.FlipUp
            if (r9 != r0) goto L_0x0039
            java.lang.String r9 = "rotationX"
            int r0 = r1 * 10
            goto L_0x003d
        L_0x0039:
            r9 = 0
            r0 = 0
            r3 = 0
            r4 = 0
        L_0x003d:
            if (r9 == 0) goto L_0x009e
            com.contextlogic.wish.ui.slideshow.ImageSlideshowSlideView r1 = r8.mCurrentSlide
            r2 = 2
            float[] r6 = new float[r2]
            float r7 = (float) r5
            r6[r5] = r7
            float r3 = (float) r3
            r7 = 1
            r6[r7] = r3
            android.animation.ObjectAnimator r1 = android.animation.ObjectAnimator.ofFloat(r1, r9, r6)
            com.contextlogic.wish.ui.slideshow.ImageSlideshowSlideView r3 = r8.mNextSlide
            float[] r2 = new float[r2]
            float r4 = (float) r4
            r2[r5] = r4
            float r4 = (float) r5
            r2[r7] = r4
            android.animation.ObjectAnimator r9 = android.animation.ObjectAnimator.ofFloat(r3, r9, r2)
            com.contextlogic.wish.ui.slideshow.ImageSlideshowSlideView r2 = r8.mCurrentSlide
            float r0 = (float) r0
            r2.setCameraDistance(r0)
            com.contextlogic.wish.ui.slideshow.ImageSlideshowSlideView r2 = r8.mNextSlide
            r2.setCameraDistance(r0)
            com.contextlogic.wish.application.WishApplication r0 = com.contextlogic.wish.application.WishApplication.getInstance()
            android.content.res.Resources r0 = r0.getResources()
            r2 = 2131034529(0x7f0501a1, float:1.7679578E38)
            int r0 = r0.getColor(r2)
            r8.setBackgroundColor(r0)
            android.animation.AnimatorSet r0 = new android.animation.AnimatorSet
            r0.<init>()
            r8.mCurrentAnimationSet = r0
            android.animation.AnimatorSet r0 = r8.mCurrentAnimationSet
            r0.play(r1)
            android.animation.AnimatorSet r0 = r8.mCurrentAnimationSet
            r1 = 2
            long r1 = r10 / r1
            r0.setDuration(r1)
            android.animation.AnimatorSet r0 = r8.mCurrentAnimationSet
            com.contextlogic.wish.ui.slideshow.ImageSlideshowView$3 r1 = new com.contextlogic.wish.ui.slideshow.ImageSlideshowView$3
            r1.<init>(r9, r10)
            r0.addListener(r1)
            android.animation.AnimatorSet r9 = r8.mCurrentAnimationSet
            r9.start()
        L_0x009e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.ui.slideshow.ImageSlideshowView.performFlipAnimation(com.contextlogic.wish.api.model.WishImageSlideshowSlide$TransitionType, long):void");
    }

    /* access modifiers changed from: private */
    public void completeTransition() {
        removeView(this.mCurrentSlide);
        ImageSlideshowSlideView imageSlideshowSlideView = this.mCurrentSlide;
        this.mCurrentSlide = this.mNextSlide;
        this.mNextSlide = imageSlideshowSlideView;
        this.mCurrentSlideIndex = getNextSlideIndex();
        loadNextSlide();
    }

    private void loadNextSlide() {
        if (this.mCurrentSlide != null && this.mCurrentSlide.getSlide() != null && this.mNextSlide != null) {
            if (this.mNextSlide.getParent() != null) {
                removeView(this.mNextSlide);
            }
            this.mNextSlide.setVisibility(4);
            addView(this.mNextSlide);
            int nextSlideIndex = getNextSlideIndex();
            if (nextSlideIndex != -1) {
                WishImageSlideshowSlide wishImageSlideshowSlide = (WishImageSlideshowSlide) this.mSlideshow.getSlides().get(nextSlideIndex);
                this.mSlideRetryCount = 0;
                this.mNextSlide.setSlide(wishImageSlideshowSlide);
                this.mTimerElapsed = false;
                cancelDelayedAction();
                this.mHandler.postDelayed(this.mNextSlideRunnable, this.mCurrentSlide.getSlide().getDuration());
            }
        }
    }

    private int getNextSlideIndex() {
        int i = -1;
        if (this.mSlideshow == null) {
            return -1;
        }
        int i2 = this.mCurrentSlideIndex + 1;
        if (i2 < this.mSlideshow.getSlides().size()) {
            i = i2;
        } else if (this.mSlideshow.getLoop()) {
            i = 0;
        }
        return i;
    }

    private void showLoadingSpinner() {
        hideErrorMessage();
        this.mCurrentSlide.setVisibility(4);
        this.mNextSlide.setVisibility(4);
        this.mLoadingSpinner.setVisibility(0);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
    }

    private void hideLoadingSpinner() {
        this.mLoadingSpinner.setVisibility(8);
    }

    private void showErrorMessage() {
        hideLoadingSpinner();
        this.mCurrentSlide.setVisibility(4);
        this.mNextSlide.setVisibility(4);
        this.mErrorMessage.setVisibility(0);
        setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.white));
    }

    private void hideErrorMessage() {
        this.mErrorMessage.setVisibility(8);
    }

    public void recycle() {
        cancelDelayedAction();
        if (this.mCurrentAnimationSet != null) {
            this.mCurrentAnimationSet.cancel();
        }
        if (this.mCurrentSlide != null) {
            this.mCurrentSlide.recycle();
        }
        if (this.mNextSlide != null) {
            this.mNextSlide.recycle();
        }
    }

    public void releaseImages() {
        cancelDelayedAction();
        if (this.mCurrentAnimationSet != null) {
            this.mCurrentAnimationSet.cancel();
        }
        if (this.mCurrentSlide != null) {
            this.mCurrentSlide.releaseImages();
        }
        if (this.mNextSlide != null) {
            this.mNextSlide.setSlide(null);
        }
    }

    public void restoreImages() {
        if (!this.mStarted || this.mCurrentSlide == null) {
            if (!this.mStarted && this.mSlideshow != null) {
                setSlideshow(this.mSlideshow);
            }
        } else if (this.mCurrentSlide.getSlide() != null) {
            this.mCurrentSlide.restoreImages();
            this.mCurrentSlide.setVisibility(0);
            loadNextSlide();
        } else if (this.mSlideshow != null) {
            setSlideshow(this.mSlideshow);
        }
    }

    public void onSlideLoadComplete(ImageSlideshowSlideView imageSlideshowSlideView) {
        if (!this.mStarted) {
            hideLoadingSpinner();
            hideErrorMessage();
            setBackgroundColor(WishApplication.getInstance().getResources().getColor(R.color.transparent));
            this.mCurrentSlide.setVisibility(0);
            this.mStarted = true;
            loadNextSlide();
        } else if (this.mTimerElapsed) {
            performTransition();
        }
    }

    public void onSlideLoadFailed(ImageSlideshowSlideView imageSlideshowSlideView) {
        this.mSlideRetryCount++;
        if (this.mSlideRetryCount < 3) {
            loadNextSlide();
        } else if (this.mStarted) {
            cancelDelayedAction();
        } else {
            showErrorMessage();
        }
    }
}
