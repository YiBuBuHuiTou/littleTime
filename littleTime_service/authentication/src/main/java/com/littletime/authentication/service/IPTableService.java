package com.littletime.authentication.service;

import com.littletime.authentication.Bean.BlackList;
import com.littletime.authentication.Bean.WhiteList;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author YiBuBuHuiTou
 * ip table service
 */
public interface IPTableService {

    /**
     * 判断ip是否合法
     * @param ip
     */
    public String checkIpIsValid(String ip);

    /**
     * 追加白名单
     * @param whiteList
     * @return
     */
    public WhiteList addIpToWhiteList(WhiteList whiteList);

    /**
     * 删除白名单数据
     * @param id
     * @return
     */
    public boolean deleteFromWhiteList(int id);

    /**
     * 更新白名单数据
     * @param whiteList
     * @return
     */
    public WhiteList updateWhiteList(WhiteList whiteList);

    /**
     * 追加黑名单
     * @param blackList
     * @return
     */
    public BlackList addIpToBlackList(BlackList blackList);

    /**
     * 删除黑名单数据
     * @param id
     * @return
     */
    public boolean deleteFromBlackList(int id);

    /**
     * 更新黑名单数据
     * @param blackList
     * @return
     */
    public BlackList updateBlackList(BlackList blackList);

}
