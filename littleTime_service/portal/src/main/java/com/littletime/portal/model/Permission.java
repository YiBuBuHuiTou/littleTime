package com.littletime.portal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 13:13
 * @Version 1.0
 **/
@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "bit_num", length = 2, unique = true)
    private int bitNum;

    @Column(name = "description", length = 64)
    private String description;

    @Column(name = "createDate",updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @UpdateTimestamp
    private Date updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBitNum() {
        return bitNum;
    }

    public void setBitNum(int bitNum) {
        this.bitNum = bitNum;
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
