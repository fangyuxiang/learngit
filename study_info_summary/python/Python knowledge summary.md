

# Python knowledge summary

## 日常知识汇总

### logging中的日志配置的三种方式

* 直接在脚本中定义
  * 基本格式

    ```markdown
    # 定义logger文件对象
    logger=logging.getLogger("filename") #如果多个logger对象，他们的名称是相同则指向同一个对象。
    logger.setLevel(logging.DEBUG)

    # 定义日志输出格式
    fmt=logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

    # 定义handler
    con_handler=logging.StreamHandler() #日志输出到串口
    file_handler=logging.FileHandler("file.log", encoding="utf8") #日志输出到文件
    handler.setLevel(logging.DEBUG) # 日志筛选级别以handler设置为主。

    # 设置handler日志输出格式和添加handler到logger对象
    handler.setFormater(fmt)
    logger.addHandler(handler)

    # 测试log
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")
    ```

  * 四种handler说明

    ```markdown
    a):串口handler
    	con_handler=logging.StreamHandler()
    b): 文件handler
    	file_handler=logging.FileHandler("file.log", encoding='utf8')
    c): 日志回滚：根据时间
    	from logging.handlers import TimedRotatingFileHandler
    	time_handler=TimedRotatingFileHandler('timeRotat.log', when='S', interval=10, backupCount=2, encoding='utf8')
    	### 指定日志的后缀名(可选，但是需要和下面配合使用)
        log_file_handler.suffix = "%Y-%m-%d_%H-%M-%S.log"
        ### 用于匹配日志的后缀文件名
        log_file_handler.extMatch = re.compile(r"^\d{4}-\d{2}-\d{2}_\d{2}-\d{2}-\d{2}.log$")
    d): 日志回滚：根据文件大小
    	from logging.handlers import RotatingFileHandler
    	rotat_handler=RotatingFileHandler("rotat_file.log", maxBytes=1000 * 1024, backupCount=2)
    	# 如果backupcount=0； 代表所有的log都写在rotating.log上
        # 如果backupcount > 0: 代表在rotating.log的大小大于maxBytes时，写入下一个文件：rotating.log.1
        # 1M = 1024 * 1kb(1kb = 1000 kb) ==> 1024 * 1000
    ```

* config.conf文件配置

  * 配置文件内容: config.conf

  ```markdown
  #必须要包含root
  [loggers]
  keys=root,configExample
  #handler个数
  [handlers]
  keys=consoleHandler,fileHandler,rotatingHandler,timeRotatingHandler

  [formatters]
  keys=simpleFormatter

  [logger_root]
  level=DEBUG
  handlers=consoleHandler
  # 定义了configExample这个logger对象中add的handler
  [logger_configExample]
  level=DEBUG
  handlers=consoleHandler,fileHandler,rotatingHandler,timeRotatingHandler
  qualname=configExample
  propagate=0

  #控制台handler
  [handler_consoleHandler]
  class=StreamHandler
  level=DEBUG
  formatter=simpleFormatter
  args=(sys.stdout,)

  # file handler
  [handler_fileHandler]
  class=FileHandler
  args=("cfg_logging.log",'a')
  level=DEBUG
  formatter=simpleFormatter

  # 日志回滚：根据文件大小
  [handler_rotatingHandler]
  class=handlers.RotatingFileHandler
  args=('cfg_rotating.log', 1000 * 1024, 2)
  level=DEBUG
  formatter=simpleFormatter

  # 日志回滚：根据设定时间
  [handler_timeRotatingHandler]
  class=handlers.TimedRotatingFileHandler
  args=('cfg_timeRotat.log','S', 10, 2)
  level=DEBUG
  formatter=simpleFormatter

  # 日志输出格式
  [formatter_simpleFormatter]
  format=%(asctime)s - %(name)s - %(levelname)s -%(message)s
  ```

  * 脚本中的内容

    ```markdown
    logging.config.fileConfig("logging.conf")
    # create logger
    logger = logging.getLogger('configExample')
    # application code
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")
    ```

* yaml文件配置

  * 配置文件内容：config.yaml

  ```markdown
  version: 1
  formatters:
    simple:
      format: '%(asctime)s - %(name)s - %(levelname)s - %(message)s'
  handlers:
    console:
      class: logging.StreamHandler
      level: DEBUG
      formatter: simple
      stream: ext://sys.stdout
    info_file_handler:
      class: logging.handlers.RotatingFileHandler
      level: INFO
      formatter: simple
      filename: yaml_test.log
      maxBytes: 1024 * 1000
      backupCount: 5
      encoding: "utf8"
  loggers:
    simpleExample:
      level: DEBUG
      handlers: [console,info_file_handler]
      propagate: no
  root:
    level: DEBUG
    handlers: [console]
  ```

  * 脚本中的内容

    ```markdown
    # 获取配置文件内容
    with open('logging.yaml', 'r') as f_conf:
        dict_conf = yaml.load(f_conf)
    logging.config.dictConfig(dict_conf)
    # application code
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")
    ```



### 深拷贝与浅拷贝区别

1. 概念和区别

   ```markdown
   a): Python中对象的赋值都是进行对象引用(内存地址)传递。
   b): 使用copy.copy(),可以进行对象的浅拷贝，它复制了对象，但对于对象中的元素，依然使用原始的引用。
   c): 如果需要复制一个容器对象，以及它里面的所有元素(包含元素的子元素)，可以使用copy.deepcopy()进行深拷贝。
   d): 如果元祖变量中只包含了原子类型对象，则不能深拷贝。
   ```

2. 实例演练

   * 浅拷贝

     ```markdown
     import copy
      
     will = ["Will", 28, ["Python", "C#", "JavaScript"]]
     wilber = copy.copy(will)
      
     print id(will)
     print will
     print [id(ele) for ele in will]
     print id(wilber)
     print wilber
     print [id(ele) for ele in wilber]
      
     will[0] = "Wilber"
     will[2].append("CSS")
     ```

     ![2017-12-10_python浅拷贝示意图](E:\git_save_study\study_info_summary\python\screen\2017-12-10_python浅拷贝示意图.png)

   * 深拷贝

     ```markdown
     import copy
      
     will = ["Will", 28, ["Python", "C#", "JavaScript"]]
     wilber = copy.deepcopy(will)

     will[0] = "Wilber"
     will[2].append("CSS")
     ```

     ![2017-12-10_python深拷贝示意图](E:\git_save_study\study_info_summary\python\screen\2017-12-10_python深拷贝示意图.png)

   * 元祖变量只包含原子类型对象，不能进行深拷贝。

     ![2017-12-10_元素只包含原子类不能进行深拷贝](E:\git_save_study\study_info_summary\python\screen\2017-12-10_元素只包含原子类不能进行深拷贝.png)

### Anaconda介绍

1. 概念和优点

   [网址]: http://python.jobbole.com/86236/	"参考网站链接"

   * 当前python使用现状

     ```markdown
     当前python使用过程中比较头疼的就是包管理和不同python版本切换的问题。针对这些问题，python会有不少发行版的python，比如WinPython，Anaconda等（将python和常用的package包打包，方便使用者）。还有virtualenv,pyenv等工具管理虚拟环境
     ```


   * Anaconda介绍

     ```markdown
     Anaconda是一个用于科学计算的Python发行版，支持Linux,Mac，Windows系统，提供包管理和环境管理的功能。可以方便地解决多版本python共存，切换以及各种第三方包安装问题。Anaconda利用工具/命令conda来进行package和environment管理，同时也包含了Python相关的配套工具。
     ```

   * conda工具说明

     ```markdown
     conda可以理解为一个工具，也是一个可以执行命令，其核心功能就是包管理（与pip使用类似）和环境管理（安装不同版本python并可以快速切换）。
     conda设计理念：conda将几乎所有的工具，第三方包都当做package对待，甚至包括python和conda自身。
     ```

2. liunx和windows下安装

   * [website]: https://www.anaconda.com/download/	"download_website"


   * windows下安装

     * 选择windows对应版本下载

       ```markdown
       name:
       	Anaconda2-5.0.1-Windows-x86_64.exe
       存放路径：
       	D:/install/anaconda
       ```

     * 安装

       ```markdown
       按照提示命令一直next就行，注意在安装的过程中勾选添加环境变量的操作。
       ```

     * 手动添加环境变量介绍

       ```markdown
       将C:\Users\Think\Anaconda2\Scripts添加到环境变量中。
       备注：
       	用户Think和Anaconda2版本根据实际情况而定。
       ```

     * 验证安装成功

       ```markdown
       cmd:
       	conda --version 命令执行成功
       ```

   * linux下安装

     * 选择linux对应版本下载

       ```markdown
       name:
       	Anaconda2-5.0.1-Linux-x86_64.sh
       存放路径：
       	D:/install/anaconda
       ```

     * 安装

       ```markdown
       a): 安装过程中错误信息： bunzip2: command not found。
       	yum install -y bzip2
       b): 按照提示信息进行回车或yes操作。
       ```

     * 手动添加环境变量介绍

       ```markdown
       # 将anaconda的bin目录加入PATH，根据版本不同，也可能是~/anaconda3/bin
       echo 'export PATH="~/anaconda2/bin:$PATH"' >> ~/.bashrc
       # 更新bashrc以立即生效
       source ~/.bashrc
       ```

     * 验证安装OK

       ```markdown
       a): which conda或者 conda --version
       b): 如果安装的是Python 2.7对应的版本,运行python --version
       	[root@e3654295db52 ~]# python --version
       	Python 2.7.14 :: Anaconda custom (64-bit)
       ```

   * 设置国内源

     ```markdown
     a): 添加Anaconda的TUMA镜像
     	conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
     b): 设置搜索时显示通道地址
     	conda config --set show_channel_urls yes
     备注：
     	执行完上述命令后，会生成~/.condarc文件。
     ```

3. 常用命令操作

   * Conda的环境管理(同时安装若干不同版本的python)

     ```markdown
     # 创建一个名为python34的环境，指定python版本是3.4（不用管3.4.x，conda会为我们自动寻找3.4.x中最新版本）
     	conda create --name python34 python=3.4
     # 激活环境
     	activate python34 # for windows
     	source activate python34 # for Linux & Mac
     # 此时再次输入
     python --version
     # 可以得到`Python 3.4.5 :: Anaconda 4.1.1 (64-bit)`，即系统已经切换到了3.4的环境

     # 退出虚拟环境
     	deactivate python34 # for windows
     	source deactivate python34 #for Linux & Mac
     # 删除一个已有的环境
     	conda remove --name python34 --all
     ```

     **备注**

     ```markdown
     a): 不同版本的python环境存放目录
     	~/anaconda/envs
     b): 查看当前那些环境被激活: conda info -e
     	 C:\Users\Think\Anaconda2\envs> conda info -e
     	python35                 C:\Users\Think\Anaconda2\envs\python35
     	root                  *  C:\Users\Think\Anaconda2
     ```

   * Conda的包管理

     ```markdown
     # 查看当前环境下已安装的包
     	conda list
     # 查看指定环境下已安装的包
     	conda list -n python34
     # 当前环境下安装包
     	conda install numpy
     # 指定环境下安装包
     	conda install -n python34 numpy
     # 更新package
     	conda update -n python34 numpy
     # 删除package
     	conda remove -n python34 numpy
     ```

     **conda将conda，python等都视为package，故可以通过conda来操作**

     ```markdown
     # 更新conda，保持conda最新
     	conda update conda
     # 更新anaconda
     	conda update anaconda
     # 更新python
     	conda update python
     ```

     **创建新环境时包含anaconda包**

     ```markdown
     # 当前环境下安装anaconda包集合
     	conda install anaconda
     # 在创建环境时就安装
     	conda create -n python34 python=3.4 anaconda
     ```

### pip更换国内源

1. 常用的国内源。

   ```markdown
   豆瓣：
   	http://pypi.douban.com/simple/
   清华：（推荐）
   	https://pypi.tuna.tsinghua.edu.cn/simple
   ```

2. 临时使用。

   ```markdown
   安装时添加指定源地址
   命令：
   	pip install -i http://pypi.douban.com/simple/ gevent
   ```

3. 永久修改，一劳永逸。

   * windows

     在用户个人目录下：C:\Users\Think\pip下的pip.ini，修改源地址

     ```markdown
     [global]
     index-url = https://pypi.tuna.tsinghua.edu.cn/simple
     ```

   * linux

     修改 ~/.pip/pip.conf (没有就创建一个)， 修改 index-url至tuna，内容如下：

     ```markdown
     [global]
     index-url = https://pypi.tuna.tsinghua.edu.cn/simple
     ```

     ​