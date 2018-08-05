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



### less

```markdown
G:直接跳转到最后
ctrl + f: 往前跳转一页
ctrl + b: 往后退一页
ctrl + j: 往前进一行
ctrl + k: 往后退一行
```

### grep

```markdown
忽略文件中的#号注释行：
	grep -v "^#" file
```

### vim

```markdown
显示行号：
	:set number
取消显示行号:
	:set nonumber
锁进：
	:set autoindent
取消缩进：
	:set noautoindent
```

[vim配置]: https://blog.csdn.net/u010871058/article/details/54253774

### 数组

```shell
#!/bin/sh
#定义方法一 数组定义为空格分割
arrayWen=(a b c d e f)
#定义方法二
arrayXue[0]="m"
arrayXue[1]="n"
arrayXue[2]="o"
arrayXue[3]="p"
arrayXue[4]="q"
arrayXue[5]="r"
#打印数组长度
echo ${#arrayWen[@]}
#for 循环遍历 
for var in ${arrayWen[@]};
do
	echo $var
done
#while循环遍历
i=0
while [[ i -lt ${#arrayXue[@]} ]]; do
	echo ${arrayXue[i]}
	let i++
done

```

### 查看系统相关的信息

1. 查看系统版本

   ```shell
   cat /proc/version
   ```

2. 查看系统处理器

   ```markdown
   cat /proc/cpuinfo
   ```

3. 查看程序是否安装

   ```markdown
   rpm -qa | grep telegraf
   ```

### scp

1. 拷贝文件

   ```markdown
   scp file.txt ip:/target
   ```

2. 拷贝目录

   ```markdown
   scp -r dir/ ip:/target
   ```

### rm

1. 删除文件

   ```markdown
   rm file
   ```

2. 删除目录

   ```markdown
   rm -d dir
   ```

### ssh免密钥登陆

1. 实现方式

   ```markdown
   当前机台的公钥复制到需要访问的机器中。（id_rsa.pub）
   ```

2. 将当前机器的公钥复制到目标机台中

   ```markdown
   ssh-copy-id -i ~/.ssh/id_rsa.pub admin@10.80.223.38
   ```

3. 命令验证

   ```markdown
   ssh 10.80.223.38 ===> 登陆连接成功
   ```

4. 脚本验证

   ```markdown
   java程序中使用: id_rsa 私钥进行登录。
   ```

### kill进程

```markdown
ps -ef | grep -v "grep" | grep serverName | awk '{print $2}' | xargs kill -9
```

### tar.gz

```markdown
1. 打包
	tar -zcvf target.tar.gz target
2. 解压
	tar -zxvf target.tar.gz
3. 打包telegaf.d
	 sudo tar -zcvf telegrafd.tar.gz exec_*
4. 打包exec_*.sh
	sudo tar -zcvf execShell.tar.gz exec_*.sh
```

### vim

1. 删除指定行

   ```shell
   :3,5d
   ```

2. 全局替换

   ```markdown
   :%s/target/new/g
   ```

3. 获取当前目录路径

   ```shell
   dirpath=$(cd `dirname $0`; pwd)
   ```

### if

1. 变量通过""引号

   ```shell
   #!/bin/bash
   var='hello'
   if [ ! -n "$var"]; then
   	echo "Is null"
   else
   	echo "not null"
   fi
   ```

2. 直接通过变量判断

   ```shell
   #!/bin/bash
   if [ ! $var ]; then
   	echo "is null"
   else:
   	echo "not null"
   fi
   ```

3. 使用test判断

   ```shell
   #!/bin/bash
   var=
   if test -z "$var"
   then
    	echo "var is not set!"
   else
   	echo "var is set"
   fi
   ```

   ​

### sed

1. 获取ip

   ```shell
   a): 获取eth0的ip
   	ifconfig eth0 | sed -n '/inet addr/p' |  awk '{print $2}' | awk -F: '{print $2}'
   ```

   ​

### cron

1. 格式说明

   ```shell
   a): 语法
   	*****(5个*)
   	第一个*表示分钟，取值0-59
   	第二个*表示小时，取值0-23
   	第三个*表示一个月的第几天，取值1-31
   	第四个*表示第几个月，取值1-12
   	第五个*表示一周中的第几天，取值0-7，其中0和7都表示周日。
   ```

2. 示例说明

   ```shell
   【每个15分钟】
   	H/15 * * * *
   【在每个小时的前半个小时内的每10分钟】
   	H(0-29)/10 * * * *
   【每两小时45分钟，从上午9:45开始，每天下午3:45结束】
   	45 9-16/2 * * *
   【每两小时一次，每个工作日上午9点到下午5点】
   	H 9-16/2 * * 1-5
   【每隔两小时构建一次】
   	H H/2 * * *
   【每天中午下班前构建一次】
   	0-12 * * * *
   ```

3. 表格参考

   | 字段                 | *    | *    | *      | *    | *     |
   | ------------------ | ---- | ---- | ------ | ---- | ----- |
   | 含义                 | 分钟   | 小时   | 日期     | 月份   | 星期    |
   | 取值范围               | 0-59 | 0-23 | 1-31   | 1-12 | 0-7   |
   | 示例                 |      |      |        |      |       |
   | 每个两小时执行一次          | H    | H/2  | *      | *    | *     |
   | 每隔3天执行一次           | H    | H    | H/3    | *    |       |
   | 每个3天执行一次(每月的1-15号) | H    | H    | 1-15/3 | *    | *     |
   | 每周1,3,5执行一次        | H    | H    | *      | *    | 1,3,5 |


### echo

1. 换行输出

   ```shell
   echo -e "hello liunx"
   ```

### ls -F

1. 输出服务类型，并截取

   ```shell
   ls -F /usr/local/tomcat/ | grep '@$' 2>/dev/null | awk '{print substr($1,1,length()-1)}'
   ```

   ​

