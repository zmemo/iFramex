package com.memo.tool.helper

import android.animation.Animator
import android.graphics.Camera
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.*
import androidx.annotation.RequiresApi
import kotlin.math.hypot

/**
 * title:动画
 * describe:
 *
 * @author zhou
 * @date 2019-06-24 11:54
 */
object AnimHelper {

    /**
     * 中心圆形扩散显示
     * @param view View 控件
     * @param duration Long 时长
     * @return Animator 动画
     */
    @JvmStatic
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun showCircleRevealAnim(view: View, duration: Long = 1500): Animator {
        val centerX = view.width / 2
        val centerY = view.height / 2
        val endRadius = hypot(centerY.toDouble(), centerX.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, endRadius)
        anim.duration = duration
        anim.start()
        return anim
    }

    /**
     * 四周圆形扩散隐藏
     * @param view View 控件
     * @param duration Long 时长
     * @return Animator 动画
     */
    @JvmStatic
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun hideCircleRevealAnim(view: View, duration: Long = 1500): Animator {
        val centerX = view.width / 2
        val centerY = view.height / 2
        val endRadius = hypot(centerY.toDouble(), centerX.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, endRadius, 0f)
        anim.duration = duration
        anim.start()
        return anim
    }

    /**
     * 创建一个渐变动画
     * @param from Float 开始
     * @param to Float 结束
     * @param duration Long 时长
     * @return AlphaAnimation 渐变动画
     */
    @JvmStatic
    fun createAlphaAnim(from: Float, to: Float, duration: Long): AlphaAnimation {
        val anim = AlphaAnimation(from, to)
        anim.duration = duration
        return anim
    }

    /**
     * 圆心翻转动画
     * @param duration Long 时长
     * @param repeatCount Int 转圈次数
     * @return RotateAnimation 动画
     */
    @JvmStatic
    fun createRotateAnim(duration: Long, repeatCount: Int = -1): RotateAnimation {
        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.interpolator = LinearInterpolator()
        rotate.duration = duration
        rotate.repeatCount = repeatCount
        rotate.fillAfter = true
        return rotate
    }

    /**
     * 缩放动画
     * @param from Float 开始
     * @param to Float 结束
     * @param duration Long 时长
     * @return ScaleAnimation 动画
     */
    fun createScaleAnim(from: Float, to: Float, duration: Long): ScaleAnimation {
        val anim = ScaleAnimation(
            from, to, from, to,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = duration
        return anim
    }

    /**
     * 上到原点 -1 0
     * 原点到下 0 -1
     * 下到原点 1 0
     * 原点到上 0 1
     */
    @JvmStatic
    fun createVTranslateAnim(fromY: Float, toY: Float, duration: Long): Animation {
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            fromY,
            Animation.RELATIVE_TO_SELF,
            toY)
        animation.duration = duration
        animation.interpolator = DecelerateInterpolator()
        return animation
    }

    /**
     * 左到原点 -1 0
     * 原点到左 0 -1
     * 右到原点 1 0
     * 原点到右 0 1
     */
    @JvmStatic
    fun createHTranslateAnim(fromX: Float, toX: Float, duration: Long): Animation {
        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            fromX,
            Animation.RELATIVE_TO_SELF,
            toX,
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            0f)
        animation.duration = duration
        animation.interpolator = DecelerateInterpolator()

        return animation
    }

    /**
     * 展示3D翻转动画
     * @param container View 两个展示界面的外部包裹容器
     * @param self View 第一个展示界面
     * @param other View 第二个展示界面
     * @param depthZ Float 景深
     * @param duration Long 时长
     * @return Rotate3dAnimation
     */
    @JvmStatic
    fun createRotate3dAnimGo(container: View, self: View, other: View, depthZ: Float, duration: Long): Rotate3dAnimation {
        val mCenterX = (self.width / 2).toFloat()
        val mCenterY = (self.height / 2).toFloat()
        val openAnimation = Rotate3dAnimation(0f, 90f, mCenterX, mCenterY, depthZ, true)
        openAnimation.duration = duration
        openAnimation.fillAfter = true
        openAnimation.interpolator = AccelerateInterpolator()
        openAnimation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                other.visibility = View.GONE
                self.visibility = View.VISIBLE
                val rotateAnimation = Rotate3dAnimation(270f, 360f, mCenterX, mCenterY, depthZ, false)
                rotateAnimation.duration = duration
                rotateAnimation.fillAfter = true
                rotateAnimation.interpolator = DecelerateInterpolator()
                container.startAnimation(rotateAnimation)
            }
        })
        return openAnimation
    }

    /**
     * 展示3D翻转动画
     * @param container View 两个展示界面的外部包裹容器
     * @param self View 第一个展示界面
     * @param other View 第二个展示界面
     * @param depthZ Float 景深
     * @param duration Long 时长
     * @return Rotate3dAnimation
     */
    @JvmStatic
    fun createRotate3dAnimBack(container: View, self: View, other: View, depthZ: Float, duration: Long): Rotate3dAnimation {
        val mCenterX = (self.width / 2).toFloat()
        val mCenterY = (self.height / 2).toFloat()
        val closeAnimation = Rotate3dAnimation(360f, 270f, mCenterX, mCenterY, depthZ, true)
        closeAnimation.duration = duration
        closeAnimation.fillAfter = true
        closeAnimation.interpolator = AccelerateInterpolator()
        closeAnimation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                other.visibility = View.VISIBLE
                self.visibility = View.GONE
                val rotateAnimation = Rotate3dAnimation(90f, 0f, mCenterX, mCenterY, depthZ, false)
                rotateAnimation.duration = duration
                rotateAnimation.fillAfter = true
                rotateAnimation.interpolator = DecelerateInterpolator()
                container.startAnimation(rotateAnimation)
            }
        })
        return closeAnimation
    }

    /**
     * 3D翻转动画
     * @param mFromDegrees Float 开始角度
     * @param mToDegrees Float 结束角度
     * @param mCenterX Float x中轴线
     * @param mCenterY Float y中轴线
     * @param mDepthZ Float z轴移动距离 景深
     * @param mReverse Boolean 是否Reverse
     */
    class Rotate3dAnimation(
        private val mFromDegrees: Float,
        private val mToDegrees: Float,
        private val mCenterX: Float,
        private val mCenterY: Float,
        private val mDepthZ: Float,
        private val mReverse: Boolean
    ) : Animation() {

        private var mCamera: Camera = Camera()

        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            val fromDegrees = mFromDegrees
            val degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime

            val centerX = mCenterX
            val centerY = mCenterY
            val camera = mCamera

            val matrix = t.matrix

            camera.save()
            if (mReverse) {
                camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime)
            } else {
                camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime))
            }
            camera.rotateY(degrees)
            camera.getMatrix(matrix)
            camera.restore()

            matrix.preTranslate(-centerX, -centerY)
            matrix.postTranslate(centerX, centerY)
        }
    }
}