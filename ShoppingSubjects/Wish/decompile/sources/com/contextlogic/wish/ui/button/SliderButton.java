package com.contextlogic.wish.ui.button;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.util.ValueUtil;

public class SliderButton extends FrameLayout {
    private boolean mAnimationAllowed;
    private Object mAnimationLock;
    private boolean mAtEnd;
    private TextView mConfirmedText;
    /* access modifiers changed from: private */
    public View mConfirmedView;
    private Animation mCurrentAnimation;
    private int mCurrentX;
    /* access modifiers changed from: private */
    public Handler mDelayHandler;
    private OnClickListener mSlideListener;
    private TextView mSlideText;
    private View mSwitchView;
    private boolean mTouchDown;

    public SliderButton(Context context) {
        this(context, null);
    }

    public SliderButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SliderButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.slider_button, this);
        this.mSwitchView = inflate.findViewById(R.id.slider_button_slide_switch);
        this.mSlideText = (TextView) inflate.findViewById(R.id.slider_button_slide_text);
        this.mConfirmedView = inflate.findViewById(R.id.slider_button_confirmed_view);
        this.mConfirmedText = (TextView) inflate.findViewById(R.id.slider_button_confirmed_text);
        this.mAnimationAllowed = true;
        this.mDelayHandler = new Handler(Looper.getMainLooper());
        this.mConfirmedView.setVisibility(8);
        this.mAnimationLock = new Object();
        startBounceAnimation();
    }

    public void setSlideListener(OnClickListener onClickListener) {
        this.mSlideListener = onClickListener;
    }

    private int getFirstBounceDistance() {
        Resources resources = getResources();
        if (resources != null) {
            return resources.getDimensionPixelOffset(R.dimen.slider_button_first_bounce_distance);
        }
        return 30;
    }

    private int getSecondBounceDistance() {
        Resources resources = getResources();
        if (resources != null) {
            return resources.getDimensionPixelOffset(R.dimen.slider_button_second_bounce_distance);
        }
        return 15;
    }

    /* access modifiers changed from: private */
    public void startBounceAnimation() {
        stopAllAnimation();
        if (this.mAnimationAllowed) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, (float) getFirstBounceDistance(), 0.0f, 0.0f);
            translateAnimation.setDuration(100);
            translateAnimation.setFillAfter(true);
            translateAnimation.setFillBefore(true);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    SliderButton.this.startBounceAnimationStep2();
                }
            });
            synchronized (this.mAnimationLock) {
                this.mCurrentAnimation = translateAnimation;
                this.mSwitchView.startAnimation(translateAnimation);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startBounceAnimationStep2() {
        stopAllAnimation();
        if (this.mAnimationAllowed) {
            TranslateAnimation translateAnimation = new TranslateAnimation((float) getFirstBounceDistance(), 0.0f, 0.0f, 0.0f);
            translateAnimation.setDuration(100);
            translateAnimation.setFillAfter(true);
            translateAnimation.setFillBefore(true);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    SliderButton.this.startBounceAnimationStep3();
                }
            });
            synchronized (this.mAnimationLock) {
                this.mCurrentAnimation = translateAnimation;
                this.mSwitchView.startAnimation(translateAnimation);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startBounceAnimationStep3() {
        stopAllAnimation();
        if (this.mAnimationAllowed) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, (float) getSecondBounceDistance(), 0.0f, 0.0f);
            translateAnimation.setDuration(50);
            translateAnimation.setFillAfter(true);
            translateAnimation.setFillBefore(true);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    SliderButton.this.startBounceAnimationStep4();
                }
            });
            synchronized (this.mAnimationLock) {
                this.mCurrentAnimation = translateAnimation;
                this.mSwitchView.startAnimation(translateAnimation);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startBounceAnimationStep4() {
        stopAllAnimation();
        if (this.mAnimationAllowed) {
            TranslateAnimation translateAnimation = new TranslateAnimation((float) getSecondBounceDistance(), 0.0f, 0.0f, 0.0f);
            translateAnimation.setDuration(50);
            translateAnimation.setFillAfter(true);
            translateAnimation.setFillBefore(true);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    SliderButton.this.stopAllAnimation();
                    SliderButton.this.mDelayHandler.postDelayed(new Runnable() {
                        public void run() {
                            SliderButton.this.startBounceAnimation();
                        }
                    }, 1500);
                }
            });
            synchronized (this.mAnimationLock) {
                this.mCurrentAnimation = translateAnimation;
                this.mSwitchView.startAnimation(translateAnimation);
            }
        }
    }

    private void claimMotionEvents() {
        ViewParent viewParent = this;
        while (true) {
            viewParent = viewParent.getParent();
            if (viewParent != null) {
                viewParent.requestDisallowInterceptTouchEvent(true);
            } else {
                return;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mConfirmedView.getVisibility() == 0) {
            return true;
        }
        int action = motionEvent.getAction();
        int width = (int) (((double) this.mSwitchView.getWidth()) * 1.3d);
        if (action == 0) {
            if (motionEvent.getX() <= ((float) width)) {
                this.mTouchDown = true;
                this.mAnimationAllowed = false;
                positionSwitch((int) motionEvent.getX());
                claimMotionEvents();
            }
        } else if (action == 2) {
            if (this.mTouchDown) {
                positionSwitch((int) motionEvent.getX());
            }
        } else if (action == 3 || action == 1) {
            if (this.mTouchDown) {
                positionSwitch((int) motionEvent.getX());
                handleTouchEnd();
            } else {
                stopAllAnimation();
                startBounceAnimation();
            }
            this.mTouchDown = false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void positionSwitch(int i) {
        stopAllAnimation();
        int width = getWidth();
        int width2 = this.mSwitchView.getWidth();
        int i2 = width - width2;
        int i3 = i - (width2 / 2);
        boolean z = false;
        int min = Math.min(i2, Math.max(0, i3));
        LayoutParams layoutParams = (LayoutParams) this.mSwitchView.getLayoutParams();
        layoutParams.leftMargin = min;
        this.mSwitchView.setLayoutParams(layoutParams);
        int convertDpToPx = (int) ValueUtil.convertDpToPx(30.0f);
        this.mSlideText.setTextColor(this.mSlideText.getTextColors().withAlpha((int) ((1.0d - (((double) min) / ((double) i2))) * 255.0d)));
        this.mCurrentX = min;
        if (min >= i2 - convertDpToPx) {
            z = true;
        }
        this.mAtEnd = z;
    }

    private void handleTouchEnd() {
        this.mAnimationAllowed = true;
        if (this.mAtEnd) {
            if (this.mSlideListener != null) {
                this.mSlideListener.onClick(this);
            }
            showConfirmedView();
            return;
        }
        animateSwitchBack();
    }

    private void animateSwitchBack() {
        stopAllAnimation();
        if (this.mAnimationAllowed) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, (float) (-this.mCurrentX), 0.0f, 0.0f);
            translateAnimation.setDuration(150);
            translateAnimation.setFillAfter(false);
            translateAnimation.setFillBefore(true);
            translateAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    SliderButton.this.stopAllAnimation();
                    SliderButton.this.positionSwitch(0);
                    SliderButton.this.mDelayHandler.postDelayed(new Runnable() {
                        public void run() {
                            SliderButton.this.startBounceAnimation();
                        }
                    }, 1000);
                }
            });
            synchronized (this.mAnimationLock) {
                this.mCurrentAnimation = translateAnimation;
                this.mSwitchView.startAnimation(translateAnimation);
            }
        }
    }

    private void showConfirmedView() {
        this.mConfirmedView.clearAnimation();
        this.mConfirmedView.setVisibility(0);
        positionSwitch(0);
        this.mDelayHandler.postDelayed(new Runnable() {
            public void run() {
                SliderButton.this.fadeAwayConfirmedView();
            }
        }, 1000);
    }

    /* access modifiers changed from: private */
    public void fadeAwayConfirmedView() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setFillBefore(true);
        alphaAnimation.setFillAfter(false);
        alphaAnimation.setDuration(500);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                SliderButton.this.mConfirmedView.setVisibility(8);
                SliderButton.this.mConfirmedView.clearAnimation();
            }
        });
        this.mConfirmedView.startAnimation(alphaAnimation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:5|6|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stopAllAnimation() {
        /*
            r3 = this;
            android.os.Handler r0 = r3.mDelayHandler
            r1 = 0
            r0.removeCallbacksAndMessages(r1)
            java.lang.Object r0 = r3.mAnimationLock
            monitor-enter(r0)
            android.view.animation.Animation r2 = r3.mCurrentAnimation     // Catch:{ all -> 0x001b }
            if (r2 == 0) goto L_0x0019
            android.view.View r2 = r3.mSwitchView     // Catch:{ all -> 0x001b }
            r2.clearAnimation()     // Catch:{ all -> 0x001b }
            android.view.animation.Animation r2 = r3.mCurrentAnimation     // Catch:{ Exception -> 0x0017 }
            r2.cancel()     // Catch:{ Exception -> 0x0017 }
        L_0x0017:
            r3.mCurrentAnimation = r1     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.ui.button.SliderButton.stopAllAnimation():void");
    }

    public void resetState() {
        stopAllAnimation();
        this.mTouchDown = false;
        this.mAnimationAllowed = true;
        startBounceAnimation();
        this.mConfirmedView.clearAnimation();
        this.mConfirmedView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        if (i == 8) {
            resetState();
        } else if (i == 0 && this.mCurrentAnimation == null) {
            startBounceAnimation();
        }
    }

    public void setConfirmedText(String str) {
        this.mConfirmedText.setText(str);
    }

    public void setSlideText(String str) {
        this.mSlideText.setText(str);
    }
}
