package io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import io.branch.referral.Branch;
import io.branch.referral.Branch.c;
import io.branch.referral.Branch.h;
import io.branch.referral.Branch.j;
import io.branch.referral.Branch.l;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.e;
import io.branch.referral.util.BRANCH_STANDARD_EVENT;
import io.branch.referral.util.ContentMetadata;
import io.branch.referral.util.CurrencyType;
import io.branch.referral.util.LinkProperties;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchUniversalObject implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        /* renamed from: a */
        public BranchUniversalObject createFromParcel(Parcel parcel) {
            return new BranchUniversalObject(parcel);
        }

        /* renamed from: a */
        public BranchUniversalObject[] newArray(int i) {
            return new BranchUniversalObject[i];
        }
    };
    private String canonicalIdentifier_;
    private String canonicalUrl_;
    private long creationTimeStamp_;
    private String description_;
    private long expirationInMilliSec_;
    private String imageUrl_;
    private CONTENT_INDEX_MODE indexMode_;
    private final ArrayList<String> keywords_;
    private CONTENT_INDEX_MODE localIndexMode_;
    private ContentMetadata metadata_;
    private String title_;

    public enum CONTENT_INDEX_MODE {
        PUBLIC,
        PRIVATE
    }

    private class a implements c {
        private final c b;
        private final l c;
        private final LinkProperties d;

        a(c cVar, l lVar, LinkProperties linkProperties) {
            this.b = cVar;
            this.c = lVar;
            this.d = linkProperties;
        }

        public void a() {
            if (this.b != null) {
                this.b.a();
            }
        }

        public void b() {
            if (this.b != null) {
                this.b.b();
            }
        }

        public void a(String str, String str2, io.branch.referral.c cVar) {
            HashMap hashMap = new HashMap();
            if (cVar == null) {
                hashMap.put(Jsonkey.SharedLink.getKey(), str);
            } else {
                hashMap.put(Jsonkey.ShareError.getKey(), cVar.a());
            }
            BranchUniversalObject.this.userCompletedAction(BRANCH_STANDARD_EVENT.SHARE.getName(), hashMap);
            if (this.b != null) {
                this.b.a(str, str2, cVar);
            }
        }

        public void a(String str) {
            if (this.b != null) {
                this.b.a(str);
            }
            if ((this.b instanceof h) && ((h) this.b).a(str, BranchUniversalObject.this, this.d)) {
                this.c.a(BranchUniversalObject.this.getLinkBuilder(this.c.p(), this.d));
            }
        }
    }

    public interface b {
        void a(boolean z, io.branch.referral.c cVar);
    }

    public int describeContents() {
        return 0;
    }

    public String getCurrencyType() {
        return null;
    }

    public double getPrice() {
        return 0.0d;
    }

    public String getType() {
        return null;
    }

    public BranchUniversalObject setContentType(String str) {
        return this;
    }

    public BranchUniversalObject setPrice(double d, CurrencyType currencyType) {
        return this;
    }

    public BranchUniversalObject() {
        this.metadata_ = new ContentMetadata();
        this.keywords_ = new ArrayList<>();
        this.canonicalIdentifier_ = "";
        this.canonicalUrl_ = "";
        this.title_ = "";
        this.description_ = "";
        this.indexMode_ = CONTENT_INDEX_MODE.PUBLIC;
        this.localIndexMode_ = CONTENT_INDEX_MODE.PUBLIC;
        this.expirationInMilliSec_ = 0;
        this.creationTimeStamp_ = System.currentTimeMillis();
    }

    public BranchUniversalObject setCanonicalIdentifier(@NonNull String str) {
        this.canonicalIdentifier_ = str;
        return this;
    }

    public BranchUniversalObject setCanonicalUrl(@NonNull String str) {
        this.canonicalUrl_ = str;
        return this;
    }

    public BranchUniversalObject setTitle(@NonNull String str) {
        this.title_ = str;
        return this;
    }

    public BranchUniversalObject setContentDescription(String str) {
        this.description_ = str;
        return this;
    }

    public BranchUniversalObject setContentImageUrl(@NonNull String str) {
        this.imageUrl_ = str;
        return this;
    }

    public BranchUniversalObject addContentMetadata(HashMap<String, String> hashMap) {
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                this.metadata_.addCustomMetadata(str, (String) hashMap.get(str));
            }
        }
        return this;
    }

    public BranchUniversalObject addContentMetadata(String str, String str2) {
        this.metadata_.addCustomMetadata(str, str2);
        return this;
    }

    public BranchUniversalObject setContentMetadata(ContentMetadata contentMetadata) {
        this.metadata_ = contentMetadata;
        return this;
    }

    public BranchUniversalObject setContentIndexingMode(CONTENT_INDEX_MODE content_index_mode) {
        this.indexMode_ = content_index_mode;
        return this;
    }

    public BranchUniversalObject setLocalIndexMode(CONTENT_INDEX_MODE content_index_mode) {
        this.localIndexMode_ = content_index_mode;
        return this;
    }

    public BranchUniversalObject addKeyWords(ArrayList<String> arrayList) {
        this.keywords_.addAll(arrayList);
        return this;
    }

    public BranchUniversalObject addKeyWord(String str) {
        this.keywords_.add(str);
        return this;
    }

    public BranchUniversalObject setContentExpiration(Date date) {
        this.expirationInMilliSec_ = date.getTime();
        return this;
    }

    public void listOnGoogleSearch(Context context) {
        a.a(context, this, (LinkProperties) null);
    }

    public void listOnGoogleSearch(Context context, LinkProperties linkProperties) {
        a.a(context, this, linkProperties);
    }

    public void removeFromLocalIndexing(Context context) {
        a.b(context, this, (LinkProperties) null);
    }

    public void removeFromLocalIndexing(Context context, LinkProperties linkProperties) {
        a.b(context, this, linkProperties);
    }

    public void userCompletedAction(String str) {
        userCompletedAction(str, null);
    }

    public void userCompletedAction(BRANCH_STANDARD_EVENT branch_standard_event) {
        userCompletedAction(branch_standard_event.getName(), null);
    }

    public void userCompletedAction(String str, HashMap<String, String> hashMap) {
        JSONObject jSONObject = new JSONObject();
        try {
            new JSONArray().put(this.canonicalIdentifier_);
            jSONObject.put(this.canonicalIdentifier_, convertToJson());
            if (hashMap != null) {
                for (String str2 : hashMap.keySet()) {
                    jSONObject.put(str2, hashMap.get(str2));
                }
            }
            if (Branch.b() != null) {
                Branch.b().a(str, jSONObject);
            }
        } catch (JSONException unused) {
        }
    }

    public void userCompletedAction(BRANCH_STANDARD_EVENT branch_standard_event, HashMap<String, String> hashMap) {
        userCompletedAction(branch_standard_event.getName(), hashMap);
    }

    public boolean isPublicallyIndexable() {
        return this.indexMode_ == CONTENT_INDEX_MODE.PUBLIC;
    }

    public boolean isLocallyIndexable() {
        return this.localIndexMode_ == CONTENT_INDEX_MODE.PUBLIC;
    }

    public HashMap<String, String> getMetadata() {
        return this.metadata_.getCustomMetadata();
    }

    public ContentMetadata getContentMetadata() {
        return this.metadata_;
    }

    public long getExpirationTime() {
        return this.expirationInMilliSec_;
    }

    public String getCanonicalIdentifier() {
        return this.canonicalIdentifier_;
    }

    public String getCanonicalUrl() {
        return this.canonicalUrl_;
    }

    public String getDescription() {
        return this.description_;
    }

    public String getImageUrl() {
        return this.imageUrl_;
    }

    public String getTitle() {
        return this.title_;
    }

    public JSONArray getKeywordsJsonArray() {
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.keywords_.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        return jSONArray;
    }

    public ArrayList<String> getKeywords() {
        return this.keywords_;
    }

    public void registerView() {
        registerView(null);
    }

    public void registerView(@Nullable b bVar) {
        if (Branch.b() != null) {
            Branch.b().a(this, bVar);
        } else if (bVar != null) {
            bVar.a(false, new io.branch.referral.c("Register view error", -109));
        }
    }

    public String getShortUrl(@NonNull Context context, @NonNull LinkProperties linkProperties) {
        return getLinkBuilder(context, linkProperties).a();
    }

    public String getShortUrl(@NonNull Context context, @NonNull LinkProperties linkProperties, boolean z) {
        return ((e) getLinkBuilder(context, linkProperties).a(z)).a();
    }

    public void generateShortUrl(@NonNull Context context, @NonNull LinkProperties linkProperties, @Nullable io.branch.referral.Branch.b bVar) {
        getLinkBuilder(context, linkProperties).a(bVar);
    }

    public void generateShortUrl(@NonNull Context context, @NonNull LinkProperties linkProperties, @Nullable io.branch.referral.Branch.b bVar, boolean z) {
        ((e) getLinkBuilder(context, linkProperties).a(z)).a(bVar);
    }

    public void showShareSheet(@NonNull Activity activity, @NonNull LinkProperties linkProperties, @NonNull io.branch.referral.util.b bVar, @Nullable c cVar) {
        showShareSheet(activity, linkProperties, bVar, cVar, null);
    }

    public void showShareSheet(@NonNull Activity activity, @NonNull LinkProperties linkProperties, @NonNull io.branch.referral.util.b bVar, @Nullable c cVar, j jVar) {
        if (Branch.b() != null) {
            l lVar = new l(activity, getLinkBuilder((Context) activity, linkProperties));
            lVar.a((c) new a(cVar, lVar, linkProperties)).a(jVar).b(bVar.g()).a(bVar.f());
            if (bVar.d() != null) {
                lVar.a(bVar.d(), bVar.h(), bVar.k());
            }
            if (bVar.e() != null) {
                lVar.a(bVar.e(), bVar.j());
            }
            if (bVar.i() != null) {
                lVar.c(bVar.i());
            }
            if (bVar.c().size() > 0) {
                lVar.a(bVar.c());
            }
            if (bVar.q() > 0) {
                lVar.d(bVar.q());
            }
            lVar.b(bVar.l());
            lVar.a(bVar.o());
            lVar.a(bVar.p());
            lVar.d(bVar.m());
            lVar.a(bVar.n());
            lVar.c(bVar.r());
            if (bVar.b() != null && bVar.b().size() > 0) {
                lVar.b(bVar.b());
            }
            if (bVar.a() != null && bVar.a().size() > 0) {
                lVar.a(bVar.a());
            }
            lVar.a();
        } else if (cVar != null) {
            cVar.a(null, null, new io.branch.referral.c("Trouble sharing link. ", -109));
        } else {
            Log.e("BranchSDK", "Sharing error. Branch instance is not created yet. Make sure you have initialised Branch.");
        }
    }

    private e getLinkBuilder(@NonNull Context context, @NonNull LinkProperties linkProperties) {
        return getLinkBuilder(new e(context), linkProperties);
    }

    /* access modifiers changed from: private */
    public e getLinkBuilder(@NonNull e eVar, @NonNull LinkProperties linkProperties) {
        if (linkProperties.getTags() != null) {
            eVar.a((List<String>) linkProperties.getTags());
        }
        if (linkProperties.getFeature() != null) {
            eVar.c(linkProperties.getFeature());
        }
        if (linkProperties.getAlias() != null) {
            eVar.a(linkProperties.getAlias());
        }
        if (linkProperties.getChannel() != null) {
            eVar.b(linkProperties.getChannel());
        }
        if (linkProperties.getStage() != null) {
            eVar.d(linkProperties.getStage());
        }
        if (linkProperties.getCampaign() != null) {
            eVar.e(linkProperties.getCampaign());
        }
        if (linkProperties.getMatchDuration() > 0) {
            eVar.a(linkProperties.getMatchDuration());
        }
        if (!TextUtils.isEmpty(this.title_)) {
            eVar.a(Jsonkey.ContentTitle.getKey(), this.title_);
        }
        if (!TextUtils.isEmpty(this.canonicalIdentifier_)) {
            eVar.a(Jsonkey.CanonicalIdentifier.getKey(), this.canonicalIdentifier_);
        }
        if (!TextUtils.isEmpty(this.canonicalUrl_)) {
            eVar.a(Jsonkey.CanonicalUrl.getKey(), this.canonicalUrl_);
        }
        JSONArray keywordsJsonArray = getKeywordsJsonArray();
        if (keywordsJsonArray.length() > 0) {
            eVar.a(Jsonkey.ContentKeyWords.getKey(), keywordsJsonArray);
        }
        if (!TextUtils.isEmpty(this.description_)) {
            eVar.a(Jsonkey.ContentDesc.getKey(), this.description_);
        }
        if (!TextUtils.isEmpty(this.imageUrl_)) {
            eVar.a(Jsonkey.ContentImgUrl.getKey(), this.imageUrl_);
        }
        if (this.expirationInMilliSec_ > 0) {
            String key = Jsonkey.ContentExpiryTime.getKey();
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(this.expirationInMilliSec_);
            eVar.a(key, sb.toString());
        }
        String key2 = Jsonkey.PublicallyIndexable.getKey();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        sb2.append(isPublicallyIndexable());
        eVar.a(key2, sb2.toString());
        JSONObject convertToJson = this.metadata_.convertToJson();
        try {
            Iterator keys = convertToJson.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                eVar.a(str, convertToJson.get(str));
            }
        } catch (JSONException e) {
            com.google.a.a.a.a.a.a.a(e);
        }
        HashMap controlParams = linkProperties.getControlParams();
        for (String str2 : controlParams.keySet()) {
            eVar.a(str2, controlParams.get(str2));
        }
        return eVar;
    }

    public static BranchUniversalObject getReferredBranchUniversalObject() {
        BranchUniversalObject createInstance;
        Branch b2 = Branch.b();
        if (b2 == null) {
            return null;
        }
        try {
            if (b2.i() == null) {
                return null;
            }
            if (b2.i().has("+clicked_branch_link") && b2.i().getBoolean("+clicked_branch_link")) {
                createInstance = createInstance(b2.i());
            } else if (b2.j() == null || b2.j().length() <= 0) {
                return null;
            } else {
                createInstance = createInstance(b2.i());
            }
            return createInstance;
        } catch (Exception unused) {
            return null;
        }
    }

    public static BranchUniversalObject createInstance(JSONObject jSONObject) {
        JSONArray jSONArray = null;
        try {
            BranchUniversalObject branchUniversalObject = new BranchUniversalObject();
            try {
                io.branch.referral.h.a aVar = new io.branch.referral.h.a(jSONObject);
                branchUniversalObject.title_ = aVar.a(Jsonkey.ContentTitle.getKey());
                branchUniversalObject.canonicalIdentifier_ = aVar.a(Jsonkey.CanonicalIdentifier.getKey());
                branchUniversalObject.canonicalUrl_ = aVar.a(Jsonkey.CanonicalUrl.getKey());
                branchUniversalObject.description_ = aVar.a(Jsonkey.ContentDesc.getKey());
                branchUniversalObject.imageUrl_ = aVar.a(Jsonkey.ContentImgUrl.getKey());
                branchUniversalObject.expirationInMilliSec_ = aVar.b(Jsonkey.ContentExpiryTime.getKey());
                Object e = aVar.e(Jsonkey.ContentKeyWords.getKey());
                if (e instanceof JSONArray) {
                    jSONArray = (JSONArray) e;
                } else if (e instanceof String) {
                    jSONArray = new JSONArray((String) e);
                }
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        branchUniversalObject.keywords_.add((String) jSONArray.get(i));
                    }
                }
                Object e2 = aVar.e(Jsonkey.PublicallyIndexable.getKey());
                if (e2 instanceof Boolean) {
                    branchUniversalObject.indexMode_ = ((Boolean) e2).booleanValue() ? CONTENT_INDEX_MODE.PUBLIC : CONTENT_INDEX_MODE.PRIVATE;
                } else if (e2 instanceof Integer) {
                    branchUniversalObject.indexMode_ = ((Integer) e2).intValue() == 1 ? CONTENT_INDEX_MODE.PUBLIC : CONTENT_INDEX_MODE.PRIVATE;
                }
                branchUniversalObject.localIndexMode_ = aVar.c(Jsonkey.LocallyIndexable.getKey()) ? CONTENT_INDEX_MODE.PUBLIC : CONTENT_INDEX_MODE.PRIVATE;
                branchUniversalObject.creationTimeStamp_ = aVar.b(Jsonkey.CreationTimestamp.getKey());
                branchUniversalObject.metadata_ = ContentMetadata.createFromJson(aVar);
                JSONObject a2 = aVar.a();
                Iterator keys = a2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    branchUniversalObject.metadata_.addCustomMetadata(str, a2.optString(str));
                }
                return branchUniversalObject;
            } catch (Exception unused) {
                return branchUniversalObject;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public JSONObject convertToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject convertToJson = this.metadata_.convertToJson();
            Iterator keys = convertToJson.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject.put(str, convertToJson.get(str));
            }
            if (!TextUtils.isEmpty(this.title_)) {
                jSONObject.put(Jsonkey.ContentTitle.getKey(), this.title_);
            }
            if (!TextUtils.isEmpty(this.canonicalIdentifier_)) {
                jSONObject.put(Jsonkey.CanonicalIdentifier.getKey(), this.canonicalIdentifier_);
            }
            if (!TextUtils.isEmpty(this.canonicalUrl_)) {
                jSONObject.put(Jsonkey.CanonicalUrl.getKey(), this.canonicalUrl_);
            }
            if (this.keywords_.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.keywords_.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
                jSONObject.put(Jsonkey.ContentKeyWords.getKey(), jSONArray);
            }
            if (!TextUtils.isEmpty(this.description_)) {
                jSONObject.put(Jsonkey.ContentDesc.getKey(), this.description_);
            }
            if (!TextUtils.isEmpty(this.imageUrl_)) {
                jSONObject.put(Jsonkey.ContentImgUrl.getKey(), this.imageUrl_);
            }
            if (this.expirationInMilliSec_ > 0) {
                jSONObject.put(Jsonkey.ContentExpiryTime.getKey(), this.expirationInMilliSec_);
            }
            jSONObject.put(Jsonkey.PublicallyIndexable.getKey(), isPublicallyIndexable());
            jSONObject.put(Jsonkey.LocallyIndexable.getKey(), isLocallyIndexable());
            jSONObject.put(Jsonkey.CreationTimestamp.getKey(), this.creationTimeStamp_);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.creationTimeStamp_);
        parcel.writeString(this.canonicalIdentifier_);
        parcel.writeString(this.canonicalUrl_);
        parcel.writeString(this.title_);
        parcel.writeString(this.description_);
        parcel.writeString(this.imageUrl_);
        parcel.writeLong(this.expirationInMilliSec_);
        parcel.writeInt(this.indexMode_.ordinal());
        parcel.writeSerializable(this.keywords_);
        parcel.writeParcelable(this.metadata_, i);
        parcel.writeInt(this.localIndexMode_.ordinal());
    }

    private BranchUniversalObject(Parcel parcel) {
        this();
        this.creationTimeStamp_ = parcel.readLong();
        this.canonicalIdentifier_ = parcel.readString();
        this.canonicalUrl_ = parcel.readString();
        this.title_ = parcel.readString();
        this.description_ = parcel.readString();
        this.imageUrl_ = parcel.readString();
        this.expirationInMilliSec_ = parcel.readLong();
        this.indexMode_ = CONTENT_INDEX_MODE.values()[parcel.readInt()];
        ArrayList arrayList = (ArrayList) parcel.readSerializable();
        if (arrayList != null) {
            this.keywords_.addAll(arrayList);
        }
        this.metadata_ = (ContentMetadata) parcel.readParcelable(ContentMetadata.class.getClassLoader());
        this.localIndexMode_ = CONTENT_INDEX_MODE.values()[parcel.readInt()];
    }
}
