# Database

## 安装

1. windows安装

   ```markdown
   a): 下载mysql-5.7.20-winx64.zip并解压，配置环境变量。
   b): 以管理员的身份运行cmd(Install/Remove of the Service Denied)，切换到bin目录执行：
   	mysqld --initialize --user=root 
   	mysqld -install
   	net start mysql
   c): 首次登陆
   	mysql -h localhost -u root -p(第一次登录没有密码，直接按回车过),登录成功
   ```

   * 安装过程中出现的问题和解决方案

     * 提示缺少msvcp120.dll

       ```markdown
       根据自己系统的版本选择对应的版本进行安装：vcredist
       ```

     * net start mysql启动失败

       ```markdown
       a)提示的错误信息：
       E:\software_ins\mysql-5.7.19-winx64\bin>net start mysql
       MySQL 服务正在启动 .
       MySQL 服务无法启动。

       服务没有报告任何错误。

       请键入 NET HELPMSG 3534 以获得更多的帮助。
       b): 解决方法：通过查看log日志，确认失败原因
       	mysqld --console
       ```

     * mysql.plugin和mysql.user table不存在

       ```markdown
       a): 新版mysql安装，执行initialize
       	mysqld --initialize --user=root
       ```

   * 忘记root密码时

     ```markdown
     a): 跳过权限认证表进行连接修改
     	step1: 关闭mysql服务（net stop mysql）
     	step2: bin目录下执行：mysqld --skip-grant-tables
     	step3: 打开另一个cmd，直接输入mysql并回车
     	step4: 连接权限数据库： user mysql;
     	step5: 改密码： update user set authentication_string="330781" where user="root"
     	step6: 刷新权限： flush privileges
     	step7: 重新使用新密码登陆：mysql -hlocalhost -uroot -p330781 -P3306
     	step8: 如果出现如下提示，那就更新下密码即可：
     		mysql> show databases;
     		ERROR 1820 (HY000): You must reset your password using ALTER USER statement 		before executing this statement.
     		cmd:  alter user 'root'@'localhost' identified by "330781";
     ```

   * mysqld服务查看和删除

     ```markdown
     a): 查看
     	sc query mysql
     b): 删除
     	sc delete mysql
     ```



### 账号信息

| 安装机台信息 | host      | 用户名  | 密码     | 端口号           |
| ------ | --------- | ---- | ------ | ------------- |
| HP pc  | localhost | fyx  | 330781 | 3306(default) |

## 日常命令汇总

### 1.日期相关

```markdown
1:获取当前时间
	select current_time()
2:获取当前日期
	select current_date()
3:获取当前时间和日期
	select now()
```

###2.查看mysql版本号

```markdown
1: 未连接数据库
	mysql\bin> mysql -V(大写)
	mysql\bin> mysql --help | grep "Distrib"
2：已经连接数据库
	mysql> select version();
	mysql> \s;
	mysql> status;
```

###3.mysql数据库导入与导出

**导入**

```markdown
1: 创建目标数据库
	CREATE DATABASE IF NOT EXISTS yibaidb DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
2: 导入数据
	2.1： 方法一：
		 mysql>use yibaidb;
		 mysql>source D:/worksp/yibaidb.sql;
	2.2: 方法二：
		mysql -hlocalhost -ufyx -p330781 yibaidb <D:/worksp/yibaidb.sql
```

**导出**

```markdown
1: 方法一
	mysqldump -hlocalhost -ufyx -p330781 database_name >e:/export_database.sql
2：方法二
	select * from fromtable into outfile "e:/fromtable.txt"
```

### 4.快速创建相同结构的表

```markdown
1: 原有表的结构：
	mysql> SHOW CREATE TABLE a:
	CREATE TABLE 'a' (
	 'name' varchar(50) default null,
	 key 'name'('name')
	) ENGINE=MyISAM DEFAULT CHARSET=latin1;
2: 快速创建相同结构的表，包含索引：
	mysql> create table b like a;
    mysql> show create table b;
     CREATE TABLE 'b'(
      'name' varchar(50) default null,
      key 'name'("name")
     ) ENGINE=MyISAM DEFAULT CHARSET=latin1;
3: 快速创建一个相同结构的表，但是不创建索引
	mysql> create table c select * from a limit 0;
	mysql> show create table c;
	CREATE TABLE 'C'(
	 'name' varchar(50) default null,
	) ENGINE=MyISAM DEFAULT CHARSET=latin1;
```

###5.一个表的数据批量导入另一张表中

```mysql
1. 不管目标table是否存在
	mysql> create table target_table select * from soucre_table;
2.目标table存在，且字段与原table相同
	a): create table target_table like source_table;
	b): insert into target_table select * from source_table;
```

### 6.limit使用

```markdown
1. 查询第一行记录
	select * from table limit 1;
2. 查询第n行到m行记录
	select * from table limit n-1, m-n
	select * from table limit 5,10: 返回第6行到第15行的记录。
	select * from table limit 4,1; 返回第5行。
3. 查询前n行记录
	select * from table limit n;
	select * from table limit 0,n;
4. 查询后n行记录
	select * from table order by id desc limit n; //倒序排序，取钱n行，id为自增形式。
5. 查询一条记录($id)的下一条记录
 	select * from table where id>$id order by id asc limit 1;
6. 查询一条记录($id)的上一条记录
	select * from table where id<$id order by id desc limit 1;
```

### 7.数据库多种备份

1. 仅备份MySQL数据库结构

   ```mysql
   mysqldump -hlocalhost -ufyx -p330781 --no-data yiibaidb > E:/yibaidb_no_data.sql
   ```

2. 仅备份MySQL数据库数据

   ```mysql
   mysqldump -hlocalhost -ufyx -p330781 --no-create-info yiibaidb >e:/yibaidb_only_data.sql
   ```

3. 将多个数据库备份到一个文件中

   ```mysql
   a):将多个数据库备份到一个文件
   	mysqldump -hlocalhost -ufyx -p330781 database_1, database_2 > e:/yibaidb_multi_db.sql
   b):将所有的数据库备份到一个文件中。
   	mysqldump -hlocalhost -ufyx -p330781 --all-database > e:/yibaidb_all_dbs_dump.sql
   ```

   ​

