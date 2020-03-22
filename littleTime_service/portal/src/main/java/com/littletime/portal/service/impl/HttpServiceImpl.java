package com.littletime.portal.service.impl;

import com.littletime.portal.config.http.HttpResult;
import com.littletime.portal.service.HttpService;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 23:07
 * @Version 1.0
 **/
@Service
public class HttpServiceImpl implements HttpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServiceImpl.class);

    private CloseableHttpClient closeableHttpClient;

    private RequestConfig requestConfig;

    @Autowired
    HttpServiceImpl(CloseableHttpClient closeableHttpClient, RequestConfig requestConfig) {
        this.closeableHttpClient = closeableHttpClient;
        this.requestConfig = requestConfig;
    }

    @Override
    public HttpResult doGet(String url) {
        LOGGER.debug("http get = " + url + "start");
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            HttpResult<String> result = new HttpResult();
            result.setCode(response.getStatusLine().getStatusCode());
            result.setMessage(response.getStatusLine().getReasonPhrase());
            if (response.getStatusLine().getStatusCode() == 200) {
                result.setData(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
            return result;
        } catch (ClientProtocolException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        LOGGER.debug("http get = " + url + "end");
        return null;
    }

    @Override
    public HttpResult deGet(String url, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return doGet(url);
        } else {
            try {
                URIBuilder uriBuilder = new URIBuilder(url);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
                return doGet(uriBuilder.build().toString());
            } catch (URISyntaxException e) {
                LOGGER.error(e.getMessage());
            }
            return null;
        }
    }

    @Override
    public HttpResult doPost(String url) {
        return doPost(url, null);
    }

    @Override
    public HttpResult doPost(String url, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> map : params.entrySet()) {
                list.add(new BasicNameValuePair(map.getKey(), map.getValue()));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = null;
            try {
                urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(e.getMessage());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        try {
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpResult<String> result = new HttpResult<>();
            result.setCode(response.getStatusLine().getStatusCode());
            result.setMessage(response.getStatusLine().getReasonPhrase());
            if (response.getStatusLine().getStatusCode() == 200) {
                result.setData(EntityUtils.toString(response.getEntity(),"UTF-8"));
            }
            return result;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
