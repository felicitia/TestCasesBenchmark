package com.onfido.android.sdk.capture.ui.camera;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.R;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.common.SdkController;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectorFrame;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectorTextLabel;
import com.onfido.android.sdk.capture.ui.BaseActivity;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.ErrorDialogFeature;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter.State;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter.View;
import com.onfido.android.sdk.capture.ui.camera.ConfirmationStepButtons.Listener;
import com.onfido.android.sdk.capture.ui.camera.face.CameraSource.ShutterCallback;
import com.onfido.android.sdk.capture.ui.camera.face.CameraSourcePreview;
import com.onfido.android.sdk.capture.ui.camera.face.CameraSourcePreview.ErrorListener;
import com.onfido.android.sdk.capture.ui.camera.face.MediaCaptureCallback;
import com.onfido.android.sdk.capture.ui.camera.face.UnknownCameraException;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessOverlayView.LivenessOverlayListener;
import com.onfido.android.sdk.capture.ui.options.Orientation;
import com.onfido.android.sdk.capture.ui.privacy.PrivacyPolicyView;
import com.onfido.android.sdk.capture.upload.DocumentSide;
import com.onfido.android.sdk.capture.upload.UploadErrorType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.utils.OnfidoApiUtil;
import com.onfido.android.sdk.capture.utils.ViewUtil;
import com.onfido.android.sdk.capture.validation.CaptureValidationBubble;
import com.onfido.android.sdk.capture.validation.PostCaptureValidationResults;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.DocSide;
import com.onfido.api.client.data.DocType;
import com.onfido.api.client.data.DocumentUpload;
import com.onfido.api.client.data.LivePhotoUpload;

public class CaptureActivity extends BaseActivity implements View, CaptureUploadServiceListener, Listener, FrameCallback, OverlayView.Listener, ShutterCallback, ErrorListener, MediaCaptureCallback, LivenessOverlayListener, PrivacyPolicyView.Listener {
    private final int A = 1000;
    private boolean B = false;
    private byte[] C;
    /* access modifiers changed from: private */
    public boolean D;
    /* access modifiers changed from: private */
    public boolean E;
    private RectF F;
    private RectF G;
    private RectData H;
    private RectData I;
    private PostCaptureValidationResults J = new PostCaptureValidationResults(false, null);
    CapturePresenter a;
    ImageUtils b;
    IdentityInteractor c;
    private a d;
    private ErrorDialogFeature e;
    private FrameLayout f;
    /* access modifiers changed from: private */
    public CameraSourcePreview g;
    /* access modifiers changed from: private */
    public OverlayView h;
    private ImageView i;
    private ImageView j;
    private ConfirmationStepButtons k;
    private DocumentType l = null;
    private Toolbar m;
    private OverlayTextView n;
    private CaptureValidationBubble o;
    /* access modifiers changed from: private */
    public CaptureValidationBubble p;
    private EdgeDetectorFrame q;
    private EdgeDetectorTextLabel r;
    private LivenessOverlayView s;
    /* access modifiers changed from: private */
    public BottomSheetBehavior t;
    private ImageView u;
    private PrivacyPolicyView v;
    private Handler w = new Handler();
    private Runnable x = new Runnable() {
        public void run() {
            CaptureActivity.this.p.animate(false);
        }
    };
    private final int y = 300;
    private final int z = 1500;

    private int a(CaptureType captureType) {
        return ContextCompat.getColor(this, AnonymousClass6.a[captureType.ordinal()] != 3 ? R.color.onfido_photo_camera_blur : R.color.onfido_video_camera_blur);
    }

    private static Intent a(Context context, Applicant applicant, OnfidoConfig onfidoConfig) {
        Intent intent = new Intent(context, CaptureActivity.class);
        intent.putExtra("onfido_config", onfidoConfig);
        intent.putExtra("applicant", applicant);
        return intent;
    }

    private Intent a(String str) {
        Intent intent = new Intent();
        intent.putExtra("upload_id", str);
        return intent;
    }

    private DocumentType a(DocType docType) {
        switch (docType) {
            case PASSPORT:
                return DocumentType.PASSPORT;
            case DRIVING_LICENSE:
                return DocumentType.DRIVING_LICENCE;
            case NATIONAL_ID_CARD:
                return DocumentType.NATIONAL_IDENTITY_CARD;
            default:
                return DocumentType.RESIDENCE_PERMIT;
        }
    }

    private OverlayView a(DocumentType documentType) {
        LayoutInflater layoutInflater;
        int i2;
        if (AnonymousClass6.a[s().ordinal()] != 1) {
            return (OverlayView) getLayoutInflater().inflate(R.layout.onfido_view_overlay_face, this.f, false);
        }
        if (documentType == DocumentType.PASSPORT) {
            layoutInflater = getLayoutInflater();
            i2 = R.layout.onfido_view_overlay_passport;
        } else {
            layoutInflater = getLayoutInflater();
            i2 = R.layout.onfido_view_overlay_document;
        }
        return (OverlayView) layoutInflater.inflate(i2, this.f, false);
    }

    private void a(float f2) {
        LayoutParams layoutParams = (LayoutParams) this.n.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, (int) f2, layoutParams.rightMargin, layoutParams.bottomMargin);
        this.n.setLayoutParams(layoutParams);
        if (this.E) {
            return;
        }
        if ((this.l != DocumentType.PASSPORT || !this.a.hasNativeLibrary()) && !this.g.isRecording()) {
            this.n.setVisibility(0);
        }
    }

    private void a(final int i2) {
        if (getSupportActionBar() != null && this.m != null) {
            this.m.post(new Runnable() {
                public void run() {
                    CaptureActivity.this.getSupportActionBar().setTitle(i2);
                }
            });
        }
    }

    private void a(int i2, String str, ErrorDialogFeature.Listener listener) {
        setLoading(false);
        this.e.show(i2, str, listener);
    }

    private void a(RectF rectF) {
        this.s.updateTextPosition(rectF);
    }

    private void a(Bundle bundle) {
        if (bundle != null && bundle.containsKey(CapturePresenter.class.getSimpleName())) {
            this.a.setState((State) bundle.getSerializable(CapturePresenter.class.getSimpleName()));
        }
    }

    private void a(android.view.View view) {
        LayoutParams layoutParams = (LayoutParams) this.n.getLayoutParams();
        layoutParams.addRule(2, view.getId());
        this.n.setLayoutParams(layoutParams);
    }

    private void a(EdgeDetectionResults edgeDetectionResults) {
        this.q.update(edgeDetectionResults);
    }

    private void a(final CaptureType captureType, DocumentType documentType) {
        this.h = a(documentType);
        this.h.setListener(this);
        this.h.setColorOutsideOverlay(a(captureType));
        this.h.runOnMeasured(new Runnable() {
            public void run() {
                CaptureActivity.this.g.setFocusMeterAreaWeight((double) CaptureActivity.this.h.getBigHorizontalWeight(), (double) CaptureActivity.this.h.getVerticalWeight());
                CaptureActivity.this.g.setPictureWeightSize((double) CaptureActivity.this.h.getBigHorizontalWeight(), (double) CaptureActivity.this.h.getVerticalWeight());
                CaptureActivity.this.g.start(captureType == CaptureType.VIDEO);
                CaptureActivity.this.g.setFrameCallback(this);
                CaptureActivity.this.D = true;
            }
        });
        this.h.setCaptureType(captureType);
        this.n.setCaptureOverlayText(captureType, documentType, e());
        this.f.addView(this.h);
    }

    private void a(UploadErrorType uploadErrorType) {
        int i2;
        int i3;
        int i4;
        int i5;
        this.a.onUploadValidationError();
        setLoading(false);
        this.a.trackCaptureError(s(), true, uploadErrorType);
        switch (uploadErrorType) {
            case DOCUMENT:
                i4 = R.string.onfido_no_document;
                i5 = R.string.onfido_message_validation_error_document;
                break;
            case NO_FACE:
                i4 = R.string.onfido_no_face;
                i5 = R.string.onfido_message_validation_error_face;
                break;
            case MULTIPLE_FACES:
                i4 = R.string.onfido_multiple_faces;
                i5 = R.string.onfido_message_validation_error_multiple_faces;
                break;
            default:
                i3 = 0;
                i2 = 0;
                break;
        }
        i3 = i4;
        i2 = i5;
        this.o.setContent(i3, i2, null, R.drawable.onfido_error_red, R.color.onfido_post_capture_error);
        this.o.animate(true);
        this.k.setErrorState(true, s());
        ViewUtil.setViewVisibilityWithoutAnimation(this.n, false);
    }

    private void a(PostCaptureValidationResults postCaptureValidationResults) {
        boolean z2 = true;
        this.E = true;
        a(true);
        this.n.setConfirmationOverlayText(this.l);
        a((android.view.View) this.k);
        n();
        if (this.l != null) {
            boolean hasBlur = postCaptureValidationResults.getHasBlur();
            this.h.switchConfirmationMode(true);
            if (postCaptureValidationResults.hasIssue()) {
                ViewUtil.setViewVisibilityWithoutAnimation(this.n, false);
            } else {
                this.n.setVisibility(0);
            }
            if (hasBlur) {
                this.o.setContent(R.string.onfido_blur_detection_title, R.string.onfido_blur_detection_subtitle, null, R.drawable.onfido_error_red, R.color.onfido_post_capture_error);
                this.o.animate(true);
            } else if (postCaptureValidationResults.didBarcodeDetectionRun() && !postCaptureValidationResults.getHasBarcode().booleanValue()) {
                this.o.setContent(R.string.onfido_barcode_error_title, R.string.onfido_barcode_error_subtitle, Integer.valueOf(R.string.onfido_barcode_error_third_title), R.drawable.onfido_error_red, R.color.onfido_post_capture_error);
                this.o.animate(true);
                this.a.trackBarcodeNotReadable(this.l, getDocumentCountryFrom(getIntent()));
                this.k.setDocumentCaptureCopy(this.l, z2);
            }
            z2 = false;
            this.k.setDocumentCaptureCopy(this.l, z2);
        }
    }

    private void a(String str, UploadErrorType uploadErrorType) {
        b(str, uploadErrorType);
    }

    private void a(boolean z2) {
        ViewUtil.setViewVisibility(this.i, z2);
        ViewUtil.setViewVisibility(this.k, z2);
        if (this.l != DocumentType.PASSPORT || !a()) {
            ViewUtil.setViewVisibility(this.j, !z2);
        } else {
            if (this.j.getVisibility() == 0) {
                ViewUtil.setViewVisibility(this.j, !z2);
            }
            ViewUtil.setViewVisibility(this.q, !z2);
            ViewUtil.setViewVisibility(this.r, !z2);
            if (!z2) {
                ((TextView) findViewById(R.id.info)).setText(getString(R.string.onfido_autocapture_info));
            }
            final int i2 = z2 ? 5 : 3;
            long j2 = z2 ? 0 : 1500;
            this.w.removeCallbacksAndMessages(null);
            this.w.postDelayed(new Runnable() {
                public void run() {
                    CaptureActivity.this.t.setState(i2);
                }
            }, j2);
        }
        if (z2) {
            a(false, false, s());
            a(true, true, s());
            return;
        }
        a(false, true, s());
        a(true, false, s());
    }

    private void a(boolean z2, boolean z3, CaptureType captureType) {
        if (AnonymousClass6.a[captureType.ordinal()] != 1) {
            this.a.trackFaceCapture(z2, z3, k().isPortrait(), captureType);
            return;
        }
        CountryCode documentCountryFrom = getDocumentCountryFrom(getIntent());
        this.a.trackDocumentCapture(z2, z3, k().isPortrait(), this.l, documentCountryFrom, this.l.backSideCaptureNeeded(documentCountryFrom) ? e() : null);
    }

    private void a(byte[] bArr) {
        this.i.setScaleX(this.g.getIsFront() ? -1.0f : 1.0f);
        Bitmap decodeScaledResource = this.b.decodeScaledResource(bArr, this.i.getWidth(), this.i.getHeight());
        BitmapDrawable bitmapDrawable = this.i.getDrawable() instanceof BitmapDrawable ? (BitmapDrawable) this.i.getDrawable() : null;
        if (!(bitmapDrawable == null || bitmapDrawable.getBitmap() == null)) {
            bitmapDrawable.getBitmap().recycle();
        }
        this.i.setImageBitmap(decodeScaledResource);
    }

    private boolean a() {
        return this.l == DocumentType.PASSPORT && this.a.hasNativeLibrary() && this.a.shouldAutocapture(((int) (this.G.width() / this.g.getPreviewZoomFactor())) * ((int) (this.G.height() / this.g.getPreviewZoomFactor())));
    }

    private void b() {
        this.j.setImageResource(R.drawable.onfido_record_video_button);
        LayoutParams layoutParams = (LayoutParams) this.j.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, getResources().getDimensionPixelOffset(R.dimen.onfido_liveness_capture_button_bottom_margin));
        this.j.setLayoutParams(layoutParams);
    }

    private void b(int i2) {
        if (this.m.getNavigationIcon() != null) {
            this.m.getNavigationIcon().setColorFilter(ContextCompat.getColor(this, i2), Mode.SRC_ATOP);
        }
    }

    private void b(RectF rectF) {
        this.F = rectF;
        if (getDocTypeFrom(getIntent()) == DocumentType.PASSPORT && this.u.getVisibility() != 8 && this.a.hasNativeLibrary()) {
            LayoutParams layoutParams = new LayoutParams((int) this.F.width(), (int) this.F.height());
            layoutParams.setMargins((int) this.F.left, (int) this.F.top, (int) this.F.left, 0);
            this.u.setLayoutParams(layoutParams);
            this.u.setVisibility(0);
        }
        if (s() == CaptureType.DOCUMENT) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.onfido_capture_frame_stroke_width);
            int i2 = dimensionPixelOffset * 2;
            LayoutParams layoutParams2 = new LayoutParams(((int) (this.F.right - this.F.left)) + i2, ((int) (this.F.bottom - this.F.top)) + i2);
            layoutParams2.setMargins(((int) this.F.left) - dimensionPixelOffset, ((int) this.F.top) - dimensionPixelOffset, ((int) this.F.left) - dimensionPixelOffset, 0);
            this.q.setLayoutParams(layoutParams2);
            this.q.setVisibility(0);
        }
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.onfido_glare_detection_bubble_top_margin);
        LayoutParams layoutParams3 = (LayoutParams) this.p.getLayoutParams();
        LayoutParams layoutParams4 = (LayoutParams) this.o.getLayoutParams();
        layoutParams3.setMargins(0, ((int) rectF.bottom) + dimensionPixelOffset2, 0, 0);
        layoutParams4.setMargins(0, ((int) rectF.bottom) + dimensionPixelOffset2, 0, 0);
        this.o.setLayoutParams(layoutParams4);
        this.p.setLayoutParams(layoutParams3);
    }

    private void b(CaptureType captureType) {
        this.d = new a(captureType, OnfidoApiUtil.createOnfidoApiClient(this, (OnfidoConfig) getIntent().getSerializableExtra("onfido_config")), this.c, this);
        if (captureType == CaptureType.DOCUMENT) {
            this.d.a(t());
            this.d.a(this.l);
        }
    }

    private void b(String str) {
        Toast makeText = Toast.makeText(this, str, 0);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    private void b(String str, final UploadErrorType uploadErrorType) {
        a(R.string.onfido_error_dialog_title, str, (ErrorDialogFeature.Listener) new ErrorDialogFeature.Listener() {
            public void onErrorDialogClose() {
                if (uploadErrorType != null) {
                    CaptureActivity.this.a.trackCaptureError(CaptureActivity.this.s(), false, uploadErrorType);
                }
            }
        });
    }

    private void b(byte[] bArr) {
        setLoading(true);
        this.o.animate(false);
        this.d.a((Applicant) getIntent().getExtras().getSerializable("applicant"), this.a.getRequiredDocumentValidations(e()), this.a.shouldPerformFaceValidation(), bArr);
        this.a.trackUploadStarted(s());
    }

    private void c() {
        SdkController.getInstance().getSdkComponent(this).inject(this);
        this.a.attachView(this);
    }

    private void c(int i2) {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(ContextCompat.getColor(this, i2));
        }
    }

    public static Intent createDocumentIntent(Context context, Applicant applicant, OnfidoConfig onfidoConfig, boolean z2, DocumentType documentType, CountryCode countryCode) {
        Intent a2 = a(context, applicant, onfidoConfig);
        a2.putExtra("type", CaptureType.DOCUMENT);
        a2.putExtra("is_front_side", z2);
        a2.putExtra("doc_type", documentType);
        a2.putExtra("doc_country", countryCode);
        return a2;
    }

    public static Intent createFaceIntent(Context context, Applicant applicant, OnfidoConfig onfidoConfig) {
        Intent a2 = a(context, applicant, onfidoConfig);
        a2.putExtra("type", CaptureType.FACE);
        return a2;
    }

    public static Intent createLivenessIntent(Context context, Applicant applicant, OnfidoConfig onfidoConfig) {
        Intent a2 = a(context, applicant, onfidoConfig);
        a2.putExtra("type", CaptureType.VIDEO);
        return a2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        a(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
            r3 = this;
            android.content.Intent r0 = r3.getIntent()
            java.lang.String r1 = "type"
            java.io.Serializable r0 = r0.getSerializableExtra(r1)
            com.onfido.android.sdk.capture.ui.CaptureType r0 = (com.onfido.android.sdk.capture.ui.CaptureType) r0
            int r1 = com.onfido.android.sdk.capture.R.id.toolbar
            android.view.View r1 = r3.findViewById(r1)
            android.support.v7.widget.Toolbar r1 = (android.support.v7.widget.Toolbar) r1
            r3.m = r1
            android.support.v7.widget.Toolbar r1 = r3.m
            r3.setSupportActionBar(r1)
            android.support.v7.app.ActionBar r1 = r3.getSupportActionBar()
            if (r1 == 0) goto L_0x0054
            android.support.v7.widget.Toolbar r1 = r3.m
            if (r1 == 0) goto L_0x0054
            android.support.v7.app.ActionBar r1 = r3.getSupportActionBar()
            r2 = 1
            r1.setDisplayHomeAsUpEnabled(r2)
            int[] r1 = com.onfido.android.sdk.capture.ui.camera.CaptureActivity.AnonymousClass6.a
            int r0 = r0.ordinal()
            r0 = r1[r0]
            switch(r0) {
                case 1: goto L_0x0042;
                case 2: goto L_0x003c;
                case 3: goto L_0x0039;
                default: goto L_0x0038;
            }
        L_0x0038:
            return
        L_0x0039:
            int r0 = com.onfido.android.sdk.capture.R.string.onfido_welcome_view_toolbar_title
            goto L_0x003e
        L_0x003c:
            int r0 = com.onfido.android.sdk.capture.R.string.onfido_welcome_view_face_capture_title
        L_0x003e:
            r3.a(r0)
            return
        L_0x0042:
            android.content.Intent r0 = r3.getIntent()
            com.onfido.android.sdk.capture.DocumentType r0 = getDocTypeFrom(r0)
            android.support.v7.widget.Toolbar r1 = r3.m
            com.onfido.android.sdk.capture.ui.camera.CaptureActivity$8 r2 = new com.onfido.android.sdk.capture.ui.camera.CaptureActivity$8
            r2.<init>(r0)
            r1.post(r2)
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.camera.CaptureActivity.d():void");
    }

    private DocSide e() {
        return getIsDocumentFrontSide(getIntent()) ? DocSide.FRONT : DocSide.BACK;
    }

    private void f() {
        this.e = new ErrorDialogFeature();
        this.e.attach(this);
    }

    private void g() {
        this.a.trackCaptureError(s(), true, UploadErrorType.NETWORK);
        b(getString(R.string.onfido_error_connection_message), UploadErrorType.NETWORK);
    }

    public static DocumentType getDocTypeFrom(Intent intent) {
        return (DocumentType) intent.getSerializableExtra("doc_type");
    }

    public static CountryCode getDocumentCountryFrom(Intent intent) {
        return (CountryCode) intent.getSerializableExtra("doc_country");
    }

    public static DocumentSide getDocumentResultFrom(Intent intent) {
        return new DocumentSide(intent.getStringExtra("upload_id"), getIsDocumentFrontSide(intent) ? DocSide.FRONT : DocSide.BACK, getDocTypeFrom(intent));
    }

    public static boolean getIsDocumentFrontSide(Intent intent) {
        return intent.getBooleanExtra("is_front_side", true);
    }

    private void h() {
        this.a.trackCaptureError(s(), true, UploadErrorType.GENERIC);
        a(j(), UploadErrorType.GENERIC);
    }

    private boolean i() {
        return (this.G == null || this.F == null) ? false : true;
    }

    private String j() {
        return getString(s() == CaptureType.DOCUMENT ? R.string.onfido_message_capture_error_document : R.string.onfido_message_capture_error_face);
    }

    private Orientation k() {
        return getResources().getConfiguration().orientation == 2 ? Orientation.LANDSCAPE : Orientation.PORTRAIT;
    }

    /* access modifiers changed from: private */
    public void l() {
        if (this.l != DocumentType.PASSPORT || !a()) {
            ViewUtil.setViewVisibility(this.j, false);
        }
        if (s() != CaptureType.VIDEO) {
            this.a.stop();
            this.g.takePicture(this, this);
            return;
        }
        this.a.onRecordingStarted();
        this.a.issueNextChallenge();
        ViewUtil.setViewVisibilityWithoutAnimation(this.n, false);
        this.s.setVisibility(0);
        this.h.paintCaptureArea();
        this.g.startVideo(this);
    }

    /* access modifiers changed from: private */
    public void m() {
        this.i.setImageBitmap(null);
        a(false);
        this.a.clearEdges();
        this.h.switchConfirmationMode(false);
        this.g.start(s() == CaptureType.VIDEO);
        this.n.setCaptureOverlayText(s(), this.l, e());
        if (this.l == DocumentType.PASSPORT) {
            this.n.setVisibility(4);
        } else {
            this.n.setVisibility(0);
        }
        this.o.animate(false);
        this.k.setListener(this);
        this.k.setErrorState(false, s());
        a((android.view.View) this.j);
        o();
        this.E = false;
        this.a.start(s(), this.l, false);
    }

    private void n() {
        if (getSupportActionBar() != null) {
            b(R.color.onfidoTextColorPrimary);
            this.m.setBackgroundColor(ContextCompat.getColor(this, R.color.onfidoColorPrimary));
            this.m.setTitleTextColor(ContextCompat.getColor(this, R.color.onfidoTextColorPrimary));
            this.m.setSubtitleTextColor(ContextCompat.getColor(this, R.color.onfidoTextColorSecondary));
            c(R.color.onfidoColorPrimaryDark);
        }
        this.h.setColorOutsideOverlay(ContextCompat.getColor(this, R.color.onfido_white));
        this.h.setLightTheme();
    }

    private void o() {
        if (getSupportActionBar() != null) {
            b(R.color.onfido_white);
            this.m.setBackgroundColor(ContextCompat.getColor(this, R.color.onfido_transparent));
            this.m.setTitleTextColor(ContextCompat.getColor(this, R.color.onfido_white));
            this.m.setSubtitleTextColor(ContextCompat.getColor(this, R.color.onfido_light_200));
            c(R.color.onfido_black);
        }
        this.h.setColorOutsideOverlay(a(s()));
        this.h.setDarkTheme();
    }

    private void p() {
        if (!getIntent().hasExtra("type")) {
            throw new IllegalArgumentException("CaptureActivity should be created through createFor factory method");
        }
    }

    private void q() {
        if (r()) {
            b(getString(R.string.onfido_message_capture_camera_unavailable));
            finish();
        }
    }

    private boolean r() {
        return !getPackageManager().hasSystemFeature("android.hardware.camera") || Camera.getNumberOfCameras() == 0;
    }

    /* access modifiers changed from: private */
    public CaptureType s() {
        return (CaptureType) getIntent().getExtras().getSerializable("type");
    }

    private boolean t() {
        return getIsDocumentFrontSide(getIntent());
    }

    public void clearEdges() {
        a(new EdgeDetectionResults());
    }

    public void confirmed() {
        b(this.C);
    }

    public void discarded() {
        m();
    }

    public void onBackPressed() {
        if (this.E) {
            m();
            return;
        }
        if (this.v == null || !this.v.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void onCameraNotFound() {
        this.a.trackCaptureError(s(), true);
        b(getString(R.string.onfido_message_capture_camera_unavailable), null);
    }

    public void onCameraUnavailable() {
        this.a.trackCaptureError(s(), true);
        b(getString(R.string.onfido_message_capture_camera_unavailable_runtime), null);
    }

    public void onChallengesCompleted() {
        this.g.finishRecording(true);
    }

    public void onContinuePressed() {
        this.a.onPolicyContinuePressed();
        this.a.start(s(), this.l, true);
        if (!getIntent().hasExtra("IS_RECREATING")) {
            a(true, this.E, s());
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.onfido_activity_capture);
        p();
        q();
        this.f = (FrameLayout) findViewById(R.id.fl_content_layout);
        this.j = (ImageView) findViewById(R.id.bt_capture);
        this.i = (ImageView) findViewById(R.id.preview_image);
        this.k = (ConfirmationStepButtons) findViewById(R.id.confirmation_step_buttons);
        this.n = (OverlayTextView) findViewById(R.id.overlayTextContainer);
        this.o = (CaptureValidationBubble) findViewById(R.id.postCaptureValidationBubble);
        this.p = (CaptureValidationBubble) findViewById(R.id.liveValidationBubble);
        this.q = (EdgeDetectorFrame) findViewById(R.id.captureFrame);
        this.r = (EdgeDetectorTextLabel) findViewById(R.id.captureLabel);
        this.s = (LivenessOverlayView) findViewById(R.id.livenessOverlayView);
        this.t = BottomSheetBehavior.from(findViewById(R.id.autoCaptureInfo));
        this.t.setState(5);
        this.u = (ImageView) findViewById(R.id.passportOverlay);
        this.s.setListener(this);
        CaptureType s2 = s();
        this.g = (CameraSourcePreview) findViewById(R.id.camera_source);
        this.g.setIsFront(s2 == CaptureType.FACE || s2 == CaptureType.VIDEO);
        this.g.setErrorListener(this);
        switch (s2) {
            case DOCUMENT:
                this.l = getDocTypeFrom(getIntent());
                this.k.setDocumentCapture();
                break;
            case FACE:
                this.k.setFaceCapture();
                this.i.setOnTouchListener(null);
                break;
            case VIDEO:
                b();
                break;
        }
        c();
        this.a.checkPermissions(this, s2);
        a(bundle);
        f();
    }

    public void onDeclinePressed() {
        onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.e.release();
        this.g.release();
        this.w.removeCallbacksAndMessages(null);
    }

    public void onDocumentUploadWithGlareWarning(final DocumentUpload documentUpload) {
        this.a.onUploadValidationError();
        setLoading(false);
        this.a.trackCaptureError(s(), true, UploadErrorType.GLARE);
        ViewUtil.setViewVisibilityWithoutAnimation(this.n, false);
        this.o.setContent(R.string.onfido_glare_detected_title, R.string.onfido_glare_detected_subtitle, null, R.drawable.onfido_warning_blue, R.color.onfido_post_capture_warning);
        this.o.animate(true);
        this.k.setListener(new Listener() {
            public void confirmed() {
                CaptureActivity.this.onValidDocumentUpload(documentUpload);
            }

            public void discarded() {
                CaptureActivity.this.m();
            }
        });
    }

    public void onDocumentUploaded(DocumentUpload documentUpload) {
        this.a.checkDocumentUploadResult(documentUpload);
    }

    public void onErrorTakingPicture() {
        a(R.string.onfido_error_dialog_title, j(), (ErrorDialogFeature.Listener) new ErrorDialogFeature.Listener() {
            public void onErrorDialogClose() {
                CaptureActivity.this.recreate();
            }
        });
    }

    public void onImageProcessingFinished() {
        this.w.postDelayed(this.x, 300);
    }

    public void onImageProcessingResult(ImageProcessingResults imageProcessingResults) {
        boolean hasGlare = imageProcessingResults.getHasGlare();
        if (this.l == DocumentType.PASSPORT && this.j.getVisibility() != 0 && !a()) {
            this.j.setVisibility(0);
        }
        if (this.l == DocumentType.PASSPORT) {
            EdgeDetectionResults edgeDetectionResults = imageProcessingResults.getEdgeDetectionResults();
            boolean hasBlur = imageProcessingResults.getHasBlur();
            a(edgeDetectionResults);
            if (hasGlare) {
                this.r.setVisibility(4);
            } else {
                this.r.updateText(getString(R.string.onfido_message_passport_capture_subtitle));
                this.r.setVisibility(0);
            }
            if (hasGlare || !edgeDetectionResults.getHasMost() || hasBlur) {
                this.j.setActivated(false);
            } else if (a()) {
                l();
            } else {
                this.r.updateText(getString(R.string.onfido_press_button_capture));
                this.j.setActivated(true);
            }
        } else {
            ViewUtil.setViewVisibilityWithoutAnimation(this.n, !hasGlare);
        }
        this.p.animate(hasGlare);
    }

    public void onIntroductionDelayFinished() {
        if (this.u.getVisibility() == 0) {
            this.u.setVisibility(8);
        }
    }

    public void onLastChallenge() {
        this.s.onLastChallenge();
    }

    public void onLayoutDrawn(RectF rectF, RectF rectF2) {
        this.G = rectF2;
        a(rectF.bottom);
        b(rectF);
        a(rectF);
        this.I = new RectData((int) (this.G.height() / this.g.getPreviewZoomFactor()), (int) (this.G.width() / this.g.getPreviewZoomFactor()), (int) ((this.G.top + ((float) this.g.getPreviewVerticalOffset())) / this.g.getPreviewZoomFactor()), (int) ((this.G.left + ((float) this.g.getPreviewHorizontalOffset())) / this.g.getPreviewZoomFactor()));
        this.H = new RectData((int) (this.F.height() / this.g.getPreviewZoomFactor()), (int) (this.F.width() / this.g.getPreviewZoomFactor()), (int) ((this.F.top + ((float) this.g.getPreviewVerticalOffset())) / this.g.getPreviewZoomFactor()), (int) ((this.F.left + ((float) this.g.getPreviewHorizontalOffset())) / this.g.getPreviewZoomFactor()));
        if (this.l == DocumentType.PASSPORT && a()) {
            LayoutParams layoutParams = (LayoutParams) this.j.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, getResources().getDimensionPixelOffset(R.dimen.onfido_autocapture_capture_button_margin_bottom));
            this.j.setLayoutParams(layoutParams);
            this.j.setVisibility(4);
            this.w.postDelayed(new Runnable() {
                public void run() {
                    if (!CaptureActivity.this.E) {
                        ((TextView) CaptureActivity.this.findViewById(R.id.info)).setText(CaptureActivity.this.getString(R.string.onfido_autocapture_info));
                        CaptureActivity.this.t.setState(3);
                    }
                }
            }, 1500);
        }
    }

    public void onLivePhotoUploaded(LivePhotoUpload livePhotoUpload) {
        setLoading(false);
        setResult(-1, a(livePhotoUpload.getId()));
        finish();
    }

    public void onManualFallbackDelayFinished() {
        if (!this.E && a()) {
            this.j.setVisibility(0);
            this.t.setState(5);
            this.w.postDelayed(new Runnable() {
                public void run() {
                    ((TextView) CaptureActivity.this.findViewById(R.id.info)).setText(CaptureActivity.this.getString(R.string.onfido_press_button_capture));
                    CaptureActivity.this.t.setState(3);
                }
            }, 1000);
        }
    }

    public void onNextChallenge(int i2, LivenessChallenge livenessChallenge) {
        this.s.updateInfo(livenessChallenge);
        this.a.trackChallenge(i2, livenessChallenge);
    }

    public void onNextClick() {
        this.a.issueNextChallenge();
    }

    public void onNextFrame(byte[] bArr) {
        if (i()) {
            PreviewFrameData previewFrameData = new PreviewFrameData(bArr, this.g.getPreviewHeight(), this.g.getPreviewWidth(), this.I, this.H, 0);
            this.a.onNextFrame(previewFrameData);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.g.stop();
        super.onPause();
    }

    public void onPermissionsDenied() {
        this.a.trackCameraPermission(true, Boolean.valueOf(false));
        setResult(0);
        finish();
    }

    public void onPermissionsGranted() {
        this.a.trackCameraPermission(true, Boolean.valueOf(true));
        a(s(), this.l);
        d();
        o();
        if (this.l != DocumentType.PASSPORT || !this.a.hasNativeLibrary()) {
            this.j.setVisibility(0);
            this.h.disableDynamicLayout();
            this.r.setVisibility(4);
        } else {
            this.j.setActivated(false);
        }
        if (this.a.hasNativeLibrary()) {
            this.p.setContent(R.string.onfido_glare_detected_title, R.string.onfido_glare_detected_subtitle, null, R.drawable.onfido_warning_blue, R.color.onfido_post_capture_warning);
        }
        if (this.a.shouldShowPrivacyPolicy(s(), e())) {
            this.v = new PrivacyPolicyView(this);
            this.v.setListener(this);
            ((ViewGroup) findViewById(16908290)).addView(this.v);
            if (this.l != DocumentType.PASSPORT) {
                this.v.removeLastStep();
            }
            this.v.show();
        }
        this.j.setOnClickListener(new OnClickListener() {
            public void onClick(android.view.View view) {
                CaptureActivity.this.l();
            }
        });
        this.k.setListener(this);
        b(s());
    }

    public void onPictureCaptured(byte[] bArr, int i2, int i3) {
        this.C = bArr;
        a(bArr);
        if (this.l != null) {
            float zoomFactor = this.g.getZoomFactor(i3, i2);
            CaptureFrameData captureFrameData = new CaptureFrameData(bArr, i2, i3, new RectData((int) (this.G.width() / zoomFactor), (int) (this.G.height() / zoomFactor), (int) ((this.G.left + ((float) this.g.getHorizontalOffset(i3, i2))) / zoomFactor), (int) ((this.G.top + ((float) this.g.getVerticalOffset(i3, i2))) / zoomFactor)), this.b.getExifOrientationDegrees(Exif.exifOrientationIdentifier(bArr)));
            this.a.applyPostCaptureValidations(captureFrameData, this.l, e(), getDocumentCountryFrom(getIntent()));
            return;
        }
        a(this.J);
    }

    public void onPostCaptureValidationsFinished(PostCaptureValidationResults postCaptureValidationResults) {
        a(postCaptureValidationResults);
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        this.a.handlePermissionsResult(i2, iArr);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.D && !this.E) {
            this.g.start(s() == CaptureType.VIDEO);
            this.g.setFrameCallback(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(CapturePresenter.class.getSimpleName(), this.a.getState());
    }

    public void onShutter() {
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (!this.E && !this.a.shouldShowPrivacyPolicy(s(), e())) {
            this.a.start(s(), this.l, !this.D);
            if (!getIntent().hasExtra("IS_RECREATING")) {
                a(true, this.E, s());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.a.stop();
        if (!this.B) {
            a(false, this.E, s());
        }
        if (this.g.isRecording()) {
            this.g.stopRecording();
        }
        this.t.setState(5);
    }

    public void onStorageThresholdReached() {
        this.g.finishRecording(false);
        new Builder(this).setTitle(R.string.onfido_liveness_storage_threshold_title).setMessage(R.string.onfido_liveness_storage_threshold_message).setPositiveButton(R.string.onfido_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CaptureActivity.this.onBackPressed();
            }
        }).setCancelable(false).show();
    }

    public void onUnknownCameraError(UnknownCameraException unknownCameraException) {
        Intent intent = new Intent();
        intent.putExtra("onfido_exception_result", unknownCameraException);
        setResult(-2, intent);
        finish();
    }

    public void onUploadError(UploadErrorType uploadErrorType) {
        UploadErrorType uploadErrorType2;
        switch (uploadErrorType) {
            case NETWORK:
                g();
                return;
            case DOCUMENT:
                uploadErrorType2 = UploadErrorType.DOCUMENT;
                break;
            case NO_FACE:
                uploadErrorType2 = UploadErrorType.NO_FACE;
                break;
            case MULTIPLE_FACES:
                uploadErrorType2 = UploadErrorType.MULTIPLE_FACES;
                break;
            case GENERIC:
                h();
                return;
            default:
                return;
        }
        a(uploadErrorType2);
    }

    public void onValidDocumentUpload(DocumentUpload documentUpload) {
        setLoading(false);
        Intent a2 = a(documentUpload.getId());
        a2.putExtra("doc_type", a(documentUpload.getType()));
        a2.putExtra("is_front_side", t());
        a2.putExtra("doc_country", getDocumentCountryFrom(getIntent()));
        setResult(-1, a2);
        finish();
    }

    public void onVideoCanceled() {
        setResult(0, new Intent());
        finish();
    }

    public void onVideoCaptured(boolean z2, String str) {
        if (z2) {
            Intent intent = new Intent();
            intent.putExtra("video_path", str);
            intent.putExtra("onfido_liveness_challenges", this.a.getUploadChallengesList());
            setResult(-1, intent);
            finish();
            return;
        }
        this.a.deleteFile(str);
    }

    public void onVideoTimeoutExceeded() {
        new Builder(this).setPositiveButton(R.string.onfido_retry, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                CaptureActivity.this.recreate();
            }
        }).setTitle(R.string.onfido_liveness_timeout_exceeded_title).setMessage(R.string.onfido_liveness_timeout_exceeded_description).setCancelable(false).show();
    }

    public void recreate() {
        Intent intent = new Intent();
        intent.putExtra("IS_RECREATING", true);
        intent.putExtra("onfido_intent_extra", getIntent());
        this.B = true;
        setResult(433, intent);
        finish();
    }
}
