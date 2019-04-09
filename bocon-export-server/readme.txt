1、双击startup.bat启动
2、接收数据
3、复制d盘根目录的文件

jre7_32文件夹：32位Java Runtime Environment Java运行环境 不能删除
jre7_64文件夹：64位Java Runtime Environment Java运行环境 不能删除

文件在d盘根目录下 每次重新启动小工具 会将下面的几个文件删除
rtd_data.csv 实时数据 
minute_data.csv 分钟数据 
hour_data.csv 小时数据 
day_data.csv 日数据 
month_data.csv 月数据(cn=2081)

通过http://localhost 可以查看接收的数据包
startup.bat配置详解
--server.port=80 代表web的端口 比如http://localhost:80
--bocon.port=12345 代表接收数据的端口 默认123456,可以修改

pollantinfo.txt 
011,COD 011代表污染因子编码 ,COD代表污染因子名称 折算值程序自动加入不需要添加01Z,烟尘折算