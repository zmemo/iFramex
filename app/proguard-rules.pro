# 指定外部模糊字典 proguard.txt 改为混淆文件名，下同
-obfuscationdictionary proguard.txt
# 指定class模糊字典
-classobfuscationdictionary proguard.txt
# 指定package模糊字典
-packageobfuscationdictionary proguard.txt


# 所有实体类的包名
-keep class com.memo.base.entity.** { *; }