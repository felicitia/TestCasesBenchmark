package com.etsy.android.ui.favorites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.models.apiv3.Collection;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.s;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyTrioDialogFragment.a;
import com.etsy.android.ui.util.CollectionUtil;
import com.etsy.android.ui.util.CollectionUtil.e;
import com.etsy.android.uikit.ui.dialog.IDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class CollectionsEditFragment extends EtsyFragment implements e {
    Collection mCollection;
    RelativeLayout mCollectionForm;
    EditText mCollectionNameField;
    /* access modifiers changed from: private */
    public CollectionUtil mCollectionUtils = null;
    private View mDeleteButton;
    RadioButton mPrivateButton;
    RadioButton mPublicButton;
    /* access modifiers changed from: private */
    public Button mSaveButton;

    @NonNull
    public String getTrackingName() {
        return "list_edit_overlay_open";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCollection = (Collection) getArguments().getSerializable(Collection.TYPE_COLLECTION);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_edit_collection, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_title);
        View findViewById = inflate.findViewById(R.id.name_title);
        this.mCollectionForm = (RelativeLayout) inflate.findViewById(R.id.collection_form);
        this.mCollectionNameField = (EditText) inflate.findViewById(R.id.collection_name);
        this.mPublicButton = (RadioButton) inflate.findViewById(R.id.privacy_option_public);
        this.mPrivateButton = (RadioButton) inflate.findViewById(R.id.privacy_option_private);
        this.mSaveButton = (Button) inflate.findViewById(R.id.button_save);
        this.mDeleteButton = inflate.findViewById(R.id.delete_button);
        this.mCollectionForm.setOnClickListener(new TrackingOnClickListener(this.mCollection) {
            public void onViewClick(View view) {
                CollectionsEditFragment.this.goBack();
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.collection_edit));
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(this.mCollection.getName());
        textView.setText(sb.toString());
        if (this.mCollection.isTypeFavorites()) {
            findViewById.setVisibility(8);
            this.mCollectionNameField.setVisibility(8);
            ((TextView) inflate.findViewById(R.id.privacy_title)).setText(getString(R.string.privacy_description_question_favorites));
            this.mPublicButton.setText(getString(R.string.privacy_description_public_favorites));
            this.mPrivateButton.setText(getString(R.string.privacy_description_private_favorites));
        } else {
            this.mCollectionNameField.setText(this.mCollection.getName());
        }
        if (this.mCollection.isPrivate()) {
            this.mPublicButton.setChecked(false);
            this.mPrivateButton.setChecked(true);
        } else {
            this.mPublicButton.setChecked(true);
            this.mPrivateButton.setChecked(false);
        }
        this.mSaveButton.setOnClickListener(new TrackingOnClickListener(this.mCollection) {
            public void onViewClick(View view) {
                CollectionsEditFragment.this.mSaveButton.setEnabled(false);
                CollectionsEditFragment.this.mCollectionNameField.clearFocus();
                s.a((View) CollectionsEditFragment.this.mCollectionNameField);
                CollectionsEditFragment.this.mCollectionUtils.a((Activity) CollectionsEditFragment.this.mActivity, (e) CollectionsEditFragment.this, CollectionsEditFragment.this.mCollection.getKey(), CollectionsEditFragment.this.mCollection.isTypeFavorites() ? null : CollectionsEditFragment.this.mCollectionNameField.getText().toString(), CollectionsEditFragment.this.mPrivateButton.isChecked());
            }
        });
        if (this.mCollection.isTypeFavorites()) {
            this.mDeleteButton.setVisibility(8);
        } else {
            this.mDeleteButton.setOnClickListener(new TrackingOnClickListener(this.mCollection) {
                public void onViewClick(View view) {
                    CollectionsEditFragment.this.confirmDelete();
                }
            });
        }
        return inflate;
    }

    /* access modifiers changed from: private */
    public void deleteCollection() {
        this.mDeleteButton.setEnabled(false);
        this.mCollectionUtils.a(this.mActivity, this, this.mCollection.getKey());
    }

    /* access modifiers changed from: private */
    public void confirmDelete() {
        com.etsy.android.ui.nav.e.a(getActivity()).d().a((a) new a() {
            public void b() {
            }

            public void c() {
            }

            public void a() {
                CollectionsEditFragment.this.deleteCollection();
            }
        }, (int) R.string.delete, (int) R.string.cancel, 0, getString(R.string.collection_delete_warning_message));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!this.mCollection.isTypeFavorites()) {
            this.mCollection.setName(this.mCollectionNameField.getText().toString());
        }
        if (this.mPublicButton.isChecked()) {
            this.mCollection.setPrivacy(Collection.PRIVACY_LEVEL_PUBLIC);
        } else {
            this.mCollection.setPrivacy("private");
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getParentFragment() instanceof IDialogFragment) {
            IDialogFragment iDialogFragment = (IDialogFragment) getParentFragment();
            iDialogFragment.setTitle(this.mActivity.getString(R.string.collection_edit));
            iDialogFragment.setOkButtonVisibility(8);
        }
        this.mCollectionUtils = new CollectionUtil(getActivity(), this, "list_edit_overlay_open");
    }

    private void setResult(int i, Collection collection) {
        Intent intent = this.mActivity.getIntent();
        intent.putExtra(Collection.TYPE_COLLECTION, collection);
        if (this.mActivity.getParent() == null) {
            this.mActivity.setResult(i, intent);
        } else {
            this.mActivity.getParent().setResult(i, intent);
        }
        this.mActivity.setResult(i, intent);
    }

    public void onCollectionEdited(Collection collection) {
        setResult(612, collection);
        goBack();
    }

    public void onCollectionDeleted() {
        setResult(611, this.mCollection);
        goBack();
    }

    public void onCollectionError(String str) {
        this.mSaveButton.setEnabled(true);
        this.mDeleteButton.setEnabled(true);
        aj.a((Context) getActivity(), str);
    }

    public boolean handleBackPressed() {
        goBack();
        return true;
    }

    /* access modifiers changed from: private */
    public void goBack() {
        this.mActivity.finish();
    }
}
