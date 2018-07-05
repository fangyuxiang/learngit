package enumTest;

import com.example.springboot.test.enumIpml.SignalEnum;

/**
 * @program: swagger-demo
 * @description: 单个枚举参数验证
 * @author: yuxiang.fang
 * @create: 2018-07-05 18:47
 **/
public class SignalTest {

    public static void main(String[] args) {
        System.out.println("单个枚举验证:" + SignalEnum.selectByCode(401));
    }
}
