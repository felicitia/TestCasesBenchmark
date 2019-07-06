package com.onfido.android.sdk.capture.ui;

import com.onfido.a.a;

public final class DocumentSelectionFragment_MembersInjector implements a<DocumentSelectionFragment> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<DocumentSelectionPresenter> b;

    public DocumentSelectionFragment_MembersInjector(com.onfido.b.a.a<DocumentSelectionPresenter> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<DocumentSelectionFragment> create(com.onfido.b.a.a<DocumentSelectionPresenter> aVar) {
        return new DocumentSelectionFragment_MembersInjector(aVar);
    }

    public void injectMembers(DocumentSelectionFragment documentSelectionFragment) {
        if (documentSelectionFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        documentSelectionFragment.presenter = (DocumentSelectionPresenter) this.b.get();
    }
}
