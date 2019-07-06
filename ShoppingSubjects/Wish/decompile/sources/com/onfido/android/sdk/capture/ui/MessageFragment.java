package com.onfido.android.sdk.capture.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.onfido.android.sdk.capture.R;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public class MessageFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final String b = "title";
    /* access modifiers changed from: private */
    public static final String c = "message";
    /* access modifiers changed from: private */
    public NextActionListener a;
    private HashMap d;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String a() {
            return MessageFragment.b;
        }

        /* access modifiers changed from: private */
        public final String b() {
            return MessageFragment.c;
        }

        public final MessageFragment createInstance(String str, String str2) {
            Intrinsics.checkParameterIsNotNull(str, "title");
            Intrinsics.checkParameterIsNotNull(str2, "subtitle");
            MessageFragment messageFragment = new MessageFragment();
            messageFragment.setInfo(str, str2);
            return messageFragment;
        }
    }

    static final class a implements OnClickListener {
        final /* synthetic */ View a;
        final /* synthetic */ MessageFragment b;

        a(View view, MessageFragment messageFragment) {
            this.b = messageFragment;
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
        if (this.d != null) {
            this.d.clear();
        }
    }

    public void onAttach(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        super.onAttach(activity);
        try {
            this.a = (NextActionListener) activity;
        } catch (ClassCastException unused) {
            String simpleName = activity.getClass().getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("Activity \"");
            sb.append(simpleName);
            sb.append("\" must implement NextActionListener interface.");
            throw new IllegalStateException(sb.toString());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.onfido_fragment_message, viewGroup, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(Companion.a());
            String string2 = arguments.getString(Companion.b());
            ((TextView) inflate.findViewById(R.id.titleTv)).setText(string);
            ((TextView) inflate.findViewById(R.id.subtitleTv)).setText(string2);
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

    public void setInfo(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "title");
        Intrinsics.checkParameterIsNotNull(str2, "subtitle");
        Bundle bundle = new Bundle();
        bundle.putString(Companion.a(), str);
        bundle.putString(Companion.b(), str2);
        setArguments(bundle);
    }
}
