package com.littletime.authentication.service;

import com.littletime.authentication.Bean.Tenant;

/**
 * @author YiBuBuHuiTou
 * tenant service
 */
public interface TenantBaseService {

    /**
     * 追加租户
     * @param tenant
     * @return
     */
    public boolean addTenant(Tenant tenant);

    /**
     * 更新租户
     * @param tenant
     * @return
     */
    public Tenant updateTenant(Tenant tenant);

    /**
     * 删除租户信息
     * @param tenantId
     * @return
     */
    public boolean deleteTenant(int tenantId);

    /**
     * 查询租户信息
     * @param tenantId
     * @return
     */
    public Tenant searchTenant(int tenantId);

}
