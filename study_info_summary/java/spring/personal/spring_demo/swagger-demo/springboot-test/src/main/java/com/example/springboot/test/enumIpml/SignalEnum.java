package com.example.springboot.test.enumIpml;/**
 * @author yuxiang.fang
 * @date 2018/7/5 18:13
 * 类说明:
 */

/**
 * @program: swagger-demo
 * @description: 当个参数enum
 * @author: yuxiang.fang
 * @create: 2018-07-05 18:13
 **/
public enum SignalEnum {
    BAT_REQUEST(401),
    BAD_GATEWAY(300);

    private int code;

    SignalEnum(int code) {
        this.code = code;
    }

    public static SignalEnum selectByCode(int code) {
        SignalEnum result = null;
        for (SignalEnum sub : SignalEnum.values()) {
            System.out.println("sub:" + sub);
            if (sub.code == code) {
                result = sub;
            }
        }
        return result;
    }

}
