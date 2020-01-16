package com.stepyen.xlearn.fragment.basics.image
import android.graphics.BitmapFactory
import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BaseFragment
import com.stepyen.xlearn.utils.FileUtil
import com.stepyen.xutil.tip.ToastUtils
import com.xuexiang.xpage.annotation.Page
import kotlinx.android.synthetic.main.fragment_image.*

/**
 * date：2019/7/5
 * author：stepyen
 * description：图片 学习
 */
@Page(name = "图片", extra = R.drawable.ic_widget_imageview)
class ImageFragment : BaseFragment() {

    companion object {
        private const val TAG = "ImageFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_image
    }

    override fun initViews() {

    }

    override fun kotlinInitViews() {


        sava_resource_image.setOnClickListener {
            savaResourceImage()
        }
        sava_network_image.setOnClickListener {
            savaNetworkImage()
        }

    }

    private fun savaResourceImage() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_dog)
        FileUtil.saveImageWithBitmap(context, bitmap)
        ToastUtils.toast("保存图片成功")
    }

    private fun savaNetworkImage() {}


}