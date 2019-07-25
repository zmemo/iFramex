package com.memo.widget.recyclerview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import static android.util.TypedValue.applyDimension;

/**
 * title:RecyclerView分割线
 * describe:
 * <p>
 * RecyclerViewDivider divider = new RecyclerViewDivider.Builder(this)
 * .setStyle(RecyclerViewDivider.Style.BETWEEN)
 * .setDrawableRes(R.drawable-xhdpi.divider)
 * //.setColorRes(R.color.divider_gray)
 * .setHeight(1.5f)
 * .setMarginLeft(72)
 * .setMarginRight(8)
 * .build();
 * mRvList.addItemDecoration(divider);
 *
 * @author zhou
 * @date 2019-05-15 15:21
 */
public class RecyclerViewDivider extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;

    public static final int VERTICAL = LinearLayout.VERTICAL;
    private final Rect mBounds = new Rect();
    /**
     * The divider drawable-xhdpi
     */
    private Drawable mDivider;
    private int mOrientation;
    private Builder mBuilder;

    private RecyclerViewDivider(Builder builder) {
        mBuilder = builder;
        setOrientation();
        setDividerDrawable();
    }

    /**
     * Set Divider Drawable
     */
    private void setDividerDrawable() {
        Drawable drawable;
        if (mBuilder.mDrawable != null) {
            drawable = mBuilder.mDrawable;
        } else {
            drawable = new DividerDrawable(mBuilder.mColor);
        }
        mDivider = drawable;
    }

    /**
     * Set Divider Orientation
     */
    private void setOrientation() {
        int orientation = mBuilder.mOrientation;
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int count = parent.getAdapter().getItemCount();
        if (mOrientation == VERTICAL) {
            int height = mDivider.getIntrinsicHeight();
            if (position == 0 && mBuilder.mShowTopDivider) {
                outRect.set(0, height, 0, height);
            } else if (!needSkip(position, count)) {
                outRect.set(0, 0, 0, height);
            }
        } else {
            int width = mDivider.getIntrinsicWidth();
            if (position == 0 && mBuilder.mShowTopDivider) {
                outRect.set(width, 0, width, 0);
            } else if (!needSkip(position, count)) {
                outRect.set(0, 0, width, 0);
            }
        }
    }

    private boolean needSkip(int position, int count) {
        return position < mBuilder.mStartSkipCount || position >= count - mBuilder.mEndSkipCount;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * Draw vertical list divider
     *
     * @param canvas canvas
     * @param parent RecyclerView
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && parent.getClipToPadding()) {
            left = parent.getPaddingLeft() + mBuilder.mMarginLeft;
            right = parent.getWidth() - parent.getPaddingRight() - mBuilder.mMarginRight;
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = mBuilder.mMarginLeft;
            right = parent.getWidth() - mBuilder.mMarginRight;
        }

        int childCount = parent.getChildCount();
        int top;
        int bottom;
        int count = parent.getAdapter().getItemCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            if (needSkip(position, count)) {
                continue;
            }
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            bottom = mBounds.bottom + Math.round(child.getTranslationY());
            top = bottom - mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }

        if (childCount > 0 && mBuilder.mShowTopDivider) {
            final View child = parent.getChildAt(0);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            top = mBounds.top + Math.round(child.getTranslationY());
            bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    /**
     * Draw horizontal list divider
     *
     * @param canvas canvas
     * @param parent RecyclerView
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && parent.getClipToPadding()) {
            top = parent.getPaddingTop() + mBuilder.mMarginTop;
            bottom = parent.getHeight() - parent.getPaddingBottom() - mBuilder.mMarginBottom;
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = mBuilder.mMarginTop;
            bottom = parent.getHeight() - mBuilder.mMarginBottom;
        }

        int childCount = parent.getChildCount();
        int left;
        int right;
        int count = parent.getAdapter().getItemCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            if (needSkip(position, count)) {
                continue;
            }
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            right = mBounds.right + Math.round(child.getTranslationX());
            left = right - mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        if (childCount > 0 && mBuilder.mShowTopDivider) {
            final View child = parent.getChildAt(0);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            left = mBounds.left + Math.round(child.getTranslationX());
            right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    /**
     * Divider Style
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Style.BOTH, Style.START, Style.END, Style.BETWEEN})
    public @interface Style {
        int END = 0;
        int START = 1;
        int BOTH = 2;
        int BETWEEN = 3;
    }

    /**
     * RecyclerView Divider Builder
     */
    public static class Builder {
        private Context mContext;
        private Drawable mDrawable;
        private int mOrientation = VERTICAL;
        private int mHeight = 1;
        private int mMarginLeft = 0;
        private int mMarginRight = 0;
        private int mMarginTop = 0;
        private int mMarginBottom = 0;
        private int mColor = 0xFFD1D1D1;
        private int mStartSkipCount = 0;
        private int mEndSkipCount = 0;
        private @Style int mStyle = Style.BETWEEN;

        private boolean mShowTopDivider;

        public Builder(Context context) {
            mContext = context;
        }

        /**
         * Set divider drawable-xhdpi
         * 设置分割线绘制Drawable
         *
         * @param drawable Divider drawable-xhdpi
         */
        public Builder setDrawable(Drawable drawable) {
            mDrawable = drawable;
            return this;
        }

        /**
         * Set divider drawable-xhdpi resource
         * 设置分割线绘制Drawable
         *
         * @param drawableRes Divider drawable-xhdpi resource
         */
        public Builder setDrawableRes(int drawableRes) {
            mDrawable = ContextCompat.getDrawable(mContext, drawableRes);
            return this;
        }

        /**
         * Set divider style
         * 设置分割线绘制类型
         *
         * @param style divider style
         * @see Style
         */
        public Builder setStyle(@Style int style) {
            mStyle = style;
            return this;
        }

        /**
         * Set divider orientation
         * 设置分割线方向 默认Vertical
         *
         * @param orientation divider orientation
         */
        public Builder setOrientation(int orientation) {
            mOrientation = orientation;
            return this;
        }

        /**
         * Set divider size
         * 设置分割线高度
         *
         * @param size divider size
         */
        public Builder setHeight(float size) {
            return setHeight(TypedValue.COMPLEX_UNIT_DIP, size);
        }

        /**
         * Set divider height
         * 设置分割线的高度
         *
         * @param unit   divider height unit
         * @param height divider height
         */
        public Builder setHeight(int unit, float height) {
            mHeight = getSizeValue(unit, height);
            return this;
        }

        /**
         * Set divider margin left
         * 设置距离左部的距离
         *
         * @param marginLeft margin left value
         */
        public Builder setMarginLeft(float marginLeft) {
            return setMarginLeft(TypedValue.COMPLEX_UNIT_DIP, marginLeft);
        }

        /**
         * Set divider margin left
         * 设置距离左部的距离
         *
         * @param unit       margin left value unit
         * @param marginLeft margin left value
         */
        public Builder setMarginLeft(int unit, float marginLeft) {
            mMarginLeft = getSizeValue(unit, marginLeft);
            return this;
        }

        /**
         * Set divider margin right
         * 设置距离右部的距离
         *
         * @param marginRight margin right value
         */
        public Builder setMarginRight(float marginRight) {
            return setMarginRight(TypedValue.COMPLEX_UNIT_DIP, marginRight);
        }

        /**
         * Set divider margin right
         * 设置距离右部的距离
         *
         * @param unit        margin right value unit
         * @param marginRight margin right value
         */
        public Builder setMarginRight(int unit, float marginRight) {
            mMarginRight = getSizeValue(unit, marginRight);
            return this;
        }

        /**
         * Set divider margin top
         * 设置距离顶部的距离
         *
         * @param marginTop margin top value
         */
        public Builder setMarginTop(int marginTop) {
            return setMarginTop(TypedValue.COMPLEX_UNIT_DIP, marginTop);
        }

        /**
         * Set divider margin right
         * 设置距离顶部的距离
         *
         * @param unit      margin right value unit
         * @param marginTop margin top value
         */
        public Builder setMarginTop(int unit, int marginTop) {
            mMarginTop = getSizeValue(unit, marginTop);
            return this;
        }

        /**
         * Set divider margin bottom
         * 设置距离底部的距离
         *
         * @param marginBottom margin bottom value
         */
        public Builder setMarginBottom(float marginBottom) {
            return setMarginBottom(TypedValue.COMPLEX_UNIT_DIP, marginBottom);
        }

        /**
         * Set divider margin bottom
         * 设置距离底部的距离
         *
         * @param unit         margin bottom value unit
         * @param marginBottom margin bottom value
         */
        public Builder setMarginBottom(int unit, float marginBottom) {
            mMarginBottom = getSizeValue(unit, marginBottom);
            return this;
        }

        /**
         * Set divider color
         * 设置分割线颜色
         *
         * @param color divider color
         */
        public Builder setColor(@ColorInt int color) {
            mColor = color;
            return this;
        }

        /**
         * Set divider color
         * 设置分割线颜色
         *
         * @param colorRes divider color resource
         */
        public Builder setColorRes(@ColorRes int colorRes) {
            mColor = ContextCompat.getColor(mContext, colorRes);
            return this;
        }

        /**
         * Set skip count from start
         * 设置开始放弃绘制分割线的Item数量
         *
         * @param startSkipCount count from start
         */
        public Builder setStartSkipCount(int startSkipCount) {
            mStartSkipCount = startSkipCount;
            return this;
        }

        /**
         * Set skip count before end
         * 设置末尾放弃绘制分割线的Item数量
         *
         * @param endSkipCount count before end
         */
        public Builder setEndSkipCount(int endSkipCount) {
            mEndSkipCount = endSkipCount;
            return this;
        }

        private int getSizeValue(int unit, float size) {
            return (int) applyDimension(unit, size, mContext.getResources().getDisplayMetrics());
        }

        public RecyclerViewDivider build() {
            switch (mStyle) {
                case Style.BETWEEN:
                    mEndSkipCount++;
                    break;
                case Style.BOTH:
                    mStartSkipCount--;
                    break;
                case Style.END:
                    break;
                case Style.START:
                    mEndSkipCount++;
                    break;
                default:
                    break;
            }
            mShowTopDivider = (mStyle == Style.BOTH && mStartSkipCount < 0) || mStyle == Style.START;
            return new RecyclerViewDivider(this);
        }
    }

    /**
     * DividerDrawable
     */
    private class DividerDrawable extends ColorDrawable {
        DividerDrawable(int color) {
            super(color);
        }

        @Override
        public int getIntrinsicWidth() {
            return mBuilder.mHeight;
        }

        @Override
        public int getIntrinsicHeight() {
            return mBuilder.mHeight;
        }
    }
}

