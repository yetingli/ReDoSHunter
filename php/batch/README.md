1：batch 是批量处理除java语言外的正则表达式；

2：check_data中的文件由test.onlyCheck()函数产生（只执行检测步骤，并将信息记录在txt文件中）；

3：result存储执行main.py脚本生成的ReDoS结果（确保你有这个文件夹）；

4：在执行main.py脚本前，需要现在check_data文件夹中放入由test.onlyCheck()函数产生txt文件；

#### warning:执行脚本前，注意替换里面的文件夹路径。包括check_data、result以及存放缓存文件的/Users/pqc/Desktop/redos/temp/目录。