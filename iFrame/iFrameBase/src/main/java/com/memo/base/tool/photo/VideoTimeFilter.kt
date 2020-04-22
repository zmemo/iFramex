package com.memo.base.tool.photo

import android.content.Context
import com.memo.base.R
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.filter.Filter
import com.zhihu.matisse.internal.entity.IncapableCause
import com.zhihu.matisse.internal.entity.Item
import java.util.*

/**
 * title:视频长度筛选
 * describe:默认15秒
 *
 * @author zhou
 * @date 2019-09-26 17:40
 */
class VideoTimeFilter(private val timeSecond: Int = 15) : Filter() {

    public override fun constraintTypes(): Set<MimeType> {
        return object : HashSet<MimeType>() {
            init {
                addAll(MimeType.ofVideo())
            }
        }
    }

    override fun filter(context: Context, item: Item): IncapableCause? {
        if (!needFiltering(context, item))
            return null
        return if (item.duration > timeSecond * 1000) {
            IncapableCause(
                IncapableCause.DIALOG, context.getString(R.string.error_video, timeSecond)
            )
        } else null
    }
}
