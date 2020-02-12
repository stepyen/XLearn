package com.stepyen.xlearn.fragment.basics.UI.RecyclerView;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseRecyclerAdapter;
import com.stepyen.xlearn.base.RecyclerViewHolder;

import java.util.ArrayList;

/**
 * date：2019/9/30
 * author：stepyen
 * description：
 */
public class RccAdapter extends BaseRecyclerAdapter<String> {
    public RccAdapter(ArrayList<String> data) {
        super(data);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.vh_rcc;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, String item) {
        holder.text(R.id.tv_rcc, item);
    }
}
