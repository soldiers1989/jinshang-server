package project.jinshang.common.bean;

import com.github.pagehelper.PageInfo;
import mizuki.project.core.restserver.config.BasicRet;

/**
 * create : wyh
 * date : 2017/11/25
 */
public class PageRet extends BasicRet{

    public class PageInfoData{
        private  PageInfo pageInfo;

        public PageInfo getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
        }
    }

    public   PageInfoData data = new PageInfoData();

    public PageInfoData getData() {
        return data;
    }

    public void setData(PageInfoData data) {
        this.data = data;
    }
}
