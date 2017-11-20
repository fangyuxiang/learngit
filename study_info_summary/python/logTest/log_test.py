#!/usr/local/bin/ env python
# -*- coding: utf-8 -*-

import logging
import time
from logging.handlers import TimedRotatingFileHandler
from logging.handlers import RotatingFileHandler
import logging.config
import re
import yaml


def log_to_file():
    # log日志追加写入
    logging.basicConfig(filename='example.log', level=logging.DEBUG)
    # log日志覆盖写入
    # logging.basicConfig(filename='example.log', filemode='w', level=logging.DEBUG)
    logging.debug('This is debug messages!')
    logging.info("This is info messages!")
    logging.warning("This is  warnging message!")


def console_log():
    # create Logger
    logger = logging.getLogger('console_example')
    logger.setLevel(logging.DEBUG)

    # create console handler and set level to debug
    ch = logging.StreamHandler()
    ch.setLevel(logging.INFO)

    # create Formatter
    fmt = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

    # add fmt to ch
    ch.setFormatter(fmt)

    # add ch to logger
    logger.addHandler(ch)

    # application code
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")


def file_hander():
    logger = logging.getLogger('file_example')
    logger.setLevel(logging.DEBUG)

    # create file hander
    file = logging.FileHandler('file_handler.log', encoding='utf8')
    file.setLevel(logging.INFO)

    # create formatter
    fmt = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

    # add fmt to file hander
    file.setFormatter(fmt)

    # add hander to logger
    logger.addHandler(file)

    # application code
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")


#参考网址：http://www.jb51.net/article/114316.htm
# 配置文件：config.conf
def config_test():
    logging.config.fileConfig("logging.conf")

    # create logger
    logger = logging.getLogger('configExample')

    # application code
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")

# 配置文件：yaml格式
def yaml_config():
    with open('logging.yaml', 'r') as f_conf:
        dict_conf = yaml.load(f_conf)
    logging.config.dictConfig(dict_conf)

    logger = logging.getLogger('simpleExample')
    logger.debug("debug message")
    logger.info('info message')
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")



# 日志回滚：根据时间进行回滚
def timeRotatingTest():
    logger = logging.getLogger('timeRotatingTest')
    logger.setLevel(logging.DEBUG)

    # create file hander
    log_file_handler = TimedRotatingFileHandler('timeRotat.log', when='S', interval=10, backupCount=2, encoding='utf8')
    ### 指定日志的后缀名
    log_file_handler.suffix = "%Y-%m-%d_%H-%M-%S.log"
    ### 用于匹配日志的后缀文件名
    log_file_handler.extMatch = re.compile(r"^\d{4}-\d{2}-\d{2}_\d{2}-\d{2}-\d{2}.log$")
    log_file_handler.setLevel(logging.INFO)

    # create formatter
    fmt = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')

    # add fmt to file hander
    log_file_handler.setFormatter(fmt)

    # add hander to logger
    logger.addHandler(log_file_handler)

    # application code
    log_contet = 'test_log'
    count = 0
    while count < 60:
        print "count:%d" % (count)
        logger.error(log_contet)
        time.sleep(1)
        count += 1
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")


# 日志回滚：根据文件大小进行回滚
def rotatingFileTest():
    logger = logging.getLogger()
    logger.setLevel(logging.DEBUG)

    # create rotatingFileHander:
    # 如果backupcount=0； 代表所有的log都写在rotating.log上
    # 如果backupcount > 0: 代表在rotating.log的大小大于maxBytes时，写入下一个文件：rotating.log.1
    # 1M = 1024 * 1kb(1kb = 1000 kb) ==> 1024 * 1000
    fileHander = RotatingFileHandler('rotating.log', maxBytes=1000 * 1024, backupCount=2)
    fileHander.setLevel(logging.DEBUG)

    # create formatter
    fmt = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
    fileHander.setFormatter(fmt)

    # add handler to logger
    logger.addHandler(fileHander)

    # write log for cycle
    log_content_info = 'this is debug log'
    log_content_error = 'this is error log'

    count = 1
    while count < 10000:
        print "count:%s" % count
        if count % 2 == 0:
            logger.debug(log_content_info)
        else:
            logger.error(log_content_error)
        count += 1
    logger.debug('debug message')
    logger.info("info message")
    logger.warn("warn message")
    logger.error("error message")
    logger.critical("critical message")


if __name__ == "__main__":
    # log_to_file()
    # console_log()
    # file_hander()
    # config_test()
    yaml_config()
    # timeRotatingTest()
    # rotatingFileTest()
