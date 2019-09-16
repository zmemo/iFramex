# https://github.com/Blankj/AndroidUtilCode/blob/androidx/lib/utilcode/proguard-rules.pro
-dontwarn com.blankj.utilcode.**

-keepclassmembers class * {
    @com.blankj.utilcode.util.BusUtils$Bus <methods>;
}

-keepattributes *Annotation*