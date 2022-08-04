@echo off
@title CMS079
Color 1c
set CLASSPATH=.;lib\*;

java -XX:-UseGCOverheadLimit -Xms512m -Xmx1024m -Xss128k -Dnet.sf.odinms.wzpath=wz gui.Qhms
pause