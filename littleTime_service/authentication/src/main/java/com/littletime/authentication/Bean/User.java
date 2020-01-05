package com.littletime.authentication.Bean;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author YiBuBuHuiTou
 */
@Entity
@Table(name = "user")
public class User {

    // 主键 并设置自增
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;

    //用户名
    @Column(name = "user_name", unique = true, nullable = false,length = 32)
    private String userName;

    // 身份证
    @Column(name = "credential",unique = true, nullable = false, updatable = false,length = 64)
    private String credential;

    //密码
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    //权限
    @Column(name = "role", length = 8)
    private int role;

    // 所属group
    @ManyToOne(targetEntity = Tenant.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    // token
    @Column(name = "toekn", unique = true,length = 128)
    private String token;

    //token生成时间
    @Column(name = "token_time")
    private Date tokenTime;

    // 创建时间
    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    // 更新时间

    @Column(name = "update_date")
    @UpdateTimestamp
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
