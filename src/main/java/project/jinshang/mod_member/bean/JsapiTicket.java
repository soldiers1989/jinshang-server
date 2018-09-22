package project.jinshang.mod_member.bean;

/**
 * 微信全局JsapiTicket
 *
 * @author xiazy
 * @create 2018-06-16 16:47
 **/
public class JsapiTicket {
    private String errcode;
    private String errmsg;
    private String ticket;
    private String expires_in;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
