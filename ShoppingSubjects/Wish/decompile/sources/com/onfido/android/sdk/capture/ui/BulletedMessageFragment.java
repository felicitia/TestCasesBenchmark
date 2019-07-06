package com.onfido.android.sdk.capture.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.utils.ViewExtensionsKt;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public class BulletedMessageFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public NextActionListener a;
    private HashMap b;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static final class a implements OnClickListener {
        final /* synthetic */ View a;
        final /* synthetic */ BulletedMessageFragment b;

        a(View view, BulletedMessageFragment bulletedMessageFragment) {
            this.b = bulletedMessageFragment;
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
            String string = arguments.getString("TITLE");
            String string2 = arguments.getString("SUBTITLE");
            ((TextView) inflate.findViewById(R.id.title)).setText(string);
            ((TextView) inflate.findViewById(R.id.subtitle)).setText(string2);
            ViewExtensionsKt.toGone((ImageView) inflate.findViewById(R.id.timerIcon));
            Iterator it = new IntRange(0, (arguments.size() - 1) - 2).iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                StringBuilder sb = new StringBuilder();
                sb.append("BULLET_");
                sb.append(nextInt);
                String string3 = arguments.getString(sb.toString());
                Context context = inflate.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                WelcomeStepView welcomeStepView = new WelcomeStepView(context);
                welcomeStepView.setPadding(0, 0, 0, welcomeStepView.getContext().getResources().getDimensionPixelSize(R.dimen.onfido_bullets_margin_vertical));
                int i = nextInt + 1;
                Intrinsics.checkExpressionValueIsNotNull(string3, "stepTitle");
                welcomeStepView.setStepInfo(i, string3);
                welcomeStepView.setIcon(R.drawable.onfido_arrow_forward_white);
                welcomeStepView.hideSeparators();
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

    public void setInfo(String str, String str2, List<String> list) {
        Intrinsics.checkParameterIsNotNull(str, StrongAuth.AUTH_TITLE);
        Intrinsics.checkParameterIsNotNull(str2, "subtitle");
        Intrinsics.checkParameterIsNotNull(list, "bullets");
        Bundle bundle = new Bundle();
        int i = 0;
        for (String str3 : list) {
            int i2 = i + 1;
            StringBuilder sb = new StringBuilder();
            sb.append("BULLET_");
            sb.append(i);
            bundle.putString(sb.toString(), str3);
            i = i2;
        }
        bundle.putString("TITLE", str);
        bundle.putString("SUBTITLE", str2);
        setArguments(bundle);
    }
}
