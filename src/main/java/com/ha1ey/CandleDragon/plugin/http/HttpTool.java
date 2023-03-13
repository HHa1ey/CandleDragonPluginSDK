package com.ha1ey.CandleDragon.plugin.http;



import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.HashMap;

public class HttpTool {
    public static Response get(String url, HashMap<String, String> headers) throws IOException {
        Response response = new Response(0, null , null,null);
        try{
            HttpURLConnection conn = getConn(url);
            conn.setRequestMethod("GET");
            for (String key : headers.keySet()) {
                conn.setRequestProperty(key, headers.get(key));
            }
            response = getResponse(conn);
        } catch(SocketTimeoutException var6){
            response.setError("连接超时！");
        } catch (IOException | KeyManagementException | NoSuchProviderException | NoSuchAlgorithmException var8){
            response.setError(var8.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public static Response post(String url, HashMap<String, String> headers, String postStr){
        Response response = new Response(0, null, null, null);
        try{
            HttpURLConnection conn = getConn(url);
            conn.setRequestMethod("POST");

            for (String key : headers.keySet()) {
                conn.setRequestProperty(key, headers.get(key));
            }
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postStr.getBytes());
            outputStream.flush();
            outputStream.close();
            response = getResponse(conn);
        } catch (Exception var8){
            response.setError(var8.getMessage());
        }
        return response;
    }

    private static Response getResponse(HttpURLConnection conn) {

        Response response = new Response(0, null, null, null);

        try{
            conn.connect();
            response.setCode(conn.getResponseCode());
            response.setHead(conn.getHeaderFields().toString());
            response.setText(streamToString(conn.getInputStream()));
        }catch (IOException var3){
            response.setError(var3.toString());
        }
        return response;
    }

    private static HttpURLConnection getConn(String url) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, ClassNotFoundException {
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        TrustManager[] trustManagers = new TrustManager[]{new Cert()};
        sslContext.init(null,trustManagers,new SecureRandom());
        HostnameVerifier ignoreHostnameVerifier = (s, sslSession) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        URL url_obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)url_obj.openConnection();
        conn.setRequestProperty("User-Agent",UserAgent.getRandomUA());
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);
        return conn;
    }

    private static String streamToString(InputStream inputStream) {
        String resultString;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        try{
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            resultString=stringBuffer.toString();
        } catch (IOException var6){
            resultString = var6.getMessage();
            var6.printStackTrace();
        }
        return resultString;
    }
}
