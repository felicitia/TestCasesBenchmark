package com.contextlogic.wish.activity.developer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LogoutCallback;
import com.contextlogic.wish.application.DeviceIdManager;
import com.contextlogic.wish.application.DeviceIdManager.DeviceIdCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.http.HttpCookieManager;
import com.contextlogic.wish.http.ServerConfig;
import com.contextlogic.wish.ui.text.ThemedDropdownEditText;
import com.contextlogic.wish.util.AddressUtil;
import com.contextlogic.wish.util.DeviceUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class DeveloperSettingsFragment extends UiFragment<DeveloperSettingsActivity> {
    private TextView mAdvertisingIdText;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> mCountryAdapter;
    /* access modifiers changed from: private */
    public Spinner mCountrySpinner;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> mCreditCardProcessorAdapter;
    /* access modifiers changed from: private */
    public Spinner mCreditCardProcessorSpinner;
    /* access modifiers changed from: private */
    public HashMap<PaymentProcessor, String> mCreditCardProcessors;
    /* access modifiers changed from: private */
    public CheckBox mDeveloperFacebookCheckBox;
    /* access modifiers changed from: private */
    public TextView mDeviceIdText;
    private CheckBox mForceAllowRotationCheckbox;
    /* access modifiers changed from: private */
    public EditText mServerPasswordText;
    /* access modifiers changed from: private */
    public ThemedDropdownEditText mServerPathText;
    /* access modifiers changed from: private */
    public EditText mServerUsernameText;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.developer_settings_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367050, getServerSuggestions());
        this.mServerPathText = (ThemedDropdownEditText) findViewById(R.id.developer_settings_fragment_server_text);
        this.mServerPathText.setAdapter(arrayAdapter);
        this.mServerPathText.setClearButton(WishApplication.getInstance().getResources().getDrawable(R.drawable.textview_clear));
        this.mServerUsernameText = (EditText) findViewById(R.id.developer_settings_fragment_server_username_text);
        this.mServerPasswordText = (EditText) findViewById(R.id.developer_settings_fragment_server_password_text);
        this.mServerPathText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                DeveloperSettingsFragment.this.updateUsernamePasswordVisibility();
            }
        });
        this.mCountrySpinner = (Spinner) findViewById(R.id.developer_settings_fragment_country_spinner);
        withActivity(new ActivityTask<DeveloperSettingsActivity>() {
            public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("Default");
                arrayList.addAll(AddressUtil.getCountries().values());
                if (arrayList.size() != 1) {
                    Collections.sort(arrayList.subList(1, arrayList.size()));
                    DeveloperSettingsFragment.this.mCountryAdapter = new ArrayAdapter(developerSettingsActivity, R.layout.spinner_item, arrayList);
                    DeveloperSettingsFragment.this.mCountryAdapter.setDropDownViewResource(17367049);
                    DeveloperSettingsFragment.this.mCountrySpinner.setAdapter(DeveloperSettingsFragment.this.mCountryAdapter);
                    DeveloperSettingsFragment.this.mCountrySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }

                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                            String str;
                            String str2 = (String) DeveloperSettingsFragment.this.mCountryAdapter.getItem(i);
                            if (str2 != null && !str2.equals("Default")) {
                                Iterator it = AddressUtil.getCountries().entrySet().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    Entry entry = (Entry) it.next();
                                    if (((String) entry.getValue()).equals(str2)) {
                                        str = (String) entry.getKey();
                                        break;
                                    }
                                }
                                ServerConfig.getInstance().setApiCountryCode(str);
                            }
                            str = null;
                            ServerConfig.getInstance().setApiCountryCode(str);
                        }
                    });
                    return;
                }
                DeveloperSettingsFragment.this.mCountrySpinner.setVisibility(8);
                ((TextView) DeveloperSettingsFragment.this.findViewById(R.id.developer_settings_fragment_login_message)).setText("Please log in first to change country settings.");
            }
        });
        this.mDeveloperFacebookCheckBox = (CheckBox) findViewById(R.id.developer_settings_fragment_developer_facebook_checkbox);
        ((TextView) findViewById(R.id.developer_settings_fragment_change_server_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                final String extractEditTextValue = ViewUtil.extractEditTextValue(DeveloperSettingsFragment.this.mServerPathText);
                final String extractEditTextValue2 = DeveloperSettingsFragment.this.mServerUsernameText.getVisibility() == 0 ? ViewUtil.extractEditTextValue(DeveloperSettingsFragment.this.mServerUsernameText) : null;
                final String extractEditTextValue3 = DeveloperSettingsFragment.this.mServerPasswordText.getVisibility() == 0 ? ViewUtil.extractEditTextValue(DeveloperSettingsFragment.this.mServerPasswordText) : null;
                final boolean isChecked = DeveloperSettingsFragment.this.mDeveloperFacebookCheckBox.isChecked();
                DeveloperSettingsFragment developerSettingsFragment = DeveloperSettingsFragment.this;
                AnonymousClass1 r0 = new ServiceTask<DeveloperSettingsActivity, ServiceFragment>() {
                    public void performTask(DeveloperSettingsActivity developerSettingsActivity, ServiceFragment serviceFragment) {
                        if (AuthenticationDataCenter.getInstance().isLoggedIn()) {
                            developerSettingsActivity.showLoadingDialog();
                            serviceFragment.getAuthenticationService().logout(false, true, new LogoutCallback() {
                                public void onSuccess() {
                                    DeveloperSettingsFragment.this.withActivity(new ActivityTask<DeveloperSettingsActivity>() {
                                        public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                                            developerSettingsActivity.hideLoadingDialog();
                                            if (extractEditTextValue != null) {
                                                ServerConfig.getInstance().setServerHost(extractEditTextValue);
                                            }
                                            if (!(extractEditTextValue2 == null || extractEditTextValue3 == null)) {
                                                ServerConfig.getInstance().setApiCredentials(extractEditTextValue2, extractEditTextValue3);
                                            }
                                            PreferenceUtil.setBoolean("DevSettingsUseDevFbApp", isChecked);
                                            developerSettingsActivity.closeForLogout();
                                        }
                                    });
                                }

                                public void onFailure(final String str) {
                                    DeveloperSettingsFragment.this.withActivity(new ActivityTask<DeveloperSettingsActivity>() {
                                        public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                                            developerSettingsActivity.hideLoadingDialog();
                                            developerSettingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        if (extractEditTextValue != null) {
                            ServerConfig.getInstance().setServerHost(extractEditTextValue);
                        }
                        if (!(extractEditTextValue2 == null || extractEditTextValue3 == null)) {
                            ServerConfig.getInstance().setApiCredentials(extractEditTextValue2, extractEditTextValue3);
                        }
                        PreferenceUtil.setBoolean("DevSettingsUseDevFbApp", isChecked);
                        developerSettingsActivity.finishActivity();
                    }
                };
                developerSettingsFragment.withServiceFragment(r0);
            }
        });
        View findViewById = findViewById(R.id.developer_settings_fragment_admin_code_area);
        if (!AuthenticationDataCenter.getInstance().isLoggedIn() || !ProfileDataCenter.getInstance().isAdmin()) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            final EditText editText = (EditText) findViewById(R.id.developer_settings_fragment_admin_code_text);
            ((TextView) findViewById(R.id.developer_settings_fragment_admin_code_button)).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PreferenceUtil.setString("AdminLoginCode", ViewUtil.extractEditTextValue(editText));
                    DeveloperSettingsFragment.this.withServiceFragment(new ServiceTask<DeveloperSettingsActivity, ServiceFragment>() {
                        public void performTask(DeveloperSettingsActivity developerSettingsActivity, ServiceFragment serviceFragment) {
                            if (AuthenticationDataCenter.getInstance().isLoggedIn()) {
                                developerSettingsActivity.showLoadingDialog();
                                serviceFragment.getAuthenticationService().logout(false, true, new LogoutCallback() {
                                    public void onSuccess() {
                                        DeveloperSettingsFragment.this.withActivity(new ActivityTask<DeveloperSettingsActivity>() {
                                            public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                                                developerSettingsActivity.hideLoadingDialog();
                                                developerSettingsActivity.closeForLogout();
                                            }
                                        });
                                    }

                                    public void onFailure(final String str) {
                                        DeveloperSettingsFragment.this.withActivity(new ActivityTask<DeveloperSettingsActivity>() {
                                            public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                                                developerSettingsActivity.hideLoadingDialog();
                                                developerSettingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                                            }
                                        });
                                    }
                                });
                                return;
                            }
                            developerSettingsActivity.finishActivity();
                        }
                    });
                }
            });
        }
        ((TextView) findViewById(R.id.developer_settings_fragment_experiments_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingsFragment.this.withActivity(new ActivityTask<DeveloperSettingsActivity>() {
                    public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                        Intent intent = new Intent();
                        intent.setClass(developerSettingsActivity, DeveloperSettingsExperimentsActivity.class);
                        developerSettingsActivity.startActivity(intent);
                    }
                });
            }
        });
        ((TextView) findViewById(R.id.developer_settings_fragment_clear_image_cache_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingsFragment.this.withServiceFragment(new ServiceTask<BaseActivity, DeveloperSettingsServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, DeveloperSettingsServiceFragment developerSettingsServiceFragment) {
                        developerSettingsServiceFragment.clearImageCache();
                    }
                });
            }
        });
        ((TextView) findViewById(R.id.developer_settings_fragment_clear_webview_cache_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingsFragment.this.withActivity(new ActivityTask<DeveloperSettingsActivity>() {
                    public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                        try {
                            new WebView(developerSettingsActivity).clearCache(true);
                        } catch (Throwable unused) {
                        }
                        try {
                            developerSettingsActivity.deleteDatabase("webview.db");
                        } catch (Throwable unused2) {
                        }
                        try {
                            developerSettingsActivity.deleteDatabase("webviewCache.db");
                        } catch (Throwable unused3) {
                        }
                        HttpCookieManager.clearWebViewCookies();
                        developerSettingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonOkDialog("Done", "WebView cache cleared"));
                    }
                });
            }
        });
        ((TextView) findViewById(R.id.developer_settings_fragment_clear_device_seen_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingsFragment.this.withServiceFragment(new ServiceTask<BaseActivity, DeveloperSettingsServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, DeveloperSettingsServiceFragment developerSettingsServiceFragment) {
                        developerSettingsServiceFragment.clearDeviceSeenHistory();
                    }
                });
            }
        });
        this.mCreditCardProcessors = new LinkedHashMap();
        this.mCreditCardProcessors.put(PaymentProcessor.Braintree, "Braintree");
        this.mCreditCardProcessors.put(PaymentProcessor.Stripe, "Stripe");
        this.mCreditCardProcessors.put(PaymentProcessor.Adyen, "Adyen");
        this.mCreditCardProcessorSpinner = (Spinner) findViewById(R.id.developer_settings_fragment_credit_card_processor_spinner);
        withActivity(new ActivityTask<DeveloperSettingsActivity>() {
            public void performTask(DeveloperSettingsActivity developerSettingsActivity) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("Default");
                arrayList.addAll(DeveloperSettingsFragment.this.mCreditCardProcessors.values());
                DeveloperSettingsFragment.this.mCreditCardProcessorAdapter = new ArrayAdapter(developerSettingsActivity, R.layout.spinner_item, arrayList);
                DeveloperSettingsFragment.this.mCreditCardProcessorAdapter.setDropDownViewResource(17367049);
                DeveloperSettingsFragment.this.mCreditCardProcessorSpinner.setAdapter(DeveloperSettingsFragment.this.mCreditCardProcessorAdapter);
                DeveloperSettingsFragment.this.mCreditCardProcessorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:12:0x004b  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onItemSelected(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                        /*
                            r0 = this;
                            com.contextlogic.wish.activity.developer.DeveloperSettingsFragment$9 r1 = com.contextlogic.wish.activity.developer.DeveloperSettingsFragment.AnonymousClass9.this
                            com.contextlogic.wish.activity.developer.DeveloperSettingsFragment r1 = com.contextlogic.wish.activity.developer.DeveloperSettingsFragment.this
                            android.widget.ArrayAdapter r1 = r1.mCreditCardProcessorAdapter
                            java.lang.Object r1 = r1.getItem(r3)
                            java.lang.String r1 = (java.lang.String) r1
                            r2 = 0
                            if (r1 == 0) goto L_0x0048
                            java.lang.String r3 = "Default"
                            boolean r3 = r1.equals(r3)
                            if (r3 != 0) goto L_0x0048
                            com.contextlogic.wish.activity.developer.DeveloperSettingsFragment$9 r3 = com.contextlogic.wish.activity.developer.DeveloperSettingsFragment.AnonymousClass9.this
                            com.contextlogic.wish.activity.developer.DeveloperSettingsFragment r3 = com.contextlogic.wish.activity.developer.DeveloperSettingsFragment.this
                            java.util.HashMap r3 = r3.mCreditCardProcessors
                            java.util.Set r3 = r3.entrySet()
                            java.util.Iterator r3 = r3.iterator()
                        L_0x0029:
                            boolean r4 = r3.hasNext()
                            if (r4 == 0) goto L_0x0048
                            java.lang.Object r4 = r3.next()
                            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                            java.lang.Object r5 = r4.getValue()
                            java.lang.String r5 = (java.lang.String) r5
                            boolean r5 = r5.equals(r1)
                            if (r5 == 0) goto L_0x0029
                            java.lang.Object r1 = r4.getKey()
                            com.contextlogic.wish.api.model.WishCart$PaymentProcessor r1 = (com.contextlogic.wish.api.model.WishCart.PaymentProcessor) r1
                            goto L_0x0049
                        L_0x0048:
                            r1 = r2
                        L_0x0049:
                            if (r1 == 0) goto L_0x0053
                            int r1 = r1.getValue()
                            java.lang.String r2 = java.lang.Integer.toString(r1)
                        L_0x0053:
                            java.lang.String r1 = "DevSettingsCreditCardProcessor"
                            com.contextlogic.wish.util.PreferenceUtil.setString(r1, r2)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.developer.DeveloperSettingsFragment.AnonymousClass9.AnonymousClass1.onItemSelected(android.widget.AdapterView, android.view.View, int, long):void");
                    }
                });
            }
        });
        this.mDeviceIdText = (TextView) findViewById(R.id.developer_settings_fragment_device_id_text);
        this.mDeviceIdText.setText("Device ID: null");
        DeviceIdManager.getInstance().registerDeviceIdCallback(new DeviceIdCallback() {
            public void onDeviceIdReceived(String str) {
                TextView access$1100 = DeveloperSettingsFragment.this.mDeviceIdText;
                StringBuilder sb = new StringBuilder();
                sb.append("Device ID: ");
                sb.append(str);
                access$1100.setText(sb.toString());
            }
        });
        this.mAdvertisingIdText = (TextView) findViewById(R.id.developer_settings_fragment_advertising_id_text);
        TextView textView = this.mAdvertisingIdText;
        StringBuilder sb = new StringBuilder();
        sb.append("Ad ID: ");
        sb.append(DeviceUtil.getAdvertisingId());
        textView.setText(sb.toString());
        this.mForceAllowRotationCheckbox = (CheckBox) findViewById(R.id.developer_settings_fragment_force_allow_rotation_checkbox);
        this.mForceAllowRotationCheckbox.setChecked(PreferenceUtil.getBoolean("ForceAllowRotation"));
        this.mForceAllowRotationCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PreferenceUtil.setBoolean("ForceAllowRotation", z);
            }
        });
        initializeValues();
    }

    private void initializeValues() {
        if (getSavedInstanceState() != null) {
            this.mDeveloperFacebookCheckBox.setChecked(getSavedInstanceState().getBoolean("StoredStateDeveloperFacebook"));
        } else {
            this.mServerPathText.setText(ServerConfig.getInstance().getServerHost());
            this.mDeveloperFacebookCheckBox.setChecked(PreferenceUtil.getBoolean("DevSettingsUseDevFbApp"));
        }
        updateUsernamePasswordVisibility();
        if (this.mCountryAdapter != null) {
            HashMap countries = AddressUtil.getCountries();
            String apiCountryCode = ServerConfig.getInstance().getApiCountryCode();
            if (apiCountryCode != null && countries.containsKey(apiCountryCode)) {
                this.mCountrySpinner.setSelection(this.mCountryAdapter.getPosition(countries.get(apiCountryCode)));
            }
        }
        if (this.mCreditCardProcessorAdapter != null) {
            PaymentProcessor creditCardProcessorPreference = PreferenceUtil.getCreditCardProcessorPreference();
            if (creditCardProcessorPreference != null && this.mCreditCardProcessors.containsKey(creditCardProcessorPreference)) {
                this.mCreditCardProcessorSpinner.setSelection(this.mCreditCardProcessorAdapter.getPosition(this.mCreditCardProcessors.get(creditCardProcessorPreference)));
            }
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        super.handleSaveInstanceState(bundle);
        bundle.putBoolean("StoredStateDeveloperFacebook", this.mDeveloperFacebookCheckBox.isChecked());
    }

    /* access modifiers changed from: private */
    public void updateUsernamePasswordVisibility() {
        String extractEditTextValue = ViewUtil.extractEditTextValue(this.mServerPathText);
        if (extractEditTextValue == null || !extractEditTextValue.equals(getString(R.string.testing_server_host))) {
            this.mServerUsernameText.setVisibility(8);
            this.mServerPasswordText.setVisibility(8);
            return;
        }
        this.mServerUsernameText.setVisibility(0);
        this.mServerPasswordText.setVisibility(0);
        String apiUsername = ServerConfig.getInstance().getApiUsername();
        if (apiUsername != null) {
            this.mServerUsernameText.setText(apiUsername);
        } else {
            this.mServerUsernameText.setText("");
        }
        String apiPassword = ServerConfig.getInstance().getApiPassword();
        if (apiPassword != null) {
            this.mServerPasswordText.setText(apiPassword);
        } else {
            this.mServerPasswordText.setText("");
        }
    }

    private ArrayList<String> getServerSuggestions() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getString(R.string.server_host));
        arrayList.add(getString(R.string.testing_server_host));
        arrayList.add("sandbox.wish.com");
        arrayList.add("tarek.corp.contextlogic.com");
        arrayList.add("nop.corp.contextlogic.com");
        arrayList.add("jim.corp.contextlogic.com");
        arrayList.add("alireza-ubuntu.corp.contextlogic.com");
        arrayList.add("wan2.corp.contextlogic.com");
        arrayList.add("adam-desktop.corp.contextlogic.com");
        arrayList.add("josh.corp.contextlogic.com");
        arrayList.add("mnachiappan.corp.contextlogic.com");
        arrayList.add("jchen.corp.contextlogic.com");
        arrayList.add("hzahid.corp.contextlogic.com");
        arrayList.add("nidheesh.corp.contextlogic.com");
        arrayList.add("gsilva.corp.contextlogic.com");
        arrayList.add("enguyen.corp.contextlogic.com");
        arrayList.add("ehardy.corp.contextlogic.com");
        arrayList.add("rresma.corp.contextlogic.com");
        arrayList.add("rlalchandani.corp.contextlogic.com");
        arrayList.add("jbadua.corp.contextlogic.com");
        arrayList.add("safifi.corp.contextlogic.com");
        arrayList.add("yhou.corp.contextlogic.com");
        arrayList.add("hyin.corp.contextlogic.com");
        arrayList.add("jshewakramani.corp.contextlogic.com");
        arrayList.add("jechen.corp.contextlogic.com");
        arrayList.add("goetomo.corp.contextlogic.com");
        arrayList.add("rhu.corp.contextlogic.com");
        arrayList.add("cyao.corp.contextlogic.com");
        arrayList.add("hleung.corp.contextlogic.com");
        arrayList.add("feiteng.corp.contextlogic.com");
        arrayList.add("thaotran.corp.contextlogic.com");
        arrayList.add("wmao.corp.contextlogic.com");
        return arrayList;
    }
}
