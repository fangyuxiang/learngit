## docker安装

###install for windows

* 环境要求

  ```markdown
  windows10 64bit + hyper-v支持 + bios中硬件虚拟化开启
  ```

* 安装方法：

  * 满足上述所有条件：

    a): 开启windows的Hyper-V

    ![开启hyper_v](E:\git_save_study\study_info_summary\docker\开启hyper_v.png)

    b): 下载Docker for windows直接安装

    ```markdown
    网址：https://download.docker.com/win/stable/InstallDocker.msi
    ```

  * 满足部分条件(必须条件：64bit)

    a): 通过docker toolbox（DockerToolbox-17.10.0-ce.exe）安装

    ```markdown
    下载网址：http://get.daocloud.io/#install-docker-for-mac-windows (国内下载源)
    ```

    b): 验证docker是否安装成功

    1. 安装成功后桌面会出现以下三个图标

       ```markdown
       a): Docker Qucikstart: 
       	首次点击会创建default虚拟机，用于运行docker
       b): Oracle VMVirtualBox：
       	docker需要运行在liunx上，所以它是用来运行liunx虚拟机的。
       c): Kitematic
       	在windows上直接使用docker容器。
       ```

    2. 点击Docker Quick Start不能正常启动：出错信息和解决方法。

       2.1: 报错信息：

       ![点击Docker_Quickstart运行错误信息](E:\git_save_study\study_info_summary\docker\点击Docker_Quickstart运行错误信息.png)

       2.2: 原因分析：

       ```markdown
       Docker Quickstart Terminal启动后会复制C:\Users\Administrator\.docker\machine\cache下的镜像boot2docker.iso到C:\Users\Administrator\.docker\machine\machines\default下面,但是由于内墙原因，导致boot2docker.iso镜像未成功下载。
       ```

       2.3: 解决方法

       ```markdown
       a): 下载对应的版本的boot2docker.iso放到路劲下：	C:\Users\Administrator\.docker\machine\cache，重启启动即可。
       b): 版本对应关系：
       	DockerToolbox-17.10.0-ce 
       c): boot2docker存放路径：
       	本地：D:\install\docker\docker_v17_10_0
       	百度网盘：个人百度网盘docker目录
       ```

    3. 验证docker

       ```markdown
       Docker Quickstart Terminal启动后，输入docker verion可查看客户端与server端信息
       ```

       ![docker_check](E:\git_save_study\study_info_summary\docker\docker_check.png)

###install for linux

* 个人系统环境

  ```mark
  系统：centos 6.9 （cat /etc/redhat-release）
  内核：2.6.32-696.16.1.el6.x86_64 （uname -r）
  ```

* 安装： <http://blog.csdn.net/u010663021/article/details/70638873>

  ```markdown
  a):安装yum源
  	yum -y install http://dl.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-8.noarch.rpm
  b):更新yum
  	sudo yum update
  c): 安装docker
  	yum install docker-io
  ```

* 启动docker服务

  ```markdown
  a): 开启docker
  	service docker start 
  	(centos7: systemctl start docker)
  b): 关闭docker
  	setvice docker stop
  	(centos7: systemctl stop docker)
  c): 添加到开机启动项
  	chkconfig docker on
  	(centos7: systemctl enable docker)
  ```

* 查看docker信息：docker version

## 配置加速器

### Linux

```markdown
a): cetenos 6.9进行说明
	添加阿里云加速镜像地址到/etc/sysconfig文件中
	ADD_REGISTRY='--add-registry https://fn43kvml.mirror.aliyuncs.com'
```

### Windows

```markdown
添加加速器地址到：/var/lib/boot2docker/profile
a): 登陆到虚拟机（默认：default虚拟机）
	docker-machine ssh default
b): 修改profile配置文件
	方法一：切换到root权限进行修改（如果不切换，会提示无权限修改）
		sudo -i
	方法二：sed命令修改：（对于https://要加转义）
	sudo sed -i "s/EXTRA_ARGS='/EXTRA_ARGS='--registry-mirror=https:\/\/fn43kvml.mirror.aliyuncs.com/g"
c): 退出虚拟机，到windows提示符界面，重启default
	docker-machine restart default
```

## 镜像加速器

### 阿里云

1. 登陆阿里云获取加速器地址: 

   登陆网址：

   ```markdown
   https://cr.console.aliyun.com/#/accelerator
   ```

   ![阿里云_加速器信息](E:\git_save_study\study_info_summary\docker\阿里云_加速器信息.png)

### other

1. [Docker官方中国区]:https://registry.docker-cn.com

2. [网易]:http://hub-mirror.c.163.com

3. [ustc]: https://docker.mirrors.ustc.edu.cn

## Docker学习笔记

### Docker介绍

1. 简介

   ```markdown
   Docker是一个开源的引擎，可以轻松的为任何一个轻量级的，可移植，自给自足的容器。开发者在笔记本上编译测试通过的容器可以批量地在生产环境中部署，包括VMs, bare metal, OpenStack集群和其他的基础应用平台。
   ```

2. docker与虚拟机比较

   * 相同点

     ```markdown
     实现资源和系统环境的隔
     ```

   * 实现原理的差异

     ```markdown
     虚拟机：
     	虚拟出一套硬件后，在其上运行一套完整的操作系统，然后在该操作系统上运行所需的应用程序。
     Docker:
     	容器内的应用程序直接运行在宿主的内核上，容器内没有自己的内核。也没有硬件虚拟。
     ```

     ![docker_virtualbox](E:\git_save_study\study_info_summary\docker\docker_virtualbox.png)

3. docker优势

   * 更高效的利用系统资源

     ```markdown
     由于容器不需要执行硬件虚拟化和运行完整操作系统等额外开销，Docker对系统资源的利用率更高。
     无论是应用执行速度，内存损耗，文件存储速度，都比传统虚拟机更高效。
     相同配置的主机，docker比虚拟机可以运行更多数量的应
     ```

   * 更快速的启动时间

     ```markdown
     Docker由于直接运行在宿主机内核，无需启动完整的操作系统，因此可以做到秒级，甚至是毫秒级的启动时间，相对于传统虚拟机技术启动应用服务器往往需要数分钟，docker可以大大的节约开发，测试，部署的时间
     ```

   * 一致的运行环境

     ```markdown
     Docker的镜像提供了除内核外完整的运行环境，确保了应用运行环境的一致性，从而保证开发，测试，生产三者环境的一致。
     ```

   * 持续交付和部署

     ```markdown
     对于开发和运维人员来说，最希望的就是一次创建或配置，可以再在任意地方正常运行。
     使用Docker可以通过定制镜像来实现持续集成，交付，部署。
     开发人员可以通过Dockerfile来进行镜像构建，并结合持续集成系统进行集成测试。
     运维人员可以直接在生产环境中快速部署该镜像，甚至结合持续部署系统进行自动化部署。
     ```

   * 更轻松的迁移

     ```markdown
     由于Docker确保了执行环境的一致性，使得应用迁移更加容易。Docker可以部署在很多平台：物理机，虚拟机，公有云，私有云，笔记本等，且保持运行结果一致。
     ```

   * 更轻松的控制和维护

     ```markdown
     Docker使用的分层存储以及镜像的技术，使得应用重复部分的复用更为容易，也使得应用的维护更加简单，基于基础镜像的扩展也变得简单。
     Docker团队同各个开源项目团队一起维护了一大批高质量的官方镜像，即可直接在生产环境中使用，又可以作为基础进一步定制，大大降低应用服务镜像的制作成本。
     ```

4. docker与虚拟机对比总结

   | 特性    | 容器        | 虚拟机     |
   | ----- | --------- | ------- |
   | 启动    | 秒级        | 分钟级     |
   | 硬盘使用  | 一般为MB     | GB      |
   | 性能    | 接近原生      | 弱(ruo)于 |
   | 系统支持量 | 单机支持上千个容器 | 一般几十个   |

### Dockertoolbox知识点

1. 基础组件

   1.1 Docker Qucikstart:

   *  windows界面操作virtualbox上虚拟机的入口。

   * 首次运行默认创建名为：default的虚拟机，用于运行docker

   * docker-machine命令(不指定默认为：default)

     ```markdown
     a):查看指定虚拟机IP
     	docker-machine ip host_name 
     b):对指定虚拟机进行开启，关闭，重启
     	docker-machine start host_name
     	docker-machine stop host_name
     	docker-machine restart host_name
     c):ssh登陆指定虚拟机
     	docker-machine ssh host_name
     d): 创建虚拟机，并添加加速器
      	docker-machine create --engine-registry-mirror=https://fn43kvml.mirror.aliyuncs.com -d virtualbox fyx
      备注：更多命令通过help帮助获取：
      docker-machine --help
     ```

   **备注:通过xshell登陆虚拟机**

   ```markdown
   ip: docker-machine ip host_name获取
   username: docker
   password: tcuser
   ```

   1.2  Oracle VMVirtualBox：

   * 虚拟机管理平台

   * 修改images保存路径

     * 默认保存路径：

       ```markdown
       C:\Users\Think\.docker\machine\machines\default\disk.vmdk
       ```

     * 修改步骤 (**虚拟机需要处于关机状态**)

       step 1: 管理>虚拟介质管理

       ![change_disk_虚拟介质管理](E:\git_save_study\study_info_summary\docker\change_disk_虚拟介质管理.png)

       step2: 点击需要转移的disk，进行复制

       ![change_disk_复制](E:\git_save_study\study_info_summary\docker\change_disk_复制.png)

       step3: 选择磁盘类型

       ![change_disk_类型选择](E:\git_save_study\study_info_summary\docker\change_disk_类型选择.png)

       step4: 选择复制保存路径

       ![change_disk_新的保存路径](E:\git_save_study\study_info_summary\docker\change_disk_新的保存路径.png)

       step5: 删除原有的disk

       ![change_disk_删除原有的disk](E:\git_save_study\study_info_summary\docker\change_disk_删除原有的disk.png)

       step6: 添加新的disk

       ![change_disk_添加disk](E:\git_save_study\study_info_summary\docker\change_disk_添加disk.png)

       step7：确认disk添加成功

       ![change_disk_添加成功](E:\git_save_study\study_info_summary\docker\change_disk_添加成功.png)

       step8: 删除原有disk

       ![change_disk_虚拟介质管理删除olddisk](E:\git_save_study\study_info_summary\docker\change_disk_虚拟介质管理删除olddisk.png)

       step9:确保是彻底删除还是保留原disk

       ![change_disk_虚拟介质管理删除olddisk_selction](E:\git_save_study\study_info_summary\docker\change_disk_虚拟介质管理删除olddisk_selction.png)

   1.3 Kitematic

   * 在windows上直接使用docker容器。

     ![kitmatic](E:\git_save_study\study_info_summary\docker\kitmatic.png)

### image container基础操作

#### 

