package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.models.Conversation3;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.List;

/* compiled from: ConvosListView.kt */
public interface o {
    void addItemsToAdapter(List<? extends e> list);

    void launchConversation(Conversation3 conversation3);

    void navigateToUserWebView(EtsyId etsyId);

    void refreshConvos();

    void showEmptyView();

    void showErrorSnackbar(int i);

    void showErrorView();

    void showListView();

    void stopRefreshing();
}
