#### 项目结构

版本： 
androidx    
minSdkVersion：19    
targetSdkVersion：28    
compileSdkVersion：28    
语言：Kotlin   
模式：MVP    

##### buildSrc
该模块主要是放置第三方依赖包，以及一些配置文件   
缺点是在AS3.3和AS3.4中无法提示，需要手动复制  
在修改完之后需要手动进行同步
***Config*** App的一些基本配置
***DepConfig*** 依赖包的基本配置
***ModuleConfig*** 模块化划分的基础配置

#### iFrameBasic
该模块放置使用api导入第三方依赖包，jar，so，aar等等  
放置最小宽度适配工具类，创建基础dimen文件  
不允许在该模块中添加其他业务逻辑代码  
在其他项目中使用的时候可以直接导入  

#### iFrameTool
该模块为基础工具类模块  
放置项目中通用的工具类  
不允许在该模块中添加其他业务逻辑代码  
在其他项目中使用的时候可以直接导入  

#### iFrameWidget
该模块为自定义控件模块  
放置项目中需要的自定义控件  
不允许在该模块中添加其他业务逻辑代码  
在其他项目中使用的时候可以直接导入 

#### iFramePay
该模块为支付模块  
直接调用工具类进行支付宝和微信支付

#### iFrameMap
该模块为地图模块  
集成基础的地图工具 

#### iFrameUMeng
该模块为友盟模块  
内容目前包含分享的登陆   
如果需要需要使用其他，在自行添加

#### iFrameProguard
该模块为混淆模块，对于添加的依赖需要混淆的就添加一个混淆文件

#### iFrameBase
该模块为项目中的基础模块  
添加iFrameTool，iFrameWidget，iFrameMap，iFramePay，iFrameUMeng，iFrameProguar  
该模块在项目中处于一个承上启下的作用，每一个业务模块都会依赖iFrameBase  
在内部添加一些业务逻辑，比如DbManager，RetrofitClient等  
***需要根据项目需求进行更改*** 

#### iFrameUi
该模块为项目中的独立界面模块，implementation依赖iFrameBase  
内部存放一些通用界面，比如二维码扫描，网页界面等一些不需要进行业务逻辑的界面  
通过RouterManager进行启动  

#### ModuleTest
一些示例代码都放在ModuleTest中

给自己的提示：需要使用的时候把项目拉下来，看下是如何配置的，因为会经常修改，但有些时候会忘了记笔记，一些常见的错误提示都写在笔记里了注意看

1.混淆模块中，添加一个第三方依赖，需要添加一份混淆文件，具体格式统一，对于业务逻辑模块中的实体类，在App模块的混淆文件中添加 ***keep*** 具体包名  
2.iFrameUMeng模块中需要对使用到第三方进行注册