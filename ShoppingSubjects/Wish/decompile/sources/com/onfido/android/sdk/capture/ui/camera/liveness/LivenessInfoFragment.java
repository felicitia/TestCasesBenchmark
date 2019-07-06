package com.onfido.android.sdk.capture.ui.camera.liveness;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.ui.BaseFragment;
import com.onfido.android.sdk.capture.ui.NextActionListener;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoPresenter.View;
import com.onfido.android.sdk.capture.utils.ContextUtilsKt;
import com.onfido.android.sdk.capture.utils.OnfidoApiUtil;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.api.client.data.Applicant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

public final class LivenessInfoFragment extends BaseFragment implements View {
    public static final Companion Companion = new Companion(null);
    static final /* synthetic */ KProperty[] a = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LivenessInfoFragment.class), "videoUploadFailDialog", "getVideoUploadFailDialog()Landroid/app/AlertDialog;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LivenessInfoFragment.class), "videoNotFoundDialog", "getVideoNotFoundDialog()Landroid/app/AlertDialog;"))};
    /* access modifiers changed from: private */
    public NextActionListener b;
    private boolean c;
    private final Lazy d = LazyKt.lazy(new e(this));
    private final Lazy e = LazyKt.lazy(new d(this));
    private HashMap f;
    public LivenessInfoPresenter presenter;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ LivenessInfoFragment createInstance$default(Companion companion, boolean z, String str, OnfidoConfig onfidoConfig, Applicant applicant, LivenessUploadChallenge[] livenessUploadChallengeArr, int i, Object obj) {
            if ((i & 2) != 0) {
                str = "";
            }
            String str2 = str;
            if ((i & 4) != 0) {
                onfidoConfig = null;
            }
            OnfidoConfig onfidoConfig2 = onfidoConfig;
            if ((i & 8) != 0) {
                applicant = null;
            }
            Applicant applicant2 = applicant;
            if ((i & 16) != 0) {
                livenessUploadChallengeArr = new LivenessUploadChallenge[0];
            }
            return companion.createInstance(z, str2, onfidoConfig2, applicant2, livenessUploadChallengeArr);
        }

        public final LivenessInfoFragment createInstance(boolean z, String str, OnfidoConfig onfidoConfig, Applicant applicant, LivenessUploadChallenge[] livenessUploadChallengeArr) {
            Intrinsics.checkParameterIsNotNull(str, "videoPath");
            Intrinsics.checkParameterIsNotNull(livenessUploadChallengeArr, "livenessUploadChallenges");
            Bundle bundle = new Bundle();
            bundle.putBoolean("IS_INTRO", z);
            bundle.putString("VIDEO_PATH", str);
            bundle.putSerializable("ONFIDO_CONFIG", onfidoConfig);
            bundle.putSerializable("APPLICANT", applicant);
            bundle.putParcelableArray("onfido_liveness_challenges", (Parcelable[]) livenessUploadChallengeArr);
            LivenessInfoFragment livenessInfoFragment = new LivenessInfoFragment();
            livenessInfoFragment.setArguments(bundle);
            return livenessInfoFragment;
        }
    }

    static final class a implements OnClickListener {
        final /* synthetic */ android.view.View a;
        final /* synthetic */ LivenessInfoFragment b;

        a(android.view.View view, LivenessInfoFragment livenessInfoFragment) {
            this.b = livenessInfoFragment;
            this.a = view;
        }

        public final void onClick(android.view.View view) {
            NextActionListener access$getNextActionListener$p = this.b.b;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onNextClicked();
            }
        }
    }

    static final class b implements OnClickListener {
        final /* synthetic */ android.view.View a;
        final /* synthetic */ String b;
        final /* synthetic */ List c;
        final /* synthetic */ LivenessInfoFragment d;

        b(String str, List list, android.view.View view, LivenessInfoFragment livenessInfoFragment) {
            this.b = str;
            this.c = list;
            this.d = livenessInfoFragment;
            this.a = view;
        }

        public final void onClick(android.view.View view) {
            LivenessInfoPresenter presenter = this.d.getPresenter();
            String str = this.b;
            Intrinsics.checkExpressionValueIsNotNull(str, "videoPath");
            presenter.uploadLivenessVideo(str, this.c);
            this.d.getPresenter().trackUploadStarted();
        }
    }

    static final class c implements OnClickListener {
        final /* synthetic */ android.view.View a;
        final /* synthetic */ LivenessInfoFragment b;

        c(android.view.View view, LivenessInfoFragment livenessInfoFragment) {
            this.b = livenessInfoFragment;
            this.a = view;
        }

        public final void onClick(android.view.View view) {
            NextActionListener access$getNextActionListener$p = this.b.b;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onPreviousClicked();
            }
        }
    }

    static final class d extends Lambda implements Function0<AlertDialog> {
        final /* synthetic */ LivenessInfoFragment a;

        d(LivenessInfoFragment livenessInfoFragment) {
            this.a = livenessInfoFragment;
            super(0);
        }

        /* renamed from: a */
        public final AlertDialog invoke() {
            return new Builder(this.a.getContext()).setMessage(this.a.getString(R.string.onfido_liveness_video_not_found)).setPositiveButton(R.string.onfido_retake_video, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    NextActionListener access$getNextActionListener$p = this.a.a.b;
                    if (access$getNextActionListener$p != null) {
                        access$getNextActionListener$p.onPreviousClicked();
                    }
                }
            }).create();
        }
    }

    static final class e extends Lambda implements Function0<AlertDialog> {
        final /* synthetic */ LivenessInfoFragment a;

        e(LivenessInfoFragment livenessInfoFragment) {
            this.a = livenessInfoFragment;
            super(0);
        }

        /* renamed from: a */
        public final AlertDialog invoke() {
            return new Builder(this.a.getContext()).setMessage(this.a.getString(R.string.onfido_message_capture_error_video)).setPositiveButton(R.string.onfido_retry, AnonymousClass1.a).create();
        }
    }

    private final AlertDialog a() {
        Lazy lazy = this.d;
        KProperty kProperty = a[0];
        return (AlertDialog) lazy.getValue();
    }

    private final AlertDialog b() {
        Lazy lazy = this.e;
        KProperty kProperty = a[1];
        return (AlertDialog) lazy.getValue();
    }

    public void _$_clearFindViewByIdCache() {
        if (this.f != null) {
            this.f.clear();
        }
    }

    public android.view.View _$_findCachedViewById(int i) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        android.view.View view = (android.view.View) this.f.get(Integer.valueOf(i));
        if (view == null) {
            android.view.View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this.f.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public final LivenessInfoPresenter getPresenter() {
        LivenessInfoPresenter livenessInfoPresenter = this.presenter;
        if (livenessInfoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return livenessInfoPresenter;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.b = (NextActionListener) activity;
        } catch (ClassCastException unused) {
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            String simpleName = activity.getClass().getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("Activity ");
            sb.append(simpleName);
            sb.append(" must implement NextActionListener interface.");
            throw new IllegalStateException(sb.toString());
        }
    }

    public android.view.View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        OnClickListener onClickListener;
        Button button;
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        android.view.View inflate = layoutInflater.inflate(R.layout.onfido_fragment_liveness_intro, viewGroup, false);
        SdkController.getSdkComponent$default(SdkController.Companion.getInstance(), null, 1, null).inject(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.c = arguments.getBoolean("IS_INTRO");
            String string = arguments.getString("VIDEO_PATH");
            OnfidoConfig onfidoConfig = (OnfidoConfig) arguments.getSerializable("ONFIDO_CONFIG");
            Applicant applicant = (Applicant) arguments.getSerializable("APPLICANT");
            Object[] objArr = (Object[]) arguments.getParcelableArray("onfido_liveness_challenges");
            Collection arrayList = new ArrayList(objArr.length);
            for (Object obj : objArr) {
                Parcelable parcelable = (Parcelable) obj;
                if (parcelable == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.onfido.android.sdk.capture.ui.camera.liveness.LivenessUploadChallenge");
                }
                arrayList.add((LivenessUploadChallenge) parcelable);
            }
            List list = (List) arrayList;
            if (this.c) {
                LivenessInfoPresenter livenessInfoPresenter = this.presenter;
                if (livenessInfoPresenter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                LivenessInfoPresenter.init$default(livenessInfoPresenter, this, null, null, 6, null);
                ((TextView) inflate.findViewById(R.id.title)).setText(inflate.getContext().getString(R.string.onfido_liveness_intro_title));
                LayoutParams layoutParams = ((Button) inflate.findViewById(R.id.secondAction)).getLayoutParams();
                if (layoutParams == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
                }
                ((Button) inflate.findViewById(R.id.firstAction)).setLayoutParams((RelativeLayout.LayoutParams) layoutParams);
                ((Button) inflate.findViewById(R.id.firstAction)).setText(getString(R.string.onfido_continue));
                ViewExtensionsKt.toGone((Button) inflate.findViewById(R.id.secondAction));
                button = (Button) inflate.findViewById(R.id.firstAction);
                onClickListener = new a(inflate, this);
            } else {
                OnfidoAPI createOnfidoApiClient = OnfidoApiUtil.createOnfidoApiClient(inflate.getContext(), onfidoConfig);
                LivenessInfoPresenter livenessInfoPresenter2 = this.presenter;
                if (livenessInfoPresenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                livenessInfoPresenter2.init(this, createOnfidoApiClient, applicant);
                ((TextView) inflate.findViewById(R.id.title)).setText(getString(R.string.onfido_thank_you));
                ((TextView) inflate.findViewById(R.id.subtitle)).setText(getString(R.string.onfido_video_recorded));
                ViewExtensionsKt.toGone((TextView) inflate.findViewById(R.id.third_title));
                ((Button) inflate.findViewById(R.id.firstAction)).setText(getString(R.string.onfido_submit_video));
                ((Button) inflate.findViewById(R.id.firstAction)).setOnClickListener(new b(string, list, inflate, this));
                button = (Button) inflate.findViewById(R.id.secondAction);
                onClickListener = new c(inflate, this);
            }
            button.setOnClickListener(onClickListener);
        }
        return inflate;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onDetach() {
        super.onDetach();
        this.b = null;
    }

    public void onLivenessVideoNotFound() {
        b().show();
    }

    public void onStart() {
        super.onStart();
        Bundle arguments = getArguments();
        boolean z = arguments != null ? arguments.getBoolean("IS_INTRO") : false;
        LivenessInfoPresenter livenessInfoPresenter = this.presenter;
        if (livenessInfoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        livenessInfoPresenter.start(z, ContextUtilsKt.getScreenOrientation(context).isPortrait());
    }

    public void onStop() {
        super.onStop();
        Bundle arguments = getArguments();
        boolean z = arguments != null ? arguments.getBoolean("IS_INTRO") : false;
        LivenessInfoPresenter livenessInfoPresenter = this.presenter;
        if (livenessInfoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        livenessInfoPresenter.stop(z, ContextUtilsKt.getScreenOrientation(context).isPortrait());
    }

    public void onVideoUploadError() {
        a().show();
    }

    public void onVideoUploaded() {
        NextActionListener nextActionListener = this.b;
        if (nextActionListener != null) {
            nextActionListener.onNextClicked();
        }
    }

    public void setActions(int i, int i2) {
        int i3;
        int i4;
        switch (i) {
            case 1:
                i3 = R.string.onfido_liveness_intro_subtitle_1_action;
                break;
            case 2:
                i3 = R.string.onfido_liveness_intro_subtitle_2_actions;
                break;
            default:
                i3 = R.string.onfido_liveness_intro_subtitle_some_actions;
                break;
        }
        switch (i2) {
            case 1:
                i4 = R.string.onfido_liveness_intro_third_subtitle_1_action;
                break;
            case 2:
                i4 = R.string.onfido_liveness_intro_third_subtitle_2_actions;
                break;
            default:
                i4 = R.string.onfido_liveness_intro_third_subtitle_some_actions;
                break;
        }
        ((TextView) _$_findCachedViewById(R.id.subtitle)).setText(getString(i3));
        ((TextView) _$_findCachedViewById(R.id.third_title)).setText(getString(i4));
    }

    public void setLoading(boolean z) {
        if (z) {
            showLoadingDialog();
        } else {
            dismissLoadingDialog();
        }
    }
}
