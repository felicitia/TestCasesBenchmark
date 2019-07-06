package com.etsy.android.ui.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.a.p;
import com.etsy.android.lib.core.f;
import com.etsy.android.lib.core.f.c;
import com.etsy.android.lib.core.g;
import com.etsy.android.lib.core.k;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.Review;
import com.etsy.android.lib.models.Transaction;
import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyMultipartEntity;
import com.etsy.android.lib.requests.EtsyMultipartEntity.AsyncMultipartRequestCallback;
import com.etsy.android.lib.requests.EtsyRequest;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import com.etsy.android.lib.requests.apiv3.LeaveReviewRequest;
import com.etsy.android.lib.util.CameraHelper;
import com.etsy.android.lib.util.CameraHelper.b;
import com.etsy.android.lib.util.aj;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.a.a;
import com.etsy.android.uikit.adapter.AbstractContextRecyclerViewAdapter;
import com.etsy.android.uikit.image.CropImageUtil.Options;
import com.etsy.android.uikit.ui.dialog.DialogActivity;
import com.etsy.android.uikit.util.AnimationUtil;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.i;
import com.etsy.android.uikit.view.ClickableImageView;
import com.etsy.android.uikit.view.RatingIconView;
import com.etsy.android.util.BOEOptionsMenuItemHelper;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.parceler.d;

public class LeaveFeedbackFragment extends EtsyFragment implements AsyncMultipartRequestCallback<Review, LeaveReviewRequest<Review>>, b {
    private static final String CROPPED_IMAGE_FILE_URI = "cropped_image_file_uri";
    private static final int[] EXAMPLE_PHOTO_DROP_DELAYS = {800, 700, 0, AbstractContextRecyclerViewAdapter.VIEW_TYPE_HEADER, 200};
    private static final int EXAMPLE_PHOTO_DROP_START_DELAY = 2000;
    private static final int EXAMPLE_PHOTO_WIGGLE_START_DELAY = 1200;
    private static final String IMAGE_FILE_URI = "image_file_uri";
    private static final int INTRO_STAR_DURATION = 125;
    private static final float INTRO_STAR_SCALE_END = 1.5f;
    private static final float INTRO_STAR_SCALE_START = 1.0f;
    private static final int INTRO_STAR_START_DELAY = 1500;
    private static final String LAST_RATING = "last_rating";
    private static final int PHOTO_MIN_HEIGHT = 640;
    private static final int PHOTO_MIN_WIDTH = 640;
    private static final String REMOVE_PHOTO = "remove_photo";
    private static final String SHOP_NAME = "shop_name";
    private static final String TRANSACTION = "transaction";
    private static final String USER = "user";
    private static final Options options = new Options().setMinHeight(640).setMinWidth(640);
    /* access modifiers changed from: private */
    public View mAddPhotoHint;
    /* access modifiers changed from: private */
    public TextView mAddPhotoLaterHint;
    private a mAnimationManager;
    /* access modifiers changed from: private */
    public ClickableImageView mCameraButton;
    /* access modifiers changed from: private */
    public ViewGroup mCameraButtonContainer;
    private CameraHelper mCameraHelper;
    /* access modifiers changed from: private */
    public View mContactShopClickArea;
    private final OnClickListener mContactShopClickListener = new TrackingOnClickListener() {
        public void onPreTrack() {
            addEventTrackedObjects(LeaveFeedbackFragment.this.mUser, LeaveFeedbackFragment.this.mTransaction);
        }

        public void onViewClick(View view) {
            if (LeaveFeedbackFragment.this.mUser != null) {
                Bundle bundle = new Bundle();
                bundle.putString(ResponseConstants.USERNAME, LeaveFeedbackFragment.this.mUser.getLoginName());
                e.a(LeaveFeedbackFragment.this.getActivity()).a().e(bundle);
            }
        }
    };
    private File mCroppedFile;
    private Uri mCroppedFileUri;
    /* access modifiers changed from: private */
    public boolean mEditing;
    private View mErrorView;
    /* access modifiers changed from: private */
    public ImageView mExamplePhoto1;
    /* access modifiers changed from: private */
    public ImageView mExamplePhoto2;
    /* access modifiers changed from: private */
    public ImageView mExamplePhoto3;
    /* access modifiers changed from: private */
    public ImageView mExamplePhoto4;
    /* access modifiers changed from: private */
    public ImageView mExamplePhoto5;
    /* access modifiers changed from: private */
    public File mFile;
    /* access modifiers changed from: private */
    public Uri mFileUri;
    private ImageView mImgListing;
    /* access modifiers changed from: private */
    public i mInputHelper;
    private boolean mIsDialogActivity;
    /* access modifiers changed from: private */
    public float mLastRating;
    /* access modifiers changed from: private */
    public ViewGroup mLayoutDetailsSection;
    private View mLoadingView;
    private int mMinimumWordsFromConfig;
    /* access modifiers changed from: private */
    public OnRatingBarChangeListener mOnRatingChanged = new OnRatingBarChangeListener() {
        /* JADX WARNING: Removed duplicated region for block: B:18:0x00f7  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0101  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x010c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onRatingChanged(android.widget.RatingBar r5, float r6, boolean r7) {
            /*
                r4 = this;
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                float r5 = r5.mLastRating
                r7 = 0
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                r0 = 8
                if (r5 != 0) goto L_0x0016
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r5 = r5.mTxtTapStarsHint
                r5.setVisibility(r0)
            L_0x0016:
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r5 = r5.mTxtRatingDesc
                r1 = 0
                r5.setVisibility(r1)
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r5 = r5.mTxtError
                r5.setVisibility(r0)
                r5 = 1084227584(0x40a00000, float:5.0)
                int r2 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
                if (r2 != 0) goto L_0x0080
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.EditText r2 = r2.mTxtMessage
                r3 = 2131756925(0x7f10077d, float:1.9144771E38)
                r2.setHint(r3)
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                float r2 = r2.mLastRating
                int r7 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r7 != 0) goto L_0x004d
                r7 = 1
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r2.showExamplePhotosAnimation()
                goto L_0x00f3
            L_0x004d:
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mAddPhotoLaterHint
                r7.setVisibility(r1)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mTxtRatingDesc
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r3 = 2131756932(0x7f100784, float:1.9144785E38)
                java.lang.String r2 = r2.getString(r3)
                r7.setText(r2)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mTxtReviewHint
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r3 = 2131756918(0x7f100776, float:1.9144757E38)
                java.lang.String r2 = r2.getString(r3)
                r7.setText(r2)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r7.hideBadRatingAdvice()
                goto L_0x00f2
            L_0x0080:
                int r7 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
                r2 = 2131756924(0x7f10077c, float:1.914477E38)
                if (r7 >= 0) goto L_0x00c9
                r7 = 1077936128(0x40400000, float:3.0)
                int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                if (r7 <= 0) goto L_0x00c9
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mAddPhotoLaterHint
                r7.setVisibility(r0)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.EditText r7 = r7.mTxtMessage
                r7.setHint(r2)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mTxtRatingDesc
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r3 = 2131756931(0x7f100783, float:1.9144783E38)
                java.lang.String r2 = r2.getString(r3)
                r7.setText(r2)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mTxtReviewHint
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r3 = 2131756935(0x7f100787, float:1.9144792E38)
                java.lang.String r2 = r2.getString(r3)
                r7.setText(r2)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r7.hideBadRatingAdvice()
                goto L_0x00f2
            L_0x00c9:
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mAddPhotoLaterHint
                r7.setVisibility(r0)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.EditText r7 = r7.mTxtMessage
                r7.setHint(r2)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.widget.TextView r7 = r7.mTxtRatingDesc
                com.etsy.android.ui.user.LeaveFeedbackFragment r2 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r3 = 2131756933(0x7f100785, float:1.9144788E38)
                java.lang.String r2 = r2.getString(r3)
                r7.setText(r2)
                com.etsy.android.ui.user.LeaveFeedbackFragment r7 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r7.showBadRatingAdvice()
            L_0x00f2:
                r7 = r1
            L_0x00f3:
                int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
                if (r5 != 0) goto L_0x0101
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.view.ViewGroup r5 = r5.mCameraButtonContainer
                r5.setVisibility(r1)
                goto L_0x010a
            L_0x0101:
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.view.ViewGroup r5 = r5.mCameraButtonContainer
                r5.setVisibility(r0)
            L_0x010a:
                if (r7 != 0) goto L_0x0123
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.view.ViewGroup r5 = r5.mLayoutDetailsSection
                int r5 = r5.getVisibility()
                if (r5 == 0) goto L_0x011e
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r5.showMessageBoxAfterLayout()
                goto L_0x0123
            L_0x011e:
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r5.showMessageBox()
            L_0x0123:
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                android.view.ViewGroup r5 = r5.mLayoutDetailsSection
                r5.setVisibility(r1)
                com.etsy.android.ui.user.LeaveFeedbackFragment r5 = com.etsy.android.ui.user.LeaveFeedbackFragment.this
                r5.mLastRating = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.etsy.android.ui.user.LeaveFeedbackFragment.AnonymousClass6.onRatingChanged(android.widget.RatingBar, float, boolean):void");
        }
    };
    /* access modifiers changed from: private */
    public RatingIconView mRatingIntro;
    /* access modifiers changed from: private */
    public RatingBar mRatingPicker;
    /* access modifiers changed from: private */
    public boolean mRemovePhoto;
    /* access modifiers changed from: private */
    public View mRemovePhotoButton;
    private final OnClickListener mRemovePhotoClickListener = new TrackingOnClickListener() {
        public void onPreTrack() {
            addEventTrackedObjects(LeaveFeedbackFragment.this.mUser, LeaveFeedbackFragment.this.mTransaction);
        }

        public void onViewClick(View view) {
            LeaveFeedbackFragment.this.mCameraButton.setOnClickListener(LeaveFeedbackFragment.this.mStartCameraClickListener);
            LeaveFeedbackFragment.this.mCameraButton.setImageResource(R.drawable.btn_blue_v2);
            LeaveFeedbackFragment.this.mRemovePhotoButton.setVisibility(8);
            LeaveFeedbackFragment.this.mAddPhotoHint.setVisibility(0);
            LeaveFeedbackFragment.this.mAddPhotoLaterHint.setVisibility(0);
            LeaveFeedbackFragment.this.mFileUri = null;
            LeaveFeedbackFragment.this.mFile = null;
            if (LeaveFeedbackFragment.this.mEditing) {
                LeaveFeedbackFragment.this.mRemovePhoto = true;
            }
        }
    };
    /* access modifiers changed from: private */
    public ScrollView mScrollView;
    /* access modifiers changed from: private */
    public String mShopName;
    private boolean mShowReviewMenuItem = true;
    /* access modifiers changed from: private */
    public final OnClickListener mStartCameraClickListener = new TrackingOnClickListener() {
        public void onPreTrack() {
            addEventTrackedObjects(LeaveFeedbackFragment.this.mUser, LeaveFeedbackFragment.this.mTransaction);
        }

        public void onViewClick(View view) {
            LeaveFeedbackFragment.this.startImagePicker();
        }
    };
    private Button mSubmitButton;
    private final OnClickListener mSubmitClickListener = new TrackingOnClickListener() {
        public void onPreTrack() {
            addEventTrackedObjects(LeaveFeedbackFragment.this.mUser, LeaveFeedbackFragment.this.mTransaction);
        }

        public void onViewClick(View view) {
            LeaveFeedbackFragment.this.mTxtError.setVisibility(8);
            if (LeaveFeedbackFragment.this.isValidReview()) {
                LeaveFeedbackFragment.this.submitReview();
            }
        }
    };
    /* access modifiers changed from: private */
    public Transaction mTransaction;
    /* access modifiers changed from: private */
    public TextView mTxtError;
    private TextView mTxtListingTitle;
    /* access modifiers changed from: private */
    public EditText mTxtMessage;
    /* access modifiers changed from: private */
    public TextView mTxtRatingDesc;
    /* access modifiers changed from: private */
    public TextView mTxtReviewHint;
    private TextView mTxtShopName;
    /* access modifiers changed from: private */
    public TextView mTxtTapStarsHint;
    /* access modifiers changed from: private */
    public User mUser;
    private ViewGroup mView;

    private static class GetTransactionRequest extends EtsyRequest<Transaction> {
        public GetTransactionRequest(EtsyId etsyId) {
            StringBuilder sb = new StringBuilder();
            sb.append("/transactions/");
            sb.append(etsyId);
            super(sb.toString(), RequestMethod.GET, Transaction.class);
        }
    }

    public Object onPreImageSave() {
        return null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        this.mIsDialogActivity = getActivity() instanceof DialogActivity;
        setHasOptionsMenu(!this.mIsDialogActivity);
        this.mTransaction = (Transaction) arguments.getSerializable(TRANSACTION);
        this.mShopName = arguments.getString("shop_name");
        this.mUser = (User) arguments.getSerializable(USER);
        this.mInputHelper = new i(getActivity());
        this.mMinimumWordsFromConfig = getConfigMap().d(com.etsy.android.lib.config.b.g);
        this.mCameraHelper = new CameraHelper(getActivity().getApplicationContext(), bundle, this);
        if (bundle != null) {
            this.mLastRating = bundle.getFloat(LAST_RATING);
            String string = bundle.getString(IMAGE_FILE_URI);
            if (string != null) {
                this.mFileUri = Uri.parse(string);
                this.mFile = new File(this.mFileUri.getPath());
            }
            String string2 = bundle.getString(CROPPED_IMAGE_FILE_URI);
            if (string2 != null) {
                this.mCroppedFileUri = Uri.parse(string2);
                this.mCroppedFile = new File(this.mCroppedFileUri.getPath());
            }
            this.mRemovePhoto = bundle.getBoolean(REMOVE_PHOTO, false);
            this.mTransaction = (Transaction) d.a(bundle.getParcelable(TRANSACTION));
            this.mUser = (User) d.a(bundle.getParcelable(USER));
            this.mShopName = bundle.getString("shop_name");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mAnimationManager = new a(getActivity());
        this.mView = (ViewGroup) layoutInflater.inflate(R.layout.fragment_leave_review2, viewGroup, false);
        this.mScrollView = (ScrollView) this.mView.getChildAt(0);
        this.mLoadingView = this.mView.findViewById(R.id.loading_view);
        this.mErrorView = this.mView.findViewById(R.id.no_internet);
        if (this.mIsDialogActivity) {
            this.mView.setPadding(0, 0, 0, 0);
            this.mScrollView.getChildAt(0).setBackgroundResource(R.color.sk_white);
            if (getActivity() instanceof LeaveFeedbackDialogActivity) {
                ((ViewGroup) this.mScrollView.getChildAt(0)).addView(createSubmitButton());
            }
        }
        aj.a((ViewGroup) this.mScrollView);
        this.mTxtMessage = (EditText) this.mView.findViewById(R.id.review_message);
        this.mRatingPicker = (RatingBar) this.mView.findViewById(R.id.rating);
        this.mTxtListingTitle = (TextView) this.mView.findViewById(R.id.txt_listing_title);
        this.mImgListing = (ImageView) this.mView.findViewById(R.id.img_listing);
        this.mTxtMessage.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.equals(LeaveFeedbackFragment.this.mTxtMessage) && z) {
                    LeaveFeedbackFragment.this.mScrollView.setLayoutTransition(null);
                }
            }
        });
        this.mTxtError = (TextView) this.mView.findViewById(R.id.review_error);
        this.mLayoutDetailsSection = (ViewGroup) this.mView.findViewById(R.id.section_add_review_details);
        this.mContactShopClickArea = this.mView.findViewById(R.id.review_contact_shop_click_area);
        this.mTxtShopName = (TextView) this.mView.findViewById(R.id.txt_shop_name);
        this.mCameraButtonContainer = (ViewGroup) this.mView.findViewById(R.id.button_add_image_container);
        this.mCameraButton = (ClickableImageView) this.mView.findViewById(R.id.button_add_image);
        this.mTxtTapStarsHint = (TextView) this.mView.findViewById(R.id.txt_tap_stars_hint);
        this.mTxtRatingDesc = (TextView) this.mView.findViewById(R.id.txt_review_praise);
        this.mTxtReviewHint = (TextView) this.mView.findViewById(R.id.review_add_review_hint);
        this.mRemovePhotoButton = this.mView.findViewById(R.id.review_remove_photo);
        this.mAddPhotoHint = this.mView.findViewById(R.id.review_add_image_hint);
        this.mRatingIntro = (RatingIconView) this.mView.findViewById(R.id.rating_intro);
        aj.a((ViewGroup) this.mCameraButton.getParent());
        this.mExamplePhoto1 = (ImageView) this.mView.findViewById(R.id.example_appreciation_photo_1);
        this.mExamplePhoto2 = (ImageView) this.mView.findViewById(R.id.example_appreciation_photo_2);
        this.mExamplePhoto3 = (ImageView) this.mView.findViewById(R.id.example_appreciation_photo_3);
        this.mExamplePhoto4 = (ImageView) this.mView.findViewById(R.id.example_appreciation_photo_4);
        this.mExamplePhoto5 = (ImageView) this.mView.findViewById(R.id.example_appreciation_photo_5);
        this.mAddPhotoLaterHint = (TextView) this.mView.findViewById(R.id.review_add_photo_later);
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mTransaction != null) {
            setTransactionData();
            return;
        }
        showLoading();
        fetchTransaction();
    }

    private void fetchTransaction() {
        getRequestQueue().a((Object) this, (g<Result>) com.etsy.android.lib.core.e.a((EtsyRequest<Result>) new GetTransactionRequest<Result>((EtsyId) getArguments().getSerializable(ResponseConstants.TRANSACTION_ID))).b("Seller(login_name,user_id)/Shops(shop_name),MainImage(listing_image_id,url_fullxfull,url_75x75,full_height,full_width),GiftCardDesign(design_id,url_150x119,url_280x166,url_560x332,url_150x119),UserReview/AppreciationPhoto(url_fullxfull,is_seller_approved,status)").a((c<Result>) new c<Transaction>() {
            public void a(List<Transaction> list, int i, k<Transaction> kVar) {
                Transaction transaction = (Transaction) list.get(0);
                LeaveFeedbackFragment.this.mTransaction = transaction;
                LeaveFeedbackFragment.this.mUser = transaction.getSeller();
                LeaveFeedbackFragment.this.mShopName = transaction.getSeller().getMainShop().getShopName();
                if (LeaveFeedbackFragment.this.getActivity() == null) {
                    return;
                }
                if (v.a().l().equals(LeaveFeedbackFragment.this.mTransaction.getBuyerUserId())) {
                    LeaveFeedbackFragment.this.setTransactionData();
                    LeaveFeedbackFragment.this.showContent();
                    return;
                }
                LeaveFeedbackFragment.this.showError();
            }
        }).a((f.a) new f.a() {
            public void a(k kVar) {
                if (LeaveFeedbackFragment.this.getActivity() != null) {
                    LeaveFeedbackFragment.this.showError();
                }
            }
        }).a((f.b) new f.b() {
            public void a(int i, String str, k kVar) {
                if (LeaveFeedbackFragment.this.getActivity() != null) {
                    LeaveFeedbackFragment.this.showError();
                }
            }
        }).a());
    }

    /* access modifiers changed from: private */
    public void setTransactionData() {
        this.mEditing = this.mTransaction.getReview() != null;
        if (this.mEditing && this.mLastRating == 0.0f) {
            this.mLastRating = (float) this.mTransaction.getReview().getRating();
        }
        populateReview();
        populateListing();
        this.mContactShopClickArea.setOnClickListener(this.mContactShopClickListener);
        this.mTxtError.setOnClickListener(new TrackingOnClickListener(this.mTransaction, this.mUser) {
            public void onViewClick(View view) {
                view.setVisibility(8);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mCameraHelper.onActivityResult(i, i2, intent);
    }

    public void onCreateOptionsMenuWithIcons(Menu menu, MenuInflater menuInflater) {
        FragmentActivity activity = getActivity();
        if (this.mShowReviewMenuItem && activity != null) {
            menuInflater.inflate(R.menu.review_action_bar, menu);
            MenuItem findItem = menu.findItem(R.id.review_submit);
            View a = BOEOptionsMenuItemHelper.a(activity, R.color.text_disabled_selector_blue, R.string.submit);
            findItem.setActionView(a);
            a.setOnClickListener(this.mSubmitClickListener);
        }
    }

    public void onDestroy() {
        this.mCameraHelper.removeCallback();
        this.mCameraHelper = null;
        super.onDestroy();
    }

    public void onDestroyView() {
        AnimationUtil.a(this.mRatingIntro, this.mExamplePhoto1, this.mExamplePhoto2, this.mExamplePhoto3, this.mExamplePhoto4, this.mExamplePhoto5, this.mTxtReviewHint);
        this.mAnimationManager.a();
        if (this.mInputHelper != null) {
            this.mInputHelper.a();
        }
        super.onDestroyView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putFloat(LAST_RATING, this.mLastRating);
        if (this.mFileUri != null) {
            bundle.putString(IMAGE_FILE_URI, this.mFileUri.toString());
        }
        if (this.mCroppedFile != null) {
            bundle.putString(CROPPED_IMAGE_FILE_URI, this.mCroppedFileUri.toString());
        }
        bundle.putBoolean(REMOVE_PHOTO, this.mRemovePhoto);
        if (this.mTransaction != null) {
            bundle.putParcelable(TRANSACTION, d.a(this.mTransaction));
        }
        if (this.mUser != null) {
            bundle.putParcelable(USER, d.a(this.mUser));
        }
        if (!TextUtils.isEmpty(this.mShopName)) {
            bundle.putString("shop_name", this.mShopName);
        }
    }

    private Button createSubmitButton() {
        this.mSubmitButton = new Button(getActivity());
        this.mSubmitButton.setOnClickListener(this.mSubmitClickListener);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.gravity = 80;
        layoutParams.setMargins(0, (int) getResources().getDimension(R.dimen.margin_medium_large), 0, 0);
        this.mSubmitButton.setLayoutParams(layoutParams);
        this.mSubmitButton.setBackgroundResource(R.drawable.btn_blue_square_v2);
        this.mSubmitButton.setText(getString(R.string.review_submit_menu));
        this.mSubmitButton.setTextAppearance(getActivity(), p.TextWhite_Large_Bold);
        return this.mSubmitButton;
    }

    private void animateStarIntro() {
        this.mRatingPicker.setIsIndicator(true);
        this.mRatingIntro.setRating(5.0f);
        this.mRatingIntro.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                LeaveFeedbackFragment.this.mRatingIntro.getViewTreeObserver().removeOnPreDrawListener(this);
                LeaveFeedbackFragment.this.mRatingIntro.getLayoutParams().width = LeaveFeedbackFragment.this.mRatingPicker.getLayoutParams().width;
                LeaveFeedbackFragment.this.mRatingIntro.getLayoutParams().height = LeaveFeedbackFragment.this.mRatingPicker.getLayoutParams().height;
                LeaveFeedbackFragment.this.mRatingIntro.setBackgroundResource(17170445);
                for (int i = 0; i < LeaveFeedbackFragment.this.mRatingIntro.getChildCount(); i++) {
                    LeaveFeedbackFragment.this.animateStarAt(i);
                }
                LeaveFeedbackFragment.this.hideStarIntro();
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void animateStarAt(int i) {
        ImageView imageView = (ImageView) this.mRatingIntro.getChildAt(i);
        imageView.setAlpha(0.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{1.0f, INTRO_STAR_SCALE_END});
        ofFloat.setDuration(125);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{1.0f, INTRO_STAR_SCALE_END});
        ofFloat2.setDuration(125);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, "scaleX", new float[]{INTRO_STAR_SCALE_END, 1.0f});
        ofFloat3.setDuration(125);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, "scaleY", new float[]{INTRO_STAR_SCALE_END, 1.0f});
        ofFloat4.setDuration(125);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageView, "alpha", new float[]{0.0f, 1.0f});
        ofFloat5.setDuration(125);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat3).with(ofFloat4);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setStartDelay((long) (1500 + (i * 100)));
        animatorSet2.play(ofFloat).with(ofFloat2).with(ofFloat5).before(animatorSet);
        this.mAnimationManager.a(animatorSet2);
        animatorSet2.start();
    }

    /* access modifiers changed from: private */
    public void hideStarIntro() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mRatingIntro, "alpha", new float[]{1.0f, 0.0f});
        ofFloat.setDuration(300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat).after((long) (1750 + (this.mRatingIntro.getChildCount() * 100)));
        animatorSet.start();
        this.mAnimationManager.a(animatorSet);
        ofFloat.addListener(new HardwareAnimatorListener(this.mRatingIntro) {
            public void onAnimationEnd(Animator animator) {
                if (LeaveFeedbackFragment.this.mRatingPicker != null) {
                    LeaveFeedbackFragment.this.mRatingPicker.setIsIndicator(false);
                    LeaveFeedbackFragment.this.mRatingPicker.setOnRatingBarChangeListener(LeaveFeedbackFragment.this.mOnRatingChanged);
                }
            }
        });
    }

    private void setCameraButtonFromFile(File file) {
        this.mCameraButton.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
    }

    private void populateCameraButton() {
        Review review = this.mTransaction.getReview();
        this.mRemovePhotoButton.setOnClickListener(this.mRemovePhotoClickListener);
        if ((review == null || !review.hasAppreciationPhoto() || this.mRemovePhoto) && this.mFile == null && this.mCroppedFile == null) {
            this.mRemovePhotoButton.setVisibility(8);
            this.mAddPhotoHint.setVisibility(0);
            this.mCameraButton.setOnClickListener(this.mStartCameraClickListener);
            return;
        }
        if (this.mCroppedFile != null) {
            setCameraButtonFromFile(this.mCroppedFile);
        } else if (this.mFile != null) {
            setCameraButtonFromFile(this.mFile);
        } else if (review.hasAppreciationPhoto()) {
            getImageBatch().a(review.getAppreciationPhoto().getImageUrl(), (ImageView) this.mCameraButton);
        }
        this.mRemovePhotoButton.setVisibility(0);
        this.mAddPhotoHint.setVisibility(8);
        this.mAddPhotoLaterHint.setVisibility(8);
        this.mCameraButton.setOnClickListener(null);
    }

    private void populateReview() {
        Review review = this.mTransaction.getReview();
        if (review != null) {
            this.mTxtMessage.setText(review.getReviewMessage());
            this.mRatingPicker.setRating((float) review.getRating());
            this.mTxtMessage.setVisibility(0);
        } else if (this.mLastRating == 0.0f) {
            this.mTxtMessage.setVisibility(8);
        }
        populateCameraButton();
        if (review != null || this.mLastRating > 0.0f) {
            float rating = review != null ? (float) review.getRating() : this.mLastRating;
            this.mLayoutDetailsSection.setVisibility(0);
            this.mTxtRatingDesc.setVisibility(0);
            this.mTxtTapStarsHint.setVisibility(8);
            if (!this.mTransaction.isFeedbackMutable()) {
                configureForImmutableReview(review);
                return;
            }
            if (rating < 5.0f) {
                this.mCameraButtonContainer.setVisibility(8);
                this.mTxtReviewHint.setText(getString(R.string.review_review_item_hint));
            } else {
                if (review == null || !review.hasAppreciationPhoto()) {
                    this.mAddPhotoLaterHint.setVisibility(0);
                } else {
                    this.mAddPhotoLaterHint.setVisibility(8);
                }
                this.mTxtReviewHint.setText(getString(R.string.review_add_photo_hint));
            }
            if (rating <= 3.0f) {
                this.mContactShopClickArea.setVisibility(0);
                this.mTxtReviewHint.setVisibility(8);
            }
            this.mRatingIntro.setVisibility(8);
            this.mRatingPicker.setOnRatingBarChangeListener(this.mOnRatingChanged);
        } else {
            this.mLayoutDetailsSection.setVisibility(8);
            this.mTxtRatingDesc.setVisibility(8);
            this.mTxtTapStarsHint.setVisibility(0);
            animateStarIntro();
        }
    }

    private void configureForImmutableReview(Review review) {
        this.mTxtMessage.setFocusable(false);
        this.mTxtMessage.setEnabled(false);
        this.mTxtMessage.setHint(null);
        if (!this.mIsDialogActivity) {
            configureSubmitMenuItem(false);
        } else if (this.mSubmitButton != null) {
            this.mSubmitButton.setVisibility(8);
        }
        this.mTxtReviewHint.setVisibility(8);
        this.mRatingPicker.setIsIndicator(true);
        if (!review.hasAppreciationPhoto()) {
            this.mCameraButtonContainer.setVisibility(8);
        } else {
            this.mRemovePhotoButton.setVisibility(8);
        }
    }

    private void populateListing() {
        this.mTxtListingTitle.setText(this.mTransaction.getTitle());
        if (this.mShopName != null) {
            this.mTxtShopName.setText(getString(R.string.review_listing_item_from, this.mShopName));
            this.mTxtShopName.setVisibility(0);
        } else {
            this.mTxtShopName.setVisibility(8);
        }
        String str = null;
        if (!(this.mTransaction.getMainImage() == null || this.mTransaction.getMainImage().getUrl75x75() == null)) {
            str = this.mTransaction.getMainImage().getUrl75x75();
        }
        getImageBatch().a(str, this.mImgListing, getResources().getDimensionPixelSize(R.dimen.listing_thumbnail_width), getResources().getDimensionPixelSize(R.dimen.listing_thumbnail_height));
    }

    /* access modifiers changed from: private */
    public void showMessageBox() {
        if (this.mTxtMessage.getVisibility() == 8) {
            this.mTxtMessage.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void showMessageBoxAfterLayout() {
        if (this.mScrollView.getLayoutTransition() == null) {
            showMessageBox();
        } else {
            this.mScrollView.getLayoutTransition().getAnimator(4).addListener(new HardwareAnimatorListener(this.mScrollView) {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (!(LeaveFeedbackFragment.this.mScrollView == null || LeaveFeedbackFragment.this.mScrollView.getLayoutTransition() == null)) {
                        LeaveFeedbackFragment.this.mScrollView.getLayoutTransition().getAnimator(4).removeListener(this);
                    }
                    LeaveFeedbackFragment.this.showMessageBox();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showBadRatingAdvice() {
        if (this.mUser != null && this.mContactShopClickArea.getVisibility() != 0) {
            AnimationUtil.b(this.mTxtReviewHint, 200, new HardwareAnimatorListener(this.mTxtReviewHint) {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    LeaveFeedbackFragment.this.mTxtReviewHint.setVisibility(8);
                    AnimationUtil.a(LeaveFeedbackFragment.this.mContactShopClickArea, 200);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void hideBadRatingAdvice() {
        if (this.mContactShopClickArea.getVisibility() != 8) {
            AnimationUtil.b(this.mContactShopClickArea, 200, new HardwareAnimatorListener(this.mContactShopClickArea) {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    LeaveFeedbackFragment.this.mContactShopClickArea.setVisibility(8);
                    AnimationUtil.a((View) LeaveFeedbackFragment.this.mTxtReviewHint, 200);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void finishReview(Review review) {
        if (review == null) {
            review = new Review((int) this.mRatingPicker.getRating(), this.mTxtMessage.getText().toString().trim());
        }
        this.mTransaction.setReview(review);
        if (getActivity() != null) {
            i.a((Context) getActivity());
            Intent intent = new Intent();
            intent.putExtra(TRANSACTION, this.mTransaction);
            getActivity().setResult(411, intent);
            com.etsy.android.uikit.util.e.b(getActivity().getSupportFragmentManager(), e.a(getActivity()));
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isValidReview() {
        int rating = (int) this.mRatingPicker.getRating();
        int length = this.mTxtMessage.getText().toString().trim().split("\\s+").length;
        if (rating < 1 || rating > 5) {
            this.mInputHelper.a(this.mTxtError, (int) R.string.error_review_stars);
            return false;
        } else if (rating >= 5 || length >= this.mMinimumWordsFromConfig) {
            return true;
        } else {
            this.mInputHelper.a(this.mTxtError, (int) R.string.error_review_words);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    public void submitReview() {
        int rating = (int) this.mRatingPicker.getRating();
        String trim = this.mTxtMessage.getText().toString().trim();
        LeaveReviewRequest leaveReviewRequest = new LeaveReviewRequest("/reviews", RequestMethod.POST, Review.class);
        leaveReviewRequest.setV3Scope(APIv3Scope.MEMBER);
        try {
            EtsyMultipartEntity etsyMultipartEntity = new EtsyMultipartEntity();
            etsyMultipartEntity.addPart(ResponseConstants.RATING, new StringBody(Integer.toString(rating)));
            etsyMultipartEntity.addPart("review", new StringBody(trim, Charset.forName("UTF-8")));
            etsyMultipartEntity.addPart(ResponseConstants.TRANSACTION_ID, new StringBody(this.mTransaction.getTransactionId().getId()));
            if (this.mCroppedFile != null) {
                etsyMultipartEntity.addPart(ResponseConstants.IMAGE, new FileBody(this.mCroppedFile, "jpeg"));
            } else if (this.mFile != null) {
                etsyMultipartEntity.addPart(ResponseConstants.IMAGE, new FileBody(this.mFile, "jpeg"));
            } else if (this.mRemovePhoto) {
                etsyMultipartEntity.addPart("remove_image", new StringBody(Integer.toString(1)));
            }
            etsyMultipartEntity.createMultipartAsync(leaveReviewRequest, this);
        } catch (UnsupportedEncodingException unused) {
            onRequestCreationFailed();
        }
    }

    /* access modifiers changed from: private */
    public void showExamplePhotosAnimation() {
        this.mLayoutDetailsSection.setVisibility(0);
        this.mExamplePhoto1.setVisibility(0);
        this.mExamplePhoto2.setVisibility(0);
        this.mExamplePhoto3.setVisibility(0);
        this.mExamplePhoto4.setVisibility(0);
        this.mExamplePhoto5.setVisibility(0);
        this.mScrollView.setLayoutTransition(null);
        this.mScrollView.post(new Runnable() {
            public void run() {
                LeaveFeedbackFragment.this.mScrollView.smoothScrollTo(0, LeaveFeedbackFragment.this.mScrollView.getBottom());
            }
        });
        int width = ((((this.mView.getWidth() - (getResources().getDimensionPixelSize(R.dimen.margin_medium_large) * 4)) - this.mView.getPaddingLeft()) - this.mView.getPaddingRight()) - (getResources().getDimensionPixelSize(R.dimen.card_shadow_padding) * 2)) / 3;
        this.mExamplePhoto1.getLayoutParams().width = width;
        this.mExamplePhoto1.getLayoutParams().height = width;
        this.mExamplePhoto2.getLayoutParams().width = width;
        this.mExamplePhoto2.getLayoutParams().height = width;
        this.mExamplePhoto3.getLayoutParams().width = width;
        this.mExamplePhoto3.getLayoutParams().height = width;
        this.mExamplePhoto4.getLayoutParams().width = width;
        this.mExamplePhoto4.getLayoutParams().height = width;
        this.mExamplePhoto5.getLayoutParams().width = width;
        this.mExamplePhoto5.getLayoutParams().height = width;
        this.mCameraButtonContainer.getLayoutParams().width = width;
        this.mCameraButtonContainer.getLayoutParams().height = width;
        ((RelativeLayout.LayoutParams) this.mAddPhotoHint.getLayoutParams()).addRule(13);
        this.mAnimationManager.a(AnimationUtil.d(this.mExamplePhoto1, EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.a(AnimationUtil.d(this.mExamplePhoto2, EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.a(AnimationUtil.d(this.mExamplePhoto3, EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.a(AnimationUtil.d(this.mExamplePhoto4, EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mAnimationManager.a(AnimationUtil.d(this.mExamplePhoto5, EXAMPLE_PHOTO_WIGGLE_START_DELAY));
        this.mTxtReviewHint.setText(getString(R.string.review_show_your_appreciation));
        this.mAnimationManager.a(AnimationUtil.a((View) this.mExamplePhoto1, EXAMPLE_PHOTO_DROP_DELAYS[0] + 2000, createExamplePhotosSkewAndDropListener(this.mExamplePhoto1)));
        this.mAnimationManager.a(AnimationUtil.a((View) this.mExamplePhoto2, EXAMPLE_PHOTO_DROP_DELAYS[1] + 2000, (HardwareAnimatorListener) null));
        this.mAnimationManager.a(AnimationUtil.a((View) this.mExamplePhoto3, EXAMPLE_PHOTO_DROP_DELAYS[2] + 2000, (HardwareAnimatorListener) null));
        this.mAnimationManager.a(AnimationUtil.a((View) this.mExamplePhoto4, EXAMPLE_PHOTO_DROP_DELAYS[3] + 2000, (HardwareAnimatorListener) null));
        this.mAnimationManager.a(AnimationUtil.a((View) this.mExamplePhoto5, 2000 + EXAMPLE_PHOTO_DROP_DELAYS[4], (HardwareAnimatorListener) null));
    }

    private HardwareAnimatorListener createExamplePhotosSkewAndDropListener(View view) {
        return new HardwareAnimatorListener(view) {
            public void onAnimationEnd(Animator animator) {
                if (LeaveFeedbackFragment.this.mTxtReviewHint != null) {
                    LeaveFeedbackFragment.this.mAddPhotoLaterHint.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) LeaveFeedbackFragment.this.mCameraButtonContainer.getLayoutParams();
                    layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, ((RelativeLayout.LayoutParams) LeaveFeedbackFragment.this.mTxtMessage.getLayoutParams()).bottomMargin);
                    AnimationUtil.b(LeaveFeedbackFragment.this.mTxtReviewHint, 200, LeaveFeedbackFragment.this.createExamplePhotosCleanupListener(LeaveFeedbackFragment.this.mTxtReviewHint));
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public HardwareAnimatorListener createExamplePhotosCleanupListener(final TextView textView) {
        return new HardwareAnimatorListener(textView) {
            public void onAnimationEnd(Animator animator) {
                if (textView != null) {
                    textView.setText(LeaveFeedbackFragment.this.getString(R.string.review_add_photo_hint));
                    AnimationUtil.a((View) textView, 200, (AnimatorListenerAdapter) LeaveFeedbackFragment.this.createExamplePhotosFinishedListener(textView));
                    LeaveFeedbackFragment.this.showMessageBox();
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public HardwareAnimatorListener createExamplePhotosFinishedListener(View view) {
        return new HardwareAnimatorListener(view) {
            public void onAnimationEnd(Animator animator) {
                if (LeaveFeedbackFragment.this.mExamplePhoto1 != null) {
                    LeaveFeedbackFragment.this.mLayoutDetailsSection.setLayoutAnimation(null);
                    LeaveFeedbackFragment.this.mExamplePhoto1.setVisibility(8);
                    LeaveFeedbackFragment.this.mExamplePhoto2.setVisibility(8);
                    LeaveFeedbackFragment.this.mExamplePhoto3.setVisibility(8);
                    LeaveFeedbackFragment.this.mExamplePhoto4.setVisibility(8);
                    LeaveFeedbackFragment.this.mExamplePhoto5.setVisibility(8);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void startImagePicker() {
        if (getConfigMap().c(com.etsy.android.lib.config.b.aS)) {
            this.mCameraHelper.startImagePickerForCrop((Fragment) this, (int) R.string.choose_image);
        } else {
            this.mCameraHelper.startImagePicker((Fragment) this, (int) R.string.choose_image);
        }
    }

    public void onNoAvailableActivities() {
        aj.b(getContext(), (int) R.string.no_available_chooser);
    }

    public void onImageSaveSuccess(Object obj, Bitmap bitmap, File file) {
        this.mFile = file;
        this.mFileUri = Uri.fromFile(this.mFile);
        this.mRemovePhoto = false;
        this.mCameraButton.setImageBitmap(bitmap);
        this.mCameraButton.setOnClickListener(null);
        this.mRemovePhotoButton.setVisibility(0);
        this.mAddPhotoHint.setVisibility(8);
        this.mAddPhotoLaterHint.setVisibility(8);
    }

    public void onImageSaveFail(Object obj, File file) {
        if (!isDetached()) {
            this.mInputHelper.a(this.mTxtError, getString(R.string.camera_error_creating_file));
        }
    }

    public void onRequestCrop(Uri uri, Uri uri2) {
        e.a(getActivity()).a().a(49, (Fragment) this).a(uri, uri2, options);
    }

    public void onPermissionGranted() {
        startImagePicker();
    }

    public void onRequestCreated(LeaveReviewRequest<Review> leaveReviewRequest) {
        this.mInputHelper.a((int) R.string.sending_review);
        getRequestQueue().a((g<Result>) com.etsy.android.lib.core.e.a((EtsyRequest<Result>) leaveReviewRequest).a((c<Result>) new c<Review>() {
            public void a(List<Review> list, int i, k<Review> kVar) {
                if (LeaveFeedbackFragment.this.mInputHelper != null) {
                    LeaveFeedbackFragment.this.mInputHelper.a();
                }
                Review review = null;
                if (list.size() > 0 && list.get(0) != null) {
                    review = (Review) list.get(0);
                }
                LeaveFeedbackFragment.this.finishReview(review);
            }
        }).a((f.b) new f.b() {
            public void a(int i, String str, k kVar) {
                if (LeaveFeedbackFragment.this.mInputHelper != null) {
                    LeaveFeedbackFragment.this.mInputHelper.a();
                    LeaveFeedbackFragment.this.mInputHelper.a(LeaveFeedbackFragment.this.mTxtError, LeaveFeedbackFragment.this.getString(R.string.review_request_creation_error));
                }
            }
        }).a());
    }

    public void showContent() {
        configureSubmitMenuItem(true);
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mScrollView != null) {
            this.mScrollView.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void showError() {
        configureSubmitMenuItem(false);
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(0);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(8);
        }
        if (this.mScrollView != null) {
            this.mScrollView.setVisibility(8);
        }
    }

    private void showLoading() {
        configureSubmitMenuItem(false);
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
        }
        if (this.mErrorView != null) {
            this.mErrorView.setVisibility(8);
        }
        if (this.mScrollView != null) {
            this.mScrollView.setVisibility(8);
        }
    }

    private void configureSubmitMenuItem(boolean z) {
        this.mShowReviewMenuItem = z;
        getActivity().invalidateOptionsMenu();
    }

    public void onRequestCreationFailed() {
        this.mInputHelper.a();
        this.mInputHelper.a(this.mTxtError, getString(R.string.review_request_creation_error));
    }
}
