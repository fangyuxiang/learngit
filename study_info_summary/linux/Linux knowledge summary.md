# Linux knowledge summary

## 日常基础点汇总

### 获取shell脚本当前路径:

```shell
path=$(cd `dirname $0`; pwd)
```

### vim操作

| 作用   | 示例             | 命令                |
| ---- | -------------- | ----------------- |
| 全局替换 | 将Hello替换从hello | :%s/Hello/hello/g |

### 安装Java

1. 安装流程

   ```markdown
   下载JDK -> 压缩 -> 环境变量配置
   ```

2. jdk二进制(.tar.gz)进行安装

   * 下载网址

     ```markdown
     http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
     ```

   * 切换到root权限下解压jdk

     ```markdown
     a): 创建一个Java文件
     	mkdir /usr/java
     b)； 将上面下载的jdk，解压到java文件夹中
     	tar zxvf jdk-8u152-linux-x64.tar.gz
     ```

   * 配置环境变量

     ```markdown
     编辑：/etc/profile文件
     	JAVA_HOME=/usr/java/jdk1.8.0_152
     	CLASSPATH=%JAVA_HOME/lib/
     	PATH=$PATH:$JAVA_HOME/bin/
     	export JAVA_HOME CLASSPATH PATH
     ```

3. jdk的rpm包进行安装

   * rpm包的安装，删除，更新

     ```markdown
     a): rpm包删除（如果以前就有安装）
     	rpm -e package_name
     b): rpm包安装
     	rpm -ivh jdk-8u152-linux-x64.rpm
     c): rpm包更新
     	rpm -Uvh jdk-8u152-linux-x64.rpm
     ```

     ​

