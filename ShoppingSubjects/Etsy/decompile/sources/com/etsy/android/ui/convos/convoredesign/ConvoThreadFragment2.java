package com.etsy.android.ui.convos.convoredesign;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.extensions.j;
import com.etsy.android.lib.convos.adapters.ConvoThreadAdapter;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.models.ImageInfo;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.convos.convoredesign.af.d;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.util.e;
import com.jakewharton.rxbinding2.widget.h;
import com.jakewharton.rxbinding2.widget.n;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.o;
import kotlin.jvm.internal.p;

/* compiled from: ConvoThreadFragment2.kt */
public final class ConvoThreadFragment2 extends BaseRecyclerViewListFragment<o> implements com.etsy.android.lib.core.b.a, ai {
    private HashMap _$_findViewCache;
    public ae imageHelper;
    public l logCat;
    private Disposable messageBoxDisposable;
    public af presenter;
    private ProgressDialog progressDialog;

    /* compiled from: ConvoThreadFragment2.kt */
    static final class a<T> implements Consumer<n> {
        final /* synthetic */ ConvoThreadFragment2 a;

        a(ConvoThreadFragment2 convoThreadFragment2) {
            this.a = convoThreadFragment2;
        }

        /* renamed from: a */
        public final void accept(n nVar) {
            Editable editable = nVar.editable();
            String obj = editable != null ? editable.toString() : null;
            if (obj != null) {
                af presenter = this.a.getPresenter();
                EditText editText = (EditText) this.a._$_findCachedViewById(com.etsy.android.e.a.message);
                p.a((Object) editText, "message");
                int selectionStart = editText.getSelectionStart();
                EditText editText2 = (EditText) this.a._$_findCachedViewById(com.etsy.android.e.a.message);
                p.a((Object) editText2, "message");
                presenter.a(obj, selectionStart, editText2.getSelectionEnd());
            }
        }
    }

    /* compiled from: ConvoThreadFragment2.kt */
    public static final class b extends com.etsy.android.ui.dialog.EtsyTrioDialogFragment.b {
        final /* synthetic */ ConvoThreadFragment2 a;

        public void b() {
        }

        public void c() {
        }

        b(ConvoThreadFragment2 convoThreadFragment2) {
            this.a = convoThreadFragment2;
        }

        public void a() {
            this.a.getPresenter().k();
        }
    }

    /* compiled from: ConvoThreadFragment2.kt */
    public static final class c extends com.etsy.android.ui.dialog.EtsyTrioDialogFragment.b {
        final /* synthetic */ ConvoThreadFragment2 a;

        public void b() {
        }

        public void c() {
        }

        c(ConvoThreadFragment2 convoThreadFragment2) {
            this.a = convoThreadFragment2;
        }

        public void a() {
            this.a.getPresenter().l();
        }
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public int getLayoutId() {
        return R.layout.fragment_convo_thread;
    }

    public final ae getImageHelper() {
        ae aeVar = this.imageHelper;
        if (aeVar == null) {
            p.b("imageHelper");
        }
        return aeVar;
    }

    public final void setImageHelper(ae aeVar) {
        p.b(aeVar, "<set-?>");
        this.imageHelper = aeVar;
    }

    public final af getPresenter() {
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        return afVar;
    }

    public final void setPresenter(af afVar) {
        p.b(afVar, "<set-?>");
        this.presenter = afVar;
    }

    public final l getLogCat() {
        l lVar = this.logCat;
        if (lVar == null) {
            p.b("logCat");
        }
        return lVar;
    }

    public final void setLogCat(l lVar) {
        p.b(lVar, "<set-?>");
        this.logCat = lVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        if (!afVar.e()) {
            loadContent();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentActivity activity = getActivity();
        p.a((Object) activity, "activity");
        com.etsy.android.lib.core.img.c imageBatch = getImageBatch();
        p.a((Object) imageBatch, "imageBatch");
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        com.etsy.android.ui.convos.convoredesign.af.b a2 = afVar.a();
        af afVar2 = this.presenter;
        if (afVar2 == null) {
            p.b("presenter");
        }
        d b2 = afVar2.b();
        af afVar3 = this.presenter;
        if (afVar3 == null) {
            p.b("presenter");
        }
        ConvoThreadAdapter convoThreadAdapter = new ConvoThreadAdapter(activity, imageBatch, a2, b2, afVar3.c());
        this.mAdapter = convoThreadAdapter;
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        p.a((Object) onCreateView, "view");
        ImageView imageView = (ImageView) onCreateView.findViewById(com.etsy.android.e.a.camera);
        p.a((Object) imageView, "view.camera");
        j.a(imageView, new ConvoThreadFragment2$onCreateView$1(this));
        FloatingActionButton floatingActionButton = (FloatingActionButton) onCreateView.findViewById(com.etsy.android.e.a.send_message);
        p.a((Object) floatingActionButton, "view.send_message");
        j.a(floatingActionButton, new ConvoThreadFragment2$onCreateView$2(this));
        FloatingActionButton floatingActionButton2 = (FloatingActionButton) onCreateView.findViewById(com.etsy.android.e.a.send_message);
        p.a((Object) floatingActionButton2, "view.send_message");
        floatingActionButton2.setEnabled(false);
        this.messageBoxDisposable = h.d((EditText) onCreateView.findViewById(com.etsy.android.e.a.message)).b().a((Consumer<? super T>) new a<Object>(this));
        ((ComposeImageLayout) onCreateView.findViewById(com.etsy.android.e.a.image_attachment_layout)).setRemoveListener(new ConvoThreadFragment2$onCreateView$4(this));
        if (layoutInflater != null) {
            layoutInflater.inflate(R.layout.convo_title_bar, (ViewGroup) getActivity().findViewById(R.id.app_bar_layout));
        }
        return onCreateView;
    }

    /* access modifiers changed from: protected */
    public LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity(), 1, true);
    }

    public void onDestroyView() {
        super.onDestroyView();
        Disposable disposable = this.messageBoxDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        _$_clearFindViewByIdCache();
    }

    public void onSaveInstanceState(Bundle bundle) {
        p.b(bundle, "outState");
        super.onSaveInstanceState(bundle);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        p.b(menu, "menu");
        p.b(menuInflater, "inflater");
        menuInflater.inflate(R.menu.convo_thread_options, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        p.b(menu, "menu");
        MenuItem findItem = menu.findItem(R.id.menu_read_state);
        p.a((Object) findItem, "readStateItem");
        FragmentActivity activity = getActivity();
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        findItem.setTitle(activity.getString(afVar.d()));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        p.b(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            return true;
        }
        if (itemId == R.id.menu_delete) {
            showMarkAsTrashConfirmation();
            return true;
        } else if (itemId == R.id.menu_mark_spam) {
            showMarkAsSpamConfirmation();
            return true;
        } else if (itemId != R.id.menu_read_state) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            af afVar = this.presenter;
            if (afVar == null) {
                p.b("presenter");
            }
            afVar.a(true);
            return true;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        ae aeVar = this.imageHelper;
        if (aeVar == null) {
            p.b("imageHelper");
        }
        aeVar.a(i, i2, intent);
    }

    public void onStart() {
        super.onStart();
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        afVar.n();
    }

    public void onStop() {
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        afVar.o();
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        afVar.f();
        ae aeVar = this.imageHelper;
        if (aeVar == null) {
            p.b("imageHelper");
        }
        aeVar.c();
    }

    public void onLoadContent() {
        BaseRecyclerViewAdapter baseRecyclerViewAdapter = this.mAdapter;
        p.a((Object) baseRecyclerViewAdapter, "mAdapter");
        if (baseRecyclerViewAdapter.getDataItemCount() > 0 && isRefreshing()) {
            this.mAdapter.clear();
        }
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        afVar.g();
    }

    public void showErrorSnackbar(int i) {
        aj.a(getView(), i);
    }

    public void clearMessageInput() {
        af afVar = this.presenter;
        if (afVar == null) {
            p.b("presenter");
        }
        afVar.j();
        EditText editText = (EditText) _$_findCachedViewById(com.etsy.android.e.a.message);
        p.a((Object) editText, "message");
        editText.setText(null);
    }

    public void showSendMessageError(String str) {
        p.b(str, "message");
        aj.a(getView(), (CharSequence) getResources().getString(R.string.convo_send_error_message, new Object[]{str}));
    }

    public void addItemsToAdapter(List<? extends o> list) {
        p.b(list, ResponseConstants.ITEMS);
        this.mAdapter.addItems(o.d(list));
    }

    public void addDraftToAdapter(o oVar) {
        p.b(oVar, "item");
        this.mAdapter.addItemAtPosition(0, oVar);
        this.mRecyclerView.scrollToPosition(0);
    }

    public void notifyItemChanged(int i) {
        this.mAdapter.notifyItemChanged(i);
    }

    public void setTitle(String str) {
        p.b(str, "title");
        FragmentActivity activity = getActivity();
        p.a((Object) activity, "activity");
        activity.setTitle(str);
    }

    public void reloadOptionsMenu() {
        getActivity().invalidateOptionsMenu();
    }

    public void stopRefreshing() {
        SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
        p.a((Object) swipeRefreshLayout, "mSwipeRefreshLayout");
        swipeRefreshLayout.setRefreshing(false);
        setLoading(false);
        setRefreshing(false);
    }

    public void updateReply(String str) {
        p.b(str, "reply");
        ((EditText) _$_findCachedViewById(com.etsy.android.e.a.message)).setText(str);
    }

    public void enableSend(boolean z) {
        FloatingActionButton floatingActionButton = (FloatingActionButton) _$_findCachedViewById(com.etsy.android.e.a.send_message);
        p.a((Object) floatingActionButton, ConvoThreadAdapter.SEND_MESSAGE);
        floatingActionButton.setEnabled(z);
    }

    public void goBack() {
        FragmentActivity activity = getActivity();
        p.a((Object) activity, "activity");
        e.b(activity.getSupportFragmentManager(), com.etsy.android.uikit.nav.b.b(getActivity()));
    }

    public void setShouldRefresh() {
        getActivity().setResult(-1);
    }

    public void addImageAttachment(Bitmap bitmap, int i) {
        p.b(bitmap, "bitmap");
        ((ComposeImageLayout) _$_findCachedViewById(com.etsy.android.e.a.image_attachment_layout)).addImage(bitmap, i);
    }

    public void removeImageAttachment(int i) {
        ((ComposeImageLayout) _$_findCachedViewById(com.etsy.android.e.a.image_attachment_layout)).removeImage(i);
    }

    public void showImageLoadingIndicator(int i) {
        ((ComposeImageLayout) _$_findCachedViewById(com.etsy.android.e.a.image_attachment_layout)).addLoading(i);
    }

    public void removeImageLoadingIndicator(int i) {
        ((ComposeImageLayout) _$_findCachedViewById(com.etsy.android.e.a.image_attachment_layout)).removeLoading(i);
    }

    private final void showMarkAsSpamConfirmation() {
        com.etsy.android.ui.nav.e.a(getActivity()).d().a((com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a) new b(this), (int) R.string.convo_remove_warning_mark_spam_button, (int) R.string.convo_remove_warning_cancel_button, 0, getString(R.string.convo_mark_spam_warning_message));
    }

    public void showLoadingDialog(int i) {
        this.progressDialog = aj.b((Context) getActivity(), getString(i));
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null) {
            progressDialog2.show();
        }
    }

    public void hideLoadingDialog() {
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null) {
            progressDialog2.dismiss();
        }
    }

    private final void showMarkAsTrashConfirmation() {
        com.etsy.android.ui.nav.e.a(getActivity()).d().a((com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a) new c(this), (int) R.string.convo_remove_warning_delete_button, (int) R.string.convo_remove_warning_cancel_button, 0, getString(R.string.convo_delete_warning_message));
    }

    public void updateImageAttachmentButton(boolean z) {
        if (z) {
            ImageView imageView = (ImageView) _$_findCachedViewById(com.etsy.android.e.a.camera);
            p.a((Object) imageView, "camera");
            imageView.setEnabled(true);
            ImageView imageView2 = (ImageView) _$_findCachedViewById(com.etsy.android.e.a.camera);
            p.a((Object) imageView2, "camera");
            imageView2.setAlpha(1.0f);
            return;
        }
        ImageView imageView3 = (ImageView) _$_findCachedViewById(com.etsy.android.e.a.camera);
        p.a((Object) imageView3, "camera");
        imageView3.setEnabled(false);
        ImageView imageView4 = (ImageView) _$_findCachedViewById(com.etsy.android.e.a.camera);
        p.a((Object) imageView4, "camera");
        imageView4.setAlpha(0.6f);
    }

    public void hideTitleBar() {
        FragmentActivity activity = getActivity();
        j.b(activity != null ? activity.findViewById(R.id.convo_title_bar) : null);
    }

    public void showYellowCircleTitleBar(String str, String str2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            j.a(activity.findViewById(R.id.convo_title_bar));
            TextView textView = (TextView) activity.findViewById(R.id.state_indicator_text);
            if (textView != null) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) activity.findViewById(R.id.state_button);
            if (textView2 != null) {
                textView2.setText(str2);
                j.a(textView2, new ConvoThreadFragment2$showYellowCircleTitleBar$$inlined$apply$lambda$1(this, str, str2));
            }
            j.b(activity.findViewById(R.id.custom_order_check));
            j.a(activity.findViewById(R.id.custom_order_circle));
            View findViewById = activity.findViewById(R.id.state_indicator_circle);
            if (findViewById != null) {
                findViewById.setBackgroundResource(R.drawable.creamsicle_circle);
            }
        }
    }

    public void showGreenCircleTitleBar(String str, String str2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            j.a(activity.findViewById(R.id.convo_title_bar));
            TextView textView = (TextView) activity.findViewById(R.id.state_indicator_text);
            if (textView != null) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) activity.findViewById(R.id.state_button);
            if (textView2 != null) {
                textView2.setText(str2);
                j.a(textView2, new ConvoThreadFragment2$showGreenCircleTitleBar$$inlined$apply$lambda$1(this, str, str2));
            }
            j.b(activity.findViewById(R.id.custom_order_check));
            j.a(activity.findViewById(R.id.custom_order_circle));
            View findViewById = activity.findViewById(R.id.state_indicator_circle);
            if (findViewById != null) {
                findViewById.setBackgroundResource(R.drawable.green_circle);
            }
        }
    }

    public void showCheckmarkTitleBar(String str, String str2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            j.a(activity.findViewById(R.id.convo_title_bar));
            TextView textView = (TextView) activity.findViewById(R.id.state_indicator_text);
            if (textView != null) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) activity.findViewById(R.id.state_button);
            if (textView2 != null) {
                textView2.setText(str2);
                j.a(textView2, new ConvoThreadFragment2$showCheckmarkTitleBar$$inlined$apply$lambda$1(this, str, str2));
            }
            j.a(activity.findViewById(R.id.custom_order_check));
            j.b(activity.findViewById(R.id.custom_order_circle));
        }
    }

    public void showTitleBar(String str) {
        p.b(str, "title");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            j.a(activity.findViewById(R.id.convo_title_bar));
            j.a(activity.findViewById(R.id.title_header));
            j.b(activity.findViewById(R.id.custom_order_group));
            TextView textView = (TextView) activity.findViewById(R.id.title_header);
            if (textView != null) {
                textView.setText(str);
            }
        }
    }

    public void openDetailedImagesView(int i, List<ImageInfo> list) {
        p.b(list, "images");
        ArrayList arrayList = new ArrayList();
        Iterable<ImageInfo> iterable = list;
        Collection arrayList2 = new ArrayList(o.a(iterable, 10));
        for (ImageInfo imageUrl : iterable) {
            arrayList2.add(imageUrl.toImageUrl());
        }
        arrayList.addAll((List) arrayList2);
        com.etsy.android.ui.nav.e.a(getActivity()).a().a(AnimationMode.ZOOM_IN_OUT).a(arrayList, i);
    }

    public void openNonImageAttachmentView(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            l lVar = this.logCat;
            if (lVar == null) {
                p.b("logCat");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No application found to open url: ");
            sb.append(str);
            lVar.b(sb.toString(), e);
        }
    }

    public void navToCart() {
        com.etsy.android.ui.nav.e.a((Fragment) this).a().r();
    }

    public void navToReceipt(EtsyId etsyId) {
        p.b(etsyId, "receiptId");
        com.etsy.android.ui.nav.e.a((Fragment) this).a().d(etsyId);
    }

    public void refreshConvo() {
        setRefreshing(true);
        loadContent();
    }
}
