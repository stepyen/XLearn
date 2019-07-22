package com.stepyen.xlearn.fragment.expands.countdown;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseFragment;
import com.stepyen.xlearn.fragment.expands.countdown.CountDownAdapter;
import com.stepyen.xlearn.fragment.expands.countdown.CountDownEntity;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import butterknife.BindView;

/**
 * date：2019/7/5
 * author：stepyen
 * description：
 */
@Page(name = "列表倒计时", extra = R.drawable.ic_widget_imageview)
public class RecycleViewCountDownFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycleview_count_down;
    }

    @Override
    protected void initViews() {
        ArrayList<CountDownEntity> data = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            CountDownEntity entity = new CountDownEntity();
            entity.title = "标题" + (i + 1);
            entity.time = (i + 1) * 100;
            data.add(entity);
        }

        // 防止更新item时防止抖动
        ((SimpleItemAnimator) mRv.getItemAnimator()).setSupportsChangeAnimations(false);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRv.setAdapter(new CountDownAdapter(getContext(),data));
    }

}