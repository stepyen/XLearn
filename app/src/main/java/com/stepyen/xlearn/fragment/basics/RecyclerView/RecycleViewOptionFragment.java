package com.stepyen.xlearn.fragment.basics.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.stepyen.xlearn.R;
import com.stepyen.xlearn.base.BaseTestFragment;
import com.stepyen.xlearn.fragment.basics.custom.CustomStyleView;
import com.umeng.commonsdk.debug.I;
import com.xuexiang.xpage.annotation.Page;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * date：2019/7/24
 * author：stepyen
 * description：自定义主题样式
 */
@Page(name = "RecycleView操作刷新")
public class RecycleViewOptionFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        View view = addView(R.layout.fragment_rcc_option);
        RecyclerView rcc = view.findViewById(R.id.rcc);
        Button btn_rcc_diffUtil_update  = view.findViewById(R.id.btn_rcc_diffUtil_update);
        rcc.setLayoutManager(new LinearLayoutManager(getContext()));
        rcc.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        ArrayList<String> oldData = getData();
        RccAdapter adapter = new RccAdapter(oldData);
        rcc.setAdapter(adapter);

        List<String> newList = getData();
//        newList.set(4, "我改变了");
        newList.add("我变化了啊");


        // 测试 DiffUtil
        btn_rcc_diffUtil_update.setOnClickListener(v->{
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallBack(oldData, newList), true);
            result.dispatchUpdatesTo(adapter);
        });

    }

    @Override
    public void initLayoutView() {

    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("数据" + i);
        }

        return data;
    }


    public static class DiffCallBack extends DiffUtil.Callback{

        private List<String> mOldList;
        private List<String> mNewList;

        public DiffCallBack(List<String> oldList, List<String> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
        }
    }
}