# https://github.com/zhpanvip/BannerViewPager/blob/master/app/proguard-rules.pro
-keep class * extends androidx.fragment.app.Fragment {
    public void setUserVisibleHint(boolean);
    public void onHiddenChanged(boolean);
    public void onResume();
    public void onPause();
}
-keep public class * extends android.app.Activity
-keep class com.example.zhpan.circleviewpager.bean.** { *; }
-keep class com.example.zhpan.circleviewpager.net.** { *; }
-keep class com.zhpan.idea.** { *; }