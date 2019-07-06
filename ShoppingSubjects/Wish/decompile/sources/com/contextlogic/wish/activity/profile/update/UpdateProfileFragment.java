package com.contextlogic.wish.activity.profile.update;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.settings.SettingsFormFragment;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.ui.image.NetworkImageView;
import com.contextlogic.wish.ui.view.FormTextInputLayout;
import com.contextlogic.wish.ui.view.FormTextInputLayout.OnFieldChangedListener;
import com.contextlogic.wish.ui.view.FormTextInputLayout.OnVerifyFieldListener;
import com.contextlogic.wish.util.ArrayUtil;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.LocaleUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class UpdateProfileFragment extends SettingsFormFragment<UpdateProfileActivity> {
    private boolean[] mAreFieldsChanged = new boolean[Field.values().length];
    private boolean[] mAreFieldsErrored = new boolean[Field.values().length];
    /* access modifiers changed from: private */
    public Date mBirthDate;
    /* access modifiers changed from: private */
    public FormTextInputLayout mDobForm;
    /* access modifiers changed from: private */
    public FormTextInputLayout mFirstNameForm;
    private RadioGroup mGenderRadioGroup;
    /* access modifiers changed from: private */
    public FormTextInputLayout mLastNameForm;
    private TextView mProfileImageCaption;
    private View mProfileImageContainer;
    private NetworkImageView mProfileImageView;

    private enum Field {
        PROFILE_IMAGE,
        FIRST_NAME,
        LAST_NAME,
        DOB,
        GENDER
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutResourceId() {
        return R.layout.update_profile_fragment;
    }

    public void restoreImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.restoreImages();
        }
    }

    public void releaseImages() {
        if (this.mProfileImageView != null) {
            this.mProfileImageView.releaseImages();
        }
    }

    /* access modifiers changed from: protected */
    public void initializeContent(View view) {
        this.mProfileImageView = (NetworkImageView) view.findViewById(R.id.update_profile_redesign_image);
        this.mProfileImageContainer = view.findViewById(R.id.update_profile_redesign_image_container);
        this.mProfileImageCaption = (TextView) view.findViewById(R.id.update_profile_redesign_image_caption);
        this.mFirstNameForm = (FormTextInputLayout) view.findViewById(R.id.update_profile_redesign_form_input_first);
        this.mLastNameForm = (FormTextInputLayout) view.findViewById(R.id.update_profile_redesign_form_input_last);
        this.mDobForm = (FormTextInputLayout) view.findViewById(R.id.update_profile_redesign_form_input_birthday);
        this.mGenderRadioGroup = (RadioGroup) view.findViewById(R.id.update_profile_redesign_gender_radio_group);
        if (LocaleUtil.isJapanese()) {
            swapViews(this.mFirstNameForm, this.mLastNameForm);
        }
        initializeValues();
        initializeListeners();
        getLoadingPageView().markLoadingComplete();
    }

    private void initializeValues() {
        initProfileImage();
        initNames();
        initGenderSelection();
        initDob();
    }

    private void initializeListeners() {
        this.mFirstNameForm.setOnVerifyFormListener(getRequiredFieldVerifier(Field.FIRST_NAME));
        this.mLastNameForm.setOnVerifyFormListener(getRequiredFieldVerifier(Field.LAST_NAME));
        this.mProfileImageContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UpdateProfileFragment.this.handleChangeProfilePicture();
            }
        });
        this.mDobForm.setDropdownClickListener(new OnClickListener() {
            public void onClick(View view) {
                UpdateProfileFragment.this.showDobPicker();
            }
        });
        this.mFirstNameForm.setOnFieldChangedListener(new OnFieldChangedListener() {
            public void onFieldChanged(String str) {
                UpdateProfileFragment.this.updateFieldChanged(Field.FIRST_NAME, UpdateProfileFragment.this.hasFirstNameChanged(str));
            }
        });
        this.mLastNameForm.setOnFieldChangedListener(new OnFieldChangedListener() {
            public void onFieldChanged(String str) {
                UpdateProfileFragment.this.updateFieldChanged(Field.LAST_NAME, UpdateProfileFragment.this.hasLastNameChanged(str));
            }
        });
        this.mDobForm.setOnFieldChangedListener(new OnFieldChangedListener() {
            public void onFieldChanged(String str) {
                UpdateProfileFragment.this.updateFieldChanged(Field.DOB, UpdateProfileFragment.this.hasDobChanged(str));
            }
        });
        this.mGenderRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                UpdateProfileFragment.this.updateFieldChanged(Field.GENDER, UpdateProfileFragment.this.hasGenderChanged());
            }
        });
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            AnonymousClass7 r0 = new OnFocusChangeListener() {
                public void onFocusChange(View view, final boolean z) {
                    UpdateProfileFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                        public void performTask(UpdateProfileActivity updateProfileActivity) {
                            updateProfileActivity.setBottomNavigationVisible(!z);
                        }
                    });
                }
            };
            this.mFirstNameForm.setOnFocusChangedListener(r0);
            this.mLastNameForm.setOnFocusChangedListener(r0);
        }
    }

    /* access modifiers changed from: private */
    public boolean hasFirstNameChanged(String str) {
        boolean z = true;
        if (ProfileDataCenter.getInstance().getFirstName() != null) {
            return !ProfileDataCenter.getInstance().getFirstName().equalsIgnoreCase(str);
        }
        if (str == null) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public boolean hasLastNameChanged(String str) {
        boolean z = true;
        if (ProfileDataCenter.getInstance().getLastName() != null) {
            return !ProfileDataCenter.getInstance().getLastName().equalsIgnoreCase(str);
        }
        if (str == null) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public boolean hasDobChanged(String str) {
        String localizedReadableDate = (!AuthenticationDataCenter.getInstance().isLoggedIn() || ProfileDataCenter.getInstance().getBirthday() == null) ? null : DateUtil.getLocalizedReadableDate(ProfileDataCenter.getInstance().getBirthday());
        boolean z = true;
        if (localizedReadableDate != null) {
            return !localizedReadableDate.equalsIgnoreCase(str);
        }
        if (str == null) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public boolean hasGenderChanged() {
        boolean z = false;
        if (this.mGenderRadioGroup == null) {
            return false;
        }
        String gender = ProfileDataCenter.getInstance().getGender();
        if (gender == null || !gender.equalsIgnoreCase(getSelectedGender())) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void updateFieldChanged(Field field, boolean z) {
        if (!(field == null || this.mAreFieldsChanged == null || this.mAreFieldsChanged[field.ordinal()] == z)) {
            this.mAreFieldsChanged[field.ordinal()] = z;
            handleButtonEnabling();
        }
    }

    /* access modifiers changed from: private */
    public void updateFieldErrored(Field field, boolean z) {
        if (!(field == null || this.mAreFieldsErrored == null || this.mAreFieldsErrored[field.ordinal()] == z)) {
            this.mAreFieldsErrored[field.ordinal()] = z;
            handleButtonEnabling();
        }
    }

    private void handleButtonEnabling() {
        setButtonState(anyFieldChanged() && !anyFieldErrored());
    }

    private boolean anyFieldChanged() {
        return ArrayUtil.any(this.mAreFieldsChanged);
    }

    private boolean anyFieldErrored() {
        return ArrayUtil.any(this.mAreFieldsErrored);
    }

    private void initProfileImage() {
        if (ProfileDataCenter.getInstance().getProfileImage() != null) {
            this.mProfileImageView.setImage(ProfileDataCenter.getInstance().getProfileImage());
            this.mProfileImageContainer.setActivated(true);
            this.mProfileImageCaption.setText(R.string.profile_photo);
        } else {
            this.mProfileImageView.setImageResource(R.drawable.profilecamera_80);
            this.mProfileImageContainer.setActivated(false);
            this.mProfileImageCaption.setText(R.string.upload_a_photo);
        }
        this.mProfileImageView.setCircleCrop(true);
    }

    private void initNames() {
        this.mFirstNameForm.setText(ProfileDataCenter.getInstance().getFirstName());
        this.mLastNameForm.setText(ProfileDataCenter.getInstance().getLastName());
    }

    private void initGenderSelection() {
        if (this.mGenderRadioGroup != null) {
            String gender = ProfileDataCenter.getInstance().getGender();
            if (gender != null && gender.equalsIgnoreCase("male")) {
                this.mGenderRadioGroup.check(R.id.update_profile_redesign_radio_button_male);
            } else if (gender != null && gender.equalsIgnoreCase("female")) {
                this.mGenderRadioGroup.check(R.id.update_profile_redesign_radio_button_female);
            }
        }
    }

    private void initDob() {
        if (!AuthenticationDataCenter.getInstance().isLoggedIn() || ProfileDataCenter.getInstance().getBirthday() == null) {
            this.mDobForm.setText(null);
            this.mDobForm.setHint(getString(R.string.not_set));
            return;
        }
        this.mDobForm.setText(DateUtil.getLocalizedReadableDate(ProfileDataCenter.getInstance().getBirthday()));
    }

    private void swapViews(View view, View view2) {
        if (view != null && view2 != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
            viewGroup.addView(view, viewGroup.indexOfChild(view2) + 1);
            int nextFocusDownId = view.getNextFocusDownId();
            view.setNextFocusDownId(view2.getNextFocusDownId());
            view2.setNextFocusDownId(nextFocusDownId);
        }
    }

    private OnVerifyFieldListener getRequiredFieldVerifier(final Field field) {
        return new OnVerifyFieldListener() {
            public String getErrorMessage(String str) {
                boolean z = str == null;
                String string = z ? UpdateProfileFragment.this.getString(R.string.required_field) : null;
                UpdateProfileFragment.this.updateFieldErrored(field, z);
                return string;
            }
        };
    }

    /* access modifiers changed from: private */
    public void showDobPicker() {
        withActivity(new ActivityTask<UpdateProfileActivity>() {
            public void performTask(UpdateProfileActivity updateProfileActivity) {
                Calendar calendar;
                Date safeParseLocalizedReadableDateString = DateUtil.safeParseLocalizedReadableDateString(ViewUtil.extractEditTextValue(UpdateProfileFragment.this.mDobForm.getEditText()));
                if (safeParseLocalizedReadableDateString != null) {
                    calendar = Calendar.getInstance();
                    calendar.setTime(safeParseLocalizedReadableDateString);
                } else if (!AuthenticationDataCenter.getInstance().isLoggedIn() || ProfileDataCenter.getInstance().getBirthday() == null) {
                    calendar = Calendar.getInstance();
                    calendar.set(1, calendar.get(1) - 18);
                } else {
                    calendar = Calendar.getInstance();
                    calendar.setTime(ProfileDataCenter.getInstance().getBirthday());
                }
                KeyboardUtil.hideKeyboard((Activity) updateProfileActivity);
                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateProfileFragment.this.getContext(), R.style.DateTimeDialogTheme, new OnDateSetListener() {
                    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                        Calendar instance = Calendar.getInstance();
                        instance.set(i, i2, i3);
                        UpdateProfileFragment.this.mBirthDate = new Date(instance.getTimeInMillis());
                        UpdateProfileFragment.this.mDobForm.setText(DateUtil.getLocalizedReadableDate(UpdateProfileFragment.this.mBirthDate));
                    }
                }, calendar.get(1), calendar.get(2), calendar.get(5));
                datePickerDialog.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public String getSelectedGender() {
        return (this.mGenderRadioGroup == null || this.mGenderRadioGroup.getCheckedRadioButtonId() != R.id.update_profile_redesign_radio_button_male) ? "female" : "male";
    }

    /* access modifiers changed from: protected */
    public void onSaveButtonClicked() {
        KeyboardUtil.hideKeyboard((Fragment) this);
        if (!anyFieldChanged() || anyFieldErrored()) {
            handleButtonEnabling();
        } else {
            withServiceFragment(new ServiceTask<UpdateProfileActivity, UpdateProfileServiceFragment>() {
                /* JADX WARNING: Removed duplicated region for block: B:10:0x0042  */
                /* JADX WARNING: Removed duplicated region for block: B:11:0x006a  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void performTask(com.contextlogic.wish.activity.profile.update.UpdateProfileActivity r13, com.contextlogic.wish.activity.profile.update.UpdateProfileServiceFragment r14) {
                    /*
                        r12 = this;
                        java.util.Calendar r13 = java.util.Calendar.getInstance()
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r0 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        java.util.Date r0 = r0.mBirthDate
                        r1 = 0
                        r2 = 1
                        if (r0 == 0) goto L_0x0018
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r0 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        java.util.Date r0 = r0.mBirthDate
                        r13.setTime(r0)
                        goto L_0x002d
                    L_0x0018:
                        com.contextlogic.wish.api.datacenter.ProfileDataCenter r0 = com.contextlogic.wish.api.datacenter.ProfileDataCenter.getInstance()
                        java.util.Date r0 = r0.getBirthday()
                        if (r0 == 0) goto L_0x002f
                        com.contextlogic.wish.api.datacenter.ProfileDataCenter r0 = com.contextlogic.wish.api.datacenter.ProfileDataCenter.getInstance()
                        java.util.Date r0 = r0.getBirthday()
                        r13.setTime(r0)
                    L_0x002d:
                        r0 = 1
                        goto L_0x0030
                    L_0x002f:
                        r0 = 0
                    L_0x0030:
                        r3 = 2
                        int r3 = r13.get(r3)
                        int r8 = r3 + 1
                        r3 = 5
                        int r7 = r13.get(r3)
                        int r9 = r13.get(r2)
                        if (r0 == 0) goto L_0x006a
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r13 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        com.contextlogic.wish.ui.view.FormTextInputLayout r13 = r13.mFirstNameForm
                        com.contextlogic.wish.ui.text.ThemedEditText r13 = r13.getEditText()
                        java.lang.String r5 = com.contextlogic.wish.util.ViewUtil.extractEditTextValue(r13)
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r13 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        com.contextlogic.wish.ui.view.FormTextInputLayout r13 = r13.mLastNameForm
                        com.contextlogic.wish.ui.text.ThemedEditText r13 = r13.getEditText()
                        java.lang.String r6 = com.contextlogic.wish.util.ViewUtil.extractEditTextValue(r13)
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r13 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        java.lang.String r10 = r13.getSelectedGender()
                        r11 = 0
                        r4 = r14
                        r4.updateProfile(r5, r6, r7, r8, r9, r10, r11)
                        goto L_0x008f
                    L_0x006a:
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r13 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        com.contextlogic.wish.ui.view.FormTextInputLayout r13 = r13.mFirstNameForm
                        com.contextlogic.wish.ui.text.ThemedEditText r13 = r13.getEditText()
                        java.lang.String r13 = com.contextlogic.wish.util.ViewUtil.extractEditTextValue(r13)
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r0 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        com.contextlogic.wish.ui.view.FormTextInputLayout r0 = r0.mLastNameForm
                        com.contextlogic.wish.ui.text.ThemedEditText r0 = r0.getEditText()
                        java.lang.String r0 = com.contextlogic.wish.util.ViewUtil.extractEditTextValue(r0)
                        com.contextlogic.wish.activity.profile.update.UpdateProfileFragment r2 = com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.this
                        java.lang.String r2 = r2.getSelectedGender()
                        r14.updateProfile(r13, r0, r2, r1)
                    L_0x008f:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.profile.update.UpdateProfileFragment.AnonymousClass10.performTask(com.contextlogic.wish.activity.profile.update.UpdateProfileActivity, com.contextlogic.wish.activity.profile.update.UpdateProfileServiceFragment):void");
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleChangeProfilePicture() {
        withServiceFragment(new ServiceTask<BaseActivity, UpdateProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, UpdateProfileServiceFragment updateProfileServiceFragment) {
                updateProfileServiceFragment.changeProfileImage();
            }
        });
    }

    public void updateUserImage(WishUser wishUser) {
        if (this.mProfileImageView != null && wishUser != null && wishUser.getProfileImage() != null && this.mProfileImageContainer != null && this.mProfileImageCaption != null) {
            this.mProfileImageView.setImage(wishUser.getProfileImage());
            this.mProfileImageContainer.setActivated(true);
            this.mProfileImageCaption.setText(R.string.profile_photo);
        }
    }

    public boolean onBackPressed() {
        if (!anyFieldChanged()) {
            return super.onBackPressed();
        }
        withServiceFragment(new ServiceTask<BaseActivity, UpdateProfileServiceFragment>() {
            public void performTask(BaseActivity baseActivity, UpdateProfileServiceFragment updateProfileServiceFragment) {
                updateProfileServiceFragment.confirmClosing();
            }
        });
        return true;
    }

    public void handleUpdateSuccess() {
        if (this.mAreFieldsChanged != null) {
            Arrays.fill(this.mAreFieldsChanged, false);
            handleButtonEnabling();
        }
        if (getView() != null) {
            getView().requestFocus();
        }
        withActivity(new ActivityTask<UpdateProfileActivity>() {
            public void performTask(UpdateProfileActivity updateProfileActivity) {
                UpdateProfileFragment.this.withActivity(new ActivityTask<UpdateProfileActivity>() {
                    public void performTask(UpdateProfileActivity updateProfileActivity) {
                        SuccessBottomSheetDialog.create(updateProfileActivity).setTitle(UpdateProfileFragment.this.getString(R.string.profile_updated_exclamation)).autoDismiss().show();
                    }
                });
            }
        });
    }
}
