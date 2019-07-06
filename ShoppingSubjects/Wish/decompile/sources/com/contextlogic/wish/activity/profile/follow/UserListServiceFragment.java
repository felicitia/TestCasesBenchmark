package com.contextlogic.wish.activity.profile.follow;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.FollowService;
import com.contextlogic.wish.api.service.standalone.GetProfileUsersService;
import com.contextlogic.wish.api.service.standalone.GetProfileUsersService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.UnfollowService;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.ui.button.FollowButton;
import com.contextlogic.wish.ui.button.ToggleLoadingButton.ButtonMode;
import java.util.ArrayList;

public class UserListServiceFragment extends ServiceFragment<UserListActivity> {
    private FollowService mFollowService;
    private GetProfileUsersService mGetProfileUsersService;
    /* access modifiers changed from: private */
    public UnfollowService mUnfollowService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetProfileUsersService = new GetProfileUsersService();
        this.mFollowService = new FollowService();
        this.mUnfollowService = new UnfollowService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetProfileUsersService.cancelAllRequests();
        this.mFollowService.cancelAllRequests();
        this.mUnfollowService.cancelAllRequests();
    }

    public void loadUsers(String str, final int i) {
        this.mGetProfileUsersService.requestService(str, i, 25, new SuccessCallback() {
            public void onSuccess(final ArrayList<WishUser> arrayList, final boolean z) {
                UserListServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UserListFragment>() {
                    public void performTask(BaseActivity baseActivity, UserListFragment userListFragment) {
                        userListFragment.handleLoadingSuccess(arrayList, i + 25, z);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                UserListServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UserListFragment>() {
                    public void performTask(BaseActivity baseActivity, UserListFragment userListFragment) {
                        userListFragment.handleLoadingFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void followUser(final String str) {
        this.mFollowService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
                UserListServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UserListFragment>() {
                    public void performTask(BaseActivity baseActivity, UserListFragment userListFragment) {
                        userListFragment.handleFollowUserSuccess(str);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                UserListServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UserListFragment>() {
                    public void performTask(BaseActivity baseActivity, UserListFragment userListFragment) {
                        userListFragment.handleFollowUserFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void unfollowUser(final FollowButton followButton, final String str) {
        withUiFragment(new UiTask<BaseActivity, UserListFragment>() {
            public void performTask(BaseActivity baseActivity, UserListFragment userListFragment) {
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, UserListServiceFragment.this.getString(R.string.unfollow), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(2, UserListServiceFragment.this.getString(R.string.no), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                arrayList.add(multiButtonDialogChoice2);
                baseActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(UserListServiceFragment.this.getString(R.string.are_you_sure)).setSubTitle(UserListServiceFragment.this.getString(R.string.are_you_sure_unfollow_user)).setButtons(arrayList).hideXButton().setCancelable(true).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            followButton.setButtonMode(ButtonMode.SelectedLoading);
                            UserListServiceFragment.this.mUnfollowService.requestService(str, new DefaultSuccessCallback() {
                                public void onSuccess() {
                                    UserListServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UserListFragment>() {
                                        public void performTask(BaseActivity baseActivity, UserListFragment userListFragment) {
                                            userListFragment.handleUnfollowUserSuccess(str);
                                        }
                                    }, "FragmentTagMainContent");
                                }
                            }, new DefaultFailureCallback() {
                                public void onFailure(String str) {
                                    UserListServiceFragment.this.withUiFragment(new UiTask<BaseActivity, UserListFragment>() {
                                        public void performTask(BaseActivity baseActivity, UserListFragment userListFragment) {
                                            userListFragment.handleFollowUserFailure();
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
}
