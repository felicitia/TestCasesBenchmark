package com.contextlogic.wish.activity.settings.notifications;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.model.WishNotificationPreference;
import com.contextlogic.wish.api.model.WishNotificationPreference.PreferenceType;
import java.util.ArrayList;

public class NotificationSettingsAdapter extends BaseAdapter {
    private Context mContext;
    protected OnItemSelectedListener mListener;
    private PreferenceType mPreferenceType;
    private DataProvider mProvider;

    public interface DataProvider {
        ArrayList<WishNotificationPreference> getData();

        String getUserPhoneNumber();

        boolean hasSmsPreference();

        boolean isUserSmsBlocked();
    }

    public interface OnItemSelectedListener {
        void onPushNotiPrefItemSelected(WishNotificationPreference wishNotificationPreference, boolean z);

        void onSmsNotiPrefItemSelected(boolean z);
    }

    static class ViewHolder {
        CheckBox checkbox;
        TextView description;
        TextView title;

        ViewHolder() {
        }
    }

    enum ViewType {
        PUSH,
        SMS
    }

    public long getItemId(int i) {
        return 0;
    }

    public NotificationSettingsAdapter(Context context, DataProvider dataProvider, OnItemSelectedListener onItemSelectedListener, PreferenceType preferenceType) {
        this.mContext = context;
        this.mProvider = dataProvider;
        this.mListener = onItemSelectedListener;
        this.mPreferenceType = preferenceType;
    }

    public int getCount() {
        if (this.mProvider == null || this.mProvider.getData() == null) {
            return 0;
        }
        int size = this.mProvider.getData().size();
        if (this.mProvider.hasSmsPreference()) {
            size++;
        }
        return size;
    }

    public WishNotificationPreference getItem(int i) {
        if (i < getCount()) {
            return (WishNotificationPreference) this.mProvider.getData().get(i);
        }
        return null;
    }

    public int getItemViewType(int i) {
        if (i != getCount() - 1 || !this.mProvider.hasSmsPreference()) {
            return ViewType.PUSH.ordinal();
        }
        return ViewType.SMS.ordinal();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.separated_notification_settings_row, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.notification_settings_row_title);
            viewHolder.description = (TextView) view.findViewById(R.id.notification_settings_row_description);
            viewHolder.checkbox = (CheckBox) view.findViewById(R.id.notification_settings_row_checkbox);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (getItemViewType(i) == ViewType.PUSH.ordinal()) {
            final WishNotificationPreference item = getItem(i);
            if (item == null) {
                return null;
            }
            viewHolder.title.setText(item.getName());
            viewHolder.description.setText(item.getDescription());
            viewHolder.checkbox.setChecked(item.isPreferenceSelected(this.mPreferenceType));
            viewHolder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    NotificationSettingsAdapter.this.mListener.onPushNotiPrefItemSelected(item, z);
                }
            });
            return view;
        }
        String userPhoneNumber = this.mProvider.getUserPhoneNumber();
        if (TextUtils.isEmpty(userPhoneNumber)) {
            return null;
        }
        viewHolder.title.setText(this.mContext.getString(R.string.sms_title));
        viewHolder.description.setText(this.mContext.getString(R.string.sms_description, new Object[]{userPhoneNumber}));
        viewHolder.checkbox.setChecked(!this.mProvider.isUserSmsBlocked());
        viewHolder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NotificationSettingsAdapter.this.mListener.onSmsNotiPrefItemSelected(z);
            }
        });
        return view;
    }
}
