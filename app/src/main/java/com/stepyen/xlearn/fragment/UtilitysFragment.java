
package com.stepyen.xlearn.fragment;
import com.stepyen.xlearn.AppPageConfig;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.model.PageInfo;

import java.util.List;

@Page(name = "工具", anim = CoreAnim.none)
public class UtilitysFragment extends BaseHomeFragment {

    @Override
    protected List<PageInfo> getPageContents() {
        return AppPageConfig.getInstance().getUtils();
    }
}
