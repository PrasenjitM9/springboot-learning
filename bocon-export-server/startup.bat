@echo off
if /i "%PROCESSOR_IDENTIFIER:~0,3%" == "X86" goto 1 //32位
if /i "%PROCESSOR_IDENTIFIER:~0,3%" NEQ "X86" goto 2  //64位
:1  
jre7_32\bin\java -jar bocon-export-server.jar --spring.profiles.active=prod --server.port=80 --bocon.port=7005

:2  
jre7_64\bin\java -jar bocon-export-server.jar --spring.profiles.active=prod --server.port=80 --bocon.port=7005