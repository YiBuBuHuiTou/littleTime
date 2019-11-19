package com.cxd.littletime.common.constant;

/**
 * 用户权限
 * @author YiBuBuHuiTou
 */
public enum PERMISSION {
    /**
     * 注册权限 bit0   0：不可， 1：可
     */
    REGISTER("REGISTER", 0),

    /**
     * 登录权限 bit1    0：不可， 1：可
     */
    LOGIN("LOGIN",1);

    /**
     *权限名
     */
    private String name;

    /**
     *权限位
     */
    private int index;

    /**
     * 构造方法
     * @param name 权限名
     * @param index 权限位
     */
    private PERMISSION(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     *  根据index获取枚举名
     * @param index  索引
     * @return       枚举名
     */
    public static PERMISSION getNameByIndex(int index) {
        return PERMISSION.class.getEnumConstants()[index];
    }
}
