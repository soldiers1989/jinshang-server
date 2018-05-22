package project.jinshang.mod_front.bean;

import mizuki.project.core.restserver.config.BasicRet;

import java.io.Serializable;
import java.util.List;

/**
 * create : wyh
 * date : 2018/2/8
 */
public   class  ShowCateRet extends BasicRet implements Serializable {

    private  ShowCateData data = new ShowCateData();

    public ShowCateData getData() {
        return data;
    }

    public void setData(ShowCateData data) {
        this.data = data;
    }
}


