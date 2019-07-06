package com.etsy.android.ui.convos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.R;
import com.etsy.android.lib.convos.a.d;
import com.etsy.android.lib.convos.adapters.ConvoThreadAdapter;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.models.BaseModelImage;
import com.etsy.android.lib.models.convo.adapter.IConvoPresentationItem;
import com.etsy.android.lib.models.convo.adapter.ManufacturerProjectHeaderItem;
import com.etsy.android.lib.models.convo.adapter.ManufacturerProjectImagesItem;
import com.etsy.android.lib.models.convo.adapter.ManufacturerProjectItemDecoration;
import com.etsy.android.lib.models.convo.adapter.ManufacturerProjectRelatedListingItem;
import com.etsy.android.lib.models.convo.adapter.ManufacturerProjectStringItem;
import com.etsy.android.lib.models.convo.adapter.ManufacturerProjectSummaryItem;
import com.etsy.android.lib.models.convo.context.ManufacturerProject;
import com.etsy.android.lib.models.convo.context.ManufacturerProjectCapability;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.util.af;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.BaseRecyclerViewListFragment;
import com.etsy.android.uikit.adapter.BaseRecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ManufacturerProjectFragment extends BaseRecyclerViewListFragment<IConvoPresentationItem> implements d, a {
    com.etsy.android.lib.util.b.a fileSupport;
    private ManufacturerProject manufacturerProject;

    public void draftMessageChanged(@NonNull String str, int i, int i2) {
    }

    public void requestAddImage() {
    }

    public void requestAddSnippet() {
    }

    public void requestSendMessage() {
    }

    public void showManufacturingProject(@NonNull ManufacturerProjectSummaryItem manufacturerProjectSummaryItem) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.manufacturerProject = (ManufacturerProject) org.parceler.d.a(bundle.getParcelable("manufacturer_project"));
        } else if (getArguments() != null) {
            this.manufacturerProject = (ManufacturerProject) org.parceler.d.a(getArguments().getParcelable("manufacturer_project"));
        }
    }

    /* access modifiers changed from: protected */
    @NonNull
    public LayoutManager createLayoutManager() {
        return new GridLayoutManager(getActivity(), 1);
    }

    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mAdapter = new ConvoThreadAdapter(this, getActivity(), getImageBatch(), this.fileSupport);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mRecyclerView.addItemDecoration(new ManufacturerProjectItemDecoration(getActivity()));
        this.mRecyclerView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.sk_white));
        if (this.manufacturerProject != null) {
            setupManufacturerProject(this.manufacturerProject);
        }
        return onCreateView;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putParcelable("manufacturer_project", org.parceler.d.a(this.manufacturerProject));
    }

    /* access modifiers changed from: protected */
    public void onLoadContent() {
        setupManufacturerProject(this.manufacturerProject);
    }

    private void stopRefreshing() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        setLoading(false);
        setRefreshing(false);
    }

    private void setupManufacturerProject(@NonNull ManufacturerProject manufacturerProject2) {
        this.manufacturerProject = manufacturerProject2;
        this.mAdapter.clear();
        this.mAdapter.addItem(new ManufacturerProjectHeaderItem(manufacturerProject2));
        if (!manufacturerProject2.getProjectImages().isEmpty() || !manufacturerProject2.getListingImages().isEmpty()) {
            this.mAdapter.addItem(new ManufacturerProjectImagesItem(manufacturerProject2));
        }
        this.mAdapter.addItem(new ManufacturerProjectStringItem(7, getString(R.string.quantity)));
        this.mAdapter.addItem(new ManufacturerProjectStringItem(8, manufacturerProject2.getQuantity()));
        this.mAdapter.addItem(new ManufacturerProjectStringItem(6, getString(R.string.cost_per_item)));
        this.mAdapter.addItem(new ManufacturerProjectStringItem(8, ManufacturerProjectSummaryItem.formatBudget(manufacturerProject2)));
        this.mAdapter.addItem(new ManufacturerProjectStringItem(6, getString(R.string.need_by)));
        this.mAdapter.addItem(new ManufacturerProjectStringItem(8, ManufacturerProjectSummaryItem.formatTimeline(getActivity(), manufacturerProject2)));
        if (!manufacturerProject2.getHumanReadableDevelopmentMaterials().isEmpty()) {
            this.mAdapter.addItem(new ManufacturerProjectStringItem(7, getString(R.string.designer_has)));
            for (String manufacturerProjectStringItem : manufacturerProject2.getHumanReadableDevelopmentMaterials()) {
                this.mAdapter.addItem(new ManufacturerProjectStringItem(9, manufacturerProjectStringItem));
            }
        }
        if (af.b(manufacturerProject2.getListingTitle()) && manufacturerProject2.getListingId() != null && af.b(manufacturerProject2.getListingId().getId())) {
            this.mAdapter.addItem(new ManufacturerProjectRelatedListingItem(manufacturerProject2.getListingId(), manufacturerProject2.getListingTitle()));
        }
        this.mAdapter.addItem(new ManufacturerProjectStringItem(7, getString(R.string.project_description)));
        this.mAdapter.addItem(new ManufacturerProjectStringItem(8, manufacturerProject2.getDescription()));
        if (manufacturerProject2.hasDesignerMadeBefore()) {
            this.mAdapter.addItem(new ManufacturerProjectStringItem(9, String.format(getString(R.string.designer_has_made_before), new Object[]{manufacturerProject2.getDesignerRealName()})));
        }
        if (!manufacturerProject2.getHumanReadableCapabilities().isEmpty()) {
            boolean z = true;
            for (String str : manufacturerProject2.getHumanReadableCapabilities().keySet()) {
                int size = ((List) manufacturerProject2.getHumanReadableCapabilities().get(str)).size();
                if (size > 0) {
                    BaseRecyclerViewAdapter baseRecyclerViewAdapter = this.mAdapter;
                    int i = z ? 7 : 6;
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.substring(0, 1).toUpperCase(Locale.ROOT));
                    sb.append(str.substring(1));
                    baseRecyclerViewAdapter.addItem(new ManufacturerProjectStringItem(i, sb.toString()));
                    String[] strArr = new String[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        strArr[i2] = ((ManufacturerProjectCapability) ((List) manufacturerProject2.getHumanReadableCapabilities().get(str)).get(i2)).getDescription();
                    }
                    this.mAdapter.addItem(new ManufacturerProjectStringItem(8, TextUtils.join(", ", strArr)));
                    z = false;
                }
            }
        }
        showListView();
        stopRefreshing();
    }

    public void showImages(@NonNull ArrayList<? extends BaseModelImage> arrayList, int i) {
        e.a(getActivity()).a().a(arrayList, i);
    }

    public void showListing(@NonNull EtsyId etsyId) {
        e.a(getActivity()).a().a(etsyId);
    }
}
