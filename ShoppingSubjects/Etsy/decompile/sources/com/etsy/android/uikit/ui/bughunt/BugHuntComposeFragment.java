package com.etsy.android.uikit.ui.bughunt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.lib.a.i;
import com.etsy.android.lib.a.k;
import com.etsy.android.lib.a.o;
import com.etsy.android.lib.core.http.loader.NetworkLoader;
import com.etsy.android.lib.core.img.g;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.CameraHelper.b;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.s;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.view.ImageAttachmentLayout;
import com.etsy.android.uikit.view.ImageAttachmentLayout.a;
import com.etsy.etsyapi.models.resource.pub.BugHuntReport;
import java.io.File;
import java.util.List;

public class BugHuntComposeFragment extends NetworkLoaderFragment implements b, a {
    private static final String BUNDLE_BUG_MESSAGE = "bug_message";
    private static final String BUNDLE_USERNAME = "username";
    private static final int SUBMIT = 0;
    /* access modifiers changed from: private */
    public BugHuntActivity mBugHuntActivity;
    private ImageButton mCameraButton;
    /* access modifiers changed from: private */
    public CameraHelper mCameraHelper;
    private OnClickListener mClickListener = new TrackingOnClickListener() {
        public void onViewClick(View view) {
            BugHuntComposeFragment.this.hideKeyboard();
            BugHuntComposeFragment.this.mCameraHelper.startImagePicker((Fragment) BugHuntComposeFragment.this, o.choose_image, null);
        }
    };
    private EditText mDescriptionEditText;
    /* access modifiers changed from: private */
    public ImageAttachmentLayout mImageAttachment;
    /* access modifiers changed from: private */
    public View mLoadingIndicator;
    private View mScreenshotMessage;
    private TextView mScreenshotPrompt;
    private View mUsernameContainer;
    private EditText mUsernameEditText;
    /* access modifiers changed from: private */
    public View mView;

    public void onRequestCrop(Uri uri, Uri uri2) {
    }

    public static BugHuntComposeFragment newInstance(String str) {
        BugHuntComposeFragment bugHuntComposeFragment = new BugHuntComposeFragment();
        if (!TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putString("image_uri", str);
            bugHuntComposeFragment.setArguments(bundle);
        }
        return bugHuntComposeFragment;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mBugHuntActivity = (BugHuntActivity) activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCameraHelper = new CameraHelper(getActivity(), bundle, this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(k.fragment_bughunt_compose, viewGroup, false);
        this.mView = inflate.findViewById(i.bughunt_compose_content);
        this.mLoadingIndicator = inflate.findViewById(i.loading_indicator);
        this.mCameraButton = (ImageButton) this.mView.findViewById(i.bughunt_image_add);
        this.mCameraButton.setOnClickListener(this.mClickListener);
        this.mImageAttachment = (ImageAttachmentLayout) this.mView.findViewById(i.bughunt_image_attachments);
        this.mImageAttachment.setImageAttachmentCallback(this);
        this.mUsernameEditText = (EditText) this.mView.findViewById(i.bugbounty_edit_username);
        this.mUsernameContainer = this.mView.findViewById(i.bugbounty_edit_username_container);
        this.mDescriptionEditText = (EditText) this.mView.findViewById(i.edit_bug_message);
        this.mScreenshotPrompt = (TextView) this.mView.findViewById(i.label_bugbounty_screenshot_prompt);
        this.mScreenshotMessage = this.mView.findViewById(i.footer_message);
        checkUsernameView();
        reconnectToRequest();
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            if (bundle.containsKey(BUNDLE_BUG_MESSAGE) && bundle.get(BUNDLE_BUG_MESSAGE) != null) {
                this.mDescriptionEditText.setText(bundle.getCharSequence(BUNDLE_BUG_MESSAGE));
            }
            if (bundle.containsKey("username") && bundle.get("username") != null) {
                this.mUsernameEditText.setText(bundle.getCharSequence("username"));
            }
        }
        if (getArguments() != null && getArguments().containsKey("image_uri")) {
            String string = getArguments().getString("image_uri");
            ImageAttachmentLayout.b startLoading = this.mImageAttachment.startLoading();
            if (startLoading != null) {
                Pair b = g.b(string);
                this.mImageAttachment.addBitmap(startLoading, g.a(string, ((Integer) b.first).intValue(), ((Integer) b.second).intValue()), new File(string));
                checkAttachmentsView();
            }
        }
        if (getUsername() == null) {
            focusEditText(this.mUsernameEditText);
        } else {
            focusEditText(this.mDescriptionEditText);
        }
    }

    public void onResume() {
        super.onResume();
        checkAttachmentsView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mCameraHelper.saveState(bundle);
        bundle.putCharSequence(BUNDLE_BUG_MESSAGE, this.mDescriptionEditText.getText());
        bundle.putCharSequence("username", this.mUsernameEditText.getText());
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    public void onDestroy() {
        this.mCameraHelper.removeCallback();
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onNoAvailableActivities() {
        aj.b((Context) getActivity(), o.no_available_chooser);
    }

    public Object onPreImageSave() {
        return this.mImageAttachment.startLoading();
    }

    public void onImageSaveSuccess(Object obj, Bitmap bitmap, File file) {
        this.mImageAttachment.addBitmap((ImageAttachmentLayout.b) obj, bitmap, file);
        checkCanAttachMore();
        checkAttachmentsView();
    }

    public void onImageSaveFail(Object obj, File file) {
        if (getActivity() != null) {
            aj.b((Context) getActivity(), o.camera_helper_image_load_error);
        }
        this.mImageAttachment.onAbort((ImageAttachmentLayout.b) obj, file);
        checkCanAttachMore();
    }

    public void onPermissionGranted() {
        this.mCameraHelper.startImagePicker((Fragment) this, o.choose_image);
    }

    public void onRemove() {
        checkCanAttachMore();
        checkAttachmentsView();
    }

    public void reconnectToRequest() {
        Loader loader = getLoaderManager().getLoader(0);
        if (loader != null && loader.isStarted()) {
            showLoading();
            sendBugReport();
        }
    }

    public void showLoading() {
        this.mLoadingIndicator.setVisibility(0);
        this.mView.setVisibility(8);
        this.mBugHuntActivity.displayFab(false);
        hideKeyboard();
    }

    public void sendBugReport() {
        loadDataFromNetwork(0, createIssue(this.mDescriptionEditText != null ? this.mDescriptionEditText.getText().toString() : "", getResources().getString(o.bughunt_component), this.mImageAttachment), (NetworkLoader.a<T>) new NetworkLoader.a<BugHuntReport>() {
            public void a(@NonNull List<BugHuntReport> list, int i, @NonNull com.etsy.android.lib.core.a.a<BugHuntReport> aVar) {
                BugHuntComposeFragment.this.mView.setVisibility(0);
                BugHuntComposeFragment.this.mLoadingIndicator.setVisibility(8);
                BugHuntComposeFragment.this.mBugHuntActivity.displayFab(true);
                aj.b((Context) BugHuntComposeFragment.this.getActivity(), o.supportfeedback_message_send_success);
                BugHuntComposeFragment.this.mImageAttachment.clear();
                BugHuntComposeFragment.this.mBugHuntActivity.setupLeaderboard();
            }

            public void a(int i, @Nullable String str, @NonNull com.etsy.android.lib.core.a.a<BugHuntReport> aVar) {
                BugHuntComposeFragment.this.mView.setVisibility(0);
                BugHuntComposeFragment.this.mLoadingIndicator.setVisibility(8);
                BugHuntComposeFragment.this.mBugHuntActivity.displayFab(true);
                Toast.makeText(BugHuntComposeFragment.this.getActivity(), "Error Submitting Report", 0).show();
            }
        });
    }

    /* JADX INFO: used method not loaded: com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a.b(java.io.File):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.a.a(java.io.File):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00aa, code lost:
        r6.b((java.io.File) r7.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00b4, code lost:
        r6.a((java.io.File) r7.get(0));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.etsy.etsyapi.c<com.etsy.etsyapi.models.resource.pub.BugHuntReport> createIssue(java.lang.String r6, java.lang.String r7, com.etsy.android.uikit.view.ImageAttachmentLayout r8) {
        /*
            r5 = this;
            int r0 = r6.length()
            r1 = 0
            r2 = 255(0xff, float:3.57E-43)
            if (r0 <= r2) goto L_0x0020
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r2 = 252(0xfc, float:3.53E-43)
            java.lang.String r6 = r6.substring(r1, r2)
            r0.append(r6)
            java.lang.String r6 = "..."
            r0.append(r6)
            java.lang.String r6 = r0.toString()
        L_0x0020:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = android.os.Build.DEVICE
            r0.append(r2)
            java.lang.String r2 = " "
            r0.append(r2)
            java.lang.String r2 = android.os.Build.MODEL
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            int r2 = r0.length()
            r3 = 128(0x80, float:1.794E-43)
            if (r2 <= r3) goto L_0x0046
            r2 = 127(0x7f, float:1.78E-43)
            java.lang.String r0 = r0.substring(r1, r2)
        L_0x0046:
            com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec$a r2 = com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec.builder()
            r2.e(r0)
            com.etsy.android.lib.core.v r0 = com.etsy.android.lib.core.v.a()
            boolean r0 = r0.e()
            if (r0 == 0) goto L_0x006e
            com.etsy.android.lib.core.v r0 = com.etsy.android.lib.core.v.a()
            com.etsy.android.lib.models.datatypes.EtsyId r0 = r0.l()
            long r3 = r0.getIdAsLong()
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            com.etsy.etsyapi.models.EtsyId r0 = com.etsy.etsyapi.models.EtsyId.create(r0)
            r2.a(r0)
        L_0x006e:
            com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec$a r6 = r2.a(r6)
            com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec$a r6 = r6.b(r7)
            java.lang.String r7 = android.os.Build.VERSION.RELEASE
            com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec$a r6 = r6.c(r7)
            com.etsy.android.lib.config.g r7 = com.etsy.android.lib.config.g.a()
            java.lang.String r7 = r7.f()
            com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec$a r6 = r6.d(r7)
            if (r8 == 0) goto L_0x00bd
            java.util.List r7 = r8.getImageFiles()
            int r7 = r7.size()
            if (r7 <= 0) goto L_0x00bd
            java.util.List r7 = r8.getImageFiles()
            int r8 = r7.size()
            switch(r8) {
                case 1: goto L_0x00b4;
                case 2: goto L_0x00aa;
                case 3: goto L_0x00a0;
                default: goto L_0x009f;
            }
        L_0x009f:
            goto L_0x00bd
        L_0x00a0:
            r8 = 2
            java.lang.Object r8 = r7.get(r8)
            java.io.File r8 = (java.io.File) r8
            r6.c(r8)
        L_0x00aa:
            r8 = 1
            java.lang.Object r8 = r7.get(r8)
            java.io.File r8 = (java.io.File) r8
            r6.b(r8)
        L_0x00b4:
            java.lang.Object r7 = r7.get(r1)
            java.io.File r7 = (java.io.File) r7
            r6.a(r7)
        L_0x00bd:
            com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec r6 = r6.a()
            com.etsy.etsyapi.c r6 = r6.request()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.uikit.ui.bughunt.BugHuntComposeFragment.createIssue(java.lang.String, java.lang.String, com.etsy.android.uikit.view.ImageAttachmentLayout):com.etsy.etsyapi.c");
    }

    public void send() {
        if (TextUtils.isEmpty(getUsername())) {
            Toast.makeText(getActivity(), "Check Username or Sign In", 0).show();
        } else if (TextUtils.isEmpty(this.mDescriptionEditText.getText())) {
            this.mDescriptionEditText.setError("Enter Description");
        } else {
            if (!v.a().e()) {
                SharedPreferencesUtility.e(getActivity(), getUsername());
            }
            sendBugReport();
        }
    }

    private void checkCanAttachMore() {
        if (this.mImageAttachment.hasSpaceAvailable()) {
            this.mCameraButton.setVisibility(0);
        } else {
            this.mCameraButton.setVisibility(8);
        }
    }

    private void checkUsernameView() {
        boolean e = v.a().e();
        this.mUsernameContainer.setVisibility(e ? 8 : 0);
        String username = getUsername();
        if (!e && username != null) {
            this.mUsernameEditText.setText(username);
        }
        s.b((View) username != null ? this.mDescriptionEditText : this.mUsernameEditText);
    }

    private void checkAttachmentsView() {
        int size = this.mImageAttachment.getImageFiles().size();
        switch (size) {
            case 0:
                this.mScreenshotPrompt.setText(getResources().getString(o.bugbounty_screenshot_prompt));
                this.mScreenshotMessage.setVisibility(0);
                this.mImageAttachment.setVisibility(8);
                return;
            case 1:
                TextView textView = this.mScreenshotPrompt;
                StringBuilder sb = new StringBuilder();
                sb.append(size);
                sb.append(" Screenshot Attached");
                textView.setText(sb.toString());
                this.mScreenshotMessage.setVisibility(8);
                this.mImageAttachment.setVisibility(0);
                return;
            default:
                TextView textView2 = this.mScreenshotPrompt;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(size);
                sb2.append(" Screenshots Attached");
                textView2.setText(sb2.toString());
                this.mScreenshotMessage.setVisibility(8);
                this.mImageAttachment.setVisibility(0);
                return;
        }
    }

    private String getUsername() {
        String str;
        if (v.a().e()) {
            str = SharedPreferencesUtility.e(getActivity());
        } else {
            str = SharedPreferencesUtility.m(getActivity());
            if (str == null) {
                str = this.mUsernameEditText.getText().toString();
            }
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    private boolean validateMessage() {
        if (getUsername() == null) {
            return false;
        }
        return !TextUtils.isEmpty(this.mDescriptionEditText.getText());
    }

    /* access modifiers changed from: private */
    public void hideKeyboard() {
        s.a(this.mView);
    }

    private void focusEditText(EditText editText) {
        editText.clearFocus();
        editText.requestFocus();
        if (getActivity() != null) {
            s.c((Context) getActivity()).showSoftInput(editText, 0);
        }
    }
}
