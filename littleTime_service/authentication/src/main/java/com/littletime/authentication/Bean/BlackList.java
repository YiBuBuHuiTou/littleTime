package com.littletime.authentication.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author YiBuBuHuiTou
 * 黑名单
 */
@Entity
@Table(name = "black_list")
public class BlackList implements Serializable {
    // 序列化类版本号
    private static final long serialVersionUID = 1L;

    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true, length = 8, updatable = false)
    private int id;

    //ip
    @Column(name = "ips")
    private String ips;

    //说明
    @Column(name = "description", length = 128)
    private String description;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @Column(name = "create_date", updatable = false)
    @CreationTimestamp
    private Date createDate;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @Column(name = "update_date")
    @UpdateTimestamp
    private Date updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
