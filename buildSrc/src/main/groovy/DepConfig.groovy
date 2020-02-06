class DepConfig {

    static gradleVersion = "3.5.2"
    static kotlinVersion = "1.3.61"
    static retrofitVersion = "2.7.1"
    static glideVersion = "4.11.0"
    static autoDisposeVersion = "1.4.0"
    static doKitVersion = "2.2.1"

    // Gradle
    static gradle = "com.android.tools.build:gradle:$gradleVersion"
    static gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    static gradleKotlinAndroid = "org.jetbrains.kotlin:kotlin-android-extensions:$kotlinVersion"
    static gradleArouter = "com.alibaba:arouter-register:1.0.2"
    static aspectjx = "com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.4"

    // Test
    static junit = "junit:junit:4.12"
    static testJunit = "androidx.test.ext:junit:1.1.1"
    static testEspresso = "androidx.test.espresso:espresso-core:3.2.0"

    // 控件
    static appcompat = "androidx.appcompat:appcompat:1.1.0"
    static constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    static recyclerView = "androidx.recyclerview:recyclerview:1.0.0"
    static cardView = "androidx.cardview:cardview:1.0.0"
    static basePopupCore = "com.github.razerdp:BasePopup:2.2.1"
    static basePopupAndroidx = "com.github.razerdp:BasePopup-compat-androidx:2.2.1"
    static rWidgetHelper = "com.ruffian.library:RWidgetHelper:1.1.8"
    static baseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.47-androidx"
    static matisse = "com.zhihu.android:matisse:0.5.3-beta3"
    static uCrop = "com.github.yalantis:ucrop:2.2.3"
    static pickerView = "com.contrarywind:Android-PickerView:4.1.8"
    static qrCode = "cn.bingoogolapple:bga-qrcode-zxing:1.3.6"
    static loadSir = "com.kingja.loadsir:loadsir:1.3.6"
    static agentWeb = "com.just.agentweb:agentweb:4.0.3-beta"
    static smartRefreshLayout = "com.scwang.smartrefresh:SmartRefreshLayout:1.1.0"
    static tabLayout = "com.flyco.tablayout:FlycoTabLayout_Lib:2.1.2@aar"
    static androidSwipeLayout = "com.daimajia.swipelayout:library:1.2.0@aar"
    static flexBoxLayout = "com.google.android:flexbox:1.1.1"

    // 工具
    static kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    static coreKtx = "androidx.core:core-ktx:1.0.2"
    static multiDex = "androidx.multidex:multidex:2.0.0"
    static androidUtilCode = "com.blankj:utilcodex:1.26.0"
    static rxJava2 = "io.reactivex.rxjava2:rxjava:2.2.10"
    static rxAndroid2 = "io.reactivex.rxjava2:rxandroid:2.1.1"
    static rxBus = "com.blankj:rxbus:1.6"
    static retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    static retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    static retrofitConverter = "com.squareup.retrofit2:converter-scalars:$retrofitVersion"
    static retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    static retrofitUrlManager = "me.jessyan:retrofit-url-manager:1.4.0"
    static autoDispose = "com.uber.autodispose:autodispose-android:$autoDisposeVersion"
    static autoDisposeArch = "com.uber.autodispose:autodispose-android-archcomponents:$autoDisposeVersion"
    static glide = "com.github.bumptech.glide:glide:$glideVersion"
    static glideOkHttp = "com.github.bumptech.glide:okhttp3-integration:$glideVersion"
    static arouter = "com.alibaba:arouter-api:1.5.0"
    static mmkv = "com.tencent:mmkv:1.0.22"
    static preview = "com.ycjiang:ImagePreview:2.3.4"
    static stickyHeader = "com.oushangfeng:PinnedSectionItemDecoration:1.3.2"
    static luBan = "top.zibin:Luban:1.1.8"
    static lottie = "com.airbnb.android:lottie:3.1.0"
    static banner = "com.youth.banner:banner:1.4.10"

    // 插件
    static doKitRelease = "com.didichuxing.doraemonkit:doraemonkit-no-op:$doKitVersion"
    static doKitDebug = "com.didichuxing.doraemonkit:doraemonkit:$doKitVersion"
    static leakCanaryDebug = "com.squareup.leakcanary:leakcanary-android:2.0-beta-3"

    // Compiler
    static glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
    static arouterCompiler = "com.alibaba:arouter-compiler:1.2.2"
}