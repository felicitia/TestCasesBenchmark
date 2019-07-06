package com.etsy.etsyapi;

import com.etsy.etsyapi.api.pub.BugHuntLeadersGetSpec;
import com.etsy.etsyapi.api.pub.BugHuntReportCreatePostSpec;
import com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardListingSpec;
import com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsDashboardSpec;
import com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsInventorySpec;
import com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficDetailSpec;
import com.etsy.etsyapi.api.shop.bespoke.MissionControlStatsTrafficHeroSpec;
import com.etsy.etsyapi.api.shop.bespoke.SellerDashboardGetSpec;
import com.etsy.etsyapi.api.shop.bespoke.SellerDashboardSDLSpec;
import com.etsy.etsyapi.models.EtsyId;
import com.etsy.etsyapi.models.resource.pub.BugHuntLeader;
import com.etsy.etsyapi.models.resource.pub.BugHuntReport;
import com.etsy.etsyapi.models.resource.pub.Image;
import com.etsy.etsyapi.models.resource.pub.ImageSize;
import com.etsy.etsyapi.models.resource.pub.ListingImage;
import com.etsy.etsyapi.models.resource.pub.Pagination;
import com.etsy.etsyapi.models.resource.pub.ServerDrivenLayoutBaseAction;
import com.etsy.etsyapi.models.resource.pub.ServerDrivenLayoutDeepLink;
import com.etsy.etsyapi.models.resource.pub.ServerDrivenLayoutLandingPageLink;
import com.etsy.etsyapi.models.resource.pub.ServerDrivenLayoutMessageCard;
import com.etsy.etsyapi.models.resource.pub.ServerDrivenLayoutSectionHeader;
import com.etsy.etsyapi.models.resource.shop.ListingExpiring;
import com.etsy.etsyapi.models.resource.shop.ListingsCount;
import com.etsy.etsyapi.models.resource.shop.MissionControlCommonOrderCounts;
import com.etsy.etsyapi.models.resource.shop.MissionControlDashboardLegacyStats;
import com.etsy.etsyapi.models.resource.shop.MissionControlDashboardOverviewCount;
import com.etsy.etsyapi.models.resource.shop.MissionControlDashboardOverviewMetrics;
import com.etsy.etsyapi.models.resource.shop.MissionControlDashboardPage;
import com.etsy.etsyapi.models.resource.shop.MissionControlDashboardShopAdvisor;
import com.etsy.etsyapi.models.resource.shop.MissionControlDashboardShopAdvisorItem;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsAction;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsColor;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsDatasetDefault;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsDatasetTimeSeries;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsDatasetVisitsOrders;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsEmptyState;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsEntry;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsEntryCustomer;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsEntryListing;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsEvent;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsFilter;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsFilterOption;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsModuleBanner;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsModuleContainer;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsModuleDefault;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsModuleTimeSeries;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsModuleVisitsOrders;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsOnboardingTakeover;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsOnboardingTakeoverBullet;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsPage;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsPageList;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsPageMetadata;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsParams;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsRequestMetadata;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionAllListings;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionBanner;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionCustomerInterests;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionDestinations;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionEtsyTrafficBreakdown;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionFavorites;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionGeolocation;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionHelp;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionInventoryDetail;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionInventoryLeastViewedListings;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionInventoryMostViewedListings;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionPlatforms;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionSearchTerms;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionSocial;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionTrafficHero;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionTrafficSources;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionVisitsAndOrdersComparison;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsSectionWebsites;
import com.etsy.etsyapi.models.resource.shop.MissionControlStatsYearOverYear;
import com.etsy.etsyapi.models.resource.shop.SOEDashboard;
import com.etsy.etsyapi.models.resource.shop.StatsCurrencyTotal;
import com.etsy.etsyapi.models.resource.shop.StatsListingInsight;
import com.etsy.etsyapi.models.resource.shop.StatsOverview;
import com.etsy.etsyapi.models.resource.shop.StatsOverviewCurrencyElement;
import com.etsy.etsyapi.models.resource.shop.StatsOverviewElement;
import com.etsy.etsyapi.models.resource.shop.StatsTotal;
import com.etsy.etsyapi.models.resource.shop.SuggestionItem;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;

/* compiled from: AutoValueEtsy_EtsyModelFactory */
public final class a extends b {
    public <T> T a(JsonParser jsonParser, Class<T> cls) throws IOException {
        if (BugHuntLeadersGetSpec.class.isAssignableFrom(cls)) {
            return BugHuntLeadersGetSpec.read(jsonParser);
        }
        if (BugHuntReportCreatePostSpec.class.isAssignableFrom(cls)) {
            return BugHuntReportCreatePostSpec.read(jsonParser);
        }
        if (MissionControlStatsDashboardListingSpec.class.isAssignableFrom(cls)) {
            return MissionControlStatsDashboardListingSpec.read(jsonParser);
        }
        if (MissionControlStatsDashboardSpec.class.isAssignableFrom(cls)) {
            return MissionControlStatsDashboardSpec.read(jsonParser);
        }
        if (MissionControlStatsInventorySpec.class.isAssignableFrom(cls)) {
            return MissionControlStatsInventorySpec.read(jsonParser);
        }
        if (MissionControlStatsTrafficDetailSpec.class.isAssignableFrom(cls)) {
            return MissionControlStatsTrafficDetailSpec.read(jsonParser);
        }
        if (MissionControlStatsTrafficHeroSpec.class.isAssignableFrom(cls)) {
            return MissionControlStatsTrafficHeroSpec.read(jsonParser);
        }
        if (SellerDashboardGetSpec.class.isAssignableFrom(cls)) {
            return SellerDashboardGetSpec.read(jsonParser);
        }
        if (SellerDashboardSDLSpec.class.isAssignableFrom(cls)) {
            return SellerDashboardSDLSpec.read(jsonParser);
        }
        if (EtsyId.class.isAssignableFrom(cls)) {
            return EtsyId.read(jsonParser);
        }
        if (BugHuntLeader.class.isAssignableFrom(cls)) {
            return BugHuntLeader.read(jsonParser);
        }
        if (BugHuntReport.class.isAssignableFrom(cls)) {
            return BugHuntReport.read(jsonParser);
        }
        if (Image.class.isAssignableFrom(cls)) {
            return Image.read(jsonParser);
        }
        if (ImageSize.class.isAssignableFrom(cls)) {
            return ImageSize.read(jsonParser);
        }
        if (ListingImage.class.isAssignableFrom(cls)) {
            return ListingImage.read(jsonParser);
        }
        if (Pagination.class.isAssignableFrom(cls)) {
            return Pagination.read(jsonParser);
        }
        if (ServerDrivenLayoutBaseAction.class.isAssignableFrom(cls)) {
            return ServerDrivenLayoutBaseAction.read(jsonParser);
        }
        if (ServerDrivenLayoutDeepLink.class.isAssignableFrom(cls)) {
            return ServerDrivenLayoutDeepLink.read(jsonParser);
        }
        if (ServerDrivenLayoutLandingPageLink.class.isAssignableFrom(cls)) {
            return ServerDrivenLayoutLandingPageLink.read(jsonParser);
        }
        if (ServerDrivenLayoutMessageCard.class.isAssignableFrom(cls)) {
            return ServerDrivenLayoutMessageCard.read(jsonParser);
        }
        if (ServerDrivenLayoutSectionHeader.class.isAssignableFrom(cls)) {
            return ServerDrivenLayoutSectionHeader.read(jsonParser);
        }
        if (ListingExpiring.class.isAssignableFrom(cls)) {
            return ListingExpiring.read(jsonParser);
        }
        if (ListingsCount.class.isAssignableFrom(cls)) {
            return ListingsCount.read(jsonParser);
        }
        if (MissionControlCommonOrderCounts.class.isAssignableFrom(cls)) {
            return MissionControlCommonOrderCounts.read(jsonParser);
        }
        if (MissionControlDashboardLegacyStats.class.isAssignableFrom(cls)) {
            return MissionControlDashboardLegacyStats.read(jsonParser);
        }
        if (MissionControlDashboardOverviewCount.class.isAssignableFrom(cls)) {
            return MissionControlDashboardOverviewCount.read(jsonParser);
        }
        if (MissionControlDashboardOverviewMetrics.class.isAssignableFrom(cls)) {
            return MissionControlDashboardOverviewMetrics.read(jsonParser);
        }
        if (MissionControlDashboardPage.class.isAssignableFrom(cls)) {
            return MissionControlDashboardPage.read(jsonParser);
        }
        if (MissionControlDashboardShopAdvisor.class.isAssignableFrom(cls)) {
            return MissionControlDashboardShopAdvisor.read(jsonParser);
        }
        if (MissionControlDashboardShopAdvisorItem.class.isAssignableFrom(cls)) {
            return MissionControlDashboardShopAdvisorItem.read(jsonParser);
        }
        if (MissionControlStatsAction.class.isAssignableFrom(cls)) {
            return MissionControlStatsAction.read(jsonParser);
        }
        if (MissionControlStatsColor.class.isAssignableFrom(cls)) {
            return MissionControlStatsColor.read(jsonParser);
        }
        if (MissionControlStatsDatasetDefault.class.isAssignableFrom(cls)) {
            return MissionControlStatsDatasetDefault.read(jsonParser);
        }
        if (MissionControlStatsDatasetTimeSeries.class.isAssignableFrom(cls)) {
            return MissionControlStatsDatasetTimeSeries.read(jsonParser);
        }
        if (MissionControlStatsDatasetVisitsOrders.class.isAssignableFrom(cls)) {
            return MissionControlStatsDatasetVisitsOrders.read(jsonParser);
        }
        if (MissionControlStatsEmptyState.class.isAssignableFrom(cls)) {
            return MissionControlStatsEmptyState.read(jsonParser);
        }
        if (MissionControlStatsEntry.class.isAssignableFrom(cls)) {
            return MissionControlStatsEntry.read(jsonParser);
        }
        if (MissionControlStatsEntryCustomer.class.isAssignableFrom(cls)) {
            return MissionControlStatsEntryCustomer.read(jsonParser);
        }
        if (MissionControlStatsEntryListing.class.isAssignableFrom(cls)) {
            return MissionControlStatsEntryListing.read(jsonParser);
        }
        if (MissionControlStatsEvent.class.isAssignableFrom(cls)) {
            return MissionControlStatsEvent.read(jsonParser);
        }
        if (MissionControlStatsFilter.class.isAssignableFrom(cls)) {
            return MissionControlStatsFilter.read(jsonParser);
        }
        if (MissionControlStatsFilterOption.class.isAssignableFrom(cls)) {
            return MissionControlStatsFilterOption.read(jsonParser);
        }
        if (MissionControlStatsModuleBanner.class.isAssignableFrom(cls)) {
            return MissionControlStatsModuleBanner.read(jsonParser);
        }
        if (MissionControlStatsModuleContainer.class.isAssignableFrom(cls)) {
            return MissionControlStatsModuleContainer.read(jsonParser);
        }
        if (MissionControlStatsModuleDefault.class.isAssignableFrom(cls)) {
            return MissionControlStatsModuleDefault.read(jsonParser);
        }
        if (MissionControlStatsModuleTimeSeries.class.isAssignableFrom(cls)) {
            return MissionControlStatsModuleTimeSeries.read(jsonParser);
        }
        if (MissionControlStatsModuleVisitsOrders.class.isAssignableFrom(cls)) {
            return MissionControlStatsModuleVisitsOrders.read(jsonParser);
        }
        if (MissionControlStatsOnboardingTakeover.class.isAssignableFrom(cls)) {
            return MissionControlStatsOnboardingTakeover.read(jsonParser);
        }
        if (MissionControlStatsOnboardingTakeoverBullet.class.isAssignableFrom(cls)) {
            return MissionControlStatsOnboardingTakeoverBullet.read(jsonParser);
        }
        if (MissionControlStatsPage.class.isAssignableFrom(cls)) {
            return MissionControlStatsPage.read(jsonParser);
        }
        if (MissionControlStatsPageList.class.isAssignableFrom(cls)) {
            return MissionControlStatsPageList.read(jsonParser);
        }
        if (MissionControlStatsPageMetadata.class.isAssignableFrom(cls)) {
            return MissionControlStatsPageMetadata.read(jsonParser);
        }
        if (MissionControlStatsParams.class.isAssignableFrom(cls)) {
            return MissionControlStatsParams.read(jsonParser);
        }
        if (MissionControlStatsRequestMetadata.class.isAssignableFrom(cls)) {
            return MissionControlStatsRequestMetadata.read(jsonParser);
        }
        if (MissionControlStatsSectionAllListings.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionAllListings.read(jsonParser);
        }
        if (MissionControlStatsSectionBanner.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionBanner.read(jsonParser);
        }
        if (MissionControlStatsSectionCustomerInterests.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionCustomerInterests.read(jsonParser);
        }
        if (MissionControlStatsSectionDestinations.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionDestinations.read(jsonParser);
        }
        if (MissionControlStatsSectionEtsyTrafficBreakdown.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionEtsyTrafficBreakdown.read(jsonParser);
        }
        if (MissionControlStatsSectionFavorites.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionFavorites.read(jsonParser);
        }
        if (MissionControlStatsSectionGeolocation.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionGeolocation.read(jsonParser);
        }
        if (MissionControlStatsSectionHelp.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionHelp.read(jsonParser);
        }
        if (MissionControlStatsSectionInventoryDetail.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionInventoryDetail.read(jsonParser);
        }
        if (MissionControlStatsSectionInventoryLeastViewedListings.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionInventoryLeastViewedListings.read(jsonParser);
        }
        if (MissionControlStatsSectionInventoryMostViewedListings.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionInventoryMostViewedListings.read(jsonParser);
        }
        if (MissionControlStatsSectionPlatforms.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionPlatforms.read(jsonParser);
        }
        if (MissionControlStatsSectionSearchTerms.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionSearchTerms.read(jsonParser);
        }
        if (MissionControlStatsSectionSocial.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionSocial.read(jsonParser);
        }
        if (MissionControlStatsSectionTrafficHero.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionTrafficHero.read(jsonParser);
        }
        if (MissionControlStatsSectionTrafficSources.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionTrafficSources.read(jsonParser);
        }
        if (MissionControlStatsSectionVisitsAndOrdersComparison.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionVisitsAndOrdersComparison.read(jsonParser);
        }
        if (MissionControlStatsSectionWebsites.class.isAssignableFrom(cls)) {
            return MissionControlStatsSectionWebsites.read(jsonParser);
        }
        if (MissionControlStatsYearOverYear.class.isAssignableFrom(cls)) {
            return MissionControlStatsYearOverYear.read(jsonParser);
        }
        if (SOEDashboard.class.isAssignableFrom(cls)) {
            return SOEDashboard.read(jsonParser);
        }
        if (StatsCurrencyTotal.class.isAssignableFrom(cls)) {
            return StatsCurrencyTotal.read(jsonParser);
        }
        if (StatsListingInsight.class.isAssignableFrom(cls)) {
            return StatsListingInsight.read(jsonParser);
        }
        if (StatsOverview.class.isAssignableFrom(cls)) {
            return StatsOverview.read(jsonParser);
        }
        if (StatsOverviewCurrencyElement.class.isAssignableFrom(cls)) {
            return StatsOverviewCurrencyElement.read(jsonParser);
        }
        if (StatsOverviewElement.class.isAssignableFrom(cls)) {
            return StatsOverviewElement.read(jsonParser);
        }
        if (StatsTotal.class.isAssignableFrom(cls)) {
            return StatsTotal.read(jsonParser);
        }
        if (SuggestionItem.class.isAssignableFrom(cls)) {
            return SuggestionItem.read(jsonParser);
        }
        return null;
    }

    public Module a() {
        SimpleModule simpleModule = new SimpleModule("EtsyApiModule");
        simpleModule.addDeserializer(BugHuntLeadersGetSpec.class, new JsonDeserializer<BugHuntLeadersGetSpec>() {
            /* renamed from: a */
            public BugHuntLeadersGetSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return BugHuntLeadersGetSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(BugHuntReportCreatePostSpec.class, new JsonDeserializer<BugHuntReportCreatePostSpec>() {
            /* renamed from: a */
            public BugHuntReportCreatePostSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return BugHuntReportCreatePostSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsDashboardListingSpec.class, new JsonDeserializer<MissionControlStatsDashboardListingSpec>() {
            /* renamed from: a */
            public MissionControlStatsDashboardListingSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsDashboardListingSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsDashboardSpec.class, new JsonDeserializer<MissionControlStatsDashboardSpec>() {
            /* renamed from: a */
            public MissionControlStatsDashboardSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsDashboardSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsInventorySpec.class, new JsonDeserializer<MissionControlStatsInventorySpec>() {
            /* renamed from: a */
            public MissionControlStatsInventorySpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsInventorySpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsTrafficDetailSpec.class, new JsonDeserializer<MissionControlStatsTrafficDetailSpec>() {
            /* renamed from: a */
            public MissionControlStatsTrafficDetailSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsTrafficDetailSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsTrafficHeroSpec.class, new JsonDeserializer<MissionControlStatsTrafficHeroSpec>() {
            /* renamed from: a */
            public MissionControlStatsTrafficHeroSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsTrafficHeroSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(SellerDashboardGetSpec.class, new JsonDeserializer<SellerDashboardGetSpec>() {
            /* renamed from: a */
            public SellerDashboardGetSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return SellerDashboardGetSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(SellerDashboardSDLSpec.class, new JsonDeserializer<SellerDashboardSDLSpec>() {
            /* renamed from: a */
            public SellerDashboardSDLSpec deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return SellerDashboardSDLSpec.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(EtsyId.class, new JsonDeserializer<EtsyId>() {
            /* renamed from: a */
            public EtsyId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return EtsyId.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(BugHuntLeader.class, new JsonDeserializer<BugHuntLeader>() {
            /* renamed from: a */
            public BugHuntLeader deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return BugHuntLeader.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(BugHuntReport.class, new JsonDeserializer<BugHuntReport>() {
            /* renamed from: a */
            public BugHuntReport deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return BugHuntReport.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(Image.class, new JsonDeserializer<Image>() {
            /* renamed from: a */
            public Image deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return Image.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ImageSize.class, new JsonDeserializer<ImageSize>() {
            /* renamed from: a */
            public ImageSize deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ImageSize.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ListingImage.class, new JsonDeserializer<ListingImage>() {
            /* renamed from: a */
            public ListingImage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ListingImage.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(Pagination.class, new JsonDeserializer<Pagination>() {
            /* renamed from: a */
            public Pagination deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return Pagination.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ServerDrivenLayoutBaseAction.class, new JsonDeserializer<ServerDrivenLayoutBaseAction>() {
            /* renamed from: a */
            public ServerDrivenLayoutBaseAction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ServerDrivenLayoutBaseAction.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ServerDrivenLayoutDeepLink.class, new JsonDeserializer<ServerDrivenLayoutDeepLink>() {
            /* renamed from: a */
            public ServerDrivenLayoutDeepLink deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ServerDrivenLayoutDeepLink.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ServerDrivenLayoutLandingPageLink.class, new JsonDeserializer<ServerDrivenLayoutLandingPageLink>() {
            /* renamed from: a */
            public ServerDrivenLayoutLandingPageLink deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ServerDrivenLayoutLandingPageLink.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ServerDrivenLayoutMessageCard.class, new JsonDeserializer<ServerDrivenLayoutMessageCard>() {
            /* renamed from: a */
            public ServerDrivenLayoutMessageCard deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ServerDrivenLayoutMessageCard.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ServerDrivenLayoutSectionHeader.class, new JsonDeserializer<ServerDrivenLayoutSectionHeader>() {
            /* renamed from: a */
            public ServerDrivenLayoutSectionHeader deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ServerDrivenLayoutSectionHeader.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ListingExpiring.class, new JsonDeserializer<ListingExpiring>() {
            /* renamed from: a */
            public ListingExpiring deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ListingExpiring.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(ListingsCount.class, new JsonDeserializer<ListingsCount>() {
            /* renamed from: a */
            public ListingsCount deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return ListingsCount.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlCommonOrderCounts.class, new JsonDeserializer<MissionControlCommonOrderCounts>() {
            /* renamed from: a */
            public MissionControlCommonOrderCounts deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlCommonOrderCounts.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlDashboardLegacyStats.class, new JsonDeserializer<MissionControlDashboardLegacyStats>() {
            /* renamed from: a */
            public MissionControlDashboardLegacyStats deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlDashboardLegacyStats.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlDashboardOverviewCount.class, new JsonDeserializer<MissionControlDashboardOverviewCount>() {
            /* renamed from: a */
            public MissionControlDashboardOverviewCount deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlDashboardOverviewCount.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlDashboardOverviewMetrics.class, new JsonDeserializer<MissionControlDashboardOverviewMetrics>() {
            /* renamed from: a */
            public MissionControlDashboardOverviewMetrics deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlDashboardOverviewMetrics.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlDashboardPage.class, new JsonDeserializer<MissionControlDashboardPage>() {
            /* renamed from: a */
            public MissionControlDashboardPage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlDashboardPage.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlDashboardShopAdvisor.class, new JsonDeserializer<MissionControlDashboardShopAdvisor>() {
            /* renamed from: a */
            public MissionControlDashboardShopAdvisor deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlDashboardShopAdvisor.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlDashboardShopAdvisorItem.class, new JsonDeserializer<MissionControlDashboardShopAdvisorItem>() {
            /* renamed from: a */
            public MissionControlDashboardShopAdvisorItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlDashboardShopAdvisorItem.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsAction.class, new JsonDeserializer<MissionControlStatsAction>() {
            /* renamed from: a */
            public MissionControlStatsAction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsAction.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsColor.class, new JsonDeserializer<MissionControlStatsColor>() {
            /* renamed from: a */
            public MissionControlStatsColor deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsColor.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsDatasetDefault.class, new JsonDeserializer<MissionControlStatsDatasetDefault>() {
            /* renamed from: a */
            public MissionControlStatsDatasetDefault deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsDatasetDefault.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsDatasetTimeSeries.class, new JsonDeserializer<MissionControlStatsDatasetTimeSeries>() {
            /* renamed from: a */
            public MissionControlStatsDatasetTimeSeries deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsDatasetTimeSeries.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsDatasetVisitsOrders.class, new JsonDeserializer<MissionControlStatsDatasetVisitsOrders>() {
            /* renamed from: a */
            public MissionControlStatsDatasetVisitsOrders deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsDatasetVisitsOrders.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsEmptyState.class, new JsonDeserializer<MissionControlStatsEmptyState>() {
            /* renamed from: a */
            public MissionControlStatsEmptyState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsEmptyState.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsEntry.class, new JsonDeserializer<MissionControlStatsEntry>() {
            /* renamed from: a */
            public MissionControlStatsEntry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsEntry.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsEntryCustomer.class, new JsonDeserializer<MissionControlStatsEntryCustomer>() {
            /* renamed from: a */
            public MissionControlStatsEntryCustomer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsEntryCustomer.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsEntryListing.class, new JsonDeserializer<MissionControlStatsEntryListing>() {
            /* renamed from: a */
            public MissionControlStatsEntryListing deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsEntryListing.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsEvent.class, new JsonDeserializer<MissionControlStatsEvent>() {
            /* renamed from: a */
            public MissionControlStatsEvent deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsEvent.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsFilter.class, new JsonDeserializer<MissionControlStatsFilter>() {
            /* renamed from: a */
            public MissionControlStatsFilter deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsFilter.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsFilterOption.class, new JsonDeserializer<MissionControlStatsFilterOption>() {
            /* renamed from: a */
            public MissionControlStatsFilterOption deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsFilterOption.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsModuleBanner.class, new JsonDeserializer<MissionControlStatsModuleBanner>() {
            /* renamed from: a */
            public MissionControlStatsModuleBanner deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsModuleBanner.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsModuleContainer.class, new JsonDeserializer<MissionControlStatsModuleContainer>() {
            /* renamed from: a */
            public MissionControlStatsModuleContainer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsModuleContainer.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsModuleDefault.class, new JsonDeserializer<MissionControlStatsModuleDefault>() {
            /* renamed from: a */
            public MissionControlStatsModuleDefault deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsModuleDefault.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsModuleTimeSeries.class, new JsonDeserializer<MissionControlStatsModuleTimeSeries>() {
            /* renamed from: a */
            public MissionControlStatsModuleTimeSeries deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsModuleTimeSeries.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsModuleVisitsOrders.class, new JsonDeserializer<MissionControlStatsModuleVisitsOrders>() {
            /* renamed from: a */
            public MissionControlStatsModuleVisitsOrders deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsModuleVisitsOrders.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsOnboardingTakeover.class, new JsonDeserializer<MissionControlStatsOnboardingTakeover>() {
            /* renamed from: a */
            public MissionControlStatsOnboardingTakeover deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsOnboardingTakeover.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsOnboardingTakeoverBullet.class, new JsonDeserializer<MissionControlStatsOnboardingTakeoverBullet>() {
            /* renamed from: a */
            public MissionControlStatsOnboardingTakeoverBullet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsOnboardingTakeoverBullet.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsPage.class, new JsonDeserializer<MissionControlStatsPage>() {
            /* renamed from: a */
            public MissionControlStatsPage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsPage.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsPageList.class, new JsonDeserializer<MissionControlStatsPageList>() {
            /* renamed from: a */
            public MissionControlStatsPageList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsPageList.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsPageMetadata.class, new JsonDeserializer<MissionControlStatsPageMetadata>() {
            /* renamed from: a */
            public MissionControlStatsPageMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsPageMetadata.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsParams.class, new JsonDeserializer<MissionControlStatsParams>() {
            /* renamed from: a */
            public MissionControlStatsParams deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsParams.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsRequestMetadata.class, new JsonDeserializer<MissionControlStatsRequestMetadata>() {
            /* renamed from: a */
            public MissionControlStatsRequestMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsRequestMetadata.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionAllListings.class, new JsonDeserializer<MissionControlStatsSectionAllListings>() {
            /* renamed from: a */
            public MissionControlStatsSectionAllListings deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionAllListings.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionBanner.class, new JsonDeserializer<MissionControlStatsSectionBanner>() {
            /* renamed from: a */
            public MissionControlStatsSectionBanner deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionBanner.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionCustomerInterests.class, new JsonDeserializer<MissionControlStatsSectionCustomerInterests>() {
            /* renamed from: a */
            public MissionControlStatsSectionCustomerInterests deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionCustomerInterests.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionDestinations.class, new JsonDeserializer<MissionControlStatsSectionDestinations>() {
            /* renamed from: a */
            public MissionControlStatsSectionDestinations deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionDestinations.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionEtsyTrafficBreakdown.class, new JsonDeserializer<MissionControlStatsSectionEtsyTrafficBreakdown>() {
            /* renamed from: a */
            public MissionControlStatsSectionEtsyTrafficBreakdown deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionEtsyTrafficBreakdown.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionFavorites.class, new JsonDeserializer<MissionControlStatsSectionFavorites>() {
            /* renamed from: a */
            public MissionControlStatsSectionFavorites deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionFavorites.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionGeolocation.class, new JsonDeserializer<MissionControlStatsSectionGeolocation>() {
            /* renamed from: a */
            public MissionControlStatsSectionGeolocation deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionGeolocation.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionHelp.class, new JsonDeserializer<MissionControlStatsSectionHelp>() {
            /* renamed from: a */
            public MissionControlStatsSectionHelp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionHelp.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionInventoryDetail.class, new JsonDeserializer<MissionControlStatsSectionInventoryDetail>() {
            /* renamed from: a */
            public MissionControlStatsSectionInventoryDetail deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionInventoryDetail.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionInventoryLeastViewedListings.class, new JsonDeserializer<MissionControlStatsSectionInventoryLeastViewedListings>() {
            /* renamed from: a */
            public MissionControlStatsSectionInventoryLeastViewedListings deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionInventoryLeastViewedListings.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionInventoryMostViewedListings.class, new JsonDeserializer<MissionControlStatsSectionInventoryMostViewedListings>() {
            /* renamed from: a */
            public MissionControlStatsSectionInventoryMostViewedListings deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionInventoryMostViewedListings.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionPlatforms.class, new JsonDeserializer<MissionControlStatsSectionPlatforms>() {
            /* renamed from: a */
            public MissionControlStatsSectionPlatforms deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionPlatforms.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionSearchTerms.class, new JsonDeserializer<MissionControlStatsSectionSearchTerms>() {
            /* renamed from: a */
            public MissionControlStatsSectionSearchTerms deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionSearchTerms.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionSocial.class, new JsonDeserializer<MissionControlStatsSectionSocial>() {
            /* renamed from: a */
            public MissionControlStatsSectionSocial deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionSocial.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionTrafficHero.class, new JsonDeserializer<MissionControlStatsSectionTrafficHero>() {
            /* renamed from: a */
            public MissionControlStatsSectionTrafficHero deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionTrafficHero.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionTrafficSources.class, new JsonDeserializer<MissionControlStatsSectionTrafficSources>() {
            /* renamed from: a */
            public MissionControlStatsSectionTrafficSources deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionTrafficSources.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionVisitsAndOrdersComparison.class, new JsonDeserializer<MissionControlStatsSectionVisitsAndOrdersComparison>() {
            /* renamed from: a */
            public MissionControlStatsSectionVisitsAndOrdersComparison deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionVisitsAndOrdersComparison.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsSectionWebsites.class, new JsonDeserializer<MissionControlStatsSectionWebsites>() {
            /* renamed from: a */
            public MissionControlStatsSectionWebsites deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsSectionWebsites.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(MissionControlStatsYearOverYear.class, new JsonDeserializer<MissionControlStatsYearOverYear>() {
            /* renamed from: a */
            public MissionControlStatsYearOverYear deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return MissionControlStatsYearOverYear.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(SOEDashboard.class, new JsonDeserializer<SOEDashboard>() {
            /* renamed from: a */
            public SOEDashboard deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return SOEDashboard.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(StatsCurrencyTotal.class, new JsonDeserializer<StatsCurrencyTotal>() {
            /* renamed from: a */
            public StatsCurrencyTotal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return StatsCurrencyTotal.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(StatsListingInsight.class, new JsonDeserializer<StatsListingInsight>() {
            /* renamed from: a */
            public StatsListingInsight deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return StatsListingInsight.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(StatsOverview.class, new JsonDeserializer<StatsOverview>() {
            /* renamed from: a */
            public StatsOverview deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return StatsOverview.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(StatsOverviewCurrencyElement.class, new JsonDeserializer<StatsOverviewCurrencyElement>() {
            /* renamed from: a */
            public StatsOverviewCurrencyElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return StatsOverviewCurrencyElement.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(StatsOverviewElement.class, new JsonDeserializer<StatsOverviewElement>() {
            /* renamed from: a */
            public StatsOverviewElement deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return StatsOverviewElement.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(StatsTotal.class, new JsonDeserializer<StatsTotal>() {
            /* renamed from: a */
            public StatsTotal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return StatsTotal.read(jsonParser);
            }
        });
        simpleModule.addDeserializer(SuggestionItem.class, new JsonDeserializer<SuggestionItem>() {
            /* renamed from: a */
            public SuggestionItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                return SuggestionItem.read(jsonParser);
            }
        });
        return simpleModule;
    }
}
