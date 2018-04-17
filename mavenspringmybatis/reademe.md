#导出依赖包
> 必须配置maven 环境变量

>切换到项目pom.xml 所在的目录，执行mvn dependency:copy-dependencies 命令：

导出到targed/dependency，输入以下命令：
```
mavenspringmybatis> mvn dependency:copy-dependencies
```

在工程创建lib文件夹，输入以下命令：
```
mavenspringmybatis> mvn dependency:copy-dependencies -DoutputDirectory=dependencies  
```

设置依赖级别，通常用编译需要的jar，dependency 中默认级别为 compile（编译需要），如：只导出scop 为 compile 的包
```
mavenspringmybatis> mvn dependency:copy-dependencies -DoutputDirectory=dependencies   -DincludeScope=compile  
```
