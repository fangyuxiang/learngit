# Charles使用和问题汇总

# 问题汇总

## ios解决https抓包显示unkown

1. 所有设置和配置都是ok

   * 手机端设置代理

     ```markdown
     a:手机端连接网络，确保与charles的网络连接相同。
     b:查看charles所在电脑的ipaddress
     	help -> Local IP Addres
     c:设置手机端端代理：
     	ip: local ip address
     	port: 8888
     ```

   * 手机端安装SLL证书：

     ```mark
     打开手机浏览器输入：chls.pro/ssl
     ```

     ![client端证书安装](E:\git_save_study\study_info_summary\charles\capture_screen\client端证书安装.png)

2. 错误信息

   ```markdown
   charles抓包显示unkown，查看错误信息：You may need to configure your browser or application to trust the Charles Root Certificate. See SSL Proxying in the Help menu.
   ```

   ![IOS证书未添加信任时的错误信息](E:\git_save_study\study_info_summary\charles\capture_screen\IOS证书未添加信任时的错误信息.png)

3. 解决方法

   ```markdown
   原因：
   	iOS 10.3之后,安装新的自定义证书默认是不受信任的。如果要信任已安装的自定义证书,需要手动打开开关以信任证书。
   解决方法：
   	设置->通用->关于本机->证书信任设置-> 找到charles proxy custom root certificate然后信任该证书即可
   ```

   ![ios设置信任证书](E:\git_save_study\study_info_summary\charles\capture_screen\ios设置信任证书.png)

