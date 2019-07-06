package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.Map.Entry;

class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    public DefaultCreateReportSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        super(kit, str, str2, httpRequestFactory, HttpMethod.POST);
    }

    public boolean invoke(CreateReportRequest createReportRequest) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), createReportRequest), createReportRequest);
        StringBuilder sb = new StringBuilder();
        sb.append("Sending report to: ");
        sb.append(getUrl());
        Fabric.getLogger().d("CrashlyticsCore", sb.toString());
        int code = applyMultipartDataTo.code();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Create report request ID: ");
        sb2.append(applyMultipartDataTo.header("X-REQUEST-ID"));
        Fabric.getLogger().d("CrashlyticsCore", sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Result was: ");
        sb3.append(code);
        Fabric.getLogger().d("CrashlyticsCore", sb3.toString());
        return ResponseParser.parse(code) == 0;
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        HttpRequest header = httpRequest.header("X-CRASHLYTICS-API-KEY", createReportRequest.apiKey).header("X-CRASHLYTICS-API-CLIENT-TYPE", "android").header("X-CRASHLYTICS-API-CLIENT-VERSION", CrashlyticsCore.getInstance().getVersion());
        for (Entry header2 : createReportRequest.report.getCustomHeaders().entrySet()) {
            header = header.header(header2);
        }
        return header;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, CreateReportRequest createReportRequest) {
        Report report = createReportRequest.report;
        return httpRequest.part("report[file]", report.getFileName(), "application/octet-stream", report.getFile()).part("report[identifier]", report.getIdentifier());
    }
}
