package com.littletime.authentication.Bean;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author YiBuBuHuiTou
 */
@Entity
@Table(name = "tenant")
public class Tenant {

    /**
     * tenant id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tenant_id", updatable = false)
    private int tenantId;

    /**
     *  tenant description
     */
    @Column(name = "description", length = 128)
    private String description;


    /**
     * tenant name
     */
    @Column(name = "tenant_name", length = 32, nullable = false, unique = true)
    private String tenantName;
    ;
    /**
     * create time
     */
    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    /**
     * update date
     */
    @Column(name = "update_date")
    @UpdateTimestamp
    private Date updateDate;

    /**
     * expiry date
     */
    @Column(name = "expiry_date")
    private Date expiryDate;


    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
