package com.etsy.android.ui.user;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.etsy.android.R;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.legacy.b;
import com.etsy.android.lib.models.AppBuild;
import com.etsy.android.lib.models.EtsyCurrency;
import com.etsy.android.lib.push.f;
import com.etsy.android.lib.util.CurrencyUtil;
import com.etsy.android.lib.util.SharedPreferencesUtility;
import com.etsy.android.lib.util.g;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.ui.nav.e;
import com.etsy.android.ui.util.c;
import com.etsy.android.uikit.nav.FragmentNavigator.AnimationMode;
import com.etsy.android.uikit.util.TrackingOnCheckedChangeListener;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.util.d;
import java.util.Locale;

public class UserSettingsFragment extends EtsyFragment implements a {
    /* access modifiers changed from: private */
    public g mAlphaUpdateCheck;
    private OnClickListener mClickListener = new TrackingOnClickListener() {
        public void onViewClick(View view) {
        }
    };
    /* access modifiers changed from: private */
    public EtsyDialogFragment mParentDialog;
    /* access modifiers changed from: private */
    public View mUpdateRow;
    /* access modifiers changed from: private */
    public View mView;
    f pushRegistration;

    @NonNull
    public String getTrackingName() {
        return "your_account_settings";
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = layoutInflater.inflate(R.layout.fragment_user_settings, viewGroup, false);
        setUpCommonButtons();
        if (v.a().e()) {
            setUpSignedInButtons();
        }
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mParentDialog = (EtsyDialogFragment) getParentFragment();
        this.mParentDialog.setWindowAnimation(R.style.DialogAnimBottom);
        this.mParentDialog.enableTouchInterceptPadding(getResources().getDimensionPixelSize(R.dimen.signin_dialog_padding));
        if (c.b()) {
            this.mAlphaUpdateCheck = new g(getActivity());
            this.mAlphaUpdateCheck.a((g.a) new g.a() {
                public void a(AppBuild appBuild) {
                    if (UserSettingsFragment.this.mUpdateRow != null) {
                        UserSettingsFragment.this.mUpdateRow.setVisibility(0);
                    }
                }
            });
            return;
        }
        this.mUpdateRow.setVisibility(c.c(getActivity()) ? 0 : 8);
    }

    private void setUpCommonButtons() {
        CheckBox checkBox = (CheckBox) this.mView.findViewById(R.id.history_checkbox);
        checkBox.setChecked(SharedPreferencesUtility.k(this.mActivity));
        int i = 0;
        checkBox.setVisibility(0);
        checkBox.setOnCheckedChangeListener(new TrackingOnCheckedChangeListener() {
            public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
                SharedPreferencesUtility.a((Context) UserSettingsFragment.this.mActivity, z);
                b.a().a("history_enabled", "your_account_settings", String.valueOf(z));
            }
        });
        final TextView textView = (TextView) this.mView.findViewById(R.id.clear_viewing_history_row);
        if (com.etsy.android.ui.homescreen.b.a().d()) {
            disableClearViewHistory(textView);
        } else {
            textView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    UserSettingsFragment.this.getRequestQueue().a((com.etsy.android.lib.core.http.request.a<?, ?, ?>) com.etsy.android.ui.homescreen.b.a().a(UserSettingsFragment.this.getActivity(), new com.etsy.android.ui.homescreen.b.a() {
                        public void a() {
                            com.etsy.android.contentproviders.a.a(UserSettingsFragment.this.mActivity);
                            UserSettingsFragment.this.disableClearViewHistory(textView);
                        }
                    }));
                }
            });
        }
        final TextView textView2 = (TextView) this.mView.findViewById(R.id.clear_search_history_row);
        if (com.etsy.android.contentproviders.a.b(this.mActivity) < 1) {
            disableClearSearchHistory(textView2);
        } else {
            textView2.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    com.etsy.android.contentproviders.a.c(UserSettingsFragment.this.mActivity);
                    UserSettingsFragment.this.disableClearSearchHistory(textView2);
                }
            });
        }
        this.mView.findViewById(R.id.about_row).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                e.a((FragmentActivity) UserSettingsFragment.this.mActivity).a().b(0);
            }
        });
        this.mUpdateRow = this.mView.findViewById(R.id.update_row);
        this.mUpdateRow.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (!c.b()) {
                    UserSettingsFragment.this.startActivity(c.a(UserSettingsFragment.this.getAnalyticsContext()));
                } else if (UserSettingsFragment.this.mAlphaUpdateCheck.a()) {
                    UserSettingsFragment.this.showUpdateRow(true);
                    UserSettingsFragment.this.mAlphaUpdateCheck.a((g.b) new g.b() {
                        public void a(boolean z) {
                            UserSettingsFragment.this.showUpdateRow(false);
                        }
                    });
                } else if (!UserSettingsFragment.this.getConfigMap().b()) {
                    Toast.makeText(UserSettingsFragment.this.getActivity(), "You need to be logged in as an admin to update from an internal build", 1).show();
                } else {
                    Toast.makeText(UserSettingsFragment.this.getActivity(), "Uh oh. Couldn't fetch a build.", 1).show();
                }
            }
        });
        if (getConfigMap().b() || getConfigMap().c(com.etsy.android.lib.config.b.e)) {
            this.mView.findViewById(R.id.currency_row).setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a((FragmentActivity) UserSettingsFragment.this.mActivity).f().a(UserSettingsFragment.this.mParentDialog.getChildFragmentManager()).a(AnimationMode.FADE).b((int) R.id.inner_fragment_container).a((CurrencySelectFragment.a) new CurrencySelectFragment.a() {
                        public void a(EtsyCurrency etsyCurrency) {
                            Locale locale = Locale.getDefault();
                            ((TextView) UserSettingsFragment.this.mView.findViewById(R.id.currency_display_current)).setText(etsyCurrency.getUnit().getCurrencySymbol());
                            ((TextView) UserSettingsFragment.this.mView.findViewById(R.id.currency_code)).setText(etsyCurrency.getUnit().getCurrencyCodeIfNotEqualToSupplied("USD"));
                            UserSettingsFragment.this.mParentDialog.setTitle(UserSettingsFragment.this.mActivity.getString(R.string.settings));
                            UserSettingsFragment.this.getAnalyticsContext().a("set_locale_preferences", new UserSettingsFragment$7$1$1(this, locale));
                        }

                        public void a() {
                            UserSettingsFragment.this.mParentDialog.setTitle(UserSettingsFragment.this.mActivity.getString(R.string.settings));
                        }
                    });
                }
            });
            String d = CurrencyUtil.d();
            ((TextView) this.mView.findViewById(R.id.currency_display_current)).setText(d);
            ((TextView) this.mView.findViewById(R.id.currency_code)).setText(CurrencyUtil.a(CurrencyUtil.c(), d));
        } else {
            this.mView.findViewById(R.id.currency_row).setVisibility(8);
            this.mView.findViewById(R.id.currency_divider).setVisibility(8);
        }
        View findViewById = this.mView.findViewById(R.id.phablets_row);
        if (getConfigMap().b()) {
            findViewById.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a((FragmentActivity) UserSettingsFragment.this.mActivity).a().o();
                }
            });
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(8);
        }
        if (d.b()) {
            this.mView.findViewById(R.id.shake_row).setVisibility(0);
            CheckBox checkBox2 = (CheckBox) this.mView.findViewById(R.id.shake_checkbox);
            checkBox2.setChecked(SharedPreferencesUtility.l(this.mActivity));
            checkBox2.setOnCheckedChangeListener(new TrackingOnCheckedChangeListener() {
                public void onViewCheckedChanged(CompoundButton compoundButton, boolean z) {
                    SharedPreferencesUtility.b((Context) UserSettingsFragment.this.mActivity, z);
                }
            });
        }
        this.mView.findViewById(R.id.legal_row).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (UserSettingsFragment.this.getConfigMap().c(com.etsy.android.lib.config.b.ap)) {
                    e.a((FragmentActivity) UserSettingsFragment.this.mActivity).a().b(10);
                } else {
                    e.a((FragmentActivity) UserSettingsFragment.this.mActivity).f().a(UserSettingsFragment.this.mParentDialog.getChildFragmentManager()).a(AnimationMode.FADE).b((int) R.id.inner_fragment_container).p();
                }
            }
        });
        View findViewById2 = this.mView.findViewById(R.id.vespa_page_section);
        if (findViewById2 != null) {
            findViewById2.setVisibility(d.a() ? 0 : 8);
            findViewById2.findViewById(R.id.vespa_page_row).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    e.a(UserSettingsFragment.this.getActivity()).a().i(null);
                }
            });
            findViewById2.findViewById(R.id.vespa_explore_page_row).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    e.a(UserSettingsFragment.this.getActivity()).a().i("explore_demo_page");
                }
            });
        }
        View findViewById3 = this.mView.findViewById(R.id.custom_view_demo_section);
        if (findViewById3 != null) {
            if (!d.a()) {
                i = 8;
            }
            findViewById3.setVisibility(i);
            findViewById3.findViewById(R.id.custom_view_demo_row).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    e.a(UserSettingsFragment.this.getActivity()).a().x();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showUpdateRow(boolean z) {
        ((TextView) this.mUpdateRow.findViewById(R.id.update_text)).setText(z ? R.string.updating : R.string.update);
        this.mUpdateRow.findViewById(R.id.update_progress_bar).setVisibility(z ? 0 : 8);
        this.mUpdateRow.setEnabled(!z);
    }

    /* access modifiers changed from: private */
    public void disableClearViewHistory(TextView textView) {
        textView.setText(R.string.viewing_history_cleared);
        textView.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void disableClearSearchHistory(TextView textView) {
        textView.setText(R.string.search_history_cleared);
        textView.setEnabled(false);
    }

    private void setUpSignedInButtons() {
        this.mView.findViewById(R.id.signed_in_settings).setVisibility(0);
        ((TextView) this.mView.findViewById(R.id.connected_title)).setText(String.format(Locale.getDefault(), getString(R.string.connected_as_user), new Object[]{SharedPreferencesUtility.d(this.mActivity)}).toUpperCase(Locale.getDefault()));
        TextView textView = (TextView) this.mView.findViewById(R.id.notifications_row);
        if (this.pushRegistration.a()) {
            textView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    e.a((FragmentActivity) UserSettingsFragment.this.mActivity).a().n();
                }
            });
        } else if (com.etsy.android.lib.messaging.a.b((Context) this.mActivity)) {
            textView.setText(R.string.get_notifications);
            textView.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    com.etsy.android.lib.messaging.a.b((Activity) UserSettingsFragment.this.mActivity);
                }
            });
        } else {
            textView.setVisibility(8);
        }
        this.mView.findViewById(R.id.sign_out_button).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                UserSettingsFragment.this.signOut();
            }
        });
    }

    /* access modifiers changed from: private */
    public void signOut() {
        v.a().f();
        this.mParentDialog.dismiss();
        this.mActivity.finish();
    }

    @NonNull
    public OnClickListener getOnClickListener() {
        return this.mClickListener;
    }
}
