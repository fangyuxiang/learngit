# IDEA快捷键汇总

## 常用快捷键

1. 自动生成花括号

   ```markdown
   ctrl + shift + enter
   ```

2. 文件操作

   ```markdown
   ctrl + e: 打开最新操作的文件。
   cttl + n: 定位文件。
   ```

3. json字符串转义

   ```markdown
   a: 光标定位在双引号的中间，按住： alt+enter, 选择：Inject language or reference, 回车，选择：JSON(JSON file)
   b: 继续alt + enter, 选择：edit json fragment
   c: 编辑完成退出： ctrl + f4
   ```

4. project视图操作

   ```mariadb
   a: 视图切换
   	alt + 1: 切换到视图
   b: 视图大小调整
   	ctrl + alt + < : 往左移动
   	ctrl + alt + > : 往右移动
   c: project视图收缩：
   	shift + esc 
   ```

5. 导航栏

   ```markdown
   a: 调出视图栏
   	alt + v
   b: 临时展示导航栏
   	alt + home
   c: 临时导航栏消失：
   	esc
   ```

6. 快速查找当前类在project中的位置

   ```markdown
   a: 弹出select in视图：
   	alt + f1
   b: 选择： project view -> project
   ```

7. 自动生成not null判断

   ```markdown
   在需要判断的条件的后面输入: .notnull
   ```

   ​



## 自动生成类和方法的注释

1. setting中进行配置。

2. [website]: https://blog.csdn.net/qq_34581118/article/details/78409782#%E7%94%9F%E6%88%90%E7%B1%BB%E6%B3%A8%E8%A7%A3%E6%A8%A1%E6%9D%BF	"参考网站"

## 好用的插件

1.  Lombok plugin

   ```markdown
   简化我们的代码，通过注解即可实现get和setter等方法。
   ```

   * 简单介绍

     ```markdown
     @Getter：  
     @Setter：
     @ToString： 
     @Data： 
       1): 为所有字段添加@ToString, @EqualsAndHasCode, @Getter方法。
       2): 为非final字段添加@Setter
       3): 为final字段添加@RequireArgsConstructor
     @NonNull: 避免空指针
     @CleanUp: 自动调用close()方法。一般用于处理输入与输出流。
     @NoArgsConstructor: 自动生成无参数构造函数。
     @AllArgsConstructor: 自动生成全参数构造函数。
     @Builder: 实例化对象
     ```

2. Free MyBatis plugin。

   ```markdown
   mapper.xml 和 mapper interface映射更清晰，跳转方法。
   ```

   ​