package project.jinshang.mod_front.bean;

import mizuki.project.core.restserver.config.BasicRet;

/**
 * ShowCateRetByType
 *
 * @author xiazy
 * @create 2018-08-01 15:16
 **/
public class ShowCateRetByType  extends BasicRet{
    public ShowCateDataByType data=new ShowCateDataByType();

    public ShowCateDataByType getData() {
        return data;
    }

    public void setData(ShowCateDataByType data) {
        this.data = data;
    }
}
