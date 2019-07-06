package com.etsy.android.ui.convos.convoredesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.StringRes;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.lib.models.datatypes.EtsyId;
import java.util.List;

/* compiled from: ConvoThreadView.kt */
public interface ai {
    void addDraftToAdapter(o oVar);

    void addImageAttachment(Bitmap bitmap, int i);

    void addItemsToAdapter(List<? extends o> list);

    void clearMessageInput();

    void enableSend(boolean z);

    Context getContext();

    void goBack();

    void hideLoadingDialog();

    void hideTitleBar();

    void navToCart();

    void navToReceipt(EtsyId etsyId);

    void notifyItemChanged(int i);

    void openDetailedImagesView(int i, List<ImageInfo> list);

    void openNonImageAttachmentView(String str);

    void refreshConvo();

    void reloadOptionsMenu();

    void removeImageAttachment(int i);

    void removeImageLoadingIndicator(int i);

    void setShouldRefresh();

    void setTitle(String str);

    void showCheckmarkTitleBar(String str, String str2);

    void showErrorSnackbar(@StringRes int i);

    void showGreenCircleTitleBar(String str, String str2);

    void showImageLoadingIndicator(int i);

    void showListView();

    void showLoadingDialog(@StringRes int i);

    void showTitleBar(String str);

    void showYellowCircleTitleBar(String str, String str2);

    void stopRefreshing();

    void updateImageAttachmentButton(boolean z);
}
