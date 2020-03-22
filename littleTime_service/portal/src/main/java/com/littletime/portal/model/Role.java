package com.littletime.portal.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author YiBuBuHuiTou
 * @Description
 * @Date 2020/3/21 13:32
 * @Version 1.0
 **/
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "role_name", length = 16,unique = true)
    private String roleName;

    @Column(name = "description", length = 64)
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
