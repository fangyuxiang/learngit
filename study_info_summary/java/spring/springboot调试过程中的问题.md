# 问题记录

## Swagger页面前后端联调问题记录

### dto初始化失败

1. 报错log

   ```markdown
   Caused by: java.lang.NoSuchMethodException: com.caocao.testgroup.chase.server.model.ServerDTO.<init>()
   ```

2. 问题原因和解决方法

   * 问题原因

   ```markdown
   Spring管理的对象实例必须包含一个无参的构造函数
   ```

   * 解决方法

   ```markdown
   ServerDTO方法上添加构造方法注解： @NoArgsConstructor
   ```

   ​















## 生产部署

### 查看jvm参数值

```mark
jinfo -flags pid
```

