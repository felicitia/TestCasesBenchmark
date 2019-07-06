package com.onfido.android.sdk.capture.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.ui.options.WelcomeScreenOptions;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public final class WelcomeFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public NextActionListener a;
    private HashMap b;
    public WelcomePresenter presenter;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final WelcomeFragment createInstance(Context context, WelcomeScreenOptions welcomeScreenOptions) {
            StringBuilder sb;
            String str;
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(welcomeScreenOptions, "options");
            WelcomeFragment welcomeFragment = new WelcomeFragment();
            Bundle bundle = new Bundle();
            int i = 0;
            for (CaptureType captureType : welcomeScreenOptions.getFlowSteps()) {
                int i2 = i + 1;
                switch (captureType) {
                    case DOCUMENT:
                        str = context.getString(R.string.onfido_welcome_view_document_capture_title);
                        sb = new StringBuilder();
                        break;
                    case FACE:
                        str = context.getString(R.string.onfido_welcome_view_face_capture_title);
                        sb = new StringBuilder();
                        break;
                    case VIDEO:
                        str = context.getString(R.string.onfido_welcome_view_liveness_capture_title);
                        sb = new StringBuilder();
                        break;
                }
                sb.append("FLOW_STEPS_");
                sb.append(i);
                bundle.putString(sb.toString(), str);
                i = i2;
            }
            welcomeFragment.setArguments(bundle);
            return welcomeFragment;
        }
    }

    static final class a implements OnClickListener {
        final /* synthetic */ View a;
        final /* synthetic */ WelcomeFragment b;

        a(View view, WelcomeFragment welcomeFragment) {
            this.b = welcomeFragment;
            this.a = view;
        }

        public final void onClick(View view) {
            NextActionListener access$getNextActionListener$p = this.b.a;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onNextClicked();
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this.b != null) {
            this.b.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this.b == null) {
            this.b = new HashMap();
        }
        View view = (View) this.b.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this.b.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.a = (NextActionListener) activity;
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

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.onfido_fragment_bulleted_message, viewGroup, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            SdkController.getSdkComponent$default(SdkController.Companion.getInstance(), null, 1, null).inject(this);
            ((TextView) inflate.findViewById(R.id.title)).setText(getString(R.string.onfido_welcome_view_title));
            ((TextView) inflate.findViewById(R.id.subtitle)).setText(getString(R.string.onfido_welcome_view_time));
            Iterator it = new IntRange(0, arguments.size() - 1).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                StringBuilder sb = new StringBuilder();
                sb.append("FLOW_STEPS_");
                sb.append(nextInt);
                String string = arguments.getString(sb.toString());
                Context context = inflate.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                WelcomeStepView welcomeStepView = new WelcomeStepView(context);
                int i = nextInt + 1;
                Intrinsics.checkExpressionValueIsNotNull(string, "stepTitle");
                welcomeStepView.setStepInfo(i, string);
                if (nextInt == 0) {
                    welcomeStepView.hideSeparator(true);
                    if (arguments.size() == 1) {
                        welcomeStepView.setIcon(R.drawable.onfido_arrow_forward_white);
                    } else {
                        ((LinearLayout) inflate.findViewById(R.id.stepsContainer)).addView(welcomeStepView);
                    }
                } else if (nextInt != arguments.size() - 1) {
                    ((LinearLayout) inflate.findViewById(R.id.stepsContainer)).addView(welcomeStepView);
                }
                welcomeStepView.hideSeparator(false);
                ((LinearLayout) inflate.findViewById(R.id.stepsContainer)).addView(welcomeStepView);
            }
            ((Button) inflate.findViewById(R.id.next)).setOnClickListener(new a(inflate, this));
        }
        return inflate;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onDetach() {
        super.onDetach();
        this.a = null;
    }

    public void onStart() {
        super.onStart();
        WelcomePresenter welcomePresenter = this.presenter;
        if (welcomePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        welcomePresenter.trackWelcome(true);
    }

    public void onStop() {
        super.onStop();
        WelcomePresenter welcomePresenter = this.presenter;
        if (welcomePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        welcomePresenter.trackWelcome(false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Context context = getContext();
        if (context != null) {
            ((Button) _$_findCachedViewById(R.id.next)).setText(context.getString(R.string.onfido_start));
        }
    }
}
