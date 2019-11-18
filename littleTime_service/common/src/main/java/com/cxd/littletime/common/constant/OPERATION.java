package com.cxd.littletime.common.constant;

/**
 * 用户操作
 *@author YiBuBuHuiTou
 */
public enum OPERATION {
    // 注册          0
    REGISTER("REGISTER",0),
    //登录           1
    LOGIN("LOGIN",1),
    //修改密码        2
    CHANGE_PASSWORD("CHANGE_PASSWORD", 2),
    //删除密码        3
    DELETE_PASSWORD("DELETE_PASSWORD",3),
    //加密            4
    ENCRYPT("ENCRYPT", 4),
    //解密            5
    DECRYPT("DECRYPT", 5);
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
    private OPERATION(String name, int index) {
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
    public static OPERATION getNameByIndex(int index) {
        return OPERATION.class.getEnumConstants()[index];
      }
}