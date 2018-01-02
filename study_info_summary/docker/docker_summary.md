## docker安装

###install for windows

* 环境要求

  ```markdown
  windows10 64bit + hyper-v支持 + bios中硬件虚拟化开启
  ```

* 安装方法：

  * 满足上述所有条件,直接下载windows安装程序即可：

    a): 开启windows的Hyper-V

    ![开启hyper_v](E:\git_save_study\study_info_summary\docker\screen\开启hyper_v.png)

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

       ![点击Docker_Quickstart运行错误信息](E:\git_save_study\study_info_summary\docker\screen\点击Docker_Quickstart运行错误信息.png)

       2.2: 原因分析：

       ```markdown
       Docker Quickstart Terminal启动后会复制C:\Users\Administrator\.docker\machine\cache下的镜像boot2docker.iso到C:\Users\Administrator\.docker\machine\machines\default下面,但是由于内墙原因，导致boot2docker.iso镜像未成功下载。
       ```

       2.3: 解决方法

       ```markdown
       a): 下载对应的版本的boot2docker.iso,DockerToolBox版本信息：
       	DockerToolbox-17.10.0-ce 								         
       b): 将下载的boot2docker.iso放到以下路径：
       	C:\Users\Administrator\.docker\machine\cache，重启启动即可。
       备注：已经下载的相关文件路径存放目录：
       	本地：D:\install\docker\docker_v17_10_0
       	百度网盘：个人百度网盘docker目录
       ```

    3. 验证docker

       ```markdown
       Docker Quickstart Terminal启动后，输入docker verion可查看客户端与server端信息
       ```

       ![docker_check](E:\git_save_study\study_info_summary\docker\screen\docker_check.png)

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
第一种：添加加速器地址到：/var/lib/boot2docker/profile
a): 登陆到虚拟机（默认：default虚拟机）
	docker-machine ssh default
b): 修改profile配置文件
	方法一：切换到root权限进行修改（如果不切换，会提示无权限修改）
		sudo -i
	方法二：sed命令修改：（对于https://要加转义）
	sudo sed -i "s/EXTRA_ARGS='/EXTRA_ARGS='--registry-mirror=https:\/\/fn43kvml.mirror.aliyuncs.com/g" /var/lib/boot2docker/profile
c): 退出虚拟机，到windows提示符界面，重启虚拟机
	docker-machine restart default
第二种：新建虚拟机时就添加地址进去
	docker-machine create --engine-registry-mirror=https://fn43kvml.mirror.aliyuncs.com -d virtualbox virtualName
```

## 镜像加速器获取

### 阿里云

1. 登陆阿里云获取加速器地址: 

   登陆网址：

   ```markdown
   https://cr.console.aliyun.com/#/accelerator
   ```

   ![阿里云_加速器信息](E:\git_save_study\study_info_summary\docker\screen\阿里云_加速器信息.png)

### other

1. [Docker官方中国区]:https://registry.docker-cn.com

2. [网易]:http://hub-mirror.c.163.com

3. [ustc]: https://docker.mirrors.ustc.edu.cn

## 阿里云搭建私有仓库

1. 创建镜像仓库的命名空间

   ```markdown
   网址：https://cr.console.aliyun.com/?spm=5176.1971733.0.2.duOGn4#/namespace/index
   ```

   ![2017-12-24_阿里云_创建命名空间](E:\git_save_study\study_info_summary\docker\screen\2017-12-24_阿里云_创建命名空间.png)

2. 创建镜像和修改registry登陆密码

   ```markdown
   网址：https://cr.console.aliyun.com/?spm=5176.1971733.0.2.duOGn4#/imageList
   ```

   ![2017-12-24_阿里云_创建镜像仓库](E:\git_save_study\study_info_summary\docker\screen\2017-12-24_阿里云_创建镜像仓库.png)

3. 上传私有镜像到阿里云

   * 构建私有镜像(以ubuntu为基础镜像),并上传

     ```markdown
     登陆：(uname: 1255729193@qq.com  pwd: 330781fang)
     	sudo docker login -u=1255729193@qq.com registry.cn-hangzhou.aliyuncs.com
     	Password: 
     	Login Succeeded
     打标签：	
     	sudo docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/fyx/image-test:[镜像版本号]
     上传镜像：
     	sudo docker push registry.cn-hangzhou.aliyuncs.com/fyx/images-test:v1
     ```

     ![2017-12-24_image_tag](E:\git_save_study\study_info_summary\docker\screen\2017-12-24_image_tag.png)

     **阿里云确认上传成功**

     ```markdown
     镜像列表 -》管理 -》镜像版本
     ```

     ![2017-12-24_镜像版本信息](E:\git_save_study\study_info_summary\docker\screen\2017-12-24_镜像版本信息.png)

4. 私有镜像拉取(需要先进行登陆)

   ```markdown
   a): 未登陆进行拉取，提示错误。
   docker@fyx:~$ docker pull registry.cn-hangzhou.aliyuncs.com/fyx/image-test:v1
   Error response from daemon: pull access denied for registry.cn-		hangzhou.aliyuncs.com/fyx/image-test, repository does not exist or may require 'docker login'
   docker@fyx:~$ 
   b): 先登录在拉取(registry password: 330781fang)
   docker@default:~$ docker login -u=1255729193@qq.com registry.cn-hangzhou.aliyuncs.com
   Password: 
   Login Succeeded
   ```

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

     ![docker_virtualbox](E:\git_save_study\study_info_summary\docker\screen\docker_virtualbox.png)

3. docker优势

   * 更高效的利用系统资源

     ```markdown
     由于容器不需要执行硬件虚拟化和运行完整操作系统等额外开销，Docker对系统资源的利用率更高。
     无论是应用执行速度，内存损耗，文件存储速度，都比传统虚拟机更高效。
     相同配置的主机，docker比虚拟机可以运行更多数量
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
     	docker-machine ip vmhost 
     b):对指定虚拟机进行开启，关闭，重启
     	docker-machine start vmhost
     	docker-machine stop vmhost
     	docker-machine restart vmhost
     c):ssh登陆指定虚拟机
     	docker-machine ssh vmhost
     d): 创建虚拟机，并添加加速器
      	docker-machine create --engine-registry-mirror=https://fn43kvml.mirror.aliyuncs.com -d virtualbox fyx
      备注：更多命令通过help帮助获取：
      docker-machine --help
     ```

   **备注:通过xshell登陆虚拟机**

   ```markdown
   ip: docker-machine ip vmhost
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

       ![change_disk_虚拟介质管理](E:\git_save_study\study_info_summary\docker\screen\change_disk_虚拟介质管理.png)

       step2: 点击需要转移的disk，进行复制

       ![change_disk_复制](E:\git_save_study\study_info_summary\docker\screen\change_disk_复制.png)

       step3: 选择磁盘类型

       ![change_disk_类型选择](E:\git_save_study\study_info_summary\docker\screen\change_disk_类型选择.png)

       step4: 选择复制保存路径

       ![change_disk_新的保存路径](E:\git_save_study\study_info_summary\docker\screen\change_disk_新的保存路径.png)

       step5: 删除原有的disk

       ![change_disk_删除原有的disk](E:\git_save_study\study_info_summary\docker\screen\change_disk_删除原有的disk.png)

       step6: 添加新的disk

       ![change_disk_添加disk](E:\git_save_study\study_info_summary\docker\screen\change_disk_添加disk.png)

       step7：确认disk添加成功

       ![change_disk_添加成功](E:\git_save_study\study_info_summary\docker\screen\change_disk_添加成功.png)

       step8: 删除原有disk

       ![change_disk_虚拟介质管理删除olddisk](E:\git_save_study\study_info_summary\docker\screen\change_disk_虚拟介质管理删除olddisk.png)

       step9:确保是彻底删除还是保留原disk

       ![change_disk_虚拟介质管理删除olddisk_selction](E:\git_save_study\study_info_summary\docker\screen\change_disk_虚拟介质管理删除olddisk_selction.png)

   1.3 Kitematic

   * 在windows上直接使用docker容器。

     ![kitmatic](E:\git_save_study\study_info_summary\docker\screen\kitmatic.png)

### Docker相关基本概念

1. image(镜像)

   ```markdown
   a): 操作系统基本常识
   	操作系统分为内核和用户空间，对于Linux而言，内核启动后，会挂载root文件系统为其提供用户空间支持。
   b): Docker镜像
   	是一个特俗的文件系统，除了提供了容器运行时所需的程序，库，资源，配置等文件外，还包含了一些为运行时准备的一些配置参数(匿名卷，环境变量，用户等)。镜像不包含任何动态数据，其内容在构建之后也不会被改变。
   c): 镜像分层存储
   	镜像包含操作系统完整的root文件系统，其体积往往是庞大的，因此在DOcker设计时，就充分利用UniosFS的技术，将其设计为分层存储的架构。所以镜像是有多层文件系统联合组成。
   	镜像构建时，会一层层构建，前一层是后一层的基础，每一层构建完就不会再发生改变，任意一层的任何改变都只是发生在自己层。比如：删除前一层文件的操作，实际上不是真的删除前一层的文件，而是仅仅在当前层标记为该文件已删除，实际上该文件会一直跟随镜像。
   ```

2. container(容器)

   ```markdown
   a): 镜像与容器的关系
   	两者的关系好比面向对象编程设计中的类与实例一样，镜像是静态的定义，容器是镜像运行的实体，容器可以被创建，启动，停止，删除，暂停等。
   b): 容器的实质
   	容器的实质是进程，但与直接在宿主执行的进程不同，容器运行在属于自己的独立的用户名空间（即运行在一个隔离的环境中）。因此容器可以拥有自己的root文件系统，自己的网络配置，自己的进程空间，甚至是自己的用户ID空间。
   c): 容器存储层
   	以镜像为基础在当其上创建了一个容器存储层，为容器的运行提供读写操作。其生命周期依附于容器的生命周期。
   	按照Docker最佳实践要求，容器不应该向其存储层写入任何数据，容器的存储层应该是无状态的。所有文件的写入操作，都应该使用数据卷或者绑定宿主目录。在这些位置的读写会直接跳过容器存储层直接对宿主发生读写，其稳定性和可靠性更高。同时数据卷的生命周期独立于容器，可以保证数据的持久性。
   ```

3. registry(仓库)

   ```markdown
   a):概念
   	存放镜像的仓库，提供了集中存储，分发镜像的服务。
   b):仓库与标签
   	每个仓库包含多个标签，每个标签对应一个镜像，我们可以通过：<仓库>:<标签>格式来指定具体版本的镜像。以ubuntu镜像为例，ubuntu为仓库名称，14.04/16.04为标签。如果忽略了标签，默认以latest为标签。
   c):仓库命名规则：
   	经常以两段路径出现：fyx/nginx-proxy,前者表示DockerRegistry多用户下的用户名，后者表示软件名。
   ```

4. boot2docker

   ```markdown
   一个轻量级 linux 虚拟机，主要是为了让非 linux 系统也能用上 docker 。它实质上是一个 virtualbox 虚拟主机+一个能管理这个虚拟主机的命令行工具
   ```

### image container基础操作

1. 通用：

   | 操作              | 命令                    | 示例                 |
   | --------------- | --------------------- | ------------------ |
   | 查看docker版本      | docker version        | docker version     |
   | 查看docker信息      | docker info           | docker info        |
   | 查看某命令help信息     | docker help [command] | docker help attach |
   | 查看docker help信息 | docker --help         | docker --help      |

2. container相关：

   | 操作                                       | 命令                                       | 示例                                       |
   | ---------------------------------------- | ---------------------------------------- | ---------------------------------------- |
   | 创建container                              | docker create                            | docker create fyx/auto_test              |
   | 运行container                              | docker run                               | docker run fyx/auto_test /bin/bash       |
   | 运行container: 后台+交互式+tty                  | docker run -itd images /bin/bash         | docker run -itd ubuntu /bin/bash         |
   | 运行container后进入其bash控制台: 交互式和tty          | docker run -t -i images /bin/bash        | docker run -t -i ubuntu /bin/bash        |
   | 运行container并让其在后台运行，并端口映射                | docker run -p [port in container]:[port in pysical system] -d [image] command | docker run p 5000:5000 -d training/webapp python app.py |
   | 查看正在运行的所有container信息                     | docker ps                                | docker ps                                |
   | 查看最后创建的container                         | docker ps -l                             | docker ps -l                             |
   | 查看所的container                            | docker ps -a                             | docker ps -a                             |
   | 输出指定container的stdout信息(用来看log，效果与tail -f类似，会实时输出) | docker logs -f [container]               | docker logs  -f hello                    |
   | 查看container进程列表                          | docker top [container]                   | docket top hello                         |
   | 查看container详细信息                          | docker insepect [container]              | docker inspect hello                     |
   | 停止container                              | docker stop [container]                  | docker stop hello                        |
   | 强制停止container                            | docker kill [container]                  | docker kill hello                        |
   | 启动一个已经停止的container                       | docker start [container]                 | docker start hello                       |
   | 重启container                              | docker restart [container]               | docker restart hello                     |
   | 删除container                              | docker rm [container]                    | docker rm hello                          |
   | 查看指定端口映射关系                               | docker port [container] port             | docke port hello 5000                    |

   **备注：上述命令中container，既可以是容器名称，也可以是容器的id**

3. image相关：

| 操作                                   | 命令                                       | 示例                               |
| ------------------------------------ | ---------------------------------------- | -------------------------------- |
| 从container创建镜像                       | docker  commit [container] imagename     | docker commit hello fyx/hello:v2 |
| 从Dockerfile创建镜像                      | docker build -t [imageName] pathToFolder | docker build -t fyx/hello:v3 .   |
| 查看本地所有image                          | docker images                            | docker images                    |
| 在registry中搜索镜像                       | docker search [query]                    | docker search ubuntu             |
| 从registry中获取镜像(如果没有指定tag，默认使用latest) | docker pull [imageName]                  | docker pull ubuntu:14.04         |
| 给image打上标签                           | docker tag [imageTag] imageName          | docker t1 fyx/hello:v3           |
| 把本地的image上传到registry中（此时会上传所有的tag）   | docker push [imageName]                  | docker push fyx/hello:v3         |
| 删除本地image                            | docker rmi [imageName]                   | docker rmi fyx/hello:v3          |

### 容器间共享文件

```markdown
命令：
	docker run -v [host-src]:[container-dest][:<options>]来指定目录映射。
参数说明：
	host-src: 系统目录
	container-dest：映射到容器里的目录
	options：通常是权限配置：rw,ro
示例：
	docker run -v /var/temp:/var/tmp:rw -it ubuntu:14.04 /bin/bash
```

### 命令操作汇总

1. 对容器进行批量操作

   * 批量删除容器(该容器已处于退出状态)

     ```markdown
     docker ps -a | grep Exited | awk '{print $1}' | xargs -n 1 docker rm
     ```

   * 批量开启已关闭的容器

     ```markdown
     docker ps -a | awk '{print $1}' | xargs -n 1 docker start
     ```

   * 批量关闭已运行的容器

     ```markdown
     docker ps -a | grep Up | awk {'print $1'} | xargs -n 1 docker stop
     ```

   * kill docker进程

     ```markdown
     docker kill $(docker ps -qa)
     ```

   * 重新打包镜像

     ```markdown
     docker -a "author" -m "add information" container imagename
     ```

   * 删除None镜像

     ```markdown
     docker rmi $(docker images | awk '/<none>/ {print $3}')
     ```

   * -q, -f参数

     ```markdown
     a): 删除虚悬镜像
     	docker rmi $(docker images -q -f dangling=true)
     b): 删除指定名称镜像
     	docker rmi $(docker images -q ubuntu:14.04)
     c): 删除mongo3.2之前的镜像
     	docker rmi $(docker images -q -f before=mongo:3.2)
     ```

2. 安装docker-compose

   * 概念：

     ```markdown
     Docker Compose 是可以把多个容器连接到一起统一管理的工具。
     ```

   * 方式一：(由于网络原因安装容易失败)

     ```markdown
     step1:
     	sudo curl -L https://github.com/docker/compose/releases/download/1.17.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
     step2:
     	 chmod +x /usr/local/bin/docker-compose 
     step3: 
     	docker-compose version
     ```

   * 方式二：(推荐安装)

     ```markdown
     step1：
     	yum install epel-release -y
     	yum install python-pip -y
     step2:
     	yum install docker-compose
     step3:
     	docker-compose version
     ```

   * 安装Django and PostgreSQL

     * [官方参考网站]: https://docs.docker.com/compose/django/#define-the-project-components

     ```markdown
     命令：
     	docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123 mysql:latest
     登陆：
     	mysql -h192.168.99.101 -uroot -p123 -P3306
     ```



### docker mysql相关

1. pull mysql images

   ```markdown
   a): 查看当前mysql镜像版本信息：
   	docker search mysql
   b): 拉取指定mysql镜像版本
   	docker pull mysql:latest
   ```

2. 启动mysql和连接测试

   * 运行mysql容器：指定不同端口和访问机台

     ```markdown
     a): 指定特定的端口
     	docker run -dit --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin mysql:latest
     	确认运行OK： docker ps(有相关的顿口映射信息：0.0.0.0:3306->3306/tcp) 
     	e421a67040c9     mysql:latest       6 minutes        0.0.0.0:3306->3306/tcp   mysql
     b): 随机生成宿主机台的端口：(-P改成大写即可)
     	docker run -itd --name random -P -e MYSQL_ROOT_PASSWORD=admin mysql:latest
     	92c0823cb0bd     mysql:latest       11 minutes    0.0.0.0:32768->3306/tcp   random
     c): 为了安全考虑，只希望宿主机台可以访问mysql服务。
     	docker run -itd --name security -p 127.0.0.1:3311:3306 -e MYSQL_ROOOT_PASSWORD=admin mysql:latest
     	cd8d75c7aba9     mysql:latest       11 minutes   127.0.0.1:3309->3306/tcp  security
     ```


   * 进入容器内连接mysql

     ```markdown
     a): 进入容器内
     	docker exec -it mysql /bin/bash
     b): 本地建立mysql连接
     	mysql -uroot -padmin -P3306
     ```

   * mysql客户端远程连接

     ```markdown
     命令：
     	mysql -h192.168.99.101 -uroot -padmin -P3306
     备注：
     	-h: 指的是docker的宿主机器IP
     ```

3. mysql数据本地持久化

   * 挂载本地文件到mysql容器

     ```markdown
     命令：
     	docker run -d -p 3306:3306 --name mysql -v /share_dir/docker_fyx/mysql/data/:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=admin mysql:latest
     备注：
     	mysql中的目录： /var/lib/mysql
     ```

   * 下次访问容器，直接使用本地化保存的数据

     ```markdown
     a): -v挂载的目录路径使用本地化保存的路径一致即可。
     b): 命令同上，可自由选择--name的值。
     ```

### 目录挂载,实现数据持久化

[参考网站]: http://blog.csdn.net/magerguo/article/details/72514813

1. 镜像容器与宿主机器共享文件

```markdown
a): 格式
	-v  host_path:container_path
b): 举例: 
	1): 宿主机的/test目录挂载到容器的/soft目录,默认读写
	docker run -it --name fyx -v /test:/soft ubuntu:14.04 /bin/bash
	2): 以只读的模式挂载
	docker run -it --name fyx -v /test:/soft:ro ubuntu:14.04 /bin/bash
```

2. 数据卷

```markdown
a): 概念：
	其他容器启动可以直接挂载数据卷容器中定义的挂载信息。
b): 举例
	1. 先创建一个容器
	docker run -it -v /home/docker/:/usr/docker --name datavol ubuntu:14.04 /bin/bash
	2. --volumes-from指定从哪个数据卷来挂载数据
	docker run -it --volumes-from datavol ubuntu:14.04 /bin/bash
```

### docker inspect命令

1. 查看全部信息

   ```markdown
   docker inspect $contianerid
   ```

2. 查看指定信息

   ```markdown
   a): 查看docker容器在宿主机器上的pid
    docker inspect --format "{.State.Pid}" $containerID
    docker inspect -f "{.State.Pid}" $containerID
   ```

   ​








## 问题汇总

### docker commit失败或者hang住

```markdown
命令：
	docker commit $container  iamges:v
现象：
	一直处于等待状态中
原因：
	容器启用的时候使用-v参数，共享volumes导致。
```

