package com.memo.test.ui.recyclerview.grid

import com.memo.test.ui.recyclerview.MultiEntity
import com.memo.test.ui.recyclerview.grid.provider.*
import com.memo.tool.adapter.recyclerview.BaseMultiAdapter
import com.memo.tool.adapter.recyclerview.BaseMultiProvider

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-09-02 14:42
 */
class GridMultiAdapter : BaseMultiAdapter<MultiEntity>() {
    /**
     * 返回MultiProvider集合
     */
    override fun bindMultiType(): List<BaseMultiProvider<MultiEntity>> {
        return arrayListOf(
            //轮播图模式
            BannerProvider(),
            //标题模式
            TitleProvider(),
            //歌单模式
            ListProvider(),
            //海报模式
            PosterProvider(),
            //歌曲模式 标题右侧
            SongRightProvider(),
            //歌曲模式 标题上侧
            SongTopProvider()
        )
    }
}