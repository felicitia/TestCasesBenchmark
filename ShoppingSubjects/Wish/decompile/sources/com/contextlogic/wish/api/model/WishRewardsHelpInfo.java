package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.api.model.WishRewardsDashboardInfo.BadgeType;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRewardsHelpInfo extends BaseModel implements Parcelable {
    public static final Creator<WishRewardsHelpInfo> CREATOR = new Creator<WishRewardsHelpInfo>() {
        public WishRewardsHelpInfo createFromParcel(Parcel parcel) {
            return new WishRewardsHelpInfo(parcel);
        }

        public WishRewardsHelpInfo[] newArray(int i) {
            return new WishRewardsHelpInfo[i];
        }
    };
    private ArrayList<Chart> mCharts;
    private ArrayList<WishRewardsReturnPolicyInformation> mInformationRows;
    private ArrayList<RowType> mRowTypes;
    private String mTitle;

    public static class Chart extends BaseModel implements Parcelable {
        public static final Creator<Chart> CREATOR = new Creator<Chart>() {
            public Chart createFromParcel(Parcel parcel) {
                return new Chart(parcel);
            }

            public Chart[] newArray(int i) {
                return new Chart[i];
            }
        };
        private ArrayList<String> mRowTitles;
        private ArrayList<Row> mRows;
        private String mTitle;

        public static class Cell extends BaseModel implements Parcelable {
            public static final Creator<Cell> CREATOR = new Creator<Cell>() {
                public Cell createFromParcel(Parcel parcel) {
                    return new Cell(parcel);
                }

                public Cell[] newArray(int i) {
                    return new Cell[i];
                }
            };
            private BadgeType mBadgeType;
            private boolean mBold;
            private String mText;

            public int describeContents() {
                return 0;
            }

            protected Cell(Parcel parcel) {
                this.mBold = parcel.readByte() != 0;
                this.mText = parcel.readString();
                this.mBadgeType = BadgeType.fromInteger(parcel.readInt());
            }

            public Cell(JSONObject jSONObject) throws JSONException, ParseException {
                super(jSONObject);
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeByte(this.mBold ? (byte) 1 : 0);
                parcel.writeString(this.mText);
                parcel.writeInt(this.mBadgeType.getValue());
            }

            /* access modifiers changed from: protected */
            public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
                if (jSONObject.has("badge_type")) {
                    this.mBadgeType = BadgeType.fromInteger(jSONObject.getInt("badge_type"));
                }
                this.mText = jSONObject.optString("text");
                this.mBold = jSONObject.optBoolean("make_bold");
            }

            public boolean isBold() {
                return this.mBold;
            }

            public String getText() {
                return this.mText;
            }

            public BadgeType getBadgeType() {
                return this.mBadgeType;
            }
        }

        public static class Row extends BaseModel implements Parcelable {
            public static final Creator<Row> CREATOR = new Creator<Row>() {
                public Row createFromParcel(Parcel parcel) {
                    return new Row(parcel);
                }

                public Row[] newArray(int i) {
                    return new Row[i];
                }
            };
            ArrayList<Cell> cells;

            public int describeContents() {
                return 0;
            }

            protected Row(Parcel parcel) {
                this.cells = parcel.createTypedArrayList(Cell.CREATOR);
            }

            public Row(JSONObject jSONObject) throws JSONException, ParseException {
                super(jSONObject);
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeTypedList(this.cells);
            }

            /* access modifiers changed from: protected */
            public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
                this.cells = JsonUtil.parseArray(jSONObject, "cells", new DataParser<Cell, JSONObject>() {
                    public Cell parseData(JSONObject jSONObject) throws JSONException, ParseException {
                        return new Cell(jSONObject);
                    }
                });
            }

            public ArrayList<Cell> getCells() {
                return this.cells;
            }
        }

        public int describeContents() {
            return 0;
        }

        public Chart(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        protected Chart(Parcel parcel) {
            this.mTitle = parcel.readString();
            this.mRowTitles = parcel.createStringArrayList();
            this.mRows = parcel.createTypedArrayList(Row.CREATOR);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mTitle);
            parcel.writeStringList(this.mRowTitles);
            parcel.writeTypedList(this.mRows);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            this.mTitle = JsonUtil.optString(jSONObject, StrongAuth.AUTH_TITLE);
            this.mRowTitles = JsonUtil.parseArray(jSONObject, "row_titles", new DataParser<String, String>() {
                public String parseData(String str) throws JSONException {
                    return str;
                }
            });
            this.mRows = JsonUtil.parseArray(jSONObject, "rows", new DataParser<Row, JSONObject>() {
                public Row parseData(JSONObject jSONObject) throws JSONException, ParseException {
                    return new Row(jSONObject);
                }
            });
        }

        public ArrayList<Row> getRows() {
            return this.mRows;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public ArrayList<String> getRowTitles() {
            return this.mRowTitles;
        }
    }

    public enum RowType implements Parcelable {
        INFORMATION(1),
        CHART(2);
        
        public static final Creator<RowType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<RowType>() {
                public RowType createFromParcel(Parcel parcel) {
                    return RowType.values()[parcel.readInt()];
                }

                public RowType[] newArray(int i) {
                    return new RowType[i];
                }
            };
        }

        private RowType(int i) {
            this.mValue = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mValue);
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public int describeContents() {
        return 0;
    }

    protected WishRewardsHelpInfo(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mInformationRows = parcel.createTypedArrayList(WishRewardsReturnPolicyInformation.CREATOR);
        this.mCharts = parcel.createTypedArrayList(Chart.CREATOR);
    }

    public WishRewardsHelpInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeTypedList(this.mInformationRows);
        parcel.writeTypedList(this.mCharts);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.optString(StrongAuth.AUTH_TITLE);
        this.mInformationRows = JsonUtil.parseArray(jSONObject, "information_rows", new DataParser<WishRewardsReturnPolicyInformation, JSONObject>() {
            public WishRewardsReturnPolicyInformation parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishRewardsReturnPolicyInformation(jSONObject);
            }
        });
        this.mCharts = JsonUtil.parseArray(jSONObject, "charts", new DataParser<Chart, JSONObject>() {
            public Chart parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new Chart(jSONObject);
            }
        });
        final RowType[] values = RowType.values();
        this.mRowTypes = JsonUtil.parseArray(jSONObject, "row_types", new DataParser<RowType, Long>() {
            public RowType parseData(Long l) throws JSONException {
                for (RowType value : values) {
                    if (((long) value.getValue()) == l.longValue()) {
                        return values[(int) (l.longValue() - 1)];
                    }
                }
                throw new JSONException("RowType Not Found");
            }
        });
    }

    public String getTitle() {
        return this.mTitle;
    }

    public ArrayList<RowType> getRowTypes() {
        return this.mRowTypes;
    }

    public ArrayList<WishRewardsReturnPolicyInformation> getInformationRows() {
        return this.mInformationRows;
    }

    public ArrayList<Chart> getCharts() {
        return this.mCharts;
    }
}
