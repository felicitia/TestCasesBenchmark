package com.onfido.android.sdk.capture.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.common.SdkController;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public final class DocumentSelectionFragment extends BaseFragment {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public NextActionListener a;
    private HashMap b;
    public DocumentSelectionPresenter presenter;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DocumentSelectionFragment createInstance() {
            return new DocumentSelectionFragment();
        }
    }

    static final class a implements OnClickListener {
        final /* synthetic */ DocumentSelectionFragment a;

        a(DocumentSelectionFragment documentSelectionFragment) {
            this.a = documentSelectionFragment;
        }

        public final void onClick(View view) {
            NextActionListener access$getNextActionListener$p = this.a.a;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onDocumentTypeSelected(DocumentType.PASSPORT);
            }
        }
    }

    static final class b implements OnClickListener {
        final /* synthetic */ DocumentSelectionFragment a;

        b(DocumentSelectionFragment documentSelectionFragment) {
            this.a = documentSelectionFragment;
        }

        public final void onClick(View view) {
            NextActionListener access$getNextActionListener$p = this.a.a;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onDocumentTypeSelected(DocumentType.DRIVING_LICENCE);
            }
        }
    }

    static final class c implements OnClickListener {
        final /* synthetic */ DocumentSelectionFragment a;

        c(DocumentSelectionFragment documentSelectionFragment) {
            this.a = documentSelectionFragment;
        }

        public final void onClick(View view) {
            NextActionListener access$getNextActionListener$p = this.a.a;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onDocumentTypeSelected(DocumentType.NATIONAL_IDENTITY_CARD);
            }
        }
    }

    static final class d implements OnClickListener {
        final /* synthetic */ DocumentSelectionFragment a;

        d(DocumentSelectionFragment documentSelectionFragment) {
            this.a = documentSelectionFragment;
        }

        public final void onClick(View view) {
            NextActionListener access$getNextActionListener$p = this.a.a;
            if (access$getNextActionListener$p != null) {
                access$getNextActionListener$p.onDocumentTypeSelected(DocumentType.RESIDENCE_PERMIT);
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this.b != null) {
            this.b.clear();
        }
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
        View inflate = layoutInflater.inflate(R.layout.onfido_fragment_document_selection, viewGroup, false);
        SdkController.getSdkComponent$default(SdkController.Companion.getInstance(), null, 1, null).inject(this);
        ((RelativeLayout) inflate.findViewById(R.id.passportContainer)).setOnClickListener(new a(this));
        ((RelativeLayout) inflate.findViewById(R.id.drivingLicenceContainer)).setOnClickListener(new b(this));
        ((RelativeLayout) inflate.findViewById(R.id.identityCardContainer)).setOnClickListener(new c(this));
        ((RelativeLayout) inflate.findViewById(R.id.residencePermitContainer)).setOnClickListener(new d(this));
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
        DocumentSelectionPresenter documentSelectionPresenter = this.presenter;
        if (documentSelectionPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        documentSelectionPresenter.trackDocumentSelection(true);
    }

    public void onStop() {
        super.onStop();
        DocumentSelectionPresenter documentSelectionPresenter = this.presenter;
        if (documentSelectionPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        documentSelectionPresenter.trackDocumentSelection(false);
    }
}
