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

   * __切换到root权限下解压jdk

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
     	CLASSPATH=$JAVA_HOME/lib/
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



### ubuntu中vim设置自动补全

1. [参考网址]: http://blog.sina.com.cn/s/blog_15c3d688a0102z61r.html

2. 总的思路

   ```markdown
   a): 下载文件
   	百度网盘链接：http://pan.baidu.com/s/1qYdNVf2
   	本地存储路径：D:/BaiduYunDownload/vim配置文件
   b): 将上述文件拷贝到ubuntu的home目录中进行修改
   	1. cp vim .vim -av
   	2. mv vimback.txt .vimrc
   c): 安装ctags(不然每次打开vim，都会报警)
    	cd /usr
    	sudo apt-get install ctags -y
   d): 确认.vimrc中存在如下两句
   	set tags=/usr/tags;
   	set autochdir
   ```


### Centos中找不到ifconfig的解决方法

1. ifconfig介绍

   ```markdown
   ifconfig命令是设置或显示网络接口的程序，可以显示机器的网卡信息
   ```

2. ifconfig出错信息

   ```markdown
   [root@42473063080b fyx]# ifconfig
   bash: ifconfig: command not found
   [root@42473063080b fyx]# 
   ```

3. 排除步骤

   ```markdown
   a): 首先确认环境变量信息，我们知道ifconfig是在/sbin中的。
   [root@42473063080b fyx]# echo $PATH
   /root/anaconda2/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
   b): 查看sbin
   	ls /sbin | grep ifconfig
   ```

4. ifconfig安装

   1. centos系统

   ```markdown
   a): 确认ifconfig所属的安装包
   	[root@42473063080b fyx]# yum search ifconfig
   	Loaded plugins: fastestmirror, ovl
   	Loading mirror speeds from cached hostfile
    	* base: mirrors.shuosc.org
    	* extras: mirrors.cn99.com
   	 * updates: mirrors.aliyun.com
   	============================== Matched: ifconfig ===============================
   	net-tools.x86_64 : Basic networking tools
   b): yum安装
   	yum install net-tools.x86_64 -y
   ```

   2. ubuntu系统

   ```markdown
   # 同步/etc/apt/sources.list /etc/apt/sources.list.d中的元索引，保障获取到最新软件。
   	apt-get update
   # ifconfig安装
   	apt-get install net-tools
   # ping安装
   	apt-get install iputils-ping
   # vim安装
   	apt-get install vim 
   ```


### awk匹配操作

1. 匹配以f开头和x结尾的内容

   ```markdown
   ll | awk '{if($2~/^ffafffx$/) print}'
   ```

   ​

