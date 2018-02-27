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

## Jemter与Mysql

### mysql的连接

1. 安装mysql的驱动包

   * [mysql_jar]: https://dev.mysql.com/downloads/file/?id=474258

   * 新建测试计划，加载jar包路径

     ![01_测试计划导入jar包](E:\git_save_study\study_info_summary\Database\01_测试计划导入jar包.png)

2. 创建jdbc连接

   * 步骤：

     ```markdown
     右键[thread] -> [添加] -> [配置元件] -> [JDBC Connection Configuration]
     ```

   * 具体配置信息

     ![02_配置jdbc连接](E:\git_save_study\study_info_summary\Database\02_配置jdbc连接.png)

3. 创建JDBC的select请求

   * 步骤：

     ```markdown
     右键【线程组】——【添加】—— [Simpler] --- 【JDBC Request】
     ```

   * 具体配置信息

     ![03_jdbc多语句提交配置](E:\git_save_study\study_info_summary\Database\03_jdbc多语句提交配置.png)


### 参数之csv data set config

1. 创建.csv或.dat等结尾的文件

2. 新建csv data set config

   ```markdown
   线程组或者测试计划右键》添加》配置原件》CSV Data Set Config
   ```

3. csv data set config配置

   ![04_csv_config](E:\git_save_study\study_info_summary\Database\04_csv_config.png)

   ```markdown
   Filename:
   	和脚本同路径只需要填写文件名称或者相对路径，如果不在同一目录，则需要填写绝对路径。
   File encoding:
   	包含中文的时候建议：utf-8。
   Variable Names:
   	变量名称，多个变量名之间默认(\t)分割。
   Delimiter:
   	会根据Variable Names的设置自动引用。
   Allow quoted data:
   	双引号相关，例：如果参数中需包含，或者”等，该项可以选择True，效果如下：value1","value2
   Recycle on EOF?:
   	设置为True，则允许循环取值。
   Stop thread EOF?:
   	当Recycle on EOF设置为False，且Stop thread on EOF设置为True:那么读完csv文件的记录后，线程停止。此时线程运行不在依赖线程执行次数。
   Sharing mode:
   	All threads:所有线程组，所有线程组循环取值，线程组1取第一行，线程2取第二行。
   	Current threads group: 当前线程组，各个线程组分别取循环值。
   	Current thread: 当前线程，该测试内所有的线程都去第一行。
   ```

4. 请求中引用参数

   ```markdown
   select * from ${table} limit ${limit}
   ```

5. 线程组设置循环次数，原则是每次读取一行数据



## 日常命令汇总

### 1.日期相关

```markdown
1:获取当前时间
	select current_time()
2:获取当前日期
	select current_date()
3:获取当前时间和日期
	select now()
4:当前时间戳
	select current_timestamp()
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
1. 目标table不存在，则先创建后导入数据。
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
   	mysqldump -hlocalhost -ufyx -p330781 --all-databases > e:/yibaidb_all_dbs_dump.sql
   ```

### 8. select

1. 批量查询数据

   ```mysql
   select * from article where id in(1,3,5)
   ```

2. 使用concat连接查询的结果

   ```mysql
   select concat(firstName,".",lastName) as fullName from employees where employeesNumber =1;
   ```

3. 使用group by

   ```mysql
   作用：把相同的结果编为一组
   例如：从customer表中列出不重复的城市，及其数量
   select city, count(*) from customer group by city
   ```

4. 使用having

   ```mysql
   having允许有条件的聚合数据为组，缺点：group by+having速度有点慢
   select city, count(*) from customer group by city having count(*)>10
   ```

5. distinct去重复

   ```mysql
   select distinct city from customer order by id desc
   ```

6. 多表查询

   ```mysql
   select product from orders as os, orderdetails as ot where os.productNumber=ot.productNumber and os.reg_data>=2017-11-11 order by os.id desc
   ```

### 9. left join和inner join，right join, full join

* 相互间的区别

  ```markdown
  left join:
  	以左表为基准，取左表记录，右表匹配向左表（记录不足的地方为null）
  right join:
  	以右表为基准，取右表记录，左表匹配向右表(记录不足的地方为null)
  full join:
  	显示两个表的所有信息，相当于and操作，记录不足的地方互为null
  inner join:
  	只显示两个表相关联的信息。
  ```


* left join在select中的应用

  ```mysql
  描述：
  	每个客户都有零个或者多个订单，但是每个订单都属于唯一的一个客户。
  select c.customerNumber, c.customerName, orderNumber 
  from customers c
  	left join orders o on c.customerNumber=o.customerNumber
  where 
  	orderNumber is null
  ```

* left join 在delete中的应用

  ```mysql
  delete customers
  from customers left join orders on customers.customerNumber=orders.customerNumber
  where orderNumber is null
  ```

* inner join在delete中应用

  ```mysql
  描述：
  	删除t1表中id=1的行，并使用delete ... inner join语句删除t2表中的ref=1的行。
  delete t1, t2 from t1 inner join t2 on t1.id=t2.ref where t1.id=1
  ```

### 10. on duplicate key update

```mysql
作用：
	插入新行或者使用新值更新原有行记录。
For example
	insert into task(task_id,subject,start_date,end_date,description) values(4,'Test on duplicate key update',now(), now(),'Next Priority')
	on duplicate key update
	taks_id=task_id+1,
	subject="Test ON DUPLICATE KEY UPDATE";
```

### 11.数据库，数据的删除

* 删除数据库或者数据表

  ```mysql
  删除数据库：
  	drop database (if exists) db_name
  删除数据表：
  	drop table (if exists) tb_name
  删除视图表：
  	drop view table_name
  ```

* 删除数据

  ```mysql
  删除指定行数据：
  	delete from table_1 where id=2;
  删除所有行数据：
  	delete from table_1;
  删除所有行数据：性能会更好
  	truncate table 
  ```

### 12.alter table修改表的结构

* 修改列的属性

  ```mysql
  描述：
  	设置列的自动递增属性。
  alter table tasks change column task_id task_id int(11) not null auto_increment
  ```

* 添加新的列

  ```mysql
  指定列的后面添加：
  	alter table tasks add column complete decimal(2,1) not null after description
  指定列的前添加：
  	alter table tasks add column complete decimal(2,1) not null before description
  ```

* 删除指定的列

  ```mysql
  alter table tasks drop column description
  ```

* 重命名表名称

  ```mysql
  通过alter table进行修改：
  	alter table tasks rename to work_items;
  通过rename command进行修改：
  	rename table lastnames to unique_lastnames;
  ```

### 13.处理重复数据

1. 防止表中出现重复数据

   ```mysql
   策略：
   	MySQL数据表中设置指定的字段为：PRIMARY  KEY(主键)和UNIQUE(唯一)索引来保证数据的唯一性。
   For example:
   	create table person(
       	first_name char(20),
         	last_name char(20),
         	sex char(10),
         	primary key(first_name,last_name)
         	unique(first_name, last_name)
       );
   ```

2. insert ingore into与insert into

   ```mysql
   insert ignore into:
   	如果数据库中存在数据，则忽略该条数据。如果数据库中不存在数据，那么则添加数据。
   ```

3. 统计重复数据

   ```mysql
   select count(*) as repeatitions, first_name from person group by first_name having repeatitions>1; 
   ```

4. 过滤重复数据

   ```mysql
   distinct过滤：
   	select distinct first_name from person;
   group by读取不重复的数据：
   	select first_name, last_name from person group by(first_name, last_name);
   ```

5. 删除重复数据

   ```mysql
   1:复制相同的表和数据
   	create table tmp select last_name, first_name from person group by(first_name, last_name);
   2:删除有的数据表
   	drop table person;
   3:重命名tmp表
   	alter table tmp rename to person
   	rename tmp to person
   ```

### 14. 忘记密码时的处理方法

* 停止mysql

  ```mysql
  net stop mysql
  ```

* 启用mysqld with --skip-grant-table

  ```mysql
  描述：
  	跳过权限表认证
  mysqld --skip-grant-table
  ```

* 更新密码

  ```mysql
  1.新开cmd窗口
  	mysql -uroot -p (之后一路回车即可)
  2.更新密码
  	update mysql.user set authentication_string=password("new_pwd") where user='root'
  	flush privileges
  ```

* 任务管理器关闭mysqld进程

  ```mysql
  查看任务管理器，关闭mysqld进程。或者执行以下命令： taskkill /f /im mysqld.exe
  ```

* 启动mysql

  ```mysql
  net start mysql
  mysql -uroot -pnew_pwd即可登录
  ```


### 15. count()函数

1. count()函数的三种不同表现形式

   ```markdown
   count(*):
   	返回所有的行包含：null和非null行。
   	对于MyISAM表而言，其执行速度非常快。
   count(expression):
   	返回不包含NULL值的行数（有可能是重复的数据）。
   count(distinct expression):
   	返回不包含NULL值的唯一行数。
   ```

### 16. INSTR()

1. INSTR()函数说明

   ```markdown
   作用：
   	返回字符串中子字符串中第一次出现的index值。如果不存在，则返回0
   语法：
   	INSTR(str, substr) 不区分大小写
   	INSTR(str, BINARY substr) 区分大小写
   示例：
   	select instr('MySQL', 'sql')
   	select instr('MySQL', binary 'SQL')
   	select productname from products where instr(productname,'car')>0
   ```

2. INSRT & LIKE

   ```markdown
   共同点：
   	不区分大小写，执行全表扫描，执行速度相同。
   示例：
   	select productname from products where productname like '%Car%';
   ```


### 17.SUM()

1. 简单介绍

   ```markdown
   作用：
   	sum()函数用于计算一组值或表达式的总和： sum(distinct expression)
   如何工作的？
   	1.select语句中使用sum函数，如果没有匹配行则返回：NULL。
   	2.DISTINCT运算符允许计算集合中的不同值。
   	3.SUM函数忽略计算中的NULL值。
   ```

2. sum与format

   ```markdown
   format:用于格式化sum()函数的返回值。
   格式：
   	format(sum(),2)  ===>接收两个参数，第一个为sum函数，第二个为格式化小数点。
   实例：
   	format(sum(),2)  ===> 10,223.83
   	format(sum(),3)  ===> 10,223.830
   ```

3. sum与group by

   ```markdown
   需求：计算每个订单的总金额
   sql语句：
   	select format(sum(quantityordered * priceeach),2) total from orderdetails group by ordernumber order by total desc;
   limit的使用：
   	select format(sum(quantityordered * priceeach),2) total from orderdetails group by ordernumber order by total desc limit 10; 
   ```

4. sum与having

   ```markdown
   需求：计算每个订单号的总金额，并筛选总金额大于6000的订单。
   sql语句：
   	select ordernumber, format(sum(quantityordered * priceeach),2) from orderdetails group by ordernumber having sum(quantityordered * priceeach) > 60000 order by sum(quantityordered * priceeach); 
   ```

5. sum与null

   ```markdown
   背景：sum函数如果没有匹配的行，函数返回null，如何实现返回0.
   解决方法：使用coalesce()函数
   COALESCE()函数：
   	coalesce(arg1,arg2):接收两个参数，如果第一个参数为null，则返回第二个参数。
   示例：
   	select coalesce(sum(quantityordered * priceeach),0) from orderdetails where productcode = 's1_2212121';
   ```

6. sum与inner join

   ```markdown
   需求：计算取消订单金额的总和
   sql语句：
   	select format(sum(quantityordered * priceeach),2) loss from orderdetails inner join orders using(ordernumber) where status = 'Cancelled';
   ```

### 18.MIN()

1. MIN函数介绍

   ```markdown
   定义：返回一组中最小的值
   应用场景：查找最小数，选择最便宜的产品，获取最低的信用额度等
   语法：
   	MIN(DISTINCT expression);
   注意点：
   	1.DISTINCT对于MIN函数不产生作用。它只是为了ISO兼容性。
   	2.DISTINCT运算符在其他聚合函数(SUM,AVG,COUNT)中生效。
   ```

2. MIN与子查询

   ```markdown
   需求：筛选出价格最低的产品信息
   sql语句：
   	select productcode, productname, buyprice from products where buyprice=(select min(buyprice) from products);
   ```

3. MIN与GROUP BY

   ```markdown
   需求：查询每个产品线的最低价格产品
   sql语句：
   	select productline, min(buyprice) low_price from products group by productline order by low_price;
   ```

4. LEFT JOIN实现min效果

   ```markdown
   需求：在不使用MIN函数和子查询的情况下实现相同的效果：
   sql语句：
   	select a.productline, a.productcode, a.productname, a.buyprice from products a left join products b on a.productline=b.productline and b.buyprice<a.buyprice where b.productcode is null;
   ```

   ​