package project.jinshang.mod_common.bean;

import mizuki.project.core.restserver.config.BasicRet;

/**
 * create : wyh
 * date : 2017/11/20
 */
public class BasicExtRet extends BasicRet {

    private  Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
