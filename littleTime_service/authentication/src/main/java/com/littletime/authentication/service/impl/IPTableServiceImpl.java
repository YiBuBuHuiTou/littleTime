package com.littletime.authentication.service.impl;

import com.cxd.littletime.common.util.IpUtils;
import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.BlackList;
import com.littletime.authentication.Bean.WhiteList;
import com.littletime.authentication.common.I18nUtils;
import com.littletime.authentication.config.CustomConfig;
import com.littletime.authentication.dao.BlackListDao;
import com.littletime.authentication.dao.WhiteListDao;
import com.littletime.authentication.interceptor.CommonInterceptor;
import com.littletime.authentication.service.IPTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "ip_access", cacheManager = "stringCacheManager")
public class IPTableServiceImpl implements IPTableService {

    private WhiteListDao whiteListDao;

    private BlackListDao blackListDao;

    private CustomConfig customConfig;

    //LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(CommonInterceptor.class);

    private static final String SUCCESS = "legal";

    private static final String ERROR = "illegal";

    @Autowired
    IPTableServiceImpl(WhiteListDao whiteListDao, BlackListDao blackListDao, CustomConfig customConfig) {
        this.customConfig = customConfig;
        this.blackListDao = blackListDao;
        this.whiteListDao = whiteListDao;
    }


    @Cacheable(key = "#ip")
    @Override
    public String checkIpIsValid(String ip) {
        boolean isValid = false;
        // 读取配置文件的黑白名单设置
        List<String> blackList = customConfig.getBlackList();
        List<String> whiteList = customConfig.getWhiteList();
        //黑名单和白名单拦截
        boolean isInWhiteConf = false;
        boolean isInWhiteDb = false;
        if (whiteList != null) {
            // 配置文件白名单
            isInWhiteConf = IpUtils.isIpInIpList(ip, whiteList);
        } else {
            //白名单不存在就全部设为允许
            isInWhiteConf = true;
        }
        // db 白名单check
        List<WhiteList> dbWhiteLists = whiteListDao.findAll();
        if (dbWhiteLists != null && dbWhiteLists.size() > 0) {
            whiteList.clear();
            for (WhiteList whiteList1 : dbWhiteLists) {
                whiteList.add(whiteList1.getIps());
            }
            isInWhiteDb = IpUtils.isIpInDBIpList(ip, whiteList);
        } else {
            isInWhiteDb = true;
        }
        if (isInWhiteConf || isInWhiteDb) {
            isValid = true;
            LOGGER.info(I18nUtils.getMessage("AUTHENTICATION_IP_IN_WHITE_LIST"));
        } else {
            isValid = false;
            LOGGER.warn(I18nUtils.getMessage("AUTHENTICATION_IP_NOT_IN_WHITE_LIST", ip));
        }
        boolean isInBlackConf = true;
        boolean isInBlackDb = true;
        if (blackList != null) {
            //配置文件黑名单
            isInBlackConf = IpUtils.isIpInIpList(ip,blackList);
        } else {
            isInBlackConf = false;
        }
        List<BlackList> blackLists  = blackListDao.findAll();
        if (blackList != null && blackLists.size() > 0) {
            isInBlackDb = IpUtils.isIpInDBIpList(ip, blackList);
        } else {
            isInBlackDb = false;
        }


        if (!isInBlackConf && !isInBlackDb) {
            isValid = true;
            LOGGER.info(I18nUtils.getMessage("AUTHENTICATION_IP_NOT_IN_BLACK_LIST"));
        } else {
            isValid = false;
            LOGGER.warn(I18nUtils.getMessage("AUTHENTICATION_IP_IN_BLACK_LIST", ip));
        }

        if (isValid) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    public WhiteList addIpToWhiteList(WhiteList whiteList) {
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteFromWhiteList(int id) {
        boolean result = false;
        try {
            whiteListDao.deleteById(id);
            result = true;
        }catch (Exception e) {
            LOGGER.error("白名单删除失败 id = "+ id , e);
        }
        return  result;
    }

    @Override
    @CacheEvict(allEntries = true)
    public WhiteList updateWhiteList(WhiteList whiteList) {
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public BlackList addIpToBlackList(BlackList blackList) {
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean deleteFromBlackList(int id) {
        return false;
    }

    @Override
    @CacheEvict(allEntries = true)
    public BlackList updateBlackList(BlackList blackList) {
        return null;
    }
}
