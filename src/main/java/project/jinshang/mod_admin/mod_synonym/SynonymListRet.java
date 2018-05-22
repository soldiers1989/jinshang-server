package project.jinshang.mod_admin.mod_synonym;

import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.config.BasicRet;

import java.util.List;

public class SynonymListRet extends BasicRet{
    public class SynonymListRetData{
        private PageInfo pageInfo;

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }
    }
    private SynonymListRetData data = new SynonymListRetData();

    public SynonymListRetData getData() {
        return data;
    }

    public SynonymListRet setData(SynonymListRetData data) {
        this.data = data;
        return this;
    }
}
