package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;


public class HttpClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    private static ConnectionSocketFactory plainsf = null;
    private static LayeredConnectionSocketFactory sslsf = null;
    private static Registry<ConnectionSocketFactory> registry = null;
    private static PoolingHttpClientConnectionManager cm = null;

    private static HttpRequestRetryHandler httpRequestRetryHandler = null;

    private static RequestConfig requestConfig = null;

    private static CloseableHttpClient httpClient = null;

    static {
        plainsf = PlainConnectionSocketFactory.getSocketFactory();
        sslsf = SSLConnectionSocketFactory.getSocketFactory();
        registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();
        cm = new PoolingHttpClientConnectionManager(registry);
        // ???????????????
        cm.setMaxTotal(30);
        // ???????????????????????????
        cm.setDefaultMaxPerRoute(10);
        //??????????????????
        httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {// ?????????????????????5???????????????
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// ????????????????????????????????????????????????
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// ????????????SSL????????????
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// ??????
                    return false;
                }
                if (exception instanceof UnknownHostException) {// ????????????????????????
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// ???????????????
                    return false;
                }
                if (exception instanceof SSLException) {// ssl????????????
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // ??????????????????????????????????????????
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(5000)
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .build();

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler)
                .build();

    }

    /**
     * get??????
     *
     * @param url       ??????ur
     * @return
     */
    public static String doGet(String url) {
        String result = null;
        CloseableHttpResponse response = null;
        HttpGet httpget = new HttpGet(url);
        try {
            String resultEnc = "UTF-8";
            httpget.setConfig(requestConfig);
            response = httpClient.execute(httpget, HttpClientContext.create());
            result = EntityUtils.toString(response.getEntity(), resultEnc);
        } catch (Exception e) {
            LOGGER.error("get?????? doPost", e);
            return result;
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    LOGGER.error("get?????? doPost  IOException", e);
                }
            }
            httpget.abort();
        }
        return result;
    }

    /**
     * ???????????????
     *
     * @param url
     * @param paraMap
     * @return
     */
    public static byte[] doImgPost(String url, Map<String, Object> paraMap) {
        byte[] result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        try {
            // ?????????????????????
            JSONObject postData = new JSONObject();
            for (Map.Entry<String, Object> entry : paraMap.entrySet()) {
                postData.put(entry.getKey(), entry.getValue());
            }
            httpPost.setEntity(new StringEntity(postData.toString(), "UTF-8"));
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toByteArray(entity);
        } catch (ConnectionPoolTimeoutException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return result;
    }


}
