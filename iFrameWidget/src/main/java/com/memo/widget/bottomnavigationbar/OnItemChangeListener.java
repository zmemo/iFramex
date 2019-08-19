package com.memo.widget.bottomnavigationbar;

import android.view.MenuItem;

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-19 17:27
 */
public interface OnItemChangeListener {
    /**
     * 底部导航条目改变
     *
     * @param menuItem 当前条目
     * @param position 改变的下标
     */
    void onItemChange(MenuItem menuItem, int position);
}
