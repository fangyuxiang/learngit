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


## java高级用法

### StringUtils

```java
StringUtils.isNotEmpty(var)
```

### vavr

```java
 Option<ErrorCode> ecop = Option.of(errorCode);
 Option<Object> noneOption = Option.of(none);
```

### fastjson

1. 对象转json优雅输出

   * 紧凑型

     ```java
      User user = new User();
      //...set user data
     	
      ObjectMapper mapper = new ObjectMapper();
      System.out.println(mapper.writeValueAsString(user));

     output:
     	{"age":29,"messages":["msg 1","msg 2","msg 3"],"name":"mkyong"}
     ```

   * 优雅型

     ```java
     User user = new User();
     // ...set user data

     ObejctMapper mapper = new ObjectMapper();
     System.out.print(mapper.writerWithDefaultPrettyPrinter.writeValueAsString(user))
       
      output:
     	{
           "age" : 29,
           "messages" : [ "msg 1", "msg 2", "msg 3" ],
           "name" : "mkyong"
         }
     ```


   * 参考网站

     [english website]: http://www.mkyong.com/java/how-to-enable-pretty-print-json-output-jackson/
     [chinese website]: https://www.cnblogs.com/howiechih/p/4799142.html

2. json转换成对象

   * 转换成一个对象

     ```java
     String jsonString = "[{'id':'1'},{'id':'2'}]";
     ObjectMapper mapper = new ObjectMapper();
      List<Bean> list = (List<Bean>)mapper.readValue(jsonString,Bean.class)
     ```

   * 转换成对象列表

     ```java
     String jsonString = "[{'id':'1'},{'id':'2'}]";
     ObjectMapper mapper = new ObjectMapper();
     JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Bean.class)
      List<Bean> list = (List<Bean>)mapper.readValue(jsonString,javaType)
     ```

   * 转换成map对象

     ```java
     String jsonString = "[{'id':'1'},{'id':'2'}]";
     ObjectMapper mapper = new ObjectMapper();
     JavaType javaType = mapper.getTypeFactory().constructParametricType(HasMap.class, String.class, Bean.class)
      List<Bean> list = (List<Bean>)mapper.readValue(jsonString,javaType)
     ```

     [website]: https://blog.csdn.net/zhuyijian135757/article/details/38269715

   3. 自定义封装类

      ```java
      public final class JsonUtils{
          private static final ObjectMappper objectMapper = new ObjectMapper();
        
        
          public static String toJson(Object object){
              String json;
              try{
                  json = objectMapper.writeValueAsString(object);
              }catch(JsonProcessingException e){
                  throw new RuntimeException("json serializtion error",e); 
              }
              return json;
          } 
          
          public static String prettyJson(Object object){
              String json;
              try{
                  json=objectMapper.mapper.writerWithDefaultPrettyPrinter.writeValueAsString(object);
              }catch(JsonProcessingException e){
                  throw new RuntimeException("json serialization error",e);
              }
             retrun json;
          } 
        
          public static <T> T object(String json, Class<T> clazz){
              try {
                  return objectMapper.readValue(json, clazz);
              } catch (IOException e) {
                  throw new RuntimeException("json deserialization error", e);
              }
          }
        
         public static <T> List<T> ObjectList(String json, Class<T> clazz){
              try{
                 JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class,clazz)
                  return objectMapper.readValue(json,javaType)
              } catch (IOException e){
                  throw new RuntimeException("json deserialication error",e);
              }
         }  
      }
      ```

      ​










## 生产部署

### 查看jvm参数值

```mark
jinfo -flags pid
```

## jenkins部署目录

```markdown
/ccdata/jenkins/workspace/chase-dev/
```

### log日志目录

```markdown
/usr/local/springboot/chase/logs
```



## 知识汇总

### 名词解释

1. java bean

   ```markdown
   概念：是一套java规则，包含了如下几点
   	1. 类声明是public，并且包含无参构造函数。
   	2. 属性是私有的，通过set()和get()来访问。
   	3. 类支持事件，例如addXXXListener(XXXEvent e),也可以是自定义事件。
   	4. 提供一个所谓的自省/发射机制，这样在运行时可以查看java bean的各种信息。
   	5. 这个类应该是可序列化的，这样就可以把java bean的状态保存在磁盘中。
   ```

2. pojo

   ```java
   Plain Old Java Object
   	由来：由于我们需要事务、安全、性能、分布式，所以java推出了EJB(Enterprise Java bean),但是如果要使用它，必须要按照他的要求声明与自省业务无关的代码，背上沉重的枷锁。这时程序员就要求我们只要保留与自身相关的业务逻辑代码，而其他的安全、事务一样不能少。这就有了后来的spring boot。
   ```

3. Ioc和DI

   ```java
   Ioc:(控制反转)
   	Inversion of Control。
   DI:(依赖注入)
   	Dependency Injection
   说明：
   	以前类的声明是有程序调用者自己操作的，现在类的声明全都交给了spring，这就造成了程序的调用要依赖spring，主动权不在自己的手里啦。刚开始把这个叫做ios，后来改成DI，这样比较贴切。
   ```

4. AOP

   ```java
   AOP: （面向切片的编程）
   	Aspect Oriented Programming
   ```

   ​