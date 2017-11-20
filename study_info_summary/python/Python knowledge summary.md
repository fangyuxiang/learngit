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

    ​

