package com.etsy.android.ui.convos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.convos.adapters.ConvoMessageAdapter;
import com.etsy.android.lib.convos.adapters.a.C0064a;
import com.etsy.android.lib.convos.d;
import com.etsy.android.lib.convos.e;
import com.etsy.android.lib.convos.h;
import com.etsy.android.lib.convos.j;
import com.etsy.android.lib.core.e.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.http.request.EtsyApiV3Request;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.i;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.z;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.Conversation;
import com.etsy.android.lib.models.ConversationImage;
import com.etsy.android.lib.models.ConversationMessage;
import com.etsy.android.lib.models.EmptyResult;
import com.etsy.android.lib.models.apiv3.TranslatedConversationMessage;
import com.etsy.android.lib.models.convo.ConversationThread;
import com.etsy.android.lib.models.convo.adapter.ManufacturerProjectSummaryItem;
import com.etsy.android.lib.models.convo.context.ManufacturerProject;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.ConversationRequest;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.apiv3.TranslatedConversationMessageRequest;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.p;
import com.etsy.android.lib.util.s;
import com.etsy.android.lib.util.t;
import com.etsy.android.ui.BOENavDrawerActivity;
import com.etsy.android.ui.EtsyCommonListFragment;
import com.etsy.android.uikit.BaseActivity;
import com.etsy.android.uikit.nav.ActivityNavigator.AnimationMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageAttachmentLayout;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ConvoThreadFragment extends EtsyCommonListFragment implements com.etsy.android.lib.convos.d.b, com.etsy.android.lib.core.b.a, com.etsy.android.lib.util.CameraHelper.b, com.etsy.android.uikit.view.ImageAttachmentLayout.a {
    private static final String ERROR_MESSAGE = "error_message";
    private static final String MESSAGE_FAILED = "com.etsy.android.convos.MESSAGE_FAILED";
    private static final String MESSAGE_SENT = "com.etsy.android.convos.MESSAGE_SENT";
    /* access modifiers changed from: private */
    public static final String TAG = f.a(ConvoThreadFragment.class);
    com.etsy.android.lib.util.b.a fileSupport;
    /* access modifiers changed from: private */
    public ConvoMessageAdapter mAdapter;
    private TextView mAttachImageButton;
    /* access modifiers changed from: private */
    public CameraHelper mCameraHelper;
    e mConovMessageCallbacks = new e() {
        public void a(EtsyId etsyId, boolean z) {
            if (z) {
                com.etsy.android.ui.nav.e.a(ConvoThreadFragment.this.getActivity()).a().f(com.etsy.android.lib.config.a.a().d().b(com.etsy.android.lib.config.b.aw));
                return;
            }
            com.etsy.android.ui.nav.e.a((FragmentActivity) ConvoThreadFragment.this.mActivity).a().c(etsyId);
        }

        public void a(int i, List<ConversationImage> list) {
            String urlFullxFull = ((ConversationImage) list.get(i)).getUrlFullxFull();
            if (ConvoThreadFragment.this.fileSupport.a(urlFullxFull)) {
                com.etsy.android.ui.nav.e.a((FragmentActivity) ConvoThreadFragment.this.mActivity).a().a(AnimationMode.ZOOM_IN_OUT).a(new ArrayList<>(list), i);
            } else {
                p.d(ConvoThreadFragment.this.mActivity, urlFullxFull);
            }
        }

        public boolean a() {
            return t.f();
        }

        public void a(final ConversationMessage conversationMessage, final C0064a aVar) {
            ConvoThreadFragment.this.getRequestQueue().a((Object) this, (g<Result>) com.etsy.android.lib.core.e.a((EtsyRequest<Result>) TranslatedConversationMessageRequest.getTranslatedConversationMessage(conversationMessage.getConversationId(), conversationMessage.getMessageOrder(), t.d())).a((c) new c() {
                public void a() {
                    ConvoThreadFragment.this.mAdapter.onTranslationLoading(aVar);
                }
            }).a((com.etsy.android.lib.core.f.c<Result>) new com.etsy.android.lib.core.f.c<TranslatedConversationMessage>() {
                public void a(List<TranslatedConversationMessage> list, int i, k<TranslatedConversationMessage> kVar) {
                    conversationMessage.setTranslatedMessage(((TranslatedConversationMessage) list.get(0)).getTranslatedConversationMessage());
                    ConvoThreadFragment.this.mAdapter.onTranslationSuccess(aVar, conversationMessage);
                }
            }).a((com.etsy.android.lib.core.f.b) new com.etsy.android.lib.core.f.b() {
                public void a(int i, String str, k kVar) {
                    ConvoThreadFragment.this.mAdapter.onTranslationError(aVar);
                }
            }).a());
        }
    };
    /* access modifiers changed from: private */
    public Conversation mConversation;
    private d mConvoHelper;
    private com.etsy.android.lib.convos.adapters.a mConvoMessageHelper;
    private Draft mDraft;
    private boolean mIsPhotoLoading = false;
    private boolean mIsSending;
    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if (ConvoThreadFragment.MESSAGE_SENT.equals(intent.getAction())) {
                ConvoThreadFragment.this.messageSent();
            } else if (ConvoThreadFragment.MESSAGE_FAILED.equals(intent.getAction())) {
                ConvoThreadFragment.this.messageError(intent.getStringExtra("error_message"));
            }
        }
    };
    private ProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public EditText mReplyEditText;
    /* access modifiers changed from: private */
    public ImageAttachmentLayout mReplyImagesLayout;
    private boolean mReplyShowing;
    private View mReplyView;
    private boolean mSaveDraft = true;
    private boolean mShouldUpdateReadState;
    private String mUserAvatarUrl;
    private EtsyId mUserId;

    /* renamed from: com.etsy.android.ui.convos.ConvoThreadFragment$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ConvoRemoveType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.etsy.android.ui.convos.ConvoThreadFragment$ConvoRemoveType[] r0 = com.etsy.android.ui.convos.ConvoThreadFragment.ConvoRemoveType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.etsy.android.ui.convos.ConvoThreadFragment$ConvoRemoveType r1 = com.etsy.android.ui.convos.ConvoThreadFragment.ConvoRemoveType.SPAM     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.etsy.android.ui.convos.ConvoThreadFragment$ConvoRemoveType r1 = com.etsy.android.ui.convos.ConvoThreadFragment.ConvoRemoveType.TRASH     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.convos.ConvoThreadFragment.AnonymousClass2.<clinit>():void");
        }
    }

    private enum ConvoRemoveType {
        SPAM,
        TRASH
    }

    private class a extends i<EmptyResult> {
        boolean a;
        boolean c;

        public a(boolean z) {
            this.a = !ConvoThreadFragment.this.mConversation.isRead();
            this.c = z;
        }

        /* access modifiers changed from: protected */
        public void b_() {
            a(this.a);
            if (this.c && (ConvoThreadFragment.this.getActivity() instanceof ConvoViewActivity)) {
                h.b(ConvoThreadFragment.this.getActivity());
                ((ConvoViewActivity) ConvoThreadFragment.this.getActivity()).popOrGoBack();
            }
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<EmptyResult> a() {
            return ConversationRequest.markConvoRead(ConvoThreadFragment.this.mConversation.getConversationId(), Boolean.valueOf(this.a));
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k kVar) {
            if (kVar == null || !kVar.a()) {
                if (ConvoThreadFragment.this.getActivity() != null) {
                    Toast.makeText(ConvoThreadFragment.this.getActivity(), ConvoThreadFragment.this.getActivity().getString(R.string.convo_error_read_state), 0).show();
                    String access$1800 = ConvoThreadFragment.TAG;
                    String str = "Error updating read state %s";
                    Object[] objArr = new Object[1];
                    objArr[0] = kVar != null ? kVar.c() : "";
                    f.d(access$1800, str, objArr);
                }
                a(!this.a);
            }
        }

        private void a(boolean z) {
            ConvoThreadFragment.this.mConversation.setIsRead(Boolean.valueOf(z));
            if (z) {
                com.etsy.android.ui.user.i.b((Context) ConvoThreadFragment.this.getActivity());
            }
            a(ConvoThreadFragment.this.mConversation.getConversationId(), z);
        }

        private void a(final long j, final boolean z) {
            z.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    com.etsy.android.lib.convos.contentprovider.b.a((Context) ConvoThreadFragment.this.mActivity, j, z);
                    return null;
                }
            }, new Void[0]);
        }
    }

    private class b extends i<EmptyResult> {
        ProgressDialog a;
        String c;
        String d;
        EtsyRequest<EmptyResult> e;

        protected b(ConvoRemoveType convoRemoveType) {
            if (AnonymousClass2.a[convoRemoveType.ordinal()] != 1) {
                this.c = ConvoThreadFragment.this.mActivity.getString(R.string.deleting_convo);
                this.d = ConvoThreadFragment.this.mActivity.getString(R.string.convo_error_delete);
                this.e = ConversationRequest.markConvoTrash(ConvoThreadFragment.this.mConversation.getConversationId(), Boolean.valueOf(true));
                return;
            }
            this.c = ConvoThreadFragment.this.mActivity.getString(R.string.marking_spam_convo);
            this.d = ConvoThreadFragment.this.mActivity.getString(R.string.convo_error_mark_spam);
            this.e = ConversationRequest.markConvoSpam(ConvoThreadFragment.this.mConversation.getConversationId(), Boolean.valueOf(true));
        }

        /* access modifiers changed from: protected */
        public void b_() {
            this.a = aj.b((Context) ConvoThreadFragment.this.mActivity, this.c);
            this.a.show();
        }

        /* access modifiers changed from: protected */
        public EtsyRequest<EmptyResult> a() {
            return this.e;
        }

        /* access modifiers changed from: protected */
        public void a_(k<EmptyResult> kVar) {
            com.etsy.android.lib.convos.contentprovider.b.a((Context) ConvoThreadFragment.this.mActivity, ConvoThreadFragment.this.mConversation.getConversationId());
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void a(k kVar) {
            if (this.a != null) {
                this.a.dismiss();
            }
            if (kVar.a()) {
                ConvoThreadFragment.this.mReplyImagesLayout.clear();
                d.c(ConvoThreadFragment.this.mActivity);
                if (ConvoThreadFragment.this.getActivity() instanceof ConvoViewActivity) {
                    h.b(ConvoThreadFragment.this.getActivity());
                    ((ConvoViewActivity) ConvoThreadFragment.this.getActivity()).popOrGoBack();
                    return;
                }
                return;
            }
            Toast.makeText(ConvoThreadFragment.this.mActivity, this.d, 0).show();
        }
    }

    @NonNull
    public String getTrackingName() {
        return "conversations_thread";
    }

    public void onRequestCrop(Uri uri, Uri uri2) {
    }

    public static ConvoThreadFragment getInstance() {
        return new ConvoThreadFragment();
    }

    public ConvoThreadFragment() {
        super(R.layout.fragment_conversations_messages);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BOENavDrawerActivity) {
            this.mActivity = (BOENavDrawerActivity) activity;
        }
        this.mUserAvatarUrl = SharedPreferencesUtility.f(activity);
        this.mUserId = SharedPreferencesUtility.c(getActivity());
        if (l.c((Activity) this.mActivity)) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(MESSAGE_FAILED);
            intentFilter.addAction(MESSAGE_SENT);
            LocalBroadcastManager.getInstance(activity).registerReceiver(this.mMessageReceiver, intentFilter);
        }
    }

    public void onDetach() {
        if (l.c((Activity) this.mActivity)) {
            LocalBroadcastManager.getInstance(this.mActivity).unregisterReceiver(this.mMessageReceiver);
        }
        super.onDetach();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        fetchConvo(bundle);
        this.mCameraHelper = new CameraHelper(getActivity().getApplicationContext(), bundle, this);
        setHasOptionsMenu(true);
        if (!l.c((Activity) this.mActivity)) {
            setRetainInstance(true);
        }
    }

    private void fetchConvo(Bundle bundle) {
        if (bundle != null) {
            this.mConversation = (Conversation) bundle.getSerializable("conversation");
            return;
        }
        this.mConversation = (Conversation) getArguments().getSerializable("conversation");
        this.mShouldUpdateReadState = getArguments().getBoolean("convo_change_read_state", true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (bundle != null) {
            fetchConvo(bundle);
        }
        if (this.mConversation.getConversationContext() instanceof ManufacturerProject) {
            createManufacturerProjectSummaryHeader(layoutInflater, (ManufacturerProject) this.mConversation.getConversationContext());
        }
        retrieveSavedDraft();
        createReplyFooter(layoutInflater);
        if (this.mIsSending || d.a((Context) this.mActivity, this.mConversation.getConversationId())) {
            showSending();
        }
        if (bundle == null && this.mShouldUpdateReadState && !this.mConversation.isRead()) {
            changeReadState(false);
        }
        if (l.a((Activity) getActivity())) {
            onCreateView.findViewById(R.id.convo_vertical_divider).setVisibility(0);
        }
        ((TextView) onCreateView.findViewById(R.id.conversation_subject)).setText(this.mConversation.getTitle());
        return onCreateView;
    }

    private com.etsy.android.lib.convos.adapters.a genHelper() {
        if (this.mConvoMessageHelper == null) {
            com.etsy.android.lib.convos.adapters.a aVar = new com.etsy.android.lib.convos.adapters.a(this.mActivity, getImageBatch(), this.mConovMessageCallbacks, this.mConversation.getOtherUser(), this.mUserId, this.mUserAvatarUrl);
            this.mConvoMessageHelper = aVar;
        }
        return this.mConvoMessageHelper;
    }

    private void createManufacturerProjectSummaryHeader(@NonNull LayoutInflater layoutInflater, @NonNull final ManufacturerProject manufacturerProject) {
        if (this.mListView.getHeaderViewsCount() <= 0) {
            View inflate = layoutInflater.inflate(R.layout.convo_manufacturer_project_summary, this.mListView, false);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.manufacturer_project_summary_image);
            if (manufacturerProject.getDefaultImage() != null) {
                String url170x135 = manufacturerProject.getDefaultImage().getUrl170x135();
                if (af.b(url170x135)) {
                    imageView.setVisibility(0);
                    this.mImageBatch.a(url170x135, imageView);
                } else {
                    imageView.setVisibility(4);
                }
            } else {
                imageView.setVisibility(4);
            }
            ((TextView) inflate.findViewById(R.id.manufacturer_project_summary_budget)).setText(ManufacturerProjectSummaryItem.formatBudget(manufacturerProject));
            ((TextView) inflate.findViewById(R.id.manufacturer_project_timeline_date)).setText(ManufacturerProjectSummaryItem.formatTimeline(getActivity(), manufacturerProject));
            ((TextView) inflate.findViewById(R.id.manufacturer_project_summary_quantity)).setText(manufacturerProject.getQuantity());
            final WeakReference weakReference = new WeakReference(this);
            inflate.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    ConvoThreadFragment convoThreadFragment = (ConvoThreadFragment) weakReference.get();
                    if (convoThreadFragment != null) {
                        convoThreadFragment.showManufacturerProject(manufacturerProject);
                    }
                }
            });
            this.mListView.addHeaderView(inflate);
        }
    }

    /* access modifiers changed from: private */
    public void showManufacturerProject(@NonNull ManufacturerProject manufacturerProject) {
        com.etsy.android.ui.nav.e.a(getActivity()).a().a(manufacturerProject);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mAdapter == null) {
            this.mAdapter = new ConvoMessageAdapter(this.mActivity, getImageBatch(), genHelper());
        } else {
            this.mAdapter.refreshActivity(getActivity());
        }
        View view = new View(this.mActivity);
        this.mListView.addFooterView(view);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.removeFooterView(view);
        if (this.mAdapter.getCount() == 0) {
            showLoadingView();
            getMessages();
        }
    }

    private void retrieveSavedDraft() {
        if (this.mActivity != null && this.mDraft == null && this.mConversation != null) {
            Draft b2 = d.b(this.mActivity);
            if (b2.getConvoId() != this.mConversation.getConversationId()) {
                com.etsy.android.lib.util.f.a(b2.getImages());
                d.c(this.mActivity);
                this.mDraft = null;
                return;
            }
            this.mDraft = b2;
        } else if (this.mDraft != null && TextUtils.isEmpty(this.mDraft.getMessage())) {
            this.mDraft.message(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
    }

    private Draft getDraft() {
        String str;
        if (this.mConversation == null || this.mReplyEditText == null) {
            return this.mDraft;
        }
        if (this.mReplyEditText.getText().toString().length() == 0) {
            str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        } else {
            str = this.mReplyEditText.getText().toString();
        }
        return new Draft().convoId(this.mConversation.getConversationId()).message(str).subject(this.mConversation.getTitle()).userName(this.mConversation.getOtherUser().getFormattedDisplayName(this.mContext)).saveCursorPosition(this.mReplyEditText.getSelectionStart(), this.mReplyEditText.getSelectionEnd()).images(this.mReplyImagesLayout.getImageFiles());
    }

    public void onResume() {
        super.onResume();
        setTitle();
    }

    public void onFragmentResume() {
        super.onFragmentResume();
        setTitle();
    }

    public void onStop() {
        if (!this.mReplyShowing || !this.mSaveDraft) {
            d.c(this.mActivity);
            this.mReplyImagesLayout.clear();
        }
        this.mProgressDialog = null;
        super.onStop();
    }

    public void onDestroy() {
        this.mCameraHelper.removeCallback();
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.mCameraHelper.saveState(bundle);
        if (this.mConversation != null) {
            bundle.putSerializable("conversation", this.mConversation);
        }
        if (!this.mReplyShowing || !this.mSaveDraft) {
            d.c(this.mActivity);
            if (this.mReplyImagesLayout != null) {
                this.mReplyImagesLayout.clear();
            }
        } else {
            this.mDraft = getDraft();
            d.a((Context) this.mActivity, this.mDraft);
        }
        super.onSaveInstanceState(bundle);
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.convo_thread_action_bar, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.menu_read_state);
        if (findItem != null) {
            if (this.mConversation.isRead()) {
                findItem.setTitle(this.mActivity.getString(R.string.convo_mark_as_unread));
            } else {
                findItem.setTitle(this.mActivity.getString(R.string.convo_mark_as_read));
            }
        }
        MenuItem findItem2 = menu.findItem(R.id.menu_reply);
        MenuItem findItem3 = menu.findItem(R.id.menu_send_reply);
        if (findItem2 != null && findItem3 != null) {
            findItem2.setVisible(!this.mReplyShowing);
            findItem3.setVisible(this.mReplyShowing);
            boolean replyEnabled = setReplyEnabled();
            findItem3.getIcon().setAlpha(replyEnabled ? 255 : 75);
            findItem3.setEnabled(replyEnabled);
        }
    }

    private boolean setReplyEnabled() {
        return !this.mIsSending && !this.mIsPhotoLoading && j.a(this.mReplyEditText);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                this.mSaveDraft = false;
                return true;
            case R.id.menu_delete /*2131362572*/:
                showDelete();
                return true;
            case R.id.menu_mark_spam /*2131362579*/:
                showMarkAsSpam();
                return true;
            case R.id.menu_read_state /*2131362582*/:
                changeReadState(true);
                return true;
            case R.id.menu_reply /*2131362583*/:
                showReply();
                this.mActivity.invalidateOptionsMenu();
                return true;
            case R.id.menu_send_reply /*2131362587*/:
                sendReply();
                this.mActivity.invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public boolean handleBackPressed() {
        this.mSaveDraft = false;
        return super.handleBackPressed();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    private void checkCanUploadMore() {
        if (this.mReplyImagesLayout.hasSpaceAvailable()) {
            this.mAttachImageButton.setEnabled(true);
            aj.a((View) this.mAttachImageButton, 1.0f);
            return;
        }
        this.mAttachImageButton.setEnabled(false);
        aj.a((View) this.mAttachImageButton, 0.6f);
    }

    /* access modifiers changed from: protected */
    public void onRetryClicked() {
        getMessages();
    }

    private void getMessages() {
        if (NetworkUtils.a().b()) {
            fetchMessagesFromApiV3();
        } else {
            showErrorView();
        }
    }

    /* access modifiers changed from: private */
    public void handleConvoMessages(@NonNull List<ConversationMessage> list) {
        Collections.reverse(list);
        this.mAdapter.clear();
        if (this.mListView != null && (this.mConversation.getConversationContext() instanceof ManufacturerProject)) {
            createManufacturerProjectSummaryHeader(LayoutInflater.from(this.mListView.getContext()), (ManufacturerProject) this.mConversation.getConversationContext());
        }
        this.mAdapter.addAll((Collection<? extends T>) list);
        if (this.mAdapter.getCount() > 0) {
            showListView();
        } else {
            showEmptyView();
        }
    }

    private void fetchMessagesFromApiV3() {
        com.etsy.android.lib.core.http.request.d.a a2 = com.etsy.android.lib.core.http.request.d.a.a((EtsyApiV3Request) new com.etsy.android.lib.core.http.request.EtsyApiV3Request.a(ConversationThread.class, String.format("/etsyapps/v3/bespoke/member/conversations/%s/thread", new Object[]{String.valueOf(this.mConversation.getConversationId())})).d());
        a2.a((C0065a<ResultType>) new com.etsy.android.lib.core.http.request.d.b<ConversationThread>() {
            public void a(@NonNull List<ConversationThread> list, int i, @NonNull com.etsy.android.lib.core.a.a<ConversationThread> aVar) {
                ConversationThread conversationThread = (ConversationThread) list.get(0);
                ConvoThreadFragment.this.mConversation.setContextTypeId(conversationThread.getConversation().getContextTypeId());
                ConvoThreadFragment.this.mConversation.setConversationContext(conversationThread.getConversation().getConversationContext());
                ConvoThreadFragment.this.handleConvoMessages(conversationThread.getMessages());
            }

            public void a(int i, String str, @NonNull com.etsy.android.lib.core.a.a<ConversationThread> aVar) {
                ConvoThreadFragment.this.showErrorView();
            }
        }, (Fragment) this);
        getRequestQueue().a((Object) getActivity(), a2.c());
    }

    /* access modifiers changed from: private */
    public void markConvoAsSpam() {
        Context activity = getActivity() == null ? this.mActivity : getActivity();
        if (NetworkUtils.a().b()) {
            getRequestQueue().a((Object) this, (g<Result>) new b<Result>(ConvoRemoveType.SPAM));
        } else {
            Toast.makeText(activity, R.string.no_internet, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void deleteConvo() {
        Context activity = getActivity() == null ? this.mActivity : getActivity();
        if (NetworkUtils.a().b()) {
            getRequestQueue().a((Object) this, (g<Result>) new b<Result>(ConvoRemoveType.TRASH));
        } else {
            Toast.makeText(activity, R.string.no_internet, 0).show();
        }
    }

    private void changeReadState(boolean z) {
        Context activity = getActivity() == null ? this.mActivity : getActivity();
        if (NetworkUtils.a().b()) {
            getRequestQueue().a((Object) this, (g<Result>) new a<Result>(z));
        } else {
            Toast.makeText(activity, R.string.no_internet, 0).show();
        }
    }

    public void onPreSendMessage() {
        showSending();
        d.a((Context) this.mActivity, true);
    }

    public void onMessageSent() {
        if (this.mActivity != null) {
            d.a((Context) this.mActivity, false);
            if (l.c((Activity) this.mActivity)) {
                Intent intent = new Intent();
                intent.setAction(MESSAGE_SENT);
                LocalBroadcastManager.getInstance(this.mActivity).sendBroadcastSync(intent);
                LocalBroadcastManager.getInstance(this.mActivity).sendBroadcast(new Intent(MESSAGE_SENT));
                return;
            }
            messageSent();
        }
    }

    public void onMessageError(String str) {
        if (l.c((Activity) this.mActivity)) {
            Intent intent = new Intent();
            intent.setAction(MESSAGE_FAILED);
            intent.putExtra("error_message", str);
            LocalBroadcastManager.getInstance(this.mActivity).sendBroadcast(intent);
            return;
        }
        messageError(str);
    }

    /* access modifiers changed from: private */
    public void messageSent() {
        if (this.mActivity != null) {
            getMessages();
            endSending();
            hideReply();
            d.c(this.mActivity);
            this.mDraft = null;
            this.mReplyImagesLayout.clear();
            this.mActivity.invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: private */
    public void messageError(String str) {
        if (this.mActivity != null) {
            BaseActivity baseActivity = this.mActivity;
            Resources resources = getResources();
            Object[] objArr = new Object[1];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            aj.a((Context) baseActivity, resources.getString(R.string.convo_send_error_message, objArr));
            endSending();
            d.a((Context) this.mActivity, false);
        }
    }

    private void showSending() {
        this.mProgressDialog = aj.b((Context) this.mActivity, this.mActivity.getString(R.string.convo_message_sending_v2));
        this.mProgressDialog.show();
        this.mReplyEditText.setEnabled(false);
        this.mAttachImageButton.setEnabled(false);
        this.mIsSending = true;
        this.mActivity.invalidateOptionsMenu();
    }

    private void endSending() {
        if (this.mReplyEditText != null) {
            this.mReplyEditText.setEnabled(true);
        }
        if (this.mAttachImageButton != null) {
            this.mAttachImageButton.setEnabled(true);
        }
        this.mIsSending = false;
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
        if (this.mActivity != null) {
            this.mActivity.invalidateOptionsMenu();
        }
    }

    public void onNoAvailableActivities() {
        aj.b((Context) this.mActivity, (int) R.string.camera_error_creating_file);
    }

    public Object onPreImageSave() {
        this.mAttachImageButton.setEnabled(false);
        blockSendButton(true);
        return this.mReplyImagesLayout.startLoading();
    }

    public void onImageSaveSuccess(Object obj, Bitmap bitmap, File file) {
        if (bitmap != null) {
            this.mReplyImagesLayout.addBitmap((com.etsy.android.uikit.view.ImageAttachmentLayout.b) obj, bitmap, file);
        } else {
            this.mReplyImagesLayout.onAbort((com.etsy.android.uikit.view.ImageAttachmentLayout.b) obj, file);
        }
        blockSendButton(false);
        checkCanUploadMore();
    }

    public void onImageSaveFail(Object obj, File file) {
        this.mReplyImagesLayout.onAbort((com.etsy.android.uikit.view.ImageAttachmentLayout.b) obj, file);
        blockSendButton(false);
        aj.b((Context) getActivity(), (int) R.string.camera_helper_image_load_error);
        checkCanUploadMore();
    }

    public void onPermissionGranted() {
        this.mCameraHelper.startImagePicker((Fragment) this, (int) R.string.choose_image);
    }

    private void blockSendButton(boolean z) {
        this.mIsPhotoLoading = z;
        this.mActivity.invalidateOptionsMenu();
    }

    public void onRemove() {
        checkCanUploadMore();
    }

    private void setTitle() {
        if (this.mActivity != null && this.mConversation != null && !l.c((Activity) getActivity())) {
            this.mActivity.setTitle(this.mConversation.getOtherUser().getDisplayName());
        }
    }

    private void createReplyFooter(LayoutInflater layoutInflater) {
        boolean z;
        String str;
        if (this.mReplyView == null) {
            z = true;
            View inflate = layoutInflater.inflate(R.layout.list_item_convo_reply, null);
            this.mReplyEditText = (EditText) inflate.findViewById(R.id.reply_edittext);
            this.mReplyEditText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    ConvoThreadFragment.this.mActivity.invalidateOptionsMenu();
                }
            });
            this.mAttachImageButton = (TextView) inflate.findViewById(R.id.reply_attach_image);
            this.mAttachImageButton.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    s.a((View) ConvoThreadFragment.this.mReplyEditText);
                    ConvoThreadFragment.this.mCameraHelper.startImagePicker((Fragment) ConvoThreadFragment.this, (int) R.string.choose_image);
                }
            });
            getImageBatch().b(this.mUserAvatarUrl, (ImageView) inflate.findViewById(R.id.convo_user_img), getResources().getDimensionPixelSize(R.dimen.conversation_avatar));
            this.mReplyImagesLayout = (ImageAttachmentLayout) inflate.findViewById(R.id.linear_reply_convo_images);
            this.mReplyImagesLayout.setImageAttachmentCallback(this);
            this.mReplyView = inflate;
        } else {
            z = false;
        }
        if (this.mDraft != null && !TextUtils.isEmpty(this.mDraft.getMessage())) {
            if (this.mDraft.getMessage().equals(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                str = "";
            } else {
                str = this.mDraft.getMessage();
            }
            this.mReplyEditText.setText(str);
            if (this.mReplyEditText.length() > 0 && this.mDraft.getCursorStartPosition() > -1 && this.mDraft.getCursorEndPosition() > -1) {
                this.mReplyEditText.setSelection(this.mDraft.getCursorStartPosition(), this.mDraft.getCursorEndPosition());
            }
            this.mReplyImagesLayout.refreshRatio();
            if (z) {
                this.mReplyImagesLayout.setImages(this.mDraft.getImages());
            }
            showReply();
        }
    }

    private void showReply() {
        this.mReplyShowing = true;
        this.mListView.addFooterView(this.mReplyView);
        this.mActivity.invalidateOptionsMenu();
        checkCanUploadMore();
        this.mReplyEditText.requestFocus();
        s.b((Context) this.mActivity);
        scrollToBottom();
    }

    private void scrollToBottom() {
        if (this.mListView != null && this.mAdapter != null) {
            final int count = this.mAdapter.getCount() + this.mListView.getHeaderViewsCount();
            this.mListView.post(new Runnable() {
                public void run() {
                    ConvoThreadFragment.this.mListView.smoothScrollToPosition(count);
                }
            });
        }
    }

    private void hideReply() {
        this.mReplyEditText.setText("");
        this.mReplyShowing = false;
        this.mListView.removeFooterView(this.mReplyView);
        s.a((View) this.mReplyEditText);
    }

    private void sendReply() {
        if (this.mConvoHelper == null) {
            this.mConvoHelper = new d(this.mActivity, this);
        }
        if (NetworkUtils.a().b()) {
            this.mConvoHelper.a(new Draft().convoId(this.mConversation.getConversationId()).message(this.mReplyEditText.getText().toString()).subject(this.mConversation.getTitle()).userName(this.mConversation.getOtherUser().getUsername()).images(this.mReplyImagesLayout.getImageFiles()));
        } else {
            aj.a((Context) this.mActivity, (int) R.string.network_unavailable);
        }
    }

    private void showDelete() {
        com.etsy.android.ui.nav.e.a(getActivity()).d().a((com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a) new com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a() {
            public void b() {
            }

            public void c() {
            }

            public void a() {
                ConvoThreadFragment.this.deleteConvo();
            }
        }, (int) R.string.convo_remove_warning_delete_button, (int) R.string.convo_remove_warning_cancel_button, 0, getString(R.string.convo_delete_warning_message));
    }

    private void showMarkAsSpam() {
        com.etsy.android.ui.nav.e.a(getActivity()).d().a((com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a) new com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a() {
            public void b() {
            }

            public void c() {
            }

            public void a() {
                ConvoThreadFragment.this.markConvoAsSpam();
            }
        }, (int) R.string.convo_remove_warning_mark_spam_button, (int) R.string.convo_remove_warning_cancel_button, 0, getString(R.string.convo_mark_spam_warning_message));
    }
}
