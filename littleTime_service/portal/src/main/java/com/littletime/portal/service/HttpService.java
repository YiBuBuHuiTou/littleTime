package com.littletime.portal.service;

import com.littletime.portal.config.http.HttpResult;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 23:05
 * @Version 1.0
 **/
public interface HttpService {

    HttpResult doGet(String url);

    HttpResult deGet(String url, Map<String, String> params) throws URISyntaxException;

    HttpResult doPost(String url);

    HttpResult doPost(String url, Map<String, String> params) throws UnsupportedEncodingException;
}
