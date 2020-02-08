package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantDao extends JpaRepository<Tenant, Integer> {

    public Tenant findFirstByTenantId(int tenantId);

    public Tenant findFirstByTenantUUId(String uuid);

    public Tenant findFirstByTenantName(String tenantName);

}
