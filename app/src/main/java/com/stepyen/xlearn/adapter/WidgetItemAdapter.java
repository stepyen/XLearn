package com.stepyen.xlearn.adapter;
import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseRecyclerAdapter;
import com.stepyen.xlearn.base.RecyclerViewHolder;
import com.xuexiang.xpage.model.PageInfo;

import java.util.List;

public class WidgetItemAdapter extends BaseRecyclerAdapter<PageInfo> {

    public WidgetItemAdapter(List<PageInfo> list) {
        super(list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.layout_widget_item;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, PageInfo item) {
        holder.getTextView(R.id.item_name).setText(item.getName());
        if (item.getExtra() != 0) {
            holder.getImageView(R.id.item_icon).setImageResource(item.getExtra());
        }
    }

}
