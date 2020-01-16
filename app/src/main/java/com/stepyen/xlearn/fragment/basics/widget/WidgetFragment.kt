package com.stepyen.xlearn.fragment.basics.widget

import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.ComponentContainerFragment
import com.stepyen.xlearn.fragment.basics.animation.FrameAnimotionFragment
import com.stepyen.xlearn.fragment.basics.animation.ShowAnimotionFragment
import com.xuexiang.xpage.annotation.Page

/**
 * date：2020-01-16
 * author：stepyen
 * description: 学习 android 控件
 *
 */
@Page(name = "控件", extra = R.drawable.ic_widget_imageview)
class WidgetFragment : ComponentContainerFragment() {
    override fun getPagesClasses(): Array<Class<*>> {
       return arrayOf(

               TextViewFragment::class.java,
               ImageViewFragment::class.java,
               DialogFragment::class.java,
               ConstraintLayoutFragment::class.java


       )
    }

}


