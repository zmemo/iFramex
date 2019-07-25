#### 项目结构

版本： 
androidx   
minSdkVersion 19  
targetSdkVersion：27   
compileSdkVersion：28   
语言：Kotlin   
模式：MVP  

##### buildSrc
该模块主要是放置第三方依赖包，以及一些配置文件   
使用config.gradle的时候，如果需要更改都需要重新同步一次  
缺点是在AS3.3和AS3.4中无法提示，需要手动复制  

#### iFrameBasic
该模块放置使用api导入第三方依赖包，jar，so，aar等等  
放置最小宽度适配工具类，创建基础dimen文件  
不允许在该模块中添加其他业务逻辑代码  
在其他项目中使用的时候可以直接导入  

#### iFrameTool
该模块为工具类模块，api依赖iFrameBasic  
放置项目中通用的工具类  
不允许在该模块中添加其他业务逻辑代码  
在其他项目中使用的时候可以直接导入  

#### iFrameWidget
该模块为自定义控件模块，api依赖iFrameTool  
放置项目中需要的自定义控件  
不允许在该模块中添加其他业务逻辑代码  
在其他项目中使用的时候可以直接导入  

#### iFrameBase
该模块为项目中的基础模块，api依赖iFrameWidget  
该模块在项目中处于一个承上启下的作用，每一个业务模块都会依赖iFrameBase  
可以在内部添加一些业务逻辑，比如DbManager，RrtrofitClient等  
可以更具项目需求进行更改  

#### iFrameUi
该模块为项目中的独立界面模块，implementation依赖iFrameBase  
内部存放一些通用界面，比如二维码扫描，网页界面等一些不需要进行业务逻辑的界面  
通过RouterManager进行启动  