package com.ha1ey.CandleDragon.plugin.http;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class Cert implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {}

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {}

    @Override
    public X509Certificate[] getAcceptedIssuers() {return null;}
}