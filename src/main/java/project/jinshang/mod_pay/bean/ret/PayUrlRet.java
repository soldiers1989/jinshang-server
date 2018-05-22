package project.jinshang.mod_pay.bean.ret;

import mizuki.project.core.restserver.config.BasicRet;

public class PayUrlRet extends BasicRet{
    private PayUrlRetData data = new PayUrlRetData();
    public class PayUrlRetData{
        private String url;

        public String getUrl() {
            return url;
        }

        public PayUrlRetData setUrl(String url) {
            this.url = url;
            return this;
        }
    }

    public PayUrlRetData getData() {
        return data;
    }

    public PayUrlRet setData(PayUrlRetData data) {
        this.data = data;
        return this;
    }
}
