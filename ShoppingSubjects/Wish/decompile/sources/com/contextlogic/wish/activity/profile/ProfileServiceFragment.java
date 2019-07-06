package com.contextlogic.wish.activity.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.DialogTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.ServiceFragment.PermissionCallback;
import com.contextlogic.wish.activity.productdetails.ProductDetailsActivity;
import com.contextlogic.wish.activity.profile.wishlist.CreateWishlistDialogFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishFollowedWishlist;
import com.contextlogic.wish.api.model.WishImage;
import com.contextlogic.wish.api.model.WishProduct;
import com.contextlogic.wish.api.model.WishRating;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.model.WishWishlist;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.CreateWishlistService;
import com.contextlogic.wish.api.service.standalone.DeleteWishlistService;
import com.contextlogic.wish.api.service.standalone.FollowService;
import com.contextlogic.wish.api.service.standalone.GetProductService;
import com.contextlogic.wish.api.service.standalone.GetProductService.FeedExtraInfo;
import com.contextlogic.wish.api.service.standalone.GetProfileService;
import com.contextlogic.wish.api.service.standalone.GetUserFollowedWishlistsService;
import com.contextlogic.wish.api.service.standalone.GetUserProductPhotosService;
import com.contextlogic.wish.api.service.standalone.GetUserProductRatingsService;
import com.contextlogic.wish.api.service.standalone.GetUserWishlistsService;
import com.contextlogic.wish.api.service.standalone.GetWishlistNameSuggestionService;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.RenameWishlistService;
import com.contextlogic.wish.api.service.standalone.UnfollowService;
import com.contextlogic.wish.api.service.standalone.UploadProfileImageService;
import com.contextlogic.wish.api.service.standalone.WishlistPrivacyService;
import com.contextlogic.wish.api.service.standalone.WishlistUnfollowService;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;
import java.util.List;

public class ProfileServiceFragment extends ServiceFragment<ProfileActivity> {
    /* access modifiers changed from: private */
    public CreateWishlistService mCreateWishlistService;
    private DeleteWishlistService mDeleteWishlistService;
    private FollowService mFollowService;
    private GetProductService mGetProductService;
    private GetProfileService mGetProfileService;
    private GetUserFollowedWishlistsService mGetUserFollowedWishlistsService;
    private GetUserProductPhotosService mGetUserProductPhotosService;
    private GetUserProductRatingsService mGetUserProductRatingsService;
    private GetUserWishlistsService mGetUserWishlistsService;
    private GetWishlistNameSuggestionService mGetWishlistNameSuggestionService;
    /* access modifiers changed from: private */
    public ParseImageChooserIntentService mParseImageChooserIntentService;
    /* access modifiers changed from: private */
    public RenameWishlistService mRenameWishlistService;
    /* access modifiers changed from: private */
    public UnfollowService mUnfollowService;
    private UploadProfileImageService mUploadProfileImageService;
    private WishlistPrivacyService mWishlistPrivacyService;
    private WishlistUnfollowService mWishlistUnfollowService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetProfileService = new GetProfileService();
        this.mGetUserWishlistsService = new GetUserWishlistsService();
        this.mGetUserFollowedWishlistsService = new GetUserFollowedWishlistsService();
        this.mCreateWishlistService = new CreateWishlistService();
        this.mGetWishlistNameSuggestionService = new GetWishlistNameSuggestionService();
        this.mUploadProfileImageService = new UploadProfileImageService();
        this.mParseImageChooserIntentService = new ParseImageChooserIntentService();
        this.mWishlistUnfollowService = new WishlistUnfollowService();
        this.mFollowService = new FollowService();
        this.mUnfollowService = new UnfollowService();
        this.mWishlistPrivacyService = new WishlistPrivacyService();
        this.mRenameWishlistService = new RenameWishlistService();
        this.mDeleteWishlistService = new DeleteWishlistService();
        this.mGetUserProductRatingsService = new GetUserProductRatingsService();
        this.mGetUserProductPhotosService = new GetUserProductPhotosService();
        this.mGetProductService = new GetProductService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetProfileService.cancelAllRequests();
        this.mGetUserWishlistsService.cancelAllRequests();
        this.mGetUserFollowedWishlistsService.cancelAllRequests();
        this.mCreateWishlistService.cancelAllRequests();
        this.mGetWishlistNameSuggestionService.cancelAllRequests();
        this.mUploadProfileImageService.cancelAllRequests();
        this.mParseImageChooserIntentService.cancelAllRequests();
        this.mWishlistUnfollowService.cancelAllRequests();
        this.mFollowService.cancelAllRequests();
        this.mUnfollowService.cancelAllRequests();
        this.mWishlistPrivacyService.cancelAllRequests();
        this.mRenameWishlistService.cancelAllRequests();
        this.mDeleteWishlistService.cancelAllRequests();
        this.mGetUserProductRatingsService.cancelAllRequests();
        this.mGetUserProductPhotosService.cancelAllRequests();
        this.mGetProductService.cancelAllRequests();
    }

    public void changeProfileImage() {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                profileActivity.requestPermission("android.permission.CAMERA", new PermissionCallback() {
                    public void onPermissionGranted() {
                        ProfileServiceFragment.this.requestExternalFilePermissions();
                    }

                    public void onPermissionDenied() {
                        ProfileServiceFragment.this.requestExternalFilePermissions();
                    }
                });
            }
        });
    }

    public void requestExternalFilePermissions() {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                profileActivity.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionCallback() {
                    public void onPermissionGranted() {
                        ProfileServiceFragment.this.openPictureChooser();
                    }

                    public void onPermissionDenied() {
                        ProfileServiceFragment.this.withActivity(new ActivityTask<ProfileActivity>() {
                            public void performTask(ProfileActivity profileActivity) {
                                profileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(profileActivity.getString(R.string.please_enable_file_access_permissions_for_images)));
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void openPictureChooser() {
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                final Intent imageChooserIntent = IntentUtil.getImageChooserIntent();
                profileActivity.startActivityForResult(imageChooserIntent, profileActivity.addResultCodeCallback(new ActivityResultCallback() {
                    public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                        if (i2 != 0) {
                            if (i2 == -1) {
                                ProfileServiceFragment.this.withActivity(new ActivityTask<ProfileActivity>() {
                                    public void performTask(ProfileActivity profileActivity) {
                                        profileActivity.showLoadingDialog();
                                    }
                                });
                                ProfileServiceFragment.this.mParseImageChooserIntentService.requestService(imageChooserIntent, intent, new SuccessCallback() {
                                    public void onSuccess(Bitmap bitmap) {
                                        ProfileServiceFragment.this.uploadProfileImage(bitmap);
                                    }
                                }, new DefaultFailureCallback() {
                                    public void onFailure(String str) {
                                        ProfileServiceFragment.this.withActivity(new ActivityTask<ProfileActivity>() {
                                            public void performTask(ProfileActivity profileActivity) {
                                                profileActivity.hideLoadingDialog();
                                                profileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(profileActivity.getString(R.string.problem_opening_selected_image)));
                                            }
                                        });
                                    }
                                });
                            } else {
                                ProfileServiceFragment.this.withActivity(new ActivityTask<ProfileActivity>() {
                                    public void performTask(ProfileActivity profileActivity) {
                                        profileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(profileActivity.getString(R.string.problem_opening_selected_image)));
                                    }
                                });
                            }
                        }
                    }
                }));
            }
        });
    }

    /* access modifiers changed from: private */
    public void uploadProfileImage(Bitmap bitmap) {
        this.mUploadProfileImageService.requestService(bitmap, new UploadProfileImageService.SuccessCallback() {
            public void onSuccess(final WishUser wishUser) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        baseActivity.hideLoadingDialog();
                        profileFragment.updateUserImage(wishUser);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ProfileServiceFragment.this.withActivity(new ActivityTask<ProfileActivity>() {
                    public void performTask(ProfileActivity profileActivity) {
                        profileActivity.hideLoadingDialog();
                        profileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                });
            }
        });
    }

    public void createWishlist() {
        final CreateWishlistDialogFragment createWishlistDialogFragment = new CreateWishlistDialogFragment();
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                profileActivity.startDialog(createWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        final String string = bundle.getString("ResultName");
                        ProfileServiceFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ProfileServiceFragment>() {
                            public void performTask(BaseActivity baseActivity, ProfileServiceFragment profileServiceFragment) {
                                ProfileServiceFragment.this.mCreateWishlistService.requestService(string, new DefaultSuccessCallback() {
                                    public void onSuccess() {
                                        ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                                            public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                                                profileFragment.handleCreateWishlistSuccess();
                                            }
                                        }, "FragmentTagMainContent");
                                    }
                                }, new DefaultFailureCallback() {
                                    public void onFailure(final String str) {
                                        ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                                            public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                                                ProfileServiceFragment.this.withActivity(new ActivityTask<ProfileActivity>() {
                                                    public void performTask(ProfileActivity profileActivity) {
                                                        profileActivity.hideLoadingDialog();
                                                        profileActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                                                    }
                                                });
                                                profileFragment.handleLoadingFailure();
                                            }
                                        }, "FragmentTagMainContent");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        createWishlistDialogFragment.loadNameSuggestions(null);
    }

    public void loadUser(String str) {
        this.mGetProfileService.requestService(str, new GetProfileService.SuccessCallback() {
            public void onSuccess(final WishUser wishUser) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleUserLoadingSuccess(wishUser);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(String str, int i) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleLoadingFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void launchProductDetail(String str) {
        this.mGetProductService.requestService(str, null, new GetProductService.SuccessCallback() {
            public void onSuccess(final WishProduct wishProduct, FeedExtraInfo feedExtraInfo) {
                ProfileServiceFragment.this.withActivity(new ActivityTask<ProfileActivity>() {
                    public void performTask(ProfileActivity profileActivity) {
                        Intent intent = new Intent();
                        intent.setClass(profileActivity, ProductDetailsActivity.class);
                        ProductDetailsActivity.addInitialProduct(intent, wishProduct);
                        profileActivity.startActivity(intent);
                    }
                });
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(final String str, int i) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadWishlist(String str, int i) {
        this.mGetUserWishlistsService.requestService(str, i, 10, 10, new GetUserWishlistsService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishWishlist> arrayList, final int i, final boolean z) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleWishlistLoadingSuccess(arrayList, i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleLoadingWishlistFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadFollowedWishlist(String str, int i) {
        this.mGetUserFollowedWishlistsService.requestService(str, i, 10, 10, new GetUserFollowedWishlistsService.SuccessCallback() {
            public void onSuccess(final List<WishFollowedWishlist> list, final int i, final boolean z) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleFollowedWishlistLoadingSuccess(list, i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleLoadingWishlistFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadUserProductRatings(String str, int i) {
        this.mGetUserProductRatingsService.requestService(str, i, 10, new GetUserProductRatingsService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishRating> arrayList, final int i, final boolean z) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleLoadingUserProductRatingsSuccess(arrayList, i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleLoadingUserProductRatingsFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadUserProductPhotos(String str, int i) {
        this.mGetUserProductPhotosService.requestService(str, i, 9, new GetUserProductPhotosService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishImage> arrayList, final int i, final boolean z) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.loadUserPhotosSuccess(arrayList, i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.loadUserPhotosFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void getWishlistNameSuggestion(String str) {
        this.mGetWishlistNameSuggestionService.requestService(str, new GetWishlistNameSuggestionService.SuccessCallback() {
            public void onSuccess(final ArrayList<String> arrayList) {
                ProfileServiceFragment.this.withDialogFragment(new DialogTask<BaseActivity, BaseDialogFragment>() {
                    public void performTask(BaseActivity baseActivity, BaseDialogFragment baseDialogFragment) {
                        if (baseDialogFragment instanceof CreateWishlistDialogFragment) {
                            ((CreateWishlistDialogFragment) baseDialogFragment).updateSuggestions(arrayList);
                        }
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
            }
        });
    }

    public void unFollowWishlist(final WishWishlist wishWishlist) {
        this.mWishlistUnfollowService.requestService(wishWishlist.getWishlistId(), new DefaultSuccessCallback() {
            public void onSuccess() {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleUnfollowWishlistSuccess(wishWishlist);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void followUser(final ProfileHeaderView profileHeaderView, final String str) {
        profileHeaderView.setFollowButtonMode(ButtonMode.UnselectedLoading);
        this.mFollowService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.handleFollowActionSuccess(str);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileHeaderView.setFollowButtonMode(ButtonMode.Unselected);
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void unfollowUser(final ProfileHeaderView profileHeaderView, final String str) {
        withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
            public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, ProfileServiceFragment.this.getString(R.string.unfollow), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(2, ProfileServiceFragment.this.getString(R.string.no), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                arrayList.add(multiButtonDialogChoice2);
                baseActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(ProfileServiceFragment.this.getString(R.string.are_you_sure)).setSubTitle(ProfileServiceFragment.this.getString(R.string.are_you_sure_unfollow_user)).setButtons(arrayList).hideXButton().setCancelable(true).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            profileHeaderView.setFollowButtonMode(ButtonMode.SelectedLoading);
                            ProfileServiceFragment.this.mUnfollowService.requestService(str, new DefaultSuccessCallback() {
                                public void onSuccess() {
                                    ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                                        public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                                            profileFragment.handleFollowActionSuccess(str);
                                        }
                                    }, "FragmentTagMainContent");
                                }
                            }, new DefaultFailureCallback() {
                                public void onFailure(final String str) {
                                    ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                                        public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                                            profileHeaderView.setFollowButtonMode(ButtonMode.Selected);
                                            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                                        }
                                    }, "FragmentTagMainContent");
                                }
                            });
                        }
                    }
                });
            }
        }, "FragmentTagMainContent");
    }

    public void setWishlistPrivacy(final int i, String str, final boolean z) {
        this.mWishlistPrivacyService.requestService(str, z, new DefaultSuccessCallback() {
            public void onSuccess() {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        profileFragment.setWishlistPrivacySuccess(i, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ProfileServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
                    public void performTask(BaseActivity baseActivity, ProfileFragment profileFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void renameWishlist(final WishWishlist wishWishlist) {
        final CreateWishlistDialogFragment createWishlistDialogFragment = new CreateWishlistDialogFragment();
        withUiFragment(new UiTask<BaseActivity, ProfileFragment>() {
            public void performTask(final BaseActivity baseActivity, final ProfileFragment profileFragment) {
                baseActivity.startDialog(createWishlistDialogFragment, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        final String string = bundle.getString("ResultName");
                        ProfileServiceFragment.this.mRenameWishlistService.requestService(wishWishlist.getWishlistId(), string, new DefaultSuccessCallback() {
                            public void onSuccess() {
                                profileFragment.handleWishlistRenameSuccess(wishWishlist, string);
                            }
                        }, new DefaultFailureCallback() {
                            public void onFailure(String str) {
                                baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                            }
                        });
                    }
                });
                createWishlistDialogFragment.setInitialName(wishWishlist.getName());
            }
        }, "FragmentTagMainContent");
    }

    public void showWishStarDialog(String str) {
        final WishStarDialogFragment createWishStarDialogFragment = WishStarDialogFragment.createWishStarDialogFragment(str);
        withActivity(new ActivityTask<ProfileActivity>() {
            public void performTask(ProfileActivity profileActivity) {
                profileActivity.startDialog(createWishStarDialogFragment);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_WISH_STAR_DIALOG);
            }
        });
    }
}
