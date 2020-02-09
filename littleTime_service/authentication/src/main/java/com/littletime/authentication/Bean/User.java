package com.littletime.authentication.Bean;

import com.cxd.littletime.common.constant.ENCRYPT_TYPE;
import com.cxd.littletime.common.util.CryptoUtils;
import com.cxd.littletime.common.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author YiBuBuHuiTou
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    // 序列化类版本号
    private static final long serialVersionUID = 1L;

    //log
    private static Logger LOGGER = LoggerFactory.getLogger(User.class);

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

    //角色(administrator:0 , infra_admin: 1 , tenant_admin: 2, tenant_user: 3)
    @Column(name = "role", length = 8)
    private int role;

    // 权限
    @Column(name = "privilege", length = 8)
    private int privilege;

    // 所属group
    //@ManyToOne(targetEntity = Tenant.class, fetch = FetchType.LAZY)  使用延迟加载回导致反序列化失败
    @ManyToOne(targetEntity = Tenant.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    // token
    @Column(name = "token", unique = true,length = 255)
    private String token;

    //token生成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @Column(name = "token_time")
    private Date tokenTime;

    // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @CreationTimestamp
    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;

    // 更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
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
        if (StringUtils.isEmpty(password)) {
            this.password = "";
        } else {
            try {
                this.password = CryptoUtils.hashAlgorithm(ENCRYPT_TYPE.SHA256, password);
            } catch (NoSuchAlgorithmException e) {
                this.password = "";
                LOGGER.error("encrypt password  failed. ", e);
            }
        }
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
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
        this.tokenTime = new Date();
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
