package com.etsy.android.ui.convos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.convos.d;
import com.etsy.android.lib.convos.d.b;
import com.etsy.android.lib.convos.k;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.apiv3.User;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.l;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.uikit.adapter.RecentContactsAdapter;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.e;
import com.etsy.android.uikit.util.j;
import com.etsy.android.uikit.view.ContactsAutoComplete;
import com.etsy.android.uikit.view.ImageAttachmentLayout;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.File;

public class ConvoComposeFragment extends EtsyFragment implements b, a, CameraHelper.b, ImageAttachmentLayout.a {
    i convoRepository;
    private TextView mCameraButton;
    /* access modifiers changed from: private */
    public CameraHelper mCameraHelper;
    private TrackingOnClickListener mClickListener;
    private int mConvoId;
    private Draft mDraft;
    private ImageAttachmentLayout.b mHolder;
    private ImageAttachmentLayout mImageAttachment;
    private boolean mImageIsAttaching;
    private boolean mIsReply = false;
    private boolean mIsTablet;
    private k mListener;
    private EditText mMessageEditText;
    private IDialogFragment mParentDialog;
    private ProgressDialog mProgressDialog;
    private View mSendButton;
    /* access modifiers changed from: private */
    public EditText mSubjectEditText;
    private TextWatcher mTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ConvoComposeFragment.this.checkSendButton();
        }
    };
    private AutoCompleteTextView mUserNameEditText;
    /* access modifiers changed from: private */
    public View mView;
    @Nullable
    private Disposable recipientLookupDisposable;
    com.etsy.android.lib.f.a schedulers;
    private int signInCounter = 0;

    public void onRequestCrop(Uri uri, Uri uri2) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCameraHelper = new CameraHelper(getActivity().getApplicationContext(), bundle, this);
        this.mIsTablet = l.c((Activity) getActivity());
        if (!this.mIsTablet) {
            setHasOptionsMenu(true);
        }
        if (bundle != null) {
            this.mDraft = d.b(this.mActivity);
            this.signInCounter = bundle.getInt("signInCounter", 0);
        }
        if (getActivity() instanceof k) {
            this.mListener = (k) getActivity();
        }
        this.mClickListener = new TrackingOnClickListener() {
            public void onViewClick(View view) {
                int id = view.getId();
                if (id == R.id.btn_send) {
                    ConvoComposeFragment.this.send();
                } else if (id != R.id.btn_x) {
                    if (id == R.id.button_image) {
                        ConvoComposeFragment.this.hideKeyboard();
                        ConvoComposeFragment.this.mCameraHelper.startImagePicker((Fragment) ConvoComposeFragment.this, (int) R.string.choose_image);
                    }
                } else if (ConvoComposeFragment.this.getActivity() != null) {
                    e.b(ConvoComposeFragment.this.getFragmentManager(), com.etsy.android.ui.nav.e.a(ConvoComposeFragment.this.getActivity()));
                }
            }
        };
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(R.layout.fragment_conversations_compose, null);
        this.mCameraButton = (TextView) this.mView.findViewById(R.id.button_image);
        this.mCameraButton.setOnClickListener(this.mClickListener);
        this.mImageAttachment = (ImageAttachmentLayout) this.mView.findViewById(R.id.linear_convo_image_attachments);
        this.mImageAttachment.setImageAttachmentCallback(this);
        this.mMessageEditText = (EditText) this.mView.findViewById(R.id.edit_message);
        this.mMessageEditText.addTextChangedListener(this.mTextWatcher);
        this.mUserNameEditText = (ContactsAutoComplete) this.mView.findViewById(R.id.edit_username);
        this.mUserNameEditText.setAdapter(new RecentContactsAdapter(getActivity(), getImageBatch()));
        this.mUserNameEditText.addTextChangedListener(this.mTextWatcher);
        this.mUserNameEditText.setFocusableInTouchMode(true);
        this.mUserNameEditText.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ConvoComposeFragment.this.mSubjectEditText.requestFocus();
            }
        });
        this.mSubjectEditText = (EditText) this.mView.findViewById(R.id.edit_subject);
        this.mSubjectEditText.addTextChangedListener(this.mTextWatcher);
        this.mProgressDialog = aj.b((Context) getActivity(), getResources().getString(R.string.convo_message_sending_v2));
        setUpReplyOrNewMessage();
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            this.mParentDialog = (IDialogFragment) getParentFragment();
            this.mParentDialog.hideHeader();
            this.mParentDialog.setWindowMode(WindowMode.LARGE);
            setupHeaderView();
        }
    }

    private void setupHeaderView() {
        ((ViewStub) this.mView.findViewById(R.id.convo_header)).setVisibility(0);
        this.mSendButton = this.mView.findViewById(R.id.btn_send);
        this.mSendButton.setOnClickListener(this.mClickListener);
        this.mSendButton.setEnabled(preValidateMessage());
        this.mView.findViewById(R.id.btn_x).setOnClickListener(this.mClickListener);
        setHeaderViewHeight();
    }

    public void setHeaderViewHeight() {
        this.mView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                ConvoComposeFragment.this.mView.setMinimumHeight(((IDialogFragment) ConvoComposeFragment.this.getParentFragment()).getMinHeight(WindowMode.LARGE));
                ConvoComposeFragment.this.mView.setMinimumWidth(((IDialogFragment) ConvoComposeFragment.this.getParentFragment()).getMinWidth(WindowMode.LARGE));
                j.b(ConvoComposeFragment.this.mView.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            }
        });
    }

    public void onResume() {
        super.onResume();
        setTitle();
        if (v.a().e()) {
            return;
        }
        if (this.signInCounter > 0) {
            com.etsy.android.ui.nav.e.a((Fragment) this).h();
            return;
        }
        ((com.etsy.android.ui.nav.b) com.etsy.android.ui.nav.e.a((Activity) getActivity()).a(getView())).a(EtsyAction.CONTACT_USER, getArguments());
        this.signInCounter++;
    }

    public void onFragmentResume() {
        super.onFragmentResume();
        setTitle();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mCameraHelper.saveState(bundle);
        bundle.putInt("signInCounter", this.signInCounter);
        d.a((Context) this.mActivity, getDraft());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mIsTablet && this.mParentDialog != null) {
            setHeaderViewHeight();
        }
        layoutImageAttachments();
    }

    private void layoutImageAttachments() {
        this.mImageAttachment.refreshRatio();
        this.mImageAttachment.calcSizes(ErrorDialogData.SUPPRESSED);
        this.mImageAttachment.requestLayout();
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.convo_compose_action_bar, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.menu_send_reply);
        if (findItem != null) {
            Drawable icon = findItem.getIcon();
            boolean z = !this.mImageIsAttaching && preValidateMessage();
            icon.setAlpha(z ? 255 : 75);
            findItem.setEnabled(z);
        }
        super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_send_reply) {
            return super.onOptionsItemSelected(menuItem);
        }
        send();
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    private void fetchRecipient(long j) {
        this.recipientLookupDisposable = this.convoRepository.a(j).b(this.schedulers.a()).a(this.schedulers.b()).a((Consumer<? super T>) new d<Object>(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$fetchRecipient$0$ConvoComposeFragment(i.a aVar) throws Exception {
        if (aVar instanceof i.a.b) {
            User a = ((i.a.b) aVar).a();
            if (this.mUserNameEditText != null) {
                this.mUserNameEditText.setText(a.getDisplayName());
            }
        }
    }

    private void setUpReplyOrNewMessage() {
        if (getArguments() != null) {
            Long valueOf = Long.valueOf(getArguments().getLong("user_id", 0));
            String string = getArguments().getString(ResponseConstants.USERNAME);
            String string2 = getArguments().getString(ResponseConstants.SUBJECT);
            String string3 = getArguments().getString("message");
            this.mIsReply = getArguments().containsKey("convo_id");
            this.mConvoId = getArguments().getInt("convo_id");
            if (af.a(string)) {
                if (this.mIsReply) {
                    string = String.format(getString(R.string.to_user), new Object[]{string});
                }
                this.mUserNameEditText.setText(string);
                this.mUserNameEditText.setEnabled(false);
                this.mSubjectEditText.requestFocus();
            } else if (valueOf.longValue() > 0) {
                fetchRecipient(valueOf.longValue());
            }
            if (af.a(string2)) {
                if (this.mIsReply) {
                    string2 = String.format(getString(R.string.re_subject), new Object[]{string2});
                }
                this.mSubjectEditText.setText(string2);
                this.mSubjectEditText.setEnabled(false);
                this.mMessageEditText.requestFocus();
            }
            if (af.a(string3)) {
                this.mMessageEditText.setText(string3);
                this.mMessageEditText.requestFocus();
                this.mMessageEditText.setSelection(string3.length());
            }
        }
        if (this.mDraft != null && this.mDraft.getConvoId() == 0) {
            this.mMessageEditText.setText(this.mDraft.getMessage());
            this.mMessageEditText.setSelection(this.mDraft.getCursorStartPosition(), this.mDraft.getCursorEndPosition());
            this.mUserNameEditText.setText(this.mDraft.getUserName());
            this.mSubjectEditText.setText(this.mDraft.getSubject());
            if (!this.mIsTablet) {
                this.mImageAttachment.setImages(this.mDraft.getImages());
            }
            checkCanAttachMore();
        }
    }

    private Draft getDraft() {
        Draft saveCursorPosition = new Draft().message(this.mMessageEditText.getText().toString()).subject(this.mSubjectEditText.getText().toString()).userName(this.mUserNameEditText.getText().toString()).saveCursorPosition(this.mMessageEditText.getSelectionStart(), this.mMessageEditText.getSelectionEnd());
        if (!this.mIsTablet) {
            saveCursorPosition.images(this.mImageAttachment.getImageFiles());
        }
        return saveCursorPosition;
    }

    private void setTitle() {
        if (!this.mIsTablet) {
            String string = getResources().getString(R.string.convo_compose_new_title);
            if (this.mIsReply) {
                string = String.format(getResources().getString(R.string.convo_compose_reply), new Object[]{getArguments().getString(ResponseConstants.USERNAME)});
            }
            this.mActivity.setTitle(string);
        }
    }

    public void onDestroy() {
        if (this.recipientLookupDisposable != null) {
            this.recipientLookupDisposable.dispose();
        }
        this.mCameraHelper.removeCallback();
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onPreSendMessage() {
        this.mProgressDialog = aj.b((Context) getActivity(), getResources().getString(R.string.convo_message_sending_v2));
        this.mProgressDialog.show();
    }

    public void onMessageSent() {
        this.mProgressDialog.dismiss();
        hideKeyboard();
        aj.b((Context) getActivity(), (int) R.string.convo_message_send_success);
        this.mImageAttachment.clear();
        d.c(this.mActivity);
        LocalBroadcastManager.getInstance(this.mActivity).sendBroadcast(new Intent("com.etsy.android.convos.MESSAGE_SENT"));
        if (this.mListener != null) {
            this.mListener.onMessageSent();
        }
    }

    public void onMessageError(String str) {
        this.mProgressDialog.dismiss();
        Toast makeText = Toast.makeText(getActivity(), str, 0);
        makeText.setGravity(16, 0, 0);
        makeText.show();
    }

    public void onNoAvailableActivities() {
        aj.b((Context) this.mActivity, (int) R.string.no_available_chooser);
    }

    public Object onPreImageSave() {
        this.mImageIsAttaching = true;
        checkSendButton();
        return this.mImageAttachment.startLoading();
    }

    public void onImageSaveSuccess(Object obj, Bitmap bitmap, File file) {
        this.mImageAttachment.addBitmap((ImageAttachmentLayout.b) obj, bitmap, file);
        this.mImageIsAttaching = false;
        checkCanAttachMore();
        checkSendButton();
    }

    public void onImageSaveFail(Object obj, File file) {
        if (this.mActivity != null) {
            aj.b((Context) this.mActivity, (int) R.string.camera_helper_image_load_error);
        }
        this.mImageAttachment.onAbort((ImageAttachmentLayout.b) obj, file);
        this.mImageIsAttaching = false;
        checkCanAttachMore();
        checkSendButton();
    }

    public void onPermissionGranted() {
        this.mCameraHelper.startImagePicker((Fragment) this, (int) R.string.choose_image);
    }

    public void onRemove() {
        checkCanAttachMore();
    }

    /* access modifiers changed from: private */
    public void send() {
        if (NetworkUtils.a().b()) {
            new d(this.mActivity, this).a(new Draft().userName(this.mUserNameEditText.getText().toString()).subject(this.mSubjectEditText.getText().toString()).message(this.mMessageEditText.getText().toString()).images(this.mImageAttachment.getImageFiles()).convoId((long) this.mConvoId));
        } else {
            aj.a((Context) this.mActivity, (int) R.string.network_unavailable);
        }
    }

    private void checkCanAttachMore() {
        if (this.mImageAttachment.hasSpaceAvailable()) {
            this.mCameraButton.setVisibility(0);
        } else {
            this.mCameraButton.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void checkSendButton() {
        if (this.mSendButton != null) {
            this.mSendButton.setEnabled(!this.mImageIsAttaching && preValidateMessage());
        } else {
            this.mActivity.invalidateOptionsMenu();
        }
    }

    private boolean preValidateMessage() {
        if (this.mUserNameEditText == null || this.mSubjectEditText == null || this.mMessageEditText == null) {
            return false;
        }
        return com.etsy.android.lib.convos.j.b(this.mActivity, this.mUserNameEditText.getText().toString(), this.mSubjectEditText.getText().toString(), this.mMessageEditText.getText().toString());
    }

    /* access modifiers changed from: private */
    public void hideKeyboard() {
        s.a(this.mView);
    }

    public void onDestroyView() {
        if (this.mUserNameEditText != null) {
            RecentContactsAdapter recentContactsAdapter = (RecentContactsAdapter) this.mUserNameEditText.getAdapter();
            if (!(recentContactsAdapter == null || recentContactsAdapter.getCursor() == null)) {
                recentContactsAdapter.getCursor().close();
            }
        }
        super.onDestroyView();
    }
}
