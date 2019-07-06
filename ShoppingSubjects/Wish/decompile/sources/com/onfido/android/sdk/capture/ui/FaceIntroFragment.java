package com.onfido.android.sdk.capture.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.util.HashMap;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public final class FaceIntroFragment extends BulletedMessageFragment {
    public static final Companion Companion = new Companion(null);
    private HashMap a;
    public FaceIntroPresenter presenter;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final BulletedMessageFragment createInstance(String str, String str2, List<String> list) {
            Intrinsics.checkParameterIsNotNull(str, StrongAuth.AUTH_TITLE);
            Intrinsics.checkParameterIsNotNull(str2, "subtitle");
            Intrinsics.checkParameterIsNotNull(list, "bullets");
            FaceIntroFragment faceIntroFragment = new FaceIntroFragment();
            faceIntroFragment.setInfo(str, str2, list);
            return faceIntroFragment;
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this.a != null) {
            this.a.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        View view = (View) this.a.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this.a.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        SdkController.getSdkComponent$default(SdkController.Companion.getInstance(), null, 1, null).inject(this);
        return onCreateView;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onStart() {
        super.onStart();
        FaceIntroPresenter faceIntroPresenter = this.presenter;
        if (faceIntroPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        faceIntroPresenter.trackFaceIntro(true);
    }

    public void onStop() {
        super.onStop();
        FaceIntroPresenter faceIntroPresenter = this.presenter;
        if (faceIntroPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        faceIntroPresenter.trackFaceIntro(false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        View childAt = ((LinearLayout) _$_findCachedViewById(R.id.stepsContainer)).getChildAt(0);
        if (childAt == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.WelcomeStepView");
        }
        ((WelcomeStepView) childAt).setIcon(R.drawable.onfido_face_icon);
        View childAt2 = ((LinearLayout) _$_findCachedViewById(R.id.stepsContainer)).getChildAt(1);
        if (childAt2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.WelcomeStepView");
        }
        ((WelcomeStepView) childAt2).setIcon(R.drawable.onfido_glasses_icon);
    }
}
