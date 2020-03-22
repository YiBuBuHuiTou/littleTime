package com.littletime.portal.config.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 16:07
 * @Version 1.0
 **/
@Configuration
public class HttpClientConfig {

    @Value("${http.maxTotal}")
    private int maxTotal;

    @Value("${http.defaultMaxPerRoute}")
    private int defaultMaxPerRoute;

    @Value("${http.connectTimeout}")
    private int connectTimeout;

    @Value("${http.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    @Value("${http.socketTimeout}")
    private int socketTimeout;

    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;

    /**
     * 首先实例化一个连接池管理器，设置最大连接数、并发连接数
     *
     * @return poolingHttpClientConnectionManager
     */
    @Bean(name = "poolingHttpClientConnectionManager")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return poolingHttpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器
     * 这里需要以参数形式注入上面实例化的连接池管理器
     *
     * @param poolingHttpClientConnectionManager
     * @return
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder httpClientBuilder(@Qualifier("poolingHttpClientConnectionManager") PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        return httpClientBuilder;
    }

    @Bean(name = "closeableHttpClient")
    public CloseableHttpClient closeableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类
     * 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性，有需要的话可以在此设置
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder builder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectionRequestTimeout(connectionRequestTimeout);
        builder.setConnectTimeout(connectTimeout);
        builder.setSocketTimeout(socketTimeout);
        builder.setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
        return builder;
    }

    /**
     *
     * @return
     */
    @Bean(name = "requestConfig")
    public RequestConfig requestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }
}
