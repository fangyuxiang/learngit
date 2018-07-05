package com.example.springboot.test.enumIpml;

/**
 * @program: swagger-demo
 * @description: 枚举描述多个参数
 * @author: yuxiang.fang
 * @create: 2018-07-05 18:51
 **/
public enum TwoArgumentsEnum {
    APP_KEY_ERROR(111, "appKey 错误"),

    BAD_REQUEST(222, "错误的请求"),

    MISS_PARAMNETS(333, "缺少参数");

    private int code;
    private String desc;

    TwoArgumentsEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TwoArgumentsEnum selectByDesc(String desc) {
        TwoArgumentsEnum result = null;
        for (TwoArgumentsEnum sub : TwoArgumentsEnum.values()) {
            System.out.println("两个参数枚举遍历:" + sub);
            if (sub.desc.equalsIgnoreCase(desc)) {
                result = sub;
                break;
            }
        }
        return result;
    }


}
