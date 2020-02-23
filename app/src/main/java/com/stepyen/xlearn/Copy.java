package com.stepyen.xlearn;

/**
 * date：2019/7/18
 * author：stepyen
 * description：
 */
public class Copy   {


    /**
     * id : 8
     * time : 2020-02-03 09:14:43
     * sendId : 1
     */

    private int id;
    private String time;
    private String sendId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }
}



//@Page(name = "动画", extra = R.drawable.ic_widget_imageview)
//public class AimationFragment extends ComponentContainerFragment {
//    @Override
//    protected Class[] getPagesClasses() {
//        return new Class[]{
//                FrameAnimotionFragment.class,
//                ShowAnimotionFragment.class
//        };
//    }
//}



//@Page(name = "帧动画")
//public class FrameAnimotionFragment extends BaseTestFragment {
//
//    @Override
//    public void initLayoutView() {
//        addView(R.layout.fragment_frame_animotion);
//    }
//
//    @Override
//    protected void initViews() {
//
//    }
//
//
//    @OnClick({R.id.btn_switch})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_switch:
//
//                break;
//        }
//    }
//}
