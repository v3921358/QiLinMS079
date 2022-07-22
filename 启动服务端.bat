@echo off
@title Êý¾Ý¿âMysql1.7
Color 1c
set path=config\bin;%SystemRoot%\system32;%SystemRoot%;%SystemRoot%
set JRE_HOME=config
set JAVA_HOME=config
set CLASSPATH=.;config\*;

java -XX:-UseGCOverheadLimit -Xms512m -Xmx1024m -Xss128k -Dnet.sf.odinms.wzpath=wz gui.QILIN
pause