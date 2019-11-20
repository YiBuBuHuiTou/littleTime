package com.cxd.littletime.common.constant;

/**
 * 加密方式
 * @author YiBuBuHuiTou
 */
public enum ENCRYPT_TYPE {

    /**
     * MD5加密方式
     */
    MD5("MD5",0),
    /**
     * SHA
     */
    SHA256("SHA256",1),

    /**
     * AES 加密方式
     */

    AES("AES",2);
    /**
     * 枚举类型名字
     */
    private String name;
    /**
     *  枚举类型位置
     */
    private int index;

    /**
     * operation 构造方法
     * @param name    名称
     * @param index   位置
     */
    private ENCRYPT_TYPE(String name, int index) {
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
    public static ENCRYPT_TYPE getNameByIndex(int index) {
        return ENCRYPT_TYPE.class.getEnumConstants()[index];
    }

    /**
     * 获取索引
     * @return 索引
     */
    public int getIndex() {
        return this.ordinal();
    }


}
