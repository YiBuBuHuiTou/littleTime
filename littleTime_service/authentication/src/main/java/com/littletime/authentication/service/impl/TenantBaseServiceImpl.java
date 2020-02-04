package com.littletime.authentication.service.impl;

import com.littletime.authentication.Bean.Tenant;
import com.littletime.authentication.service.TenantBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TenantBaseServiceImpl implements TenantBaseService {

    private static Logger LOGGER = LoggerFactory.getLogger(TenantBaseServiceImpl.class);

    /**
     * 追加租户
     *
     * @param tenant
     * @return
     */
    @Override
    public boolean addTenant(Tenant tenant) {
        return false;
    }

    /**
     * 更新租户
     *
     * @param tenant
     * @return
     */
    @Override
    public Tenant updateTenant(Tenant tenant) {
        return null;
    }

    /**
     * 删除租户信息
     *
     * @param tenantId
     * @return
     */
    @Override
    public boolean deleteTenant(int tenantId) {
        return false;
    }

    /**
     * 查询租户信息
     *
     * @param tenantId
     * @return
     */
    @Override
    public Tenant searchTenant(int tenantId) {
        return null;
    }
}
