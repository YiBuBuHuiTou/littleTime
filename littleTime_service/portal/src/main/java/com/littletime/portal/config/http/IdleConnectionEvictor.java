package com.littletime.portal.config.http;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/22 22:28
 * @Version 1.0
 **/
@Component
public class IdleConnectionEvictor extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(IdleConnectionEvictor.class);

    private HttpClientConnectionManager httpClientConnectionManager;

    private volatile boolean shutdown;

    @Autowired
    IdleConnectionEvictor(HttpClientConnectionManager httpClientConnectionManager) {
        super();
        this.httpClientConnectionManager = httpClientConnectionManager;
    }

    @Override
    public void run() {
        try{
            while(!shutdown) {
                synchronized (this) {
                    wait(5000);
                    httpClientConnectionManager.closeExpiredConnections();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 销毁释放资源
     */
    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}
