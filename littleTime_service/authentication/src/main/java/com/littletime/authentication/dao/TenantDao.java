package com.littletime.authentication.dao;

import com.littletime.authentication.Bean.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantDao extends JpaRepository<Tenant, Integer> {

    public Tenant findByTenantId(int tenantId);

    public Tenant findByTenantUUId(String uuid);

    public Tenant findByTenantName(String tenantName);

}
