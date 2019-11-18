package com.cxd.littletime.common.constant;

/**
 * 用户账号数据保存类型
 * @author YiBuBuHuiTou
 */
public enum AUTH_TYPE {

    /**
     * 用户账号数据为加密状态
     */
    ENCRYPTED("ENCRYPTED", 0 ),

    /**
     * 用户账号数据为未加密状态
     */
    DECRYPTED("DECRYPTED", 1);
    /**
     *枚举类型名字
     */
    private String name;

    /**
     *枚举类型｜序列
     */
    private int index;

    /**
     * 枚举类型构造函数
     * @param name 枚举类型名字
     * @param index 枚举类型｜序列
     */
    private AUTH_TYPE(String name, int index) {
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
    public static AUTH_TYPE getNameByIndex(int index) {
        return AUTH_TYPE.class.getEnumConstants()[index];
    }
}
