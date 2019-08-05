package com.memo.test

import android.app.Activity
import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.manager.router.RouterManager
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.activity.BaseActivity
import com.memo.test.anim.AnimActivity
import com.memo.test.bus.BusSubscribeActivity
import com.memo.test.download.DownLoadActivity
import com.memo.test.map.MapActivity
import com.memo.test.matisse.MatisseSelectActivity
import com.memo.test.multirecyclerview.MultiRecyclerActivity
import com.memo.test.ninegridview.NineGridActivity
import com.memo.test.notification.NotificationActivity
import com.memo.test.retrofit.RetrofitActivity
import com.memo.test.share.ShareFromActivity
import com.memo.test.status.LoadSirActivity
import com.memo.test.sw.SmallestWidthActivity
import com.memo.tool.dialog.dialog.AlertDialog
import com.memo.tool.dialog.dialog.BottomGridDialog
import com.memo.tool.dialog.dialog.BottomListDialog
import com.memo.tool.dialog.dialog.LocateListDialog
import com.memo.tool.dialog.entity.Area
import com.memo.tool.ext.OnNotFastClickListener
import com.memo.tool.ext.onClick
import com.memo.tool.ext.startActivity
import com.memo.tool.utils.DialogHelper
import com.memo.tool.utils.ImageLoadHelper
import com.memo.tool.utils.QrcodeHelper
import com.memo.tool.utils.toast
import kotlinx.android.synthetic.main.activity_test.*

/**
 * title:测试界面
 * describe:测试
 *
 * @author zhou
 * @date 2019-07-25 17:13
 */
@Route(path = RouterPath.Test.TestActivity)
class TestActivity : BaseActivity() {

    private val REQUEST_CODE_QRCODE = 1

    private var area: Area? = null

    private var index = 0

    private val mAlertDialog: AlertDialog by lazy {
        AlertDialog(mContext, message = "这是一个提示")
            .setOnTipClickListener({
                toast("点击确定")
            }, {
                toast("点击取消")
            })
    }

    private val mBottomListDialog: BottomListDialog by lazy {
        BottomListDialog(
            mContext,
            arrayListOf("Item 1", "Item 2", "Item 3")
        ).setOnItemClickListener { _, item ->
            toast(item)
        }
    }
    private val mLocateListDialog: LocateListDialog by lazy {
        LocateListDialog(
            mContext,
            arrayListOf("Item 1", "Item 2", "Item 3")
        ).setOnItemClickListener { position, item ->
            toast(item)
        }
    }
    private val mBottomGridDialog: BottomGridDialog by lazy {
        BottomGridDialog(
            mContext, arrayListOf(
                BottomGridDialog.GridItem(R.drawable.iframe, "Item 1", 1),
                BottomGridDialog.GridItem(R.drawable.iframe, "Item 2", 2),
                BottomGridDialog.GridItem(R.drawable.iframe, "Item 3", 3)
            )
        ).setOnItemClickListener { _, item ->
            toast(item.name)
        }
    }

    override fun bindLayoutResId(): Int = R.layout.activity_test

    override fun initialize() {
        initData()
        initView()
        initListener()
        doSomeThing()
    }

    private fun initData() {
        //解析全国省份字符串
        addDisposable(DialogHelper.parseArea { area = it })
    }

    private fun initView() {

    }

    private fun initListener() {
        mBtnGlide.onClick(listener)
        mBtnRetrofit.onClick(listener)
        mBtnBus.onClick(listener)
        mBtnSW.onClick(listener)
        mBtnDialog.onClick(listener)
        mBtnDown.onClick(listener)
        mBtnNine.onClick(listener)
        mBtnStatus.onClick(listener)
        mBtnMatisse.onClick(listener)
        mBtnQrcode.onClick(listener)
        mBtnNotification.onClick(listener)
        mBtnMap.onClick(listener)
        mBtnAnim.onClick(listener)
        mBtnShare.onClick(listener)
        mBtnWeb.onClick(listener)
        mBtnMulti.onClick(listener)
    }

    private fun doSomeThing() {

    }

    private val listener = object : OnNotFastClickListener {
        override fun onNotFastClick(view: View) {
            when (view.id) {
                R.id.mBtnGlide -> {
                    addDisposable(ImageLoadHelper.clearDiskCache(mContext))
                    ImageLoadHelper.clearMemoryCache(mContext)
                }
                R.id.mBtnRetrofit -> {
                    startActivity<RetrofitActivity>()
                }
                R.id.mBtnBus -> {
                    startActivity<BusSubscribeActivity>()
                }
                R.id.mBtnSW -> {
                    startActivity<SmallestWidthActivity>()
                }
                R.id.mBtnDialog -> {
                    when (index++ % 7) {
                        0 -> {
                            area?.let {
                                DialogHelper.selectCity(mContext, it) { city ->
                                    toast(city)
                                }
                            }
                        }
                        1 -> {
                            DialogHelper.selectTime(mContext) { time ->
                                toast(time)
                            }
                        }
                        2 -> mAlertDialog.show()
                        3 -> mBottomListDialog.show()
                        4 -> mBottomGridDialog.show()
                        5 -> mLocateListDialog.showHorizontal(view)
                        6 -> mLoadDialog.show()
                    }
                }
                R.id.mBtnDown -> {
                    startActivity<DownLoadActivity>()
                }
                R.id.mBtnNine -> {
                    startActivity<NineGridActivity>()
                }
                R.id.mBtnStatus -> {
                    startActivity<LoadSirActivity>()
                }
                R.id.mBtnMatisse -> {
                    startActivity<MatisseSelectActivity>()
                }
                R.id.mBtnQrcode -> {
                    RouterManager.get().startQrCodeActivityForResult(mActivity, REQUEST_CODE_QRCODE)
                }
                R.id.mBtnNotification -> {
                    startActivity<NotificationActivity>()
                }
                R.id.mBtnMap -> {
                    startActivity<MapActivity>()
                }
                R.id.mBtnAnim -> {
                    startActivity<AnimActivity>()
                }
                R.id.mBtnShare -> {
                    startActivity<ShareFromActivity>()
                }
                R.id.mBtnWeb -> {
                    RouterManager.get().startWebActivity("https://www.baidu.com", "百度一下")
                }
                R.id.mBtnMulti -> {
                    startActivity<MultiRecyclerActivity>()
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_QRCODE) {
            val message = QrcodeHelper.obtainQrcode(intent)
            toast(message)
        }
    }

}
