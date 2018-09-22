package project.jinshang.scheduled.Bean;

/**
 * productInfo
 *
 * @author xiazy
 * @create 2018-07-20 16:00
 **/
public class ProductStoreInfo {
    long id;
    long pdid;
    long freightmode;
    long memberid;
    long shippingtemplatesgroup;

    public long getPdid() {
        return pdid;
    }

    public void setPdid(long pdid) {
        this.pdid = pdid;
    }

    public long getFreightmode() {
        return freightmode;
    }

    public void setFreightmode(long freightmode) {
        this.freightmode = freightmode;
    }

    public long getMemberid() {
        return memberid;
    }

    public void setMemberid(long memberid) {
        this.memberid = memberid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShippingtemplatesgroup() {
        return shippingtemplatesgroup;
    }

    public void setShippingtemplatesgroup(long shippingtemplatesgroup) {
        this.shippingtemplatesgroup = shippingtemplatesgroup;
    }
}
