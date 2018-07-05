package enumTest;

import com.example.springboot.test.enumIpml.TwoArgumentsEnum;

/**
 * @program: swagger-demo
 * @description: 多个参数枚举验证
 * @author: yuxiang.fang
 * @create: 2018-07-05 18:59
 **/
public class TwoParamsTest {

    public static void main(String[] args) {
        System.out.println("两个参数时枚举验证："+ TwoArgumentsEnum.selectByDesc("缺少参数"));
        System.out.println("获取BAD_REQUEST.id:" +  TwoArgumentsEnum.BAD_REQUEST.getCode());
        System.out.println("获取子定义:" +  TwoArgumentsEnum.BAD_REQUEST);
        System.out.println("type类型:" + TwoArgumentsEnum.MISS_PARAMNETS.getClass());
    }
}
